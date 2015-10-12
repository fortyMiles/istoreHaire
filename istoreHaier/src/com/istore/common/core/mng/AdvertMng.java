/**
 * 
 */
package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.FTP;

/**
 * @author wangyan
 *
 */
public interface AdvertMng {
	//获取广告列表
	public List<Advert> getAdvertUpList(int advertType,int startIndex,int endIndex,int storeId);
	//获取FTP信息
	public List<FTP> getFTPinfo();
	//新增广告位
	
	public String advertImageAdd(int priority,String url,String imageUrl,String desc,int type,String prodouctGroup,String storeId);
	//获取现有广告位的最大值
	public String findMaxPriority(String type);
	//根据ID获取广告位
	public List<Advert> getAdvertById(int advertId);
	//修改广告位
	public String updateAdvert(int advertId,String finalfilename,String link,String desc,String prodouctGroup);
	
	public String deleteAdvert(int advertId);
	//上移和下移
	public String updateAdvertPrority(int id,String priority);
	//生成HTML
	public int createAdvertHtml(String usage,String templatePath,List ftplist);
	
	public int getAdvertListSize(int advertType,String storeId);
   
}
