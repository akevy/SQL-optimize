package com.dboracle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.vo.VsqlVo;
@WebServlet(name = "MainServelt",urlPatterns={"/Main"})
public class Main extends HttpServlet{

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		String str = arg0.getParameter("select1");
		PrintWriter out = arg1.getWriter();
		ConnOracle co = new ConnOracle();
		Connection conn = co.getConnect(ConnOracle.filepath);
		String sql = "select * from ("+
    "select a.sql_id,round(a.ELAPSED_TIME/(decode(a.EXECUTIONS,0,1,a.EXECUTIONS)),2)/1000000 Elapsed_time_avg from v$sql a order by 2 desc) where rownum<=10";
		PreparedStatement pst = co.getStatement(conn, sql);
		ResultSet rs = co.getResultSet(pst);
		List list = new ArrayList();
		try {
			while(rs.next()){
				VsqlVo sqlvo = new VsqlVo();
				sqlvo.setSql_id(rs.getString(1));
				list.add(sqlvo);
			}
			ServletContext application = this.getServletContext();
			application.setAttribute("dbid", str);
			application.setAttribute("vo", list);
			arg0.getRequestDispatcher("/main.jsp").forward(arg0, arg1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
}
