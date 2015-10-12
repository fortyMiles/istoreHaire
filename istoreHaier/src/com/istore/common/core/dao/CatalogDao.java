package com.istore.common.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.istore.common.core.bean.Catalog;

/**
 * 
 * @author jiangtao
 * 
 */
public interface CatalogDao {

	/**
	 * 获得一级目录列表数.
	 * 
	 * @param storeId
	 * @return
	 */
	public Integer getCatgroupListSize(Long storeId);

	/**
	 * 获得一级目录列表. 关于level参数说明，当不需要分页获得一级目录列表时该参数需传0，需要分页时可传任意值.
	 * 
	 * @param storeId
	 * @param level
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<Catalog> getCatgroupList(Long storeId, Integer level,
			Integer startIndex, Integer endIndex);

	/**
	 * 目录添加 关于此方法level参数说明，添加一级目录时需传值1，添加二级以下（包括二级）目录时传值2.
	 * 
	 * @param storeId
	 * @param name
	 * @param catgroupIdParent
	 * @param level
	 * @param sequence
	 * @throws Exception
	 */
	public void addCatgroup(Long storeId, String name, Long catgroupIdParent,
			Integer level, double sequence) throws Exception;

	/**
	 * 获得某一级目录下二级目录列表.
	 * 
	 * @param storeId
	 * @param catgroupId
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<Catalog> getCatgroupDetail(String storeId, String catgroupId,
			Integer startIndex, Integer endIndex);

	/**
	 * 获得某一级目录下二级目录的总数.
	 * 
	 * @param storeId
	 * @param catgroupId
	 * @return
	 */
	public Integer getCatGroupDetailSize(String storeId, String catgroupId);

	/**
	 * 目录名称更新
	 * 
	 * @param catgroupName
	 *            待更新名称
	 * @param catgroupId
	 *            待更新目录ID
	 * @throws Exception
	 *             更新异常
	 */
	public void updateCatgroupName(String catgroupName, Integer catgroupId)
			throws Exception;

	/**
	 * 查询该目录（包括子目录）下是否存在商品.
	 * 
	 * @param catgroupId
	 *            目录ID.
	 * @param level
	 *            目录等级.
	 * @return 存在的商品总数.
	 */
	public Integer isGoodsExist(Long catgroupId, Integer level);

	/**
	 * 删除目录
	 * 
	 * @param catgroupId
	 *            待删除目录ID
	 * @param level
	 *            目录等级
	 * @throws SQLException
	 *             删除异常
	 */
	public void deleteCatgroup(Long catgroupId, Integer level)
			throws SQLException;

	/**
	 * 获得已挂商品数量.
	 * 
	 * @param catgroup_id
	 *            目录ID
	 * @param store_id
	 * @param markfordelete
	 *            商品的状态
	 * @return 已挂商品数量.
	 */
	public int getCatentryListSize(long catgroup_id, long store_id,
			long markfordelete);

	/**
	 * 获得已挂商品列表.
	 * 
	 * @param catgroup_id
	 *            目录ID
	 * @param store_id
	 * @param markforDelete
	 *            商品状态.
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<Catalog> getCatentryList(long catgroup_id, long store_id,
			long markforDelete, long startIndex, long endIndex);

	/**
	 * 获得可挂商品数量.
	 * 
	 * @param partnumber
	 *            商品款号（搜索条件）
	 * @param catentryName
	 *            商品名称（搜索条件）
	 * @param storeId
	 * @return
	 */
	public int getAddCatentryListSize(String partnumber, String catentryName,
			String storeId);

	/**
	 * 获得可挂商品列表
	 * 
	 * @param partnumber
	 *            商品款号（搜索条件）
	 * @param catentryName
	 *            商品名称（搜索条件）
	 * @param startIndex
	 * @param endIndex
	 * @param storeId
	 * @return
	 */
	public List<Catalog> getAddCatentryList(String partnumber,
			String catentryName, int startIndex, int endIndex, String storeId);

	/**
	 * 商品挂入目录
	 * 
	 * @param id
	 *            商品ID
	 * @param storeid
	 * @param catgroupid
	 *            对应三级目录ID.
	 * @throws Exception
	 *             挂入目录异常
	 */
	public void insertSaleCatentry(long id, long storeid, long catgroupid)
			throws Exception;

	/**
	 * 查询已经挂入目录的商品.
	 * 
	 * @param storeid
	 * @param catgroupid
	 * @return
	 */
	public List<Catalog> getexistCatentry(long storeid, long catgroupid);

	/**
	 * 删除商品与目录的关联.
	 * 
	 * @param catgroup_id
	 * @param catentry_id
	 * @param store_id
	 * @throws Exception
	 */
	public void deleteCatentry(long catgroup_id, long catentry_id, long store_id)
			throws Exception;

	/**
	 * 图片上传.
	 * 
	 * @param catgroup_id
	 * @param filename
	 * @throws Exception
	 */
	public void updateImageUrl(String catgroup_id, String filename)
			throws Exception;
}
