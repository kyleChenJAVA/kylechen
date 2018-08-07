package com.kylechen.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kylechen.model.utils.Dict;
import com.kylechen.model.utils.Freemarker;
import com.kylechen.model.utils.TableMain;
import com.kylechen.model.utils.TableModel;

import freemarker.template.Configuration;

/**
 * @author chenzhiwei 文件名：CodeController.java 时间：2018年8月1日 备注：
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

	@Resource
	Configuration cfg;

	// 新增表字段
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addColumn", method = RequestMethod.POST)
	@ResponseBody
	public Map addColumn(@RequestBody TableModel request) {
		System.out.println(this.getClass() + "请求参数：" + request.toString());
		Map responseMap = new HashMap();
		HttpSession session = getSession();
		List<TableModel> list = new ArrayList<TableModel>();
		try {
			//所有字段转小写
			request.setColumnName(request.getColumnName().toLowerCase());
			if (null != session.getAttribute("modelList")) {
				list = (List<TableModel>) session.getAttribute("modelList");
				for (TableModel obj : list) {
					if (obj.getColumnName().equals(request.getColumnName())) {
						responseMap.put("list", list);
						responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
						responseMap.put(Dict.MAP_MSG, "该字段已存在：" + request.getColumnName());
						return responseMap;
					}
				}
				list.add(request);
			} else {
				// 首次增加字段自动建主键
				TableModel obj = new TableModel();
				obj.setColumnLength("32");
				obj.setColumnMemo("主键");
				obj.setColumnType("VARCHAR");
				obj.setColumnName("id");
				list.add(obj);
				list.add(request);
			}
			session.setAttribute("modelList", list);
			responseMap.put("list", list);
			responseMap.put("status", Dict.KEY_SUCCUSS);
			System.out.println(this.getClass() + "返回参数：" + JSON.toJSONString(responseMap));

		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
		}
		return responseMap;
	}

	// 清除字段
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/delOne", method = RequestMethod.POST)
	@ResponseBody
	public Map delOne(@RequestBody TableModel request) {
		System.out.println(this.getClass() + "请求参数：" + request.toString());
		Map responseMap = new HashMap();
		List<TableModel> list = new ArrayList<TableModel>();
		List<TableModel> newlist = new ArrayList<TableModel>();
		HttpSession session = getSession();
		try {
			if (null != session.getAttribute("modelList")) {
				list = (List<TableModel>) session.getAttribute("modelList");
				for (TableModel obj : list) {
					if (!obj.getColumnName().equals(request.getColumnName())) {
						newlist.add(obj);
					}
				}
			}
			session.setAttribute("modelList", newlist);
			responseMap.put("list", newlist);
			responseMap.put("status", Dict.KEY_SUCCUSS);
			System.out.println(this.getClass() + "返回参数：" + JSON.toJSONString(responseMap));
		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
		}
		return responseMap;
	}

	// 清除字段
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/delAll", method = RequestMethod.POST)
	@ResponseBody
	public Map delAll() {
		System.out.println(this.getClass() + "清除字段操作！");
		Map responseMap = new HashMap();
		HttpSession session = getSession();
		try {
			session.removeAttribute("modelList");
			responseMap.put("list", null);
			responseMap.put("status", Dict.KEY_SUCCUSS);
			System.out.println(this.getClass() + "返回参数：" + JSON.toJSONString(responseMap));
		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
		}
		return responseMap;
	}

	// 生成代码模型
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map createCode(@RequestBody TableMain obj) {
		System.out.println(this.getClass() + "请求参数：");
		Map responseMap = new HashMap();
		try {
			HttpSession session = getSession();
			List<TableModel> list = (List<TableModel>) session.getAttribute("modelList");
			// 模版变量
			Map root = new HashMap();
			root.put("tableModel", obj.getTableModel());
			// 表结构字段
			root.put("model", list);
			// 包名
			root.put("packageName", obj.getPackageName());
			// 表名
			root.put("tableName", obj.getTableName());
			// 表注释
			root.put("tableMemo", obj.getTableColumn());
			// 输出文件名（首字母大写）
			String outFileName = obj.getTableModel().substring(0, 1).toUpperCase() + obj.getTableModel().substring(1);;
			// 输出文件路径
			String filePath = obj.getOutPath();
			//"/Users/chenzhiwei/Desktop/ftl/";
			// ftl路径
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			String ftlPath = path.getAbsolutePath() + "/static/ftl/";
			// *-edit.html文件（单表增，改）
			Freemarker.printFile("edit.ftl", root, outFileName + "-edit.html", filePath, ftlPath);
			// *-list.html文件（单表查，删）
			Freemarker.printFile("list.ftl", root, outFileName + "-list.html", filePath, ftlPath);
			// 建表sql文件
			Freemarker.printFile("sql.ftl", root, outFileName + ".sql", filePath, ftlPath);
			// controller.java文件（暂时不包含mybatis文件）
			Freemarker.printFile("java.ftl", root, outFileName + "Controller.java", filePath, ftlPath);
			System.out.println(this.getClass() + "返回参数：" + JSON.toJSONString(responseMap));
		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
		}
		return responseMap;
	}

}
