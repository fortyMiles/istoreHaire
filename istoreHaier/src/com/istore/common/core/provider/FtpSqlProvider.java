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
 * @author mojilin
 * @time 2014-7-15下午1:26:01
 */
public class FtpSqlProvider {

	/**
	 * 获得FTP列表
	 * 
	 * @param parameters
	 * @return
	 */
	public String getFtpListByType(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("ID, HOST, PORT, USERNAME, PASSWORD, XPATH, TYPE")
				.FROM("W_FTP").WHERE("TYPE = " + parameters.get("type"));
		return sql.toString();
	}
}
