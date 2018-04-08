package com.taxation.dao;

import java.util.List;

import com.taxation.bean.SmsConfigEntity;

public interface SmsConfigDao {
    int deleteByPrimaryKey(String smsConfigId);

    int insert(SmsConfigEntity record);

    int insertSelective(SmsConfigEntity record);

    SmsConfigEntity selectByPrimaryKey(String smsConfigId);

    int updateByPrimaryKeySelective(SmsConfigEntity record);

    int updateByPrimaryKey(SmsConfigEntity record);
    
    List<SmsConfigEntity> querySmsConfigList();
}