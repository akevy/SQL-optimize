package com.dboracle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.PubVo;

public class PubSql implements SqlDAO {

	private String sql = "select id,PARENT_ID,action,url from PUB_ACTION where PARENT_ID=? and module=?";

	public List fillResult(int id,String mod) {
		List list = new ArrayList();
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		PreparedStatement pst = co.getStatement(conn, sql);
		try {
			pst.setInt(1, id);
			pst.setString(2, mod);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ResultSet rs = co.getResultSet(pst);

		try {
			while (rs.next()) {
				PubVo pv = new PubVo();
				pv.setId(rs.getInt(1));
				pv.setParent_id(rs.getInt(2));
				pv.setAction(rs.getString(3));
				pv.setUrl(rs.getString(4));
				list.add(pv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
