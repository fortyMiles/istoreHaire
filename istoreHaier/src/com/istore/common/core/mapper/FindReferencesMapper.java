/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mapper
 * @Title FindChannelMapper.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.Catgroup;
import com.istore.common.core.bean.XReferences;
import com.istore.common.core.provider.ReferencesSqlProvider;

/**
 * @ClassName: FindChannelMapper.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午9:55:02
 */
public interface FindReferencesMapper {

	/**
	 * 获得样板工程数量
	 * 
	 * @param xchannel_id
	 * 
	 * @return
	 */
	@SelectProvider(type = ReferencesSqlProvider.class, method = "getReferencesListSize")
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getReferencesListSize(
			@Param("xchannel_id") long xchannel_id);

	public static final String GET_REFERENCES_LIST = "SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM (SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3, ROWNUM ROWINDEX FROM (SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM XREFERENCES WHERE STATUS != 'C' AND XCHANNEL_ID = #{xchannel_id}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY XREFERENCES_ID";

	/**
	 * 获得样板工程列表
	 * 
	 * @param xchannel_id
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_REFERENCES_LIST)
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getReferencesList(
			@Param("xchannel_id") long xchannel_id,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

	public final static String ADD_REFERENCES_BY_SERIVALNUMBER = "INSERT INTO XREFERENCES(XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, CATGROUP_ID, STATUS) VALUES(SEQ_XREFERENCES.NEXTVAL, #{serialnumber}, #{country}, #{projectplace}, #{description}, #{type}, #{installtime}, #{installseries}, #{installdetails}, #{totalcapacity}, #{keycapacity}, #{pictures}, #{createtime}, #{author}, #{xchannel_id}, #{seq}, #{xgroup_id}, #{catgroup_id}, #{status})";

	/**
	 * 样板工程添加
	 * 
	 * @param serialnumber
	 * @param country
	 * @param projectplace
	 * @param description
	 * @param type
	 * @param installtime
	 * @param installseries
	 * @param installdetails
	 * @param totalcapacity
	 * @param keycapacity
	 * @param pictures
	 * @param createtime
	 * @param author
	 * @param xchannel_id
	 * @param seq
	 * @param xgroup_id
	 * @param catgroup_id
	 * @param status
	 * @return
	 */
	@Insert(ADD_REFERENCES_BY_SERIVALNUMBER)
	public int addReferencesBySerialNumber(
			@Param("serialnumber") String serialnumber,
			@Param("country") String country,
			@Param("projectplace") String projectplace,
			@Param("description") String description,
			@Param("type") String type,
			@Param("installtime") String installtime,
			@Param("installseries") String installseries,
			@Param("installdetails") String installdetails,
			@Param("totalcapacity") String totalcapacity,
			@Param("keycapacity") String keycapacity,
			@Param("pictures") String pictures,
			@Param("createtime") Timestamp createtime,
			@Param("author") long author,
			@Param("xchannel_id") long xchannel_id, @Param("seq") long seq,
			@Param("xgroup_id") String xgroup_id,
			@Param("catgroup_id") long catgroup_id,
			@Param("status") String status);

	/**
	 * 查询创建时间样板工程
	 * 
	 * @param author
	 * @param createtime
	 * @return
	 */
	@SelectProvider(type = ReferencesSqlProvider.class, method = "getReferencesByCreatetime")
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getReferencesByCreatetime(
			@Param("author") long author,
			@Param("createtime") Timestamp createtime);

	/**
	 * 获得当前编辑样板工程
	 * 
	 * @param xreferences_id
	 * @return
	 */
	@SelectProvider(type = ReferencesSqlProvider.class, method = "getEditReferencesListByID")
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "catgroup_id", property = "catgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getEditReferencesListByID(
			@Param("xreferences_id") long xreferences_id);

	public static final String EDIT_REFERENCES_BY_ID = "UPDATE XREFERENCES SET SERIALNUMBER = #{serialnumber}, COUNTRY = #{country}, PROJECTPLACE = #{projectplace}, DESCRIPTION = #{description}, TYPE = #{type}, INSTALLTIME = #{installtime}, INSTALLSERIES = #{installseries}, INSTALLDETAILS = #{installdetails}, TOTALCAPACITY = #{totalcapacity}, KEYCAPACITY = #{keycapacity}, PICTURES = #{pictures}, SEQ = #{seq}, XGROUP_ID = #{xgroup_id}, CATGROUP_ID = #{catgroup_id} STATUS = #{status} WHERE XREFERENCES_ID = #{xreferences_id}";

	/**
	 * 保存当前编辑频道
	 * 
	 * @param xchannel_id
	 * @param edit_xchannel_name
	 * @param lastupdate
	 * @param seq
	 * @param edit_tag
	 * @param edit_type
	 * @return
	 */
	@Update(EDIT_REFERENCES_BY_ID)
	public int editReferencesByID(@Param("xreferences_id") long xreferences_id,
			@Param("serialnumber") String serialnumber,
			@Param("country") String country,
			@Param("projectplace") String projectplace,
			@Param("description") String description,
			@Param("type") String type,
			@Param("installtime") String installtime,
			@Param("installseries") String installseries,
			@Param("installdetails") String installdetails,
			@Param("totalcapacity") String totalcapacity,
			@Param("keycapacity") String keycapacity,
			@Param("pictures") String pictures, @Param("seq") long seq,
			@Param("xgroup_id") String xgroup_id,
			@Param("catgroup_id") long catgroup_id,
			@Param("status") String status);

	public static final String UPDATE_REFERENCES_BY_ID = "UPDATE XREFERENCES SET STATUS = #{status} WHERE XREFERENCES_ID = #{xreferences_id}";

	/**
	 * 频道逻辑删除
	 * 
	 * @param xchannel_id
	 * @param lastupdate
	 * @return
	 */
	@Update(UPDATE_REFERENCES_BY_ID)
	public int updateReferencesNewsByID(
			@Param("xreferences_id") long xreferences_id,
			@Param("status") String status);

	/**
	 * 频道搜索数量
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @return
	 */
	@SelectProvider(type = ReferencesSqlProvider.class, method = "getSearchReferencesListCount")
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "catgroup_id", property = "catgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getSearchReferencesListCount(
			@Param("xchannel_id") long xchannel_id,
			@Param("serialnumber") String serialnumber,
			@Param("country") String country,
			@Param("projectplace") String projectplace,
			@Param("description") String description,
			@Param("type") String type,
			@Param("installtime") String installtime,
			@Param("installseries") String installseries,
			@Param("installdetails") String installdetails,
			@Param("totalcapacity") String totalcapacity,
			@Param("keycapacity") String keycapacity);

	public static final String GET_SEARCH_REFERENCES_LIST = "SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM (SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3, ROWNUM ROWINDEX FROM (SELECT XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM XREFERENCES WHERE STATUS != 'C' AND XCHANNEL_ID = #{xchannel_id} AND SERIALNUMBER LIKE #{serialnumber} AND COUNTRY LIKE #{country} AND PROJECTPLACE LIKE #{projectplace} AND DESCRIPTION LIKE #{description} AND TYPE LIKE #{type} AND INSTALLTIME LIKE #{installtime} AND INSTALLSERIES LIKE #{installseries} AND INSTALLDETAILS LIKE #{installdetails} AND TOTALCAPACITY LIKE #{totalcapacity} AND KEYCAPACITY LIKE #{keycapacity}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY XREFERENCES_ID";

	/**
	 * 频道搜索列表
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @param endIndex
	 * @param startIndex
	 * @return
	 */
	@Select(GET_SEARCH_REFERENCES_LIST)
	@Results(value = {
			@Result(column = "xreferences_id", property = "xreferences_id", id = true),
			@Result(column = "serialnumber", property = "serialnumber"),
			@Result(column = "country", property = "country"),
			@Result(column = "projectplace", property = "projectplace"),
			@Result(column = "details", property = "details"),
			@Result(column = "description", property = "description"),
			@Result(column = "type", property = "type"),
			@Result(column = "installtime", property = "installtime"),
			@Result(column = "installseries", property = "installseries"),
			@Result(column = "installdetails", property = "installdetails"),
			@Result(column = "totalcapacity", property = "totalcapacity"),
			@Result(column = "keycapacity", property = "keycapacity"),
			@Result(column = "pictures", property = "pictures"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "catgroup_id", property = "catgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XReferences> getSearchChannelList(
			@Param("xchannel_id") long xchannel_id,
			@Param("serialnumber") String serialnumber,
			@Param("country") String country,
			@Param("projectplace") String projectplace,
			@Param("description") String description,
			@Param("type") String type,
			@Param("installtime") String installtime,
			@Param("installseries") String installseries,
			@Param("installdetails") String installdetails,
			@Param("totalcapacity") String totalcapacity,
			@Param("keycapacity") String keycapacity,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

	public static final String GET_CATGROUP_BY_STORE_ID = "SELECT C.CATGROUP_ID, D.NAME FROM CATTOGRP C LEFT JOIN CATGRPDESC D ON C.CATGROUP_ID = D.CATGROUP_ID  WHERE C.CATALOG_ID = (SELECT MIN(CATALOG_ID) FROM STORECAT WHERE STOREENT_ID = #{store_id} AND MASTERCATALOG = 0)";

	/**
	 * 获得一级目录
	 * 
	 * @param store_id
	 * @return
	 */
	@Select(GET_CATGROUP_BY_STORE_ID)
	@Results(value = {
			@Result(column = "catgroup_id", property = "catgroup_id", id = true),
			@Result(column = "name", property = "name") })
	public List<Catgroup> getCatgroupByStoreID(@Param("store_id") long store_id);

}
