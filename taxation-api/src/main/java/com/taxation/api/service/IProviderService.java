package com.taxation.api.service;

import com.taxation.api.common.PaginationDto;
import com.taxation.bean.ProviderDto;
import com.taxation.bean.ProviderEntity;
import com.taxation.bean.ProviderInfoDto;

import java.util.List;

/**
 * 服务提供者操作接口
 * @author yc
 *
 */
public interface IProviderService extends IBaseUserService<ProviderEntity>,IBaseService<ProviderEntity> {
	
	ProviderEntity getProviderById(String providerId);

	List<ProviderEntity> getProviderList();

	PaginationDto<ProviderInfoDto> getProviderByPagination(int page, int pageSize);

	PaginationDto<ProviderDto> queryAllProviderByPage(int page, int pageSize);
}
