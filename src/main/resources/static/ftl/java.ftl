package ${packageName}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import ${packageName}.model.request.SearchRequest;
import ${packageName}.model.utils.Dict;
import ${packageName}.model.utils.PageUtils;
import ${packageName}.model.utils.UuidUtil;
import ${packageName}.mybatis.mapper.${tableModel}Mapper;
import ${packageName}.mybatis.model.${tableModel};
import ${packageName}.mybatis.model.${tableModel}Example;

/**
 * @author chenzhiwei
 * 文件名：${tableModel}Controller.java
 * 时间：2018年8月1日
 * 备注：
 */
@Controller
@RequestMapping("/${tableModel}")
public class ${tableModel}Controller  extends BaseController {
	@Resource
	private ${tableModel}Mapper mapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map query(@RequestBody SearchRequest request) {
		System.out.println(this.getClass()+"请求参数："+request.toString());
		Map responseMap=new HashMap();
		try {
			${tableModel}Example example=new ${tableModel}Example();			     
			if (request==null) {
				example.createCriteria().andIdIsNotNull();
			} else {
				example.createCriteria().andIdIsNotNull();
			}
			List<${tableModel}>  list=mapper.selectByExample(example);
			int currentPage=request.getCurrentPage();
			int count=list.size();
			//分页处理完成的数据
			responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
			//当前 页
			responseMap.put("currentPage",currentPage);
			//总数
			responseMap.put("sumCount", count);
			//总页数
			responseMap.put("sumPage", PageUtils.getSumPage(list));
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    		System.out.println(this.getClass()+"返回参数："+JSON.toJSONString(responseMap));

		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}		
		return responseMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/selectOne", method=RequestMethod.POST)
	@ResponseBody
	public Map selectOne(@RequestBody String str) {
		System.out.println(this.getClass()+"请求参数："+str);
		Map responseMap=new HashMap();
		try {
			${tableModel} obj=mapper.selectByPrimaryKey(str);
			responseMap.put("conf",obj);	
    		System.out.println(this.getClass()+"返回参数："+JSON.toJSONString(responseMap));
		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}		
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map save(@RequestBody ${tableModel} request) {
		System.out.println(this.getClass()+"请求参数："+request.toString());

    	Map responseMap=new HashMap();
    	try {
    		if(StringUtils.isEmpty(request.getId())) {
    			request.setId(UuidUtil.get32UUID());
    			mapper.insert(request);
    		}else {
    			mapper.updateByPrimaryKey(request);
    		}
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    		System.out.println(this.getClass()+"返回参数："+JSON.toJSONString(responseMap));

    	}catch (Exception e) {
    		responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}
    	
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Map del(@RequestBody ${tableModel} request) {
		System.out.println(this.getClass()+"请求参数："+request.toString());

    	Map responseMap=new HashMap();
    	try {
    		mapper.deleteByPrimaryKey(request.getId());
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    	}catch (Exception e) {
    		responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}
		System.out.println(this.getClass()+"返回参数："+JSON.toJSONString(responseMap));

		return responseMap;
	}
}
