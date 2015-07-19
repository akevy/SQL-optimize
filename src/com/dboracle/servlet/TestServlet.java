package com.dboracle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.dao.SqlExecute;
import com.dboracle.util.Transform;
import com.dboracle.vo.EmpVo;
import com.dboracle.vo.VsqlVo;

@WebServlet(name="Test",urlPatterns={"/TestServlet"})
public class TestServlet  extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		
		//super.service(arg0, arg1);
		PrintWriter out = arg1.getWriter();
		out.println(ConnOracle.filepath);
		SqlExecute sqlexe = new SqlExecute();
//		String sql = "select ename,sal from emp";
//		List list = sqlexe.query(sql, "com.dboracle.vo.EmpVo");
//		for (Object ls : list){
//		out.println(((EmpVo)ls).getEname());
//		out.println(((EmpVo)ls).getSal());
//		}
		/**
		 * 注意：这里sql的select 部分要和vo类字段名称相同，并且顺序一致
		 */
		String sql = "select ename,sal,hiredate from scott.emp";
		List list = sqlexe.query(sql, "com.dboracle.vo.EmpVo");
		for (Object vo : list){
			out.println(((EmpVo) vo).getEname());
			out.println((((EmpVo)vo).getSal()));
			out.println(((EmpVo) vo).getHiredate());
		}
	}
	
}
