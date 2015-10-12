/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mapper
 * @Title FindChannelMapper.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;
import com.istore.common.core.provider.AttrSqlProvider;
import com.istore.common.core.provider.ChannelSqlProvider;

/**
 * @author tgq
 */
public interface FindAttrMapper {

	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	@SelectProvider(type = AttrSqlProvider.class, method = "getAttrListCount")
	@Results(value = {
			@Result(column = "attr_id", property = "attrId", id = true),
			@Result(column = "name", property = "name"),
			@Result(column = "description", property = "description")
			 })
	public List<Attr> getAttrListCount();

	public static final String GET_ATTR_LIST = "select attr_id, name,description from (select rownum rn,attr.attr_id,attrdesc.name,attrdesc.description from attrdesc,attr where attr.FIELD1 = 1 and attrdesc.language_id = -1 and attrdesc.attr_id = attr.attr_id) where rn > #{startIndex} and rn  <= #{endIndex} ORDER BY attr_id";

	/**
	 * 获得定义列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_ATTR_LIST)
	@Results(value = {
			@Result(column = "attr_id", property = "attrId", id = true),
			@Result(column = "name", property = "name"),
			@Result(column = "description", property = "description") })
	public List<Attr> getAttrList(@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);

	
	
	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	@SelectProvider(type = AttrSqlProvider.class, method = "getAttrdescListCount")
	@Results(value = {
			@Result(column = "attr_id", property = "attrId", id = true),
			@Result(column = "name", property = "name"),
			@Result(column = "description", property = "description")
			 })
	public List<Attr> getAttrdescListCount();

	public static final String GET_ATTRDESC_LIST = "select attr_id, name,description from (select rownum rn,attr.attr_id,attrdesc.name,attrdesc.description from attrdesc,attr where attr.FIELD1 = 2 and attrdesc.language_id = -1 and attrdesc.attr_id = attr.attr_id) where rn > #{startIndex} and rn  <= #{endIndex} ORDER BY attr_id";

	/**
	 * 获得描述列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_ATTRDESC_LIST)
	@Results(value = {
			@Result(column = "attr_id", property = "attrId", id = true),
			@Result(column = "name", property = "name"),
			@Result(column = "description", property = "description") })
	public List<Attr> getAttrdescList(@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);
	
	public static final String DELETE_ATTE_BY_ID = "delete attr WHERE attr_id = #{attrid}";

	/**
	 * 属性删除
	 * 
	 * @param
	 * @param 
	 * @return
	 */
	@Update(DELETE_ATTE_BY_ID)
	public int deleteAttrByID(@Param("attrid") long attrid);
	
	/**
	 * 获得当前编辑属性
	 * 
	 * @param attr_id
	 * @return
	 */
	@SelectProvider(type = AttrSqlProvider.class, method = "getEditAttrByID")
	@Results(value = {
			@Result(column = "attr_id", property = "attrId", id = true),
			@Result(column = "name", property = "name"),
			@Result(column = "description", property = "description"),
			 })
	public List<Attr> getEditAttrListByID(
			@Param("attr_id") long attr_id);
	
	
	public static final String EDIT_Attr_BY_ID = "UPDATE attrdesc SET name = #{name}, description = #{description} WHERE attr_id = #{attr_id} and language_id = -1";

	/**
	 * 保存当前编辑属性
	 */
	@Update(EDIT_Attr_BY_ID)
	public Integer editAttrByID(@Param("attr_id") long attr_id,
			@Param("name") String name,
			 @Param("description") String description
			);
	
	public static final String ADD_CHANNEL_BY_NAME = "INSERT INTO attrdesc(ATTR_ID,LANGUAGE_ID,ATTRTYPE_ID,NAME,DESCRIPTION,QTYUNIT_ID,OPTCOUNTER) VALUES(#{attr_id},-1,'STRING',#{name},#{description},'C62',1)";

	/**
	 * 属性添加
	 * 
	
	 */
	@Insert(ADD_CHANNEL_BY_NAME)
	public Integer addAttrByName(
			@Param("attr_id") long attr_id,
			@Param("name") String name,
			@Param("description") String description
			);
	public static final String ADD_MAX_BY_NAME = "select max(attr_id) from attr";
	/**
	 * 生成ID
	 */
	@Select(ADD_MAX_BY_NAME)
	public Long selectmaxid();

	public static final String ADD_ATTR_BY_NAME = "INSERT INTO attr(ATTR_ID,IDENTIFIER,ATTRTYPE_ID,ATTRDICT_ID,STOREENT_ID,SEQUENCE,DISPLAYABLE,SEARCHABLE,COMPARABLE,FIELD1,OPTCOUNTER,ATTRUSAGE,FACETABLE) VALUES(#{attr_id},#{identy},'STRING',10001,10001,0.0,1,0,0,#{type},1,1,0)";

	/**
	 * 向attr插入数据
	 * 
	 */
	@Insert(ADD_ATTR_BY_NAME)
	public Integer addAttr(
			@Param("attr_id") long attr_id,		
			@Param("identy") String identy,
			@Param("type") int attruseage
			);	
	
	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	@SelectProvider(type = AttrSqlProvider.class, method = "getAttrvalListCount")
	@Results(value = {
			@Result(column = "ATTRVAL_ID", property = "attrvalId", id = true),
			@Result(column = "VALUE", property = "attrval"),
			@Result(column = "SEQUENCE", property = "attrvalsq")
			 })
	public List<Attr> getAttrvalListCount(Long attrid);
	
	public static final String GET_ATTRVAL_LIST = "select ATTRVAL_ID, VALUE,SEQUENCE from (select rownum rn,attrval.ATTRVAL_ID,attrvaldesc.VALUE,attrvaldesc.SEQUENCE from attrdesc,attrval,attrvaldesc where attrdesc.language_id = -1 and attrval.ATTRVAL_ID = attrvaldesc.ATTRVAL_ID and attrdesc.attr_id = attrvaldesc.attr_id and attrvaldesc.language_id = -1 and attrdesc.attr_id = #{attrid}) where rn > #{startIndex} and rn  <= #{endIndex} ORDER BY attrval_id";
	
	/**
	 * 获得属性值列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	@Select(GET_ATTRVAL_LIST)
	@Results(value = {
			@Result(column = "ATTRVAL_ID", property = "attrvalId", id = true),
			@Result(column = "VALUE", property = "attrval"),
			@Result(column = "SEQUENCE", property = "attrvalsq") })
	public List<Attr> getAttrvalList(@Param("attrid") long attrid,@Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);
	
	
	
	
	public static final String DELETE_ATTEVAL_BY_ID = "delete from attrval where attrval_id= #{attrval_Id}";

	/**
	 * 属性zhi删除
	 * 
	 */
	@Update(DELETE_ATTEVAL_BY_ID)
	public int deleteAttrvalByID(@Param("attrval_Id") long attrval_Id);
	
	
	/**
	 * 获得当前编辑属性值
	 * 
	 * @param attr_id
	 * @return
	 */
	@SelectProvider(type = AttrSqlProvider.class, method = "getEditAttrvalByID")
	@Results(value = {
			@Result(column = "ATTRVAL_ID", property = "attrvalId", id = true),
			@Result(column = "value", property = "attrval"),
			@Result(column = "SEQUENCE", property = "attrvalsq"),
			 })
	public List<Attr> getEditAttrvalListByID(
			@Param("attrval_id") long attrval_id);
	
	
	public static final String EDIT_AttrVAL_BY_ID = "UPDATE attrvaldesc SET value= #{value}, SEQUENCE = #{squ} WHERE attrval_id = #{attrval_id} and language_id = -1";

	/**
	 * 保存当前编辑属性值
	 */
	@Update(EDIT_AttrVAL_BY_ID)
	public Integer editAttrvalByID(@Param("attrval_id") long attrval_id,
			@Param("value") String value,
			 @Param("squ") int squ
			);
	/**
	 * 添加属性值
	 */
	
	public static final String ADD_ATTRVAL_BY_NAME = "INSERT INTO attrval(ATTRVAL_ID,ATTR_ID,IDENTIFIER,VALUSAGE,STOREENT_ID,OPTCOUNTER) VALUES(#{attrval_id},#{attrid},#{identy},1,10051,1)";
	@Insert(ADD_ATTRVAL_BY_NAME)
	public Integer addAttrval(
			@Param("attrval_id") long attrval_id,
			@Param("identy") String identy,
			@Param("attrid") String attrid
			);
	public static final String ADD_MAXVALID_BY_NAME = "select max(attrval_id) from attrval";
	/**
	 * 生成ID
	 */
	@Select(ADD_MAXVALID_BY_NAME)
	public Long selectvalmaxid();

	public static final String ADD_ATTRVALDESC_BY_NAME = "INSERT INTO attrvaldesc(ATTRVAL_ID,LANGUAGE_ID,ATTR_ID,VALUE,VALUSAGE,SEQUENCE,STRINGVALUE,QTYUNIT_ID,OPTCOUNTER) VALUES(#{attrval_id},-1,#{attr_id},#{value},1,#{squ},#{value},'C62',1)";

	/**
	 * 向attrdesc插入数据
	 * 
	 */
	@Insert(ADD_ATTRVALDESC_BY_NAME)
	public Integer addAttrvaldesc(
			@Param("attrval_id") long attrval_id,		
			@Param("attr_id") long attr_id,
			@Param("value") String value,
			@Param("squ") int squ
			);	
	
	
	
}
