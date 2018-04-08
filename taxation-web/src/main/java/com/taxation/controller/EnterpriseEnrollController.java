package com.taxation.controller;

import com.taxation.api.service.IEnterpriseEnrollService;
import com.taxation.bean.TempFileEntity;
import com.taxation.helper.EnterpriseEnrollHelper;
import com.taxation.web.util.AjaxUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("enterpriseEnroll")
public class EnterpriseEnrollController {
	private static Logger logger = Logger.getLogger(EnterpriseEnrollController.class);
	@Autowired
	private IEnterpriseEnrollService enterpriseEnrollService;

	@RequestMapping(value = "/saveEnterpriseEnrollInfo.htm", method = RequestMethod.POST)
	public String queryProvider(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = enterpriseEnrollService.saveEnterpriseEnrollInfo(EnterpriseEnrollHelper.buildEnterpriseEnrollEntity(content));
			if (flag){
				AjaxUtils.ajaxJsonSuccessMessage(response, "公司申请注册成功");
			}else{
				AjaxUtils.ajaxJsonErrorMessage(response, "公司申请注册失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "公司注册异常");
			logger.error(e);
		}
		return null;
	}
	@RequestMapping(value="/saveFiles.htm", method=RequestMethod.POST    )
	public void ngUpload(HttpServletRequest request, HttpServletResponse res){
		//接收参数
		String providerId = request.getParameter("providerId");

		//解析器解析request的上下文
		CommonsMultipartResolver multipartResolver =
				new CommonsMultipartResolver(request.getSession().getServletContext());
		//先判断request中是否包涵multipart类型的数据，
		if(multipartResolver.isMultipart(request)){
			//再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//这里的name为fileItem的alias属性值，相当于form表单中name
				String name=(String)iter.next();
				System.out.println("name:"+name);
				//根据name值拿取文件
				MultipartFile file = multiRequest.getFile(name);
				if(file != null){
					String originalFileName = file.getOriginalFilename();
					originalFileName = originalFileName.substring(originalFileName.lastIndexOf("\\")+1);

					//获取扩展
					String extName = originalFileName.substring(originalFileName.lastIndexOf("."));//.jpg
					//UUID
					String uuid = UUID.randomUUID().toString().replace("-", "");
					//新名称
					String newName = uuid+extName;
					String filePath =  request.getSession().getServletContext().getRealPath("/")+"upload/enclosure/";
					File serverFile = new File(filePath+ newName);
					if(!serverFile.getParentFile().exists()) {
						//如果目标文件所在的目录不存在，则创建父目录
						serverFile.getParentFile().mkdirs();
					}
					try {
						//1.上传文件
						file.transferTo(serverFile);
						//2.保存文件信息
						TempFileEntity record = new TempFileEntity();
						record.setProviderId(providerId);
						record.setCurrentFileName(newName);
						record.setOriginalFileName(originalFileName);
						record.setFilePath(filePath);
						enterpriseEnrollService.insertTempFile(record);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}


}

