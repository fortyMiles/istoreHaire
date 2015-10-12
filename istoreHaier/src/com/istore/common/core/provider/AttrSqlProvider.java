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
 *
 * @time 2014-7-9上午10:03:47
 */
public class AttrSqlProvider {

	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	public String getAttrListCount() {
		SQL sql = new SQL()
				.SELECT("attr.attr_id,attrdesc.name, attrdesc.description")
				.FROM("attr,attrdesc").WHERE("attr.FIELD1 = 1 and attr.attr_id = attrdesc.attr_id and attrdesc.LANGUAGE_ID = -1");
		return sql.toString();
	}
	
	/**
	 * 获得描述数量
	 * 
	 * @return
	 */
	public String getAttrdescListCount() {
		SQL sql = new SQL()
				.SELECT("attr.attr_id,attrdesc.name, attrdesc.description")
				.FROM("attr,attrdesc").WHERE("attr.FIELD1 = 2 and attr.attr_id = attrdesc.attr_id and attrdesc.LANGUAGE_ID = -1");
		return sql.toString();
	}

	/**
	 * 获得当前编辑属性
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditAttrByID(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("attr.attr_id, attrdesc.name, attrdesc.description")
				.FROM("attr,attrdesc")
				.WHERE("attr.attr_id = " + parameters.get("attr_id")+"and attr.attr_id = attrdesc.attr_id and attrdesc.LANGUAGE_ID = -1");
		return sql.toString();
	}
	
	

	/**
	 * 获得属性值数量
	 * 
	 * @return
	 */
	public String getAttrvalListCount(Long attrid) {
		SQL sql = new SQL()
				.SELECT("attrval.ATTRVAL_ID,attrvaldesc.VALUE, attrvaldesc.SEQUENCE")
				.FROM("attrval,attrvaldesc,attrdesc").WHERE("attrdesc.attr_id = attrval.attr_id and attrval.ATTRVAL_ID = attrvaldesc.ATTRVAL_ID and attrdesc.LANGUAGE_ID = -1 and attrvaldesc.LANGUAGE_ID = -1 and attrdesc.attr_id="+attrid);
		return sql.toString();
	}
	
	/**
	 * 获得当前编辑属性zhi
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditAttrvalByID(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("attrval.attrval_id, attrvaldesc.value, attrvaldesc.SEQUENCE")
				.FROM("attrval,attrvaldesc")
				.WHERE("attrval.attrval_id = " + parameters.get("attrval_id")+"and attrval.attrval_id = attrvaldesc.attrval_id and attrvaldesc.LANGUAGE_ID = -1");
		return sql.toString();
	}
	
	
	
}
