package com.taxation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.taxation.api.common.PaginationDto;
import com.taxation.bean.ProviderDto;
import com.taxation.bean.ProviderInfoDto;
import org.springframework.stereotype.Service;

import com.taxation.api.service.IProviderService;
import com.taxation.bean.ProviderEntity;
import com.taxation.dao.ProviderDao;

@Service("providerService")
public class ProviderServiceImpl implements IProviderService {

	@Resource
	private ProviderDao providerDao;

	@Override
	public ProviderEntity getProviderById(String providerId) {
		return this.providerDao.selectByPrimaryKey(providerId);
	}

	@Override
	public List<ProviderEntity> getProviderList() {
		return this.providerDao.getProviderList();
	}

	@Override
	public PaginationDto<ProviderInfoDto> getProviderByPagination(int page, int pageSize) {
		PaginationDto<ProviderInfoDto> pagination = new PaginationDto<ProviderInfoDto>(page, pageSize);
		int totalCount = this.providerDao.getProviderCount();
		pagination.setTotalCount(totalCount);
		int offset = (page - 1) * pageSize;
		List<ProviderInfoDto> list = this.providerDao.getProviderByPagination(offset, pageSize);
		pagination.setData(list);
		return pagination;
	}

	@Override
	public PaginationDto<ProviderDto> queryAllProviderByPage(int page, int pageSize) {
		PaginationDto<ProviderDto> pagination = new PaginationDto<ProviderDto>(page, pageSize);
		int totalCount = this.providerDao.queryAllProviderCount();
		pagination.setTotalCount(totalCount);
		int offset = (page - 1) * pageSize;
		List<ProviderDto> list = this.providerDao.queryAllProviderByPage(offset, pageSize);
		pagination.setData(list);
		return pagination;
	}

	@Override
	public boolean register(ProviderEntity t) {
		int re = this.providerDao.insert(t);
		return re == 1 ? true : false;
	}

	@Override
	public boolean login(ProviderEntity t) {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public void insert(ProviderEntity t) {
		providerDao.insert(t);

	}

	@Override
	public void update(ProviderEntity t) {
		providerDao.updateByPrimaryKey(t);

	}

	@Override
	public void delete(String id) {
		providerDao.deleteByPrimaryKey(id);

	}

	@Override
	public List<ProviderEntity> query(ProviderEntity t) {
		return providerDao.queryProviderList(t);
	}

	@Override
	public int count(ProviderEntity t) {
		return providerDao.count(t);
	}

}
