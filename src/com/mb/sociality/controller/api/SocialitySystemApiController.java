package com.mb.sociality.controller.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.DBussinessCardSocical;
import com.mb.sociality.model.DScheduled;
import com.mb.sociality.service.BBussinessCardAttributeService;
import com.mb.sociality.service.BBussinessCardCategoryService;
import com.mb.sociality.service.BBussinessCardStatusService;
import com.mb.sociality.service.BScheduledProjectService;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.service.DBussinessCardSocicalService;
import com.mb.sociality.service.DScheduledService;
import com.mb.sociality.utils.Constant;
import com.mb.sociality.utils.Constant.Status;
import com.mb.sociality.utils.ShareTool;
import com.mb.sociality.validator.NewBusinessCardVOValidator;
import com.mb.sociality.validator.NewScheduledVOValidator;
import com.mb.sociality.validator.UpdateBusinessCardVOValidator;
import com.mb.sociality.validator.UpdateScheduledVOValidator;
import com.mb.sociality.vo.CardAttributeVO;
import com.mb.sociality.vo.CardCategoryVO;
import com.mb.sociality.vo.CardStatusVO;
import com.mb.sociality.vo.ResponseVO;
import com.mb.sociality.vo.api.BussinessCardVO;
import com.mb.sociality.vo.api.LoginInfoVO;
import com.mb.sociality.vo.api.NewBusinessCardSocialVO;
import com.mb.sociality.vo.api.NewBusinessCardVO;
import com.mb.sociality.vo.api.NewCardAttributeVO;
import com.mb.sociality.vo.api.NewCardCategoryVO;
import com.mb.sociality.vo.api.NewCardStatusVO;
import com.mb.sociality.vo.api.NewScheduledProjectVO;
import com.mb.sociality.vo.api.NewScheduledVO;
import com.mb.sociality.vo.api.ScheduledProjectVO;
import com.mb.sociality.vo.api.ScheduledVO;
import com.mb.sociality.vo.api.SelectBusinessCardSocialVO;
import com.mb.sociality.vo.api.UpdateBusinessCardVO;
import com.mb.sociality.vo.api.UpdateCardAttributeVO;
import com.mb.sociality.vo.api.UpdateCardCategoryVO;
import com.mb.sociality.vo.api.UpdateCardStatusVO;
import com.mb.sociality.vo.api.UpdateScheduledProjectVO;
import com.mb.sociality.vo.api.UpdateScheduledVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Log4j
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/SocialApi")
public class SocialitySystemApiController extends BaseController{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private NewBusinessCardVOValidator newBusinessCardVOValidator;
	@Autowired
	private UpdateBusinessCardVOValidator updateBusinessCardVOValidator;
	@Autowired
	private NewScheduledVOValidator newScheduledVOValidator;
	@Autowired
	private UpdateScheduledVOValidator updateScheduledVOValidator;
	@Autowired
	private BBussinessCardCategoryService bBussinessCardCategoryService;
	@Autowired
	private BBussinessCardAttributeService bBussinessCardAttributeService;
	@Autowired
	private BBussinessCardStatusService bBussinessCardStatusService;
	@Autowired
	private DBussinessCardService dBussinessCardService;
	@Autowired
	private DBussinessCardSocicalService dBussinessCardSocicalService;
	@Autowired
	private BScheduledProjectService bScheduledProjectService;
	@Autowired
	private DScheduledService scheduledService;
	@Autowired
	private HttpSession httpSession;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request, txManager);
	}
	
	/*
	 * 登入
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO login(HttpServletRequest request, @RequestBody LoginInfoVO requestVO) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		httpSession.setAttribute("token", requestVO.getToken());
		responseVO.setResult(requestVO);
		return responseVO;
	}
	
	/*
	 * 名片類別
	 */

	@RequestMapping(value = "/CardCategory/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO cardCategory(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<CardCategoryVO> vo = bBussinessCardCategoryService.selectByLimit(page, rows);
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/CardCategory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newCardCategory(HttpServletRequest request, @RequestBody @Valid NewCardCategoryVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BBussinessCardCategory vo = bBussinessCardCategoryService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.CREATE_CATEGORY_MSG001.getCode());
			responseVO.setMessage(Status.CREATE_CATEGORY_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BBussinessCardCategory) bBussinessCardCategoryService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_CATEGORY_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_CATEGORY_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardCategory/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateCardCategory(HttpServletRequest request, @PathVariable String pk, 
			@RequestBody @Valid UpdateCardCategoryVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}

		BBussinessCardCategory vo = bBussinessCardCategoryService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.UPDATE_CATEGORY_MSG003.getCode());
			responseVO.setMessage(Status.UPDATE_CATEGORY_MSG003.getMessage());
			return responseVO;
		}
		
		vo = bBussinessCardCategoryService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.UPDATE_CATEGORY_MSG001.getCode());
			responseVO.setMessage(Status.UPDATE_CATEGORY_MSG001.getMessage());
			return responseVO;
		}
		
		requestVO.setGuid(pk);
		vo = (BBussinessCardCategory) bBussinessCardCategoryService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_CATEGORY_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_CATEGORY_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardCategory/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteCardCategory(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		BBussinessCardCategory vo = bBussinessCardCategoryService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_CATEGORY_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_CATEGORY_MSG001.getMessage());
			return responseVO;
		}

		String guid = bBussinessCardCategoryService.deleteByGuid(pk);
		if(guid == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_CATEGORY_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_CATEGORY_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 名片屬性
	 */

	@RequestMapping(value = "/CardAttribute/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO cardAttribute(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<CardAttributeVO> vo = bBussinessCardAttributeService.selectByLimit(page, rows);
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/CardAttribute", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newCardCategory(HttpServletRequest request, @RequestBody @Valid NewCardAttributeVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BBussinessCardAttribute vo = bBussinessCardAttributeService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.CREATE_ATTRIBUTE_MSG001.getCode());
			responseVO.setMessage(Status.CREATE_ATTRIBUTE_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BBussinessCardAttribute) bBussinessCardAttributeService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_ATTRIBUTE_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_ATTRIBUTE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardAttribute/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateCardAttribute(HttpServletRequest request, @PathVariable String pk, 
			@RequestBody @Valid UpdateCardAttributeVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BBussinessCardAttribute vo = bBussinessCardAttributeService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.UPDATE_ATTRIBUTE_MSG003.getCode());
			responseVO.setMessage(Status.UPDATE_ATTRIBUTE_MSG003.getMessage());
			return responseVO;
		}
		
		vo = bBussinessCardAttributeService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.UPDATE_ATTRIBUTE_MSG001.getCode());
			responseVO.setMessage(Status.UPDATE_ATTRIBUTE_MSG001.getMessage());
			return responseVO;
		}
		
		requestVO.setGuid(pk);
		vo = (BBussinessCardAttribute) bBussinessCardAttributeService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_ATTRIBUTE_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_ATTRIBUTE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardAttribute/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteCardAttribute(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		BBussinessCardAttribute vo = bBussinessCardAttributeService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_ATTRIBUTE_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_ATTRIBUTE_MSG001.getMessage());
			return responseVO;
		}

		String guid = bBussinessCardAttributeService.deleteByGuid(pk);
		if(guid == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_ATTRIBUTE_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_ATTRIBUTE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 名片狀態
	 */

	@RequestMapping(value = "/CardStatus/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO cardStatus(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<CardStatusVO> vo = bBussinessCardStatusService.selectByLimit(page, rows);
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/CardStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newCardStatus(HttpServletRequest request, @RequestBody @Valid NewCardStatusVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BBussinessCardStatus vo = bBussinessCardStatusService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.CREATE_STATUS_MSG001.getCode());
			responseVO.setMessage(Status.CREATE_STATUS_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BBussinessCardStatus) bBussinessCardStatusService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_STATUS_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_STATUS_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardStatus/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateCardStatus(HttpServletRequest request, @PathVariable String pk, 
			@RequestBody @Valid UpdateCardStatusVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}

		BBussinessCardStatus vo = bBussinessCardStatusService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.UPDATE_STATUS_MSG003.getCode());
			responseVO.setMessage(Status.UPDATE_STATUS_MSG003.getMessage());
			return responseVO;
		}
		
		vo = bBussinessCardStatusService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.UPDATE_STATUS_MSG001.getCode());
			responseVO.setMessage(Status.UPDATE_STATUS_MSG001.getMessage());
			return responseVO;
		}
		
		requestVO.setGuid(pk);
		vo = (BBussinessCardStatus) bBussinessCardStatusService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_STATUS_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_STATUS_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/CardStatus/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteCardStatus(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		BBussinessCardStatus vo = bBussinessCardStatusService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_STATUS_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_STATUS_MSG001.getMessage());
			return responseVO;
		}

		String guid = bBussinessCardStatusService.deleteByGuid(pk);
		if(guid == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_STATUS_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_STATUS_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 名片夾
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/BusinessCard", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO businessCard(HttpServletRequest request) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<DBussinessCard> voList = new ArrayList<>();
		try {
			voList = (List<DBussinessCard>) dBussinessCardService.select();
			for (DBussinessCard vo : voList) {
				if(StringUtils.isNotBlank(vo.getImagePath())){
					File photo = new File(vo.getImagePath());
					if(photo.exists()){
						vo.setImage(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(photo)));
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_BUSINESS_CARD_MSG002.getCode());
			responseVO.setMessage(Status.SELECT_BUSINESS_CARD_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/BusinessCard/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO businessCard(HttpServletRequest request, @PathVariable int page, @PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<DBussinessCard> voList = new ArrayList<>();
		try {
			voList = dBussinessCardService.selectByLimit(page, rows);
			for (DBussinessCard vo : voList) {
				if(StringUtils.isNotBlank(vo.getImagePath())){
					File photo = new File(vo.getImagePath());
					if(photo.exists()){
						vo.setImage(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(photo)));
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_BUSINESS_CARD_MSG002.getCode());
			responseVO.setMessage(Status.SELECT_BUSINESS_CARD_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/BusinessCard/{page}/{rows}/{conditions}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO businessCard(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows, @PathVariable String conditions) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<DBussinessCard> voList = new ArrayList<>();
		try {
			JSONObject json = JSONObject.fromObject(conditions);
			String category = json.optString("category");
			String status = json.optString("status");
			String attribute = json.optString("attribute");
			String text = json.optString("keywords");
			voList = dBussinessCardService.selectByLike(page, rows, StringUtils.isNotBlank(category) ? category : Constant.NULL, 
					StringUtils.isNotBlank(attribute) ? attribute : Constant.NULL, StringUtils.isNotBlank(status) ? status : Constant.NULL, 
							StringUtils.isNotBlank(text) ? text : Constant.NULL);
			if(voList != null){
				for (DBussinessCard vo : voList) {
					if(StringUtils.isNotBlank(vo.getImagePath())){
						File photo = new File(vo.getImagePath());
						if(photo.exists()){
							vo.setImage(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(photo)));
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_BUSINESS_CARD_MSG002.getCode());
			responseVO.setMessage(Status.SELECT_BUSINESS_CARD_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/BusinessCard/{pk}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO businessCard(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		BussinessCardVO vo = dBussinessCardService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.SELECT_BUSINESS_CARD_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_BUSINESS_CARD_MSG001.getMessage());
			return responseVO;
		}
		
		if(StringUtils.isNotBlank(vo.getImagePath())){
			File photo = new File(vo.getImagePath());
			if(photo.exists()){
				vo.setImage(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(photo)));
			}
		}

		if(vo.getFirstMeetTime() != null){
			vo.setFirstMeetTimeStr(ShareTool.dateToString(vo.getFirstMeetTime(), Constant.DATE_PATTERN));
		}
		
		List<DBussinessCardSocical> vo2 = dBussinessCardSocicalService.selectByCardGuid(pk);
		List<DBussinessCard> vo3 = (List<DBussinessCard>) dBussinessCardService.select();
		List<SelectBusinessCardSocialVO> vo4 = new ArrayList<>();
		for (DBussinessCardSocical social : vo2) {
			SelectBusinessCardSocialVO vo5 = new SelectBusinessCardSocialVO();
			for (DBussinessCard blobs : vo3) {
				if(StringUtils.equals(social.getContact(), blobs.getGuid())){
					vo5.setGuid(social.getContact());
					vo5.setCellPhoneNumber(blobs.getCellphoneNumber());
					vo5.setCompanyName(blobs.getCompanyName());
					vo5.setName(blobs.getName());
					vo5.setNickname(blobs.getNickname());
					vo5.setRemark(blobs.getRemark());
				}
			}
			vo4.add(vo5);
		}
		
		vo.setBusinessCardSocials(vo4);
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		return responseVO;
	}

	@RequestMapping(value = "/BusinessCard", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newBusinessCard(HttpServletRequest request, @RequestBody @Valid NewBusinessCardVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		newBusinessCardVOValidator.validate(requestVO, result);
		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
//		DBussinessCard vo = dBussinessCardService.selectByName(requestVO.getName());
//		if(vo != null){
//			responseVO.setStatus(Status.CREATE_BUSINESS_MSG001.getCode());
//			responseVO.setMessage(Status.CREATE_BUSINESS_MSG001.getMessage());
//			return responseVO;
//		}
		
		requestVO.setFirstMeetTime(StringUtils.isBlank(requestVO.getFirstMeetTimeStr()) ? 
				new Date() : ShareTool.stringToDate(requestVO.getFirstMeetTimeStr(), Constant.DATE_PATTERN));
		requestVO.setbBussinessCardAttributeGuid(requestVO.getAttribute());
		requestVO.setbBussinessCardCategoryGuid(requestVO.getCategory());
		requestVO.setbBussinessCardStatusGuid(requestVO.getStatus());
		requestVO.setbEnterpriseEmployeeGuid(requestVO.getUndertaker());
		
		/*
		 * TODO 寫死，之後要記得改回來
		 */
		
		requestVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		if(StringUtils.isNotBlank(requestVO.getBirthday())){
			String[] date = requestVO.getBirthday().split("-");
			requestVO.setBirthdayYear(date[0]);
			requestVO.setBirthdayMonth(date[1]);
			requestVO.setBirthdayDate(date[2]);
		}
		
		String name = UUID.randomUUID().toString();
		String path = baseDir + Constant.IMG_ROOT + "/" + name + ".jpeg";
		String image = requestVO.getImage();
		
		requestVO.setImage("");
		requestVO.setImagePath(path);
		requestVO.setGuid(name);
		
		DBussinessCard vo = (DBussinessCard) dBussinessCardService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_BUSINESS_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_BUSINESS_MSG002.getMessage());
			return responseVO;
		}
		
		/*
		 * 上傳圖片
		 */

		if(StringUtils.isNotBlank(image)){
			path = ShareTool.uploadImg(path, image, vo.getGuid());
			if(StringUtils.isBlank(path)){
				txManager.rollback(status);
				responseVO.setStatus(Status.CREATE_BUSINESS_MSG004.getCode());
				responseVO.setMessage(Status.CREATE_BUSINESS_MSG004.getMessage());
				return responseVO;
			}
		}
		
		/*
		 * 新增人脈
		 */
		
		if(StringUtils.isNotBlank(requestVO.getSocialContacts())){
			try {
				JSONArray data = JSONArray.fromObject(requestVO.getSocialContacts());
				for (int x = 0; x < data.size(); x++) {
					JSONObject json = data.getJSONObject(x);
					NewBusinessCardSocialVO vo2 = new NewBusinessCardSocialVO();
					vo2.setContact(json.optString("contactPk"));
					vo2.setdBussinessCardGuid(vo.getGuid());

					/*
					 * TODO 寫死，之後要記得改回來
					 */
					
					vo2.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
					
					DBussinessCardSocical vo3 = (DBussinessCardSocical) dBussinessCardSocicalService.create(vo2);
					if(vo3 == null){
						txManager.rollback(status);
						responseVO.setStatus(Status.CREATE_BUSINESS_SOCIAL_MSG001.getCode());
						responseVO.setMessage(Status.CREATE_BUSINESS_SOCIAL_MSG001.getMessage());
						return responseVO;
					}
				}
			} catch (Exception e) {
				log.debug(e, e);
				txManager.rollback(status);
				responseVO.setStatus(Status.CREATE_BUSINESS_MSG003.getCode());
				responseVO.setMessage(Status.CREATE_BUSINESS_MSG003.getMessage());
				return responseVO;
			}
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}

	@RequestMapping(value = "/BusinessCard/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateBusinessCard(HttpServletRequest request, @PathVariable String pk, @RequestBody @Valid UpdateBusinessCardVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(StringUtils.isNotBlank(requestVO.getBirthday())){
			updateBusinessCardVOValidator.validate(requestVO, result);
		}
		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		requestVO.setGuid(pk);
		requestVO.setbBussinessCardAttributeGuid(requestVO.getAttribute());
		requestVO.setbBussinessCardCategoryGuid(requestVO.getCategory());
		requestVO.setbBussinessCardStatusGuid(requestVO.getStatus());
		requestVO.setbEnterpriseEmployeeGuid(requestVO.getUndertaker());
		
		if(StringUtils.isNotBlank(requestVO.getBirthday())){
			String[] date = requestVO.getBirthday().split("-");
			requestVO.setBirthdayYear(date[0]);
			requestVO.setBirthdayMonth(date[1]);
			requestVO.setBirthdayDate(date[2]);
		}
		
		String name = pk;
		String path = baseDir + Constant.IMG_ROOT + "/" + name + ".jpeg";
		String image = requestVO.getImage();
		
		requestVO.setImage("");
		requestVO.setImagePath(path);
		requestVO.setGuid(name);

		/*
		 * TODO 寫死，之後要記得改回來
		 */

		requestVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		DBussinessCard vo = (DBussinessCard) dBussinessCardService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_BUSINESS_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_BUSINESS_MSG002.getMessage());
			return responseVO;
		}
		
		/*
		 * 上傳圖片
		 */

		if(StringUtils.isNotBlank(image)){
			path = ShareTool.uploadImg(path, image, vo.getGuid());
			if(StringUtils.isBlank(path)){
				txManager.rollback(status);
				responseVO.setStatus(Status.UPDATE_BUSINESS_MSG004.getCode());
				responseVO.setMessage(Status.UPDATE_BUSINESS_MSG004.getMessage());
				return responseVO;
			}	
		}
		
		/*
		 * 更新人脈
		 */
		
		if(StringUtils.isNotBlank(requestVO.getSocialContacts())){
			try {
				dBussinessCardSocicalService.deleteByBussinessCardGuid(vo.getGuid());
				JSONArray data = JSONArray.fromObject(requestVO.getSocialContacts());
				for (int x = 0; x < data.size(); x++) {
					JSONObject json = data.getJSONObject(x);
					NewBusinessCardSocialVO vo2 = new NewBusinessCardSocialVO();
					vo2.setContact(json.optString("contactPk"));
					vo2.setdBussinessCardGuid(vo.getGuid());

					/*
					 * TODO 寫死，之後要記得改回來
					 */
					
					vo2.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
					
					DBussinessCardSocical vo3 = (DBussinessCardSocical) dBussinessCardSocicalService.create(vo2);
					if(vo3 == null){
						txManager.rollback(status);
						responseVO.setStatus(Status.UPDATE_BUSINESS_SOCIAL_MSG001.getCode());
						responseVO.setMessage(Status.UPDATE_BUSINESS_SOCIAL_MSG001.getMessage());
						return responseVO;
					}
				}
			} catch (Exception e) {
				log.debug(e, e);
				txManager.rollback(status);
				responseVO.setStatus(Status.UPDATE_BUSINESS_MSG003.getCode());
				responseVO.setMessage(Status.UPDATE_BUSINESS_MSG003.getMessage());
				return responseVO;
			}
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}

	@RequestMapping(value = "/BusinessCard/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteBusinessCard(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		DBussinessCard vo = dBussinessCardService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_BUSINESS_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_BUSINESS_MSG001.getMessage());
			return responseVO;
		}
		
		pk = dBussinessCardService.deleteByGuid(pk);
		if(pk == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_BUSINESS_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_BUSINESS_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 預定項目
	 */

	@RequestMapping(value = "/ScheduledProject/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO scheduledProject(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<ScheduledProjectVO> vo = bScheduledProjectService.selectByLimit(page, rows);
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/ScheduledProject", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newScheduledProject(HttpServletRequest request, @RequestBody @Valid NewScheduledProjectVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		BScheduledProject vo = bScheduledProjectService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.CREATE_SCHEDULE_PROJECT_MSG001.getCode());
			responseVO.setMessage(Status.CREATE_SCHEDULE_PROJECT_MSG001.getMessage());
			return responseVO;
		}
		
		vo = (BScheduledProject) bScheduledProjectService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_SCHEDULE_PROJECT_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_SCHEDULE_PROJECT_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/ScheduledProject/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateScheduledProject(HttpServletRequest request, @PathVariable String pk, 
			@RequestBody @Valid UpdateScheduledProjectVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}

		BScheduledProject vo = bScheduledProjectService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.UPDATE_SCHEDULE_PROJECT_MSG003.getCode());
			responseVO.setMessage(Status.UPDATE_SCHEDULE_PROJECT_MSG003.getMessage());
			return responseVO;
		}
		
		vo = bScheduledProjectService.selectByName(requestVO.getName());
		if(vo != null){
			responseVO.setStatus(Status.UPDATE_SCHEDULE_PROJECT_MSG001.getCode());
			responseVO.setMessage(Status.UPDATE_SCHEDULE_PROJECT_MSG001.getMessage());
			return responseVO;
		}
		
		requestVO.setGuid(pk);
		vo = (BScheduledProject) bScheduledProjectService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_SCHEDULE_PROJECT_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_SCHEDULE_PROJECT_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/ScheduledProject/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteScheduledProject(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		BScheduledProject vo = bScheduledProjectService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_SCHEDULE_PROJECT_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_SCHEDULE_PROJECT_MSG001.getMessage());
			return responseVO;
		}

		String guid = bScheduledProjectService.deleteByGuid(pk);
		if(guid == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_SCHEDULE_PROJECT_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_SCHEDULE_PROJECT_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
	
	/*
	 * 業務行程
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Scheduled", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO scheduled(HttpServletRequest request) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<ScheduledVO> voList = new ArrayList<>();
		try {
			voList = (List<ScheduledVO>) scheduledService.select();
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_SCHEDULE_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_SCHEDULE_MSG001.getMessage());
			return responseVO;
		}
		
		for (ScheduledVO vo : voList) {
			if(vo.getStartTime() != null){
				vo.setStartTimeStr(ShareTool.dateToString(vo.getStartTime(), Constant.DATE_PATTERN));
			}

			if(vo.getEndTime() != null){
				vo.setEndTimeStr(ShareTool.dateToString(vo.getEndTime(), Constant.DATE_PATTERN));
			}

			if(vo.getEmailNotifyTime() != null){
				vo.setEmailNotifyTimeStr(ShareTool.dateToString(vo.getEmailNotifyTime(), Constant.DATE_PATTERN));
			}

			if(vo.getAlarmTime() != null){
				vo.setAlarmTimeStr(ShareTool.dateToString(vo.getAlarmTime(), Constant.DATE_PATTERN));
			}
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}

	@RequestMapping(value = "/Scheduled/{page}/{rows}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO scheduled(HttpServletRequest request, @PathVariable int page, @PathVariable int rows) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<ScheduledVO> voList = new ArrayList<>();
		try {
			voList = scheduledService.selectByLimit(page, rows);
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_SCHEDULE_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_SCHEDULE_MSG001.getMessage());
			return responseVO;
		}
		
		for (ScheduledVO vo : voList) {
			if(vo.getStartTime() != null){
				vo.setStartTimeStr(ShareTool.dateToString(vo.getStartTime(), Constant.DATE_PATTERN));
			}

			if(vo.getEndTime() != null){
				vo.setEndTimeStr(ShareTool.dateToString(vo.getEndTime(), Constant.DATE_PATTERN));
			}

			if(vo.getEmailNotifyTime() != null){
				vo.setEmailNotifyTimeStr(ShareTool.dateToString(vo.getEmailNotifyTime(), Constant.DATE_PATTERN));
			}

			if(vo.getAlarmTime() != null){
				vo.setAlarmTimeStr(ShareTool.dateToString(vo.getAlarmTime(), Constant.DATE_PATTERN));
			}
			
			BScheduledProject vo2 = bScheduledProjectService.selectByGuid(vo.getbScheduledProjectGuid());
			vo.setScheduledProject(vo2.getName());
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}

	@RequestMapping(value = "/Scheduled/{page}/{rows}/{conditions}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO scheduled(HttpServletRequest request, @PathVariable int page, 
			@PathVariable int rows, @PathVariable String conditions) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		List<ScheduledVO> voList = new ArrayList<>();
		try {
			JSONObject json = JSONObject.fromObject(conditions);
			String status = json.optString("status");
			String text = json.optString("keywords");
			String startTime = json.optString("startTime");
			String endTime = json.optString("endTime");
			voList = scheduledService.selectByLike(page, rows, StringUtils.isNotBlank(status) ? status : null, 
					StringUtils.isNotBlank(startTime) ? startTime : null, StringUtils.isNotBlank(endTime) ? endTime : null,  
					StringUtils.isNotBlank(text) ? text : Constant.NULL);
		} catch (Exception e) {
			log.error(e, e);
			responseVO.setStatus(Status.SELECT_SCHEDULE_MSG001.getCode());
			responseVO.setMessage(Status.SELECT_SCHEDULE_MSG001.getMessage());
			return responseVO;
		}
		
		if(voList != null){
			for (ScheduledVO vo : voList) {
				if(vo.getStartTime() != null){
					vo.setStartTimeStr(ShareTool.dateToString(vo.getStartTime(), Constant.DATE_PATTERN));
				}

				if(vo.getEndTime() != null){
					vo.setEndTimeStr(ShareTool.dateToString(vo.getEndTime(), Constant.DATE_PATTERN));
				}

				if(vo.getEmailNotifyTime() != null){
					vo.setEmailNotifyTimeStr(ShareTool.dateToString(vo.getEmailNotifyTime(), Constant.DATE_PATTERN));
				}

				if(vo.getAlarmTime() != null){
					vo.setAlarmTimeStr(ShareTool.dateToString(vo.getAlarmTime(), Constant.DATE_PATTERN));
				}
				
				BScheduledProject vo2 = bScheduledProjectService.selectByGuid(vo.getbScheduledProjectGuid());
				vo.setScheduledProject(vo2.getName());
			}
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(voList);
		
		return responseVO;
	}

	@RequestMapping(value = "/Scheduled/{pk}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO scheduled(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		DScheduled vo = scheduledService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.SELECT_SCHEDULE_MSG002.getCode());
			responseVO.setMessage(Status.SELECT_SCHEDULE_MSG002.getMessage());
			return responseVO;
		}
		
		ScheduledVO vo2 = new ScheduledVO();
		BeanUtils.copyProperties(vo2, vo);
		if(vo.getStartTime() != null){
			vo2.setStartTimeStr(ShareTool.dateToString(vo.getStartTime(), Constant.DATE_PATTERN));
		}

		if(vo.getEndTime() != null){
			vo2.setEndTimeStr(ShareTool.dateToString(vo.getEndTime(), Constant.DATE_PATTERN));
		}

		if(vo.getEmailNotifyTime() != null){
			vo2.setEmailNotifyTimeStr(ShareTool.dateToString(vo.getEmailNotifyTime(), Constant.DATE_PATTERN));
		}

		if(vo.getAlarmTime() != null){
			vo2.setAlarmTimeStr(ShareTool.dateToString(vo.getAlarmTime(), Constant.DATE_PATTERN));
		}
		
		BScheduledProject vo3 = bScheduledProjectService.selectByGuid(vo.getbScheduledProjectGuid());
		vo2.setScheduledProject(vo3.getName());
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo2);
		
		return responseVO;
	}
	
	@RequestMapping(value = "/Scheduled", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO newScheduled(HttpServletRequest request, @RequestBody @Valid NewScheduledVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		newScheduledVOValidator.validate(requestVO, result);
		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
//		DScheduled vo = dScheduledService.selectByName(requestVO.getName());
//		if(vo != null){
//			responseVO.setStatus(Status.CREATE_SCHEDULE_MSG001.getCode());
//			responseVO.setMessage(Status.CREATE_SCHEDULE_MSG001.getMessage());
//			return responseVO;
//		}

		/*
		 * TODO 寫死，之後要記得改回來
		 */

		requestVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		requestVO.setStartTime(ShareTool.stringToDate(requestVO.getStartDateTime(), Constant.DATE_PATTERN));
		requestVO.setEndTime(ShareTool.stringToDate(requestVO.getEndDateTime(), Constant.DATE_PATTERN));
		requestVO.setEmailNotifyTime(ShareTool.stringToDate(requestVO.getEmailNotifyTimeStr(), Constant.DATE_PATTERN));
		requestVO.setAlarmTime(ShareTool.stringToDate(requestVO.getAlarmTimeStr(), Constant.DATE_PATTERN));
		requestVO.setbScheduledProjectGuid(StringUtils.isBlank(requestVO.getScheduledProject()) ? 
				null : requestVO.getScheduledProject());
		requestVO.setdBussinessCardGuid(StringUtils.isBlank(requestVO.getBusinessCard()) ? null : requestVO.getBusinessCard());
		DScheduled vo = (DScheduled) scheduledService.create(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.CREATE_SCHEDULE_MSG002.getCode());
			responseVO.setMessage(Status.CREATE_SCHEDULE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/Scheduled/{pk}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO updateScheduled(HttpServletRequest request, @PathVariable String pk, 
			@RequestBody @Valid UpdateScheduledVO requestVO, BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		updateScheduledVOValidator.validate(requestVO, result);
		if(result.hasErrors()){
			responseVO.setStatus(Status.FIELD_MSG001.getCode());
			responseVO.setMessage(Status.FIELD_MSG001.getMessage());
			responseVO.setResult(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}

		DScheduled vo = scheduledService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.UPDATE_SCHEDULE_MSG003.getCode());
			responseVO.setMessage(Status.UPDATE_SCHEDULE_MSG003.getMessage());
			return responseVO;
		}

		/*
		 * TODO 寫死，之後要記得改回來
		 */

		requestVO.setbCorporationGuid("aec64ec6-2949-4dd5-a013-e7102f110d42");
		
		requestVO.setGuid(pk);
		requestVO.setStartTime(ShareTool.stringToDate(requestVO.getStartDateTime(), Constant.DATE_PATTERN));
		requestVO.setEndTime(ShareTool.stringToDate(requestVO.getEndDateTime(), Constant.DATE_PATTERN));
		requestVO.setEmailNotifyTime(ShareTool.stringToDate(requestVO.getEmailNotifyTimeStr(), Constant.DATE_PATTERN));
		requestVO.setAlarmTime(ShareTool.stringToDate(requestVO.getAlarmTimeStr(), Constant.DATE_PATTERN));
		requestVO.setbScheduledProjectGuid(StringUtils.isBlank(requestVO.getScheduledProject()) ? 
				null : requestVO.getScheduledProject());
		requestVO.setdBussinessCardGuid(StringUtils.isBlank(requestVO.getBusinessCard()) ? null : requestVO.getBusinessCard());
		vo = (DScheduled) scheduledService.update(requestVO);
		if(vo == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.UPDATE_SCHEDULE_MSG002.getCode());
			responseVO.setMessage(Status.UPDATE_SCHEDULE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(vo.getGuid());
		
		txManager.commit(status);
		return responseVO;
	}
	
	@RequestMapping(value = "/Scheduled/{pk}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseVO deleteScheduled(HttpServletRequest request, @PathVariable String pk) throws Exception{
		ResponseVO responseVO = new ResponseVO();

		DScheduled vo = scheduledService.selectByGuid(pk);
		if(vo == null){
			responseVO.setStatus(Status.DELETE_SCHEDULE_MSG001.getCode());
			responseVO.setMessage(Status.DELETE_SCHEDULE_MSG001.getMessage());
			return responseVO;
		}

		String guid = scheduledService.deleteByGuid(pk);
		if(guid == null){
			txManager.rollback(status);
			responseVO.setStatus(Status.DELETE_SCHEDULE_MSG002.getCode());
			responseVO.setMessage(Status.DELETE_SCHEDULE_MSG002.getMessage());
			return responseVO;
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(pk);
		
		txManager.commit(status);
		return responseVO;
	}
}
