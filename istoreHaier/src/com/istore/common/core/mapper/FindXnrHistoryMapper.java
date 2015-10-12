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

import com.istore.common.core.bean.XnrHistory;
import com.istore.common.core.provider.XnrHistorySqlProvider;

/**
 * @ClassName: FindXnrHistoryMapper.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午9:55:02
 */
public interface FindXnrHistoryMapper {

	public final static String ADD_XNRHISTORY = "INSERT INTO XNRHISTORY(XNRHISTORY_ID, XNR_ID, TYPE, CHECKER, LASTUPDATE, STATUS, XCOMMENT) VALUES(SEQ_XNRHISTORY.NEXTVAL, #{xnr_id}, #{type}, #{checker}, #{lastupdate}, #{status}, #{xcomment})";

	/**
	 * 添加新闻历史记录
	 * 
	 * @param xnr_id
	 * @param type
	 * @param checker
	 * @param lastupdate
	 * @param status
	 * @param xcomment
	 * @return
	 */
	@Insert(ADD_XNRHISTORY)
	public int addXnrHistory(@Param("xnr_id") long xnr_id,
			@Param("type") String type, @Param("checker") long checker,
			@Param("lastupdate") Timestamp lastupdate,
			@Param("status") String status, @Param("xcomment") String xcomment);

	/**
	 * 获得新闻样板历史记录
	 * 
	 * @param lastupdate
	 * @return
	 */
	@SelectProvider(type = XnrHistorySqlProvider.class, method = "getXnrHistoryByLastupdate")
	@Results(value = {
			@Result(column = "xnrhistory_id", property = "xnrhistory_id", id = true),
			@Result(column = "xnr_id", property = "xnr_id"),
			@Result(column = "checker", property = "checker"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "author", property = "author"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "status", property = "status"),
			@Result(column = "xcomment", property = "xcomment") })
	public List<XnrHistory> getXnrHistoryByLastupdate(
			@Param("lastupdate") Timestamp lastupdate);

	public final static String GET_REJECTCASE_BY_NEWS_ID = "SELECT XCOMMENT FROM (SELECT XCOMMENT, ROWNUM ROWINDEX FROM XNRHISTORY WHERE XNR_ID = #{xnr_id} AND TYPE = #{type} AND STATUS = 'R' ORDER BY LASTUPDATE DESC) X WHERE X.ROWINDEX = 1";

	/**
	 * 新闻审核拒绝原因
	 * 
	 * @param xnews_id
	 * @return
	 */
	@Select(GET_REJECTCASE_BY_NEWS_ID)
	@Results(value = { @Result(column = "xcomment", property = "xcomment") })
	public String getRejectcaseByXNRID(@Param("xnr_id") long xnr_id,
			@Param("type") String type);

}
