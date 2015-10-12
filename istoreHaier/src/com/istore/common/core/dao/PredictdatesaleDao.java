package com.istore.common.core.dao;

import java.util.List;

import com.istore.common.core.bean.Predictdatesale;

public interface PredictdatesaleDao {
	public List<Predictdatesale> findReport(String userid , String beginDate,String endDate,Integer startNum,Integer endNum);
	public Predictdatesale findListCount(String userid , String beginDate,String endDate);
	public List<Object> getColumnData(String beginDate,String endDate);
	
}
