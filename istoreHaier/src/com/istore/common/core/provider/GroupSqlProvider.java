/**
 * @Project istoreHaier
 * @Package com.istore.common.core.provider
 * @Title FtpSqlProvider.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName: FtpSqlProvider.java
 * @Description: TODO
 * @author Gao Minquan
 * @time 2014-7-15下午1:26:01
 */
public class GroupSqlProvider {

	/**
	 * 获得GroupName
	 * 
	 * @param parameters
	 * @return
	 */
	
	//Added By Gaominquan 2014-7-26
	public String insertGroupByName() {
		String MAX_SELECT = "(select max(xGROUP_ID)+1 from xGROUP)";
		
		String SQL =  new SQL().INSERT_INTO("xGROUP")
			.VALUES("xGROUP_ID",MAX_SELECT)
			.VALUES("xGROUP_NAME", "#{group.xgroup_name}")
			.VALUES("DESCRIPTION", "#{group.description}")
			.VALUES("STORE_ID", "#{group.store_id}").toString();
		
		return SQL;
	}
	
	public String deleteGroupByID(Map<String,Object> parameters){
		String name = parameters.get("groupID").toString();
		
		String TABLE_NAME = "XGROUP";
		String GROUP_ID = "xGROUP_ID";
		
		String SQL = "delete from "+TABLE_NAME+" where "+GROUP_ID+"=="+name;
		
		return SQL;
	}
	
	//Added By Gaominquan End.
	
}
