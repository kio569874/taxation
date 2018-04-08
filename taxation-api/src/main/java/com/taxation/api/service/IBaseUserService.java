package com.taxation.api.service;

public interface IBaseUserService<T> {
	
	boolean register(T t);

	boolean login(T t);

}
