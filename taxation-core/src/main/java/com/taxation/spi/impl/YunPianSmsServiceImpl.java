package com.taxation.spi.impl;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.bean.SmsConfigEntity;
import com.taxation.bean.SmsNoticeEntity;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

/**
 * 云片网短信服务实现
 * 
 * @author yc
 * @param 
 *
 */
public class YunPianSmsServiceImpl extends AbstractSmsHandler {

	private static Logger logger = Logger
			.getLogger(YunPianSmsServiceImpl.class);
	
	@Override
	public void sendSmsCheckCode(SmsConfigEntity smsConfigEntity,
			SmsCheckCodeEntity smsCheckCodeEntity) {

		try {
			String apikey = smsConfigEntity.getApikey();
			String hour = smsConfigEntity.getValidDuration() == null ? "15分钟"
					: smsConfigEntity.getValidDuration();
			String mouldContext = smsConfigEntity.getMouldContext();
			String checkCode = productCheckCode(1, 999999);
			mouldContext = mouldContext.replace("#code", checkCode);
			mouldContext = mouldContext.replace("#HOUR", hour);
			smsCheckCodeEntity.setSmsCheckCode(checkCode);
			smsCheckCodeEntity.setSmsContext(mouldContext);
			smsCheckCodeEntity.setSendTime(new Date());

			// TODO 需要改成这样系统的post请求发送，不直接使用云片网的api
			YunpianRestClient client = new YunpianRestClient(apikey);// 用apikey生成client,可作为全局静态变量
			SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
			ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(
					smsCheckCodeEntity.getUserPhone(), mouldContext);// 发送短信,ResultDO<?>.isSuccess()判断是否成功

			this.buildResultData(result, smsCheckCodeEntity);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void sendSmsNotice(SmsConfigEntity smsConfigEntity,
			SmsNoticeEntity smsNoticeEntity) {
	}

	public void buildResultData(ResultDO<SendSingleSmsInfo> result,
			SmsCheckCodeEntity smsCheckCodeEntity) {
		if (result.isSuccess()) {
			smsCheckCodeEntity.setSendReturncode(result.getData().getCode()
					+ "");
			smsCheckCodeEntity.setSendReturnmessage(result.getData().getMsg());
			smsCheckCodeEntity.setSendCount(result.getData().getCount());
			smsCheckCodeEntity.setSendFee(result.getData().getFee() + "");
			smsCheckCodeEntity.setFeeUnit(result.getData().getUnit());
			smsCheckCodeEntity.setSendId(result.getData().getSid());
		} else {
			JSONObject jsonObj = JSONObject.fromObject(result.getE()
					.getMessage());
			Map<String, Object> map = jsonObj;
			String code = map.get("code") == null ? "" : map.get("code")
					.toString();
			String message = map.get("detail") == null ? "" : map.get("detail")
					.toString();
			String httpStatusCode = map.get("http_status_code") == null ? ""
					: map.get("http_status_code").toString();
			smsCheckCodeEntity.setSendReturncode(code);
			smsCheckCodeEntity.setSendReturnmessage(message);

		}

	}

}
