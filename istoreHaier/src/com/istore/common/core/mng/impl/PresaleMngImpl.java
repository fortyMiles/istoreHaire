package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.Predictdatesale;
import com.istore.common.core.dao.PredictdatesaleDao;
import com.istore.common.core.mng.PresaleMng;

@Service
@Transactional
public class PresaleMngImpl implements PresaleMng {
	@Autowired
	PredictdatesaleDao dao;
	
	public Integer findListCount(String userid, String beginDate, String endDate) {
		if(userid != null && beginDate != null && endDate != null ){
			Predictdatesale psale =  dao.findListCount(userid, beginDate, endDate);
			return psale.getCount();
		}
		
		return null;
	}

	public List<Predictdatesale> findReport(String userid, String beginDate, String endDate,
			Integer startNum, Integer endNum) {
		List<Predictdatesale> list = null;
		if(userid != null && beginDate != null && endDate != null ){
		   list = dao.findReport(userid, beginDate, endDate, startNum, endNum);
		}
		return list;
	}

	public List<Object> getColumnData(String beginDate, String endDate) {
		List<Object> list = null;
		if(beginDate != null && endDate != null ){
			list = dao.getColumnData(beginDate, endDate);
		}
		return list;
	}

}
