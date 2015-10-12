package com.istore.common.core.bean;

public class Pager {
	private Integer currentPage;// 当前页
	private Integer pageSize;// 每页显示条目数量
	private Integer listCount;// 总条目数
	private Integer pageCount;// 总页数
	private Integer startNum;
	private Integer endNum;
	public Integer getStartNum() {
		return (currentPage - 1)*pageSize;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getEndNum() {
		return currentPage*pageSize;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage < 1){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getListCount() {
		return listCount;
	}
	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}
	public Integer getPageCount() {
		if(listCount == 0){
			return 1;
		}else{
		return listCount%pageSize == 0 ? listCount/pageSize : listCount/pageSize + 1;
		}
	}
	public void setPageCount(Integer pageCount) {
		
		this.pageCount = pageCount;
	}
}
