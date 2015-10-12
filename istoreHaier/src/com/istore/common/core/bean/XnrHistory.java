package com.istore.common.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class XnrHistory implements Serializable {
	private long xnrhistory_id;
	private long xnr_id;
	private long checker;
	private Timestamp lastupdate;
	private String status;
	private String xcomment;

	public long getXnrhistory_id() {
		return xnrhistory_id;
	}

	public void setXnrhistory_id(long xnrhistory_id) {
		this.xnrhistory_id = xnrhistory_id;
	}

	public long getXnr_id() {
		return xnr_id;
	}

	public void setXnr_id(long xnr_id) {
		this.xnr_id = xnr_id;
	}

	public long getChecker() {
		return checker;
	}

	public void setChecker(long checker) {
		this.checker = checker;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXcomment() {
		return xcomment;
	}

	public void setXcomment(String xcomment) {
		this.xcomment = xcomment;
	}
}
