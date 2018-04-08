package com.taxation.dao;

import com.taxation.bean.TempFileEntity;

import java.util.List;

public interface TempFileDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TempFileEntity record);

    int insertSelective(TempFileEntity record);

    TempFileEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempFileEntity record);

    int updateByPrimaryKey(TempFileEntity record);

    List<TempFileEntity> getTempFilesByProviderId(String providerId);

    int deleteTempFilsByProviderId(String providerId);
}