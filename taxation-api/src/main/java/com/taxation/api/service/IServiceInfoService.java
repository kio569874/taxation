package com.taxation.api.service;

import java.util.List;

import com.taxation.api.common.PaginationDto;
import com.taxation.bean.*;

/**
 * 服务信息接口
 * 
 * @author yc
 *
 */
public interface IServiceInfoService extends IBaseService<ServiceEntity> {
	/**
	 * 根据服务信息ID查询服务信息
	 * 
	 * @param serviceId
	 * @return
	 */
	ServiceInfoDto getServiceById(String serviceId);

	/**
	 * 根据服务提供者ID查询他的服务信息
	 * 
	 * @param providerId
	 * @return
	 */
	List<ServiceInfoDto> queryServiceByProviderId(String providerId);


	/**
	 * 查询所有服务
	 * @param page
	 * @param pageSize
     * @return
     */
	PaginationDto<ServiceDto> getAllServiceByPagination(int page, int pageSize);

	/**
	 * 查询收获地址
	 * @param userId
	 * @return
     */
	List<UserAddress> getMyAddressByUserId(String userId);

	/**
	 * 增加新的收获地址
	 * @param record
	 * @return
     */
	boolean addNewAddress(UserAddress record);

	/**
	 * 获取地区json格式字符串
	 * @return
     */
	String getCityAreaJsonStr();

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
	boolean updateOldAdressInfo(UserAddress record);

	/**
	 * 保存订单信息
	 * @param orderParam
	 * @return
     */
	OrderEntity saveOrderInfo(OrderParam orderParam);

	/**
	 * 更新支付方式
	 * @param orderId
	 * @param payType
     * @return
     */
	boolean updatePayType(String orderId,String payType);

}
