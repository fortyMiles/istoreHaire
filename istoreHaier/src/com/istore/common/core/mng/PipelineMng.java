package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Pipeline;

public interface PipelineMng {
	  public List<Pipeline> pipeLineViewList(String userid, String pipeline_id,String orders_id, Pager pager,String store_id);
	  public Pipeline pipeLineViewListCount(String userid, String pipeline_id,String orders_id,String store_id);
	  public int modPipelineStatus(String pipeline_id, String status,String ordersid,String store_id,String comment);
}
