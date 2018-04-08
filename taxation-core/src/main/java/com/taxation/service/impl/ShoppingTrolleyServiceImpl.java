package com.taxation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.service.IShoppingTrolleyService;
import com.taxation.bean.ShoppingTrolleyDto;
import com.taxation.bean.ShoppingTrolleyEntity;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.ShoppingTrolleyDao;
import com.taxation.utils.CreateIdUtil;

@Service("shoppingTrolleyService")
public class ShoppingTrolleyServiceImpl implements IShoppingTrolleyService {

	@Resource
	private ShoppingTrolleyDao shoppingTrolleyDao;

	@Override
	public boolean addServiceToMyShoppingTrolley(ShoppingTrolleyEntity record) {
		record.setId(CreateIdUtil
				.createId(CreateIdContants.SHOPPINGTROLLEY_ID_TAG));
		int affectedRows = shoppingTrolleyDao.insert(record);
		return affectedRows == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean deleteMyShoppingTrolleys(String id) {
		int affectedRows = shoppingTrolleyDao.deleteByPrimaryKey(id);
		return affectedRows == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public List<ShoppingTrolleyDto> getMyShoppingTrolleys(String userId) {
		return shoppingTrolleyDao.getMyShoppingTrolleys(userId);
	}
}
