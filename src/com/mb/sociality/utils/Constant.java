package com.mb.sociality.utils;

public class Constant {
	public static final String IV = ShareTool.getProperty("aes.iv");
	public static final String KEY = ShareTool.getProperty("aes.key");
	public static final String IMG_ROOT = ShareTool.getProperty("img.root");
	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String NULL = "null";
	
	/*
	 * Api URI
	 */
	
	public static final String USER_API = ShareTool.getProperty("domain") + "/SpringRestv2/api/user";
	
	/*
	 * Jsp path
	 */
	
	public static final String USER_LIST = "user/user-list";
	public static final String USER_LOGIN = "user/user-login";
	public static final String USER_CREATE = "user/user-create";
	public static final String USER_UPDATE = "user/user-update";
	public static final String USER_DELETE = "user/user-delete";
	public static final String USER_SINGLE = "user/user-single";
	public static final String USER_MULTIPLE = "user/user-multiple";
	public static final String USER_VIEW = "user/user-view";
	
	public static final String SOCIALITY_CATEGORY_LIST = "sociality/category-list";
	public static final String SOCIALITY_CATEGORY_CREATE = "sociality/category-create";
	public static final String SOCIALITY_CATEGORY_UPDATE = "sociality/category-update";
	public static final String SOCIALITY_CATEGORY_DELETE = "sociality/category-delete";
	public static final String SOCIALITY_CATEGORY_VIEW = "sociality/category-view";
	
	public static final String SOCIALITY_ATTRIBUTE_LIST = "sociality/attribute-list";
	public static final String SOCIALITY_ATTRIBUTE_CREATE = "sociality/attribute-create";
	public static final String SOCIALITY_ATTRIBUTE_UPDATE = "sociality/attribute-update";
	public static final String SOCIALITY_ATTRIBUTE_DELETE = "sociality/attribute-delete";
	public static final String SOCIALITY_ATTRIBUTE_VIEW = "sociality/attribute-view";
	
	public static final String SOCIALITY_STATUS_LIST = "sociality/status-list";
	public static final String SOCIALITY_STATUS_CREATE = "sociality/status-create";
	public static final String SOCIALITY_STATUS_UPDATE = "sociality/status-update";
	public static final String SOCIALITY_STATUS_DELETE = "sociality/status-delete";
	public static final String SOCIALITY_STATUS_VIEW = "sociality/status-view";
	
	public static final String SOCIALITY_SCHEDULED_LIST = "sociality/scheduled-list";
	public static final String SOCIALITY_SCHEDULED_CREATE = "sociality/scheduled-create";
	public static final String SOCIALITY_SCHEDULED_UPDATE = "sociality/scheduled-update";
	public static final String SOCIALITY_SCHEDULED_DELETE = "sociality/scheduled-delete";
	public static final String SOCIALITY_SCHEDULED_VIEW = "sociality/scheduled-view";
	
	public static final String SOCIALITY_CARD_LIST = "sociality/card-list";
	public static final String SOCIALITY_CARD_CREATE = "sociality/card-create";
	public static final String SOCIALITY_CARD_UPDATE = "sociality/card-update";
	public static final String SOCIALITY_CARD_DELETE = "sociality/card-delete";
	public static final String SOCIALITY_CARD_VIEW = "sociality/card-view";
	public static final String SOCIALITY_CARD_BUSSINESS = "sociality/card-bussiness";
	
	public static final String SOCIALITY_CONTACT_LIST = "sociality/card-contact";
	public static final String SOCIALITY_CONTACT_CREATE = "sociality/card-contact-list";
	public static final String SOCIALITY_CONTACT_UPDATE = "sociality/contact-update";
	public static final String SOCIALITY_CONTACT_DELETE = "sociality/contact-delete";
	public static final String SOCIALITY_CONTACT_VIEW = "sociality/contact-view";	
	
	public static final String D_SCHEDULED_LIST = "sociality/d-scheduled-list";
	/*
	 * 系統狀態
	 */
	
	public enum Status { 
		SELECT_USER_MSG001("無此使用者"),

		SELECT_BUSINESS_CARD_MSG001("無此名片資料"),
		SELECT_BUSINESS_CARD_MSG002("篩選條件格式請使用JSON"),
		SELECT_BUSINESS_CARD_MSG003("無此名片資料"),

		SELECT_SCHEDULE_MSG001("篩選條件格式請使用JSON"),
		SELECT_SCHEDULE_MSG002("無此行程資料"),
		
		CREATE_USER_MSG001("帳號已存在"),
		CREATE_USER_MSG002("請確認密碼是否一致"),
		CREATE_USER_MSG003("註冊失敗"),
		
		CREATE_CARD_MSG001("日期格式錯誤"),
		
		CREATE_CATEGORY_MSG001("類別名稱已存在"),
		CREATE_CATEGORY_MSG002("新增失敗"),

		CREATE_ATTRIBUTE_MSG001("屬性名稱已存在"),
		CREATE_ATTRIBUTE_MSG002("新增失敗"),
		
		CREATE_STATUS_MSG001("狀態名稱已存在"),
		CREATE_STATUS_MSG002("新增失敗"),

		CREATE_BUSINESS_MSG001("名片已存在"),
		CREATE_BUSINESS_MSG002("新增失敗"),
		CREATE_BUSINESS_MSG003("新增人脈錯誤"),
		CREATE_BUSINESS_MSG004("新增圖片失敗"),

		CREATE_BUSINESS_SOCIAL_MSG001("新增人脈失敗"),

		CREATE_SCHEDULE_PROJECT_MSG001("項目名稱已存在"),
		CREATE_SCHEDULE_PROJECT_MSG002("新增失敗"),

		CREATE_SCHEDULE_MSG001("行程名稱已存在"),
		CREATE_SCHEDULE_MSG002("新增失敗"),

		UPDATE_BUSINESS_MSG001("名片已存在"),
		UPDATE_BUSINESS_MSG002("更新失敗"),
		UPDATE_BUSINESS_MSG003("更新人脈錯誤"),
		UPDATE_BUSINESS_MSG004("新增圖片失敗"),

		UPDATE_BUSINESS_SOCIAL_MSG001("更新人脈失敗"),

		UPDATE_USER_MSG001("更新失敗"),
		UPDATE_USER_MSG002("密碼錯誤"),

		UPDATE_CATEGORY_MSG001("更新名稱重複"),
		UPDATE_CATEGORY_MSG002("更新失敗"),
		UPDATE_CATEGORY_MSG003("無此類別紀錄"),

		UPDATE_ATTRIBUTE_MSG001("更新名稱重複"),
		UPDATE_ATTRIBUTE_MSG002("更新失敗"),
		UPDATE_ATTRIBUTE_MSG003("無此屬性紀錄"),

		UPDATE_STATUS_MSG001("更新名稱重複"),
		UPDATE_STATUS_MSG002("更新失敗"),
		UPDATE_STATUS_MSG003("無此狀態紀錄"),

		UPDATE_SCHEDULE_PROJECT_MSG001("更新名稱重複"),
		UPDATE_SCHEDULE_PROJECT_MSG002("更新失敗"),
		UPDATE_SCHEDULE_PROJECT_MSG003("無此項目紀錄"),

		UPDATE_SCHEDULE_MSG001("更新名稱重複"),
		UPDATE_SCHEDULE_MSG002("更新失敗"),
		UPDATE_SCHEDULE_MSG003("無此行程紀錄"),

		DELETE_USER_MSG001("刪除失敗"),
		DELETE_USER_MSG002("資料使用中，無法刪除"),

		DELETE_CATEGORY_MSG001("無此類別紀錄"),
		DELETE_CATEGORY_MSG002("刪除失敗"),

		DELETE_ATTRIBUTE_MSG001("無此屬性紀錄"),
		DELETE_ATTRIBUTE_MSG002("刪除失敗"),

		DELETE_STATUS_MSG001("無此狀態紀錄"),
		DELETE_STATUS_MSG002("刪除失敗"),

		DELETE_BUSINESS_MSG001("無此名片紀錄"),
		DELETE_BUSINESS_MSG002("刪除失敗"),

		DELETE_SCHEDULE_PROJECT_MSG001("無此項目紀錄"),
		DELETE_SCHEDULE_PROJECT_MSG002("刪除失敗"),

		DELETE_SCHEDULE_MSG001("無此行程紀錄"),
		DELETE_SCHEDULE_MSG002("刪除失敗"),
		
		LOGIN_MSG001("錯誤的帳號或密碼"),
		
		FIELD_MSG001("必要欄位不足"), 
		
		EXCEPTION_RECORD_MSG001("無此錯誤紀錄"),
		EXCEPTION_RECORD_MSG002("新增失敗"),
		
		SUCCESS("200", "執行成功"),
		UNKNOWN_ERROR("999", "未知的錯誤");
		
		private String code;
		private String message;
		
		private Status(String message) {
			this.message = message;
		}
		
		private Status(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		/*
		 * setter getter
		 */
		
		public String getMessage() {
			return message;
		}

		public String getCode() {
			return code;
		}
	}
}
