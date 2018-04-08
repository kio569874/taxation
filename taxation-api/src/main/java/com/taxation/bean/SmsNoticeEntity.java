package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class SmsNoticeEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7353661349357395907L;

	private String smsNoticeId;

    private String userPhone;

    private String sendContext;

    private Date sendTime;

    private String sendReturncode;

    private String sendReturnmessage;

    private Integer sendCount;

    private String sendFee;

    private String feeUnit;

    private String sendId;

    private String noticeType;

    private Date createTime;

    public String getSmsNoticeId() {
        return smsNoticeId;
    }

    public void setSmsNoticeId(String smsNoticeId) {
        this.smsNoticeId = smsNoticeId == null ? null : smsNoticeId.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getSendContext() {
        return sendContext;
    }

    public void setSendContext(String sendContext) {
        this.sendContext = sendContext == null ? null : sendContext.trim();
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

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}