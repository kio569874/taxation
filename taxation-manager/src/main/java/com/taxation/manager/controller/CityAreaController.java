package com.taxation.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.service.ICityAreaService;
import com.taxation.entity.Page;
import com.taxation.manager.utils.AjaxUtils;
import com.taxation.manager.utils.SystemUserHelper;
import com.taxation.model.CityArea;

@Controller
@RequestMapping("cityArea")
public class CityAreaController {

	private static Logger logger = Logger.getLogger(SystemUserController.class);
	
	@Autowired
	private ICityAreaService cityAreaService;
	
	// 添加城市代码
	@RequestMapping(value = "/addCityArea.htm", method = RequestMethod.POST)
	public String addCityArea(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			int count = this.cityAreaService.add(SystemUserHelper.buildCityAreaModel(content));
			if(count > 0){
				AjaxUtils.ajaxJsonSuccessMessage(response, "保存成功");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "保存失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, e.getMessage());
			logger.error(e);
		}
		return null;
	}
	
	// 删除城市代码
	@RequestMapping(value = "/removeCityArea.htm", method = RequestMethod.POST)
	public String removeCityArea(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			int count = this.cityAreaService.delete(SystemUserHelper.buildCityAreaModel(content));
			if(count > 0){
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, e.getMessage());
			logger.error(e);
		}
		return null;
	}
	
	// 修改城市代码
	@RequestMapping(value = "/editCityArea.htm", method = RequestMethod.POST)
	public String editCityArea(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			int count = this.cityAreaService.update(SystemUserHelper.buildCityAreaModel(content));
			if(count > 0){
				AjaxUtils.ajaxJsonSuccessMessage(response, "修改成功");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "修改失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, e.getMessage());
			logger.error(e);
		}
		return null;
	}
	
	// 分页查询城市代码
	@RequestMapping(value = "/page.htm", method = RequestMethod.POST)
	public String page(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Page<CityArea> page = this.cityAreaService.page(SystemUserHelper.buildCityAreaModel(content));
			if (page != null
					&& !CollectionUtils.isEmpty(page.getList())) {
				JSONObject js = JSONObject.fromObject(page);
				AjaxUtils.ajaxJsonSuccessMessage(response, js.toString());
			} else {
			}

		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, e.getMessage());
			logger.error(e);
		}
		return null;
	}
	
	// 查询一条数据
	@RequestMapping(value = "/getOne.htm", method = RequestMethod.POST)
	public String getOne(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			
			CityArea cityArea = this.cityAreaService.getOne(SystemUserHelper.buildCityAreaModel(content));
			if (cityArea != null) {
				JSONObject js = JSONObject.fromObject(cityArea);
				AjaxUtils.ajaxJsonSuccessMessage(response, js.toString());
			} else {
			}

		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, e.getMessage());
			logger.error(e);
		}
		return null;
	}
	
	@RequestMapping(value = "/batchRemove.htm", method = RequestMethod.POST)
	public String batchRemove(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String cityAreaPids = map.get("cityAreaPids").toString();
			int count = this.cityAreaService.batchRemove(new CityArea(), cityAreaPids);
			if (count > 0) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除了" + count + "条数据");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "批量删除失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "删除异常");
			logger.error(e);
		}
		return null;
	}
	
}
