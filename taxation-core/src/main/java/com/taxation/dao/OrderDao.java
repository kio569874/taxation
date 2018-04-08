package com.taxation.dao;

import java.util.List;

import com.taxation.bean.OrderDto;
import com.taxation.bean.OrderEntity;

public interface OrderDao {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderEntity record);

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderEntity record);

    int updateByPrimaryKey(OrderEntity record);
    
    int getTotalCount(String userId);

    List<OrderDto> queryListByPage(int offset, int pageSize,String userId);
    
    OrderEntity queryOrderEntityByOrderNo(String orderNo);

    int updatePayType(String orderId,String payType);
}