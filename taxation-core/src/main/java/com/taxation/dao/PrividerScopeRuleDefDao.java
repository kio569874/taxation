package com.taxation.dao;

import com.taxation.bean.PrividerScopeRuleDefEntity;

public interface PrividerScopeRuleDefDao {
    int deleteByPrimaryKey(String ruleCode);

    int insert(PrividerScopeRuleDefEntity record);

    int insertSelective(PrividerScopeRuleDefEntity record);

    PrividerScopeRuleDefEntity selectByPrimaryKey(String ruleCode);

    int updateByPrimaryKeySelective(PrividerScopeRuleDefEntity record);

    int updateByPrimaryKey(PrividerScopeRuleDefEntity record);
}