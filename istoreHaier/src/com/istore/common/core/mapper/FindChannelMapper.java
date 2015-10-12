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

import com.istore.common.core.bean.XChannel;
import com.istore.common.core.provider.ChannelSqlProvider;

/**
 * @ClassName: FindChannelMapper.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午9:55:02
 */
public interface FindChannelMapper {

	/**
	 * 获得频道数量
	 * 
	 * @param store_id
	 * 
	 * @return
	 */
	@SelectProvider(type = ChannelSqlProvider.class, method = "getChannelListCount")
	@Results(value = {
			@Result(column = "xchannel_id", property = "xchannel_id", id = true),
			@Result(column = "xchannel_name", property = "xchannel_name"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "markfordelete", property = "markfordelete"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "type", property = "type") })
	public List<XChannel> getChannelListCount(@Param("store_id") long store_id);

	public static final String GET_CHANNEL_LIST = "SELECT XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE FROM (SELECT XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE, ROWNUM ROWINDEX FROM (SELECT XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE FROM XCHANNEL WHERE MARKFORDELETE = 0 AND STORE_ID = #{store_id}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY XCHANNEL_ID";

	/**
	 * 获得频道列表
	 * 
	 * @param store_id
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_CHANNEL_LIST)
	@Results(value = {
			@Result(column = "xchannel_id", property = "xchannel_id", id = true),
			@Result(column = "xchannel_name", property = "xchannel_name"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "markfordelete", property = "markfordelete"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "type", property = "type") })
	public List<XChannel> getChannelList(@Param("store_id") long store_id,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

	public static final String ADD_CHANNEL_BY_NAME = "INSERT INTO XCHANNEL(XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE) VALUES(SEQ_XCHANNEL.NEXTVAL, #{xchannel_name}, #{store_id}, #{createtime}, #{author}, #{lastupdate}, #{markfordelete}, #{seq}, #{type})";

	/**
	 * 频道添加
	 * 
	 * @param xchannel_name
	 * @param store_id
	 * @param createtime
	 * @param author
	 * @param lastupdate
	 * @param markfordelete
	 * @param seq
	 * @param tag
	 * @param type
	 * @return
	 */
	@Insert(ADD_CHANNEL_BY_NAME)
	public Integer addChannelByName(
			@Param("xchannel_name") String xchannel_name,
			@Param("store_id") long store_id,
			@Param("createtime") Timestamp createtime,
			@Param("author") long author,
			@Param("lastupdate") Timestamp lastupdate,
			@Param("markfordelete") long markfordelete, @Param("seq") long seq,
			@Param("type") String type);

	/**
	 * 获得当前编辑频道
	 * 
	 * @param xchannel_id
	 * @return
	 */
	@SelectProvider(type = ChannelSqlProvider.class, method = "getEditChannelByID")
	@Results(value = {
			@Result(column = "xchannel_id", property = "xchannel_id", id = true),
			@Result(column = "xchannel_name", property = "xchannel_name"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "markfordelete", property = "markfordelete"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "type", property = "type") })
	public List<XChannel> getEditChannelListByID(
			@Param("xchannel_id") long xchannel_id);

	public static final String EDIT_CHANNEL_BY_ID = "UPDATE XCHANNEL SET XCHANNEL_NAME = #{edit_xchannel_name}, LASTUPDATE = #{lastupdate}, SEQ = #{seq}, TYPE = #{edit_type} WHERE XCHANNEL_ID = #{xchannel_id}";

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
	@Update(EDIT_CHANNEL_BY_ID)
	public int editChannelByID(@Param("xchannel_id") long xchannel_id,
			@Param("edit_xchannel_name") String edit_xchannel_name,
			@Param("lastupdate") Timestamp lastupdate, @Param("seq") long seq,
			@Param("edit_type") String edit_type);

	public static final String DELETE_CHANNEL_BY_ID = "UPDATE XCHANNEL SET LASTUPDATE = #{lastupdate}, MARKFORDELETE = 1 WHERE XCHANNEL_ID = #{xchannel_id}";

	/**
	 * 频道逻辑删除
	 * 
	 * @param xchannel_id
	 * @param lastupdate
	 * @return
	 */
	@Update(DELETE_CHANNEL_BY_ID)
	public int deleteChannelByID(@Param("xchannel_id") long xchannel_id,
			@Param("lastupdate") Timestamp lastupdate);

	/**
	 * 频道搜索数量
	 * 
	 * @param store_id
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @return
	 */
	@SelectProvider(type = ChannelSqlProvider.class, method = "getSearchChannelListCount")
	@Results(value = {
			@Result(column = "xchannel_id", property = "xchannel_id", id = true),
			@Result(column = "xchannel_name", property = "xchannel_name"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "markfordelete", property = "markfordelete"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "type", property = "type") })
	public List<XChannel> getSearchChannelListCount(
			@Param("store_id") long store_id,
			@Param("xchannel_name") String xchannel_name,
			@Param("type") String type);

	public static final String GET_SEARCH_CHANNEL_LIST = "SELECT A.XCHANNEL_ID, A.XCHANNEL_NAME, A.STORE_ID, A.CREATETIME, A.AUTHOR, A.LASTUPDATE, A.MARKFORDELETE, A.SEQ, A.TYPE FROM (SELECT X.XCHANNEL_ID, X.XCHANNEL_NAME, X.STORE_ID, X.CREATETIME, X.AUTHOR, X.LASTUPDATE, X.MARKFORDELETE, X.SEQ, X.TYPE, ROWNUM ROWINDEX FROM (SELECT XCHANNEL_ID, XCHANNEL_NAME, STORE_ID, CREATETIME, AUTHOR, LASTUPDATE, MARKFORDELETE, SEQ, TYPE FROM XCHANNEL WHERE MARKFORDELETE = 0 AND STORE_ID = #{store_id} AND XCHANNEL_NAME LIKE #{xchannel_name} AND TYPE LIKE #{type}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY A.XCHANNEL_ID";

	/**
	 * 频道搜索列表
	 * 
	 * @param store_id
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @param endIndex
	 * @param startIndex
	 * @return
	 */
	@Select(GET_SEARCH_CHANNEL_LIST)
	@Results(value = {
			@Result(column = "xchannel_id", property = "xchannel_id", id = true),
			@Result(column = "xchannel_name", property = "xchannel_name"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "markfordelete", property = "markfordelete"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "type", property = "type") })
	public List<XChannel> getSearchChannelList(
			@Param("store_id") long store_id,
			@Param("xchannel_name") String xchannel_name,
			@Param("type") String type, @Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);

	/**
	 * 获得频道名称
	 * 
	 * @param xchannel_id
	 * @return
	 */
	@SelectProvider(type = ChannelSqlProvider.class, method = "getChannelNameListByID")
	@Results(value = { @Result(column = "xchannel_name", property = "xchannel_name") })
	public String getChannelNameListByID(@Param("xchannel_id") long xchannel_id);

}
