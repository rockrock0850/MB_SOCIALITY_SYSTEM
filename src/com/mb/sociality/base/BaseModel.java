package com.mb.sociality.base;

import java.util.Date;

import com.mb.sociality.utils.ShareTool;

public class BaseModel {
	   private Integer isInvalid = 1;

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.creator_guid
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String creatorGuid = ShareTool.newGUID();

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.creator_name
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String creatorName = "admin";

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.creator_date
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private Date creatorDate = ShareTool.nowDate();

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.modifier_guid
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String modifierGuid = "admin";

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.modifier_name
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String modifierName = "admin"; 

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.modifier_date
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private Date modifierDate = ShareTool.nowDate();

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.invalid_guid
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String invalidGuid = "admin";

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.invalid_name
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private String invalidName = "admin";

	    /**
	     * This field was generated by MyBatis Generator.
	     * This field corresponds to the database column b_bussiness_card_attribute.invalid_date
	     *
	     * @mbggenerated Mon May 30 10:40:53 CST 2016
	     */
	    private Date invalidDate = ShareTool.nowDate();

		public Integer getIsInvalid() {
			return isInvalid;
		}

		public void setIsInvalid(Integer isInvalid) {
			this.isInvalid = isInvalid;
		}

		public String getCreatorGuid() {
			return creatorGuid;
		}

		public void setCreatorGuid(String creatorGuid) {
			this.creatorGuid = creatorGuid;
		}

		public String getCreatorName() {
			return creatorName;
		}

		public void setCreatorName(String creatorName) {
			this.creatorName = creatorName;
		}

		public Date getCreatorDate() {
			return creatorDate;
		}

		public void setCreatorDate(Date creatorDate) {
			this.creatorDate = creatorDate;
		}

		public String getModifierGuid() {
			return modifierGuid;
		}

		public void setModifierGuid(String modifierGuid) {
			this.modifierGuid = modifierGuid;
		}

		public String getModifierName() {
			return modifierName;
		}

		public void setModifierName(String modifierName) {
			this.modifierName = modifierName;
		}

		public Date getModifierDate() {
			return modifierDate;
		}

		public void setModifierDate(Date modifierDate) {
			this.modifierDate = modifierDate;
		}

		public String getInvalidGuid() {
			return invalidGuid;
		}

		public void setInvalidGuid(String invalidGuid) {
			this.invalidGuid = invalidGuid;
		}

		public String getInvalidName() {
			return invalidName;
		}

		public void setInvalidName(String invalidName) {
			this.invalidName = invalidName;
		}

		public Date getInvalidDate() {
			return invalidDate;
		}

		public void setInvalidDate(Date invalidDate) {
			this.invalidDate = invalidDate;
		}
}