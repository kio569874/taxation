package com.taxation.dao;

import com.taxation.bean.PrividerScopeElementDefEntity;

public interface PrividerScopeElementDefDao {
    int deleteByPrimaryKey(String elementCode);

    int insert(PrividerScopeElementDefEntity record);

    int insertSelective(PrividerScopeElementDefEntity record);

    PrividerScopeElementDefEntity selectByPrimaryKey(String elementCode);

    int updateByPrimaryKeySelective(PrividerScopeElementDefEntity record);

    int updateByPrimaryKey(PrividerScopeElementDefEntity record);
}