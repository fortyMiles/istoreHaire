/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mng.impl
 * @Title ChannelMngImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.XChannel;
import com.istore.common.core.dao.ChannelDao;
import com.istore.common.core.mng.ChannelMng;

/**
 * @ClassName: ChannelMngImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:07:53
 */
@Service
@Transactional
public class ChannelMngImpl implements ChannelMng {

	@Autowired
	ChannelDao channelDao;

	/*
	 * 获得新闻数量
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getChannelListSize()
	 */
	public int getChannelListSize() {
		return channelDao.getChannelListSize();
	}

	/*
	 * 获得新闻列表
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getChannelList()
	 */
	public List<XChannel> getChannelList(int startIndex, int endIndex) {
		return channelDao.getChannelList(startIndex, endIndex);
	}

	/*
	 * 新闻添加
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#addChannelByName(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addChannelByName(String xchannel_name, String add_seq,
			String type) {
		return channelDao.addChannelByName(xchannel_name, add_seq, type);
	}

	/*
	 * 获得当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#getEditChannelByID(java.lang.String
	 * )
	 */
	public List<XChannel> getEditChannelListByID(String edit_xchannel_id) {
		return channelDao.getEditChannelListByID(edit_xchannel_id);
	}

	/*
	 * 保存当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#editChannelByID(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String editChannelByID(String edit_xchannel_id,
			String edit_xchannel_name, String edit_seq, String edit_type) {
		return channelDao.editChannelByID(edit_xchannel_id, edit_xchannel_name,
				edit_seq, edit_type);
	}

	/*
	 * 频道逻辑删除
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#deleteChannelByID(java.lang.String)
	 */
	public String deleteChannelByID(String edit_xchannel_id) {
		return channelDao.deleteChannelByID(edit_xchannel_id);
	}

	/*
	 * 频道搜索数量
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#getSearchChannelListSize(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int getSearchChannelListCount(String search_xchannel_name,
			String search_type) {
		return channelDao.getSearchChannelListCount(search_xchannel_name,
				search_type);
	}

	/*
	 * 频道搜索列表
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#searchChannelList(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public List<XChannel> getSearchChannelList(String search_xchannel_name,
			String search_type, int startIndex, int endIndex) {
		return channelDao.getSearchChannelList(search_xchannel_name,
				search_type, startIndex, endIndex);
	}

}
