package com.taxation.helper;

import java.util.Map;

import com.taxation.bean.EnterpriseEnrollDto;
import net.sf.json.JSONObject;

import com.taxation.bean.EnterpriseEnrollEntity;

public class EnterpriseEnrollHelper {

	public static EnterpriseEnrollDto buildEnterpriseEnrollEntity(
			String content) {
		JSONObject jsonObj = JSONObject.fromObject(content);
		Map<String, Object> map = jsonObj;
		Map<String, Object> enterpriseEnrollMap = (Map<String, Object>) map
				.get("params");
		String userId = enterpriseEnrollMap.get("userId") == null ? ""
				: enterpriseEnrollMap.get("userId").toString();
		String providerId = enterpriseEnrollMap.get("providerId") == null ? ""
				: enterpriseEnrollMap.get("providerId").toString();
		String userName = enterpriseEnrollMap.get("userName") == null ? ""
				: enterpriseEnrollMap.get("userName").toString();
		String userSex = enterpriseEnrollMap.get("userSex") == null ? ""
				: enterpriseEnrollMap.get("userSex").toString();
		String userIdcard = enterpriseEnrollMap.get("userIdcard") == null ? ""
				: enterpriseEnrollMap.get("userIdcard").toString();
		String userPhone = enterpriseEnrollMap.get("userPhone") == null ? ""
				: enterpriseEnrollMap.get("userPhone").toString();

		String operateSiteNature = enterpriseEnrollMap.get("operateSiteNature") == null ? ""
				: enterpriseEnrollMap.get("operateSiteNature").toString();
		String produceOperateType = enterpriseEnrollMap
				.get("produceOperateType") == null ? "" : enterpriseEnrollMap
				.get("produceOperateType").toString();
		String province = enterpriseEnrollMap.get("province") == null ? ""
				: enterpriseEnrollMap.get("province").toString();
		String city = enterpriseEnrollMap.get("city") == null ? ""
				: enterpriseEnrollMap.get("city").toString();
		String area = enterpriseEnrollMap.get("area") == null ? ""
				: enterpriseEnrollMap.get("area").toString();
		String address = enterpriseEnrollMap.get("address") == null ? ""
				: enterpriseEnrollMap.get("address").toString();
		String price = enterpriseEnrollMap.get("price") == null ? ""
				: enterpriseEnrollMap.get("price").toString();
		String serviceId = enterpriseEnrollMap.get("serviceId") == null ? ""
				: enterpriseEnrollMap.get("serviceId").toString();
		String addressId = enterpriseEnrollMap.get("addressId") == null ? ""
				: enterpriseEnrollMap.get("addressId").toString();
		String payType = enterpriseEnrollMap.get("payType") == null ? ""
				: enterpriseEnrollMap.get("payType").toString();
		EnterpriseEnrollDto enterpriseEnrollEntity = new EnterpriseEnrollDto();
		enterpriseEnrollEntity.setUserId(userId);
		enterpriseEnrollEntity.setProviderId(providerId);
		enterpriseEnrollEntity.setUserName(userName);
		enterpriseEnrollEntity.setUserSex(userSex);
		enterpriseEnrollEntity.setUserIdcard(userIdcard);
		enterpriseEnrollEntity.setUserPhone(userPhone);
		enterpriseEnrollEntity.setOperateSiteNature(operateSiteNature);
		enterpriseEnrollEntity.setProduceOperateType(produceOperateType);
		enterpriseEnrollEntity.setProvince(province);
		enterpriseEnrollEntity.setCity(city);
		enterpriseEnrollEntity.setArea(area);
		enterpriseEnrollEntity.setAddress(address);
		enterpriseEnrollEntity.setPrice(price);
		enterpriseEnrollEntity.setServiceId(serviceId);
		enterpriseEnrollEntity.setAddressId(addressId);
		enterpriseEnrollEntity.setPayType(payType);

		return enterpriseEnrollEntity;
	}
}
