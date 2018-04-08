package com.taxation.dao;

import com.taxation.bean.CityAreaEntity;

import java.util.List;

public interface CityAreaDao {
    int insert(CityAreaEntity record);

    int insertSelective(CityAreaEntity record);

    List<CityAreaEntity> getCityAreaList();

}