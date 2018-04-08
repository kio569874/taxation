package com.taxation.helper;

import java.util.Map;

import net.sf.json.JSONObject;

import com.taxation.bean.ShoppingTrolleyEntity;

public class ShoppingTrolleyHelper {

	public static ShoppingTrolleyEntity buildShoppingTrolleyEntity(
			String content) {
		JSONObject jsonObj = JSONObject.fromObject(content);
		Map<String, Object> map = jsonObj;
		String userId = map.get("userId") == null ? "" : map.get("userId")
				.toString();
		String serviceId = map.get("serviceId") == null ? "" : map.get(
				"serviceId").toString();
		// String loginTime =
		// userMap.get("userMemberName")==null?"":userMap.get("userMemberName").toString();
		// String loginIp =
		// userMap.get("userAccount")==null?"":userMap.get("userAccount").toString();

		ShoppingTrolleyEntity shoppingTrolleyEntity = new ShoppingTrolleyEntity();
		shoppingTrolleyEntity.setUserId(userId);
		shoppingTrolleyEntity.setServiceId(serviceId);
		return shoppingTrolleyEntity;
	}

}
