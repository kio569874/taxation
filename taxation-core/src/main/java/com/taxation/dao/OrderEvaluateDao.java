package com.taxation.dao;

import com.taxation.bean.OrderEvaluateEntity;

public interface OrderEvaluateDao {
    int insert(OrderEvaluateEntity record);

    int insertSelective(OrderEvaluateEntity record);
}