package com.taxation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.service.IShoppingTrolleyService;
import com.taxation.helper.ShoppingTrolleyHelper;
import com.taxation.web.util.AjaxUtils;

@Controller
@RequestMapping("shoppingTrolley")
public class ShoppingTrolleyController {
	private static Logger logger = Logger
			.getLogger(ShoppingTrolleyController.class);

	@Autowired
	private IShoppingTrolleyService shoppingTrolleyService;

	@RequestMapping(value = "/addToCart.htm", method = RequestMethod.POST)
	public String saveSystemUser(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = shoppingTrolleyService
					.addServiceToMyShoppingTrolley(ShoppingTrolleyHelper
							.buildShoppingTrolleyEntity(content));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "添加购物车成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "添加购物车失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加购物车异常");
			logger.error(e);
		}
		return null;
	}
}
