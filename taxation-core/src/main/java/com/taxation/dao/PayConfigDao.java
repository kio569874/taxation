package com.taxation.dao;

import java.util.List;

import com.taxation.bean.PayConfigEntity;

public interface PayConfigDao {
	int deleteByPrimaryKey(String payConfigId);

	int insert(PayConfigEntity record);

	int insertSelective(PayConfigEntity record);

	PayConfigEntity selectByPrimaryKey(String payConfigId);

	int updateByPrimaryKeySelective(PayConfigEntity record);

	int updateByPrimaryKey(PayConfigEntity record);
	
	List<PayConfigEntity> queryPayConfigList();
}