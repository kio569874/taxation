package com.taxation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.taxation.model.CityArea;
import com.taxation.model.CityAreaExample;

public interface CityAreaMapper {
    int countByExample(CityAreaExample example);

    int deleteByExample(CityAreaExample example);

    int insert(CityArea record);

    int insertSelective(CityArea record);

    List<CityArea> selectByExample(CityAreaExample example);

    int updateByExampleSelective(@Param("record") CityArea record, @Param("example") CityAreaExample example);

    int updateByExample(@Param("record") CityArea record, @Param("example") CityAreaExample example);
    
    /**
	 * 分页查询
	 * 
	 * @param example
	 * @param params
	 * @return
	 */
	List<CityArea> selectWithPageByExample(@Param("example") CityAreaExample example,@Param("params") Map<String, String> params);
	
}