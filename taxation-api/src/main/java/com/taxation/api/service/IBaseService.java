package com.taxation.api.service;

import java.util.List;

/**
 * 公用接口
 * @author yc
 *
 * @param <T>
 */
public interface IBaseService<T> {
	
	/**
	 * 插入
	 * @param t
	 */
	public void insert(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t);

	/**
	 * 根据id删除记录
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 单表分页查询
	 * TODO T修改修改成具体的分页及查询实体
	 * @param t
	 * @return
	 */
    public List<T> query(T t);
    
    /**
     * 查询总条数 TODO T修改修改成具体的分页及查询实体
     * @param t
     * @return
     */
    public int count(T t);
}
