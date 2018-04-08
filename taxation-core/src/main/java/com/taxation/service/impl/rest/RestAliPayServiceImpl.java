package com.taxation.service.impl.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.taxation.api.service.rest.IRestAliPayService;

/**
 * 支付实现
 * 
 * @author yc
 *
 */
@Service("restAliPayService")
@Path("payResult")
public class RestAliPayServiceImpl implements IRestAliPayService {

	private static Logger logger = Logger.getLogger(RestAliPayServiceImpl.class);

	@Override
	@POST
	@Path("/notifyUrl")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8,ContentType.TEXT_PLAIN_UTF_8})
	public String asyncReceivePayresult(String context) {
//		https://商家网站通知地址?voucher_detail_list=[{"amount":"0.20","merchantContribute":"0.00","name":"5折券","otherContribute":"0.20","type":"ALIPAY_DISCOUNT_VOUCHER","voucherId":"2016101200073002586200003BQ4"}]&fund_bill_list=[{"amount":"0.80","fundChannel":"ALIPAYACCOUNT"},{"amount":"0.20","fundChannel":"MDISCOUNT"}]&subject=PC网站支付交易&trade_no=2016101221001004580200203978&gmt_create=2016-10-12 21:36:12&notify_type=trade_status_sync&total_amount=1.00&out_trade_no=mobile_rdm862016-10-12213600&invoice_amount=0.80&seller_id=2088201909970555&notify_time=2016-10-12 21:41:23&trade_status=TRADE_SUCCESS&gmt_payment=2016-10-12 21:37:19&receipt_amount=0.80&passback_params=passback_params123&buyer_id=2088102114562585&app_id=2016092101248425&notify_id=7676a2e1e4e737cff30015c4b7b55e3kh6& sign_type=RSA2&buyer_pay_amount=0.80&sign=***&point_amount=0.00

		System.out.println("异步接收支付宝通知：" + context);
		logger.info("异步接收支付宝通知：" + context);
		return context;

	}

}
