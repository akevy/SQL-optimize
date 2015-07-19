package com.dboracle.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.dao.PubSql;
import com.dboracle.vo.PubVo;

@WebServlet(name = "TOPSQLServlet", urlPatterns = { "/Sql_pub" })
public class TopPubServlet extends HttpServlet {
	private String sql = "";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("action").equals("topsql")) {
			PubSql ps = new PubSql();
			List list = ps.fillResult(2,"left_module");

			request.setAttribute("pub", list);
			request.getRequestDispatcher("/frame2.jsp").forward(request, response);
		}

		if (request.getParameter("action").equals("performance")) {
			PubSql ps = new PubSql();
			List list = ps.fillResult(1,"left_module");

			request.setAttribute("pub", list);
			request.getRequestDispatcher("/frame2.jsp").forward(request, response);
		}
		
		if (request.getParameter("action").equals("integrate")) {
			PubSql ps = new PubSql();
			List list = ps.fillResult(6,"left_module");

			request.setAttribute("pub", list);
			request.getRequestDispatcher("/frame2.jsp").forward(request, response);
		}

	}
}
