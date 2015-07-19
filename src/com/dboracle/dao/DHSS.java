package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.SqlStatVo;

public class DHSS implements SqlDAO {
	private String stat1str;
	private String stat2str;
	String sql ="select snap_id,sql_id,time_avg,to_char(BEGIN_INTERVAL_TIME,'YYYY-MM-DD HH24:mi'),px_server_avg,parse_call_avg,buffer_get_avg,disk_read_avg,cpu_time_avg,sharable_mem_avg from ("+ 
			"select a.snap_id,b.BEGIN_INTERVAL_TIME,a.INSTANCE_NUMBER,a.PARSING_SCHEMA_NAME,a.sql_id,"
			+ "a.SORTS_DELTA,"
			+ "a.EXECUTIONS_DELTA,"
			+ "a.ELAPSED_TIME_DELTA,"
			+ "a.PX_SERVERS_EXECS_DELTA,"
			+ "a.PARSE_CALLS_DELTA,"
			+ "a.BUFFER_GETS_DELTA,"
			+ "a.DISK_READS_DELTA,"
			+ "a.CPU_TIME_DELTA,"
			+ "a.SHARABLE_MEM,"
			+ "round(a.ELAPSED_TIME_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA)/1000000,2) time_avg,"
			+ "round(a.PX_SERVERS_EXECS_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) px_server_avg,"
			+ "round(a.PARSE_CALLS_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) parse_call_avg,"
			+ "round(a.BUFFER_GETS_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) buffer_get_avg,"
			+ "round(a.DISK_READS_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) disk_read_avg,"
			+ "round(a.CPU_TIME_DELTA/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) cpu_time_avg,"
			+ "round(a.SHARABLE_MEM/decode(a.EXECUTIONS_DELTA,0,1,a.EXECUTIONS_DELTA),2) sharable_mem_avg"
			+ " from dba_hist_sqlstat a,dba_hist_snapshot b"
			+ " where a.snap_id = b.snap_id	and a.INSTANCE_NUMBER = b.INSTANCE_NUMBER"
			+ " and a.dbid = b.dbid ";

	public List fillResult(String type, Timestamp begin_time,
			Timestamp end_time, String dbid, String instance_num) {
		
		List list = new ArrayList();
		ConnOracle co = new ConnOracle();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = co.getConnect(ConnOracle.filepath);
		System.out.println("Innter"+type+"@"+dbid+"@"+begin_time);
		
		if (begin_time == null) {
			sql = sql +
					" and a.dbid=? and a.instance_number=?"+
					" order by "+type+" desc) where rownum<=10";
			 pst = co.getStatement(conn, sql);
			 try {
				pst.setLong(1, Long.valueOf(dbid));
				pst.setInt(2, Integer.valueOf(instance_num));
				rs = co.getResultSet(pst);
				 while(rs.next()){
					 SqlStatVo ssv = new SqlStatVo();
						ssv.setSnap(rs.getString(1));
						ssv.setSql_id(rs.getString(2));
						ssv.setElapsed_time(rs.getString(3));
						ssv.setSnap_time(rs.getString(4));
						ssv.setPx_server_avg(rs.getString(5));
						ssv.setParse_call_avg(rs.getString(6));
						ssv.setBuffer_get_avg(rs.getString(7));
						ssv.setDisk_read_avg(rs.getString(8));
						ssv.setCpu_time_avg(rs.getString(9));
						ssv.setSharable_mem_avg(rs.getString(10));
						list.add(ssv);
				 }
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			
		}
		else{
			sql = sql
					+ " and a.dbid=? and a.instance_number=? and b.BEGIN_INTERVAL_TIME between ? and ? "
					+ " order by "+type+" desc) where rownum<=10";
		try {
			 pst = co.getStatement(conn, sql);
			pst.setString(1, dbid);
			pst.setInt(2, Integer.valueOf(instance_num));
			pst.setTimestamp(3, begin_time);
			pst.setTimestamp(4, end_time);
			rs = co.getResultSet(pst);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
		try {
			while (rs.next()) {
				SqlStatVo ssv = new SqlStatVo();
				ssv.setSnap(rs.getString(1));
				ssv.setSql_id(rs.getString(2));
				ssv.setElapsed_time(rs.getString(3));
				ssv.setSnap_time(rs.getString(4));
				ssv.setPx_server_avg(rs.getString(5));
				ssv.setParse_call_avg(rs.getString(6));
				ssv.setBuffer_get_avg(rs.getString(7));
				ssv.setDisk_read_avg(rs.getString(8));
				ssv.setCpu_time_avg(rs.getString(9));
				ssv.setSharable_mem_avg(rs.getString(10));
				list.add(ssv);

			}
			co.close(conn, pst, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
		co.close(conn, pst, rs);
		return list;
	}

}
