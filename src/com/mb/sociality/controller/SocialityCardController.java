package com.mb.sociality.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.mb.sociality.model.BEnterpriseEmployee;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.service.BBussinessCardAttributeService;
import com.mb.sociality.service.BBussinessCardCategoryService;
import com.mb.sociality.service.BBussinessCardStatusService;
import com.mb.sociality.service.BEnterpriseEmployeeService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.SimpleFieldVO;
import com.mb.sociality.vo.SocialityCardResponseVO;
import com.mb.sociality.vo.api.LoginInfoVO;
import com.mb.sociality.vo.form.CardCreateVO;
import com.mb.sociality.vo.form.CardUpdeteVO;

import lombok.extern.log4j.Log4j;

@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/sociality/card")
public class SocialityCardController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private DBussinessCardService service;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private BBussinessCardCategoryService categoryService;
	@Autowired
	private BBussinessCardAttributeService attributeService;
	@Autowired
	private BBussinessCardStatusService statusService;
	@Autowired
	private BEnterpriseEmployeeService enterpriceService;
	
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(value = "/card_list" , method = RequestMethod.GET)@SuppressWarnings("unchecked")
	public @ResponseBody Object card_list(HttpServletRequest request) throws Exception{
	    Map<String, Object> map = new HashMap<>();
	    
		List<DBussinessCard> dataList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
			dataList = (List<DBussinessCard>) service.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			dataList = (List<DBussinessCard>) service.select();
		}
		
	    //categoryList.forEach(category -> user.setImg(ShareTool.getImgSrc(baseUri, user.getImg())));
		map.put("categoryList", dataList);
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CARD_LIST, "dataMap", map);
	}
	
	@RequestMapping(value = "/card_contact_list" , method = RequestMethod.GET)
	public @ResponseBody Object card_contact_list(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<>();
		return new ModelAndView(Constant.SOCIALITY_CONTACT_CREATE, "dataMap", map);
	}
	
	@RequestMapping(value = "/card_contact/{guid}" , method = RequestMethod.GET)
	public @ResponseBody Object card_contact(HttpServletRequest request, @PathVariable String guid) throws Exception{
		SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
		responseVO.setGuid(guid);
		httpSession.setAttribute("selectionGuid", guid);
		
		return new ModelAndView(Constant.SOCIALITY_CONTACT_LIST,"responseVO",responseVO);
	}
	
	@RequestMapping(value = "/card_bussiness" , method = RequestMethod.GET)
	public @ResponseBody Object card_bussiness(HttpServletRequest request) throws Exception{
//	   
//		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
//			httpSession.setAttribute("guid", request.getParameter("guid"));
//		}
		return new ModelAndView(Constant.SOCIALITY_CARD_BUSSINESS);
	}
	
	@RequestMapping(value = "/card_create" ,  method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object card_create(HttpServletRequest request, @Valid CardCreateVO createVO, BindingResult result) 
			throws Exception{
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){	
			SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
			responseVO = putDataList(responseVO, request);
			if(httpSession.getAttribute("guid") != null){
				BEnterpriseEmployee employee = enterpriceService.selectByGuid(httpSession.getAttribute("guid").toString());
				responseVO.setEmployeeName(employee.getAccount());
				responseVO.setbEnterpriseEmployeeGuid(employee.getGuid());
			}else {
				BEnterpriseEmployee employee = enterpriceService.selectByGuid("7ca53f13-ce02-422c-a755-15ac9c945334");
				responseVO.setEmployeeName(employee.getAccount());
				responseVO.setbEnterpriseEmployeeGuid(employee.getGuid());
			}
			return new ModelAndView(Constant.SOCIALITY_CARD_CREATE,"responseVO",responseVO);
		}
				
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
			responseVO.setResult(array.toString());
			responseVO = putDataList(responseVO, request);
			return new ModelAndView(Constant.SOCIALITY_CARD_CREATE, "responseVO", responseVO);
		}
		
		
		if (!createVO.getFile().isEmpty()) {
			log.debug("createVO.getFile()"+createVO.getFile().isEmpty());
			File file = ShareTool.getUploadFile(baseDir, createVO.getFile(), createVO.getName());
			createVO.setImg(file == null ? "" : file.getAbsolutePath());
			createVO.setImagePath(file.getAbsolutePath());
		}
		
		//生日String[] dateArray = date.split("/");
		if (StringUtils.isNotBlank(createVO.getBirthday())) {
			if (!checkDate(createVO.getBirthday())) {
				SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
				responseVO.setResult(Status.CREATE_CARD_MSG001.getMessage());
				responseVO = putDataList(responseVO, request);
				return new ModelAndView(Constant.SOCIALITY_CARD_CREATE, "responseVO", responseVO);
			}
			String[] dateArray = createVO.getBirthday().split("/");
			createVO.setBirthdayYear(dateArray[0]);
			createVO.setBirthdayMonth(dateArray[1]);
			createVO.setBirthdayDate(dateArray[2]);
		}
		
		//出事時間
		if (StringUtils.isNotBlank(createVO.getFirstMeetTimeString())) {
			if (!checkDate(createVO.getFirstMeetTimeString())) {
				SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
				responseVO.setResult(Status.CREATE_CARD_MSG001.getMessage());
				responseVO = putDataList(responseVO, request);
				return new ModelAndView(Constant.SOCIALITY_CARD_CREATE, "responseVO", responseVO);
			}
			String pattern = "yyyy/MM/dd";
			Date parseDate = new SimpleDateFormat(pattern).parse(createVO.getFirstMeetTimeString());
			createVO.setFirstMeetTime(parseDate);
		}else{
			String pattern = "yyyy/MM/dd HH:mm:ss";
			Date parseDate = new SimpleDateFormat(pattern).parse("1899/12/31 00:00:00");
			createVO.setFirstMeetTime(parseDate);
		}
		
		if(httpSession.getAttribute("guid") != null){
			createVO.setbCorporationGuid(httpSession.getAttribute("guid").toString());
		}
		
		/*
		 * TODO 寫死，之後要記得改回來
		 */
		
		createVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		service.create(createVO);
		List<DBussinessCard> dataList = (List<DBussinessCard>) service.select();
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CARD_LIST, "dataMap", map);
	}
	
	@RequestMapping(value = "/card_delete", method = {RequestMethod.POST, RequestMethod.GET})@SuppressWarnings("unchecked")
	public @ResponseBody Object card_delete(HttpServletRequest request, @RequestParam(required = false) String guid) throws Exception{	
		service.deleteByGuid(guid);

		List<DBussinessCard> dataList = (List<DBussinessCard>) service.select();
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CARD_LIST, "dataMap", map);
	}
	
	@RequestMapping(value = "/card_update/{guid}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked")
	public @ResponseBody Object card_update(HttpServletRequest request, @Valid CardUpdeteVO updateVO, BindingResult result, @PathVariable String guid) throws Exception{
		SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
		if(StringUtils.equals(RequestMethod.GET.toString(), request.getMethod())){	
			responseVO = fetchDataByGuid(guid, request);
			txManager.commit(status);
			return new ModelAndView(Constant.SOCIALITY_CARD_UPDATE,"responseVO",responseVO);
		}
				
		if(result.hasErrors()){
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO = new SocialityCardResponseVO();
			responseVO = putDataList(responseVO, request);
			responseVO = fetchDataByGuid(updateVO.getGuid(), request);
			responseVO.setResult(array.toString());
			return new ModelAndView(Constant.SOCIALITY_CARD_UPDATE, "responseVO", responseVO);
		}
		
		if (!updateVO.getFile().isEmpty()) {
			log.debug("createVO.getFile()"+updateVO.getFile().isEmpty());
			File file = ShareTool.getUploadFile(baseDir, updateVO.getFile(), updateVO.getGuid());
			updateVO.setImg(file == null ? "" : file.getAbsolutePath());
			updateVO.setImagePath(file.getAbsolutePath());
		}
		
		//生日
		if (StringUtils.isNotBlank(updateVO.getBirthday())) {
			if(!checkDate(updateVO.getBirthday())){
				responseVO = new SocialityCardResponseVO();
				responseVO = fetchDataByGuid(updateVO.getGuid(), request);
				responseVO.setResult(Status.CREATE_CARD_MSG001.getMessage());
				return new ModelAndView(Constant.SOCIALITY_CARD_UPDATE, "responseVO", responseVO);
			}
			String[] dateArray = updateVO.getBirthday().split("/");
			updateVO.setBirthdayYear(dateArray[0]);
			updateVO.setBirthdayMonth(dateArray[1]);
			updateVO.setBirthdayDate(dateArray[2]);
		}
		
		//出事時間
		if (StringUtils.isNotBlank(updateVO.getFirstMeetTimeString())) {
			if(!checkDate(updateVO.getFirstMeetTimeString())){
				responseVO = new SocialityCardResponseVO();
				responseVO = fetchDataByGuid(updateVO.getGuid(), request);
				responseVO.setResult(Status.CREATE_CARD_MSG001.getMessage());
				return new ModelAndView(Constant.SOCIALITY_CARD_UPDATE, "responseVO", responseVO);
			}
			String pattern = "yyyy/MM/dd";
			Date parseDate = new SimpleDateFormat(pattern).parse(updateVO.getFirstMeetTimeString());
			updateVO.setFirstMeetTime(parseDate);
		}else{
			String pattern = "yyyy/MM/dd HH:mm:ss";
			Date parseDate = new SimpleDateFormat(pattern).parse("1899/12/31 00:00:00");
			updateVO.setFirstMeetTime(parseDate);
		}
		
		updateVO.setGuid(updateVO.getGuid());
		log.debug("1234"+updateVO.getGuid());
		service.update(updateVO);
		List<DBussinessCard> dataList = (List<DBussinessCard>) service.select();
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("msg",  Status.SUCCESS.getMessage());
		txManager.commit(status);
		return new ModelAndView(Constant.SOCIALITY_CARD_LIST, "dataMap", map);
	}
	
	@RequestMapping(value = "/card_view/{guid}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Object card_view(HttpServletRequest request, @PathVariable String guid) throws Exception{
		SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
		responseVO = new SocialityCardResponseVO();
		responseVO = putDataList(responseVO, request);
		responseVO = fetchDataByGuid(guid, request);
		
		BEnterpriseEmployee employee = enterpriceService.selectByGuid(responseVO.getbEnterpriseEmployeeGuid());
		responseVO.setEmployeeName(employee.getAccount());
		responseVO.setbEnterpriseEmployeeGuid(employee.getGuid());
		return new ModelAndView(Constant.SOCIALITY_CARD_VIEW, "responseVO", responseVO);
	}
	
	//檢查日期格式
	boolean checkDate(String date){
		String[] dateArray = date.split("/");
		if(dateArray.length != 3 || dateArray[0].length() != 4 || dateArray[1].length() != 2
					|| dateArray[2].length() != 2){
				return false;
		}
		return true;
	}
	
	//置入下拉選單欄位
	@SuppressWarnings("unchecked")
	SocialityCardResponseVO putDataList(SocialityCardResponseVO responseVO,HttpServletRequest request){	
		List<BBussinessCardStatus> statusList;
		List<BBussinessCardCategory> categoryList;
		List<BBussinessCardAttribute> attributeList;
		
		if (httpSession.getAttribute("guid") != null) {
			statusList = statusService.selectByCorporationGuid(request.getParameter("guid"));
			categoryList = categoryService.selectByCorporationGuid(request.getParameter("guid"));
			attributeList = attributeService.selectByCorporationGuid(request.getParameter("guid"));
		}else{
			statusList = (List<BBussinessCardStatus>) statusService.select();
			categoryList = (List<BBussinessCardCategory>)categoryService.select();
			attributeList = (List<BBussinessCardAttribute>)attributeService.select();
		}
		
		responseVO.setAttribute(attributeList);
		responseVO.setCategory(categoryList);
		responseVO.setStatus(statusList);
		return responseVO;
	}
	
	//提取該guid data
	SocialityCardResponseVO fetchDataByGuid(String guid,HttpServletRequest request){
		SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
		try {
			BeanUtils.copyProperties(responseVO, service.selectByGuid(guid));
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error(e, e);
		}
		responseVO = putDataList(responseVO, request);
		//出事時間
		String pattern = "yyyy/MM/dd HH:mm:ss";
		Date parseDate;
		try {
			parseDate = new SimpleDateFormat(pattern).parse("1899/12/31 00:00:00");
			int isDefualt = responseVO.getFirstMeetTime().compareTo(parseDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String firstTime = sdf.format(responseVO.getFirstMeetTime());
			if (isDefualt == 0) {
				responseVO.setFirstMeetTimeString("");
			}else{
				responseVO.setFirstMeetTimeString(firstTime);
			}
		} catch (ParseException e) {
			log.error(e, e);
		}
		BEnterpriseEmployee user = enterpriceService.selectByGuid(responseVO.getbEnterpriseEmployeeGuid());
		responseVO.setEmployeeName(user.getAccount());
		String fixDate = "";
		
		if(StringUtils.isNotBlank(responseVO.getBirthdayYear()) 
				&& StringUtils.isNotBlank(responseVO.getBirthdayMonth()) 
				&& StringUtils.isNotBlank(responseVO.getBirthdayDate())){
			fixDate = String.format("%s/%s/%s", responseVO.getBirthdayYear(),responseVO.getBirthdayMonth(),responseVO.getBirthdayDate());
		}
		responseVO.setBirthday(fixDate);
		
		return responseVO;
	}
}
