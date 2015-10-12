package com.istore.common.core.bean;

public class CatentryImage {

	// 主键
	private String id;
	// 商品编号
	private String catentryId;
	// 路片路径
	private String imageUrl;

	// 更新时间
	private String lastupdate;
	// 描述
	private String description;
	// 标识
	private int useage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatentryId() {
		return catentryId;
	}

	public void setCatentryId(String catentryId) {
		this.catentryId = catentryId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUseage() {
		return useage;
	}

	public void setUseage(int useage) {
		this.useage = useage;
	}

}
