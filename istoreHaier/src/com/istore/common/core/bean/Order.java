package com.istore.common.core.bean;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int count;
	private String orderId;			//订单Id
	private String totalProduct;    //订单总价
	private String orderTime;		//下单时间
	private String status;			//订单状态
	private String memberId;		//会员Id
	private String quality;         //数量
	private String addressId;       //订单配送地址
	private String orderitemsId;	//子订单Id
	private String catentryId;		//产品目录Id
	private String partnum;			//商品sku
	private String price;           //商品单价
	private String lastUpdate;		//子订单的修改时间
	
	
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(String totalProduct) {
		this.totalProduct = totalProduct;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getOrderitemsId() {
		return orderitemsId;
	}
	public void setOrderitemsId(String orderitemsId) {
		this.orderitemsId = orderitemsId;
	}
	public String getCatentryId() {
		return catentryId;
	}
	public void setCatentryId(String catentryId) {
		this.catentryId = catentryId;
	}
	public String getPartnum() {
		return partnum;
	}
	public void setPartnum(String partnum) {
		this.partnum = partnum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
