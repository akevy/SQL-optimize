package com.dboracle.test;

import java.lang.reflect.Field;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.dao.SqlExecute;

public class TestDao {
	public static void main(String []args){
		Integer i = 10;
		System.out.println(i.getClass().getName());
		System.out.println(new Date());
		System.out.println(new java.sql.Date(1000));
		System.out.println(new Timestamp(1000));
	}
}
