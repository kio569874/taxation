package com.taxation.api.service;

import java.math.BigDecimal;

import com.taxation.bean.AliPayOrderJnlEntity;

public interface IAliPayService {

	/**
	 * 
	 * @param outTradeNo
	 *            本系统订单号
	 * @param totalAmount
	 *            订单金额
	 * @param subject
	 *            订单标题
	 * @return
	 */
	String tradePay(String outTradeNo, double totalAmount, String subject);

	/**
	 * @param orderPayId
	 *            支付宝订单表id
	 * @param outTradeNo
	 *            本系统订单号
	 * @param tradeNo
	 *            支付宝订单号
	 * @param refundAmount
	 *            退款金额
	 * @param outRequestNo
	 *            退款请求流水号，标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	 * @return
	 */
	String refund(String orderPayId, String outTradeNo, String tradeNo,
			BigDecimal refundAmount, String outRequestNo);
	
	/**
	 * 查询交易状态
	 * 
	 * @return
	 */
	String queryTradeStatus(AliPayOrderJnlEntity aliPayOrderJnlEntity);
	
	/**
	 * 查询交易状态并处理入库
	 * @param aliPayOrderJnlEntity
	 */
	void queryTradeStatusAndExecute(AliPayOrderJnlEntity aliPayOrderJnlEntity);
	
	/**
	 * 查询退款状态
	 * 
	 * @return
	 */
	String queryRefundStatus();

	/**
	 * 关闭交易
	 * 
	 * @return
	 */
	String closeTrade();

	/**
	 * 查询对账单下载地址
	 * 
	 * @return
	 */
	String queryDataServiceBillDownloadUrl();

}
