package com.taxation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.taxation.api.common.PaginationDto;
import com.taxation.utils.CreateIdUtil;
import com.taxation.utils.PaginationUtil;

import org.springframework.stereotype.Service;

import com.taxation.api.service.IUserService;
import com.taxation.bean.UserEntity;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;

	@Override
	public UserEntity getUserById(String userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public PaginationDto<UserEntity> queryUserListByPage(int page,
			int pageSize, Integer totalCount) {
		PaginationDto<UserEntity> paginationDto = new PaginationDto<UserEntity>();
		// 分页参数校验
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(userDao.getTotalCount());
		}
		List<UserEntity> userEntityList = userDao.queryListByPage(offset,
				pageSize);
		paginationDto.setData(userEntityList);
		return paginationDto;
	}

	@Override
	public boolean register(UserEntity t) {
		t.setUserId(CreateIdUtil.createId(CreateIdContants.USER_ID_TAG));
		int re = userDao.insert(t);
		return re == 1 ? true : false;
	}

	@Override
	public boolean login(UserEntity t) {
		return false;
	}

	@Override
	public void insert(UserEntity t) {
		userDao.insert(t);
	}

	@Override
	public void update(UserEntity t) {
		userDao.updateByPrimaryKey(t);
	}

	@Override
	public void delete(String id) {
		userDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserEntity> query(UserEntity t) {
		return null;
	}

	@Override
	public int count(UserEntity t) {
		return userDao.getTotalCount();
	}

	@Override
	public UserEntity selectByUserAccount(String userAccount) {
		return userDao.selectByUserAccount(userAccount);
	}

	@Override
	public UserEntity getUserByAccountAndPassword(String userAccount,
			String userPassword) {
		return userDao.getUserByAccountAndPassword(userAccount, userPassword);
	}

	@Override
	public boolean updatePassWordByUserAccount(String userAccount,
			String newPassword) {
		return userDao.updatePassWordByUserAccount(userAccount, newPassword) == 1 ? Boolean.TRUE
				: Boolean.FALSE;
	}

	@Override
	public boolean updateLoginInfoByUserAccount(UserEntity record) {
		return userDao.updateLoginInfoByUserAccount(record) == 1 ? Boolean.TRUE
				: Boolean.FALSE;
	}

	@Override
	public boolean bindUserEmailByUserAccount(String userAccount,
			String userEmail) {
		return userDao.bindUserEmailByUserAccount(userAccount, userEmail) == 1 ? Boolean.TRUE
				: Boolean.FALSE;
	}

	@Override
	public boolean updateUserEntity(UserEntity record) {
		return userDao.updateByPrimaryKey(record) == 1 ? Boolean.TRUE
				: Boolean.FALSE;
	}

}
