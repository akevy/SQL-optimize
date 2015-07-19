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

public class IntegrateSession implements SqlDAO {
	String sql = " select snap_time, (sum(value) - 15) LogonsCurrent "+
   " from sys.logon_current "+
  " where snap_time > = "+
  " to_date('2014-09-11 00:09:03', 'YYYY-MM-DD HH24:mi:ss') "+
//  " and snap_time < to_date('2014-09-19 16:09:04', 'YYYY-MM-DD HH24:mi:ss') "+ 
    " and dbid in ('2621703575')  group by snap_time "+
  " order by 1";
	public List fillResult(){
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		System.out.println(sql);
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
