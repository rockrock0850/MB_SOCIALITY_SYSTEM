package com.mb.sociality.mapper;

import com.mb.sociality.model.ExceptionRecord;
import com.mb.sociality.model.ExceptionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExceptionRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int countByExample(ExceptionRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int deleteByExample(ExceptionRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int insert(ExceptionRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int insertSelective(ExceptionRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    List<ExceptionRecord> selectByExample(ExceptionRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int updateByExampleSelective(@Param("record") ExceptionRecord record, @Param("example") ExceptionRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exception_record
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    int updateByExample(@Param("record") ExceptionRecord record, @Param("example") ExceptionRecordExample example);
}