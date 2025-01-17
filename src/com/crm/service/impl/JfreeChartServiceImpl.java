package com.crm.service.impl;

import java.awt.Font;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;

import com.crm.dao.JfreeChartDao;
import com.crm.service.JfreeChartService;
import com.crm.utils.JfreeChartUtils;

public class JfreeChartServiceImpl implements JfreeChartService {
	private JfreeChartDao jfreeChartDao;

	public void setJfreeChartDao(JfreeChartDao jfreeChartDao) {
		this.jfreeChartDao = jfreeChartDao;
	}

	@Override
	public String getUrl(String str) throws Exception {
		List<Integer> count;
		List<String> name;
		String title;
		Integer total;

		DefaultPieDataset dpd = new DefaultPieDataset();
		if (str.equals("source")) {
			title = "客户来源";
			count = jfreeChartDao.findSourceCount();
			name = jfreeChartDao.findSourceName();
			total = jfreeChartDao.findSourceTotal();
		} else {
			title = "客户行业";
			count = jfreeChartDao.findIndustryCount();
			name = jfreeChartDao.findIndustryName();
			total = jfreeChartDao.findIndustryTotal();
		}

		for (Integer i = 0; i < total; i++) {
			dpd.setValue(name.get(i), count.get(i));
		}

		String url = JfreeChartUtils.getJfreeChart(title, dpd);
		return url;
	}

}
