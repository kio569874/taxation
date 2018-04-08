package com.taxation.dao;

import com.taxation.bean.AliPayRefundJnlEntity;

public interface AliPayRefundJnlDao {
    int deleteByPrimaryKey(String refundId);

    int insert(AliPayRefundJnlEntity record);

    int insertSelective(AliPayRefundJnlEntity record);

    AliPayRefundJnlEntity selectByPrimaryKey(String refundId);

    int updateByPrimaryKeySelective(AliPayRefundJnlEntity record);

    int updateByPrimaryKeyWithBLOBs(AliPayRefundJnlEntity record);

    int updateByPrimaryKey(AliPayRefundJnlEntity record);
}