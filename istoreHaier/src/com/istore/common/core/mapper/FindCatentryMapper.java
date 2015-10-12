package com.istore.common.core.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;


import com.istore.common.core.bean.Catentry;
import com.istore.common.core.bean.FtpUser;
import com.istore.common.core.bean.Picture;


public interface FindCatentryMapper {
	
	public static final String GET_SEARNAMECOUNT_LIST = "select count(1) from catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID=xcatentstore.CATENTRY_ID and catentry.CATENTRY_ID=catentdesc.CATENTRY_ID and store.STORE_ID=xcatentstore.STORE_ID and catentdesc.LANGUAGE_ID=store.LANGUAGE_ID and xcatentstore.STORE_ID=#{storeid} and catentdesc.NAME like #{key}";
	/**
	 * 获得按名称搜索的商品数量
	 */
	@Select(GET_SEARNAMECOUNT_LIST)
	public int getSearchNameCount(@Param("storeid") long storeid,@Param("key") String key);
	
	public static final String GET_SEARCHNAME_LIST = "select CATENTRY_ID, PARTNUMBER , NAME from " +
			                                "(select rownum rn,CATENTRY_ID, PARTNUMBER , NAME  from(" +
			                                 "select catentry.CATENTRY_ID,catentry.PARTNUMBER, catentdesc.NAME from " +
			                                 "catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID = catentdesc.CATENTRY_ID and catentry.CATENTRY_ID =xcatentstore.CATENTRY_ID" +
			                                 " and catentdesc.LANGUAGE_ID =store.LANGUAGE_ID and store.store_id=xcatentstore.store_id and store.store_id=#{storeid} and catentdesc.NAME like #{key} ORDER BY CATENTRY_ID)) where rn > #{startIndex} and rn  <= #{endIndex}";
	/**
	 * 获得名称搜索的商品列表
	 * 
	 */	
	@Select(GET_SEARCHNAME_LIST)
	@Results(value = {
			@Result(column = "CATENTRY_ID", property = "catentryId", id = true),
			@Result(column = "PARTNUMBER", property = "partnumber"),
			@Result(column = "NAME", property = "name") ,
			@Result(column = "SHORTDESCRIPTION", property = "shortdescription") ,
			@Result(column = "LONGDESCRIPTION", property = "description") })
	public List<Catentry> getSearchName(@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex,@Param("storeid") long storeid,
			@Param("key") String key
	         );
	
	public static final String GET_SEARPARTCOUNT_LIST = "select count(1) from catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID=xcatentstore.CATENTRY_ID and catentry.CATENTRY_ID=catentdesc.CATENTRY_ID and store.STORE_ID=xcatentstore.STORE_ID and catentdesc.LANGUAGE_ID=store.LANGUAGE_ID and xcatentstore.STORE_ID=#{storeid} and catentry.PARTNUMBER like #{key}";
	/**
	 * 获得按款号搜索的商品数量
	 */
	@Select(GET_SEARPARTCOUNT_LIST)
	public int getSearchPartCount(@Param("storeid") long storeid,@Param("key") String key);
	
	public static final String GET_SEARCHPART_LIST = "select CATENTRY_ID, PARTNUMBER , NAME from " +
			                                "(select rownum rn,CATENTRY_ID, PARTNUMBER , NAME  from(" +
			                                 "select catentry.CATENTRY_ID,catentry.PARTNUMBER, catentdesc.NAME from " +
			                                 "catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID = catentdesc.CATENTRY_ID and catentry.CATENTRY_ID =xcatentstore.CATENTRY_ID" +
			                                 " and catentdesc.LANGUAGE_ID =store.LANGUAGE_ID and store.store_id=xcatentstore.store_id and store.store_id=#{storeid} and catentry.PARTNUMBER like #{key} ORDER BY CATENTRY_ID)) where rn > #{startIndex} and rn  <= #{endIndex}";
	/**
	 * 获得按款号搜索的商品列表
	 * 
	 */
	
	@Select(GET_SEARCHPART_LIST)
	@Results(value = {
			@Result(column = "CATENTRY_ID", property = "catentryId", id = true),
			@Result(column = "PARTNUMBER", property = "partnumber"),
			@Result(column = "NAME", property = "name") ,
			@Result(column = "SHORTDESCRIPTION", property = "shortdescription") ,
			@Result(column = "LONGDESCRIPTION", property = "description") })
	public List<Catentry> getSearchPart(@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex,@Param("storeid") long storeid,
			@Param("key") String key
	         );
	
	
	
	public static final String GET_CATCOUNT_LIST = "select count(1) from catentry,xcatentstore where catentry.CATENTRY_ID=xcatentstore.CATENTRY_ID and xcatentstore.STORE_ID=#{storeid}";
	/**
	 * 获得商品数量
	 */
	@Select(GET_CATCOUNT_LIST)
	public int getCatListCount(@Param("storeid") long storeid);
	
	public static final String GET_CAT_LIST = "select CATENTRY_ID, PARTNUMBER , NAME from " +
			                                "(select rownum rn,CATENTRY_ID, PARTNUMBER , NAME  from(" +
			                                 "select catentry.CATENTRY_ID,catentry.PARTNUMBER, catentdesc.NAME from " +
			                                 "catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID = catentdesc.CATENTRY_ID and catentry.CATENTRY_ID =xcatentstore.CATENTRY_ID" +
			                                 " and catentdesc.LANGUAGE_ID =store.LANGUAGE_ID and store.store_id=xcatentstore.store_id and store.store_id=#{storeid}  ORDER BY CATENTRY_ID)) where rn > #{startIndex} and rn  <= #{endIndex}";
	/**
	 * 获得商品列表
	 * 
	 */
	@Select(GET_CAT_LIST)
	@Results(value = {
			@Result(column = "CATENTRY_ID", property = "catentryId", id = true),
			@Result(column = "PARTNUMBER", property = "partnumber"),
			@Result(column = "NAME", property = "name") ,
			@Result(column = "SHORTDESCRIPTION", property = "shortdescription") ,
			@Result(column = "LONGDESCRIPTION", property = "description") })
	public List<Catentry> getCatList(@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex,@Param("storeid") long storeid);
	
	
	
	
	
	
	
	public static final String GET_SINCAT_LIST = "select catentry.CATENTRY_ID,catentry.PARTNUMBER, catentdesc.NAME,catentdesc.SHORTDESCRIPTION,catentdesc.LONGDESCRIPTION from " +
			                      "catentry,catentdesc,xcatentstore,store where catentry.CATENTRY_ID = catentdesc.CATENTRY_ID and catentry.CATENTRY_ID = xcatentstore.CATENTRY_ID and  " +
			                      "catentdesc.LANGUAGE_ID = store.LANGUAGE_ID and store.store_id=xcatentstore.store_id and catentry.CATENTRY_ID =#{catentryid} and store.store_id=#{storeid}";

	/**
	 * 获得单个商品信息
	 */
	@Select(GET_SINCAT_LIST)
	@Results(value = {
			@Result(column = "CATENTRY_ID", property = "catentryId", id = true),
			@Result(column = "PARTNUMBER", property = "partnumber"),
			@Result(column = "NAME", property = "name") ,
			@Result(column = "SHORTDESCRIPTION", property = "shortdescription") ,
			@Result(column = "LONGDESCRIPTION", property = "description") })
	public List<Catentry> getEditCatListByID(@Param("catentryid") long catentryid,@Param("storeid") long storeid);
	
	
	public static final String SELECT_LAN_ID = "select language_id from store where store_id = #{storeid}";
	/**
	 * 查出当前语言
	 */
	@Update(SELECT_LAN_ID)
	public Integer selectLan(
			@Param("storeid") long storeid
			);
	
	
	public static final String EDIT_CAT_BY_ID = "UPDATE catentry SET PARTNUMBER = #{partnumber} WHERE CATENTRY_ID = #{catentryid}";

	/**
	 * 保存当前编辑商品
	 */
	@Update(EDIT_CAT_BY_ID)
	public Integer editCatByID(@Param("catentryid") long catentryid,
			@Param("partnumber") String partnumber
			);
	public static final String EDIT_CATDESC_BY_ID = "UPDATE catentdesc SET SHORTDESCRIPTION = #{shortdesc}, LONGDESCRIPTION = #{description},NAME = #{name} WHERE CATENTRY_ID = #{catentryid} and LANGUAGE_ID = #{LAN}";

	/**
	 * 保存当前编辑商品
	 */
	@Update(EDIT_CATDESC_BY_ID)
	public Integer editCatdescByID(@Param("catentryid") long catentryid,
			@Param("shortdesc") String shortdesc,
			@Param("description") String description,
			@Param("name") String name,
			@Param("LAN") int LAN
			);
	
	
	

	@Select("${sql}")
	public Integer getCatentryListSize(@Param("param") Map<String, Long> param, @Param("sql")String sql);

	@Results(value={
			@Result(column="catentry_id_rel",property="catentryId"),
			@Result(column="name",property="name"),
			@Result(column="PARTNUMBER",property="partnumber"),
			@Result(column="SEQ", property="seq")
			})
	@Select("${sql}")
	public List<Catentry> getCatentryList(@Param("param") Map<String, Long> param, @Param("sql")String sql);

	@Select("${sql}")
	public int getAddCatentryListSize(@Param("param")Map<String, String> param, @Param("sql")String sql);

	@Results(value={
			@Result(column="catentry_id",property="catentryId"),
			@Result(column="name",property="name"),
			@Result(column="partnumber",property="partnumber")})
	@Select("${sql}")
	public List<Catentry> getAddCatentryList(@Param("param") Map<String, String> param,
			@Param("sql")String sql);

	@Results(value={@Result(column="catentry_id_rel",property="catentryId")})
	@Select("select catentry_id_rel from XCATENTREL where catentry_id=#{catentry_id} and store_id=#{store_id}")
	public List<Catentry> getExistCatentryId(@Param("store_id") long storeId, @Param("catentry_id")long catentryId);

	@Insert("insert into xcatentrel(catentry_id,catentry_id_rel,seq,store_id) values(#{catentryId},#{id},0,#{storeId})")
	public void insertCatentryRel(@Param("id") long id, @Param("storeId") long storeId, @Param("catentryId") long catentryId);

	@Delete("delete xcatentrel where catentry_id=#{catentry_id} and catentry_id_rel=#{catentry_id_rel} and store_id=#{storeId}")
	public void deleteCatentryRel(@Param("catentry_id") long catentry_id, @Param("catentry_id_rel") long catentry_id_rel,
			@Param("storeId") long storeId);

	@Update("update xcatentrel set seq=#{seq} where catentry_id=#{catentry_id} and catentry_id_rel=#{catentry_id_rel} and store_id=#{storeId} ")
	public void modifySeq(@Param("catentry_id") long catentry_id, @Param("catentry_id_rel") long catentry_id_rel, @Param("seq") long seq, @Param("storeId") long storeId);
	
	public static final String UPDATETIME_BY_ID  = "UPDATE CATENTRY SET LASTUPDATE=#{date} WHERE catentry_id=#{catentryId}";

	/**
	 * 保存当前时间
	 */
	@Update(UPDATETIME_BY_ID)
	public Integer updateTime(@Param("date") Date date,
			@Param("catentryId") String catentryId);
	
	
    public static final String UPDATESOLR_BY_ID  = "INSERT INTO TI_DELTA_CATENTRY(MASTERCATALOG_ID,CATENTRY_ID,ACTION) VALUES(#{catalogId},#{catentryId},#{type})";

	/**
	 * 保存增量索引
	 */
	@Update(UPDATESOLR_BY_ID)
	public void updateSolrIndex(@Param("catalogId") long catalogId,
			@Param("catentryId") long catentryId, @Param("type") String type);
	
	
	public static final String FINDROWS_BY_ID  = "SELECT COUNT(1) FROM TI_DELTA_CATENTRY WHERE CATENTRY_ID=#{catentryId}";

	/**
	 * 查询
	 */
	@Update(FINDROWS_BY_ID)
	public long findByCatentryId(@Param("catentryId") String catentryId);
	
	
	public static final String UPDATEIMAGE_BY_ID  = "UPDATE CATENTDESC SET THUMBNAIL=#{url} WHERE CATENTRY_ID=#{catentryId} AND LANGUAGE_ID=-1";

	/**
	 * 更新
	 */
	@Update(UPDATEIMAGE_BY_ID)
	public int updateImage(@Param("url") String url,
			@Param("catentryId") String catentryId);

	public static final String GET_FtpUser_LIST = "select HOST,PORT,USERNAME,PASSWORD,XPATH from w_ftp where type =#{type}";

	/**
	 * 获得单个商品列表
	 */
	@Select(GET_FtpUser_LIST)
	@Results(value = {
			@Result(column = "HOST", property = "host"),
			@Result(column = "PORT", property = "port"),
			@Result(column = "USERNAME", property = "username") ,
			@Result(column = "PASSWORD", property = "password"),  
			@Result(column = "XPATH", property = "remoteDir") })
      public FtpUser getFtpuser(@Param("type") int type);
	
	
	public static final String GET_PIC_LIST = "select CATENTRY_ID, IMGURL , SEQ,STORE_ID from (select rownum rn,CATENTRY_ID, IMGURL , SEQ,STORE_ID from(select CATENTRY_ID, IMGURL , SEQ,STORE_ID from xcatentimg where CATENTRY_ID=#{catentry_id} and  STORE_ID=#{storeid} ORDER BY SEQ DESC)) where rn > #{startIndex} and rn  <= #{endIndex}";

	/**
	 * 获得图片列表
	 */
	@Select(GET_PIC_LIST)
	@Results(value = {
			@Result(column = "CATENTRY_ID", property = "catentry_id" ),
			@Result(column = "IMGURL", property = "imgurl" ,id = true),
			@Result(column = "SEQ", property = "seq") ,
			@Result(column = "STORE_ID", property = "store_id") })
	public List<Picture> getPicList(@Param("startIndex") int startIndex,@Param("endIndex") int endIndex,@Param("catentry_id") long catentry_id,@Param("storeid") long storeid);
	
	public static final String GET_PICCOUNT_LIST = "select count(1) from xcatentimg where CATENTRY_ID=#{catentry_id} and STORE_ID=#{storeid}";
	/**
	 * 获得图片列表数量
	 */
	@Select(GET_PICCOUNT_LIST)
	public int findsize(@Param("catentry_id") long catentry_id,
			@Param("storeid") long storeid);
		
	public static final String GET_SIZE_LIST="SELECT COUNT(1) FROM CATENTRY WHERE CATENTRY_ID=#{catentryId} AND FIELD1=#{field}";

	@Select(GET_SIZE_LIST)
	public long findRows(@Param("catentryId") String catentryId,
			@Param("field") String field);
		
		
	public static final String GET_MAXSEQ_LIST = "select max(seq) from XCATENTIMG where CATENTRY_ID=#{catentryId} and STORE_ID=#{storeId}";

	@Select(GET_MAXSEQ_LIST)
	public int maxseq(@Param("catentryId") String catentryId,
			@Param("storeId") long storeId);
		
	public static final String GET_FIND_LIST = "select count(seq) from XCATENTIMG where CATENTRY_ID=#{catentryId} and STORE_ID=#{storeId}";

	@Select(GET_FIND_LIST)
	public int find(@Param("catentryId") String catentryId,
			@Param("storeId") long storeId);
		
		
	public static final String INSERT_IMG_LIST = "INSERT INTO XCATENTIMG(IMGURL,seq,store_id,CATENTRY_ID) VALUES(#{url},#{seq},#{storeId},#{catentryId})";

	/**
	 *添加图片
	 */
	@Update(INSERT_IMG_LIST)
	public int update(@Param("url") String url,
			@Param("catentryId") String catentryId, @Param("seq") int seq,
			@Param("storeId") long storeId);
		
	public static final String UPDATE_LIST = "UPDATE CATENTRY SET FIELD1=#{field1} WHERE catentry_id=#{catentryId}";

	@Update(UPDATE_LIST)
	public int updateact(@Param("field1") String field1,
			@Param("catentryId") String catentryId);

			/**
			 * 删图
			 */
	public static final String DELETEIMG_LIST ="delete xcatentimg where SEQ=#{Seq} and CATENTRY_ID = #{catentryId} and STORE_ID=#{storeId}";

	@Update(DELETEIMG_LIST)
	public int deleteImg(@Param("Seq") int Seq,
			@Param("catentryId") long catentryId, @Param("storeId") long storeId);

				/**
				 * 修改图片顺序
				 */
	public static final String UPDATEIMGSEQ_LIST ="select count(*) from xcatentimg where seq = #{Newseq} and CATENTRY_ID = #{catentryId} and STORE_ID=#{storeId}";

	@Select(UPDATEIMGSEQ_LIST)
	public int checkseq(@Param("Newseq") int Newseq,
			@Param("catentryId") long catentryId, @Param("storeId") long storeId);
				
	public static final String UPDATEIMG_LIST ="update xcatentimg set seq = #{Newseq} where seq = #{seq} and CATENTRY_ID = #{catentryId}  and STORE_ID=#{storeId}";

	@Update(UPDATEIMG_LIST)
	public int updateseq(@Param("Newseq") int Newseq, @Param("seq") int seq,
			@Param("catentryId") long catentryId, @Param("storeId") long storeId);
				
				
}
