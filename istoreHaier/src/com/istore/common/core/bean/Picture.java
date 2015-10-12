package com.istore.common.core.bean;

public class Picture {
private long catentry_id;
private long seq;
private long store_id;
private String imgurl;
public long getCatentry_id() {
	return catentry_id;
}
public void setCatentry_id(long catentryId) {
	catentry_id = catentryId;
}
public long getSeq() {
	return seq;
}
public void setSeq(long seq) {
	this.seq = seq;
}
public long getStore_id() {
	return store_id;
}
public void setStore_id(long storeId) {
	store_id = storeId;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}



}
