package com.dboracle.conndb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public interface ConnOracleimp {
	public Connection getConnect(String filepath);
	public PreparedStatement getStatement(Connection conn,String sql);
	public ResultSet getResultSet(PreparedStatement pst);
	public void close(Connection conn);
	public void close(PreparedStatement pst);
	public void close(ResultSet rs);
	public void close(Connection conn,PreparedStatement pst,ResultSet rs);
}
