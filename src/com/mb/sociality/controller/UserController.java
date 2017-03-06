package com.mb.sociality.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mb.sociality.base.BaseController;
import com.mb.sociality.model.User;
import com.mb.sociality.service.UserService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.form.UserCreateVO;
import com.mb.sociality.vo.form.UserUpdateVO;

import lombok.extern.log4j.Log4j;


@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession httpSession;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody Object view(HttpServletRequest request) throws Exception{
		return new ModelAndView(Constant.USER_VIEW);
	}
	
	@RequestMapping(value = "/multiple", method = RequestMethod.GET)
	public @ResponseBody Object multiple(HttpServletRequest request) throws Exception{
		return new ModelAndView(Constant.USER_MULTIPLE);
	}
	
	@RequestMapping(value = "/single", method = RequestMethod.GET)
	public @ResponseBody Object single(HttpServletRequest request) throws Exception{
		return new ModelAndView(Constant.USER_SINGLE);
	}
	
	@RequestMapping(method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object list(HttpServletRequest request) throws Exception{
	    List<User> userList = (List<User>) userService.select();
	    userList.forEach(user -> user.setImg(ShareTool.getImgSrc(baseUri, user.getImg())));
		return new ModelAndView(Constant.USER_LIST, "userList", "");
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object login(HttpServletRequest request, @Valid User user, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.USER_LOGIN);
		}
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			return new ModelAndView(Constant.USER_LOGIN, "errorList", errorList);
		}
		
		if(userService.selectByLoginInfo(user) == null){
			return new ModelAndView(Constant.USER_LOGIN, "msg", Status.LOGIN_MSG001.getMessage());
		}

		httpSession.setAttribute("account", user.getAccount());
		httpSession.setAttribute("password", user.getPassword());

		return list(request);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody Object logout(HttpServletRequest request) throws Exception{
		if(httpSession.getAttributeNames().hasMoreElements()){
			httpSession.invalidate();
		}
		
		return list(request);
	}

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object create(HttpServletRequest request, @Valid UserCreateVO user, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.USER_CREATE);
		}
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			return new ModelAndView(Constant.USER_CREATE, "errorList", errorList);
		}

		if(userService.selectByAccount(user.getAccount()) != null){
			return new ModelAndView(Constant.USER_CREATE, "msg", Status.CREATE_USER_MSG001.getMessage());
		}
		
		if(!StringUtils.equals(user.getPassword(), user.getReplyPassword())){
			return new ModelAndView(Constant.USER_CREATE, "msg", Status.CREATE_USER_MSG002.getMessage());
		}

		File file = ShareTool.getUploadFile(baseDir, user.getFile(), user.getAccount());
		user.setImg(file == null ? "" : file.getAbsolutePath());
		
		if(userService.create(user) == null){
			return new ModelAndView(Constant.USER_CREATE, "msg", Status.CREATE_USER_MSG003.getMessage());
		}

	    txManager.commit(status);
		return new ModelAndView(Constant.USER_CREATE, "msg", Status.SUCCESS.getMessage());
	}

	@RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object update(HttpServletRequest request, @Valid UserUpdateVO user, BindingResult result) throws Exception{
		if(!httpSession.getAttributeNames().hasMoreElements()){
			return new ModelAndView(Constant.USER_LOGIN);
		}
		
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			user.setAccount((String) httpSession.getAttribute("account"));
			return new ModelAndView(Constant.USER_UPDATE, "user", user);
		}

		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			return new ModelAndView(Constant.USER_UPDATE, "errorList", errorList);
		}
		
		if(userService.selectByLoginInfo(user) == null){
			return new ModelAndView(Constant.USER_UPDATE, "msg", Status.UPDATE_USER_MSG002.getMessage());
		}

		File file = ShareTool.getUploadFile(baseDir, user.getFile(), user.getAccount());
		user.setImg(file == null ? null : file.getAbsolutePath());
		user.setPassword(user.getNewPassword());
		
		if(userService.update(user) == null){
			return new ModelAndView(Constant.USER_UPDATE, "msg", Status.UPDATE_USER_MSG001.getMessage());
		}

	    txManager.commit(status);
		return new ModelAndView(Constant.USER_UPDATE, "msg", Status.SUCCESS.getMessage());
	}

	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object delete(HttpServletRequest request, @RequestParam(required = false) String account) throws Exception{
		if(!httpSession.getAttributeNames().hasMoreElements()){
			return new ModelAndView(Constant.USER_LOGIN);
		}
		
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.USER_DELETE);
		}
		
		User user = userService.selectByAccount(account);
		if(user != null){
			File file = new File(user.getImg());
			if(file.exists()){
				file.delete();
			}
		}
		
		if(userService.deleteByAccount(account) == 0){
			return new ModelAndView(Constant.USER_DELETE, "msg", Status.DELETE_USER_MSG001.getMessage());
		}

	    txManager.commit(status);
		return new ModelAndView(Constant.USER_DELETE, "msg", Status.SUCCESS.getMessage());
	}

	/*
	 * Exception Handler
	 */
	
	@Override
	@ExceptionHandler(Exception.class)
	public ResponseVO handleError(HttpServletRequest req, Exception exception) {
		return super.handleError(req, exception);
	}
}
