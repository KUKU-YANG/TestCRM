package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.SaleVisitDao;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	private SaleVisitDao saleVisitDao;

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// ��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		// ��װÿҳ��ʾ��¼��
		pageBean.setPageSize(pageSize);
		// ��װ�ܼ�¼��
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ��װ��ҳ��
		Double tc = totalCount.doubleValue();
		Double totalPage = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		// ��װÿҳ��ʾ���ݵļ���
		// �ӵڼ�����ʼ
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) throws Exception {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public SaleVisit findById(String visit_id) throws Exception {
		SaleVisit saleVisit = saleVisitDao.findById(visit_id);
		return saleVisit;
	}

	@Override
	public void update(SaleVisit saleVisit) throws Exception {
		saleVisitDao.update(saleVisit);
	}

	@Override
	public void delete(SaleVisit saleVisit) throws Exception {
		saleVisitDao.delete(saleVisit);
	}

}
