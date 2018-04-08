package com.taxation.util;

import net.sf.json.JSONObject;


public class JSONBeanUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T JsonToBean(Class<T> className, String content) {
		JSONObject jsonObject = JSONObject.fromObject(content) ;
		 return (T) JSONObject.toBean(jsonObject, className);
	}
}
