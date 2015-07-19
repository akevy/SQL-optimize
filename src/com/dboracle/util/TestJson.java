package com.dboracle.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json-lib test
 * 
 * @author db-oracle
 * 
 */
public class TestJson {
	public static void main(String[] args) {
		TestJson tj = new TestJson();
		tj.testArraryToJson();
		tj.testCollectionToJson();
		tj.testMapToJson();
		tj.testBeanToJson();
		tj.testJsonToObject();
	}

	/**
	 * 数组转换为json
	 */
	public void testArraryToJson() {
		boolean[] boolarray = new boolean[] { true, false, true };
		JSONArray jsobject = JSONArray.fromObject(boolarray);
		System.out.println(jsobject);
	}

	/**
	 * Collection转换为json
	 */
	public void testCollectionToJson() {
		List list = new ArrayList();
		list.add("testno1");
		list.add("testno2");
		list.add("testno3");
		JSONArray jsay = JSONArray.fromObject(list);
		System.out.println(jsay);

		Set set = new HashSet<String>();
		set.add("testset1");
		set.add("testset2");
		jsay = JSONArray.fromObject(set);
		System.out.println(jsay);
	}

	/**
	 * Map转换为json
	 */
	public void testMapToJson() {
		HashMap map = new HashMap();
		map.put("name", "json");
		map.put("name2", "json2");

		/**
		 * HashMap 也不能有重复的key，如果有重复的key,则最后的value覆盖前边的
		 */
		map.put("name", "json3");

		JSONArray js = JSONArray.fromObject(map);
		System.out.println(js);
	}

	/**
	 * 复合类型
	 */

	public void testBeanToJson() {
		Mybean bean = new Mybean();
		bean.setId(1);
		bean.setName("银行卡");
		bean.setDate(new Date());
		List cardNum = new ArrayList();
		cardNum.add("农行");
		cardNum.add("建行");
		cardNum.add("工行");
		cardNum.add(new Person("test"));
		bean.setList(cardNum);

		JSONArray js = JSONArray.fromObject(bean);
		System.out.println(js);

	}

	/**
	 * JSON 转换为object
	 */
	public void testJsonToObject() {
		String json = "{name:'json',bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		System.out.println(jsonObject);
		System.out.println(jsonObject.get("name"));
		List list = (List) JSONArray.toCollection(jsonObject.getJSONArray("array"));
		for (Object obj:list){
			System.out.println(obj);
		}
	}
}
