package com.taxation.constants;

/**
 * t_order表的pay_status
 * 
 */
public enum PayStatusEnum {
	/**
	 * 待付款
	 */
	WAITPAY("0"),

	/**
	 * 已支付
	 */
	PAYSUCCESS("1"),
	/**
	 * 支付超时
	 */
	PAYTIMEOUT("2");

	String status = "";

	private PayStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}
}
