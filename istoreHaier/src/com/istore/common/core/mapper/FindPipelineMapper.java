package com.istore.common.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;


import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Pipeline;
import com.istore.common.core.provider.PipelineProvider;

public interface FindPipelineMapper {
	@SelectProvider(type = PipelineProvider.class, method = "ListCountSQL")
	@Results(value = { 
			@Result(column = "allamount", property = "count")
	})
	public Pipeline findListCount(@Param("userid")String userid, @Param("pipeline_id")String pipeline_id, @Param("orders_id")String orders_id,@Param("store_id")String store_id);
	
	@SelectProvider(type = PipelineProvider.class, method = "pipeLineViewListSQL")
	@Results(value = { 
			@Result(column = "popeline_id", property = "popeline_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "name", property = "name"),
			@Result(column = "short_name", property = "short_name"),
			@Result(column = "long_desc", property = "long_desc"),
			@Result(column = "address_name", property = "address_name"),
			@Result(column = "last_update", property = "last_update"),
			@Result(column = "submit_date", property = "submit_date"),
			@Result(column = "orders_id", property = "orders_id"),
			@Result(column = "expected_date", property = "expected_date"),
			@Result(column = "confidence", property = "confidence"),
			@Result(column = "xcomment", property = "xcomment"),
			@Result(column = "phone", property = "phone"),
			@Result(column = "email", property = "email"),
			@Result(column = "project_type", property = "project_type"),
			@Result(column = "project_area", property = "project_area"),
			@Result(column = "location", property = "location"),
			@Result(column = "products_request", property = "products_request"),
			@Result(column = "draft_information", property = "draft_information"),
			@Result(column = "contact_title", property = "contact_title")
	})
	public List<Pipeline> pipeLineViewList(@Param("userid")String userid, @Param("pipeline_id")String pipeline_id,@Param("orders_id")String orders_id,@Param("pager") Pager pager,@Param("store_id")String store_id);
	
	@UpdateProvider(type = PipelineProvider.class,method = "modPipelineStatus")
	public int modPipelineStatus(@Param("pipeline_id")String pipeline_id,@Param("status") String status,@Param("ordersid")String ordersid,@Param("store_id")String store_id);
	
	@InsertProvider(type = PipelineProvider.class,method = "addPipelineCommentSQL")
	public int addPipelineComment(@Param("pipeline_id")String pipeline_id,@Param("comment")String comment);
}
