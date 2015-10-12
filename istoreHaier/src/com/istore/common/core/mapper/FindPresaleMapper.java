package com.istore.common.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import com.istore.common.core.bean.Predictdatesale;
import com.istore.common.core.provider.PreSaleSqlProvider;

public interface FindPresaleMapper {
	
	@SelectProvider(type = PreSaleSqlProvider.class, method = "findListCount")
	@Results(value = { 
			@Result(column = "listcount", property = "count")
	})
	public Predictdatesale findListCount(@Param("userid") String userid ,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	
	
	
	@SelectProvider(type = PreSaleSqlProvider.class, method = "findReport")
	@Results(value = { 
			@Result(column = "material", property = "material"),
			@Result(column = "userid", property = "userid"),
			@Result(column = "date1", property = "date1"),
			@Result(column = "date1sale", property = "date1sale"),
			@Result(column = "date2", property = "date2"),
			@Result(column = "date2sale", property = "date2sale"),
			@Result(column = "date3", property = "date3"),
			@Result(column = "date3sale", property = "date3sale")
	})
	public List<Predictdatesale> findReport(@Param("userid") String userid ,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("startNum") Integer startNum,@Param("endNum") Integer endNum);
 
}
