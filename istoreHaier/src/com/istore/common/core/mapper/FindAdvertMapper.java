/**
 * 
 */
package com.istore.common.core.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.FTP;
import com.istore.common.core.provider.AdvertSqlProvider;

//import com.istore.common.core.provider.ChannelSqlProvider;

/**
 * @author wangyan
 * 
 */
public interface FindAdvertMapper {
	public static final String ADD_ADVERT = "INSERT INTO x_espotimgrel(ID, PRIORITY,URL, IMG_ADDRESS ,DESCPT,TYPE,GROUPID,STOREID) VALUES(SEQ12_x_espotimgrel.NEXTVAL, #{priority}, #{url}, #{imageUrl}, #{desc}, #{type},#{prodouctGroup},#{storeId})";

	public static final String UPDATE_ADVERT = "UPDATE x_espotimgrel SET IMG_ADDRESS=#{finalfilename},URL=#{link},DESCPT=#{desc},GROUPID=#{prodouctGroup} where ID=#{advertId}";

	public static final String DELETE_ADVERT = "DELETE FROM x_espotimgrel WHERE ID=#{advertId}";

	public static final String UPDATE_PRIORITY = "UPDATE x_espotimgrel SET PRIORITY=#{priority} where ID=#{advertId}";
 
	public static final String SELECT_ADVERT="select ID,url,TYPE,DESCPT,IMG_ADDRESS,PRIORITY from (select  ID,url,TYPE,DESCPT,IMG_ADDRESS,PRIORITY,ROWNUM ROWINDEX  from x_espotimgrel WHERE type=#{advertType} and storeId=#{storeId}) a where a. ROWINDEX >#{startIndex} and  a.ROWINDEX<#{endIndex} order by priority";
	
	//type=3
	public static final String SELECT_ADVERT3="select id,url,img_address,priority,type,descpt ,name  from (select a.id,a.url,a.img_address,a.priority,a.type,a.descpt ,b.name ,ROWNUM ROWINDEX from  x_espotimgrel a  left join catgrpdesc b on a.groupid= b.catgroup_id left join storecgrp c on b.catgroup_id=c.catgroup_id  where a.type=#{advertType} and a.storeid=#{storeId}) a where a. ROWINDEX >#{startIndex} and  a.ROWINDEX<#{endIndex} order by a.priority";
	
	@Select(SELECT_ADVERT)
	@Results(value = { @Result(column = "ID", property = "id", id = true),
			@Result(column = "TYPE", property = "type"),
			@Result(column = "DESCPT", property = "title"),
			@Result(column = "URL", property = "url"),
			@Result(column = "IMG_ADDRESS", property = "image"),
			@Result(column = "PRIORITY", property = "priority") })
	public List<Advert> getAdvertList(@Param("advertType") int advertType,@Param("startIndex") int startIndex,@Param("endIndex") int endIndex,@Param("storeId") int storeId);
	
	
	//type=3 获取列表
	
	@Select(SELECT_ADVERT3)
	@Results(value = { @Result(column = "ID", property = "id", id = true),
			@Result(column = "TYPE", property = "type"),
			@Result(column = "DESCPT", property = "title"),
			@Result(column = "URL", property = "url"),
			@Result(column = "IMG_ADDRESS", property = "image"),
			@Result(column = "PRIORITY", property = "priority"),
			@Result(column = "name", property = "name")
	})
	public List<Advert> getAdvertList3(@Param("advertType") int advertType,@Param("startIndex") int startIndex,@Param("endIndex") int endIndex,@Param("storeId") int storeId);
	

	@SelectProvider(type = AdvertSqlProvider.class, method = "getFTPinfo")
	@Results(value = { @Result(column = "ID", property = "id", id = true),
			@Result(column = "HOST", property = "host"),
			@Result(column = "PORT", property = "port"),
			@Result(column = "USERNAME", property = "username"),
			@Result(column = "PASSWORD", property = "password"),
			@Result(column = "XPATH", property = "xpath") })
	public List<FTP> getFTPinfo();

	@SelectProvider(type = AdvertSqlProvider.class, method = "findMaxPriority")
	public String findMaxPriority(String type);

	@Insert(ADD_ADVERT)
	public Integer addAdvertByName(@Param("priority") Integer priority,
			@Param("url") String url, @Param("imageUrl") String imageUrl,
			@Param("desc") String desc, @Param("type") Integer type,@Param("prodouctGroup") String prodouctGroup,
			@Param("storeId") String storeId);

	@SelectProvider(type = AdvertSqlProvider.class, method = "getAdvertById")
	@Results(value = { @Result(column = "DESCPT", property = "title"),
			@Result(column = "URL", property = "url"),
			@Result(column = "IMG_ADDRESS", property = "image"),
			@Result(column = "GROUPID", property = "prodouctGroup")})
	public List<Advert> getAdvertById(int advertId);

	@Update(UPDATE_ADVERT)
	public Integer updateAdvertById(@Param("advertId") Integer advertId,
			@Param("finalfilename") String finalfilename,
			@Param("link") String link, @Param("desc") String desc,@Param("prodouctGroup") String prodouctGroup);

	@Delete(DELETE_ADVERT)
	public Integer deleteAdvertById(@Param("advertId") Integer advertId);

	@Update(UPDATE_PRIORITY)
	public Integer updateAdvertPrority(@Param("advertId") Integer advertId,
			@Param("priority") String priority);

	@SelectProvider(type = AdvertSqlProvider.class, method = "getImageMapByEId")
	@Results(value = { @Result(column = "ID", property = "id", id = true),
			@Result(column = "TYPE", property = "type"),
			@Result(column = "DESCPT", property = "title"),
			@Result(column = "URL", property = "url"),
			@Result(column = "IMG_ADDRESS", property = "image"),
			@Result(column = "PRIORITY", property = "priority") })
	public List<Advert> getImageMapByEId(@Param("advertId") int advertId,
			@Param("type") int type);
	
	@SelectProvider(type = AdvertSqlProvider.class, method = "getAdvertListSize")
	public int getAdvertListSize(@Param("advertType") int advertType,@Param("advertType") String storeId);
}
