package com.taxation.api.service;

import java.util.List;
import java.util.Map;

import com.taxation.bean.AliPayOrderJnlEntity;

public interface IAlipayOrderJnlService extends IBaseService<AliPayOrderJnlEntity>{
	
	List<AliPayOrderJnlEntity> queryAliPayOrderJnlList(Map<String,Object> map);
}
