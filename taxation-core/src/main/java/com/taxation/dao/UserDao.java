package com.taxation.dao;

import com.taxation.bean.UserEntity;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String userId);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    int getTotalCount();

    List<UserEntity> queryListByPage(int offset, int pageSize);
    
    UserEntity selectByUserAccount(String userAccount);

    UserEntity getUserByAccountAndPassword(String userAccount,String userPassword);

    int updatePassWordByUserAccount(String userAccount,String newPassword);

    int updateLoginInfoByUserAccount(UserEntity record);

    int bindUserEmailByUserAccount(String userAccount, String userEmail);
}