package com.dboracle.test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestDynamicAttribute extends SimpleTagSupport implements DynamicAttributes{
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspWriter out = getJspContext().getOut();
		out.println("这是一个简单的属性输出");
		for (int i=0; i < keys.size(); i++) {
			out.println(keys.get(i)+":"+values.get(i));
		}
	}

	@Override
	public void setDynamicAttribute(String arg0, String arg1, Object arg2)
			throws JspException {
		
		keys.add(arg1);
		values.add(arg2);
		
	}

}
