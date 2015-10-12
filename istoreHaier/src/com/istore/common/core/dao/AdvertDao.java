/**
 * 
 */
package com.istore.common.core.dao;

import java.util.List;

import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.FTP;

/**
 * @author wangyan
 *
 */
public interface AdvertDao {
	
	public List<Advert> getAdvertUpList(int advertType,int startIndex,int endIndex,int storeId);
	
	public List<FTP> getFTPinfo();
	public String advertImageAdd(int priority,String url,String imageUrl,String desc,int type,String prodouctGroup,String storeId);
	
	public String findMaxPriority(String type);
	
	//根据ID获取广告位
	public List<Advert> getAdvertById(int advertId);
	//修改广告位
	public String updateAdvert(int advertId,String finalfilename,String link,String desc,String prodouctGroup);
	//删除广告位
	public String deleteAdvert(int advertId);
	//上移广告位
	public String updateAdvertPrority(int id,String priority);
	
	//生成HTML
	public int createAdvertHtml(String usage,String templatePath,List list);
	
	public int getAdvertListSize(int advertType,String storeId);

}
