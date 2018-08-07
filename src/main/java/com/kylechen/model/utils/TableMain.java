package com.kylechen.model.utils;

/**
 * @author chenzhiwei
 * 文件名：TableMainl.java
 * 时间：2018年8月3日
 * 备注：
 */
public class TableMain {
	//表名
	private String tableName;
	//表注释
	private String tableColumn;
	//包名
	private String packageName;
	//前缀
	private String tableModel;
	//输出路径
	private String outPath;
	
	public String getOutPath() {
		return outPath;
	}
	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableColumn() {
		return tableColumn;
	}
	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getTableModel() {
		return tableModel;
	}
	public void setTableModel(String tableModel) {
		this.tableModel = tableModel;
	}
	@Override
	public String toString() {
		return "TableMain [tableName=" + tableName + ", tableColumn=" + tableColumn + ", packageName=" + packageName
				+ ", tableModel=" + tableModel + "]";
	}
	
	
}
