package com.mb.sociality.model;

import java.util.Date;

import com.mb.sociality.base.BaseModel;

public class DBussinessCard extends BaseModel{
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String guid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.id
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.b_corporation_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String bCorporationGuid = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.b_bussiness_card_category_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String bBussinessCardCategoryGuid = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.b_bussiness_card_status_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String bBussinessCardStatusGuid = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.b_bussiness_card_attribute_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String bBussinessCardAttributeGuid = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.b_enterprise_employee_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String bEnterpriseEmployeeGuid = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String name = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.title
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String title = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.nickname
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String nickname = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.cellphone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String cellphoneNumber = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.email
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String email = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.company_name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String companyName = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.company_address
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String companyAddress = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.company_phone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String companyPhoneNumber = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.feature
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String feature = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.birthday_year
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String birthdayYear = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.birthday_month
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String birthdayMonth = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.birthday_date
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String birthdayDate = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.marrage
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String marrage = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.first_meet_time
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private Date firstMeetTime = new Date();
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.first_meet_location
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String firstMeetLocation = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.image
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String image = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.image_path
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String imagePath = "";
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column d_bussiness_card.remark
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	private String remark = "";

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.guid
	 * @return  the value of d_bussiness_card.guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.guid
	 * @param guid  the value for d_bussiness_card.guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.id
	 * @return  the value of d_bussiness_card.id
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.id
	 * @param id  the value for d_bussiness_card.id
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.b_corporation_guid
	 * @return  the value of d_bussiness_card.b_corporation_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getbCorporationGuid() {
		return bCorporationGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.b_corporation_guid
	 * @param bCorporationGuid  the value for d_bussiness_card.b_corporation_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setbCorporationGuid(String bCorporationGuid) {
		this.bCorporationGuid = bCorporationGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.b_bussiness_card_category_guid
	 * @return  the value of d_bussiness_card.b_bussiness_card_category_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getbBussinessCardCategoryGuid() {
		return bBussinessCardCategoryGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.b_bussiness_card_category_guid
	 * @param bBussinessCardCategoryGuid  the value for d_bussiness_card.b_bussiness_card_category_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setbBussinessCardCategoryGuid(String bBussinessCardCategoryGuid) {
		this.bBussinessCardCategoryGuid = bBussinessCardCategoryGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.b_bussiness_card_status_guid
	 * @return  the value of d_bussiness_card.b_bussiness_card_status_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getbBussinessCardStatusGuid() {
		return bBussinessCardStatusGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.b_bussiness_card_status_guid
	 * @param bBussinessCardStatusGuid  the value for d_bussiness_card.b_bussiness_card_status_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setbBussinessCardStatusGuid(String bBussinessCardStatusGuid) {
		this.bBussinessCardStatusGuid = bBussinessCardStatusGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.b_bussiness_card_attribute_guid
	 * @return  the value of d_bussiness_card.b_bussiness_card_attribute_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getbBussinessCardAttributeGuid() {
		return bBussinessCardAttributeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.b_bussiness_card_attribute_guid
	 * @param bBussinessCardAttributeGuid  the value for d_bussiness_card.b_bussiness_card_attribute_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setbBussinessCardAttributeGuid(String bBussinessCardAttributeGuid) {
		this.bBussinessCardAttributeGuid = bBussinessCardAttributeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.b_enterprise_employee_guid
	 * @return  the value of d_bussiness_card.b_enterprise_employee_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getbEnterpriseEmployeeGuid() {
		return bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.b_enterprise_employee_guid
	 * @param bEnterpriseEmployeeGuid  the value for d_bussiness_card.b_enterprise_employee_guid
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setbEnterpriseEmployeeGuid(String bEnterpriseEmployeeGuid) {
		this.bEnterpriseEmployeeGuid = bEnterpriseEmployeeGuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.name
	 * @return  the value of d_bussiness_card.name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.name
	 * @param name  the value for d_bussiness_card.name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.title
	 * @return  the value of d_bussiness_card.title
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.title
	 * @param title  the value for d_bussiness_card.title
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.nickname
	 * @return  the value of d_bussiness_card.nickname
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.nickname
	 * @param nickname  the value for d_bussiness_card.nickname
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.cellphone_number
	 * @return  the value of d_bussiness_card.cellphone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.cellphone_number
	 * @param cellphoneNumber  the value for d_bussiness_card.cellphone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.email
	 * @return  the value of d_bussiness_card.email
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.email
	 * @param email  the value for d_bussiness_card.email
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.company_name
	 * @return  the value of d_bussiness_card.company_name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.company_name
	 * @param companyName  the value for d_bussiness_card.company_name
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.company_address
	 * @return  the value of d_bussiness_card.company_address
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.company_address
	 * @param companyAddress  the value for d_bussiness_card.company_address
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.company_phone_number
	 * @return  the value of d_bussiness_card.company_phone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.company_phone_number
	 * @param companyPhoneNumber  the value for d_bussiness_card.company_phone_number
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.feature
	 * @return  the value of d_bussiness_card.feature
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.feature
	 * @param feature  the value for d_bussiness_card.feature
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.birthday_year
	 * @return  the value of d_bussiness_card.birthday_year
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getBirthdayYear() {
		return birthdayYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.birthday_year
	 * @param birthdayYear  the value for d_bussiness_card.birthday_year
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.birthday_month
	 * @return  the value of d_bussiness_card.birthday_month
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.birthday_month
	 * @param birthdayMonth  the value for d_bussiness_card.birthday_month
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.birthday_date
	 * @return  the value of d_bussiness_card.birthday_date
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getBirthdayDate() {
		return birthdayDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.birthday_date
	 * @param birthdayDate  the value for d_bussiness_card.birthday_date
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.marrage
	 * @return  the value of d_bussiness_card.marrage
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getMarrage() {
		return marrage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.marrage
	 * @param marrage  the value for d_bussiness_card.marrage
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setMarrage(String marrage) {
		this.marrage = marrage;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.first_meet_time
	 * @return  the value of d_bussiness_card.first_meet_time
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public Date getFirstMeetTime() {
		return firstMeetTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.first_meet_time
	 * @param firstMeetTime  the value for d_bussiness_card.first_meet_time
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setFirstMeetTime(Date firstMeetTime) {
		this.firstMeetTime = firstMeetTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.first_meet_location
	 * @return  the value of d_bussiness_card.first_meet_location
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getFirstMeetLocation() {
		return firstMeetLocation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.first_meet_location
	 * @param firstMeetLocation  the value for d_bussiness_card.first_meet_location
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setFirstMeetLocation(String firstMeetLocation) {
		this.firstMeetLocation = firstMeetLocation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.image
	 * @return  the value of d_bussiness_card.image
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getImage() {
		return image;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.image
	 * @param image  the value for d_bussiness_card.image
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.image_path
	 * @return  the value of d_bussiness_card.image_path
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.image_path
	 * @param imagePath  the value for d_bussiness_card.image_path
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column d_bussiness_card.remark
	 * @return  the value of d_bussiness_card.remark
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column d_bussiness_card.remark
	 * @param remark  the value for d_bussiness_card.remark
	 * @mbggenerated  Thu Jun 16 10:14:01 CST 2016
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}