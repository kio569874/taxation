package com.taxation.constants;

/**
 * 短信类型枚举，对应sms_config的sms_type字段
 * @author yc
 *
 */
public enum SmsTypeEnum {
	/**
	 * 验证码
	 */
	YZM("YZM"),

	/**
	 * 通知
	 */
	NOTICE("NOTICE");

	String smsType = "";

	private SmsTypeEnum(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsType() {

		return this.smsType;
	}

	public boolean isSameState(String smsTypeX) {
		return this.smsType.equals(smsTypeX);
	}
}
