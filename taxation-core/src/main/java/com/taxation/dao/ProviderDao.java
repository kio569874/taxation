package com.taxation.dao;

import java.util.List;

import com.taxation.bean.ProviderDto;
import com.taxation.bean.ProviderEntity;
import com.taxation.bean.ProviderInfoDto;

public interface ProviderDao {
	int deleteByPrimaryKey(String providerId);

	int insert(ProviderEntity record);

	int insertSelective(ProviderEntity record);

	ProviderEntity selectByPrimaryKey(String providerId);

	int updateByPrimaryKeySelective(ProviderEntity record);

	int updateByPrimaryKey(ProviderEntity record);

	List<ProviderEntity> queryProviderList(ProviderEntity record);

	int count(ProviderEntity t);

	List<ProviderEntity> getProviderList();

	List<ProviderInfoDto> getProviderByPagination(int page, int pageSize);

	List<ProviderDto> queryAllProviderByPage(int page, int pageSize);

	int queryAllProviderCount();

	int getProviderCount();
}