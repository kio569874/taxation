package com.taxation.api.service;

import com.taxation.api.common.PaginationDto;
import com.taxation.bean.UserEntity;

/**
 * 用户操作接口
 * 
 * @author yc
 *
 */
public interface IUserService extends IBaseUserService<UserEntity>,
		IBaseService<UserEntity> {

	UserEntity getUserById(String userId);

	PaginationDto<UserEntity> queryUserListByPage(int page, int pageSize,
			Integer totalCount);

	UserEntity selectByUserAccount(String userAccount);

	UserEntity getUserByAccountAndPassword(String userAccount,
			String userPassword);
	boolean updatePassWordByUserAccount(String userAccount,String newPassword);

	/**
	 * 用户登录成功后，更新登陆信息：上次登录时间，上次登录ip等字段
	 * @param record
	 * @return
     */
	boolean updateLoginInfoByUserAccount(UserEntity record);

	/**
	 * 绑定邮箱
	 * @param userAccount
	 * @param userEmail
     * @return
     */
	boolean bindUserEmailByUserAccount(String userAccount,String userEmail);
	
	boolean updateUserEntity(UserEntity record);
}
