package com.taxation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.service.IAliPayService;
import com.taxation.web.util.AjaxUtils;

@Controller
@RequestMapping("pay")
public class PayController {

	private static Logger logger = Logger.getLogger(PayController.class);

	// private static String APP_ID = "2017063007605563";
	// private static String APP_PRIVATE_KEY =
	// "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQ10x1uMATzKXj+4CcXgSa0QlqN21gH+GiGhRUDg925XgJsHhl6jECKWEfnVV0s0V5iZypDGqhWB4+lauU/uEubDoHXgGyYdqNuhaMWSGDdWKbKSun/0bhqs4fulUy+qxbcgqtApDRMBTXsYcl+W8Zol4tmmcnDt+70thtKoucpgWMPCP+FJUWZByqxzKuwoyE8GTqXPNxTGpKx3n/2ingorujACiMLnXsML/DgLFjikrV7tYdCmvfU2sTDq5c/7nNvzeI4gGPxct9ap3L8wuLDGcZWkAVJTjIn64LPseY0L34wdeuW+OT0G4DcjvJ8sLEzfLVJcIzuw6zmERliPitAgMBAAECggEAJRE0XBw/69E/0yyWKW5WN1iv1CMDNspx8A5EjY5bns3y/mfPKCXbDPaSCIk3SXpnmUcCdLDcyRXIB7Q+CQ+BiWPhJemE/aXVTNgG1TwqO35bEeJjbccC2NXhknUZQMauPWjeGpDBhweLDfg84hcHu8O13m3ZYEGKOIMpKCbvO9NOjoPbgtkYfmrSkbA7sqiAyga7a0Y8oL2/LgtGPXyxdKJeIJc6rCoDL/URz7RhQWmQ4rSni6tWfqR3o+mRWcTYUdKbQbWt2YOP7xd2ftE2cRJJOqu4i4ck8m3C4fYPoEhpKeo4p//ksnjkw5ZtdBxUiNcIKoiN59+oWbZQfnyMcQKBgQDICCxVIQPBlDc5UirSfnf3Yuerh9IFMkr6HwHJz45n41Vp7CVBmxbbL4AwFcsk+Vt+B3jbUmmRz77lTlfXq3WQP1pUnlwjdHPeJ9b1JYLoZ6duebpprbWLkVhM60p88Z/xjCBL18FxTE/PFRSFRM+AC5UUISVRj5tEbIvbiKcRrwKBgQC5Xe1XtKN9AJFePYVl8dRzg7peoLtJegI7qukYZX2IdMmk8mxYJTjT6ivNdBoHaViWR+9hyO+UH6c7Nywat8t1WEFlwBN1eOT8AgC9AebOql+/GI7gXgnkYoZeA+p/rGExQBIm4cSQKOPYb9AscBueeFQGDyUBNfVqR46qXJd+YwKBgA6OUbOMKHrQr0gTi5wvqcktwFACHS1VoJEc6oNmACXdLBz+GyPwBjTC+yAEpfi10IuWPZwwHhCHPovvLIM8LdzmT0tzDp9h3QCgg4rpROJmkI+Aeikwg9J5LCTExnHbH1lzafI67A+zjucx0IF2JWda8Gk7lDygJ5tODstV4VpvAoGAEY8ULHqwAedL3eF2AXIm7cpMnSock5k7HOvTqLv0aDin4TgVi0P7XheQjqWw0YLpIVMvGdQu3Zkh/jn7s4w1eagiicDoFvicuEJ4T28erfT1nOR2pEpEJZ4vnDhCvlVV35RyI7y/Iei9rILq9UqpUxmS3AATQUobNTYSDZE3yIECgYBeG5Z15+6OD9ciZv1dISQi9a1aiM89K2duHWT4n7kmmGeGyhk5nyCxlZnzTfI6qAsysFL9DBJOwrL8rMbqCxi6GQVccdkilE9I8yh9msmlTGkQiLUFbBT7gHQUaVavBkCy9oYFiqOnFHLJjDsI8S8bUHmYgkY5GLIdA7C6CdMVBw==";
	// private static String FORMAT = "json";
	// private static String CHARSET = "UTF-8";
	// private static String ALIPAY_PUBLIC_KEY
	// ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAghxk/l/V+nagctfhSYhc3lljeHPXxFt92zyv4qc4bCycsKfx4tS5kJVU5dx6bQyP5XHm9Bv6NeyN22E120ZdzAeGq5YsWg82pDzMut3e0R1zPB8p3Acqekr1nGTA9MBs6pYTyk83Coml991rlZTWcXHnD89kzRagLoQjRQljSEPhQ61KGkDkLG8/Pk/qC5to2+r3JjS6J+7EdnLcxVDappk00S2gs8Dbx4Amx6Tpr2uX72XhAw3/oT0EspM1E5Ot5pkICXBQaSf1FSvqDcmWcY0TXcmTq5rGiWlZadCKhnhNj3nHA3YkmQCmsFBSBFzdcpEBZTKdSW8BABW5OL4e7QIDAQAB";
	// private static String SIGN_TYPE = "RSA2";

	@Autowired
	private IAliPayService aliPayService;

	@RequestMapping(value = "/orderAliPay.htm", method = RequestMethod.POST)
	public void orderAliPay(@RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String orderNo = map.get("orderNo").toString();
			double orderAmount = Double.parseDouble(map.get("orderAmount").toString());
			String orderSubject = map.get("orderSubject").toString();
			String outTradeNo = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()) + "000010000200005";
			double money = 0.10;
			// BigDecimal big = new BigDecimal(0.10 + "");
			String subject = "我要付款";
			String result = aliPayService.tradePay(orderNo, money, orderSubject);
			// 不存在订单数据
			AjaxUtils.ajaxJsonSuccessMessage(response, result);
		} catch (Exception e) {
			logger.error(e);
			AjaxUtils.ajaxJsonErrorMessage(response, "订单支付异常");
		}

	}
}
