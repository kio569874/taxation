package com.taxation.dao;

import com.taxation.bean.ShoppingTrolleyDto;
import com.taxation.bean.ShoppingTrolleyEntity;

import java.util.List;

public interface ShoppingTrolleyDao {

    int deleteByPrimaryKey(String id);

    int insert(ShoppingTrolleyEntity record);

    int insertSelective(ShoppingTrolleyEntity record);

    ShoppingTrolleyEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShoppingTrolleyEntity record);

    int updateByPrimaryKey(ShoppingTrolleyEntity record);

    List<ShoppingTrolleyDto> getMyShoppingTrolleys(String userId);
}