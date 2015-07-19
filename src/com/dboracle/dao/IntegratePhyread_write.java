package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.DbinstanVo;
import com.dboracle.vo.IntegratePhyReadVo;
import com.dboracle.vo.IntegrateVo;

public class IntegratePhyread_write implements SqlDAO {
	public String sql ="select to_char(snap_time,'MMDD') snap_time,max,min,avg from ( "+  
			" select to_date(to_char(snap_time,'YYYY-MM-DD'),'YYYY-MM-DD') snap_time,max(PhysicalReadsPerSecond_MB) max,min(PhysicalReadsPerSecond_MB) min,round(avg(PhysicalReadsPerSecond_MB),2) avg from ( "+
		    " select trunc(snap_time) snap_time, "+
		      " round(sum(value) * 8 / 1024 / 3600,2)  PhysicalReadsPerSecond_MB "+
		  " from physicalread where snap_time > = "+
		        " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
		    " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
		    " and dbid in ('954042741'，'2483856027','773692991')  "+ 
		   " group by snap_time "+
		 " order by 1) group by snap_time order by 1 )";
	
	
	public String sql2 =  " select to_char(snap_time,'MMDD') snap_time,max,min,avg from ( "+
			 " select snap_time,max(PhysicalWritesPerSecondMB) max,min(PhysicalWritesPerSecondMB) min,round(avg(PhysicalWritesPerSecondMB),2) avg from ( "+
					    " select trunc(snap_time) snap_time, "+
					      " round(sum(value) * 8 / 1024 / 3600,2)  PhysicalWritesPerSecondMB "+
					  " from physicalwrite where snap_time > = "+
					        " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and dbid in ('954042741'，'2483856027','773692991')  "+ 
					   " group by snap_time "+
					 " order by 1) group by snap_time order by 1 ) ";
	public String sql3 = " select to_char(snap_time,'MMDD') snap_time,max,min,avg from ( "+
			 " select snap_time,max(Redowrite_MB_PerSecond) max,min(Redowrite_MB_PerSecond) min,round(avg(Redowrite_MB_PerSecond),2) avg from ( "+
					    " select trunc(snap_time) snap_time, "+
					      " round(sum(value) / 3600*512/1024/1024,2)  Redowrite_MB_PerSecond "+
					  " from redowrite where snap_time > = "+
					        " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
					    " and dbid in ('954042741'，'2483856027','773692991')  "+ 
					   " group by snap_time "+
					 " order by 1) group by snap_time order by 1 )";
 
			
			
	public List fillResult(String sql){
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while (rs.next()) {
				IntegratePhyReadVo ssv = new IntegratePhyReadVo();
				ssv.setSnap_time(rs.getString(1));
				ssv.setMax_value(rs.getFloat(2));
				ssv.setMin_value(rs.getFloat(3));
				ssv.setAvg_value(rs.getFloat(4));				
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
