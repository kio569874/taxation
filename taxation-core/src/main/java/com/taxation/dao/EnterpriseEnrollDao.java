package com.taxation.dao;


import com.taxation.bean.EnterpriseEnrollEntity;

public interface EnterpriseEnrollDao {
    int deleteByPrimaryKey(String id);

    int insert(EnterpriseEnrollEntity record);

    int insertSelective(EnterpriseEnrollEntity record);

    EnterpriseEnrollEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnterpriseEnrollEntity record);

    int updateByPrimaryKey(EnterpriseEnrollEntity record);
}