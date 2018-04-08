package com.taxation.api.service;

import java.util.List;

import com.taxation.entity.Page;
import com.taxation.model.CityArea;


public interface ICityAreaService{
	
	int add(CityArea param) throws Exception;
	
	int delete(CityArea param) throws Exception;
	
	int update(CityArea param) throws Exception;
	
	CityArea getOne(CityArea param) throws Exception;
	
	List<CityArea> getList(CityArea param) throws Exception;
	
	Page<CityArea> page(CityArea param) throws Exception;
	
	int batchRemove(CityArea param,String ids) throws Exception;
	
}
