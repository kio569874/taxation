package com.taxation.context;

import java.util.List;

import com.taxation.bean.PayConfigEntity;
import com.taxation.constants.alipay.AlipayPayConfigStatusEnum;

/**
 * 支付上下文
 * 
 * @author yc
 *
 */
public class PayContext {

	private static PayContext instance = new PayContext();

	private List<PayConfigEntity> payConfigEntityList = null;

	private PayContext() {

	}

	public static PayContext getInstance() {
		return instance;

	}

	public boolean isPayConfigEntityList() {
		return this.payConfigEntityList != null
				&& payConfigEntityList.size() > 0;
	}

	public void setPayConfigEntityList(List<PayConfigEntity> payConfigEntityList) {
		this.payConfigEntityList = payConfigEntityList;
	}

	/**
	 * 
	 * @param payKey
	 * @param payType
	 * @return
	 */
	public PayConfigEntity getPayConfigEntity(String payKey, String payType) {
		if (payConfigEntityList != null && payConfigEntityList.size() > 0) {
			for (PayConfigEntity payConfigEntity : payConfigEntityList) {
				if (payKey.equals(payConfigEntity.getPayKey())
						&& payType.equals(payConfigEntity.getPayType())
						&& AlipayPayConfigStatusEnum.START.getStatus().equals(
								payConfigEntity.getConfigStatus())) {
					return payConfigEntity;
				}
			}
		}
		return null;
	}

}
