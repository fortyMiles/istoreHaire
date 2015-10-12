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

import com.istore.common.core.bean.XNews;
import com.istore.common.core.provider.NewsSqlProvider;

public interface FindNewsMapper {

	/**
	 * 获得新闻数量
	 * 
	 * @param xchannel_id
	 * 
	 * @return
	 */
	@SelectProvider(type = NewsSqlProvider.class, method = "getNewsListCount")
	public List<XNews> getNewsListCount(@Param("xchannel_id") long xchannel_id);

	public static final String GET_NEWS_LIST = "SELECT XNEWS_ID, TITLE, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS FROM (SELECT XNEWS_ID, TITLE, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, ROWNUM ROWINDEX FROM (SELECT XNEWS_ID, TITLE, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS FROM XNEWS WHERE STATUS != 'C' AND XCHANNEL_ID = #{xchannel_id}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY XNEWS_ID";

	/**
	 * 获得新闻列表
	 * 
	 * @param xchannel_id
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_NEWS_LIST)
	@Results(value = {
			@Result(column = "xnews_id", property = "xnews_id", id = true),
			@Result(column = "title", property = "title"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status") })
	public List<XNews> getNewsList(@Param("xchannel_id") long xchannel_id,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

	public final static String ADD_NEWS_BY_TITLE = "INSERT INTO XNEWS(XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID,SEQ, XGROUP_ID, STATUS) VALUES(SEQ_XNEWS.NEXTVAL, #{title}, #{firstimage}, #{summary}, #{details}, #{createtime}, #{author}, #{xchannel_id}, #{seq}, #{xgroup_id}, #{status})";

	/**
	 * 新闻添加
	 * 
	 * @param title
	 * @param firstimage
	 * @param summary
	 * @param details
	 * @param createtime
	 * @param author
	 * @param xchannel_id
	 * @param seq
	 * @param xgroup_id
	 * @param status
	 * @return
	 */
	@Insert(ADD_NEWS_BY_TITLE)
	public int addNewsByTitle(@Param("title") String title,
			@Param("firstimage") String firstimage,
			@Param("summary") String summary, @Param("details") String details,
			@Param("createtime") Timestamp createtime,
			@Param("author") long author,
			@Param("xchannel_id") long xchannel_id, @Param("seq") long seq,
			@Param("xgroup_id") String xgroup_id, @Param("status") String status);

	/**
	 * 查询创建时间新闻
	 * 
	 * @param author
	 * @param createtime
	 * @return
	 */
	@SelectProvider(type = NewsSqlProvider.class, method = "getNewsByCreatetime")
	@Results(value = {
			@Result(column = "xnews_id", property = "xnews_id", id = true),
			@Result(column = "title", property = "title"),
			@Result(column = "firstimage", property = "firstimage"),
			@Result(column = "summary", property = "summary"),
			@Result(column = "details", property = "details"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XNews> getNewsByCreatetime(@Param("author") long author,
			@Param("createtime") Timestamp createtime);

	/**
	 * 获得当前编辑新闻
	 * 
	 * @param xnews_id
	 * @return
	 */
	@SelectProvider(type = NewsSqlProvider.class, method = "getEditNewsListById")
	@Results(value = {
			@Result(column = "xnews_id", property = "xnews_id", id = true),
			@Result(column = "title", property = "title"),
			@Result(column = "firstimage", property = "firstimage"),
			@Result(column = "summary", property = "summary"),
			@Result(column = "details", property = "details"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XNews> getEditNewsListById(@Param("xnews_id") long xnews_id);

	public final static String EDIT_NEWS_BY_ID = "UPDATE XNEWS SET TITLE = #{title}, FIRSTIMAGE = #{firstimage}, SUMMARY = #{summary}, DETAILS = #{details}, SEQ = #{seq}, XGROUP_ID = #{xgroup_id}, STATUS = #{status} WHERE XNEWS_ID = #{xnews_id}";

	/**
	 * 保存当前编辑新闻
	 * 
	 * @param xnews_id
	 * @param title
	 * @param firstimage
	 * @param summary
	 * @param details
	 * @param author
	 * @param xgroup_id
	 * @param status
	 * @return
	 */
	@Update(EDIT_NEWS_BY_ID)
	public int editNewsByID(@Param("xnews_id") long xnews_id,
			@Param("title") String title,
			@Param("firstimage") String firstimage,
			@Param("summary") String summary, @Param("details") String details,
			@Param("seq") long seq, @Param("xgroup_id") String xgroup_id,
			@Param("status") String status);

	public final static String UPDATE_NEWS_BY_ID = "UPDATE XNEWS SET STATUS = #{status} WHERE XNEWS_ID = #{xnews_id}";

	/**
	 * 新闻逻辑删除
	 * 
	 * @param xnews_id
	 * @param author
	 * @param status
	 * @return
	 */
	@Update(UPDATE_NEWS_BY_ID)
	public int updateNewsByID(@Param("xnews_id") long xnews_id,
			@Param("status") String status);

	/**
	 * 新闻搜索数量
	 * 
	 * @param xchannel_id
	 * @param title
	 * @param details
	 * @return
	 */
	@SelectProvider(type = NewsSqlProvider.class, method = "getSearchNewsListCount")
	@Results(value = {
			@Result(column = "xnews_id", property = "xnews_id", id = true),
			@Result(column = "title", property = "title"),
			@Result(column = "firstimage", property = "firstimage"),
			@Result(column = "summary", property = "summary"),
			@Result(column = "details", property = "details"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XNews> getSearchNewsListCount(
			@Param("xchannel_id") long xchannel_id,
			@Param("title") String title, @Param("details") String details);

	public static final String GET_SEARCH_NEWS_LIST = "SELECT XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM (SELECT XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3, ROWNUM ROWINDEX FROM (SELECT XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3 FROM XNEWS WHERE STATUS != 'C' AND XCHANNEL_ID = #{xchannel_id} AND TITLE LIKE #{title} AND DETAILS LIKE #{details}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY XNEWS_ID";

	/**
	 * 新闻搜索列表
	 * 
	 * @param xchannel_id
	 * @param title
	 * @param details
	 * @param endIndex
	 * @param startIndex
	 * @return
	 */
	@Select(GET_SEARCH_NEWS_LIST)
	@Results(value = {
			@Result(column = "xnews_id", property = "xnews_id", id = true),
			@Result(column = "title", property = "title"),
			@Result(column = "firstimage", property = "firstimage"),
			@Result(column = "summary", property = "summary"),
			@Result(column = "details", property = "details"),
			@Result(column = "createtime", property = "createtime"),
			@Result(column = "author", property = "author"),
			@Result(column = "xchannel_id", property = "xchannel_id"),
			@Result(column = "seq", property = "seq"),
			@Result(column = "xgroup_id", property = "xgroup_id"),
			@Result(column = "status", property = "status"),
			@Result(column = "field1", property = "field1"),
			@Result(column = "field2", property = "field2"),
			@Result(column = "field3", property = "field3") })
	public List<XNews> getSearchNewsList(
			@Param("xchannel_id") long xchannel_id,
			@Param("title") String title, @Param("details") String details,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);
}
