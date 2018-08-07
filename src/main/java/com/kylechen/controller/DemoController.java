package com.kylechen.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
//	@RequestMapping("/redis")
//	public String redis() {
//		Map<String,String> responseMap=new HashMap<String,String>();
//		
//		int currentPage=request.getCurrentPage();
//		int count=list.size();
//		//分页处理完成的数据
//		responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
//		//当前 页
//		responseMap.put("currentPage",currentPage);
//		//总数
//		responseMap.put("sumCount", count);
//		//总页数
//		responseMap.put("sumPage", PageUtils.getSumPage(list));
//		responseMap.put("status", Dict.STATUS_00);
//		return "Hello world!";
//	}
	

    @RequestMapping("/mq")
	public String mq() {
    	
		return "Hello world!";
	}

	

}
