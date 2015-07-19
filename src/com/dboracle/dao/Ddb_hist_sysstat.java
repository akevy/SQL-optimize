package com.dboracle.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.IntegrateVo;

public class Ddb_hist_sysstat implements SqlDAO {
	String unit = null;
	String sql = "select i1.snap_time, i1.dbid, i1.value/"+unit+
			  " from (select * "+
			          " from (SELECT trunc(cast(sn.begin_interval_time as date) + 10 / 1440, "+
			                             " 'hh24') snap_time,"+
			                       " e.instance_number, "+
			                       " (e.VALUE - lag(e.VALUE)"+
			                        " over(partition by e.dbid, "+
			                             " e.instance_number order by e.snap_id)) value, "+
			                       " n.dbid "+
			                  " FROM sys.wrh$_sysstat   e, "+
			                       " sys.wrh$_stat_name n, "+
			                       " sys.wrm$_snapshot  sn "+
			                 " WHERE e.instance_number = sn.instance_number "+
			                   " AND e.snap_id = sn.snap_id "+
			                   " AND e.stat_id = n.stat_id "+
			                   " AND e.dbid = sn.dbid "+
			                   " and e.dbid = n.dbid "+
			                   "AND e.dbid = ?"+
			                   " and e.instance_number = ? "+
			                   " AND n.stat_name = ?) "+
			         " +where value > 0) i1";
	
	public List<IntegrateVo> fillResult(String unit,String dbid,String instance_number,String statname){
		this.unit = unit;
		System.out.println(sql);
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		try {
			pst.setString(1, dbid);
			pst.setInt(2,Integer.parseInt(instance_number));
			pst.setString(3, statname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = co.getResultSet(pst);
		List<IntegrateVo> list = new ArrayList<IntegrateVo>();
		try {
			while(rs.next()){
				IntegrateVo io = new IntegrateVo();
				io.setSnap_time(rs.getString(1));
				io.setValue(rs.getString(2));
				list.add(io);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
}
