package com.taxation.manager.utils;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.taxation.bean.SystemUserEntity;
import com.taxation.model.CityArea;
import com.taxation.util.JSONBeanUtil;

public class SystemUserHelper {
	
	public static SystemUserEntity buildSystemUserEntity(String content){
		JSONObject jsonObj = JSONObject.fromObject(content);
		Map<String, Object> map = jsonObj;
		Map<String,Object> userMap = (Map<String, Object>) map.get("systemUser");
		String id = userMap.get("id")==null?"":userMap.get("id").toString();
		String userCode = userMap.get("userCode")==null?"":userMap.get("userCode").toString();
		String userPassword = userMap.get("userPassword")==null?"":userMap.get("userPassword").toString();
		String userName = userMap.get("userName")==null?"":userMap.get("userName").toString();
		String userPhone = userMap.get("userPhone")==null?"":userMap.get("userPhone").toString();
		String userLevel = userMap.get("userLevel")==null?"":userMap.get("userLevel").toString();
		String userPosition = userMap.get("userPosition")==null?"":userMap.get("userPosition").toString();
		String userStatus = userMap.get("userStatus")==null?"":userMap.get("userStatus").toString();
//		String loginTime = userMap.get("userMemberName")==null?"":userMap.get("userMemberName").toString();
//		String loginIp = userMap.get("userAccount")==null?"":userMap.get("userAccount").toString();


		SystemUserEntity systemUserEntity = new SystemUserEntity();
		systemUserEntity.setId(id);
		systemUserEntity.setCreateTime(new Date());
		systemUserEntity.setLoginIp(null);
		systemUserEntity.setLoginTime(null);
		systemUserEntity.setUpdateTime(new Date());
		systemUserEntity.setUserCode(userCode);
		systemUserEntity.setUserLevel(userLevel);
		systemUserEntity.setUserName(userName);
		systemUserEntity.setUserPhone(userPhone);
		systemUserEntity.setUserPosition(userPosition);
		systemUserEntity.setUserStatus(userStatus);
		systemUserEntity.setUserPassword(userPassword);
		return systemUserEntity;
	}
	
	public static CityArea buildCityAreaModel(String content){
		
		JSONObject jsonObj = JSONObject.fromObject(content);
		JSONObject cityAreaJsonObj = jsonObj.getJSONObject("cityArea");
		CityArea cityArea = JSONBeanUtil.JsonToBean(CityArea.class,cityAreaJsonObj.toString());
		
		return cityArea;
		
	}

}
