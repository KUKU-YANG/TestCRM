package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.LinkManDao;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService {
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		Double tc = totalCount.doubleValue();
		Double totalPage = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		// 封装每页显示数据的集合
		// 从第几个开始
		Integer begin = (currPage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) throws Exception {
		linkManDao.save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) throws Exception {
		LinkMan linkMan = linkManDao.findById(lkm_id);
		return linkMan;
	}

	@Override
	public void update(LinkMan linkMan) throws Exception {
		linkManDao.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) throws Exception {
		linkManDao.delete(linkMan);
	}

}
