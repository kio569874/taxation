package com.taxation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.service.IAlipayOrderJnlService;
import com.taxation.bean.AliPayOrderJnlEntity;
import com.taxation.dao.AliPayOrderJnlDao;

@Service("alipayOrderJnlService")
public class AlipayOrderJnlServiceImpl implements IAlipayOrderJnlService {

	@Resource
	private AliPayOrderJnlDao aliPayOrderJnlDao;

	@Override
	public void insert(AliPayOrderJnlEntity t) {
		aliPayOrderJnlDao.insert(t);

	}

	@Override
	public void update(AliPayOrderJnlEntity t) {
		aliPayOrderJnlDao.updateByPrimaryKey(t);

	}

	@Override
	public void delete(String id) {
		aliPayOrderJnlDao.deleteByPrimaryKey(id);

	}

	@Override
	public List<AliPayOrderJnlEntity> query(AliPayOrderJnlEntity t) {
		return null;
	}

	@Override
	public int count(AliPayOrderJnlEntity t) {
		return 0;
	}

	@Override
	public List<AliPayOrderJnlEntity> queryAliPayOrderJnlList(
			Map<String, Object> map) {
		return aliPayOrderJnlDao.queryAliPayOrderJnlList(map);
	}

}
