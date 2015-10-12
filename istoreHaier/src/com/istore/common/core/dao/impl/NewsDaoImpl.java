/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao.impl
 * @Title NewsDaoImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-19
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.XNews;
import com.istore.common.core.dao.NewsDao;
import com.istore.common.core.mapper.FindChannelMapper;
import com.istore.common.core.mapper.FindGroupMapper;
import com.istore.common.core.mapper.FindNewsMapper;
import com.istore.common.core.mapper.FindUserMapper;
import com.istore.common.core.mapper.FindXnrHistoryMapper;
import com.istore.common.core.util.DocumentUtils;
import com.istore.common.core.util.StatusUtils;

/**
 * @ClassName: NewsDaoImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-19下午6:34:47
 */
@Repository
public class NewsDaoImpl implements NewsDao {
	private static final String CLASS_NAME = NewsDaoImpl.class.getName();
	private static final Logger LOGGER = Logger.getLogger(NewsDaoImpl.class
			.getName());

	@Autowired
	FindNewsMapper findNewsMapper;

	@Autowired
	FindChannelMapper findChannelMapper;

	@Autowired
	FindGroupMapper findGroupMapper;

	@Autowired
	FindXnrHistoryMapper findXnrHistoryMapper;

	@Autowired
	HttpSession session;

	@Autowired
	FindUserMapper findUserMapper;

	/*
	 * 获得新闻数量
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelListSize()
	 */
	public int getNewsListSize(long xchannel_id) {
		final String METHODNAME = "getNewsListSize";
		LOGGER.entering(CLASS_NAME, METHODNAME);
		List<XNews> xNewsList = findNewsMapper.getNewsListCount(xchannel_id);
		LOGGER.exiting(CLASS_NAME, METHODNAME);
		return xNewsList.size();
	}

	/*
	 * 获得新闻列表
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelList()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getNewsList(long xchannel_id, int startIndex, int endIndex) {
		List<XNews> xNewsList = findNewsMapper.getNewsList(xchannel_id,
				startIndex, endIndex);

		List list = new ArrayList();
		String xchannel_name = "";
		for (int i = 0; i < xNewsList.size(); i++) {
			Map map = new HashMap();
			XNews xnews = new XNews();
			xnews = xNewsList.get(i);
			map.put("xnews_id", xnews.getXnews_id());
			map.put("title", xnews.getTitle());
			map.put("createtime", xnews.getCreatetime());
			map.put("author", xnews.getAuthor());
			xchannel_id = xnews.getXchannel_id();
			xchannel_name = findChannelMapper
					.getChannelNameListByID(xchannel_id);
			map.put("xchannel_name", xchannel_name);
			map.put("seq", xnews.getSeq());

			String xgroup_name = "";
			String[] groups = xnews.getXgroup_id().split(",");
			for (int j = 0; j < groups.length; j++) {
				long group_id = Long.valueOf(groups[j]);
				if (j == groups.length - 1) {
					xgroup_name += findGroupMapper
							.getGroupNameByXGroupID(group_id);
				} else {
					xgroup_name += findGroupMapper
							.getGroupNameByXGroupID(group_id) + ", ";
				}
			}
			map.put("xgroup_name", xgroup_name);
			map.put("status_id", xnews.getStatus());
			map.put("status_name", StatusUtils.getStatusName(xnews.getStatus()));
			list.add(map);
		}
		return list;
	}

	/*
	 * 新闻添加
	 * 
	 * @see com.istore.common.core.dao.NewsDao#addNewsByTitle(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addNewsByTitle(String title, String add_seq, String details,
			String group, String add_xchannel_id) {
		String flag = "failure";
		try {
			String firstimage = DocumentUtils.getDocumentImageSrc(details);
			String summary = DocumentUtils.getDocumentSummary(details);
			Timestamp currenttime = new Timestamp(new Date().getTime());
			String logonId = session.getAttribute("username").toString();
			String users_id = findUserMapper.getUsersIdByLogonId(logonId);
			long author = Long.valueOf(users_id);
			long seq = Long.valueOf(add_seq);
			long xchannel_id = Long.valueOf(add_xchannel_id);
			char[] xgroups = group.toCharArray();
			String xgroup_id = "";
			for (int i = 0; i < xgroups.length; i++) {
				if (i == xgroups.length - 1) {
					xgroup_id += xgroups[i];
				} else {
					xgroup_id += xgroups[i] + ",";
				}
			}
			String status = "D";

			// 添加一条新闻记录
			int result = findNewsMapper.addNewsByTitle(title, firstimage,
					summary, details, currenttime, author, xchannel_id, seq,
					xgroup_id, status);
			if (result == 1) {
				// 获得当前新闻记录
				List<XNews> xNewsList = findNewsMapper.getNewsByCreatetime(
						author, currenttime);

				long xnr_id = xNewsList.get(0).getXnews_id();
				String type = "N";
				long checker = author;
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnr_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
		}
		return flag;
	}

	/*
	 * 获得当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#getEditNewsListByID(java.lang.String
	 * )
	 */
	public List<XNews> getEditNewsListByID(String edit_news_id) {
		long xnews_id = Long.valueOf(edit_news_id);
		return findNewsMapper.getEditNewsListById(xnews_id);
	}

	/*
	 * 保存当前编辑新闻
	 * 
	 * @see com.istore.common.core.dao.NewsDao#editChannelByID(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String editNewsByID(String edit_news_id, String title,
			String edit_seq, String details, String group) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xnews_id = Long.valueOf(edit_news_id);
			String firstimage = DocumentUtils.getDocumentImageSrc(details);
			String summary = DocumentUtils.getDocumentSummary(details);
			long seq = Long.valueOf(edit_seq);
			char[] xgroups = group.toCharArray();
			String xgroup_id = "";
			for (int i = 0; i < xgroups.length; i++) {
				if (i == xgroups.length - 1) {
					xgroup_id += xgroups[i];
				} else {
					xgroup_id += xgroups[i] + ",";
				}
			}
			String status = "D";

			// 修改新闻
			int result = findNewsMapper.editNewsByID(xnews_id, title,
					firstimage, summary, details, seq, xgroup_id, status);

			if (result == 1) {
				String type = "N";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnews_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 新闻逻辑删除
	 * 
	 * @see com.istore.common.core.dao.NewsDao#deleteNewsByID(java.lang.String)
	 */
	public String deleteNewsByID(String edit_news_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xnews_id = Long.valueOf(edit_news_id);
			String status = "C";

			// 删除新闻
			int result = findNewsMapper.updateNewsByID(xnews_id, status);

			if (result == 1) {
				String type = "N";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnews_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 新闻提交审核
	 * 
	 * @see com.istore.common.core.dao.NewsDao#referNewsByID(java.lang.String)
	 */
	public String referNewsByID(String edit_news_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xnews_id = Long.valueOf(edit_news_id);
			String status = "W";

			// 新闻提交审核
			int result = findNewsMapper.updateNewsByID(xnews_id, status);

			if (result == 1) {
				String type = "N";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnews_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 新闻搜索数量
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#getSearchChannelListCount(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int getSearchNewsListCount(String search_xchannel_id,
			String search_title, String search_details) {
		long xchannel_id = Long.valueOf(search_xchannel_id);
		String title = "%" + search_title + "%";
		String details = "%" + search_details + "%";
		List<XNews> xNewsSearchList = findNewsMapper.getSearchNewsListCount(
				xchannel_id, title, details);
		return xNewsSearchList.size();
	}

	/*
	 * 新闻搜索列表
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#getSearchChannelList(java.lang.String,
	 * java.lang.String, java.lang.String, int, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<XNews> getSearchNewsList(String search_xchannel_id,
			String search_title, String search_details, int startIndex,
			int endIndex) {
		long xchannel_id = Long.valueOf(search_xchannel_id);
		String title = "%" + search_title + "%";
		String details = "%" + search_details + "%";

		List<XNews> xNewsList = findNewsMapper.getSearchNewsList(xchannel_id,
				title, details, startIndex, endIndex);

		List list = new ArrayList();
		String xchannel_name = "";
		for (int i = 0; i < xNewsList.size(); i++) {
			Map map = new HashMap();
			XNews xnews = new XNews();
			xnews = xNewsList.get(i);
			map.put("xnews_id", xnews.getXnews_id());
			map.put("title", xnews.getTitle());
			map.put("createtime", xnews.getCreatetime());
			map.put("author", xnews.getAuthor());
			xchannel_name = findChannelMapper
					.getChannelNameListByID(xchannel_id);
			map.put("xchannel_name", xchannel_name);
			map.put("seq", xnews.getSeq());

			String xgroup_name = "";
			String[] groups = xnews.getXgroup_id().split(",");
			for (int j = 0; i < groups.length; i++) {
				long group_id = Long.valueOf(groups[i]);
				if (j == groups.length - 1) {
					xgroup_name += findGroupMapper
							.getGroupNameByXGroupID(group_id);
				} else {
					xgroup_name += findGroupMapper
							.getGroupNameByXGroupID(group_id) + ", ";
				}
			}
			map.put("xgroup_name", xgroup_name);
			map.put("status_id", xnews.getStatus());
			map.put("status_name", StatusUtils.getStatusName(xnews.getStatus()));
			list.add(map);
		}
		return list;
	}

	/*
	 * 查看当前新闻
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#getViewNewsListByID(java.lang.String)
	 */
	public List<XNews> viewNewsListByID(String edit_news_id) {
		long xnews_id = Long.valueOf(edit_news_id);
		return findNewsMapper.getEditNewsListById(xnews_id);
	}

	/*
	 * 新闻审核通过
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#approveViewNewsListByID(java.lang.
	 * String, java.lang.String)
	 */
	public String approveNewsListByID(String edit_news_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xnews_id = Long.valueOf(edit_news_id);
			String status = "P";

			// 新闻提交审核
			int result = findNewsMapper.updateNewsByID(xnews_id, status);

			if (result == 1) {
				String type = "N";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnews_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 新闻审核拒绝
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#rejectViewNewsListByID(java.lang.String
	 * , java.lang.String)
	 */
	public String rejectNewsListByID(String edit_news_id, String xcomment) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xnews_id = Long.valueOf(edit_news_id);
			String status = "R";

			// 新闻提交审核
			int result = findNewsMapper.updateNewsByID(xnews_id, status);

			if (result == 1) {
				String type = "N";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xnews_id, type,
						checker, currenttime, status, xcomment);

				if (result == 1) {
					flag = "success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 新闻审核拒绝原因
	 * 
	 * @see
	 * com.istore.common.core.dao.NewsDao#getRejectcaseByNewsID(java.lang.String
	 * )
	 */
	public String getRejectcaseByNewsID(String edit_news_id) {
		long xnews_id = Long.valueOf(edit_news_id);
		String type = "N";
		String xcomment = findXnrHistoryMapper.getRejectcaseByXNRID(xnews_id,
				type);
		return xcomment;
	}

}
