package com.kylechen.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kylechen.model.utils.RequestData;

/*
 * 所有的controller必须继承BaseController
 *@author kylechen 
 * 2018年3月20日
 */
public class BaseController {
	
	/** new RequestData对象
	 * @return
	 */
	public BaseController(){
	}
	public RequestData getRequestData(){
		return new RequestData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	

		
	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}
