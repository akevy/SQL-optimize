package com.dboracle.util;

import java.util.Date;
import java.util.List;

public class Mybean {
 private int id;
 private String Name;
 private Date date;
 private List list;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public List getList() {
	return list;
}
public void setList(List list) {
	this.list = list;
}
}
