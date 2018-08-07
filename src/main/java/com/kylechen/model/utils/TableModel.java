package com.kylechen.model.utils;

/**
 * @author chenzhiwei
 * 文件名：TableModel.java
 * 时间：2018年8月3日
 * 备注：
 */
public class TableModel {
	//字段名
	private String columnName;
	//字段类型
	private String columnType;
	//字段长度
	private String columnLength;
	//字段备注
	private String columnMemo;
	//是否搜索项
	private String isSearch;
	
	public String getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getColumnMemo() {
		return columnMemo;
	}
	public void setColumnMemo(String columnMemo) {
		this.columnMemo = columnMemo;
	}
	@Override
	public String toString() {
		return "TableModel [columnName=" + columnName + ", columnType=" + columnType + ", columnLength=" + columnLength
				+ ", columnMemo=" + columnMemo + "]";
	}
	
}
