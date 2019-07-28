package com.crm.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.crm.service.JfreeChartService;
import com.opensymphony.xwork2.ActionSupport;

public class JfreeChartAction extends ActionSupport {
	private JfreeChartService jfreeChartService;

	public void setJfreeChartService(JfreeChartService jfreeChartService) {
		this.jfreeChartService = jfreeChartService;
	}

	public String jfreeChart() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String flag = request.getParameter("flag");
		String url;
		if (flag.equals("industry")) {
			url = jfreeChartService.getUrl("industry");
			request.setAttribute("url", url);
			return "industryJfreeChart";
		} else {
			url = jfreeChartService.getUrl("source");
			request.setAttribute("url", url);
			return "sourceJfreeChart";
		}
	}

}
