package com.taxation.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.taxation.api.service.IAliPayService;
import com.taxation.bean.AliPayOrderJnlEntity;
import com.taxation.bean.AliPayRefundJnlEntity;
import com.taxation.bean.OrderEntity;
import com.taxation.bean.PayConfigEntity;
import com.taxation.constants.PayStatusEnum;
import com.taxation.constants.alipay.AliPayPayTypeEnum;
import com.taxation.constants.alipay.AlipayOrderStatusEnum;
import com.taxation.constants.alipay.AlipayPayKeyEnum;
import com.taxation.constants.alipay.AlipayRefundStatusEnum;
import com.taxation.contants.CreateIdContants;
import com.taxation.context.PayContext;
import com.taxation.dao.AliPayOrderJnlDao;
import com.taxation.dao.AliPayRefundJnlDao;
import com.taxation.dao.OrderDao;
import com.taxation.dao.PayConfigDao;
import com.taxation.exception.AlipayException;
import com.taxation.manager.AliPayPCServiceManager;
import com.taxation.utils.CreateIdUtil;

/**
 * 支付宝支付实现
 * 
 * @author yc
 *
 */
@Service("aliPayService")
public class AliPayServiceImpl implements IAliPayService {

	private static Logger logger = Logger.getLogger(AliPayServiceImpl.class);

	@Resource
	private PayConfigDao payConfigDao;

	@Resource
	private AliPayOrderJnlDao aliPayOrderJnlDao;

	@Resource
	private AliPayRefundJnlDao aliPayRefundJnlDao;

	@Resource
	private OrderDao orderDao;

	@Override
	public String tradePay(String outTradeNo, double totalAmount, String subject) {
		logger.info("tradePay");
		// 1、记流水
		AliPayOrderJnlEntity aliPayOrderJnlEntity = new AliPayOrderJnlEntity();
		aliPayOrderJnlEntity.setOrderPayId(CreateIdUtil
				.createId(CreateIdContants.ALIPAY_ORDER_PAY_ID_TAG));
		aliPayOrderJnlEntity.setOrderNo(outTradeNo);
		aliPayOrderJnlEntity.setTotalAmount(new BigDecimal(totalAmount + ""));
		Date nowDate = new Date();
		aliPayOrderJnlEntity.setCreateTime(nowDate);
		aliPayOrderJnlEntity.setOrderStatus(AlipayOrderStatusEnum.INIT
				.getStatus());
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		ca.add(Calendar.DAY_OF_MONTH, 1);
		aliPayOrderJnlEntity.setTimeoutExpress(ca.getTime());

		aliPayOrderJnlDao.insert(aliPayOrderJnlEntity);

		String result = null;
		try {
			result = AliPayPCServiceManager.getInstance().tradePay(
					getPayConfig(), outTradeNo, totalAmount, subject);
			// 3:修改状态为支付中
			aliPayOrderJnlEntity.setOrderStatus(AlipayOrderStatusEnum.PAYMENT
					.getStatus());
			aliPayOrderJnlDao.updateByPrimaryKey(aliPayOrderJnlEntity);
		} catch (AlipayException e) {
			// TODO 支付异常
		} catch (Exception e) {
			// TODO 异常
		}
		// 支付宝返回的form表单响应到前台
		return result;
	}

	@Override
	public String refund(String orderPayId, String outTradeNo, String tradeNo,
			BigDecimal refundAmount, String outRequestNo) {
		logger.info("refund");
		// 1、记流水
		String refundId = CreateIdUtil
				.createId(CreateIdContants.ALIPAY_ORDER_REFUND_ID_TAG);
		AliPayRefundJnlEntity aliPayRefundJnlEntity = new AliPayRefundJnlEntity();
		aliPayRefundJnlEntity.setRefundId(refundId);
		aliPayRefundJnlEntity.setOrderPayId(orderPayId);
		if (outRequestNo == null || "".equals(outRequestNo)) {
			outRequestNo = refundId;
		}
		aliPayRefundJnlEntity.setOutRequestNo(outRequestNo);
		aliPayRefundJnlEntity.setRefundStatus(AlipayRefundStatusEnum.INIT
				.getStatus());
		// aliPayRefundJnlEntity.setOrderPayId(orderPayId);
		String result = null;
		try {
			result = AliPayPCServiceManager.getInstance().refund(
					getPayConfig(), outTradeNo, tradeNo, refundAmount,
					outRequestNo);
			// 3:修改状态

		} catch (AlipayException e) {
			// TODO 支付异常
		} catch (Exception e) {
			// TODO 异常
		}
		return result;
	}

	@Override
	public String queryTradeStatus(AliPayOrderJnlEntity aliPayOrderJnlEntity) {
		// TODO 自动生成的方法存根
		logger.info("查询交易状态start");
		String result = null;
		AlipayTradeQueryResponse response = null;
		try {
			response = AliPayPCServiceManager.getInstance().queryTradeStatus(
					getPayConfig(), aliPayOrderJnlEntity.getOrderNo(),
					aliPayOrderJnlEntity.getTradeNo());
			result = response.getBody();
		} catch (AlipayException e) {
			// TODO 支付异常
		} catch (Exception e) {
			// TODO 异常
		}
		return result;
	}

	@Override
	public String queryRefundStatus() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String closeTrade() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String queryDataServiceBillDownloadUrl() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void queryTradeStatusAndExecute(
			AliPayOrderJnlEntity aliPayOrderJnlEntity) {
		logger.info("查询交易状态start");
		// 查询订单表信息
		OrderEntity orderEntity = orderDao
				.queryOrderEntityByOrderNo(aliPayOrderJnlEntity.getOrderNo());
		if (orderEntity == null) {
			return;
		}
		AlipayTradeQueryResponse response = null;
		try {
			response = AliPayPCServiceManager.getInstance().queryTradeStatus(
					getPayConfig(), aliPayOrderJnlEntity.getOrderNo(),
					aliPayOrderJnlEntity.getTradeNo());
			if (response.isSuccess()) {
				String tradeStatus = response.getTradeStatus();
				aliPayOrderJnlEntity.setTradeStatus(tradeStatus);
				if ("TRADE_SUCCESS".equals(tradeStatus)) {
					// 如果是支付成功
					// 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
					aliPayOrderJnlEntity
							.setOrderStatus(AlipayOrderStatusEnum.PAYSUCCESS
									.getStatus());
					aliPayOrderJnlEntity.setTradeNo(response.getTradeNo());
					aliPayOrderJnlEntity.setReceiptAmount(new BigDecimal(
							response.getReceiptAmount()));
					aliPayOrderJnlEntity.setPointAmount(new BigDecimal(response
							.getPointAmount()));
					aliPayOrderJnlEntity.setInvoiceAmount(new BigDecimal(
							response.getInvoiceAmount()));
					aliPayOrderJnlEntity.setSendPayDate(response
							.getSendPayDate());
					aliPayOrderJnlEntity.setPayTime(response.getSendPayDate());
					aliPayOrderJnlEntity.setBuyerPayAmount(new BigDecimal(
							response.getBuyerPayAmount()));
					aliPayOrderJnlEntity.setBuyerLogonId(response
							.getBuyerLogonId());
					aliPayOrderJnlEntity.setNotifyInfo(response.getBody());

					orderEntity.setPayStatus(PayStatusEnum.PAYSUCCESS
							.getStatus());
				} else if ("TRADE_CLOSED".equals(tradeStatus)) {
					aliPayOrderJnlEntity
							.setOrderStatus(AlipayOrderStatusEnum.PAYTIMEOUT
									.getStatus());
					orderEntity.setPayStatus(PayStatusEnum.PAYTIMEOUT
							.getStatus());
				}
				// 修改订单表信息
				orderDao.updateByPrimaryKey(orderEntity);
				// 修改支付宝订单流水表信息
				aliPayOrderJnlDao.updateByPrimaryKey(aliPayOrderJnlEntity);
			} else {
				logger.info("查询失败");
			}
		} catch (AlipayException e) {
			// TODO 支付异常
		} catch (Exception e) {
			// TODO 异常
		}

	}

	private PayConfigEntity getPayConfig() {
		if (!PayContext.getInstance().isPayConfigEntityList()) {
			PayContext.getInstance().setPayConfigEntityList(
					payConfigDao.queryPayConfigList());
		}
		PayConfigEntity payConfigEntity = PayContext.getInstance()
				.getPayConfigEntity(AlipayPayKeyEnum.ALIPAY.getCode(),
						AliPayPayTypeEnum.PCWEB.getCode());
		return payConfigEntity;
	}

}
