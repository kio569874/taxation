package com.taxation.dubbo.rest.support.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.apache.log4j.Logger;

/**
 * dubbo rest实现自己的拦截器
 * 
 * @author yc
 *
 */
public class LocalContainerRequestFilter implements ContainerRequestFilter {
	private static Logger logger = Logger
			.getLogger(LocalContainerRequestFilter.class);

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		// TODO 自动生成的方法存根
//		System.out.println("test filter");
//		logger.info("test a");
	}

}
