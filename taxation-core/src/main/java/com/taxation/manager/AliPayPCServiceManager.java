package com.taxation.manager;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.taxation.bean.PayConfigEntity;
import com.taxation.exception.AlipayException;

public class AliPayPCServiceManager {

	private static Logger logger = Logger
			.getLogger(AliPayPCServiceManager.class);

	private static AliPayPCServiceManager instance = new AliPayPCServiceManager();

	private static String FORMAT = "json";
	private static String CHARSET = "UTF-8";
	private static String product_code = "FAST_INSTANT_TRADE_PAY";
	private AlipayClient alipayClient = null;

	private AliPayPCServiceManager() {

	}

	public static AliPayPCServiceManager getInstance() {
		return instance;
	}

	private void initAlipayClient(PayConfigEntity payConfigEntity) {
		if (alipayClient == null) {
			alipayClient = new DefaultAlipayClient(
					payConfigEntity.getSendUrl(), payConfigEntity.getAppId(),
					payConfigEntity.getAppPrivateKey(), FORMAT, CHARSET,
					payConfigEntity.getExosysPublicKey(),
					payConfigEntity.getSignType()); // 获得初始化的AlipayClient
		}
	}

	/**
	 * 
	 * @param payConfigEntity
	 * @param outTradeNo
	 *            本系统订单号
	 * @param totalAmount
	 *            订单金额
	 * @param subject
	 *            订单标题
	 * @return
	 * @throws Exception
	 */
	public String tradePay(PayConfigEntity payConfigEntity, String outTradeNo,
			double totalAmount, String subject) throws Exception {
		logger.info("支付宝PC支付start...");
		String form = "";
		try {
			initAlipayClient(payConfigEntity);
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
			alipayRequest.setReturnUrl(payConfigEntity.getReturnUrl());
			alipayRequest.setNotifyUrl(payConfigEntity.getNotifyUrl());// 在公共参数中设置回跳和通知地址
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("out_trade_no", outTradeNo);
			jsonObject.put("product_code", product_code);
			jsonObject.put("total_amount", totalAmount);
			jsonObject.put("subject", subject);
			jsonObject.put("timeout_express", "24h");// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
														// 该参数数值不接受小数点， 如
														// 1.5h，可转换为 90m。
			alipayRequest.setBizContent(jsonObject.toString());// 填充业务参数
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
			// form = form.replace("display:none", "");
			// form =
			// form.replace("<script>document.forms[0].submit();</script>",
			// "");
		} catch (Exception e) {
			logger.error("支付宝PC支付异常:" + e.getMessage());
			logger.error(e);
			// TODO 编码应该定制一套
			throw new AlipayException("001009", e.getMessage());

		}
		logger.info("支付宝PC支付end...");
		return form;
	}

	/**
	 * 
	 * @param outTradeNo
	 *            本系统订单号
	 * @param tradeNo
	 *            支付宝订单号
	 * @param refundAmount
	 *            退款金额
	 * @param outRequestNo
	 *            退款请求流水号，标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	 * @return
	 * @throws Exception
	 */
	public String refund(PayConfigEntity payConfigEntity, String outTradeNo,
			String tradeNo, BigDecimal refundAmount, String outRequestNo)
			throws Exception {
		logger.info("支付宝PC退款start...");
		AlipayTradeRefundResponse response = null;
		try {
			initAlipayClient(payConfigEntity);
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("out_trade_no", outTradeNo);// 商户订单号
			jsonObject.put("trade_no", tradeNo);// 支付宝订单号
			jsonObject.put("refund_amount", refundAmount.doubleValue());
			jsonObject.put("refund_reason", "正常退款");
			jsonObject.put("out_request_no", outRequestNo);// 退款流水号

			request.setBizContent(jsonObject.toString());
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			logger.error("支付宝PC退款异常:" + e);
			logger.error(e);
			throw new AlipayException("001010", e.getMessage());
		}
		// {
		//     "sign":"ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE",
		//     "alipay_trade_refund_response":{
		//         "buyer_user_id":"2088101117955611",
		//         "gmt_refund_pay":"2014-11-27 15:45:57",
		//         "refund_fee":88.88,
		//         "trade_no":"支付宝交易号",
		//         "open_id":"2088102122524333",
		//         "refund_detail_item_list":[
		//             {
		//                 "amount":10,
		//                 "fund_type":"DEBIT_CARD",
		//                 "fund_channel":"ALIPAYACCOUNT",
		//                 "real_amount":11.21
		//             }
		//         ],
		//         "fund_change":"Y",
		//         "buyer_logon_id":"159****5620",
		//         "code":"10000",
		//         "out_trade_no":"6823789339978248",
		//         "store_name":"望湘园联洋店",
		//         "msg":"Success"
		//     }
		// }
		// {
		// "alipay_trade_refund_response": {
		// "code": "20000",
		// "msg": "Service Currently Unavailable",
		// "sub_code": "isp.unknow-error",
		// "sub_msg": "系统繁忙"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return null;
	}

	public AlipayTradeQueryResponse queryTradeStatus(
			PayConfigEntity payConfigEntity, String outTradeNo, String tradeNo)
			throws Exception {

		initAlipayClient(payConfigEntity);
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("out_trade_no", outTradeNo);
		jsonObject.put("trade_no", tradeNo);
		request.setBizContent(jsonObject.toString());
		AlipayTradeQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			logger.error(e);
			throw new AlipayException("001010", e.getMessage());
		}
		return response;
	}

	public String queryRefundStatus(PayConfigEntity payConfigEntity,
			String tradeNo, String outTradeNo, String outRequestNo) {

		initAlipayClient(payConfigEntity);

		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("out_trade_no", outTradeNo);
		jsonObject.put("trade_no", tradeNo);
		jsonObject.put("out_request_no", outRequestNo);
		request.setBizContent(jsonObject.toString());
		AlipayTradeFastpayRefundQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// {
		// "alipay_trade_fastpay_refund_query_response": {
		// "code": "10000",
		// "msg": "Success",
		// "trade_no": "2014112611001004680073956707",
		// "out_trade_no": "20150320010101001",
		// "out_request_no": "20150320010101001",
		// "refund_reason": "用户退款请求",
		// "total_amount": 100.2,
		// "refund_amount": 12.33
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }
		// {
		// "alipay_trade_fastpay_refund_query_response": {
		// "code": "20000",
		// "msg": "Service Currently Unavailable",
		// "sub_code": "isp.unknow-error",
		// "sub_msg": "系统繁忙"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return null;
	}

	public String closeTrade(PayConfigEntity payConfigEntity, String tradeNo,
			String outTradeNo, String operatorId) {

		initAlipayClient(payConfigEntity);

		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("out_trade_no", outTradeNo);
		jsonObject.put("trade_no", tradeNo);
		jsonObject.put("operator_id", operatorId);
		request.setBizContent(jsonObject.toString());
		AlipayTradeCloseResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// {
		// "alipay_trade_close_response": {
		// "code": "10000",
		// "msg": "Success",
		// "trade_no": "2013112111001004500000675971",
		// "out_trade_no": "YX_001"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }

		// {
		// "alipay_trade_close_response": {
		// "code": "20000",
		// "msg": "Service Currently Unavailable",
		// "sub_code": "isp.unknow-error",
		// "sub_msg": "系统繁忙"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return null;
	}

	public String queryDataServiceBillDownloadUrl(
			PayConfigEntity payConfigEntity, String billType, String billDate) {
		initAlipayClient(payConfigEntity);

		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("bill_type", billType);
		jsonObject.put("bill_date", billDate);
		request.setBizContent(jsonObject.toString());
		AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// {
		// "alipay_data_dataservice_bill_downloadurl_query_response": {
		// "code": "10000",
		// "msg": "Success",
		// "bill_download_url":
		// "http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=X&userId=X&fileType=X&bizDates=X&downloadFileName=X&fileId=X"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }

		// {
		// "alipay_data_dataservice_bill_downloadurl_query_response": {
		// "code": "20000",
		// "msg": "Service Currently Unavailable",
		// "sub_code": "isp.unknow-error",
		// "sub_msg": "系统繁忙"
		// },
		// "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		// }
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
		return null;
	}

}
