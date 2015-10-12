/**
 * @Project Haieristore
 * @Package com.istore.common.core.mng.impl
 * @Title NewsMngImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-12
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.XNews;
import com.istore.common.core.dao.NewsDao;
import com.istore.common.core.mng.NewsMng;

/**
 * @ClassName: NewsMngImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-12上午11:24:50
 */
@Service
@Transactional
public class NewsMngImpl implements NewsMng {

	@Autowired
	NewsDao newsDao;

	/*
	 * 获得新闻数量
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getNewsListSize()
	 */
	public int getNewsListSize(long xchannel_id) {
		return newsDao.getNewsListSize(xchannel_id);
	}

	/*
	 * 获得新闻列表
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getNewsList()
	 */
	@SuppressWarnings("rawtypes")
	public List getNewsList(long xchannel_id, int startIndex, int endIndex) {
		return newsDao.getNewsList(xchannel_id, startIndex, endIndex);
	}

	/*
	 * 新闻添加
	 * 
	 * @see com.istore.common.core.mng.NewsMng#addNewsByName(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addNewsByTitle(String title, String add_seq, String details,
			String group, String add_xchannel_id) {
		return newsDao.addNewsByTitle(title, add_seq, details, group,
				add_xchannel_id);
	}

	/*
	 * 获得当前编辑新闻
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#getEditNewsListByID(java.lang.String
	 * )
	 */
	public List<XNews> getEditNewsListByID(String edit_news_id) {
		return newsDao.getEditNewsListByID(edit_news_id);
	}

	/*
	 * 保存当前编辑新闻
	 * 
	 * @see com.istore.common.core.mng.NewsMng#editChannelByID(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String editNewsByID(String edit_news_id, String title,
			String edit_seq, String details, String group) {
		return newsDao.editNewsByID(edit_news_id, title, edit_seq, details,
				group);
	}

	/*
	 * 新闻逻辑删除
	 * 
	 * @see com.istore.common.core.mng.NewsMng#deleteNewsByID(java.lang.String)
	 */
	public String deleteNewsByID(String edit_news_id) {
		return newsDao.deleteNewsByID(edit_news_id);
	}

	/*
	 * 新闻提交审核
	 * 
	 * @see com.istore.common.core.mng.NewsMng#referNewsByID(java.lang.String)
	 */
	public String referNewsByID(String edit_news_id) {
		return newsDao.referNewsByID(edit_news_id);
	}

	/*
	 * 新闻搜索数量
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#getSearchChannelListCount(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int getSearchNewsListCount(String search_xchannel_id,
			String search_title, String search_details) {
		return newsDao.getSearchNewsListCount(search_xchannel_id, search_title,
				search_details);
	}

	/*
	 * 新闻搜索列表
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#getSearchChannelList(java.lang.String,
	 * java.lang.String, java.lang.String, int, int)
	 */
	public List<XNews> getSearchNewsList(String search_xchannel_id,
			String search_title, String search_details, int startIndex,
			int endIndex) {
		return newsDao.getSearchNewsList(search_xchannel_id, search_title,
				search_details, startIndex, endIndex);
	}

	/*
	 * 查看当前新闻
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#getViewNewsListByID(java.lang.String)
	 */
	public List<XNews> viewNewsListByID(String edit_news_id) {
		return newsDao.viewNewsListByID(edit_news_id);
	}

	/*
	 * 新闻审核通过
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#approveViewNewsListByID(java.lang.
	 * String)
	 */
	public String approveNewsListByID(String edit_news_id) {
		return newsDao.approveNewsListByID(edit_news_id);
	}

	/*
	 * 新闻审核拒绝
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#rejectViewNewsListByID(java.lang.String
	 * , java.lang.String)
	 */
	public String rejectNewsListByID(String edit_news_id, String xcomment) {
		return newsDao.rejectNewsListByID(edit_news_id, xcomment);
	}

	/*
	 * 新闻审核拒绝原因
	 * 
	 * @see
	 * com.istore.common.core.mng.NewsMng#getRejectcaseByNewsID(java.lang.String
	 * )
	 */
	public String getRejectcaseByNewsID(String edit_news_id) {
		return newsDao.getRejectcaseByNewsID(edit_news_id);
	}
}
