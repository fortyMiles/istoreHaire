package com.istore.common.core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Predictdatesale;
import com.istore.common.core.dao.PredictdatesaleDao;
import com.istore.common.core.mapper.FindPresaleMapper;
import com.istore.common.core.provider.PreSaleSqlProvider;
import com.istore.common.core.util.DateUtils;

@Repository
public class PredictdatesaleDaoImpl implements PredictdatesaleDao {
	@Autowired
	private  FindPresaleMapper findPresaleMapper;
	
	public Predictdatesale findListCount(String userid, String beginDate, String endDate) {
		Predictdatesale pre = new Predictdatesale();
		if(userid != null && beginDate != null && endDate != null){
			pre = findPresaleMapper.findListCount(userid,beginDate,endDate);
		}
		return pre;
	}

	public List<Predictdatesale> findReport(String userid, String beginDate, String endDate,
			Integer startNum, Integer endNum) {
		List<Predictdatesale> list = new ArrayList<Predictdatesale>();
		if(userid != null && beginDate != null && endDate != null){
			list = findPresaleMapper.findReport(userid, beginDate, endDate, startNum, endNum);
		}
		return list;
	}

	public List<Object> getColumnData(String beginDate, String endDate) {
		List<Object> list = new ArrayList<Object>();
		int i =  new PreSaleSqlProvider().getNum(beginDate, endDate);
		String dateStr = "";
		for (int j = 0; j < i; j++) {
			dateStr = DateUtils.getDateStr(DateUtils.getDateAddMonth(DateUtils.convertStrToDate(beginDate, "yyyy-MM"), j),"yyyy-MM");
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("ColumnDate", dateStr);
			list.add(map);
		}
		return list;
	}

}
