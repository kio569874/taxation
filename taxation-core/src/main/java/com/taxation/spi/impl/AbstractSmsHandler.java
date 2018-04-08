package com.taxation.spi.impl;

import com.taxation.spi.ISmsHandle;

/**
 * 短信服务抽象实现
 * 
 * @author yc
 *
 */
public abstract class AbstractSmsHandler implements ISmsHandle {

	/**
	 * 生成验证码
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public String productCheckCode(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return randNum + "";
	}
}
