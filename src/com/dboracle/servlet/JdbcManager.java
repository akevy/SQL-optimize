package com.dboracle.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboracle.conndb.ManagerProperties;



/**
 * 接收了http://localhost:8080/SQlOptimize/JDBCManager.jsp传递过来的信息
 * @author db-oracle
 *
 */
@WebServlet(name="JStoJDBC",urlPatterns={"/utilManager"})
public class JdbcManager extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OK");
		String ip = req.getParameter("ip");
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		String port = req.getParameter("port");
		String SID = req.getParameter("SID");
		String table_prefix = req.getParameter("table_prefix");
		
		String filepath = this.getServletConfig().getServletContext().getRealPath("/")+"connect.properties";
		ManagerProperties mp = new ManagerProperties(filepath);
		mp.setProperties(ip,name, password, port, SID,table_prefix);
		
		//System.out.println(mp.getProperties());
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
		
	}
	
}
