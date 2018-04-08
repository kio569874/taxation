package com.taxation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.IOrderService;
import com.taxation.bean.OrderDto;
import com.taxation.bean.OrderEntity;
import com.taxation.dao.OrderDao;
import com.taxation.utils.PaginationUtil;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Resource
	private OrderDao orderDao;

	@Override
	public void insert(OrderEntity t) {
		orderDao.insert(t);
	}

	@Override
	public void update(OrderEntity t) {
		orderDao.updateByPrimaryKey(t);

	}

	@Override
	public void delete(String id) {
		orderDao.deleteByPrimaryKey(id);

	}

	@Override
	public List<OrderEntity> query(OrderEntity t) {
		return null;
	}

	@Override
	public int count(OrderEntity t) {
		return 0;
	}

	@Override
	public PaginationDto<OrderDto> queryOrderListByPage(int page, int pageSize,
			Integer totalCount, String userId) {
		PaginationDto<OrderDto> paginationDto = new PaginationDto<OrderDto>();
		// 分页参数校验
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(orderDao.getTotalCount(userId));
		}
		List<OrderDto> userEntityList = orderDao.queryListByPage(offset,
				pageSize, userId);
		paginationDto.setData(userEntityList);
		return paginationDto;
	}

	@Override
	public OrderEntity queryOrderEntityByOrderNo(String orderNo) {
		return orderDao.queryOrderEntityByOrderNo(orderNo);
	}
}
