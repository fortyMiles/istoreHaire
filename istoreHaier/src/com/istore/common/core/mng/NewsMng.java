/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mng
 * @Title NewsMng.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.XNews;

/**
 * @ClassName: NewsMng.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-15下午1:13:44
 */
public interface NewsMng {

	/**
	 * 获得新闻数量
	 * 
	 * @param xchannel_id
	 * 
	 * @return
	 */
	public int getNewsListSize(long xchannel_id);

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
	@SuppressWarnings("rawtypes")
	public List getNewsList(long xchannel_id, int startIndex, int endIndex);

	/**
	 * 新闻添加
	 * 
	 * @param title
	 * @param seq
	 * @param details
	 * @param group
	 * @param add_xchannel_id
	 * @return
	 */
	public String addNewsByTitle(String title, String add_seq, String details,
			String group, String add_xchannel_id);

	/**
	 * 获得当前编辑新闻
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public List<XNews> getEditNewsListByID(String edit_news_id);

	/**
	 * 保存当前编辑新闻
	 * 
	 * @param edit_xnews_id
	 * @param title
	 * @param edit_seq
	 * @param details
	 * @param group
	 * @return
	 */
	public String editNewsByID(String edit_news_id, String title,
			String edit_seq, String details, String group);

	/**
	 * 新闻逻辑删除
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public String deleteNewsByID(String edit_news_id);

	/**
	 * 新闻提交审核
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public String referNewsByID(String edit_news_id);

	/**
	 * 新闻搜索数量
	 * 
	 * @param search_xchannel_id
	 * @param search_title
	 * @param search_details
	 * @return
	 */
	public int getSearchNewsListCount(String search_xchannel_id,
			String search_title, String search_details);

	/**
	 * 新闻搜索列表
	 * 
	 * @param search_xchannel_id
	 * @param search_title
	 * @param search_details
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<XNews> getSearchNewsList(String search_xchannel_id,
			String search_title, String search_details, int startIndex,
			int endIndex);

	/**
	 * 查看当前新闻
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public List<XNews> viewNewsListByID(String edit_news_id);

	/**
	 * 新闻审核通过
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public String approveNewsListByID(String edit_news_id);

	/**
	 * 新闻审核拒绝
	 * 
	 * @param edit_news_id
	 * @param xcomment
	 * @return
	 */
	public String rejectNewsListByID(String edit_news_id, String xcomment);

	/**
	 * 新闻审核拒绝原因
	 * 
	 * @param edit_news_id
	 * @return
	 */
	public String getRejectcaseByNewsID(String edit_news_id);

}
