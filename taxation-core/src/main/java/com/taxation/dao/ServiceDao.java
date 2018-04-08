package com.taxation.dao;

import java.util.List;

import com.taxation.bean.ServiceDto;
import com.taxation.bean.ServiceEntity;
import com.taxation.bean.ServiceInfoDto;

public interface ServiceDao {
    int deleteByPrimaryKey(String serviceId);

    int insert(ServiceEntity record);

    int insertSelective(ServiceEntity record);

    ServiceEntity selectByPrimaryKey(String serviceId);

    ServiceInfoDto getServiceInfoDetail(String serviceId);

    int updateByPrimaryKeySelective(ServiceEntity record);

    int updateByPrimaryKey(ServiceEntity record);
    
    List<ServiceDto> queryAllServiceByPage(int page, int pageSize);

    int getAllServiceCount();

    List<ServiceInfoDto> queryServiceByProviderId(String providerId);
}