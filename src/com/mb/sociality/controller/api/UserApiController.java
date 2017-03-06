package com.mb.sociality.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.sociality.base.BaseController;
import com.mb.sociality.model.User;
import com.mb.sociality.service.UserService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.RequestVO;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.form.UserCreateVO;
import com.mb.sociality.vo.form.UserUpdateVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/api/user")
public class UserApiController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody ResponseVO list(){
		List<User> userList = (List<User>) userService.select();
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(userList);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO select(@PathVariable int id){
		ResponseVO responseVO = new ResponseVO();
		
		Object user = userService.selectByPrimaryKey(id);
		if(user != null){
			responseVO.setMessage(Status.SELECT_USER_MSG001.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(user);
		return responseVO;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO create(HttpServletRequest request, @RequestBody @Valid RequestVO requestVO, BindingResult result) throws JsonParseException, JsonMappingException, IOException{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		UserCreateVO user = new ObjectMapper().readValue(requestVO.getData(), UserCreateVO.class);
		
		if(userService.selectByAccount(user.getAccount()) != null){
			responseVO.setMessage(Status.CREATE_USER_MSG001.getMessage());
			return responseVO;
		}
		
		if(!StringUtils.equals(user.getPassword(), user.getReplyPassword())){
			responseVO.setMessage(Status.CREATE_USER_MSG002.getMessage());
			return responseVO;
		}

		File file = ShareTool.getUploadFile(baseDir, user.getFile(), user.getAccount());
		user.setImg(file == null ? "" : file.getAbsolutePath());

		if(userService.create(user) == null){
			responseVO.setMessage(Status.CREATE_USER_MSG003.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(user);
		return responseVO;
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO update(HttpServletRequest request, @RequestBody @Valid RequestVO requestVO, BindingResult result) throws JsonParseException, JsonMappingException, IOException{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO; 
		}
		

		UserUpdateVO user = new ObjectMapper().readValue(requestVO.getData(), UserUpdateVO.class);
		
		if(userService.selectByLoginInfo(user) == null){
			responseVO.setMessage(Status.UPDATE_USER_MSG002.getMessage());
			return responseVO; 
		}

		File file = ShareTool.getUploadFile(baseDir, user.getFile(), user.getAccount());
		user.setImg(file == null ? null : file.getAbsolutePath());
		user.setPassword(user.getNewPassword());
		
		if(userService.update(user) == null){
			responseVO.setMessage(Status.UPDATE_USER_MSG001.getMessage());
			return responseVO; 
		}

		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(user);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/{account}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO delete(@PathVariable String account){
		ResponseVO responseVO = new ResponseVO();
		
		User user = userService.selectByAccount(account);
		if(user != null){
			File file = new File(user.getImg());
			if(file.exists()){
				file.delete();
			}
		}
		
		int status = userService.deleteByAccount(account);
		responseVO.setMessage(status > 0 ? Status.SUCCESS.getMessage() : Status.DELETE_USER_MSG001.getMessage());
		responseVO.setResult(account);
		
		return responseVO;
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	public @ResponseBody ResponseVO encrypt(@RequestBody String data) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(ShareTool.encrypt(Constant.IV, Constant.KEY, data));
		return responseVO;
	}

	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
	public @ResponseBody ResponseVO decrypt(@RequestBody String data) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(ShareTool.decrypt(Constant.IV, Constant.KEY, data));
		return responseVO;
	}
	
	/*
	 * Call 別支API 時的情境
	 */

	@RequestMapping(value = "/userApi", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO listApi() throws Exception{
		return ShareTool.callApi(Constant.USER_API, "", "get");
	}

	@RequestMapping(value = "/userApi/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO selectApi(@PathVariable String id) throws Exception{
		ResponseVO responseVO = ShareTool.callApi(Constant.USER_API, id, "get");
		return responseVO;
	}

	@RequestMapping(value = "/userApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO createApi(@RequestBody @Valid RequestVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		responseVO = ShareTool.callApi(Constant.USER_API, ShareTool.toJsonStringBuilder(requestVO), "post");
		return responseVO;
	}

	@RequestMapping(value = "/userApi", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateApi(@RequestBody @Valid RequestVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO; 
		}
		
		responseVO = ShareTool.callApi(Constant.USER_API, ShareTool.toJsonStringBuilder(requestVO), "put");
		
		return responseVO;
	}

	@RequestMapping(value = "/userApi/{account}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteApi(@PathVariable String account) throws Exception{
		ResponseVO responseVO = ShareTool.callApi(Constant.USER_API, account, "delete");
		return responseVO;
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
