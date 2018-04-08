package com.taxation.dao;

import com.taxation.bean.OrderServiceEntity;

public interface OrderServiceDao {
    int insert(OrderServiceEntity record);

    int insertSelective(OrderServiceEntity record);
}