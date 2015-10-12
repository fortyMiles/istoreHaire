package com.istore.common.core.bean;

import java.sql.Timestamp;

public class Catentry {

		// 商品编号
		private long catentryId;
		// 商品partnumber
		private String partnumber;
		//商品名称
		private String name;
		// 是否可购买
		private String buyable;
		// 是否显示
		private String displayable;
		// 默认列表页的图片
		private String imgUrl;
		//上一次更新时间
		private Timestamp lastupdate;
		
		//销售目录
		private String catalog;
		
		//橱窗类型
		private int type;
		
		//详细描述
		private String description;
		
		//详细短描述
		private String shortdescription;
		
		//序列
		private long seq;
		public String getShortdescription() {
			return shortdescription;
		}

		public void setShortdescription(String shortdescription) {
			this.shortdescription = shortdescription;
		}

		//零售价
		private double listPrice;
		//配送价
		private double offerPrice;
		//品牌
		private String brand;
		//计量单位
		private String unit;
		//厚度
		private String thickness;
		
		//东莞
		private String dgStatus;
		
		//天津
		private String tjStatus;
		
		//类别
		private String categorycode;
		//是否为辅料
		private String isSupmat;
		
		//折扣
		private double discount;
		
		public String getCategorycode()
	    {
	        return categorycode;
	    }

	    public void setCategorycode(String categorycode)
	    {
	        this.categorycode = categorycode;
	    }

	   

		public long getCatentryId() {
			return catentryId;
		}

		public void setCatentryId(long catentryId) {
			this.catentryId = catentryId;
		}

		public String getPartnumber() {
			return partnumber;
		}

		public void setPartnumber(String partnumber) {
			this.partnumber = partnumber;
		}

		public String getBuyable() {
			return buyable;
		}

		public void setBuyable(String buyable) {
			this.buyable = buyable;
		}

		public String getDisplayable() {
			return displayable;
		}

		public void setDisplayable(String displayable) {
			this.displayable = displayable;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}



		public Timestamp getLastupdate() {
			return lastupdate;
		}

		public void setLastupdate(Timestamp lastupdate) {
			this.lastupdate = lastupdate;
		}

		public String getCatalog() {
			return catalog;
		}

		public void setCatalog(String catalog) {
			this.catalog = catalog;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getListPrice() {
			return listPrice;
		}

		public void setListPrice(double listPrice) {
			this.listPrice = listPrice;
		}

		public double getOfferPrice() {
			return offerPrice;
		}

		public void setOfferPrice(double offerPrice) {
			this.offerPrice = offerPrice;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getThickness() {
			return thickness;
		}

		public void setThickness(String thickness) {
			this.thickness = thickness;
		}

		public String getDgStatus() {
			return dgStatus;
		}

		public void setDgStatus(String dgStatus) {
			this.dgStatus = dgStatus;
		}

		public String getTjStatus() {
			return tjStatus;
		}

		public void setTjStatus(String tjStatus) {
			this.tjStatus = tjStatus;
		}

		public String getIsSupmat() {
			return isSupmat;
		}

		public void setIsSupmat(String isSupmat) {
			this.isSupmat = isSupmat;
		}

		public double getDiscount() {
			return discount;
		}

		public void setDiscount(double discount) {
			this.discount = discount;
		}

		public long getSeq() {
			return seq;
		}

		public void setSeq(long seq) {
			this.seq = seq;
		}	
		

}
