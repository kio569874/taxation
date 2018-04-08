package com.taxation.constants.alipay;

/**
 * 支付宝订单支付状态 t_alipay_refund_jnl 表refund_status
 * 
 * @author yc
 *
 */
public enum AlipayRefundStatusEnum {
	/**
	 * 初始化
	 */
	INIT("0"),

	/**
	 * 退款中
	 */
	REFUNDMENT("1"),
	/**
	 * 退款成功
	 */
	REFUNDSUCCESS("2"),
	/**
	 * 退款失败
	 */
	REFUNDFAILED("3");

	String status = "";

	private AlipayRefundStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}
}
