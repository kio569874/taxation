package com.taxation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taxation.api.service.ICityAreaService;
import com.taxation.dao.CityAreaMapper;
import com.taxation.entity.Page;
import com.taxation.entity.StringUtil;
import com.taxation.model.CityArea;
import com.taxation.model.CityAreaExample;
@Service("cityAreaService")
public class CityAreaServiceImpl implements ICityAreaService {

	@Resource
	private CityAreaMapper cityAreaMapper;
	
	/**
	 * @desc:私有方法，用于构造页面传入的条件查询参数生成条件查询example
	 * @param param
	 * @return
	 */
	private CityAreaExample getExample(CityArea param){
		
		CityAreaExample example = new CityAreaExample();
		CityAreaExample.Criteria c = example.createCriteria();
		if(param.getSort() != null && param.getSort()!="") {
			example.setOrderByClause(StringUtil.camelToUnderline(param.getSort()));
		}
		if(param.getPname() != null && !"".equalsIgnoreCase(param.getPname())){
			c.andPnameLike("%" + param.getPname() + "%");
		}
		return example;
		
	}

	@Override
	public int add(CityArea param) throws Exception {
		if(param.getPid() == null || "".equals(param.getPid())){
			throw new Exception("请输入需要的PID！！！");
		}
		return this.cityAreaMapper.insert(param);
	}

	@Override
	public int delete(CityArea param) throws Exception {
		if(param.getPid() == null || "".equals(param.getPid())){
			throw new Exception("请选择需要删除的记录！！！");
		}
		CityAreaExample example = new CityAreaExample();
		example.createCriteria().andPidEqualTo(param.getPid());
		return this.cityAreaMapper.deleteByExample(example);
	}

	@Override
	public int update(CityArea param) throws Exception {
		if(param.getPid() == null || "".equals(param.getPid())){
			throw new Exception("请选择需要修改的记录！！！");
		}
		CityAreaExample example = new CityAreaExample();
		example.createCriteria().andPidEqualTo(param.getPid());
		return this.cityAreaMapper.updateByExampleSelective(param, example);
	}

	@Override
	public CityArea getOne(CityArea param) throws Exception {
		if(param.getPid() == null || "".equals(param.getPid())){
			throw new Exception("请选择需要查询的记录！！！");
		}
		CityAreaExample example = new CityAreaExample();
		example.createCriteria().andPidEqualTo(param.getPid());
		List<CityArea> cityAreaList = this.cityAreaMapper.selectByExample(example);
		if(cityAreaList != null && cityAreaList.size() > 0){
			return cityAreaList.get(0);
		}
		return null;
	}

	@Override
	public List<CityArea> getList(CityArea param) throws Exception {
		return this.cityAreaMapper.selectByExample(this.getExample(param));
	}

	@Override
	public Page<CityArea> page(CityArea param) throws Exception {
		Page<CityArea> page = new Page<CityArea>();
		if(param.getOrder() == null || param.getOrder() == ""){
			param.setOrder("desc");
		}
		if(param.getSort()== null || param.getSort() == ""){
			param.setSort("pid");
		}
		if(param.getPage() == 0){
			param.setPage(1);
		}
		if(param.getRows() == 0){
			param.setRows(10);
		}
		Map<String, String> params = param.getParams();
		CityAreaExample example = this.getExample(param);
		List<CityArea> list = new ArrayList<CityArea>();
		if(params.size()>1){
			list = this.cityAreaMapper.selectWithPageByExample(example, params);
		}else{
			list = this.cityAreaMapper.selectByExample(example);
		}
		page.setList(list);
		page.setTotalRecord(this.cityAreaMapper.countByExample(this.getExample(param)));
		page.setPageSize(param.getRows());
		page.setCurrentPage(param.getPage());
		return page;
	}

	@Override
	public int batchRemove(CityArea param, String ids) throws Exception {
		if(ids == null || "".equals(ids)){
			throw new Exception("请选择需要删除的数据列表再进行批量删除操作");
		}
		CityAreaExample example = new CityAreaExample();
		CityAreaExample.Criteria c = example.createCriteria();
		try {
			String[] arr = ids.split(",");
			List<String> idList = new ArrayList<String>();
			if(arr != null && arr.length > 0){
				for(int i=0;i<arr.length;i++){
					idList.add(arr[i]);
				}
			}
			c.andPidIn(idList);
			return this.cityAreaMapper.deleteByExample(example);
		} catch (Exception e) {
		}
		return 0;
	}

}
