package com.mb.sociality.model;

import java.util.Date;

public class ExceptionRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exception_record.date
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exception_record.cause
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    private String cause;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exception_record.date
     *
     * @return the value of exception_record.date
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exception_record.date
     *
     * @param date the value for exception_record.date
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exception_record.cause
     *
     * @return the value of exception_record.cause
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    public String getCause() {
        return cause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exception_record.cause
     *
     * @param cause the value for exception_record.cause
     *
     * @mbggenerated Tue May 24 09:56:50 CST 2016
     */
    public void setCause(String cause) {
        this.cause = cause;
    }
}