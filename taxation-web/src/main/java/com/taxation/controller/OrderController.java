package com.taxation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.IOrderService;
import com.taxation.bean.OrderDto;
import com.taxation.web.util.AjaxUtils;

@Controller
@RequestMapping("order")
public class OrderController {

	private static Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/queryOrderListByUserId.htm", method = RequestMethod.POST)
	public String queryOrderListByUserId(Model model,
			@RequestBody String content, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userId = map.get("userId").toString();
			PaginationDto<OrderDto> paginationDto = orderService
					.queryOrderListByPage(1, 5, null, userId);
			List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
			orderDtoList = paginationDto.getData();
			if (orderDtoList != null && orderDtoList.size() > 0) {
				// 存在订单数据
				JSONArray js = JSONArray.fromObject(orderDtoList);
				AjaxUtils.ajaxJsonSuccessMessage(response, js.toString());
			} else {
				// 不存在订单数据
				AjaxUtils.ajaxJsonSuccessMessage(response,
						new JSONObject().toString());
			}
		} catch (Exception e) {
			logger.error(e);
			AjaxUtils.ajaxJsonErrorMessage(response, "查询用户订单信息异常");
		}
		return null;
	}
}
