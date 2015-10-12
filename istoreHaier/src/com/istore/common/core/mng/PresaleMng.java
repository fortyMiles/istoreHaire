package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.Predictdatesale;

public interface PresaleMng {
	public Integer findListCount(String userid,String beginDate,String endDate);
	public List<Predictdatesale> findReport(String userid , String beginDate,String endDate,Integer startNum,Integer endNum);
	public List getColumnData(String beginDate,String endDate);
}
