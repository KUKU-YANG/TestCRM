package com.crm.dao;

import java.util.List;

import com.crm.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {
	List<BaseDict> findByTypeCode(String dict_type_code) throws Exception;
}
