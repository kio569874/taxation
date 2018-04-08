package com.taxation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taxation.bean.SystemUserEntity;

public interface SystemUserDao {
    int deleteByPrimaryKey(String id);

    int insert(SystemUserEntity record);

    int insertSelective(SystemUserEntity record);

    SystemUserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemUserEntity record);

    int updateByPrimaryKey(SystemUserEntity record);

    int getSystemUserCount();

    List<SystemUserEntity> getSystemUserByPage(int page, int pageSize);

    int deleteSystemUsers(String[] systemUserIds);
    
    /** JustFresh 2017-07-27 begin */
    boolean changeStatus(@Param("id")String id,@Param("user_status")String user_status);
    /** JustFresh 2017-07-27 end */
}