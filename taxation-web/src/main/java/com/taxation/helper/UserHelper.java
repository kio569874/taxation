package com.taxation.helper;

import net.sf.json.JSONObject;

import com.taxation.bean.UserEntity;
import com.taxation.util.JSONBeanUtil;
import com.taxation.util.MD5Utils;

public class UserHelper {
	
	public static void main(String[] args) {
		String content = "{\"user\":{\"userAccount\":\"123456\",\"userPassword\":\"131223456\",\"userPhone\":\"13556568585\"}}";
		JSONObject jsonObj = JSONObject.fromObject(content);
		JSONObject userJsonObj = jsonObj.getJSONObject("user");
		UserEntity userEntity = JSONBeanUtil.JsonToBean(UserEntity.class,
				userJsonObj.toString());
		System.out.println(userEntity.getUserAccount());
	}

	public static UserEntity buildUserEntity(String content) {
		JSONObject jsonObj = JSONObject.fromObject(content);
		JSONObject userJsonObj = jsonObj.getJSONObject("user");
		UserEntity userEntity = JSONBeanUtil.JsonToBean(UserEntity.class,
				userJsonObj.toString());
		// 把MD5加密后的密码放入
		userEntity.setUserPassword(MD5Utils.createMD5(userEntity
				.getUserPassword()));
		return userEntity;
	}

}
