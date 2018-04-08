package com.taxation.dao;

import com.taxation.bean.SmsNoticeEntity;

public interface SmsNoticeDao {
	int deleteByPrimaryKey(String smsNoticeId);

	int insert(SmsNoticeEntity record);

	int insertSelective(SmsNoticeEntity record);

	SmsNoticeEntity selectByPrimaryKey(String smsNoticeId);

	int updateByPrimaryKeySelective(SmsNoticeEntity record);

	int updateByPrimaryKey(SmsNoticeEntity record);
}