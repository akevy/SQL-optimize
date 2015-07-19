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
import com.dboracle.vo.Iopsvo;

public class IntegrateIOPS implements SqlDAO {
	public String sql =  " select to_char(snap_time,'DDHH24') snap_time,singlerw_iops,muiltrw_iops from ( "+    
			" select a.snap_time,a.singlerw_iops,muiltrw_iops,row_number() over(partition by a.trunc_time order by a.singlerw_iops desc) rn from ( "+ 
					 " with q1 as "+
					 "(select a.snap_time, (sum(a.value) - sum(b.value)) / 3600 value1,sum(b.value)/ 3600 value2 "+
					    " from singlereadtotal_IOPS a, muiltread_IOPS b "+
					   " where a.snap_time = b.snap_time "+
					     " and a.dbid = b.dbid "+
						 " and a.instance_number=b.instance_number "+
					     " and a.snap_time > = "+
					        " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and a.snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and a.dbid in ('954042741'，'2483856027','773692991') "+
					   " group by a.snap_time), "+
					" q2 as "+
					  "(select a.snap_time, (sum(a.value) - sum(b.value)) / 3600 value1,sum(b.value)/ 3600 value2 "+
					    " from singlewritetotal_IOPS a,muiltwrite_IOPS b "+
					   " where a.snap_time = b.snap_time "+
					     " and a.dbid = b.dbid "+
						 " and a.instance_number=b.instance_number "+
					     " and a.snap_time > = "+
					        " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and a.snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and a.dbid in ('954042741'，'2483856027','773692991') "+
					   " group by a.snap_time) "+
					" select q1.snap_time,trunc(q1.snap_time) trunc_time, round(q1.value1 + q2.value1, 2) singlerw_iops,round(q1.value2 + q2.value2, 2) muiltrw_iops "+
					  " from q1, q2 "+
					 " where q1.snap_time = q2.snap_time) a order by 1) where rn=1";
	
	public String sql2 = "select snap_time,round(sum(waittime_sec),0) wait_s from ( "+
			" select snap_time,dbid,instance_number,event_name,waittime_sec,row_number() over(partition by snap_time,dbid,instance_number,event_name order by waittime_sec desc) rn from ( "+
					" select trunc(cast(sn.begin_interval_time as date) + 10 / 1440,'hh24') snap_time,sn.dbid,sn.instance_number,en.event_name, "+
					" (e.TIME_WAITED_MICRO-lag(e.TIME_WAITED_MICRO) over(partition by e.dbid,e.instance_number order by e.snap_id))/1000000 waittime_sec "+
					" from sys.WRM$_SNAPSHOT sn, sys.WRH$_SYSTEM_EVENT e, "+
					     " sys.WRH$_EVENT_NAME en "+
					" where     e.event_id         = en.event_id "+
					      " and e.dbid             = en.dbid "+
					      " and e.snap_id          = sn.snap_id "+
					      " and e.dbid             = sn.dbid "+
					      " and e.instance_number  = sn.instance_number "+
					      " and sn.status          = 0 "+
					      " and en.event_name='log file parallel write' "+
					      " and sn.BEGIN_INTERVAL_TIME >= to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and sn.BEGIN_INTERVAL_TIME < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
					      " and sn.dbid in ('954042741'，'2483856027','773692991') order by 1)) where waittime_sec>0 and rn =1 group by snap_time,event_name ";
			
			
			
			
			
			
	public List fillResult(String sql){
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				Iopsvo ssv = new Iopsvo();
				ssv.setSnap_time(rs.getString(1));
				ssv.setRead_value(rs.getFloat(2));
				ssv.setWrite_value(rs.getFloat(3));				
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
	
	public List fillResult2(String sql){
		System.out.println(sql);
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				IntegrateVo ssv = new IntegrateVo();
				ssv.setSnap_time(rs.getString(1));
				ssv.setValue(rs.getString(2));				
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
