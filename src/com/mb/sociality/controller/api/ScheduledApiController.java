package com.mb.sociality.controller.api;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mb.sociality.base.BaseController;
import com.mb.sociality.model.BScheduledProject;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.DScheduled;
import com.mb.sociality.service.BScheduledProjectService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.service.DScheduledService;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.vo.ScheduledResponseVO;
import com.mb.sociality.vo.SimpleFieldVO;
import com.mb.sociality.vo.form.DScheduledCreateVO;
import com.mb.sociality.vo.form.DScheduledDeleteVO;
import com.mb.sociality.vo.form.DScheduledUpdateVO;

import lombok.extern.log4j.Log4j;

@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/scheduled/api")
public class ScheduledApiController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private DScheduledService service;
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

	@RequestMapping(value = "/scheduled_create", method = RequestMethod.POST, produces = "application/json;charset=utf-8") 
	public @ResponseBody ScheduledResponseVO scheduled_create(@RequestBody @Valid DScheduledCreateVO createVO, BindingResult result){
		ScheduledResponseVO responseVO = new ScheduledResponseVO();
		
		if(result.hasErrors()){			
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return responseVO;
		}
		
		if (StringUtils.isBlank(createVO.getdBussinessCardGuid())) {
			createVO.setdBussinessCardGuid(null);
		}

		if (StringUtils.isNotBlank(createVO.getOptradio())) {
			createVO.setStatus(Integer.parseInt(createVO.getOptradio()));
		}
		
		if (StringUtils.isNotBlank(createVO.getEditCardBeginDate()) || StringUtils.isNotBlank(createVO.getEditCardBeginTime())) {
			createVO.setStartTime(mixDate(createVO.getEditCardBeginDate(), createVO.getEditCardBeginTime()));
		}
		if (StringUtils.isNotBlank(createVO.getEditCardEndDate()) || StringUtils.isNotBlank(createVO.getEditCardEndTime())) {
			createVO.setEndTime(mixDate(createVO.getEditCardEndDate(), createVO.getEditCardEndTime()));
		}
		if (StringUtils.isNotBlank(createVO.getEditCardMailDate()) || StringUtils.isNotBlank(createVO.getEditCardMailTime())) {
			createVO.setEmailNotifyTime(mixDate(createVO.getEditCardMailDate(), createVO.getEditCardMailTime()));
		}
		if (StringUtils.isNotBlank(createVO.getEditCardAlarmDate()) || StringUtils.isNotBlank(createVO.getEditCardAlarmTime())) {
			createVO.setAlarmTime(mixDate(createVO.getEditCardAlarmDate(), createVO.getEditCardAlarmTime()));
		}
		
		/*
		 * TODO 寫死，之後要記得改回來
		 */
		
		createVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		service.create(createVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/scheduled_update", method = RequestMethod.POST, produces = "application/json;charset=utf-8") 
	public @ResponseBody ScheduledResponseVO scheduled_update(@RequestBody @Valid DScheduledUpdateVO updateVO, BindingResult result){
		ScheduledResponseVO responseVO = new ScheduledResponseVO();
		
		if(result.hasErrors()){			
			List<FieldError> errorList = result.getFieldErrors();
			List<SimpleFieldVO> list = ShareTool.simpleFieldError(errorList);
			ArrayList<String> array = new ArrayList<String>();
			for (SimpleFieldVO simpleFieldVO : list) {
				array.add(simpleFieldVO.getMessage());
			}
			responseVO.setResult(array.toString());
			return responseVO;
		}
		
		if (StringUtils.isBlank(updateVO.getdBussinessCardGuid())) {
			updateVO.setdBussinessCardGuid(null);
		}

		if (StringUtils.isNotBlank(updateVO.getOptradio())) {
			updateVO.setStatus(Integer.parseInt(updateVO.getOptradio()));
		}
		
		if (StringUtils.isNotBlank(updateVO.getUpdateStrartDate()) || StringUtils.isNotBlank(updateVO.getUpdateStrartTime())) {
			updateVO.setStartTime(mixDate(updateVO.getUpdateStrartDate(), updateVO.getUpdateStrartTime()));
		}
		if (StringUtils.isNotBlank(updateVO.getUpdateEndDate()) || StringUtils.isNotBlank(updateVO.getUpdateEndTime())) {
			updateVO.setEndTime(mixDate(updateVO.getUpdateEndDate(), updateVO.getUpdateEndTime()));
		}
		if (StringUtils.isNotBlank(updateVO.getUpdateMailDate()) || StringUtils.isNotBlank(updateVO.getUpdateMailTime())) {
			updateVO.setEmailNotifyTime(mixDate(updateVO.getUpdateMailDate(), updateVO.getUpdateMailTime()));
		}
		if (StringUtils.isNotBlank(updateVO.getUpdateAlarmDate()) || StringUtils.isNotBlank(updateVO.getUpdateAlarmTime())) {
			updateVO.setAlarmTime(mixDate(updateVO.getUpdateAlarmDate(), updateVO.getUpdateAlarmTime()));
		}

		service.update(updateVO);
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/scheduled_fetchData", method = RequestMethod.POST, produces = "application/json;charset=utf-8") 
	public @ResponseBody Object scheduled_update(@RequestBody DScheduledUpdateVO updateVO){
		DBussinessCard card = new DBussinessCard();
		DScheduled data = service.selectByGuid(updateVO.getGuid());
		BScheduledProject project = scheduledService.selectByGuid(data.getbScheduledProjectGuid());
		if(StringUtils.isNotBlank(data.getdBussinessCardGuid())){
			card = cardService.selectByGuid(updateVO.getdBussinessCardGuid());
		}
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		String startTime = sdFormat.format(data.getStartTime());
		String endTime = sdFormat.format(data.getEndTime());
		String mailTime = sdFormat.format(data.getEmailNotifyTime());
		String alarmTime = sdFormat.format(data.getAlarmTime());
		
		if(StringUtils.equals(startTime, "1899/12/31 00:00:00")){
			startTime = "";
		}
		if(StringUtils.equals(endTime, "1899/12/31 00:00:00")){
			endTime = "";
		}
		if(StringUtils.equals(mailTime, "1899/12/31 00:00:00")){
			mailTime = "";
		}
		if(StringUtils.equals(alarmTime, "1899/12/31 00:00:00")){
			alarmTime = "";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("project", project);
		map.put("data", data);
		map.put("card", card);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("mailTime", mailTime);
		map.put("alarmTime", alarmTime);
		
		txManager.commit(status);
		return map;
	}
	
	@RequestMapping(value = "/scheduled_delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8") 
	public @ResponseBody ScheduledResponseVO scheduled_delete(@RequestBody DScheduledDeleteVO deleteVO){
		ScheduledResponseVO responseVO = new ScheduledResponseVO();
		for (String guid : deleteVO.getGuidArray()) {
			if (StringUtils.isNotBlank(guid)) {
				service.deleteByGuid(guid);
			}
		}
		txManager.commit(status);
		return responseVO;
	}
	
	Date mixDate (String date,String time){
		String newDate;
		String newTime;
		if(StringUtils.isBlank(date)){
			newDate = ShareTool.dateToStringV2(new Date());
		}else{
			newDate = date;
		}
		
		if(StringUtils.isBlank(time)){
			newTime = "00:00:00";
		}else{
			newTime = time;
		}
		
		Date mixDate = ShareTool.stringToDate(newDate + " " + newTime + ":00");
		
		return mixDate;
	}
}
