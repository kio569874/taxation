package com.taxation.constants.alipay;

/**
 * 支付宝订单支付状态 t_alipay_order_jnl 表order_status
 * 
 * @author yc
 *
 */
public enum AlipayOrderStatusEnum {
	/**
	 * 初始化
	 */
	INIT("0"),

	/**
	 * 支付中
	 */
	PAYMENT("1"),
	/**
	 * 支付成功
	 */
	PAYSUCCESS("2"),
	/**
	 * 支付失败
	 */
	PAYFAILED("3"),

	/**
	 * 支付超时
	 */
	PAYTIMEOUT("4");

	String status = "";

	private AlipayOrderStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}
}
