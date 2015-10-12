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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.XChannel;
import com.istore.common.core.dao.ChannelDao;
import com.istore.common.core.mapper.FindChannelMapper;
import com.istore.common.core.mapper.FindUserMapper;

/**
 * @ClassName: ChannelDaoImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:07:03
 */
@Repository
public class ChannelDaoImpl implements ChannelDao {

	@Autowired
	FindChannelMapper findChannelMapper;

	@Autowired
	HttpSession session;

	@Autowired
	FindUserMapper findUserMapper;

	/*
	 * 获得频道数量
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelListSize()
	 */
	public int getChannelListSize() {
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		List<XChannel> xChannelList = findChannelMapper
				.getChannelListCount(store_id);
		return xChannelList.size();
	}

	/*
	 * 获得频道列表
	 * 
	 * @see com.istore.common.core.dao.ChannelDao#getChannelList()
	 */
	public List<XChannel> getChannelList(int startIndex, int endIndex) {
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		return findChannelMapper.getChannelList(store_id, startIndex, endIndex);
	}

	/*
	 * 频道添加
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#addChannelByName(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addChannelByName(String xchannel_name, String add_seq,
			String type) {
		String flag = "false";
		try {
			long store_id = Long.valueOf(session.getAttribute("storeId")
					.toString());
			String logonId = session.getAttribute("username").toString();
			String users_id = findUserMapper.getUsersIdByLogonId(logonId);
			long author = Long.valueOf(users_id);
			long markfordelete = 0;
			Timestamp createtime = new Timestamp(new Date().getTime());
			Timestamp lastupdate = new Timestamp(new Date().getTime());
			long seq = Long.valueOf(add_seq);

			int result = findChannelMapper.addChannelByName(xchannel_name,
					store_id, createtime, author, lastupdate, markfordelete,
					seq, type);
			if (result == 1) {
				flag = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 获得当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#getEditChannelByID(java.lang.String
	 * )
	 */
	public List<XChannel> getEditChannelListByID(String edit_xchannel_id) {
		long xchannel_id = Long.valueOf(edit_xchannel_id);
		return findChannelMapper.getEditChannelListByID(xchannel_id);
	}

	/*
	 * 保存当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#editChannelByID(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String editChannelByID(String edit_xchannel_id,
			String edit_xchannel_name, String edit_seq, String edit_type) {
		String flag = "false";
		try {
			long xchannel_id = Long.valueOf(edit_xchannel_id);
			long seq = Long.valueOf(edit_seq);
			Timestamp lastupdate = new Timestamp(new Date().getTime());
			int result = findChannelMapper.editChannelByID(xchannel_id,
					edit_xchannel_name, lastupdate, seq, edit_type);
			if (result == 1) {
				flag = "success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 频道逻辑删除
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#deleteChannelByID(java.lang.String)
	 */
	public String deleteChannelByID(String edit_xchannel_id) {
		String flag = "false";
		try {
			long xchannel_id = Long.valueOf(edit_xchannel_id);
			Timestamp lastupdate = new Timestamp(new Date().getTime());
			int result = findChannelMapper.deleteChannelByID(xchannel_id,
					lastupdate);
			if (result == 1) {
				flag = "success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * 频道搜索数量
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#getSearchChannelListSize(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int getSearchChannelListCount(String search_xchannel_name,
			String search_type) {
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		String type = "";
		if ("A".equals(search_type)) {
			type = "";
		} else {
			type = search_type;
		}
		List<XChannel> xChannelSearchList = findChannelMapper
				.getSearchChannelListCount(store_id, search_xchannel_name, type);
		return xChannelSearchList.size();
	}

	/*
	 * 频道搜索
	 * 
	 * @see
	 * com.istore.common.core.dao.ChannelDao#searchChannelList(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List<XChannel> getSearchChannelList(String search_xchannel_name,
			String search_type, int startIndex, int endIndex) {
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		String type = "";
		if ("A".equals(search_type)) {
			type = "%%";
		} else {
			type = "%" + search_type + "%";
		}
		String xchannel_name = "%" + search_xchannel_name + "%";
		return findChannelMapper.getSearchChannelList(store_id, xchannel_name,
				type, startIndex, endIndex);
	}

}
