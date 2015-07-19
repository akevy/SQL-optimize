<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"
    import="java.util.List"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
</head>
<body>
<p>首页</p>
<div>
<form action="/SQlOptimize/Main">
<span>请选择数据库：</span>
<select name="select1">
<%List list = (List)request.getAttribute("dbid");
Iterator i = list.iterator();
while(i.hasNext()){
	String str = (String)i.next();
%>
  <option value ="<%=str %>"><%=str %></option>
<%} %> 
</select>
<input type="submit" value="OK">
</form>
</div>
</body>
</html>