package com.taxation.dao;

import com.taxation.bean.UserAddress;

import java.util.List;

public interface UserAddressDao {
    int deleteByPrimaryKey(String addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> getMyAddressByUserId(String userId);

    int updateDefaultAddress(String userId,String addressId);
    /**
     * 根据addressId查询收货地址地址
     * @param addressId
     * @return
     */
    UserAddress queryAddressById(String addressId);

    /**
     * 更新收货地址
     * @param record
     * @return
     */
    int updateOldAdressInfo(UserAddress record);
}