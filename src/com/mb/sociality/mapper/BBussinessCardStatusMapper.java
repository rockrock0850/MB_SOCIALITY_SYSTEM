package com.mb.sociality.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mb.sociality.model.BBussinessCardStatus;
import com.mb.sociality.model.BBussinessCardStatusExample;

public interface BBussinessCardStatusMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int countByExample(BBussinessCardStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int deleteByExample(BBussinessCardStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int deleteByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int insert(BBussinessCardStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int insertSelective(BBussinessCardStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	List<BBussinessCardStatus> selectByExample(BBussinessCardStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	BBussinessCardStatus selectByPrimaryKey(String guid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int updateByExampleSelective(@Param("record") BBussinessCardStatus record, @Param("example") BBussinessCardStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int updateByExample(@Param("record") BBussinessCardStatus record, @Param("example") BBussinessCardStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int updateByPrimaryKeySelective(BBussinessCardStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table b_bussiness_card_status
	 * @mbggenerated  Thu Jun 02 10:45:34 CST 2016
	 */
	int updateByPrimaryKey(BBussinessCardStatus record);
	
	/*
	 * 自定義方法
	 */

	List<BBussinessCardStatus> selectByLimit(@Param("offset") int offset, @Param("limit") int limit);
}