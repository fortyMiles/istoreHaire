/**
 * @Project istoreHaier
 * @Package com.istore.common.core.provider
 * @Title ChannelSqlProvider.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName: ChannelSqlProvider.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:03:47
 */
public class ChannelSqlProvider {

	/**
	 * 获得频道数量
	 * 
	 * @return
	 */
	public String getChannelListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE")
				.FROM("XCHANNEL")
				.WHERE("MARKFORDELETE = 0 AND STORE_ID = "
						+ parameters.get("store_id"));
		return sql.toString();
	}

	/**
	 * 获得当前编辑频道
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditChannelByID(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE")
				.FROM("XCHANNEL")
				.WHERE("XCHANNEL_ID = " + parameters.get("xchannel_id"));
		return sql.toString();
	}

	/**
	 * 获得频道搜索数量
	 * 
	 * @return
	 */
	public String getSearchChannelListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE")
				.FROM("XCHANNEL")
				.WHERE("MARKFORDELETE = 0 AND STORE_ID = "
						+ parameters.get("store_id")
						+ " AND XCHANNEL_NAME LIKE '%"
						+ parameters.get("xchannel_name")
						+ "%' AND TYPE LIKE '%" + parameters.get("type") + "%'");
		return sql.toString();
	}

	/**
	 * 获得频道搜索数量
	 * 
	 * @return
	 */
	public String getChannelNameListByID(Map<String, Object> parameters) {
		SQL sql = new SQL().SELECT("XCHANNEL_NAME").FROM("XCHANNEL")
				.WHERE("XCHANNEL_ID = " + parameters.get("xchannel_id"));
		return sql.toString();
	}
}
