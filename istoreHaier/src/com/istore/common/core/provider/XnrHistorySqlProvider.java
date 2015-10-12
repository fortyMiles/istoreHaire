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
 * @ClassName: XnrHistorySqlProvider.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:03:47
 */
public class XnrHistorySqlProvider {

	/**
	 * 获得新闻历史记录
	 * 
	 * @param parameters
	 * @return
	 */
	public String getXnrHistoryByLastupdate(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XNRHISTORY_ID, XNR_ID, CHECKER, LASTUPDATE, STATUS, XCOMMENT")
				.FROM("XNRHISTORY")
				.WHERE("TO_CHAR(LASTUPDATE, 'YYYY-MM-DD HH24:MI:SS') = SUBSTR('"
						+ parameters.get("lastupdate") + "', 0, 19)");
		return sql.toString();
	}
}
