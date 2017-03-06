package com.mb.sociality.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mb.sociality.base.BaseController;
import com.mb.sociality.model.BBussinessCardAttribute;
import com.mb.sociality.model.BBussinessCardCategory;
import com.mb.sociality.model.BBussinessCardStatus;
import com.mb.sociality.model.BScheduledProject;
import com.mb.sociality.service.BBussinessCardAttributeService;
import com.mb.sociality.service.BBussinessCardCategoryService;
import com.mb.sociality.service.BBussinessCardStatusService;
import com.mb.sociality.service.BScheduledProjectService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.SimpleFieldVO;
import com.mb.sociality.vo.SocialityVO;
import com.mb.sociality.vo.form.AttributeCreateVO;
import com.mb.sociality.vo.form.AttributeUpdeteVO;
import com.mb.sociality.vo.form.CategoeryUpdeteVO;
import com.mb.sociality.vo.form.ScheduledCreateVO;
import com.mb.sociality.vo.form.ScheduledUpdeteVO;
import com.mb.sociality.vo.form.StatusCreateVO;
import com.mb.sociality.vo.form.StatusUpdeteVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;


@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/sociality")
public class SocialityController extends BaseController{
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private BBussinessCardCategoryService categoryService;
	@Autowired
	private BBussinessCardAttributeService attributeService;
	@Autowired
	private BBussinessCardStatusService statusService;
	@Autowired
	private BScheduledProjectService scheduledService;
	@Autowired
	private DBussinessCardService cardService;
	
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
		
//	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody Object login(HttpServletRequest request, @Valid User user, BindingResult result) throws Exception{
//		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
//			return new ModelAndView(Constant.USER_LOGIN);
//		}
//		
//		if(result.hasErrors()){
//			List<FieldError> errorList = result.getFieldErrors();
//			return new ModelAndView(Constant.USER_LOGIN, "errorList", errorList);
//		}
//		
////		if(userService.selectByLoginInfo(user) == null){
////			return new ModelAndView(Constant.USER_LOGIN, "msg", Status.LOGIN_MSG001.getMessage());
////		}
//
//		httpSession.setAttribute("account", user.getAccount());
//		httpSession.setAttribute("password", user.getPassword());
//
//		return list(request);
//	}
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public @ResponseBody Object logout(HttpServletRequest request) throws Exception{
//		if(httpSession.getAttributeNames().hasMoreElements()){
//			httpSession.invalidate();
//		}
//		
//		return list(request);
//	}

	@RequestMapping(value = "/category_data" , method = RequestMethod.POST , produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked")
	public @ResponseBody Object category_data(HttpServletRequest request) throws Exception{
		
		List<BBussinessCardCategory> categoriesList = (List<BBussinessCardCategory>) categoryService.select();
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (BBussinessCardCategory bBussinessCardCategory : categoriesList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", bBussinessCardCategory.getName());
			map.put("guid", bBussinessCardCategory.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("rows", list);
		map2.put("total", categoriesList.size());
		socialityVO.setRows(list);
		socialityVO.setTotal(categoriesList.size());

		return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "map2", JSONObject.fromObject(map2));
	}
	

	
	@RequestMapping(value = "/scheduled_list" , method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object scheduled_list(HttpServletRequest request) throws Exception{

		List<BScheduledProject> categoryList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
	    		categoryList = (List<BScheduledProject>) scheduledService.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			categoryList = (List<BScheduledProject>) scheduledService.select();
		}
		
	    //categoryList.forEach(category -> user.setImg(ShareTool.getImgSrc(baseUri, user.getImg())));
	    Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_SCHEDULED_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/scheduled_create", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object scheduled_create(HttpServletRequest request, @Valid ScheduledCreateVO attribute, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_CREATE);
		}
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_LIST, "errorMsg", responseVO);
		}

		if(scheduledService.selectByName(attribute.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_LIST, "errorMsg", responseVO);
		}
		
		scheduledService.create(attribute);
		List<BBussinessCardStatus> categoryList = (List<BBussinessCardStatus>) statusService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_SCHEDULED_LIST, "categoryMap", map);
	}

	@RequestMapping(value = "/scheduled_update/{guid}", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object scheduled_update(HttpServletRequest request, @Valid ScheduledUpdeteVO category, BindingResult result, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			//user.setAccount((String) httpSession.getAttribute("account"));
			BScheduledProject categoryData = (BScheduledProject) scheduledService.selectByGuid(guid);
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_UPDATE, "category", categoryData);
		}

		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_UPDATE, "errorMsg", responseVO);
		}

		if(scheduledService.selectByName(category.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_SCHEDULED_UPDATE, "errorMsg", responseVO);
		}
		
		category.setGuid(category.getGuid());
		scheduledService.update(category);
		List<BScheduledProject> categoryList = (List<BScheduledProject>) scheduledService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_SCHEDULED_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/scheduled_view/{guid}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object scheduled_view(HttpServletRequest request, @PathVariable String guid) throws Exception{
		BScheduledProject categoryData = (BScheduledProject) scheduledService.selectByGuid(guid);
	     txManager.commit(status);
		 return new ModelAndView(Constant.SOCIALITY_SCHEDULED_VIEW, "category", categoryData);

	}
	
	@RequestMapping(value = "/status_list" , method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object status_list(HttpServletRequest request) throws Exception{
		List<BBussinessCardStatus> categoryList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
	    		categoryList = (List<BBussinessCardStatus>) statusService.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			categoryList = (List<BBussinessCardStatus>) statusService.select();
		}
		
	    //categoryList.forEach(category -> user.setImg(ShareTool.getImgSrc(baseUri, user.getImg())));
	    Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_STATUS_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/status_create", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object status_create(HttpServletRequest request, @Valid StatusCreateVO attribute, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.SOCIALITY_STATUS_CREATE);
		}
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_STATUS_CREATE, "errorMsg", responseVO);
		}

		if(statusService.selectByName(attribute.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_STATUS_CREATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			attribute.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		statusService.create(attribute);
		List<BBussinessCardStatus> categoryList = (List<BBussinessCardStatus>) statusService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_STATUS_LIST, "categoryMap", map);
	}

	@RequestMapping(value = "/status_update/{guid}", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object status_update(HttpServletRequest request, @Valid StatusUpdeteVO category, BindingResult result, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			//user.setAccount((String) httpSession.getAttribute("account"));
			BBussinessCardStatus categoryData = (BBussinessCardStatus) statusService.selectByGuid(guid);
			return new ModelAndView(Constant.SOCIALITY_STATUS_UPDATE, "category", categoryData);
		}

		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_STATUS_UPDATE, "errorMsg", responseVO);
		}

		if(statusService.selectByName(category.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_STATUS_UPDATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			category.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		category.setGuid(category.getGuid());
		statusService.update(category);
		List<BBussinessCardStatus> categoryList = (List<BBussinessCardStatus>) statusService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_STATUS_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/status_view/{guid}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object status_view(HttpServletRequest request, @PathVariable String guid) throws Exception{
		BBussinessCardStatus categoryData = (BBussinessCardStatus) statusService.selectByGuid(guid);
	     txManager.commit(status);
		 return new ModelAndView(Constant.SOCIALITY_STATUS_VIEW, "category", categoryData);

	}
	
	@RequestMapping(value = "/attribute_list" , method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object attribute_list(HttpServletRequest request) throws Exception{
	    
		List<BBussinessCardAttribute> categoryList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
	    		categoryList = (List<BBussinessCardAttribute>) attributeService.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			categoryList = (List<BBussinessCardAttribute>) attributeService.select();
		}
		
	    Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/attribute_create", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object attribute_create(HttpServletRequest request, @Valid AttributeCreateVO attribute, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_CREATE);
		}
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_CREATE, "errorMsg", responseVO);
		}

		if(attributeService.selectByName(attribute.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_CREATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			attribute.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		attributeService.create(attribute);
		List<BBussinessCardCategory> categoryList = (List<BBussinessCardCategory>) attributeService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_LIST, "categoryMap", map);
	}

	@RequestMapping(value = "/attribute_update/{guid}", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object attribute_update(HttpServletRequest request, @Valid AttributeUpdeteVO category, BindingResult result, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			//user.setAccount((String) httpSession.getAttribute("account"));
			BBussinessCardAttribute categoryData = (BBussinessCardAttribute) attributeService.selectByGuid(guid);
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_UPDATE, "category", categoryData);
		}

		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_UPDATE, "errorMsg", responseVO);
		}

		if(attributeService.selectByName(category.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_UPDATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			category.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		category.setGuid(category.getGuid());
		attributeService.update(category);
		List<BBussinessCardCategory> categoryList = (List<BBussinessCardCategory>) attributeService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/attribute_view/{guid}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object attribute_view(HttpServletRequest request, @PathVariable String guid) throws Exception{
		BBussinessCardAttribute categoryData = (BBussinessCardAttribute) attributeService.selectByGuid(guid);
	     txManager.commit(status);
		 return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_VIEW, "category", categoryData);

	}

	@RequestMapping(value = "/attribute_delete", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object attribute_delete(HttpServletRequest request, @RequestParam(required = false) String guid) throws Exception{	
		//delete fk exception
		if(cardService.selectByFKGuid(guid) != null){
			ResponseVO responseVO = new ResponseVO();
			responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
			return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_LIST, "errorMsg", responseVO);
		}
		
		attributeService.deleteByGuid(guid);

		List<BBussinessCardAttribute> attributeList = (List<BBussinessCardAttribute>) categoryService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("attributeList", attributeList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_ATTRIBUTE_LIST, "attributeMap", map);
	}
	
	@RequestMapping(value = "/category_list" , method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object category_list(HttpServletRequest request) throws Exception{
	   
		List<BBussinessCardCategory> categoryList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
	    		categoryList = (List<BBussinessCardCategory>) categoryService.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			categoryList = (List<BBussinessCardCategory>) categoryService.select();
		}
		
	    Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/category_create", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object category_create(HttpServletRequest request, @Valid BBussinessCardCategory category, BindingResult result) throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_CREATE);
		}
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_CREATE, "errorMsg", responseVO);
		}

		if(categoryService.selectByName(category.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_CREATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			category.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		categoryService.create(category);
		List<BBussinessCardCategory> categoryList = (List<BBussinessCardCategory>) categoryService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "categoryMap", map);
	}

	@RequestMapping(value = "/category_update/{guid}", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object category_update(HttpServletRequest request, @Valid CategoeryUpdeteVO category, BindingResult result, @PathVariable String guid) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){
			//user.setAccount((String) httpSession.getAttribute("account"));
			BBussinessCardCategory categoryData = (BBussinessCardCategory) categoryService.selectByGuid(guid);
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_UPDATE, "category", categoryData);
		}

		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_UPDATE, "errorMsg", responseVO);
		}

		if(categoryService.selectByName(category.getName())!= null){
			responseVO.setResult(Status.CREATE_USER_MSG001.getMessage());
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_UPDATE, "errorMsg", responseVO);
		}
		
		if(httpSession.getAttribute("guid") != null){
			category.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		category.setGuid(category.getGuid());
		categoryService.update(category);
		List<BBussinessCardCategory> categoryList = (List<BBussinessCardCategory>) categoryService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "categoryMap", map);
	}
	
	@RequestMapping(value = "/category_view/{guid}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object category_view(HttpServletRequest request, @PathVariable String guid) throws Exception{
		BBussinessCardCategory categoryData = (BBussinessCardCategory) categoryService.selectByGuid(guid);
	     txManager.commit(status);
		 return new ModelAndView(Constant.SOCIALITY_CATEGORY_VIEW, "category", categoryData);

	}

	@RequestMapping(value = "/category_delete", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object category_delete(HttpServletRequest request, @RequestParam(required = false) String guid) throws Exception{	
		//delete fk exception
		if(cardService.selectByFKGuid(guid) != null){
			ResponseVO responseVO = new ResponseVO();
			responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
			return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "errorMsg", responseVO);
		}
		
		categoryService.deleteByGuid(guid);

		List<BBussinessCardCategory> categoryList = (List<BBussinessCardCategory>) categoryService.select();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", categoryList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CATEGORY_LIST, "categoryMap", map);
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
