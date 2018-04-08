package com.taxation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author yc
 *
 */
public class CreateIdUtil {
	private static String macTag = "001";
	private static int no = 1;
	// 暂时先采用低效率锁的方式获取
	public static final Object LOCK = new Object();

	static {
		try {
			// 在jvm启动的时候放入macTag，多台部署的时候保证macTag每台唯一
			if (System.getProperty("macTag") != null
					&& !"".equals(System.getProperty("macTag"))) {
				macTag = System.getProperty("macTag");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String createId(String prex) {
		String FORMAT1 = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
		String tranDate = sdf.format(new Date());
		int current = 1000;
		synchronized (LOCK) {
			if (no > 8999) {
				no = 1000;
			}
			current += no++;
		}
		StringBuffer sb = new StringBuffer(prex);
		sb.append(macTag).append(tranDate).append(current);
		return sb.toString();
	}

}
