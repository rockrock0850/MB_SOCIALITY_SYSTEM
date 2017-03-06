package com.mb.sociality.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BBussinessCardCategoryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public BBussinessCardCategoryExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) { throw new RuntimeException("Value for condition cannot be null"); }
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) { throw new RuntimeException("Between values for " + property + " cannot be null"); }
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andGuidIsNull() {
			addCriterion("guid is null");
			return (Criteria) this;
		}

		public Criteria andGuidIsNotNull() {
			addCriterion("guid is not null");
			return (Criteria) this;
		}

		public Criteria andGuidEqualTo(String value) {
			addCriterion("guid =", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotEqualTo(String value) {
			addCriterion("guid <>", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidGreaterThan(String value) {
			addCriterion("guid >", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidGreaterThanOrEqualTo(String value) {
			addCriterion("guid >=", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLessThan(String value) {
			addCriterion("guid <", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLessThanOrEqualTo(String value) {
			addCriterion("guid <=", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLike(String value) {
			addCriterion("guid like", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotLike(String value) {
			addCriterion("guid not like", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidIn(List<String> values) {
			addCriterion("guid in", values, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotIn(List<String> values) {
			addCriterion("guid not in", values, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidBetween(String value1, String value2) {
			addCriterion("guid between", value1, value2, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotBetween(String value1, String value2) {
			addCriterion("guid not between", value1, value2, "guid");
			return (Criteria) this;
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidIsNull() {
			addCriterion("b_corporation_guid is null");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidIsNotNull() {
			addCriterion("b_corporation_guid is not null");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidEqualTo(String value) {
			addCriterion("b_corporation_guid =", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidNotEqualTo(String value) {
			addCriterion("b_corporation_guid <>", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidGreaterThan(String value) {
			addCriterion("b_corporation_guid >", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidGreaterThanOrEqualTo(String value) {
			addCriterion("b_corporation_guid >=", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidLessThan(String value) {
			addCriterion("b_corporation_guid <", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidLessThanOrEqualTo(String value) {
			addCriterion("b_corporation_guid <=", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidLike(String value) {
			addCriterion("b_corporation_guid like", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidNotLike(String value) {
			addCriterion("b_corporation_guid not like", value, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidIn(List<String> values) {
			addCriterion("b_corporation_guid in", values, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidNotIn(List<String> values) {
			addCriterion("b_corporation_guid not in", values, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidBetween(String value1, String value2) {
			addCriterion("b_corporation_guid between", value1, value2, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andBCorporationGuidNotBetween(String value1, String value2) {
			addCriterion("b_corporation_guid not between", value1, value2, "bCorporationGuid");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andIsInvalidIsNull() {
			addCriterion("is_invalid is null");
			return (Criteria) this;
		}

		public Criteria andIsInvalidIsNotNull() {
			addCriterion("is_invalid is not null");
			return (Criteria) this;
		}

		public Criteria andIsInvalidEqualTo(Integer value) {
			addCriterion("is_invalid =", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidNotEqualTo(Integer value) {
			addCriterion("is_invalid <>", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidGreaterThan(Integer value) {
			addCriterion("is_invalid >", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_invalid >=", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidLessThan(Integer value) {
			addCriterion("is_invalid <", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidLessThanOrEqualTo(Integer value) {
			addCriterion("is_invalid <=", value, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidIn(List<Integer> values) {
			addCriterion("is_invalid in", values, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidNotIn(List<Integer> values) {
			addCriterion("is_invalid not in", values, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidBetween(Integer value1, Integer value2) {
			addCriterion("is_invalid between", value1, value2, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andIsInvalidNotBetween(Integer value1, Integer value2) {
			addCriterion("is_invalid not between", value1, value2, "isInvalid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidIsNull() {
			addCriterion("creator_guid is null");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidIsNotNull() {
			addCriterion("creator_guid is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidEqualTo(String value) {
			addCriterion("creator_guid =", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidNotEqualTo(String value) {
			addCriterion("creator_guid <>", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidGreaterThan(String value) {
			addCriterion("creator_guid >", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidGreaterThanOrEqualTo(String value) {
			addCriterion("creator_guid >=", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidLessThan(String value) {
			addCriterion("creator_guid <", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidLessThanOrEqualTo(String value) {
			addCriterion("creator_guid <=", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidLike(String value) {
			addCriterion("creator_guid like", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidNotLike(String value) {
			addCriterion("creator_guid not like", value, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidIn(List<String> values) {
			addCriterion("creator_guid in", values, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidNotIn(List<String> values) {
			addCriterion("creator_guid not in", values, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidBetween(String value1, String value2) {
			addCriterion("creator_guid between", value1, value2, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorGuidNotBetween(String value1, String value2) {
			addCriterion("creator_guid not between", value1, value2, "creatorGuid");
			return (Criteria) this;
		}

		public Criteria andCreatorNameIsNull() {
			addCriterion("creator_name is null");
			return (Criteria) this;
		}

		public Criteria andCreatorNameIsNotNull() {
			addCriterion("creator_name is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorNameEqualTo(String value) {
			addCriterion("creator_name =", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameNotEqualTo(String value) {
			addCriterion("creator_name <>", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameGreaterThan(String value) {
			addCriterion("creator_name >", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameGreaterThanOrEqualTo(String value) {
			addCriterion("creator_name >=", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameLessThan(String value) {
			addCriterion("creator_name <", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameLessThanOrEqualTo(String value) {
			addCriterion("creator_name <=", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameLike(String value) {
			addCriterion("creator_name like", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameNotLike(String value) {
			addCriterion("creator_name not like", value, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameIn(List<String> values) {
			addCriterion("creator_name in", values, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameNotIn(List<String> values) {
			addCriterion("creator_name not in", values, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameBetween(String value1, String value2) {
			addCriterion("creator_name between", value1, value2, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorNameNotBetween(String value1, String value2) {
			addCriterion("creator_name not between", value1, value2, "creatorName");
			return (Criteria) this;
		}

		public Criteria andCreatorDateIsNull() {
			addCriterion("creator_date is null");
			return (Criteria) this;
		}

		public Criteria andCreatorDateIsNotNull() {
			addCriterion("creator_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorDateEqualTo(Date value) {
			addCriterion("creator_date =", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateNotEqualTo(Date value) {
			addCriterion("creator_date <>", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateGreaterThan(Date value) {
			addCriterion("creator_date >", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateGreaterThanOrEqualTo(Date value) {
			addCriterion("creator_date >=", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateLessThan(Date value) {
			addCriterion("creator_date <", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateLessThanOrEqualTo(Date value) {
			addCriterion("creator_date <=", value, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateIn(List<Date> values) {
			addCriterion("creator_date in", values, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateNotIn(List<Date> values) {
			addCriterion("creator_date not in", values, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateBetween(Date value1, Date value2) {
			addCriterion("creator_date between", value1, value2, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andCreatorDateNotBetween(Date value1, Date value2) {
			addCriterion("creator_date not between", value1, value2, "creatorDate");
			return (Criteria) this;
		}

		public Criteria andModifierGuidIsNull() {
			addCriterion("modifier_guid is null");
			return (Criteria) this;
		}

		public Criteria andModifierGuidIsNotNull() {
			addCriterion("modifier_guid is not null");
			return (Criteria) this;
		}

		public Criteria andModifierGuidEqualTo(String value) {
			addCriterion("modifier_guid =", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidNotEqualTo(String value) {
			addCriterion("modifier_guid <>", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidGreaterThan(String value) {
			addCriterion("modifier_guid >", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidGreaterThanOrEqualTo(String value) {
			addCriterion("modifier_guid >=", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidLessThan(String value) {
			addCriterion("modifier_guid <", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidLessThanOrEqualTo(String value) {
			addCriterion("modifier_guid <=", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidLike(String value) {
			addCriterion("modifier_guid like", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidNotLike(String value) {
			addCriterion("modifier_guid not like", value, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidIn(List<String> values) {
			addCriterion("modifier_guid in", values, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidNotIn(List<String> values) {
			addCriterion("modifier_guid not in", values, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidBetween(String value1, String value2) {
			addCriterion("modifier_guid between", value1, value2, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierGuidNotBetween(String value1, String value2) {
			addCriterion("modifier_guid not between", value1, value2, "modifierGuid");
			return (Criteria) this;
		}

		public Criteria andModifierNameIsNull() {
			addCriterion("modifier_name is null");
			return (Criteria) this;
		}

		public Criteria andModifierNameIsNotNull() {
			addCriterion("modifier_name is not null");
			return (Criteria) this;
		}

		public Criteria andModifierNameEqualTo(String value) {
			addCriterion("modifier_name =", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameNotEqualTo(String value) {
			addCriterion("modifier_name <>", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameGreaterThan(String value) {
			addCriterion("modifier_name >", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameGreaterThanOrEqualTo(String value) {
			addCriterion("modifier_name >=", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameLessThan(String value) {
			addCriterion("modifier_name <", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameLessThanOrEqualTo(String value) {
			addCriterion("modifier_name <=", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameLike(String value) {
			addCriterion("modifier_name like", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameNotLike(String value) {
			addCriterion("modifier_name not like", value, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameIn(List<String> values) {
			addCriterion("modifier_name in", values, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameNotIn(List<String> values) {
			addCriterion("modifier_name not in", values, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameBetween(String value1, String value2) {
			addCriterion("modifier_name between", value1, value2, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierNameNotBetween(String value1, String value2) {
			addCriterion("modifier_name not between", value1, value2, "modifierName");
			return (Criteria) this;
		}

		public Criteria andModifierDateIsNull() {
			addCriterion("modifier_date is null");
			return (Criteria) this;
		}

		public Criteria andModifierDateIsNotNull() {
			addCriterion("modifier_date is not null");
			return (Criteria) this;
		}

		public Criteria andModifierDateEqualTo(Date value) {
			addCriterion("modifier_date =", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateNotEqualTo(Date value) {
			addCriterion("modifier_date <>", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateGreaterThan(Date value) {
			addCriterion("modifier_date >", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateGreaterThanOrEqualTo(Date value) {
			addCriterion("modifier_date >=", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateLessThan(Date value) {
			addCriterion("modifier_date <", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateLessThanOrEqualTo(Date value) {
			addCriterion("modifier_date <=", value, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateIn(List<Date> values) {
			addCriterion("modifier_date in", values, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateNotIn(List<Date> values) {
			addCriterion("modifier_date not in", values, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateBetween(Date value1, Date value2) {
			addCriterion("modifier_date between", value1, value2, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andModifierDateNotBetween(Date value1, Date value2) {
			addCriterion("modifier_date not between", value1, value2, "modifierDate");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidIsNull() {
			addCriterion("invalid_guid is null");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidIsNotNull() {
			addCriterion("invalid_guid is not null");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidEqualTo(String value) {
			addCriterion("invalid_guid =", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidNotEqualTo(String value) {
			addCriterion("invalid_guid <>", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidGreaterThan(String value) {
			addCriterion("invalid_guid >", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidGreaterThanOrEqualTo(String value) {
			addCriterion("invalid_guid >=", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidLessThan(String value) {
			addCriterion("invalid_guid <", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidLessThanOrEqualTo(String value) {
			addCriterion("invalid_guid <=", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidLike(String value) {
			addCriterion("invalid_guid like", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidNotLike(String value) {
			addCriterion("invalid_guid not like", value, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidIn(List<String> values) {
			addCriterion("invalid_guid in", values, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidNotIn(List<String> values) {
			addCriterion("invalid_guid not in", values, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidBetween(String value1, String value2) {
			addCriterion("invalid_guid between", value1, value2, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidGuidNotBetween(String value1, String value2) {
			addCriterion("invalid_guid not between", value1, value2, "invalidGuid");
			return (Criteria) this;
		}

		public Criteria andInvalidNameIsNull() {
			addCriterion("invalid_name is null");
			return (Criteria) this;
		}

		public Criteria andInvalidNameIsNotNull() {
			addCriterion("invalid_name is not null");
			return (Criteria) this;
		}

		public Criteria andInvalidNameEqualTo(String value) {
			addCriterion("invalid_name =", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameNotEqualTo(String value) {
			addCriterion("invalid_name <>", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameGreaterThan(String value) {
			addCriterion("invalid_name >", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameGreaterThanOrEqualTo(String value) {
			addCriterion("invalid_name >=", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameLessThan(String value) {
			addCriterion("invalid_name <", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameLessThanOrEqualTo(String value) {
			addCriterion("invalid_name <=", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameLike(String value) {
			addCriterion("invalid_name like", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameNotLike(String value) {
			addCriterion("invalid_name not like", value, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameIn(List<String> values) {
			addCriterion("invalid_name in", values, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameNotIn(List<String> values) {
			addCriterion("invalid_name not in", values, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameBetween(String value1, String value2) {
			addCriterion("invalid_name between", value1, value2, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidNameNotBetween(String value1, String value2) {
			addCriterion("invalid_name not between", value1, value2, "invalidName");
			return (Criteria) this;
		}

		public Criteria andInvalidDateIsNull() {
			addCriterion("invalid_date is null");
			return (Criteria) this;
		}

		public Criteria andInvalidDateIsNotNull() {
			addCriterion("invalid_date is not null");
			return (Criteria) this;
		}

		public Criteria andInvalidDateEqualTo(Date value) {
			addCriterion("invalid_date =", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateNotEqualTo(Date value) {
			addCriterion("invalid_date <>", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateGreaterThan(Date value) {
			addCriterion("invalid_date >", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateGreaterThanOrEqualTo(Date value) {
			addCriterion("invalid_date >=", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateLessThan(Date value) {
			addCriterion("invalid_date <", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateLessThanOrEqualTo(Date value) {
			addCriterion("invalid_date <=", value, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateIn(List<Date> values) {
			addCriterion("invalid_date in", values, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateNotIn(List<Date> values) {
			addCriterion("invalid_date not in", values, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateBetween(Date value1, Date value2) {
			addCriterion("invalid_date between", value1, value2, "invalidDate");
			return (Criteria) this;
		}

		public Criteria andInvalidDateNotBetween(Date value1, Date value2) {
			addCriterion("invalid_date not between", value1, value2, "invalidDate");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_bussiness_card_category
     *
     * @mbggenerated do_not_delete_during_merge Mon May 30 10:40:53 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}