package com.istore.common.core.bean;

import java.io.Serializable;

public class Pipeline implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int count;
	private String popeline_id;
	private String status ;
	private String name ;
	private String short_name ;
	private String long_desc ;
	private String address_name ;
	private String last_update ;
	private String submit_date ;
	private String orders_id ;
	private String expected_date ;
	private String confidence ;
	private String xcomment ;
	private String phone ;
	private String email ;
	private String project_type ;
	private String project_area ;
	private String location ;
	private String products_request ;
	private String draft_information ;
	private String contact_title;
	private String n_time;
	public String getN_time() {
		if(n_time == null){
			n_time = "";
		}
		return n_time;
	}
	public void setN_time(String nTime) {
		n_time = nTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPopeline_id() {
		return popeline_id;
	}
	public void setPopeline_id(String popelineId) {
		popeline_id = popelineId;
	}
	public String getStatus() {
		if(status == null){
			status = "";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		if(name == null){
			name = "";
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		if(short_name == null ){
			short_name = "";
		}
		return short_name;
	}
	public void setShort_name(String shortName) {
		short_name = shortName;
	}
	public String getLong_desc() {
		if(long_desc == null){
			long_desc = "";
		}
		return long_desc;
	}
	public void setLong_desc(String longDesc) {
		long_desc = longDesc;
	}
	public String getAddress_name() {
		if(address_name == null){
			address_name = "";
		}
		return address_name;
	}
	public void setAddress_name(String addressName) {
		address_name = addressName;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String lastUpdate) {
		last_update = lastUpdate;
	}
	public String getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(String submitDate) {
		submit_date = submitDate;
	}
	public String getOrders_id() {
		if(orders_id == null){
			orders_id = "";
		}
		return orders_id;
	}
	public void setOrders_id(String ordersId) {
		orders_id = ordersId;
	}
	public String getExpected_date() {
		if(expected_date == null){
			expected_date = "";
		}
		return expected_date;
	}
	public void setExpected_date(String expectedDate) {
		expected_date = expectedDate;
	}
	public String getConfidence() {
		if(confidence == null){
			confidence ="";
		}
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getXcomment() {
		if(xcomment == null){
			xcomment = "";
		}
		return xcomment;
	}
	public void setXcomment(String xcomment) {
		this.xcomment = xcomment;
	}
	public String getPhone() {
		if(phone == null){
			phone = "";
		}
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		if(email==null){
			email = "";
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProject_type() {
		if(project_type == null){
			project_type = "";
		}
		return project_type;
	}
	public void setProject_type(String projectType) {
		project_type = projectType;
	}
	public String getProject_area() {
		if(project_area == null){
			project_area = "";
		}
		return project_area;
	}
	public void setProject_area(String projectArea) {
		project_area = projectArea;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProducts_request() {
		if(products_request == null){
			products_request = "";
		}
		return products_request;
	}
	public void setProducts_request(String productsRequest) {
		products_request = productsRequest;
	}
	public String getDraft_information() {
		if(draft_information == null){
			draft_information = "";
		}
		return draft_information;
	}
	public void setDraft_information(String draftInformation) {
		draft_information = draftInformation;
	}
	public String getContact_title() {
		if(contact_title == null){
			contact_title = "";
		}
		return contact_title;
	}
	public void setContact_title(String contactTitle) {
		contact_title = contactTitle;
	}

}
