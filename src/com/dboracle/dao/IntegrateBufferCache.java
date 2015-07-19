package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.DbinstanVo;
import com.dboracle.vo.IntegrateBufferVo;
import com.dboracle.vo.IntegrateVo;

public class IntegrateBufferCache implements SqlDAO {
	public String sql = "select * from ( "
			+ " select snap_time,instance_number,GB,row_number() over(partition by trunc_time,instance_number order by GB desc) rn from( "
			+ " select snap_time,trunc(snap_time) trunc_time,INSTANCE_NUMBER,round(sum(value)/1024/1024/1024,0) GB from buffercache "
			+ " where dbid in ('954042741'，'2483856027','773692991') "
			+ " and snap_time > = "
			+ " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "
			+ " group by snap_time,INSTANCE_NUMBER) order by 1,3) where rn=1";

	public String sql2 = "WITH SNAP_TIME_T AS (select * from ( "
			+ " select snap_time,instance_number,GB,row_number() over(partition by trunc_time,instance_number order by GB desc) rn from( "
			+ " select snap_time,trunc(snap_time) trunc_time,INSTANCE_NUMBER,round(sum(value)/1024/1024/1024,2) GB from buffercache "
			+ " where dbid in ('954042741'，'2483856027','773692991') "
			+ " and snap_time > = "
			+ " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "
			+ " group by snap_time,INSTANCE_NUMBER) order by 1,3) where rn=1) "
			+ " select a.snap_time,a.instance_number,b.GB,a.BCH from ( "
			+ " select snap_time,instance_number,trunc(avg(CACHE_HIT_RATIO),4) BCH from ( "
			+ " with stats as( "
			+ " select stat_id,stat_name "
			+ " from sys.wrh$_stat_name a "
			+ " where a.stat_name in ('session logical reads', "
			+ " 'physical reads direct', "
			+ " 'physical reads direct (lob)', "
			+ " 'physical reads') "
			+ " and dbid=(select dbid from v$database)) "
			+ " SELECT trunc(cast(sn.begin_interval_time as date) + 10 / 1440, "
			+ " 'hh24') snap_time, "
			+ " ses.dbid,ses.instance_number, "
			+ " trunc(avg(1 - (phy.VALUE - lob.VALUE - dir.VALUE) / ses.VALUE), 2) \"CACHE_HIT_RATIO\" "
			+ " FROM sys.wrh$_sysstat  ses, "
			+ " sys.wrh$_sysstat  lob, "
			+ " sys.wrh$_sysstat  dir, "
			+ " sys.wrh$_sysstat  phy, "
			+ " sys.wrm$_snapshot sn "
			+ " WHERE ses.stat_id = (select stat_id from stats where stat_name='session logical reads') "
			+ " AND dir.stat_id = (select stat_id from stats where stat_name='physical reads direct') "
			+ " AND lob.stat_id = (select stat_id from stats where stat_name='physical reads direct (lob)') "
			+ " AND phy.stat_id = (select stat_id from stats where stat_name='physical reads') "
			+ " and ses.snap_id = lob.snap_id "
			+ " and ses.snap_id = dir.snap_id "
			+ " and ses.snap_id = phy.snap_id "
			+ " and ses.snap_id = sn.snap_id "
			+ " and ses.dbid = lob.dbid "
			+ " and ses.dbid = dir.dbid "
			+ " and ses.dbid = phy.dbid "
			+ " and ses.dbid = sn.dbid "
			+ " and ses.instance_number = lob.instance_number "
			+ " and ses.instance_number = dir.instance_number "
			+ " and ses.instance_number = phy.instance_number "
			+ " and ses.instance_number = sn.instance_number "
			+ " and begin_interval_time  > = "
			+ " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and begin_interval_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and ses.dbid in ('954042741'，'2483856027','773692991') "
			+ " group by trunc(cast(sn.begin_interval_time as date) + 10 / 1440,'hh24'),ses.dbid,ses.instance_number "
			+ " order by 1,2) group by snap_time,instance_number) a,SNAP_TIME_T b "
			+ " where a.SNAP_TIME = b.SNAP_TIME and a.INSTANCE_NUMBER = b.INSTANCE_NUMBER ";

	public List fillResult(String sql) {
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				IntegrateBufferVo ssv = new IntegrateBufferVo();
				ssv.setSnap_time(rs.getString(1));
				ssv.setInstance_number(rs.getInt(2));
				ssv.setValue(rs.getFloat(3));

				list.add(ssv);

			}
			co.close(conn, pst, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			co.close(conn, pst, rs);
		}
		return list;

	}

	public List fillResult2(String sql) {
		System.out.println(sql);
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				IntegrateBufferVo ssv = new IntegrateBufferVo();
				ssv.setSnap_time(rs.getString(1));
				ssv.setInstance_number(rs.getInt(2));
				ssv.setValue(rs.getFloat(3));
				ssv.setBch(rs.getFloat(4));

				list.add(ssv);

			}
			co.close(conn, pst, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			co.close(conn, pst, rs);
		}
		return list;

	}

}
