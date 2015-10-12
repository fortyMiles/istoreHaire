package com.istore.common.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class OrderComment implements Serializable {
	private long comment_id; // 评论id
	private long orders_id; // 订单ID
	private long store_id; // store ID
	private int quality; // 商品质量评分
	private int service; // 服务评分
	private int logistic; // 物流评分
	private String comment_content; // 补充评论内容
	private String user_name; // 评论人
	private Timestamp lastupdate; // 评论时间
	private String replyName; // 回复人
	private String replyMessage; // 回复内容
	private Timestamp replyTime; // 回复时间
	private String isReplied;   //是否回复

	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	public long getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(long orders_id) {
		this.orders_id = orders_id;
	}

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	public Timestamp getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	public int getLogistic() {
		return logistic;
	}

	public void setLogistic(int logistic) {
		this.logistic = logistic;
	}

	public String getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(String isReplied) {
		this.isReplied = isReplied;
	}

}
