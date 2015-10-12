package com.istore.common.core.bean;

import java.io.Serializable;


public class Predictdatesale implements Serializable{
	private static final long serialVersionUID = 1L;
	private int count ;
	private String material;
	private String userid;
	private String date1;
	private String date1sale;
	private String date2;
	private String date2sale;
	private String date3;
	private String date3sale;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate1sale() {
		return date1sale;
	}
	public void setDate1sale(String date1sale) {
		this.date1sale = date1sale;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getDate2sale() {
		return date2sale;
	}
	public void setDate2sale(String date2sale) {
		this.date2sale = date2sale;
	}
	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}
	public String getDate3sale() {
		return date3sale;
	}
	public void setDate3sale(String date3sale) {
		this.date3sale = date3sale;
	}
}
