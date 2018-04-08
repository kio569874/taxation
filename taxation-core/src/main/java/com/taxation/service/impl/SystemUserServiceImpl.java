package com.taxation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.ISystemUserService;
import com.taxation.bean.SystemUserEntity;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.SystemUserDao;
import com.taxation.utils.CreateIdUtil;

@Service("systemUserService")
public class SystemUserServiceImpl implements ISystemUserService {

	@Resource
	private SystemUserDao systemUserDao;

	@Override
	public PaginationDto<SystemUserEntity> getSystemUserByPage(int page, int pageSize) {
		PaginationDto<SystemUserEntity> pagination = new PaginationDto<SystemUserEntity>(page, pageSize);
		int totalCount = this.systemUserDao.getSystemUserCount();
		pagination.setTotalCount(totalCount);
		int offset = (page - 1) * pageSize;
		List<SystemUserEntity> list = this.systemUserDao.getSystemUserByPage(offset, pageSize);
		pagination.setData(list);
		return pagination;
	}

	@Override
	public boolean saveSystemUser(SystemUserEntity record) {
		record.setId(CreateIdUtil.createId(CreateIdContants.SYSTEM_USER_ID_TAG));
		int affectedRows =  systemUserDao.insert(record);
		return affectedRows==1?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public boolean updateSystemUser(SystemUserEntity record) {
		System.out.println(record.getId());
		int affectedRows =  systemUserDao.updateByPrimaryKeySelective(record);
		return affectedRows==1?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public boolean deleteSystemUsers(String[] systemUserIds) {
		int affectedRows =  systemUserDao.deleteSystemUsers(systemUserIds);
		return affectedRows>0?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public void insert(SystemUserEntity t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void update(SystemUserEntity t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void delete(String id) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public List<SystemUserEntity> query(SystemUserEntity t) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int count(SystemUserEntity t) {
		// TODO 自动生成的方法存根
		return 0;
	}

	/** JustFresh 2017-07-27 begin */
	@Override
	public SystemUserEntity getOneById(String id) throws Exception {
		if(id == null || "".equals(id)){
			throw new Exception("请选择需要查询的数据");
		}
		return this.systemUserDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean changeStatus(String id,String userStatus) throws Exception {
		if(id == null || "".equals(id)){
			throw new Exception("请选择需要修改状态的数据！！！");
		}
		System.out.println("Service层方法" + userStatus);
		if(userStatus != null && !"".equals(userStatus)){
			return this.systemUserDao.changeStatus(id,userStatus);
		}
		return false;
	}
	/** JustFresh 2017-07-27 end */
	
}
