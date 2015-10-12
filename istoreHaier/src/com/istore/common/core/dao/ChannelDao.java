/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao
 * @Title ChannelDao.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.dao;

import java.util.List;

import com.istore.common.core.bean.XChannel;

/**
 * @ClassName: ChannelDao.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:10:36
 */
public interface ChannelDao {
	/**
	 * 获得频道数量
	 * 
	 * @return
	 */
	public int getChannelListSize();

	/**
	 * 获得频道列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	public List<XChannel> getChannelList(int startIndex, int endIndex);

	/**
	 * 频道添加
	 * 
	 * @param xchannel_name
	 * @param seq
	 * @param tag
	 * @param type
	 * @return
	 */
	public String addChannelByName(String xchannel_name, String add_seq,
			String type);

	/**
	 * 获得当前编辑频道
	 * 
	 * @param edit_xchannel_id
	 * @return
	 */
	public List<XChannel> getEditChannelListByID(String edit_xchannel_id);

	/**
	 * 保存当前编辑频道
	 * 
	 * @param edit_xchannel_id
	 * @param edit_xchannel_name
	 * @param edit_seq
	 * @param edit_tag
	 * @param edit_type
	 * @return
	 */
	public String editChannelByID(String edit_xchannel_id,
			String edit_xchannel_name, String edit_seq, String edit_type);

	/**
	 * 频道逻辑删除
	 * 
	 * @param edit_xchannel_id
	 * @return
	 */
	public String deleteChannelByID(String edit_xchannel_id);

	/**
	 * 频道搜索数量
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @return
	 */
	public int getSearchChannelListCount(String search_xchannel_name,
			String search_type);

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
	public List<XChannel> getSearchChannelList(String search_xchannel_name,
			String search_type, int startIndex, int endIndex);

}
