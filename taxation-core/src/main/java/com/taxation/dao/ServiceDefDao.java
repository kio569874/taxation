package com.taxation.dao;

import com.taxation.bean.ServiceDefEntity;

public interface ServiceDefDao {
    int deleteByPrimaryKey(String serviceCode);

    int insert(ServiceDefEntity record);

    int insertSelective(ServiceDefEntity record);

    ServiceDefEntity selectByPrimaryKey(String serviceCode);

    int updateByPrimaryKeySelective(ServiceDefEntity record);

    int updateByPrimaryKey(ServiceDefEntity record);
}