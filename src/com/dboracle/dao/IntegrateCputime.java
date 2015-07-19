package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.IntegrateVo;

public class IntegrateCputime implements SqlDAO {
	// public String sql = " select * from ( "+
	// " select to_char(snap_time,'MMDD') snap_time,a.CPU_TIME_S_S,row_number() over(partition by a.trunc_time order by a.CPU_TIME_S_S desc) rn from ( "+
	// " select "+
	// " snap_time, "+
	// " trunc(snap_time) trunc_time, "+
	// " round(sum(value)/100/3600,2)  CPU_TIME_S_S "+
	// " from sys.cpu_time where snap_time > = "+
	// " to_date('2014-08-20 23:08:25', 'YYYY-MM-DD HH24:mi:ss') "+
	// " and snap_time < to_date('2014-09-19 16:09:04', 'YYYY-MM-DD HH24:mi:ss') "+
	// " and dbid in ('573201976','812046949') "+
	// " group by snap_time "+
	// " ) a order by 1) where rn=1 ";

	public String sql = "select * from (" +
			" select to_char(snap_time,'MMDD') snap_time, "
			+ " round(sum(value)/100/3600,2)  CPU_TIME_S_S "
			+ " from cpu_time  where snap_time > = "
			+ " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and snap_time < to_date('2014-09-19 15:09:07', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and dbid in ('954042741'，'2483856027','773692991') "
			+ " group by snap_time " + " order by 1 "+" ) where CPU_TIME_S_S<50";

	public String sql2 ="select * from (" + 
			"select * from ( "
			+ " select to_char(a.snap_time,'MMDD') snap_time,a.CPU_TIME_S_S,row_number() over(partition by a.trunc_time order by a.CPU_TIME_S_S desc) rn from ( "
			+ " select "
			+ " snap_time, "
			+ " trunc(snap_time) trunc_time, "
			+ " round(sum(value)/100/3600,2)  CPU_TIME_S_S "
			+ " from sys.cpu_time where snap_time > = "
			+ " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and snap_time < to_date('2014-09-19 16:09:04', 'YYYY-MM-DD HH24:mi:ss') "
			+ " and dbid in ('954042741'，'2483856027','773692991') "
			+ " group by snap_time " + " ) a order by 1) where rn=1"+" ) where CPU_TIME_S_S<50" ;

	public List fillResult(String sql) {
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
		} finally {
			co.close(conn, pst, rs);
		}
		return list;
	}

}
