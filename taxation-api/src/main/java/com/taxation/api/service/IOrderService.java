package com.taxation.api.service;

import com.taxation.api.common.PaginationDto;
import com.taxation.bean.OrderDto;
import com.taxation.bean.OrderEntity;

public interface IOrderService extends IBaseService<OrderEntity> {

	PaginationDto<OrderDto> queryOrderListByPage(int page, int pageSize,
			Integer totalCount, String userId);

	OrderEntity queryOrderEntityByOrderNo(String orderNo);

}
