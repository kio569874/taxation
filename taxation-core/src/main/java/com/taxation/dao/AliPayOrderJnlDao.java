package com.taxation.dao;

import java.util.List;
import java.util.Map;

import com.taxation.bean.AliPayOrderJnlEntity;

public interface AliPayOrderJnlDao {
	int deleteByPrimaryKey(String orderPayId);

	int insert(AliPayOrderJnlEntity record);

	int insertSelective(AliPayOrderJnlEntity record);

	AliPayOrderJnlEntity selectByPrimaryKey(String orderPayId);

	int updateByPrimaryKeySelective(AliPayOrderJnlEntity record);

	int updateByPrimaryKeyWithBLOBs(AliPayOrderJnlEntity record);

	int updateByPrimaryKey(AliPayOrderJnlEntity record);

	List<AliPayOrderJnlEntity> queryAliPayOrderJnlList(Map<String, Object> map);
}