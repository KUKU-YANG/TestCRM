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
	 * �����������Ʋ�ѯ�ֵ�
	 * 
	 * @throws Exception
	 */
	public String findByTypeCode() throws Exception {
		System.out.println("�����������Ʋ�ѯ�ֵ�");
		System.out.println(baseDict);
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		/**
		 * JSONArray :�������list����ת��json JSONObject �����������map����ת��json JsonConfig
		 * ��תjson�����ö���
		 */
		// ɸ������Ҫ������
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "dict_sort", "dict_enable", "dict_memo" });
		// ��listת��JSON
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		// ��json���嵽ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
