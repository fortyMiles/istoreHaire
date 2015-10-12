package com.istore.common.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.Catalog;

/**
 * 
 * @author jiangtao
 *
 */
public interface CatalogMapper {

	
	@Select("${sql}")
	@Options(useCache = true, flushCache = false, timeout = 10000)
	public Integer getCatgroupListSize(@Param("param") Map<String, Long> param,
			@Param("sql") String sql);

	@Results(value = {
			@Result(column = "CATGROUP_ID", property = "catgroupId"),
			@Result(column = "IDENTIFIER", property = "identifier"),
			@Result(column = "NAME", property = "name"),
			@Result(column = "PUBLISHED", property = "published"),
			@Result(column = "field1", property = "field1") })
	@Select("${sql}")
	@Options(useCache = true, flushCache = false, timeout = 10000)
	public List<Catalog> getCatgroupList(
			@Param("param") Map<String, String> param, @Param("sql") String sql);

	@Select("select optcounter from KEYS where TABLENAME='catgroup'")
	public int getIdFromKeys();

	@Insert("${sql}")
	public void insertCommon(
			@SuppressWarnings("rawtypes") @Param("param") Map param,
			@Param("sql") String sql);

	@Results(value = {
			@Result(column = "CATGROUP_ID", property = "catgroupId"),
			@Result(column = "IDENTIFIER", property = "identifier"),
			@Result(column = "NAME", property = "name") })
	@Select("${sql}")
	public List<Catalog> getCatgroupDetail(
			@Param("param") Map<String, String> param, @Param("sql") String sql);

	@Select("${sql}")
	public Integer getCatGroupDetailSize(
			@Param("param") Map<String, String> param, @Param("sql") String sql);

	@Update("update keys set optcounter=optcounter+1 where TABLENAME='catgroup'")
	public void updateKeys();

	@Update("update catgrpdesc set name=#{catgroupName} where catgroup_id=#{catgroupId}")
	public void updateCatgroupName(@Param("catgroupName") String catgroupName,
			@Param("catgroupId") Integer catgroupId);

	@Select("${sql}")
	public Integer checkIsGoodsExist(@Param("param") Map<String, Long> param,
			@Param("sql") String sql);

	@Delete("${sql}")
	public void deleteCatgroup(@Param("param") Map<String, Long> param,
			@Param("sql") String sql);

	@Select("select count(1) from catgpenrel cp join catentry ct on cp.catentry_id=ct.catentry_id join catentdesc cd on cp.catentry_id=cd.catentry_id where cp.catalog_id=#{catalog_id} and cp.catgroup_id=#{catgroup_id}  and ct.markfordelete=#{markforDelete}")
	public int getCatentryListSize(@Param("catgroup_id") long catgroup_id,
			@Param("catalog_id") long catalogid,
			@Param("markforDelete") long markforDelete);

	@Results(value = {
			@Result(column = "catentry_id", property = "catentryId"),
			@Result(column = "name", property = "catentryName"),
			@Result(column = "partnumber", property = "partnumber") })
	@Select("${sql}")
	public List<Catalog> getgetCatentryList(
			@Param("param") Map<String, Long> param, @Param("sql") String sql);

	@Select("${sql}")
	public int getAddCatentryListSize(
			@Param("param") Map<String, String> param, @Param("sql") String sql);

	@Results(value = {
			@Result(column = "catentry_id", property = "catentryId"),
			@Result(column = "name", property = "catentryName"),
			@Result(column = "partnumber", property = "partnumber") })
	@Select("${sql}")
	public List<Catalog> getAddCatentryList(
			@Param("param") Map<String, String> param, @Param("sql") String sql);

	@Results(value = { @Result(column = "catentry_id", property = "catentryId") })
	@Select("select catentry_id from catgpenrel where catalog_id=#{catalogid} and catgroup_id=#{catgroupid}")
	public List<Catalog> getExistCatentryId(@Param("catalogid") long catalogid,
			@Param("catgroupid") long catgroupid);

	@Delete("delete from catgpenrel where catalog_id=#{catalog_id} and catgroup_id=#{catgroup_id} and catentry_id=#{catentry_id}")
	public void deleteCatentry(@Param("catgroup_id") long catgroup_id,
			@Param("catentry_id") long catentry_id,
			@Param("catalog_id") long catalog_id);

	@Select("${sql}")
	public Integer countDelta(@Param("id") Long id,
			@Param("sql") String count_sql);

	@Insert("${sql}")
	public void insertDelta(@Param("id") Long id,
			@Param("sql") String insert_sql);

	@Update("update catgroup set FIELD1=#{filename} where catgroup_id=#{id}")
	public void updateImageUrl(@Param("id") long id,
			@Param("filename") String filename);

	@Select("select LANGUAGE_ID from store where STORE_ID=#{storeId}")
	public Integer getLanguageId(@Param("storeId") Long storeId);

	@Select("select catalog_id from storecat where storeent_id=#{store_id} and mastercatalog=0")
	public Long getCatalogIdByStoreId(@Param("store_id") long store_id);
}
