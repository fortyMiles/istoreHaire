package com.istore.common.core.provider;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.istore.common.core.bean.Pager;

public class PipelineProvider {
	public String pipeLineViewListSQL(Map<String, Object> parameters){
		String userid = parameters.get("userid")==null?"":parameters.get("userid").toString();
		String pipeline_id = parameters.get("pipeline_id")==null?"":parameters.get("pipeline_id").toString();
		String orders_id = parameters.get("orders_id")==null?"":parameters.get("orders_id").toString();
		String store_id = parameters.get("store_id")==null?"":parameters.get("store_id").toString();
		Pager pager = (Pager) parameters.get("pager");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select tt.*,rownum as oracle_row  from (  select h.popeline_id,h.status,h.name,h.short_desc,h.long_desc,s.name as addressname,to_char(h.last_update,'yyyy-mm-dd hh24:mi:ss') as last_update ");
		sql.append("  ,to_char(h.submit_date,'yyyy-mm-dd hh24:mi:ss') as submit_date,h.orders_id,to_char(h.expected_date,'yyyy-mm-dd hh24:mi:ss') expected_date,h.confidence ,F_GET_HAIER_PIPELINECOMMENT(H.popeline_id) AS XCOMMENT,s.phone,s.email,h.project_type,h.project_area,h.location,h.products_request,h.draft_information,h.contact_title from xhaierpipeline h LEFT JOIN xhaieraddress s ON h.userid = s.member_id  where 1 = 1    ");
		if (StringUtils.isNotEmpty(userid)) {
			sql.append(" and h.userid = '"+userid+"'  ");
		}

		if (StringUtils.isNotEmpty(pipeline_id)) {
			sql.append(" and h.popeline_id = '"+pipeline_id+"' ");
		}  
		if (StringUtils.isNotEmpty(orders_id)) {
			sql.append(" and h.orders_id = '"+orders_id+"' ");
		}  
		sql.append(" and h.store_id = '"+store_id+"'");
		sql.append(" order by h.submit_date desc ) tt ) where oracle_row > "+pager.getStartNum()+" and oracle_row <=  "+ pager.getEndNum());
		return sql.toString();
	}
	
	public String ListCountSQL(Map<String, Object> parameters){
		
		String userid = parameters.get("userid")==null?"":parameters.get("userid").toString();
		String pipeline_id = parameters.get("pipeline_id")==null?"":parameters.get("pipeline_id").toString();
		String orders_id = parameters.get("orders_id")==null?"":parameters.get("orders_id").toString();
		String store_id = parameters.get("store_id")==null?"":parameters.get("store_id").toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("   select count(*) as allamount  from xhaierpipeline h where 1=1 ");
		sql.append(" and h.store_id = '"+store_id+"'");
		if (StringUtils.isNotEmpty(userid)) {
			sql.append(" and userid = '"+userid+"' ");
		} 

		if (StringUtils.isNotEmpty(pipeline_id)) {
			sql.append(" and h.popeline_id = '"+pipeline_id+"' ");
			 
		}  
		if (StringUtils.isNotEmpty(orders_id)) {
			sql.append(" and h.orders_id = '"+orders_id+"' ");
			 
		} 
		return sql.toString();
	}
	
	public  String modPipelineStatus(Map<String, Object> parameters){
		String status = parameters.get("status")+"";
		String pipeline_id = parameters.get("pipeline_id")+"";
		String ordersid = parameters.get("ordersid")+"";
		String store_id = parameters.get("store_id")+"";
		String sql = "";
		if (status.equals("O")) {
			sql = " update xhaierpipeline set status = '"+status+"' ,orders_id = '"+ordersid+"' WHERE POPELINE_ID = '"+pipeline_id+"'  and store_id = '"+store_id+"'";
		} else {
			if (status.equals("N")) {
				sql = " update xhaierpipeline set status = '"+status+"' ,N_TIME = SYSDATE WHERE POPELINE_ID = '"+pipeline_id+"'  ";
			} else {
				sql = " update xhaierpipeline set status =  '"+status+"'  WHERE POPELINE_ID = '"+pipeline_id+"'  ";
			}
		}
		return sql;
	}
	
	public String addPipelineCommentSQL(Map<String, Object> parameters){
		String pipeline_id = parameters.get("pipeline_id")+"";
		String content = parameters.get("comment")+"";
		String sql = " insert into  xhaierpipelinecomment (pl_comment_id,pipe_line_id,content,last_update) values(SEQ_PIPELINE_COMMENT_ID.Nextval,'"+pipeline_id+"','"+content+"',sysdate) ";
		return sql;
	}
}
