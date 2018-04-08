package com.taxation.dao;

import com.taxation.bean.SmsCheckCodeEntity;

public interface SmsCheckCodeDao {
    int deleteByPrimaryKey(String smsCheckcodeId);

    int insert(SmsCheckCodeEntity record);

    int insertSelective(SmsCheckCodeEntity record);

    SmsCheckCodeEntity selectByPrimaryKey(String smsCheckcodeId);

    int updateByPrimaryKeySelective(SmsCheckCodeEntity record);

    int updateByPrimaryKey(SmsCheckCodeEntity record);
}