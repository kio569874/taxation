package com.taxation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.taxation.bean.*;
import com.taxation.constants.OrderStatusEnum;
import com.taxation.constants.PayStateEnum;
import com.taxation.constants.PayWayEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxation.api.exceptions.TaxationException;
import com.taxation.api.service.IEnterpriseEnrollService;
import com.taxation.contants.CreateIdContants;
import com.taxation.dao.EnterpriseEnrollDao;
import com.taxation.dao.OrderDao;
import com.taxation.dao.OrderServiceDao;
import com.taxation.dao.TempFileDao;
import com.taxation.utils.CreateIdUtil;

@Service("enterpriseEnrollService")
public class EnterpriseEnrollServiceImpl implements IEnterpriseEnrollService {

	@Resource
	private EnterpriseEnrollDao companyServiceDao;

	@Resource
	private TempFileDao tempFileDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderServiceDao orderServiceDao;

	@Transactional
	@Override
	public boolean saveEnterpriseEnrollInfo(EnterpriseEnrollDto record) {
		try {
			record.setId(CreateIdUtil
					.createId(CreateIdContants.ENTERPRISEENROLL_ID_TAG));
			EnterpriseEnrollEntity enterpriseEnrollEntity = new EnterpriseEnrollEntity();
			BeanUtils.copyProperties(record,enterpriseEnrollEntity);
			companyServiceDao.insert(enterpriseEnrollEntity);
			OrderEntity orderEntity = new OrderEntity();
			OrderServiceEntity orderServiceEntity = new OrderServiceEntity();
			orderEntity.setOrderId(CreateIdUtil.createId(CreateIdContants.ORDER_ID_TAG));
			orderEntity.setOrderNo(orderEntity.getOrderId());
			orderEntity.setUserId(record.getUserId());
			orderEntity.setProviderId(record.getProviderId());
			orderEntity.setAddressId(record.getAddressId());
			orderEntity.setOrderAmount(new BigDecimal(record.getPrice()));
			orderEntity.setOrderStatus(OrderStatusEnum.INIT.getCode());
			orderEntity.setPayType(record.getPayType());
			orderEntity.setPayStatus(PayStateEnum.WatingPayment.getCode());
			orderEntity.setCreateTime(new Date());
			orderEntity.setUpdateTime(new Date());
			orderEntity.setOrderRemark("");
			orderEntity.setOrderSubject("购买公司注册服务");

			orderServiceEntity.setServiceId(record.getServiceId());
			orderServiceEntity.setPrice(new BigDecimal(record.getPrice()));
			orderServiceEntity.setContext("");
			orderServiceEntity.setOrderId(orderEntity.getOrderId());
			orderDao.insert(orderEntity);
			orderServiceDao.insert(orderServiceEntity);
		} catch (Exception e) {
			throw new TaxationException("公司注册发生异常：异常信息为：" + e.getMessage());
		}
		return true;
	}

	@Override
	public boolean insertTempFile(TempFileEntity record) {
		int re = tempFileDao.insert(record);
		return re == 1 ? true : false;
	}

	@Override
	public List<TempFileEntity> getTempFilesByProviderId(String providerId) {
		return tempFileDao.getTempFilesByProviderId(providerId);
	}

	@Override
	public boolean deleteTempFilsByProviderId(String providerId) {
		int re = tempFileDao.deleteTempFilsByProviderId(providerId);
		return re > 0 ? true : false;
	}
}
