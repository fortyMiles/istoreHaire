package com.istore.common.core.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.istore.common.core.bean.Catentry;
import com.istore.common.core.bean.CatentryImage;
import com.istore.common.core.bean.FtpUser;
import com.istore.common.core.bean.Picture;
public interface CatentryDao {
	/**
	 * 所有商品列表
	 * @return
	 */
	public int getCatListSize();
	public List<Catentry> getCatList(int startIndex, int endIndex);
	
	/**
	 * 搜索商品列表
	 * @return
	 */
	public int getCatListSize(String key,String type);
	public List<Catentry> getCatList(int startIndex, int endInde,String key,String type);
	
	
    public List<Catentry> getEditCatListByID(String catentryId);
	
	public String editCatByID(Catentry catentry);
	

	public List<Picture> getPicList(int startIndex, int endIndex,String catentid);
	
    public int findsize(String catentid);
	
	public boolean updateLastupdate(String catentryId);
	
	/***
	 * 更新商品列表页图片
	 * @param catentryImage
	 * @return
	 */
	public boolean updateImage(CatentryImage catentryImage);
	
	
	/***
	 * 更新solr索引
	 * @param catentryId
	 */
	public void updateSolrIndex(String catentryId);
	
	
	/***
	 * 通过商品ID查询 用于商品增量索引
	 * @param catentryId
	 * @return
	 */
	public long findByCatentryId(String catentryId);
	
	public FtpUser getFtpuser(int type);
	

	/***
	 * 保存商品图片信息
	 * @param catentryImage
	 * @return
	 */
	public boolean save(CatentryImage catentryImage);

	/****
	 * 查询商品的数量
	 * @param catentryId
	 * @param useage
	 * @return
	 */
	public long findRows(String catentryId,String field);
	/***
	 * 更新商品是否有详情页
	 * @param catentryId
	 */
	public boolean updateField(String catentryId,String field1);
	
/**
 * 删图
 */
	public String deleteImg(String seq,String catentry_id);
	
	/**
	 * 修改图片顺序
	 */
	public String updateimagseq(String newseq,String catentry_id,String seq);
	
	
	public int getCatentryListSize(long catentry_id, long storeId,
			long markforDelete);
	
	public List<Catentry> getCatentryList(long catentry_id, long storeId,
			long markforDelete, Integer startIndex, Integer endIndex);
	
	public int getAddCatentryListSize(String partnumber, String catentryName,
			String storeId);
	public List<Catentry> getAddCatentryList(String partnumber,
			String catentryName, Integer startIndex, Integer endIndex,
			String storeId);
	public List<Catentry> getexistCatentry(long storeId, long catentryId);
	
	public void insertSaleCatentry(long catentry_id_rel, long storeId, long catentryId) throws Exception;
	
	public void deleteCatentry(long catentry_id, long catentry_id_rel, long storeId);
	
	public void modifySeq(long catentry_id, long catentry_id_rel, long seq, long storeId);
}
