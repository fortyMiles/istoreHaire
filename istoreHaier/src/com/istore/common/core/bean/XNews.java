/**
 * @Project istoreHaier
 * @Package com.istore.common.core.bean
 * @Title XChannel.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: XNews.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-11上午8:46:17
 */
@SuppressWarnings("serial")
public class XNews implements Serializable {
	private long xnews_id;
	private String title;
	private String firstimage;
	private String summary;
	private String details;
	private Timestamp createtime;
	private long author;
	private long xchannel_id;
	private long seq;
	private String xgroup_id;
	private String status;
	private long field1;
	private String field2;
	private Timestamp field3;

	public long getXnews_id() {
		return xnews_id;
	}

	public void setXnews_id(long xnews_id) {
		this.xnews_id = xnews_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public long getXchannel_id() {
		return xchannel_id;
	}

	public void setXchannel_id(long xchannel_id) {
		this.xchannel_id = xchannel_id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getXgroup_id() {
		return xgroup_id;
	}

	public void setXgroup_id(String xgroup_id) {
		this.xgroup_id = xgroup_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getField1() {
		return field1;
	}

	public void setField1(long field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Timestamp getField3() {
		return field3;
	}

	public void setField3(Timestamp field3) {
		this.field3 = field3;
	}

}
