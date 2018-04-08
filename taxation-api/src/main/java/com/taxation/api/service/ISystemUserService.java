package com.taxation.api.service;


import com.taxation.api.common.PaginationDto;
import com.taxation.bean.SystemUserEntity;

public interface ISystemUserService extends IBaseService<SystemUserEntity>{

	/**
	 *
	 * @param page
	 * @param pageSize
     * @return
     */
	PaginationDto<SystemUserEntity> getSystemUserByPage(int page, int pageSize);

	boolean saveSystemUser(SystemUserEntity record);

	boolean updateSystemUser(SystemUserEntity record);

	boolean deleteSystemUsers(String [] systemUserIds);

	/** JustFresh 2017-07-27 begin */
	
	SystemUserEntity getOneById(String id) throws Exception;
	
	boolean changeStatus(String id,String userStatus) throws Exception;
	
	/** JustFresh 2017-07-27 end */
}
