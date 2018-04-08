package com.taxation.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.bean.SmsConfigEntity;
import com.taxation.bean.SmsNoticeEntity;
import com.taxation.constants.SmsStatusEnum;
import com.taxation.constants.SmsTypeEnum;
import com.taxation.spi.ISmsHandle;

public class SmsServiceManager {

	private static Logger logger = Logger.getLogger(SmsServiceManager.class);

	private List<SmsConfigEntity> smsConfigEntityList = new ArrayList<SmsConfigEntity>();

	private static SmsServiceManager instance = new SmsServiceManager();

	private SmsServiceManager() {

	}

	public static SmsServiceManager getInstance() {
		return instance;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param smsCheckCodeEntity
	 */
	public void sendSmsCheckCode(SmsCheckCodeEntity smsCheckCodeEntity) {
		// 1、查询当前启用的短信验证码平台信息
		SmsConfigEntity smsConfigEntity = getSmsConfigEntity(SmsTypeEnum.YZM
				.getSmsType());
		if (smsConfigEntity != null) {
			ISmsHandle ISmsHandler = getSmsImplClass(smsConfigEntity
					.getImplClass());
			ISmsHandler.sendSmsCheckCode(smsConfigEntity, smsCheckCodeEntity);
		} else {
			smsCheckCodeEntity.setSendReturncode("9999");
			smsCheckCodeEntity.setSendReturnmessage("未获取到短信平台信息");
		}

	}

	/**
	 * 发送通知短信
	 * 
	 * @param phone
	 * @param checkCodeType
	 * @return
	 */
	public void sendSmsNotice(SmsNoticeEntity smsNoticeEntity) {

	}

	/**
	 * 获取实现类
	 * 
	 * @param smsImpleClass
	 * @return
	 */
	private ISmsHandle getSmsImplClass(String smsImpleClass) {
		ISmsHandle iSmsHandler = null;
		if (iSmsHandler == null) {
			try {
				if (iSmsHandler == null) {
					iSmsHandler = (ISmsHandle) Class.forName(smsImpleClass)
							.newInstance();
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return iSmsHandler;
	}

	private SmsConfigEntity getSmsConfigEntity(String smsType) {
		if (smsConfigEntityList.size() == 0) {
			return null;
		}
		if (smsConfigEntityList != null && smsConfigEntityList.size() > 0) {
			for (SmsConfigEntity smsConfigEntity : smsConfigEntityList) {
				if (smsType.equals(smsConfigEntity.getSmsType())
						&& SmsStatusEnum.START.getStatus().equals(
								smsConfigEntity.getStatus())) {
					return smsConfigEntity;
				}
			}
		}
		return null;
	}

	public List<SmsConfigEntity> getSmsConfigEntityList() {
		return smsConfigEntityList;
	}

	public void setSmsConfigEntityList(List<SmsConfigEntity> smsConfigEntityList) {
		this.smsConfigEntityList = smsConfigEntityList;
	}

	public boolean isSmsConfigEmpty() {
		return smsConfigEntityList == null || smsConfigEntityList.size() == 0;
	}

}
