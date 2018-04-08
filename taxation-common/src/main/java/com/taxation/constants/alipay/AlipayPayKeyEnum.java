package com.taxation.constants.alipay;

public enum AlipayPayKeyEnum {

	/**
	 * 支付宝
	 */
	ALIPAY("alipay"),

	/**
	 * 微信
	 */
	WEIXIN("weixin");

	String code = "";

	private AlipayPayKeyEnum(String code) {
		this.code = code;
	}

	public String getCode() {

		return this.code;
	}

}
