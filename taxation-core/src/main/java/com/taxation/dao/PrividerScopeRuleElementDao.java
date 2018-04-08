package com.taxation.dao;

import com.taxation.bean.PrividerScopeRuleElementEntity;

public interface PrividerScopeRuleElementDao {
    int deleteByPrimaryKey(PrividerScopeRuleElementEntity key);

    int insert(PrividerScopeRuleElementEntity record);

    int insertSelective(PrividerScopeRuleElementEntity record);

    PrividerScopeRuleElementEntity selectByPrimaryKey(PrividerScopeRuleElementEntity key);

    int updateByPrimaryKeySelective(PrividerScopeRuleElementEntity record);

    int updateByPrimaryKey(PrividerScopeRuleElementEntity record);
}