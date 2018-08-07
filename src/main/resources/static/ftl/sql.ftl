
DROP TABLE IF EXISTS `${tableName}`;
CREATE TABLE `${tableName}` (
	<#list model as var>
		`${var.columnName}` ${var.columnType} (${var.columnLength})  COMMENT '`${var.columnMemo}`',	
	
	</#list>
  		PRIMARY KEY (`ID`)
) COMMENT '${tableMemo}';
