package com.taxation.api.service;

import com.taxation.bean.ShoppingTrolleyDto;
import com.taxation.bean.ShoppingTrolleyEntity;

import java.util.List;

/**
 *
 */
public interface IShoppingTrolleyService{
	/**
	 * 加入购物车
	 * @param record
	 * @return
     */
	boolean addServiceToMyShoppingTrolley(ShoppingTrolleyEntity record);

	/**
	 * 删除购物车中的服务
	 * @param id
     * @return
     */
	boolean deleteMyShoppingTrolleys(String id);

	/**
	 * 查询购物车
	 * @param userId
	 * @return
     */
	List<ShoppingTrolleyDto> getMyShoppingTrolleys(String userId);

}
