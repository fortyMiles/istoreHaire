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

import com.istore.common.core.bean.Catgroup;
import com.istore.common.core.bean.XReferences;
import com.istore.common.core.dao.ReferencesDao;
import com.istore.common.core.mng.ReferencesMng;

/**
 * @ClassName: ChannelMngImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:07:53
 */
@Service
@Transactional
public class ReferencesMngImpl implements ReferencesMng {

	@Autowired
	ReferencesDao referencesDao;

	/*
	 * 获得样板工程数量
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getChannelListSize()
	 */
	public int getReferencesListSize(long xchannel_id) {
		return referencesDao.getReferencesListSize(xchannel_id);
	}

	/*
	 * 获得样板工程列表
	 * 
	 * @see com.istore.common.core.mng.ChannelMng#getChannelList()
	 */
	@SuppressWarnings("rawtypes")
	public List getReferencesList(long xchannel_id, int startIndex, int endIndex) {
		return referencesDao.getReferencesList(xchannel_id, startIndex,
				endIndex);
	}

	/*
	 * 样板工程添加
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#addChannelByName(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String addReferencesBySerialNumber(String serialnumber,
			String country, String projectplace, String description,
			String type, String installtime, String installseries,
			String installdetails, String totalcapacity, String keycapacity,
			String add_seq, String group, String catgroup, String pictures,
			String add_xchannel_id) {
		return referencesDao.addReferencesBySerialNumber(serialnumber, country,
				projectplace, description, type, installtime, installseries,
				installdetails, totalcapacity, keycapacity, add_seq, group,
				catgroup, pictures, add_xchannel_id);
	}

	/*
	 * 获得当前编辑样板工程
	 * 
	 * @see
	 * com.istore.common.core.mng.ChannelMng#getEditChannelByID(java.lang.String
	 * )
	 */
	public List<XReferences> getEditReferencesListByID(
			String edit_xreferences_id) {
		return referencesDao.getEditReferencesListByID(edit_xreferences_id);
	}

	/*
	 * 保存当前编辑频道
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#editReferencessByID(java.lang
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
		return referencesDao.editReferencessByID(edit_xreferences_id,
				serialnumber, country, projectplace, description, type,
				installtime, installseries, installdetails, totalcapacity,
				keycapacity, edit_seq, group, catgroup, pictures);
	}

	/*
	 * 样板新闻逻辑删除
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#deleteReferencesByID(java.lang
	 * .String)
	 */
	public String deleteReferencesByID(String edit_xreferences_id) {
		return referencesDao.deleteReferencesByID(edit_xreferences_id);
	}

	/*
	 * 样板工程提交神审核
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#referReferencesByID(java.lang
	 * .String)
	 */
	public String referReferencesByID(String edit_xreferences_id) {
		return referencesDao.referReferencesByID(edit_xreferences_id);
	}

	/*
	 * 样板工程搜索数量
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#getSearchReferencesListCount
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public int getSearchReferencesListCount(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity) {
		return referencesDao.getSearchChannelListCount(search_xchannel_id,
				search_serialnumber, search_country, search_projectplace,
				search_description, search_type, search_installtime,
				search_installseries, search_installdetails,
				search_totalcapacity, search_keycapacity);
	}

	/*
	 * 样板工程搜索列表
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#getSearchReferencesList(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public List<XReferences> getSearchReferencesList(String search_xchannel_id,
			String search_serialnumber, String search_country,
			String search_projectplace, String search_description,
			String search_type, String search_installtime,
			String search_installseries, String search_installdetails,
			String search_totalcapacity, String search_keycapacity,
			int startIndex, int endIndex) {
		return referencesDao.getSearchChannelList(search_xchannel_id,
				search_serialnumber, search_country, search_projectplace,
				search_description, search_type, search_installtime,
				search_installseries, search_installdetails,
				search_totalcapacity, search_keycapacity, startIndex, endIndex);
	}

	/*
	 * 查看当前样板工程
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#getViewReferencesListByID(java
	 * .lang.String)
	 */
	public List<XReferences> getViewReferencesListByID(
			String edit_xreferences_id) {
		return referencesDao.getViewReferencesListByID(edit_xreferences_id);
	}

	/*
	 * 样板工程审核通过
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#approveReferencesByID(java.lang
	 * .String, java.lang.String)
	 */
	public String approveReferencesByID(String edit_xreferences_id,
			String xcomment) {
		return referencesDao.approveReferencesByID(edit_xreferences_id);
	}

	/*
	 * 样板工程审核拒绝
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#rejectReferencesByID(java.lang
	 * .String, java.lang.String)
	 */
	public String rejectReferencesByID(String edit_xreferences_id,
			String xcomment) {
		return referencesDao
				.rejectReferencesByID(edit_xreferences_id, xcomment);
	}

	/*
	 * 获得一级目录
	 * 
	 * @see com.istore.common.core.mng.ReferencesMng#getCatgroupByStoreId(long)
	 */
	public List<Catgroup> getCatgroupByStoreID(long store_id) {
		return referencesDao.getCatgroupByStoreID(store_id);
	}

	/*
	 * 样板工程审核拒绝原因
	 * 
	 * @see
	 * com.istore.common.core.mng.ReferencesMng#getRejectcaseByReferencesID(
	 * java.lang.String)
	 */
	public String getRejectcaseByReferencesID(String edit_xreferences_id) {
		return referencesDao.getRejectcaseByReferencesID(edit_xreferences_id);
	}

}
