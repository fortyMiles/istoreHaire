/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mng
 * @Title ChannelMng.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.Catgroup;
import com.istore.common.core.bean.XReferences;

/**
 * @ClassName: ChannelMng.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:09:34
 */
public interface ReferencesMng {

	/**
	 * 获得频道数量
	 * 
	 * @param xchannel_id
	 * @return
	 */
	public int getReferencesListSize(long xchannel_id);

	/**
	 * 获得频道列表
	 * 
	 * @param xchannel_id
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getReferencesList(long xchannel_id, int startIndex, int endIndex);

	/**
	 * 样板工程添加
	 * 
	 * @param serialnumber
	 * @param country
	 * @param projectplace
	 * @param description
	 * @param type
	 * @param installtime
	 * @param installseries
	 * @param installdetails
	 * @param add_totalcapacity
	 * @param keycapacity
	 * @param add_seq
	 * @param group
	 * @param pictures
	 * @param add_xchannel_id
	 * @param add_xchannel_id2
	 * @return
	 */
	public String addReferencesBySerialNumber(String serialnumber,
			String country, String projectplace, String description,
			String type, String installtime, String installseries,
			String installdetails, String totalcapacity, String keycapacity,
			String add_seq, String group, String catgroup, String pictures,
			String add_xchannel_id);

	/**
	 * 获得当前编辑频道
	 * 
	 * @param edit_xreferences_id
	 * @return
	 */
	public List<XReferences> getEditReferencesListByID(
			String edit_xreferences_id);

	/**
	 * 保存当前编辑样板工程
	 * 
	 * @param edit_xreferences_id
	 * @param serialnumber
	 * @param country
	 * @param projectplace
	 * @param description
	 * @param type
	 * @param installtime
	 * @param installseries
	 * @param installdetails
	 * @param totalcapacity
	 * @param keycapacity
	 * @param edit_seq
	 * @param group
	 * @param pictures
	 * @return
	 */
	public String editReferencessByID(String edit_xreferences_id,
			String serialnumber, String country, String projectplace,
			String description, String type, String installtime,
			String installseries, String installdetails, String totalcapacity,
			String keycapacity, String edit_seq, String group, String catgroup,
			String pictures);

	/**
	 * 频道逻辑删除
	 * 
	 * @param edit_xreferences_id
	 * @return
	 */
	public String deleteReferencesByID(String edit_xreferences_id);

	/**
	 * 样板工程提交审核
	 * 
	 * @param edit_xreferences_id
	 * @return
	 */
	public String referReferencesByID(String edit_xreferences_id);

	/**
	 * 样板工程搜索数量
	 * 
	 * @param search_xchannel_id
	 * @param search_serialnumber
	 * @param search_country
	 * @param search_projectplace
	 * @param search_description
	 * @param search_type
	 * @param search_installtime
	 * @param search_installseries
	 * @param search_installdetails
	 * @param search_totalcapacity
	 * @param search_keycapacity
	 * @return
	 */
	public int getSearchReferencesListCount(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity);

	

	/**
	 * 频道搜索列表
	 * 
	 * @param search_xchannel_id
	 * @param search_serialnumber
	 * @param search_country
	 * @param search_projectplace
	 * @param search_description
	 * @param search_type
	 * @param search_installtime
	 * @param search_installseries
	 * @param search_installdetails
	 * @param search_totalcapacity
	 * @param search_keycapacity
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<XReferences> getSearchReferencesList(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity,
			int startIndex, int endIndex);

	/**
	 * 查看当前样板工程
	 * 
	 * @param edit_xreferences_id
	 * @return
	 */
	public List<XReferences> getViewReferencesListByID(
			String edit_xreferences_id);

	/**
	 * 样板工程审核通过
	 * 
	 * @param edit_xreferences_id
	 * @param xcomment
	 * @return
	 */
	public String approveReferencesByID(String edit_xreferences_id,
			String xcomment);

	/**
	 * 样板工程审核拒绝
	 * 
	 * @param edit_xreferences_id
	 * @param xcomment
	 * @return
	 */
	public String rejectReferencesByID(String edit_xreferences_id,
			String xcomment);

	/**
	 * 获得一级目录
	 * 
	 * @param store_id
	 * @return
	 */
	public List<Catgroup> getCatgroupByStoreID(long store_id);

	/**
	 * 样板工程审核拒绝原因
	 * 
	 * @param edit_xreferences_id
	 * @return
	 */
	public String getRejectcaseByReferencesID(String edit_xreferences_id);

}
