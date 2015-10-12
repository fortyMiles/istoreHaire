/**
 * 
 */
package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.AdvertDao;
import com.istore.common.core.mng.AdvertMng;

/**
 * @author wangyan
 *
 */
@Service
@Transactional
public class AdvertMngImpl implements AdvertMng{
	
	@Autowired
	AdvertDao advertDao;

	public List<Advert> getAdvertUpList(int advertType,int startIndex,int endIndex,int storeId) {
		// TODO Auto-generated method stub
		return advertDao.getAdvertUpList(advertType,startIndex,endIndex,storeId);
	}

	public List<FTP> getFTPinfo() {
		// TODO Auto-generated method stub
		return advertDao.getFTPinfo();
	}

	public String advertImageAdd(int priority, String url, String imageUrl,
			String desc, int type,String prodouctGroup,String storeId) {
		// TODO Auto-generated method stub
		return advertDao.advertImageAdd(priority, url, imageUrl, desc, type,prodouctGroup,storeId);
	}

	public String findMaxPriority(String type) {
		// TODO Auto-generated method stub
		return advertDao.findMaxPriority(type);
	}

	public List<Advert> getAdvertById(int advertId) {
		// TODO Auto-generated method stub
		return advertDao.getAdvertById(advertId);
	}

	public String updateAdvert(int advertId, String finalfilename, String link,
			String desc,String prodouctGroup) {
		// TODO Auto-generated method stub
		return advertDao.updateAdvert(advertId, finalfilename, link, desc,prodouctGroup);
	}

	public String deleteAdvert(int advertId) {
		// TODO Auto-generated method stub
		return advertDao.deleteAdvert(advertId);
	}

	public String updateAdvertPrority(int id, String priority) {
		// TODO Auto-generated method stub
		return advertDao.updateAdvertPrority(id, priority);
	}

	public int createAdvertHtml(String usage, String templatePath,List ftplist) {
		// TODO Auto-generated method stub
		return advertDao.createAdvertHtml(usage, templatePath,ftplist);
	}

	public int getAdvertListSize(int advertType,String storeId) {
		// TODO Auto-generated method stub
		return advertDao.getAdvertListSize(advertType,storeId);
	}
	

}
