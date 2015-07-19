package com.dboracle.conndb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.management.relation.Relation;

public class ConnOracle implements ConnOracleimp{
	public static String filepath = "";		//用于保存jdbc配置文件路径
	public static String table_prefix = "";	//用于保存表名前缀
	@Override
	public Connection getConnect(String filepath) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Map map = new ManagerProperties(filepath).getProperties();
			String str = "jdbc:oracle:thin:@"+map.get("ip")+":"+map.get("port")+":"+map.get("SID");
			System.out.println(str+","+(String)map.get("name")+","+(String)map.get("password"));
			 conn = DriverManager.getConnection(str,(String)map.get("name"),(String)map.get("password"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	@Override
	public PreparedStatement getStatement(Connection conn,String sql) {
		
		PreparedStatement pst = null;
		try {
			 pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pst;
	}
	@Override
	public ResultSet getResultSet(PreparedStatement pst) {
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public void close(Connection conn) {
		
		
	}
	@Override
	public void close(PreparedStatement pst) {
		if (pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst = null;
		}
		
	}
	
	@Override
	public void close(ResultSet rs) {
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
	}
	@Override
	public void close(Connection conn,PreparedStatement pst,ResultSet rs) {
		close(rs);
		close(pst);
		close(conn);
		
	}

	
	 
}
