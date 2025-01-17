package com.crm.dao.impl;

import java.util.List;

import com.crm.dao.BaseDictDao;
import com.crm.domain.BaseDict;
import com.crm.domain.Customer;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	public BaseDictDaoImpl() {
		super(BaseDict.class);
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) throws Exception {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?",
				dict_type_code);
		return list;
	}

}
