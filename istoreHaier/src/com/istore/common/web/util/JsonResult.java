package com.istore.common.web.util;

public class JsonResult {

	private long page;
	private long total;
	private Object rows;

	public JsonResult(long page, long total, Object rows) {
		this.page = page;
		this.total = total;
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	
	

}
