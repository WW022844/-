package cn.itcast.domain;

import java.util.List;

public class PageBean 
{
	//当前页: pageNumber
	private Integer pageNumber;
	//每页显示的条数: pageSize
	private Integer pageSize;
	//每页显示的数据  List<Linkman> linkmanList;
	private List<Linkman> linkmanList;
	//总条数: totalcount
	private Integer totalCount;
	//总页数: totalPage
	private Integer totalPage;
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<Linkman> getLinkmanList() {
		return linkmanList;
	}
	public void setLinkmanList(List<Linkman> linkmanList) {
		this.linkmanList = linkmanList;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
