package com.kylechen.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzhiwei
 * 文件名：Test.java
 * 时间：2018年8月1日
 * 备注：
 */
@Controller
@RequestMapping("/file")
public class UploadController extends BaseController {
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/upload",produces = { "application/json" })
	@ResponseBody//登陆方法ß
	public Map upload(@RequestParam(value = "file" , required = true) MultipartFile file) {
		System.out.println(file.getName());
//		String path=UploadFile.uploadPath(file);
//		try {
//			List list=ExcelUtil.readXlsx(path);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	}



}