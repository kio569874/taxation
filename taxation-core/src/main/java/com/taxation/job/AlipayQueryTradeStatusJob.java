package com.taxation.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.taxation.api.service.IAliPayService;
import com.taxation.api.service.IAlipayOrderJnlService;
import com.taxation.bean.AliPayOrderJnlEntity;
import com.taxation.constants.alipay.AlipayOrderStatusEnum;

@Component("alipayQueryTradeStatusJob")
public class AlipayQueryTradeStatusJob {

	@Resource
	private IAliPayService aliPayService;
	@Resource
	private IAlipayOrderJnlService alipayOrderJnlService;

	private static Logger logger = Logger
			.getLogger(AlipayQueryTradeStatusJob.class);

	/**
	 * 查询支付宝状态定时器
	 */
	public void queryTradeStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询表中支付中的数据
		map.put("orderStatus", AlipayOrderStatusEnum.PAYMENT.getStatus());
		List<AliPayOrderJnlEntity> aliPayOrderJnlList = alipayOrderJnlService
				.queryAliPayOrderJnlList(map);
		if (aliPayOrderJnlList != null && aliPayOrderJnlList.size() > 0) {
			for (AliPayOrderJnlEntity aliPayOrderJnlEntity : aliPayOrderJnlList) {
				if (checkTimeOut(aliPayOrderJnlEntity)) {
					// 已过最晚付款时间，直接修改状态为超时
					aliPayOrderJnlEntity
							.setOrderStatus(AlipayOrderStatusEnum.PAYTIMEOUT
									.getStatus());
					alipayOrderJnlService.update(aliPayOrderJnlEntity);
				} else {
					// 执行未超过最晚付款时间
					aliPayService
							.queryTradeStatusAndExecute(aliPayOrderJnlEntity);
				}
			}
		}
	}

	/**
	 * 检查是否过了最晚付款时间，超过了返回true,没超过返回false
	 * 
	 * @param aliPayOrderJnlEntity
	 * @return
	 */
	private boolean checkTimeOut(AliPayOrderJnlEntity aliPayOrderJnlEntity) {
		try {
			// 1、检查是否过超时时间
			Date timeOutDate = aliPayOrderJnlEntity.getTimeoutExpress();
			Date nowDate = new Date();
			if (nowDate.after(timeOutDate)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;

		}
	}

}
