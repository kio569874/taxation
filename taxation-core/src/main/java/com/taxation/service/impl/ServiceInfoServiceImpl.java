package com.taxation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.exceptions.TaxationException;
import com.taxation.bean.*;
import com.taxation.constants.OrderStatusEnum;
import com.taxation.constants.PayStateEnum;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.taxation.api.service.IServiceInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.taxation.utils.CreateIdUtil;

@Service("serviceInfoService")
public class ServiceInfoServiceImpl implements IServiceInfoService{
	private static Logger logger = Logger.getLogger(ServiceInfoServiceImpl.class);
	@Resource
	private ServiceDao serviceDao;
	@Resource
	private UserAddressDao userAddressDao;
	/*@Resource
	private CityAreaDao cityAreaDao;*/
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderServiceDao orderServiceDao;

	private static final String ROOT_AREA_ID = "0";
	
	@Override
	public void insert(ServiceEntity t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void update(ServiceEntity t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void delete(String id) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public List<ServiceEntity> query(ServiceEntity t) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int count(ServiceEntity t) {
		// TODO 自动生成的方法存根
		return 0;
	}
	

	@Override
	public ServiceInfoDto getServiceById(String serviceId) {
		return serviceDao.getServiceInfoDetail(serviceId);
	}

	@Override
	public List<ServiceInfoDto> queryServiceByProviderId(String providerId) {
		// TODO 自动生成的方法存根
		return serviceDao.queryServiceByProviderId(providerId);
	}

	@Override
	public PaginationDto<ServiceDto> getAllServiceByPagination(int page, int pageSize) {
		PaginationDto<ServiceDto> pagination = new PaginationDto<ServiceDto>(page, pageSize);
		int totalCount = this.serviceDao.getAllServiceCount();
		pagination.setTotalCount(totalCount);
		int offset = (page - 1) * pageSize;
		List<ServiceDto> list = this.serviceDao.queryAllServiceByPage(offset, pageSize);
		pagination.setData(list);
		return pagination;
	}

	@Override
	public List<UserAddress> getMyAddressByUserId(String userId) {
		return userAddressDao.getMyAddressByUserId(userId);
	}

	@Override
	public boolean addNewAddress(UserAddress record) {
		record.setAddressId(CreateIdUtil.createId(CreateIdContants.USERADDRESS_ID_TAG));
		int affectedRows = userAddressDao.insert(record);
		if (record.getAddressDefault()) {
			userAddressDao.updateDefaultAddress(record.getUserId(),
					record.getAddressId());
		}
		return affectedRows == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public String getCityAreaJsonStr() {
		/*List<CityAreaEntity> cityAreaEntityList = cityAreaDao.getCityAreaList();*/
		List<CityAreaEntity> cityAreaEntityList = null;
		StringBuffer stringBuilder = new StringBuffer();
		if (cityAreaEntityList.size() > 0) {
			// 判断是否有子节点，虚拟跟节点是0
			stringBuilder.append("[");
			CityAreaEntity rootCityArea = getRootCityArea(cityAreaEntityList);
			findChilds(cityAreaEntityList, stringBuilder, rootCityArea);
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("]");
		}
		return stringBuilder.toString();
	}

	@Override
	public UserAddress queryAddressById(String addressId) {
		return userAddressDao.queryAddressById(addressId);
	}

	@Override
	public boolean updateOldAdressInfo(UserAddress record) {
		int affectedRows =  userAddressDao.updateByPrimaryKey(record);
		if (record.getAddressDefault()){
			userAddressDao.updateDefaultAddress(record.getUserId(),record.getAddressId());
		}
		return affectedRows ==1?Boolean.TRUE:Boolean.FALSE;
	}

	@Transactional
	@Override
	public OrderEntity saveOrderInfo(OrderParam orderParam) {
		checkOrderParam(orderParam);
		OrderEntity orderEntity = new OrderEntity();
		OrderServiceEntity orderServiceEntity = new OrderServiceEntity();
		buildOrderInfo(orderParam,orderEntity,orderServiceEntity);
		orderDao.insert(orderEntity);
		orderServiceDao.insert(orderServiceEntity);
		return orderEntity;
	}

	@Override
	public boolean updatePayType(String orderId, String payType) {
		if (StringUtils.isEmpty(orderId)){
			throw new TaxationException("参数：订单编号为空");
		}
		if (StringUtils.isEmpty(payType)){
			throw new TaxationException("参数：支付方式为空");
		}
		int affectedRows = orderDao.updatePayType(orderId,payType);
		return affectedRows==1?Boolean.TRUE:Boolean.FALSE;
	}

	private void buildOrderInfo(OrderParam orderParam,OrderEntity orderEntity,OrderServiceEntity orderServiceEntity){
		orderEntity.setOrderId(CreateIdUtil.createId(CreateIdContants.ORDER_ID_TAG));
		orderEntity.setOrderNo(orderEntity.getOrderId());
		orderEntity.setUserId(orderParam.getUserId());
		orderEntity.setProviderId(orderParam.getProviderId());
		orderEntity.setAddressId(orderParam.getAddressId());
		orderEntity.setOrderAmount(new BigDecimal(orderParam.getPrice()));
		orderEntity.setOrderStatus(OrderStatusEnum.INIT.getCode());
		orderEntity.setPayType(orderParam.getPayType());
		orderEntity.setPayStatus(PayStateEnum.WatingPayment.getCode());
		orderEntity.setCreateTime(new Date());
		orderEntity.setUpdateTime(new Date());
		orderEntity.setOrderRemark(orderParam.getOrderRemark());
		orderEntity.setOrderSubject(orderParam.getServiceName());

		orderServiceEntity.setServiceId(orderParam.getServiceId());
		orderServiceEntity.setPrice(new BigDecimal(orderParam.getPrice()));
		orderServiceEntity.setContext(orderParam.getOrderRemark());
		orderServiceEntity.setOrderId(orderEntity.getOrderId());
	}
	private static void checkOrderParam(OrderParam orderParam){
		if (orderParam == null){
			logger.error("订单参数为空");
			throw new TaxationException("订单参数为空");
		}
		if (StringUtils.isEmpty(orderParam.getAddressId())){
			logger.error("订单参数：收货地址ID为空");
			throw new TaxationException("订单参数：收货地址ID为空");
		}
		if (StringUtils.isEmpty(orderParam.getProviderId())){
			logger.error("订单参数：服务商ID为空");
			throw new TaxationException("订单参数：服务商ID为空");
		}if (StringUtils.isEmpty(orderParam.getUserId())){
			logger.error("订单参数：用户ID为空");
			throw new TaxationException("订单参数：用户ID为空");
		}
		if (StringUtils.isEmpty(orderParam.getServiceId())){
			logger.error("订单参数：服务ID为空");
			throw new TaxationException("订单参数：服务ID为空");
		}
		if (StringUtils.isEmpty(orderParam.getPayType())){
			logger.error("订单参数：支付类型为空");
			throw new TaxationException("订单参数：支付类型为空");
		}
	}
	private static void findChilds(List<CityAreaEntity> pers,StringBuffer sb,CityAreaEntity person){

		List<CityAreaEntity> temps = findChildCityArea(pers,person);
		if(temps.size()>0){
			sb.append("{\"name\":\""+person.getPname()+"\"");
			sb.append(",\"child\":[");
			for(CityAreaEntity ds : temps){
				findChilds(pers,sb,ds);
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			sb.append("},");
		}else {
			sb.append("{\"value\":\""+person.getPname()+"\"},");
		}

	}
	private static List<CityAreaEntity> findChildCityArea(List<CityAreaEntity> list,CityAreaEntity bean){
		List<CityAreaEntity> newList = new ArrayList<CityAreaEntity>();
		for (CityAreaEntity item : list){
			if (bean.getPid().equals(item.getParentid())){
				newList.add(item);
			}
		}
		return newList;
	}
	private static CityAreaEntity getRootCityArea(List<CityAreaEntity> list){
		CityAreaEntity bean = null;
		for (CityAreaEntity item:list){
			if (ROOT_AREA_ID.equals(item.getParentid())){
				bean = item;
				break;
			}
		}
		return bean;
	}
}
