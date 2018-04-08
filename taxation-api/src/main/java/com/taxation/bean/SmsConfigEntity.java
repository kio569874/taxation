package com.taxation.bean;

import java.io.Serializable;

public class SmsConfigEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2589937371403869435L;

	private String smsConfigId;

	private String smsKey;

	private String smsName;

	private String smsType;

	private String smsDesc;

	private String status;

	private String sendUrl;

	private String recUrl;

	private String apikey;

	private String mouldContext;

	private String validDuration;

	private String implClass;

	public String getSmsConfigId() {
		return smsConfigId;
	}

	public void setSmsConfigId(String smsConfigId) {
		this.smsConfigId = smsConfigId == null ? null : smsConfigId.trim();
	}

	public String getSmsKey() {
		return smsKey;
	}

	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey == null ? null : smsKey.trim();
	}

	public String getSmsName() {
		return smsName;
	}

	public void setSmsName(String smsName) {
		this.smsName = smsName == null ? null : smsName.trim();
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType == null ? null : smsType.trim();
	}

	public String getSmsDesc() {
		return smsDesc;
	}

	public void setSmsDesc(String smsDesc) {
		this.smsDesc = smsDesc == null ? null : smsDesc.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl == null ? null : sendUrl.trim();
	}

	public String getRecUrl() {
		return recUrl;
	}

	public void setRecUrl(String recUrl) {
		this.recUrl = recUrl == null ? null : recUrl.trim();
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey == null ? null : apikey.trim();
	}

	public String getMouldContext() {
		return mouldContext;
	}

	public void setMouldContext(String mouldContext) {
		this.mouldContext = mouldContext == null ? null : mouldContext.trim();
	}

	public String getValidDuration() {
		return validDuration;
	}

	public void setValidDuration(String validDuration) {
		this.validDuration = validDuration == null ? null : validDuration
				.trim();
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass == null ? null : implClass.trim();
	}
}