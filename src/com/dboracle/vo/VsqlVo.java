package com.dboracle.vo;

import java.sql.Clob;

public class VsqlVo {
	private String sql_id = null;
	private Clob sql_fulltext = null;
	public String getSql_id() {
		return sql_id;
	}
	public void setSql_id(String sql_id) {
		this.sql_id = sql_id;
	}
	public Clob getSqltext() {
		return sql_fulltext;
	}
	public void setSqltext(Clob sqltext) {
		this.sql_fulltext = sqltext;
	}
	
}
