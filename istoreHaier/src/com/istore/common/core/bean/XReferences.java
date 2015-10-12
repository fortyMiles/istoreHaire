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
 * @ClassName: XReferences.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:11:11
 */
@SuppressWarnings("serial")
public class XReferences implements Serializable {
	private long xreferences_id;
	private String serialnumber;
	private String country;
	private String projectplace;
	private String description;
	private String type;
	private String installtime;
	private String installseries;
	private String installdetails;
	private String totalcapacity;
	private String keycapacity;
	private String pictures;
	private Timestamp createtime;
	private long author;
	private long xchannel_id;
	private long seq;
	private String xgroup_id;
	private long catgroup_id;
	private String status;
	private long field1;
	private String field2;
	private Timestamp field3;

	public long getXreferences_id() {
		return xreferences_id;
	}

	public void setXreferences_id(long xreferences_id) {
		this.xreferences_id = xreferences_id;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProjectplace() {
		return projectplace;
	}

	public void setProjectplace(String projectplace) {
		this.projectplace = projectplace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInstalltime() {
		return installtime;
	}

	public void setInstalltime(String installtime) {
		this.installtime = installtime;
	}

	public String getInstallseries() {
		return installseries;
	}

	public void setInstallseries(String installseries) {
		this.installseries = installseries;
	}

	public String getInstalldetails() {
		return installdetails;
	}

	public void setInstalldetails(String installdetails) {
		this.installdetails = installdetails;
	}

	public String getTotalcapacity() {
		return totalcapacity;
	}

	public void setTotalcapacity(String totalcapacity) {
		this.totalcapacity = totalcapacity;
	}

	public String getKeycapacity() {
		return keycapacity;
	}

	public void setKeycapacity(String keycapacity) {
		this.keycapacity = keycapacity;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
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

	public long getCatgroup_id() {
		return catgroup_id;
	}

	public void setCatgroup_id(long catgroup_id) {
		this.catgroup_id = catgroup_id;
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
