package com.dboracle.conndb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.dboracle.servlet.JdbcManager;

/**
 * ��ȡJDBC������Ϣ
 * 
 * @author db-oracle
 */
public class ManagerProperties {
	private String ip;
	private String name;
	private String password;
	private String port;
	private String sid;
	private String table_prefix;
	private File file = null;
	private String filepath = null;

	public ManagerProperties(String filepath) {
		this.filepath = filepath;
		file = new File(filepath);
	}

	public void setProperties(String ip, String name, String password,
			String port, String sid,String table_prefix) throws IOException {
		this.ip = ip;
		this.name = name;
		this.password = password;
		this.port = port;
		this.sid = sid;
		this.table_prefix = table_prefix;

		FileWriter fw;
		try {
			fw = new FileWriter(file);
			String str = "ip=" + ip +"\n"+ "name=" + name + "\n" + "password="
					+ password + "\n" + "port=" + port + "\n" + "SID=" + sid+"\n"+"table_prefix="+ table_prefix;
			fw.write(str);
			System.out.println(str);
			System.out.println(file.getAbsolutePath());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map getProperties() {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			Map map = new HashMap();
			List list = new ArrayList<String>();
			while ((str = br.readLine()) != null){
				String stray[] = str.split("=");
				map.put(stray[0], stray[1]);
			}
			return map;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
