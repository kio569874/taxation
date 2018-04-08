package com.taxation.spi;

import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.bean.SmsConfigEntity;
import com.taxation.bean.SmsNoticeEntity;

public interface ISmsHandle {

	void sendSmsCheckCode(SmsConfigEntity smsConfigEntity,SmsCheckCodeEntity smsCheckCodeEntity);

	void sendSmsNotice(SmsConfigEntity smsConfigEntity,SmsNoticeEntity smsNoticeEntity);
}
