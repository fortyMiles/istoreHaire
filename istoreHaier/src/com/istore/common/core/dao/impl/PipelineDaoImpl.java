package com.istore.common.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Pipeline;
import com.istore.common.core.dao.PipelineDao;
import com.istore.common.core.mapper.FindPipelineMapper;

@Repository
public class PipelineDaoImpl implements PipelineDao {
	@Autowired 
	private FindPipelineMapper findpipelinemapper;
	
	
	public int modPipelineStatus(String pipelineId, String status,
			String ordersid,String store_id,String comment) {
		findpipelinemapper.modPipelineStatus(pipelineId, status, ordersid, store_id);
		findpipelinemapper.addPipelineComment(pipelineId, comment);
		return 1;
	}

	public List<Pipeline> pipeLineViewList(String userid, String pipelineId,
			String ordersId, Pager pager,String store_id) {
		
		return findpipelinemapper.pipeLineViewList(userid, pipelineId, ordersId, pager, store_id);
	}

	public Pipeline pipeLineViewListCount(String userid, String pipelineId,
			String ordersId,String store_id) {
		
		return findpipelinemapper.findListCount(userid, pipelineId, ordersId, store_id);
	}

}
