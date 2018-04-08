package com.taxation.constants;

/**
 * 支付方式 t_order 的pay_type 0-支付宝，1-微信
 * 
 */
public enum PayTypeEnum {
	/**
	 * 支付宝
	 */
	ALIPAY("0"),

	/**
	 * 微信
	 */
	WEIXIN("1");

	String status = "";

	private PayTypeEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}
}
