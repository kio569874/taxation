package com.taxation.helper;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import com.taxation.bean.UserAddress;
import com.taxation.util.MD5Utils;

public class UserAddressHelper {
	
	public static UserAddress buildUserAddressForAdd(String content){
		JSONObject jsonObj = JSONObject.fromObject(content);
		Map<String, Object> map = jsonObj;
		Map<String,Object> userMap = (Map<String, Object>) map.get("newAddress");
		String addressProvince = userMap.get("addressProvince")==null?"":userMap.get("addressProvince").toString();
		String addressCity = userMap.get("addressCity")==null?"":userMap.get("addressCity").toString();
		String addressArea = userMap.get("addressArea")==null?"":userMap.get("addressArea").toString();
		String addressText = userMap.get("addressText")==null?"":userMap.get("addressText").toString();
		String addressDefault = userMap.get("addressDefault")==null?"":userMap.get("addressDefault").toString();
		String userId = userMap.get("userId")==null?"":userMap.get("userId").toString();
		String userName = userMap.get("userName")==null?"":userMap.get("userName").toString();
		String userPhone = userMap.get("userPhone")==null?"":userMap.get("userPhone").toString();
//		

		UserAddress userAddress = new UserAddress();
		userAddress.setAddressProvince(addressProvince);
		userAddress.setAddressCity(addressCity);
		userAddress.setAddressArea(addressArea);
		userAddress.setAddressText(addressText);
		userAddress.setAddressDefault(Integer.parseInt(addressDefault)==1?true:false);
		userAddress.setAddressAtime((int)System.currentTimeMillis());
		userAddress.setUserId(userId);
		userAddress.setUserName(userName);
		userAddress.setUserPhone(userPhone);
		return userAddress;
	}
	public static UserAddress buildUserAddressForUpdate(String content){
		JSONObject jsonObj = JSONObject.fromObject(content);
		Map<String, Object> map = jsonObj;
		//{"user":{"userAccount":"yangchao","userPassword":"123456","userPassword1":"123456","userIdcard":"522222199007112835",
//		"userPhone":"17802141727","phoneCheckCode":"8653","checkCode":"CVFT"}}
		Map<String,Object> userMap = (Map<String, Object>) map.get("updateAddress");
		String addressId = userMap.get("addressId")==null?"":userMap.get("addressId").toString();
		String addressProvince = userMap.get("addressProvince")==null?"":userMap.get("addressProvince").toString();
		String addressCity = userMap.get("addressCity")==null?"":userMap.get("addressCity").toString();
		String addressArea = userMap.get("addressArea")==null?"":userMap.get("addressArea").toString();
		String addressText = userMap.get("addressText")==null?"":userMap.get("addressText").toString();
		String addressDefault = userMap.get("addressDefault")==null?"":userMap.get("addressDefault").toString();
		String userId = userMap.get("userId")==null?"":userMap.get("userId").toString();
		String userName = userMap.get("userName")==null?"":userMap.get("userName").toString();
		String userPhone = userMap.get("userPhone")==null?"":userMap.get("userPhone").toString();
//

		UserAddress userAddress = new UserAddress();
		userAddress.setAddressId(addressId);
		userAddress.setAddressProvince(addressProvince);
		userAddress.setAddressCity(addressCity);
		userAddress.setAddressArea(addressArea);
		userAddress.setAddressText(addressText);
		userAddress.setAddressDefault(Integer.parseInt(addressDefault)==1?true:false);
		userAddress.setAddressAtime((int)System.currentTimeMillis());
		userAddress.setUserId(userId);
		userAddress.setUserName(userName);
		userAddress.setUserPhone(userPhone);
		return userAddress;
	}

}
