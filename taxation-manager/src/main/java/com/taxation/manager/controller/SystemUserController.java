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

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.ISystemUserService;
import com.taxation.api.service.IUserService;
import com.taxation.bean.SystemUserEntity;
import com.taxation.manager.utils.AjaxUtils;
import com.taxation.manager.utils.SystemUserHelper;

@Controller
@RequestMapping("systemUserCon")
public class SystemUserController {
	private static Logger logger = Logger.getLogger(SystemUserController.class);

	@Autowired
	private ISystemUserService systemUserService;
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/querySystemUser.htm", method = RequestMethod.POST)
	public String querySystemUser(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			int page = Integer.parseInt(map.get("pageIndex").toString());
			int pageSize = Integer.parseInt(map.get("pageSize").toString());
//			UserEntity user = userService.getUserById("13888888888");
//			System.out.println(user.getLastLoginTime());
			PaginationDto<SystemUserEntity> pagination = systemUserService
					.getSystemUserByPage(page, pageSize);
			if (pagination != null
					&& !CollectionUtils.isEmpty(pagination.getData())) {
				JSONObject js = JSONObject.fromObject(pagination);
				AjaxUtils.ajaxJsonSuccessMessage(response, js.toString());
			} else {

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/saveSystemUser.htm", method = RequestMethod.POST)
	public String saveSystemUser(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = systemUserService.saveSystemUser(SystemUserHelper
					.buildSystemUserEntity(content));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "保存成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "保存失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "保存异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/updateSystemUser.htm", method = RequestMethod.POST)
	public String updateSystemUser(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = systemUserService.updateSystemUser(SystemUserHelper
					.buildSystemUserEntity(content));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "更新成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "更新失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "更新异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/batchRemove.htm", method = RequestMethod.POST)
	public String batchRemove(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String systemUserIdStr = map.get("systemUserIds").toString();
			boolean flag = systemUserService.deleteSystemUsers(systemUserIdStr
					.split(","));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "删除成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "删除失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "删除异常");
			logger.error(e);
		}
		return null;
	}
	
	@RequestMapping(value = "/getOne.htm", method = RequestMethod.POST)
	public String getOne(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String id = map.get("id").toString();
			SystemUserEntity res = this.systemUserService.getOneById(id);
			if (res != null && !res.getId().equals("")) {
				JSONObject js = JSONObject.fromObject(res);
				AjaxUtils.ajaxJsonSuccessMessage(response, js.toString());
			} else {

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询一条记录异常");
			logger.error(e);
		}
		return null;
	}
	
	@RequestMapping(value = "/changeStatus.htm", method = RequestMethod.POST)
	public String changeStatus(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String id = map.get("id").toString();
			String userStatus = map.get("userStatus").toString();
			boolean flag = this.systemUserService.changeStatus(id,userStatus);
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "修改数据状态成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "修改数据状态失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询一条记录异常");
			logger.error(e);
		}
		return null;
	}
	
}
