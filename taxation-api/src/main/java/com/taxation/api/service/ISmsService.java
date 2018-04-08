package com.taxation.api.service;

import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.bean.SmsNoticeEntity;

public interface ISmsService {

	SmsCheckCodeEntity sendSmsCheckCode(String phone, String checkCodeType);

	SmsNoticeEntity sendSmsNotity(String phone, String checkCodeType);
}
