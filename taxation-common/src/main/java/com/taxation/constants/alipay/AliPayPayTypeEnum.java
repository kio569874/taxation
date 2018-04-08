package com.taxation.constants.alipay;

public enum AliPayPayTypeEnum {
	/**
	 * 电脑网站
	 */
	PCWEB("pcweb"),

	/**
	 * 手机网站
	 */
	PHONEWEB("phoneweb"),
	/**
	 * 安卓
	 */
	ANDROID("android"),
	/**
	 * ios
	 */
	ISO("ios");

	String code = "";

	private AliPayPayTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {

		return this.code;
	}

}
