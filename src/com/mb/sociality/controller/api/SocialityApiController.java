package com.mb.sociality.controller.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.vo.MenuTreeVO;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.SocialityVO;
import com.mb.sociality.vo.form.AttributeDeleteVO;
import com.mb.sociality.vo.form.CategoryDeleteVO;
import com.mb.sociality.vo.form.ScheduledDeleteVO;
import com.mb.sociality.vo.form.StatusDeleteVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/sociality/api")
public class SocialityApiController extends BaseController{
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private BBussinessCardCategoryService service;
	@Autowired
	private BBussinessCardAttributeService attributeService;
	@Autowired
	private BBussinessCardStatusService statusService;
	@Autowired
	private BScheduledProjectService scheduledService;
	@Autowired
	private DBussinessCardService cardService;
	@Autowired
	private HttpSession httpSession;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
		
	@RequestMapping(value = "/menuTree", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody String menuTree(HttpServletRequest request){
	
		//String host = ShareTool.getProperty("host");
		String statusURL = baseUri+"/sociality/status_list";
		String categoryURL = baseUri+"/sociality/category_list";
		String attributeURL = baseUri+"/sociality/attribute_list";
		String scheduledURL = baseUri+"/sociality/scheduled_list";
		String cardURL = baseUri+"/sociality/card/card_list";
		String scheduled_futureURL = baseUri+"/sociality/scheduled/scheduled_future";
		String scheduled_historyURL = baseUri+"/sociality/scheduled/scheduled_history";
		
		List<MenuTreeVO> list = new LinkedList<>();
		String[] titleArray = {"狀態","屬性","類別","預定項目","名片","行程管理","行程記錄"};
		String breadcrumbArray = "業務人脈>";
		String[] urlArray = {statusURL,attributeURL,categoryURL,scheduledURL,cardURL,scheduled_futureURL,scheduled_historyURL};
		
		for (int i = 0; i < titleArray.length; i++) {
			MenuTreeVO menuTreeVO = new MenuTreeVO();
			menuTreeVO.setTitle(titleArray[i]);
			menuTreeVO.setUrl(urlArray[i]);
			menuTreeVO.setBreadcrumb(breadcrumbArray+titleArray[i]);
			
			list.add(menuTreeVO);
		}
		
		String str = request.getParameter("callback")+"("+JSONArray.fromObject(list)+")";
		return str;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO list(){
		
		List<BBussinessCardCategory> categoriesList;
		if (httpSession.getAttribute("guid") != null) {
			categoriesList = (List<BBussinessCardCategory>) service.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			categoriesList = (List<BBussinessCardCategory>) service.select();
		}
		
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
		
		socialityVO.setRows(list);
		socialityVO.setTotal(categoriesList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/scheduled_list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO scheduled_list(){
		
		List<BScheduledProject> categoriesList;
		if (httpSession.getAttribute("guid") != null) {
			categoriesList = (List<BScheduledProject>) scheduledService.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			categoriesList = (List<BScheduledProject>) scheduledService.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (BScheduledProject bBussinessCardCategory : categoriesList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", bBussinessCardCategory.getName());
			map.put("guid", bBussinessCardCategory.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(categoriesList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/attribute_list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO attribute_list(){
		
		List<BBussinessCardAttribute> categoriesList;
		if (httpSession.getAttribute("guid") != null) {
			categoriesList = (List<BBussinessCardAttribute>) attributeService.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			categoriesList = (List<BBussinessCardAttribute>) attributeService.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (BBussinessCardAttribute bBussinessCardCategory : categoriesList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", bBussinessCardCategory.getName());
			map.put("guid", bBussinessCardCategory.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(categoriesList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/status_list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO status_list(){
		
		List<BBussinessCardStatus> categoriesList;
		if (httpSession.getAttribute("guid") != null) {
			categoriesList = (List<BBussinessCardStatus>) statusService.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			categoriesList = (List<BBussinessCardStatus>) statusService.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (BBussinessCardStatus bBussinessCardCategory : categoriesList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", bBussinessCardCategory.getName());
			map.put("guid", bBussinessCardCategory.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(categoriesList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/scheduled_delete", method = RequestMethod.POST)
	public @ResponseBody ResponseVO scheduled_delete(HttpServletRequest request, @RequestBody ScheduledDeleteVO deleteVO){
		
		JSONArray array = JSONArray.fromObject(deleteVO.getPk());
		for (Object object : array) {
			if(cardService.selectByFKGuid(deleteVO.getPk()) != null){
				ResponseVO responseVO = new ResponseVO();
				responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
				responseVO.setStatus("999");
				return responseVO;
			}
			scheduledService.deleteByGuid(object.toString());
		}
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(deleteVO);

		txManager.commit(status);	
		return responseVO;
	}
	
	
	@RequestMapping(value = "/status_delete", method = RequestMethod.POST)
	public @ResponseBody ResponseVO status_delete(HttpServletRequest request, @RequestBody StatusDeleteVO deleteVO){
		
		JSONArray array = JSONArray.fromObject(deleteVO.getPk());
		for (Object object : array) {
			if(cardService.selectByFKGuid(deleteVO.getPk()) != null){
				ResponseVO responseVO = new ResponseVO();
				responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
				responseVO.setStatus("999");
				return responseVO;
			}
			statusService.deleteByGuid(object.toString());
		}
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(deleteVO);

		txManager.commit(status);	
		return responseVO;
	}
	
	@RequestMapping(value = "/category_delete", method = RequestMethod.POST)
	public @ResponseBody ResponseVO delete(HttpServletRequest request, @RequestBody CategoryDeleteVO deleteVO){
				
		JSONArray array = JSONArray.fromObject(deleteVO.getPk());
		for (Object object : array) {
			if(cardService.selectByFKGuid(deleteVO.getPk()) != null){
				ResponseVO responseVO = new ResponseVO();
				responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
				responseVO.setStatus("999");
				return responseVO;
			}
			service.deleteByGuid(object.toString());
		}
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(deleteVO);

		txManager.commit(status);	
		return responseVO;
	}
	
	@RequestMapping(value = "/attribute_delete", method = RequestMethod.POST)
	public @ResponseBody ResponseVO attribute_delete(HttpServletRequest request, @RequestBody AttributeDeleteVO deleteVO){
		
		JSONArray array = JSONArray.fromObject(deleteVO.getPk());
		for (Object object : array) {
			if(cardService.selectByFKGuid(deleteVO.getPk()) != null){
				ResponseVO responseVO = new ResponseVO();
				responseVO.setResult(Status.DELETE_USER_MSG002.getMessage());
				responseVO.setStatus("999");
				return responseVO;
			}
			attributeService.deleteByGuid(object.toString());
		}
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(deleteVO);

		txManager.commit(status);	
		return responseVO;
	}

}
