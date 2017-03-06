package com.mb.sociality.mapper;

import com.mb.sociality.model.BBussinessCardCategory;
import com.mb.sociality.model.BBussinessCardCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBussinessCardCategoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int countByExample(BBussinessCardCategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int deleteByExample(BBussinessCardCategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int deleteByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int insert(BBussinessCardCategory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int insertSelective(BBussinessCardCategory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	List<BBussinessCardCategory> selectByExample(BBussinessCardCategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	BBussinessCardCategory selectByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int updateByExampleSelective(@Param("record") BBussinessCardCategory record, @Param("example") BBussinessCardCategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int updateByExample(@Param("record") BBussinessCardCategory record, @Param("example") BBussinessCardCategoryExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int updateByPrimaryKeySelective(BBussinessCardCategory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_category
	 * @mbggenerated  Thu Jun 02 10:44:37 CST 2016
	 */
	int updateByPrimaryKey(BBussinessCardCategory record);
	
	/*
	 * 自定義方法
	 */

	List<BBussinessCardCategory> selectByLimit(@Param("offset") int offset, @Param("limit") int limit);
}