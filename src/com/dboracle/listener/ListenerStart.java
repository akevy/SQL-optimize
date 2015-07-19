package com.dboracle.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.conndb.ManagerProperties;

@WebListener
public class ListenerStart implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String filepath = arg0.getServletContext().getRealPath("/")
				+ "connect.properties";
		ConnOracle.filepath = filepath;
		System.out.println(ConnOracle.filepath + "++++init");
		File file = new File(filepath);
		if(file.exists()){
		ManagerProperties mp = new ManagerProperties(filepath);
		String table_prefix = (String) mp.getProperties().get("table_prefix");
		
		System.out.println(table_prefix);
		}
	}

}
