package com.taxation.constants;

public enum SmsStatusEnum {
	/**
	 * 启动
	 */
	START("0"),

	/**
	 * 停用
	 */
	STOP("1");

	String status = "";

	private SmsStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}

}
