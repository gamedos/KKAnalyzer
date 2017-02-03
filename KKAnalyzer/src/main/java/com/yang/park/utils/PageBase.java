/**
 * @author Yang.Park
 * @Time    2017.12.10
 * 分页控制数据bean
 */
package com.yang.park.utils;

public class PageBase {

	private int totalNumber;

	private int currentPage=1;
	private int totalPage;

	private int pageSize = 5;
	//
	private int startIndex = 0;
	//
	public PageBase() {

	}

	public PageBase(int size) {
		this.pageSize = size;
	}

	//
	public int getTotalNumber() {
		return totalNumber;
	}

	//
	private void count() {
		//
		if (this.totalNumber < 1)
			this.totalNumber = 1;

		this.totalPage = this.totalNumber / this.pageSize;
		int plus = (this.totalNumber % this.pageSize) == 0 ? 0 : 1;
		this.totalPage = this.totalPage + plus;
		if (this.totalPage < 1)
			this.totalPage = 1;

		if (this.currentPage > this.totalPage)
			this.currentPage = this.totalPage;
		if (this.currentPage < 1)
			this.currentPage = 1;

		this.startIndex = this.pageSize * (this.currentPage - 1);

	}

	//
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.startIndex = this.pageSize * (this.currentPage - 1);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	@Override
	public String toString() {
		return "Page [totalNumber=" + totalNumber + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", pageSize=" + pageSize + ", startIndex=" + startIndex + "]";
	}
}
