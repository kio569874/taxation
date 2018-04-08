package com.taxation.constants;

public enum CheckCodeEnum {

	/**
	 * 0 - 用户注册;
	 */
	USER_REGISTER("01"),

	/**
	 * 1 - 用户登录;
	 */
	USER_LOGIN("02"),
	/**
	 * 用户密码修改
	 */
	USER_UPDATE_PWD("03"),
	/**
	 * 商户注册
	 */
	PROVIDER_REGISTER("11"),
	/**
	 * 商户登录
	 */
	PROVIDER_LOGIN("12"),
	/**
	 * 商户密码修改
	 */
	PROVIDER_UPDATE_PWD("13");

	String checkCodeType = "";

	private CheckCodeEnum(String checkCodeType) {
		this.checkCodeType = checkCodeType;
	}

	public String getCheckCodeType() {

		return this.checkCodeType;
	}

	public boolean isSameState(String checkCodeTypeX) {
		return this.checkCodeType.equals(checkCodeTypeX);
	}

}
