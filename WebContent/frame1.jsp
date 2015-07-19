<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dboracle.conndb.ConnOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#index").click(function(){
		
	});
});
</script>

<style type="text/css">
#divtop {
	border: 0px solid black;
	height: auto;
}

#divbutton {
	border: 0px solid black
}
</style>
</head>
<body>
<!-- 这个是顶部的frameset -->
	<div id="divtop">
		<span>Welcome</span> <span>数据库版本为</span><br />
		<% 
ConnOracle co = new ConnOracle();
String filepath=this.getServletConfig().getServletContext().getRealPath("/")+"connect.properties";
Connection conn = co.getConnect(filepath);
String sql = "select BANNER from v$version";
PreparedStatement pst = co.getStatement(conn, sql);
ResultSet rs = co.getResultSet(pst);
rs.next();
String str = rs.getString(1);
co.close(conn, pst, rs);
%>
		<%=str %></br>
		<span>dbid:</span>
		<%=application.getAttribute("dbid") %>
		
	</div>
	<div id="divbutton">
		<table>
			<tr>
			<td><a href="/SQlOptimize/index" target="_top" id="index">首页</a></td>
	<%
	co = new ConnOracle();
	conn = co.getConnect(filepath);
	sql = "select id,action,url,module from pub_action where module = ?";
	pst = co.getStatement(conn, sql);
	pst.setString(1, "top_module");
	rs = co.getResultSet(pst);
	while(rs.next()){
	%>
				<td><a href="<%=rs.getString(3) %>" target="showframe2"><%=rs.getString(2) %></a></td>
				
	<%} %>
			</tr>
		</table>
	</div>

</body>
</html>