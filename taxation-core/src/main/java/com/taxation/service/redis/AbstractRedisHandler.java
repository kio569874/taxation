package com.taxation.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * redis存储抽象类
 * @author yc
 *
 */
public abstract class AbstractRedisHandler {

	@Autowired
	protected RedisClientTemplate redisClientTemplate ; 
	
}
