package com.mb.sociality.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mb.sociality.base.BaseController;
import com.mb.sociality.model.BScheduledProject;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.DScheduled;
import com.mb.sociality.service.BScheduledProjectService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.service.DScheduledService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.vo.ResponseVO;

@RestController
@Scope(value = "session")
@RequestMapping(value = "/sociality/scheduled")
public class SocialityScheduledController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private BScheduledProjectService scheduledService;
	@Autowired
	private DScheduledService service;
	@Autowired
	private DBussinessCardService cardService;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	/*
	 * 登入
	 */
	
	@RequestMapping(value = "/login/{token}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO login(HttpServletRequest request, @PathVariable String token) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		httpSession.setAttribute("token", token);
		responseVO.setResult(token);
		return responseVO;
	}
	
	@RequestMapping(value = "/scheduled_history" , method = RequestMethod.GET)
	public @ResponseBody Object scheduled_history(HttpServletRequest request) throws Exception{
		httpSession.setAttribute("token", "xxxx");
		log.debug("AAAA: " + httpSession.getAttribute("token"));
		
	    Map<String, Object> map = fetchScheduledListData(request,"history");
		return new ModelAndView(Constant.D_SCHEDULED_LIST, "dataMap", map);
	}
	
	@RequestMapping(value = "/scheduled_future" , method = RequestMethod.GET)
	public @ResponseBody Object scheduled_future(HttpServletRequest request) throws Exception{
		log.debug("BBBB: " + httpSession.getAttribute("token"));

	    //categoryList.forEach(category -> user.setImg(ShareTool.getImgSrc(baseUri, user.getImg())));
	    Map<String, Object> map = fetchScheduledListData(request,"future");
		return new ModelAndView(Constant.D_SCHEDULED_LIST, "dataMap", map);
	}
	
	@SuppressWarnings("unchecked")
	Map<String, Object> fetchScheduledListData(HttpServletRequest request,String dateType){
		
		List<DScheduled> dataList;
		List<BScheduledProject> projectList;
		List<DBussinessCard> cardList;
		if (StringUtils.isNotBlank(request.getParameter("guid"))) {
			httpSession.setAttribute("guid", request.getParameter("guid"));
			String guid = request.getParameter("guid");
			projectList = (List<BScheduledProject>) scheduledService.selectByCorporationGuid(guid);
			dataList = (List<DScheduled>) service.selectByCorporationGuidWithDateType(guid,dateType);
			cardList = (List<DBussinessCard>) cardService.selectByCorporationGuid(guid);
		}else{
			projectList = (List<BScheduledProject>) scheduledService.select();
			if (StringUtils.equals(dateType, "history")) {
				dataList = (List<DScheduled>) service.selectByHistory();
			}else{
				dataList = (List<DScheduled>) service.selectByDate();
			}
			cardList = (List<DBussinessCard>) cardService.select();
		}
		


//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
//		for (DScheduled scheduled : dataList) {
//			try {
//				scheduled.setStartTimeString(sdFormat.format(scheduled.getStartTime()));
//				scheduled.setEndTimeString(sdFormat.format(scheduled.getEndTime()));
//				scheduled.setAlarmTimeString(sdFormat.format(scheduled.getAlarmTime()));
//				scheduled.setEmailNotifyTimeString(sdFormat.format(scheduled.getEmailNotifyTime()));
//				//dataList.add(scheduled);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
		Map<String, Object> map = new HashMap<>();
		map.put("projectList", projectList);
		map.put("dataList", dataList);
		map.put("cardList", cardList);
		
		return map;
	}
}
