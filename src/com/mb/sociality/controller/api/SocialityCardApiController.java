package com.mb.sociality.controller.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.mb.sociality.model.BEnterpriseEmployee;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.DBussinessCardSocical;
import com.mb.sociality.service.BEnterpriseEmployeeService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.service.DBussinessCardSocicalService;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.SocialityCardRequestVO;
import com.mb.sociality.vo.SocialityCardResponseVO;
import com.mb.sociality.vo.SocialityVO;
import com.mb.sociality.vo.form.CardDeleteVO;
import com.mb.sociality.vo.form.ContactCreateVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/sociality/card/api")
public class SocialityCardApiController extends BaseController{
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private DBussinessCardService service;
	@Autowired
	private BEnterpriseEmployeeService employeeService;
	@Autowired
	private	DBussinessCardSocicalService socialService;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	@RequestMapping(value = "/contact_delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody SocialityCardResponseVO contact_delete(@RequestBody ContactCreateVO createVO){
		SocialityCardResponseVO socialityVO = new SocialityCardResponseVO();
		for (String contactGuid : createVO.getGuidList()) {
			socialService.deleteByGuid(contactGuid);
		}
		txManager.commit(status);
		return socialityVO;
	}
	
	@RequestMapping(value = "/contact_create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody SocialityCardResponseVO contact_create(@RequestBody ContactCreateVO createVO){
		SocialityCardResponseVO socialityVO = new SocialityCardResponseVO();
		
		for (String contactGuid : createVO.getGuidList()) {
			/*
			 * TODO 寫死，之後要記得改回來
			 */
			
			createVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
			createVO.setGuid(ShareTool.newGUID());
			createVO.setdBussinessCardGuid(httpSession.getAttribute("selectionGuid").toString());
			createVO.setContact(contactGuid);
			socialService.create(createVO);
		}
		
		
		txManager.commit(status);
		return socialityVO;
	}
	
	@RequestMapping(value = "/card_contact_list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO card_contact_list(){
		
		List<DBussinessCard> dataList;
		if (httpSession.getAttribute("guid") != null) {
			dataList = (List<DBussinessCard>) service.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			dataList = (List<DBussinessCard>) service.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (DBussinessCard data : dataList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", data.getName());
			map.put("companyName", data.getCompanyName());
			map.put("nickname", data.getNickname());
			map.put("phone", data.getCellphoneNumber());
			map.put("guid", data.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(dataList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/card_contact", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO card_contact(){
		List<DBussinessCardSocical> dataList;
		List<String> guidList = new LinkedList<>();
		List<DBussinessCard> cardList;
		SocialityCardResponseVO responseVO = new SocialityCardResponseVO();
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		
		dataList = socialService.selectByCardGuid(httpSession.getAttribute("selectionGuid").toString());
		
		if (httpSession.getAttribute("guid") != null) {
			cardList = service.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			cardList = (List<DBussinessCard>) service.select();
		}
		
		int i = 1;
		if (dataList.size() <= 0) {
			return new SocialityVO();
		}else{
			for (DBussinessCardSocical socical : dataList) {
				for (DBussinessCard data : cardList) {
					if (socical.getContact().equals(data.getGuid())) {
						Map<String, String> map = new HashMap<>();
						try {
							BeanUtils.copyProperties(responseVO, data);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
						map.put("name", responseVO.getName());
						map.put("companyName", responseVO.getCompanyName());
						map.put("nickname", responseVO.getNickname());
						map.put("phone", responseVO.getCellphoneNumber());
						//map.put("remark", responseVO.getRemark());
						map.put("guid", socical.getGuid());
						map.put("cardGuid", responseVO.getGuid());
						map.put("rowNumber", String.valueOf(i));
						i++;
						list.add(map);
					}
				}
			}
		}
		

		socialityVO.setRows(list);
		socialityVO.setTotal(dataList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/card_bussiness_data", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO card_bussiness_data(@RequestBody SocialityCardRequestVO requestVO){
		//log.debug("requestVO.getGuid()"+requestVO.getGuid());
		BEnterpriseEmployee dataList;
		if (StringUtils.isNotBlank(requestVO.getGuid())) {
			dataList = employeeService.selectByGuid(requestVO.getGuid());
		}else{
			dataList = null;
		}
		
		SocialityVO socialityVO = new SocialityVO();
		Map<String, List<Map<String, String>>> bigMap = new HashMap<>();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		Map<String, String> map = new HashMap<>();
		map.put("name", dataList.getAccount());
		map.put("guid", dataList.getGuid());
		map.put("rowNumber", String.valueOf(i));
		i++;
		list.add(map);
		bigMap.put("array",JSONArray.fromObject(list));
//		for (BEnterpriseEmployee data : dataList) {
//			Map<String, String> map = new HashMap<>();
//			map.put("name", data.getAccount());
//			map.put("guid", data.getGuid());
//			map.put("rowNumber", String.valueOf(i));
//			i++;
//			list.add(map);
//		}

		socialityVO.setJson(JSONObject.fromObject(bigMap));
//		socialityVO.setTotal(dataList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/card_bussiness", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO card_bussiness(){
		
		List<BEnterpriseEmployee> dataList;
		if (httpSession.getAttribute("guid") != null) {
			dataList = (List<BEnterpriseEmployee>) employeeService.selectByEnterpriseGuid(httpSession.getAttribute("guid").toString());
		}else{
			dataList = (List<BEnterpriseEmployee>) employeeService.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (BEnterpriseEmployee data : dataList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", data.getAccount());
			map.put("guid", data.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(dataList.size());
		return socialityVO;
	}

	@RequestMapping(value = "/card_list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")@SuppressWarnings("unchecked") 
	public @ResponseBody SocialityVO card_list(){
		
		List<DBussinessCard> dataList;
		if (httpSession.getAttribute("guid") != null) {
			dataList = (List<DBussinessCard>) service.selectByCorporationGuid(httpSession.getAttribute("guid").toString());
		}else{
			dataList = (List<DBussinessCard>) service.select();
		}
		
		SocialityVO socialityVO = new SocialityVO();
		List<Map<String, String>> list = new LinkedList<>();
		int i = 1;
		for (DBussinessCard data : dataList) {
			Map<String, String> map = new HashMap<>();
			map.put("name", data.getName());
			map.put("companyName", data.getCompanyName());
			map.put("nickname", data.getNickname());
			map.put("phone", data.getCellphoneNumber());
			map.put("guid", data.getGuid());
			map.put("rowNumber", String.valueOf(i));
			i++;
			list.add(map);
		}
		
		socialityVO.setRows(list);
		socialityVO.setTotal(dataList.size());
		return socialityVO;
	}
	
	@RequestMapping(value = "/card_delete", method = RequestMethod.POST)
	public @ResponseBody ResponseVO card_delete(HttpServletRequest request, @RequestBody CardDeleteVO deleteVO){
		
		JSONArray array = JSONArray.fromObject(deleteVO.getPk());
		for (Object object : array) {
			service.deleteByGuid(object.toString());
		}
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResult(deleteVO);

		txManager.commit(status);	
		return responseVO;
	}

}
