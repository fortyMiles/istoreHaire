package com.istore.common.core.bean;
/**
 * 属性对象
 */
public class Attr{
	//attr
	private Long attrId;//属性id
	private String identifier;//标识
	private String attrtypeId;//类型id
	private Long attrdiciId;//10001
	private Integer storeentId;//storeid，11651
	private Integer  displayable;//是否显示
	private Integer searchable;//是否能搜索
	private Integer comparable;//是否能比较
	private Integer field1;
	private Integer field2;
	private String field3;
	private Integer facetable;//是否可构面
	//attrdesc
	private String name;//属性名称
	private String description;//属性描述
	
	private Long attrvalId;//属性值id
	private String attrval;//属性值
	private int  attrvalsq;
	
	
	
	public Long getAttrvalId() {
		return attrvalId;
	}
	public void setAttrvalId(Long attrvalId) {
		this.attrvalId = attrvalId;
	}
	public String getAttrval() {
		return attrval;
	}
	public void setAttrval(String attrval) {
		this.attrval = attrval;
	}
	public int getAttrvalsq() {
		return attrvalsq;
	}
	public void setAttrvalsq(int attrvalsq) {
		this.attrvalsq = attrvalsq;
	}
	public Long getAttrId() {
		return attrId;
	}
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getAttrtypeId() {
		return attrtypeId;
	}
	public void setAttrtypeId(String attrtypeId) {
		this.attrtypeId = attrtypeId;
	}
	public Long getAttrdiciId() {
		return attrdiciId;
	}
	public void setAttrdiciId(Long attrdiciId) {
		this.attrdiciId = attrdiciId;
	}
	public Integer getStoreentId() {
		return storeentId;
	}
	public void setStoreentId(Integer storeentId) {
		this.storeentId = storeentId;
	}
	public Integer getDisplayable() {
		return displayable;
	}
	public void setDisplayable(Integer displayable) {
		this.displayable = displayable;
	}
	public Integer getSearchable() {
		return searchable;
	}
	public void setSearchable(Integer searchable) {
		this.searchable = searchable;
	}
	public Integer getField1() {
		return field1;
	}
	public void setField1(Integer field1) {
		this.field1 = field1;
	}
	public Integer getField2() {
		return field2;
	}
	public void setField2(Integer field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public Integer getFacetable() {
		return facetable;
	}
	public void setFacetable(Integer facetable) {
		this.facetable = facetable;
	}
	public Integer getComparable() {
		return comparable;
	}
	public void setComparable(Integer comparable) {
		this.comparable = comparable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
