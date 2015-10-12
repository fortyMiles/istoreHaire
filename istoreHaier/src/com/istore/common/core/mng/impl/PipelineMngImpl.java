package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Pipeline;
import com.istore.common.core.dao.PipelineDao;
import com.istore.common.core.mng.PipelineMng;
@Service
@Transactional
public class PipelineMngImpl implements PipelineMng {
	@Autowired
	PipelineDao pipelinedao;
	public int modPipelineStatus(String pipelineId, String status,
			String ordersid, String storeId,String comment) {
		return pipelinedao.modPipelineStatus(pipelineId, status, ordersid, storeId,comment);
	}

	public List<Pipeline> pipeLineViewList(String userid, String pipelineId,
			String ordersId, Pager pager, String storeId) {
		return pipelinedao.pipeLineViewList(userid, pipelineId, ordersId, pager, storeId);
	}

	public Pipeline pipeLineViewListCount(String userid, String pipelineId,
			String ordersId, String storeId) {
		return pipelinedao.pipeLineViewListCount(userid, pipelineId, ordersId, storeId);
	}

}
