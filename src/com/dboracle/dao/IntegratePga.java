package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.DbinstanVo;
import com.dboracle.vo.IntegrateVo;

public class IntegratePga implements SqlDAO {
//	String sql = " select snap_time,round(sum(value)/1024/1024,0) MB from ( "+     
//			  " select trunc(cast(b.begin_interval_time as date) + 10 / 1440, 'hh24') snap_time,a.dbid,sum(value) value "+
//			    " from sys.wrh$_pgastat a, sys.WRM$_SNAPSHOT b "+
//			   " where a.snap_id = b.snap_id "+
//			     " and a.dbid = b.dbid "+
//				 " and a.instance_number=b.instance_number "+
//			     " and begin_interval_time > = "+
//			         " to_date('2014-08-26 00:08:37', 'YYYY-MM-DD HH24:mi:ss') "+
//			     " and begin_interval_time < "+
//			         " to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+
//			     " and a.dbid in ('2231606722','1093582649')  "+
//			     " and name='total PGA inuse' "+
//			         " group by trunc(cast(b.begin_interval_time as date) + 10 / 1440, 'hh24'),a.dbid "+ 
//			         " order by 2 "+
//			" ) group by snap_time "+
//			" order by 1";
	String sql = "select snap_time,round(sum(value)/1024/1024,0) MB from ( "+ 
			" select snap_time,dbid,instance_number,value,row_number() over(partition by snap_time,dbid,instance_number order by value desc) rn from ( "+
					   " select trunc(cast(b.begin_interval_time as date) + 10 / 1440, 'hh24') snap_time,a.dbid,sum(value) value,a.instance_number,b.snap_id "+
					     " from sys.wrh$_pgastat a, sys.WRM$_SNAPSHOT b "+ 
					     " where a.snap_id = b.snap_id "+ 
					      " and a.dbid = b.dbid "+ 
						   "and a.instance_number=b.instance_number "+ 
					      " and begin_interval_time > = "+ 
					          " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+ 
					      " and begin_interval_time < "+ 
					          " to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "+ 
					      " and a.dbid in ('954042741'，'2483856027','773692991') "+
					      " and name='total PGA inuse' "+ 
					         " group by trunc(cast(b.begin_interval_time as date) + 10 / 1440, 'hh24'),a.dbid,a.instance_number,b.snap_id "+
					          " order by 2 "+ 
					 " )) where rn=1 group by snap_time "+ 
					 " order by 1";
	public List fillResult(){
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
