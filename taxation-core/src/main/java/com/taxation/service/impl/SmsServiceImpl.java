package com.taxation.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.service.ISmsService;
import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.bean.SmsConfigEntity;
import com.taxation.bean.SmsNoticeEntity;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.SmsCheckCodeDao;
import com.taxation.dao.SmsConfigDao;
import com.taxation.manager.SmsServiceManager;
import com.taxation.utils.CreateIdUtil;

@Service("smsService")
public class SmsServiceImpl implements ISmsService {

	@Resource
	private SmsConfigDao smsConfigDao;
	@Resource
	private SmsCheckCodeDao smsCheckCodeDao;

	@Override
	public SmsCheckCodeEntity sendSmsCheckCode(String phone,
			String checkCodeType) {
		if (SmsServiceManager.getInstance().isSmsConfigEmpty()) {
			List<SmsConfigEntity> smsConfigList = this.smsConfigDao
					.querySmsConfigList();
			SmsServiceManager.getInstance().setSmsConfigEntityList(
					smsConfigList);
		}
		SmsCheckCodeEntity smsCheckCodeEntity = new SmsCheckCodeEntity();
		smsCheckCodeEntity.setSmsCheckcodeId(CreateIdUtil
				.createId(CreateIdContants.SMS_ID_TAG));
		smsCheckCodeEntity.setUserPhone(phone);
		smsCheckCodeEntity.setCheckcodeType(checkCodeType);
		smsCheckCodeEntity.setCreateTime(new Date());

		SmsServiceManager.getInstance().sendSmsCheckCode(smsCheckCodeEntity);
		smsCheckCodeDao.insert(smsCheckCodeEntity);
		return smsCheckCodeEntity;
	}

	@Override
	public SmsNoticeEntity sendSmsNotity(String phone, String checkCodeType) {
		SmsNoticeEntity smsNoticeEntity = new SmsNoticeEntity();
		SmsServiceManager.getInstance().sendSmsNotice(smsNoticeEntity);
		return smsNoticeEntity;
	}

}
