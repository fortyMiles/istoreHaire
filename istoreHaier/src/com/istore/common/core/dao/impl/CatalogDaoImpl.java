package com.istore.common.core.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.istore.common.core.bean.Catalog;
import com.istore.common.core.dao.CatalogDao;
import com.istore.common.core.mapper.CatalogMapper;
import com.istore.common.core.util.DeltaUtils;

@Repository
public class CatalogDaoImpl implements CatalogDao {

	@Autowired
	private CatalogMapper mapper;

	@Autowired
	private DeltaUtils deltaUtils;

	/**
	 * 获得一级目录列表数.
	 * 
	 * @param storeId
	 * @return
	 */
	public Integer getCatgroupListSize(Long storeId) {
		String sql = "select count(1) from (select a.catgroup_id,a.IDENTIFIER from "
				+ "catgroup a join cattogrp b on a.catgroup_id=b.catgroup_id join storecgrp c "
				+ "on a.catgroup_id=c.catgroup_id where b.catalog_id=#{param.catalog_id} and c.storeent_id=#{param.storeent_id})";
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("storeent_id", storeId);
		long catalog_id = mapper.getCatalogIdByStoreId(storeId);
		param.put("catalog_id", catalog_id);
		return mapper.getCatgroupListSize(param, sql);
	}

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
			Integer startIndex, Integer endIndex) {
		String sql = "select * from (select a.catgroup_id,a.IDENTIFIER,d.NAME,d.PUBLISHED,a.field1,rownum rn "
				+ "from catgroup a join cattogrp b on a.catgroup_id=b.catgroup_id join storecgrp c "
				+ "on a.catgroup_id=c.catgroup_id join catgrpdesc d on a.catgroup_id=d.catgroup_id "
				+ "where b.catalog_id=#{param.catalog_id} and c.storeent_id=#{param.storeId} order by a.lastupdate) t ";
		long catalog_id = mapper.getCatalogIdByStoreId(storeId);
		if (level != 0) {
			sql += "where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex}";
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("storeId", storeId + "");
		param.put("startIndex", startIndex + "");
		param.put("endIndex", endIndex + "");
		param.put("catalog_id", catalog_id + "");
		List<Catalog> list = mapper.getCatgroupList(param, sql);

		for (int i = 0; i < list.size(); i++) {
			String filePath = list.get(i).getField1();
			filePath = "<img width='300' height='150' src='http://10.176.0.40/"
					+ filePath + "?v=" + Math.random() + "' />";
			list.get(i).setField1(filePath);
		}
		return list;
	}

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
	@SuppressWarnings("unchecked")
	public void addCatgroup(Long storeId, String name, Long catgroupIdParent,
			Integer level, double sequence) throws Exception {
		@SuppressWarnings("rawtypes")
		Map param = new HashMap();
		long catgroupId = (mapper.getIdFromKeys() + 1);
		mapper.updateKeys();
		String identifier = name + "_" + catgroupId + "";
		Long memberId = Long.valueOf("700000000000000"
				+ storeId.toString().substring(1));
		param.put("catgroupId", catgroupId);
		param.put("memberId", memberId + "");
		param.put("identifier", identifier);
		param.put("markforDelete", 0);
		param.put("lastUpdate", new Date());
		param.put("optCounter", 1);

		String insertCatgroupSql = "insert into catgroup(CATGROUP_ID,MEMBER_ID,IDENTIFIER,MARKFORDELETE,LASTUPDATE,OPTCOUNTER) "
				+ "values(#{param.catgroupId},#{param.memberId},#{param.identifier},#{param.markforDelete},#{param.lastUpdate},#{param.optCounter})";
		mapper.insertCommon(param, insertCatgroupSql);
		deltaUtils.deltaBuild(catgroupId, "catgroup");
		param = new HashMap<String, String>();
		param.put("catgroupId", catgroupId);

		int language_id = mapper.getLanguageId(storeId);
		param.put("languageid", language_id);
		param.put("published", 1);
		param.put("name", name);
		String insertCatgrpdescSql = "insert into catgrpdesc(LANGUAGE_ID,CATGROUP_ID,NAME,PUBLISHED) "
				+ "values(#{param.languageid},#{param.catgroupId},#{param.name},#{param.published})";
		mapper.insertCommon(param, insertCatgrpdescSql);
		deltaUtils.deltaBuild(catgroupId, "catgroup");

		param = new HashMap<String, String>();
		param.put("optCounter", 0);
		param.put("storeId", storeId);
		param.put("catgroupId", catgroupId);
		String insertStorecgrpSql = "insert into storecgrp(STOREENT_ID,CATGROUP_ID,OPTCOUNTER) "
				+ "values(#{param.storeId},#{param.catgroupId},#{param.catgroupId})";
		mapper.insertCommon(param, insertStorecgrpSql);
		deltaUtils.deltaBuild(catgroupId, "catgroup");
		long catalog_id = mapper.getCatalogIdByStoreId(storeId);
		// 一级目录
		if (level == 1) {
			param = new HashMap<String, String>();

			param.put("catalogId", catalog_id);
			param.put("catgroupId", catgroupId);
			param.put("sequence", sequence);
			param.put("optCounter", 0);
			String insertCattogrpSql = "insert into cattogrp(CATALOG_ID,CATGROUP_ID,SEQUENCE,OPTCOUNTER) "
					+ "values(#{param.catalogId},#{param.catgroupId},#{param.sequence},#{param.optCounter})";
			mapper.insertCommon(param, insertCattogrpSql);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
		}
		// 二级目录或多级目录(如果以后有多级目录的话)
		if (level == 2) {
			param = new HashMap<String, String>();
			param.put("catGroupIdParent", catgroupIdParent);
			param.put("catgroupId", catgroupId);
			param.put("catalogId", catalog_id);
			param.put("sequence", sequence);
			param.put("optCounter", 0);
			String insertCatgrprelSql = "insert into catgrprel(CATGROUP_ID_PARENT,CATGROUP_ID_CHILD,CATALOG_ID,SEQUENCE,OPTCOUNTER) "
					+ "values(#{param.catGroupIdParent},#{param.catgroupId},#{param.catalogId},#{param.sequence},#{param.optCounter})";
			mapper.insertCommon(param, insertCatgrprelSql);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
		}

	}

	/**
	 * 获得二级目录列表.
	 * 
	 * @param storeId
	 * @param catgroupId
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<Catalog> getCatgroupDetail(String storeId, String catgroupId,
			Integer startIndex, Integer endIndex) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("storeId", storeId);
		param.put("catgroupId", catgroupId);
		param.put("startIndex", startIndex + "");
		param.put("endIndex", endIndex + "");
		String sql = "select * from (select a.catgroup_id,a.identifier,b.name,a.field1,rownum rn "
				+ "from catgroup a join catgrpdesc b on a.catgroup_id=b.catgroup_id join "
				+ "(select CATGROUP_ID_CHILD from catgrprel where CATGROUP_ID_PARENT=#{param.catgroupId}) c "
				+ "on a.catgroup_id=c.catgroup_id_child join storecgrp d on a.catgroup_id=d.catgroup_id "
				+ "where d.storeent_id=#{param.storeId} and b.PUBLISHED=1 order by a.lastupdate) t where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex}";

		return mapper.getCatgroupDetail(param, sql);
	}

	/**
	 * 获得某一级目录下二级目录的总数.
	 * 
	 * @param storeId
	 * @param catgroupId
	 * @return
	 */
	public Integer getCatGroupDetailSize(String storeId, String catgroupId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("storeId", storeId);
		param.put("catgroupId", catgroupId);
		String sql = "select count(1) from catgroup a join catgrpdesc b on a.catgroup_id=b.catgroup_id join "
				+ "(select CATGROUP_ID_CHILD from catgrprel where CATGROUP_ID_PARENT=#{param.catgroupId}) c  on "
				+ "a.catgroup_id=c.catgroup_id_child join storecgrp d on a.catgroup_id=d.catgroup_id where "
				+ "d.storeent_id=#{param.storeId} and b.PUBLISHED=1";
		return mapper.getCatGroupDetailSize(param, sql);
	}

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
			throws Exception {
		mapper.updateCatgroupName(catgroupName, catgroupId);
		deltaUtils.deltaBuild(new Long(catgroupId), "catgroup");
	}

	/**
	 * 查询该目录（包括其子目录）下是否存在商品.
	 * 
	 * @param catgroupId
	 *            目录ID.
	 * @param level
	 *            目录等级.
	 * @return 存在的商品总数.
	 */
	public Integer isGoodsExist(Long catgroupId, Integer level) {
		Integer count = 0;
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("catgroup_id", catgroupId);
		// 检查一级目录下的商品
		if (level == 1) {
			String sql = "select count(1) from catgpenrel t1 join catentry t2 on t1.catentry_id=t2.catentry_id "
					+ "where t1.catgroup_id in (select c.catgroup_id_child from catgroup a join catgrprel b on "
					+ "a.catgroup_id=b.catgroup_id_parent join catgrprel c on b.catgroup_id_child=c.catgroup_id_parent "
					+ "where a.catgroup_id=#{param.catgroup_id}) and t2.markfordelete=0";
			count = mapper.checkIsGoodsExist(param, sql);
		} else if (level == 2) {// 检查二级目录下的商品
			String sql = "select count(1) from catgpenrel t1 join catentry t2 on t1.catentry_id=t2.catentry_id "
					+ "where t1.catgroup_id in (select b.catgroup_id_child from catgroup a join catgrprel b on "
					+ "a.catgroup_id=b.catgroup_id_parent where a.catgroup_id=#{param.catgroup_id}) and t2.markfordelete=0";
			count = mapper.checkIsGoodsExist(param, sql);
		} else if (level == 3) {// 检查三级目录下的商品
			String sql = "select count(1) from catgpenrel t1 join catentry t2 on t1.catentry_id=t2.catentry_id where t1.catgroup_id=#{param.catgroup_id} and t2.markfordelete=0";
			count = mapper.checkIsGoodsExist(param, sql);
		}
		return count;
	}

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
			throws SQLException {
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("catgroupId", catgroupId);
		if (level == 1) {
			// 删除三级目录
			String deleteCatgroup3 = "delete from catgroup where catgroup_id in (select c.catgroup_id_child from catgroup a join catgrprel b on a.catgroup_id=b.catgroup_id_parent join catgrprel c on b.catgroup_id_child=c.catgroup_id_parent where catgroup_id=#{param.catgroupId})";
			mapper.deleteCatgroup(param, deleteCatgroup3);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
			// 删除二级目录
			String deleteCatgroup2 = "delete from catgroup where catgroup_id in (select b.catgroup_id_child from catgroup a join catgrprel b on a.catgroup_id=b.catgroup_id_parent where catgroup_id=#{param.catgroupId})";
			mapper.deleteCatgroup(param, deleteCatgroup2);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
			// 删除一级目录
			String deleteCatgroup1 = "delete from catgroup where catgroup_id = #{param.catgroupId}";
			mapper.deleteCatgroup(param, deleteCatgroup1);
			deltaUtils.deltaBuild(catgroupId, "catgroup");

		} else if (level == 2) {
			// 删除三级目录
			String deleteCatgroup3 = "delete from catgroup where catgroup_id in (select b.catgroup_id_child from catgroup a join catgrprel b on a.catgroup_id=b.catgroup_id_parent where catgroup_id=#{param.catgroupId})";
			mapper.deleteCatgroup(param, deleteCatgroup3);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
			// 删除二级目录
			String deleteCatgroup2 = "delete from catgroup where catgroup_id = #{param.catgroupId}";
			mapper.deleteCatgroup(param, deleteCatgroup2);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
		} else if (level == 3) {
			// 删除三级目录
			String deleteCatgroup3 = "delete from catgroup where catgroup_id = #{param.catgroupId}";
			mapper.deleteCatgroup(param, deleteCatgroup3);
			deltaUtils.deltaBuild(catgroupId, "catgroup");
		}
	}

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
			long markforDelete) {
		long catalogid = mapper.getCatalogIdByStoreId(store_id);
		return mapper
				.getCatentryListSize(catgroup_id, catalogid, markforDelete);
	}

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
			long markforDelete, long startIndex, long endIndex) {
		Map<String, Long> param = new HashMap<String, Long>();
		long catalogid = mapper.getCatalogIdByStoreId(store_id);
		param.put("catgroup_id", catgroup_id);
		param.put("catalogid", catalogid);
		param.put("markforDelete", markforDelete);
		param.put("startIndex", startIndex);
		param.put("endIndex", endIndex);
		String sql = "select * from (select cp.catentry_id,cd.name,ct.partnumber,rownum rn from catgpenrel cp join catentry ct on "
				+ "cp.catentry_id=ct.catentry_id join catentdesc cd on cp.catentry_id=cd.catentry_id "
				+ "where cp.catalog_id=#{param.catalogid} and cp.catgroup_id=#{param.catgroup_id}  and "
				+ "ct.markfordelete=#{param.markforDelete}) t where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex}";
		return mapper.getgetCatentryList(param, sql);
	}

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
			String storeId) {
		String sql = "select count(1) from catentry a join catentdesc b on a.catentry_id=b.catentry_id join catgpenrel c "
				+ "on a.catentry_id=c.catentry_id join XCATENTSTORE d on a.catentry_id=d.catentry_id where "
				+ "a.catenttype_id='ProductBean' and c.catalog_id=10001 and a.markfordelete=0 and d.STORE_ID=#{param.storeId}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnumber", partnumber.toUpperCase());
		param.put("catentryName", catentryName.toUpperCase());
		param.put("storeId", storeId);
		if (!partnumber.equals("%%")) {
			sql += " and upper(partnumber) like #{param.partnumber}";
		}
		if (!catentryName.equals("%%")) {
			sql += " and upper(name) like #{param.catentryName}";
		}
		return mapper.getAddCatentryListSize(param, sql);
	}

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
			String catentryName, int startIndex, int endIndex, String storeId) {
		String sql = "select * from (select a.catentry_id,a.partnumber,b.name,rownum rn from catentry a join catentdesc b on a.catentry_id=b.catentry_id join "
				+ "catgpenrel c on a.catentry_id=c.catentry_id join XCATENTSTORE d on a.catentry_id=d.catentry_id where a.catenttype_id='ProductBean' "
				+ "and c.catalog_id=10001 and a.markfordelete=0 and d.STORE_ID=#{param.storeId}) t where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex} ";
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnumber", partnumber.toUpperCase());
		param.put("catentryName", catentryName.toUpperCase());
		param.put("startIndex", startIndex + "");
		param.put("endIndex", endIndex + "");
		param.put("storeId", storeId);
		if (!partnumber.equals("%%")) {
			sql += " and upper(partnumber) like #{param.partnumber}";
		}
		if (!catentryName.equals("%%")) {
			sql += " and upper(name) like #{param.catentryName}";
		}

		return mapper.getAddCatentryList(param, sql);
	}

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
	@SuppressWarnings("unchecked")
	public void insertSaleCatentry(long id, long storeid, long catgroupid)
			throws Exception {
		@SuppressWarnings("rawtypes")
		Map param = new HashMap();
		param.put("id", id);
		long catalogid = mapper.getCatalogIdByStoreId(storeid);
		param.put("catalogid", catalogid);
		param.put("catgroupid", catgroupid);
		param.put("lastupdate", new Timestamp(new Date().getTime()));
		String sql = "insert into catgpenrel(CATGROUP_ID,CATALOG_ID,CATENTRY_ID,SEQUENCE,LASTUPDATE) "
				+ "values(#{param.catgroupid},#{param.catalogid},#{param.id},0,#{param.lastupdate})";

		mapper.insertCommon(param, sql);
	}

	/**
	 * 查询已经挂入商品的ID
	 */
	public List<Catalog> getexistCatentry(long storeid, long catgroupid) {
		long catalogid = mapper.getCatalogIdByStoreId(storeid);
		return mapper.getExistCatentryId(catalogid, catgroupid);
	}

	/**
	 * 删除商品与目录的关联.
	 * 
	 * @param catgroup_id
	 * @param catentry_id
	 * @param store_id
	 * @throws Exception
	 */
	public void deleteCatentry(long catgroup_id, long catentry_id, long store_id)
			throws Exception {
		long catalog_id = mapper.getCatalogIdByStoreId(store_id);
		mapper.deleteCatentry(catgroup_id, catentry_id, catalog_id);
	}

	/**
	 * 图片上传.
	 * 
	 * @param catgroup_id
	 * @param filename
	 * @throws Exception
	 */
	public void updateImageUrl(String catgroup_id, String filename)
			throws Exception {
		filename = "catalog/images/" + filename;
		mapper.updateImageUrl(Long.parseLong(catgroup_id), filename);
	}

}
