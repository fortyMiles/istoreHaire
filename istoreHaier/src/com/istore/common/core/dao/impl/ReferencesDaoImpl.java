/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao.impl
 * @Title ChannelDaoImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Catgroup;
import com.istore.common.core.bean.XReferences;
import com.istore.common.core.dao.ReferencesDao;
import com.istore.common.core.mapper.FindChannelMapper;
import com.istore.common.core.mapper.FindGroupMapper;
import com.istore.common.core.mapper.FindReferencesMapper;
import com.istore.common.core.mapper.FindUserMapper;
import com.istore.common.core.mapper.FindXnrHistoryMapper;
import com.istore.common.core.util.StatusUtils;

/**
 * @ClassName: ChannelDaoImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:07:03
 */
@Repository
public class ReferencesDaoImpl implements ReferencesDao {

	@Autowired
	private FindReferencesMapper findReferencesMapper;

	@Autowired
	private FindChannelMapper findChannelMapper;

	@Autowired
	private FindGroupMapper findGroupMapper;

	@Autowired
	private FindXnrHistoryMapper findXnrHistoryMapper;

	@Autowired
	HttpSession session;

	@Autowired
	FindUserMapper findUserMapper;

	/*
	 * 获得频道数量
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelListSize()
	 */
	public int getReferencesListSize(long xchannel_id) {
		List<XReferences> xReferencesList = findReferencesMapper
				.getReferencesListSize(xchannel_id);
		return xReferencesList.size();
	}

	/*
	 * 获得频道列表
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelList()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getReferencesList(long xchannel_id, int startIndex, int endIndex) {
		List<XReferences> referencesList = findReferencesMapper
				.getReferencesList(xchannel_id, startIndex, endIndex);

		List list = new ArrayList();
		String xchannel_name = "";
		for (int i = 0; i < referencesList.size(); i++) {
			Map map = new HashMap();
			XReferences xreferences = new XReferences();
			xreferences = referencesList.get(i);
			map.put("xreferences_id", xreferences.getXreferences_id());
			map.put("serialnumber", xreferences.getSerialnumber());
			map.put("createtime", xreferences.getCreatetime());
			map.put("author", xreferences.getAuthor());
			xchannel_id = xreferences.getXchannel_id();
			xchannel_name = findChannelMapper
					.getChannelNameListByID(xchannel_id);
			map.put("xchannel_name", xchannel_name);
			map.put("seq", xreferences.getSeq());

			String xgroup_name = "";
			String[] groups = xreferences.getXgroup_id().split(",");
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
			map.put("status_id", xreferences.getStatus());
			map.put("status_name",
					StatusUtils.getStatusName(xreferences.getStatus()));
			list.add(map);
		}
		return list;

	}

	/*
	 * 样板工程添加
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#addReferencesBySerialNumber(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addReferencesBySerialNumber(String serialnumber,
			String country, String projectplace, String description,
			String type, String installtime, String installseries,
			String installdetails, String totalcapacity, String keycapacity,
			String add_seq, String group, String catgroup, String pictures,
			String add_xchannel_id) {
		String flag = "failure";
		try {
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
			long catgroup_id = Long.valueOf(catgroup);
			String status = "D";

			// 添加一条样板工程
			int result = findReferencesMapper.addReferencesBySerialNumber(
					serialnumber, country, projectplace, description, type,
					installtime, installseries, installdetails, totalcapacity,
					keycapacity, pictures, currenttime, author, xchannel_id,
					seq, xgroup_id, catgroup_id, status);
			if (result == 1) {
				// 获得当前样板工程
				List<XReferences> xReferencesList = findReferencesMapper
						.getReferencesByCreatetime(author, currenttime);

				long xnr_id = xReferencesList.get(0).getXreferences_id();
				type = "R";
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
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 获得当前编辑样板工程
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#getEditReferencesListByID(java
	 * .lang.String)
	 */
	public List<XReferences> getEditReferencesListByID(
			String edit_xreferences_id) {
		long xreferences_id = Long.valueOf(edit_xreferences_id);
		return findReferencesMapper.getEditReferencesListByID(xreferences_id);
	}

	/*
	 * 保存当前编辑样板工程
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#editReferencessByID(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String editReferencessByID(String edit_xreferences_id,
			String serialnumber, String country, String projectplace,
			String description, String type, String installtime,
			String installseries, String installdetails, String totalcapacity,
			String keycapacity, String edit_seq, String group, String catgroup,
			String pictures) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xreferences_id = Long.valueOf(edit_xreferences_id);
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
			long catgroup_id = Long.valueOf(catgroup);
			String status = "D";

			// 修改样板工程
			int result = findReferencesMapper.editReferencesByID(
					xreferences_id, serialnumber, country, projectplace,
					description, type, installtime, installseries,
					installdetails, totalcapacity, keycapacity, pictures, seq,
					xgroup_id, catgroup_id, status);

			if (result == 1) {
				type = "R";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xreferences_id,
						type, checker, currenttime, status, xcomment);

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
	 * 样板工程逻辑删除
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#deleteReferencesByID(java.lang
	 * .String)
	 */
	public String deleteReferencesByID(String edit_xreferences_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xreferences_id = Long.valueOf(edit_xreferences_id);
			String status = "C";

			// 删除新闻
			int result = findReferencesMapper.updateReferencesNewsByID(
					xreferences_id, status);

			if (result == 1) {
				String type = "R";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xreferences_id,
						type, checker, currenttime, status, xcomment);

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
	 * 样板工程提交审核
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#referReferencesByID(java.lang
	 * .String)
	 */
	public String referReferencesByID(String edit_xreferences_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xreferences_id = Long.valueOf(edit_xreferences_id);
			String status = "W";

			// 新闻提交审核
			int result = findReferencesMapper.updateReferencesNewsByID(
					xreferences_id, status);

			if (result == 1) {
				String type = "R";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xreferences_id,
						type, checker, currenttime, status, xcomment);

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
	 * 样板工程搜索数量
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#getSearchChannelListSize(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int getSearchChannelListCount(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity) {

		long xchannel_id = Long.valueOf(search_xchannel_id);
		String serialnumber = "%" + search_serialnumber + "%";
		String country = "%" + search_country + "%";
		String projectplace = "%" + search_projectplace + "%";
		String description = "%" + search_description + "%";
		String type = "%" + search_type + "%";
		String installtime = "%" + search_installtime + "%";
		String installseries = "%" + search_installseries + "%";
		String installdetails = "%" + search_installdetails + "%";
		String totalcapacity = "%" + search_totalcapacity + "%";
		String keycapacity = "%" + search_keycapacity + "%";
		List<XReferences> xReferencesSearchList = findReferencesMapper
				.getSearchReferencesListCount(xchannel_id, serialnumber,
						country, projectplace, description, type, installtime,
						installseries, installdetails, totalcapacity,
						keycapacity);
		return xReferencesSearchList.size();
	}

	/*
	 * 样板工程搜索列表
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#searchChannelList(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<XReferences> getSearchChannelList(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity,
			int startIndex, int endIndex) {

		long xchannel_id = Long.valueOf(search_xchannel_id);
		String serialnumber = "%" + search_serialnumber + "%";
		String country = "%" + search_country + "%";
		String projectplace = "%" + search_projectplace + "%";
		String description = "%" + search_description + "%";
		String type = "%" + search_type + "%";
		String installtime = "%" + search_installtime + "%";
		String installseries = "%" + search_installseries + "%";
		String installdetails = "%" + search_installdetails + "%";
		String totalcapacity = "%" + search_totalcapacity + "%";
		String keycapacity = "%" + search_keycapacity + "%";

		List<XReferences> xReferencesSearchList = findReferencesMapper
				.getSearchChannelList(xchannel_id, serialnumber, country,
						projectplace, description, type, installtime,
						installseries, installdetails, totalcapacity,
						keycapacity, startIndex, endIndex);

		List list = new ArrayList();
		String xchannel_name = "";
		for (int i = 0; i < xReferencesSearchList.size(); i++) {
			Map map = new HashMap();
			XReferences xreferences = new XReferences();
			xreferences = xReferencesSearchList.get(i);
			map.put("xreferences_id", xreferences.getXreferences_id());
			map.put("serialnumber", xreferences.getSerialnumber());
			map.put("createtime", xreferences.getCreatetime());
			map.put("author", xreferences.getAuthor());
			xchannel_id = xreferences.getXchannel_id();
			xchannel_name = findChannelMapper
					.getChannelNameListByID(xchannel_id);
			map.put("xchannel_name", xchannel_name);
			map.put("seq", xreferences.getSeq());

			String xgroup_name = "";
			String[] groups = xreferences.getXgroup_id().split(",");
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
			map.put("status_id", xreferences.getStatus());
			map.put("status_name",
					StatusUtils.getStatusName(xreferences.getStatus()));
			list.add(map);
		}
		return list;
	}

	/*
	 * 查看当前样板工程
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#getViewReferencesListByID(java
	 * .lang.String)
	 */
	public List<XReferences> getViewReferencesListByID(
			String edit_xreferences_id) {
		long xreferences_id = Long.valueOf(edit_xreferences_id);
		return findReferencesMapper.getEditReferencesListByID(xreferences_id);
	}

	/*
	 * 样板工程审核通过
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#approveReferencesByID(java.lang
	 * .String)
	 */
	public String approveReferencesByID(String edit_xreferences_id) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xreferences_id = Long.valueOf(edit_xreferences_id);
			String status = "P";

			// 新闻审核拒绝
			int result = findReferencesMapper.updateReferencesNewsByID(
					xreferences_id, status);

			if (result == 1) {
				String type = "R";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);
				String xcomment = "";

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xreferences_id,
						type, checker, currenttime, status, xcomment);

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
	 * 样板工程审核拒绝
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#rejectReferencesByID(java.lang
	 * .String, java.lang.String)
	 */
	public String rejectReferencesByID(String edit_xreferences_id,
			String xcomment) {
		String flag = "failure";
		try {
			Timestamp currenttime = new Timestamp(new Date().getTime());
			long xreferences_id = Long.valueOf(edit_xreferences_id);
			String status = "R";

			// 新闻审核拒绝
			int result = findReferencesMapper.updateReferencesNewsByID(
					xreferences_id, status);

			if (result == 1) {
				String type = "R";
				String logonId = session.getAttribute("username").toString();
				String users_id = findUserMapper.getUsersIdByLogonId(logonId);
				long checker = Long.valueOf(users_id);

				// 添加新闻历史记录
				result = findXnrHistoryMapper.addXnrHistory(xreferences_id,
						type, checker, currenttime, status, xcomment);

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
	 * 样板工程审核拒绝原因
	 * 
	 * @see
	 * com.istore.common.core.dao.ReferencesDao#getRejectcaseByReferencesID(
	 * java.lang.String)
	 */
	public String getRejectcaseByReferencesID(String edit_xreferences_id) {
		long xreferences_id = Long.valueOf(edit_xreferences_id);
		String type = "R";
		String xcomment = findXnrHistoryMapper.getRejectcaseByXNRID(
				xreferences_id, type);
		return xcomment;
	}

	/*
	 * 获得一级目录
	 * 
	 * @see com.istore.common.core.dao.ReferencesDao#getCatgroupByStoreId(long)
	 */
	public List<Catgroup> getCatgroupByStoreID(long store_id) {
		List<Catgroup> catgroupList = findReferencesMapper
				.getCatgroupByStoreID(store_id);
		return catgroupList;
	}

}
