package com.taxation.dao;

import com.taxation.bean.PrividerScopeRuleEntity;

public interface PrividerScopeRuleDao {
    int deleteByPrimaryKey(String ruleCode);

    int insert(PrividerScopeRuleEntity record);

    int insertSelective(PrividerScopeRuleEntity record);

    PrividerScopeRuleEntity selectByPrimaryKey(String ruleCode);

    int updateByPrimaryKeySelective(PrividerScopeRuleEntity record);

    int updateByPrimaryKey(PrividerScopeRuleEntity record);
}