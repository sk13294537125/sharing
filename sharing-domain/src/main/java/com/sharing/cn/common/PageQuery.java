package com.sharing.cn.common;



import java.io.Serializable;

public class PageQuery<T> extends Query<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int pageNo = 1;
	private int pageSize = 20;
	
	
	@Override
	public String toString() {
		return String.format("PageQuery [pageNo=%s, pageSize=%s]", pageNo, pageSize);
	}

	public PageQuery(){
	}
	
	public PageQuery(int pageNo, int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
