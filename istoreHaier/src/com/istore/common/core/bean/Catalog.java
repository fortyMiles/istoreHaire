package com.istore.common.core.bean;

/**
 * @author jiangtao
 */
public class Catalog {
	private Long catgroupId;// 目录ID
	private String identifier;// 编码
	private String name;// 名称
	private Integer published;// 是否对客户显示
	private Integer level;// 第几级菜单
	private Long catentryId;// 商品ID
	private String catentryName;// 商品名称
	private String partnumber;// 款号
	private String field1;// 图片路径

	public Long getCatgroupId() {
		return catgroupId;
	}

	public void setCatgroupId(Long catgroupId) {
		this.catgroupId = catgroupId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getCatentryId() {
		return catentryId;
	}

	public void setCatentryId(Long catentryId) {
		this.catentryId = catentryId;
	}

	public String getCatentryName() {
		return catentryName;
	}

	public void setCatentryName(String catentryName) {
		this.catentryName = catentryName;
	}

	public String getPartnumber() {
		return partnumber;
	}

	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

}
