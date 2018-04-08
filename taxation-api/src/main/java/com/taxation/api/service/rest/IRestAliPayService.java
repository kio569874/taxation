package com.taxation.api.service.rest;

public interface IRestAliPayService {

	/**
	 * 异步接收支付宝支付结果通知
	 * 
	 * @param context
	 */
	String asyncReceivePayresult(String context);

}
