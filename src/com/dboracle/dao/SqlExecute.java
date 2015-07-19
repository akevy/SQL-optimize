package com.dboracle.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dboracle.conndb.ConnOracle;

public class SqlExecute {
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private ConnOracle co = null;
	public SqlExecute() {
		co = new ConnOracle();
	}
	public List query(String sqlstr,String voname){
		conn = co.getConnect(ConnOracle.filepath);
		pst = co.getStatement(conn, sqlstr);
		rs = co.getResultSet(pst);
		System.out.println(rs.toString());
		List list = new ArrayList();
		try {
			Class ca = Class.forName(voname);
			Field fields[] = ca.getDeclaredFields();
			System.out.println("query");
			System.out.println(fields[0].getName());
			int cun = 0 ;
			while(rs.next()){
				Object clas = ca.newInstance();
			for(int i = 0;i < fields.length; i++ ){
				/**
				 * setAccessible(true)让一个private和final类型的变量可以编辑
				 */
				
				fields[i].setAccessible(true);
				if (rs.getObject(fields[i].getName()) instanceof BigDecimal){
				fields[i].set(clas,(( BigDecimal)rs.getObject(fields[i].getName())).intValue());
				System.out.println(fields[i].getName()+":"+(( BigDecimal)rs.getObject(fields[i].getName())).intValue());
				}
				if(rs.getObject(fields[i].getName()) instanceof String){
					fields[i].set(clas,((String)rs.getObject(fields[i].getName())));
					System.out.println(fields[i].getName()+":"+((String)rs.getObject(fields[i].getName())));
				}
				if(rs.getObject(fields[i].getName()) instanceof Clob){
				fields[i].set(clas,(rs.getObject(fields[i].getName())));
				System.out.println(fields[i].getName()+":"+(rs.getObject(fields[i].getName())));
			    }
				
				if(rs.getObject(fields[i].getName()) instanceof Timestamp){
					fields[i].set(clas,(rs.getObject(fields[i].getName())));
					System.out.println(fields[i].getName()+":"+(rs.getObject(fields[i].getName())));
				}
					System.out.println(rs.getObject(fields[i].getName()).getClass().getName());
			}
				list.add(clas);
				
			}
			System.out.println(cun);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	public List query(String sqlstr,String voname,List ls){
		conn = co.getConnect(ConnOracle.filepath);
		pst = co.getStatement(conn, sqlstr);
		
		rs = co.getResultSet(pst);
		System.out.println(rs.toString());
		List list = new ArrayList();
		try {
			Class ca = Class.forName(voname);
			Field fields[] = ca.getDeclaredFields();
			System.out.println("query");
			System.out.println(fields[0].getName());
			int cun = 0 ;
			while(rs.next()){
				Object clas = ca.newInstance();
			for(int i = 0;i < fields.length; i++ ){
				/**
				 * setAccessible(true)让一个private和final类型的变量可以编辑
				 */
				
				fields[i].setAccessible(true);
				if (rs.getObject(fields[i].getName()) instanceof BigDecimal){
				fields[i].set(clas,(( BigDecimal)rs.getObject(fields[i].getName())).intValue());
				System.out.println(fields[i].getName()+":"+(( BigDecimal)rs.getObject(fields[i].getName())).intValue());
				}
				if(rs.getObject(fields[i].getName()) instanceof String){
					fields[i].set(clas,((String)rs.getObject(fields[i].getName())));
					System.out.println(fields[i].getName()+":"+((String)rs.getObject(fields[i].getName())));
				}
				if(rs.getObject(fields[i].getName()) instanceof Clob){
				fields[i].set(clas,(rs.getObject(fields[i].getName())));
				System.out.println(fields[i].getName()+":"+(rs.getObject(fields[i].getName())));
			    }
				
				if(rs.getObject(fields[i].getName()) instanceof Timestamp){
					fields[i].set(clas,(rs.getObject(fields[i].getName())));
					System.out.println(fields[i].getName()+":"+(rs.getObject(fields[i].getName())));
				}
					System.out.println(rs.getObject(fields[i].getName()).getClass().getName());
			}
				list.add(clas);
				
			}
			System.out.println(cun);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
