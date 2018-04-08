package com.taxation.constants.alipay;

/**
 * pay_config表配置启用状态
 * @author yc
 *
 */
public enum AlipayPayConfigStatusEnum {
	/**
	 * 启动
	 */
	START("0"),

	/**
	 * 停用
	 */
	STOP("1");

	String status = "";

	private AlipayPayConfigStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}

}
