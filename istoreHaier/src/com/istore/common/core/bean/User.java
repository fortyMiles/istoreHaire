package com.istore.common.core.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private long usersId;
	private String logonId;
	private String status;//审核状态
	private String customer_id;//
	private String customer_name;//客户名称
	private String lastUpdate;//最后更新数据信息时间
	private String type;//注册人员类型
	private String createtime;//注册时间
	private String email;//邮箱
	private String phone;//手机号
	private String country;//国家
	private String city;//城市
	private String zipcode;//邮箱地址
	private String  customerNumber;
	private String  bankName;
	private String bankRegion;
	private String  streetRoom;
	private String  cityStreetRoom;
	private String  backGategory;
	private String  swift;
	private String  backgroup;
	
	

	public String getCityStreetRoom() {
		return cityStreetRoom;
	}

	public void setCityStreetRoom(String cityStreetRoom) {
		this.cityStreetRoom = cityStreetRoom;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankRegion() {
		return bankRegion;
	}

	public void setBankRegion(String bankRegion) {
		this.bankRegion = bankRegion;
	}

	public String getStreetRoom() {
		return streetRoom;
	}

	public void setStreetRoom(String streetRoom) {
		this.streetRoom = streetRoom;
	}

	public String getBackGategory() {
		return backGategory;
	}

	public void setBackGategory(String backGategory) {
		this.backGategory = backGategory;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getBackgroup() {
		return backgroup;
	}

	public void setBackgroup(String backgroup) {
		this.backgroup = backgroup;
	}

	public long getUsersId() {
		return usersId;
	}

	public void setUsersId(long usersId) {
		this.usersId = usersId;
	}

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	public final String getCustomer_id() {
		return customer_id;
	}

	public final void setCustomer_id(String customerId) {
		customer_id = customerId;
	}

	public final String getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final String getCustomer_name() {
		return customer_name;
	}

	public final void setCustomer_name(String customerName) {
		customer_name = customerName;
	}

	public final String getCreatetime() {
		return createtime;
	}

	public final void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final String getCountry() {
		return country;
	}

	public final void setCountry(String country) {
		this.country = country;
	}

	public final String getCity() {
		return city;
	}

	public final void setCity(String city) {
		this.city = city;
	}

	public final String getZipcode() {
		return zipcode;
	}

	public final void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
