package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.DbinstanVo;
import com.dboracle.vo.SqlStatVo;

public class DHBI implements SqlDAO{
	String sql = "select distinct dbid,db_name from DBA_HIST_DATABASE_INSTANCE";
	public List fillResult(){
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				DbinstanVo ssv = new DbinstanVo();
				ssv.setDbid(rs.getString(1));
				ssv.setDb_name(rs.getString(2));
				
				list.add(ssv);

			}
			co.close(conn, pst, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			co.close(conn, pst, rs);
		}
		return list;

		
	}
	
	public List fillResult(String dbid){
		sql = "select max(instance_number) from dba_hist_database_instance where dbid=?";
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		try {
			pst.setString(1, dbid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				DbinstanVo ssv = new DbinstanVo();
				ssv.setInstance_num(rs.getString(1));
				
				list.add(ssv);

			}
			co.close(conn, pst, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			co.close(conn, pst, rs);
		}
		return list;

		
	}
}
