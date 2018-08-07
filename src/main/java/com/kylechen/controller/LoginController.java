//package com.kylechen.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.kylechen.model.request.LoginRequest;
//import com.kylechen.model.utils.Dict;
//import com.kylechen.mybatis.mapper.UserInfoMapper;
//import com.kylechen.mybatis.model.UserInfo;
//import com.kylechen.mybatis.model.UserInfoExample;
///**
// * @author chenzhiwei
// * LoginController.java
// *	2018年8月1日
// *	登陆实现
// */
//@Controller
//@RequestMapping("/app")
//public class LoginController  extends BaseController{
//	@Resource 
//	private UserInfoMapper userInfoMapper;
//
////	@RequestMapping(value="/login")
////	@ResponseBody
////	public Map login(@RequestBody LoginRequest request) {
////		Map<String,String> map=new HashMap<String,String>();
////		HttpSession session = getSession();
////		try {
////			UserInfoExample userInfoExmple=new UserInfoExample();
////			userInfoExmple.createCriteria().andPhoneEqualTo(request.getUsername()).andUserpassEqualTo(request.getUserpass());
////			List<UserInfo> userInfoList=userInfoMapper.selectByExample(userInfoExmple);
////			if (userInfoList.size() != 0) {
////				UserInfo userInfo = userInfoList.get(0);
////				map.put(Dict.MAP_KEY, Dict.KEY_SUCCUSS);
////				map.put(Dict.MAP_MSG, userInfo.getUsertype());
////				session.setAttribute("loginKey", userInfo);
////			}else {
////				map.put(Dict.MAP_KEY, Dict.KEY_FAIL);
////				map.put(Dict.MAP_MSG, "用户名或者密码错误！");
////			}
////		} catch (Exception e) {
////			map.put(Dict.MAP_KEY, Dict.KEY_FAIL);
////			map.put(Dict.MAP_MSG, "系统错误："+e.toString());
////		}
////		
////		return map;
////	}
//	
//	
//
//}
