package com.action;

import java.util.List;

public abstract class BaseListAction extends BaseAction {

	protected List dtoList;
	protected Integer[] ids;
	protected Integer pageNo;
	protected Integer maxPageSize;
	public List getDtoList() {
		return dtoList;
	}
	public void setDtoList(List dtoList) {
		this.dtoList = dtoList;
	}
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getMaxPageSize() {
		return maxPageSize;
	}
	public void setMaxPageSize(Integer maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
	
	}
