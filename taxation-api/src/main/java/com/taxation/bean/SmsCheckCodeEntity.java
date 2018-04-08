package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class SmsCheckCodeEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5461489827007330529L;

	private String smsCheckcodeId;

    private String userPhone;

    private String smsCheckCode;

    private String smsContext;

    private Date sendTime;

    private String sendReturncode;

    private String sendReturnmessage;

    private Integer sendCount;

    private String sendFee;

    private String feeUnit;

    private String sendId;

    private String checkcodeType;

    private Date checkTime;

    private String checkResult;

    private Integer checkErrorNum;

    private Date createTime;

    public String getSmsCheckcodeId() {
        return smsCheckcodeId;
    }

    public void setSmsCheckcodeId(String smsCheckcodeId) {
        this.smsCheckcodeId = smsCheckcodeId == null ? null : smsCheckcodeId.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getSmsCheckCode() {
        return smsCheckCode;
    }

    public void setSmsCheckCode(String smsCheckCode) {
        this.smsCheckCode = smsCheckCode == null ? null : smsCheckCode.trim();
    }

    public String getSmsContext() {
        return smsContext;
    }

    public void setSmsContext(String smsContext) {
        this.smsContext = smsContext == null ? null : smsContext.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendReturncode() {
        return sendReturncode;
    }

    public void setSendReturncode(String sendReturncode) {
        this.sendReturncode = sendReturncode == null ? null : sendReturncode.trim();
    }

    public String getSendReturnmessage() {
        return sendReturnmessage;
    }

    public void setSendReturnmessage(String sendReturnmessage) {
        this.sendReturnmessage = sendReturnmessage == null ? null : sendReturnmessage.trim();
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public String getSendFee() {
        return sendFee;
    }

    public void setSendFee(String sendFee) {
        this.sendFee = sendFee == null ? null : sendFee.trim();
    }

    public String getFeeUnit() {
        return feeUnit;
    }

    public void setFeeUnit(String feeUnit) {
        this.feeUnit = feeUnit == null ? null : feeUnit.trim();
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public String getCheckcodeType() {
        return checkcodeType;
    }

    public void setCheckcodeType(String checkcodeType) {
        this.checkcodeType = checkcodeType == null ? null : checkcodeType.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Integer getCheckErrorNum() {
        return checkErrorNum;
    }

    public void setCheckErrorNum(Integer checkErrorNum) {
        this.checkErrorNum = checkErrorNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}