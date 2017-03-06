package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.mb.sociality.model.DBussinessCard;

public class CardCreateVO extends DBussinessCard{
	
    private MultipartFile file = null;
	private String img;	
	private String bBussinessCardCategoryGuid = ""; 
	private String bBussinessCardStatusGuid = "";
	private String bBussinessCardAttributeGuid = "";
	private String bEnterpriseEmployeeGuid = "";
	@NotBlank(message = "請輸入名稱欄位")
	private String name = "";
	private String title = "";
	private String nickname = "";
	private String cellphoneNumber = "";
	private String email = "";
	private String companyName = "";
	private String companyPhoneNumber = "";
	private String feature = "";
	private String birthday = "";
	private String birthdayYear = "";
	private String birthdayMonth = "";
	private String birthdayDate = "";
	private String marrage = "";
	private String firstMeetTimeString = "";
	private String firstMeetLocation = "";
	private String companyAddress = "";
	private String imagePath = "";
	private String remark = "";
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getbBussinessCardCategoryGuid() {
		return bBussinessCardCategoryGuid;
	}
	public void setbBussinessCardCategoryGuid(String bBussinessCardCategoryGuid) {
		this.bBussinessCardCategoryGuid = bBussinessCardCategoryGuid;
	}
	public String getbBussinessCardStatusGuid() {
		return bBussinessCardStatusGuid;
	}
	public void setbBussinessCardStatusGuid(String bBussinessCardStatusGuid) {
		this.bBussinessCardStatusGuid = bBussinessCardStatusGuid;
	}
	public String getbBussinessCardAttributeGuid() {
		return bBussinessCardAttributeGuid;
	}
	public void setbBussinessCardAttributeGuid(String bBussinessCardAttributeGuid) {
		this.bBussinessCardAttributeGuid = bBussinessCardAttributeGuid;
	}
	public String getbEnterpriseEmployeeGuid() {
		return bEnterpriseEmployeeGuid;
	}
	public void setbEnterpriseEmployeeGuid(String bEnterpriseEmployeeGuid) {
		this.bEnterpriseEmployeeGuid = bEnterpriseEmployeeGuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}
	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthdayYear() {
		return birthdayYear;
	}
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	public String getBirthdayMonth() {
		return birthdayMonth;
	}
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}
	public String getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getMarrage() {
		return marrage;
	}
	public void setMarrage(String marrage) {
		this.marrage = marrage;
	}
	public String getFirstMeetTimeString() {
		return firstMeetTimeString;
	}
	public void setFirstMeetTimeString(String firstMeetTimeString) {
		this.firstMeetTimeString = firstMeetTimeString;
	}
	public String getFirstMeetLocation() {
		return firstMeetLocation;
	}
	public void setFirstMeetLocation(String firstMeetLocation) {
		this.firstMeetLocation = firstMeetLocation;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
