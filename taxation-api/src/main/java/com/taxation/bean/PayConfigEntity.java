package com.taxation.bean;

import java.io.Serializable;

public class PayConfigEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3418073249662409921L;

	private String payConfigId;

	private String payKey;

	private String payType;

	private String payName;

	private String payDesc;

	private String configStatus;

	private String sendUrl;

	private String returnUrl;

	private String notifyUrl;

	private String appId;

	private String appPrivateKey;

	private String appPublicKey;

	private String exosysPublicKey;

	private String signType;

	private String implClass;

	public String getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(String payConfigId) {
		this.payConfigId = payConfigId == null ? null : payConfigId.trim();
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey == null ? null : payKey.trim();
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName == null ? null : payName.trim();
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc == null ? null : payDesc.trim();
	}

	public String getConfigStatus() {
		return configStatus;
	}

	public void setConfigStatus(String configStatus) {
		this.configStatus = configStatus == null ? null : configStatus.trim();
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl == null ? null : sendUrl.trim();
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl == null ? null : returnUrl.trim();
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getAppPrivateKey() {
		return appPrivateKey;
	}

	public void setAppPrivateKey(String appPrivateKey) {
		this.appPrivateKey = appPrivateKey == null ? null : appPrivateKey
				.trim();
	}

	public String getAppPublicKey() {
		return appPublicKey;
	}

	public void setAppPublicKey(String appPublicKey) {
		this.appPublicKey = appPublicKey == null ? null : appPublicKey.trim();
	}

	public String getExosysPublicKey() {
		return exosysPublicKey;
	}

	public void setExosysPublicKey(String exosysPublicKey) {
		this.exosysPublicKey = exosysPublicKey == null ? null : exosysPublicKey
				.trim();
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType == null ? null : signType.trim();
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass == null ? null : implClass.trim();
	}
}