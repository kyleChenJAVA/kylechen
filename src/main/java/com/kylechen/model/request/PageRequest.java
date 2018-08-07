
package com.kylechen.model.request;
/*
 *PageRequest.java
 *@author kylechen QQ2848142215
 * 2018年3月21日
 */
public class PageRequest {
	
	private int currentPage;//当前页码
	private int sumCount;//总数
	private int pageSize;//大小
	private int sumPage;//大小
	private String  token;//登陆token
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	 @Override
	public String toString(){
	    	final StringBuilder sb=new StringBuilder(super.toString()+"{");
	    	sb.append("currentPage=").append(currentPage).append('\'');
	    	sb.append("sumCount=").append(sumCount).append('\'');
	    	sb.append("pageSize=").append(pageSize).append('\'');
	    	sb.append("sumPage=").append(sumPage).append('\'');
	    	sb.append("token=").append(token).append('\'');
	     	sb.append("}");
	    	return sb.toString();	    	
	 }

}
