package com.crm.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.BaseDict;
import com.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
	private BaseDict baseDict = new BaseDict();

	@Override
	public BaseDict getModel() {
		return baseDict;
	}

	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	/**
	 * 根据类型名称查询字典
	 * 
	 * @throws Exception
	 */
	public String findByTypeCode() throws Exception {
		System.out.println("根据类型名称查询字典");
		System.out.println(baseDict);
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		/**
		 * JSONArray :将数组或list集合转成json JSONObject ：将对象或者map集合转成json JsonConfig
		 * ：转json的配置对象
		 */
		// 筛除不需要的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "dict_sort", "dict_enable", "dict_memo" });
		// 将list转成JSON
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		// 将json定义到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
