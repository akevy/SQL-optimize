<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
$("button").click(function(){
$("#p1").html("ÅäÖÃJDBCÁ¬½Ó×Ö·û´®");
$("#p2").hide();});

/* $("#sub").click(function(){
	alert($("#form_jdbc").serialize());
	$.post("utilManager",$("#form_jdbc").serialize(),function(data,status){
	    alert("Data: " + data + "\nStatus: " + status);
	  });
	}); */

});


	
</script>
</head>
<body>
<h2>SQL optimization</h2>
<p id="p1">This s Beta</p>
<p id="p2">Please Click "Click me"</p>
<button type="button">Click me</button>
<form id="form_jdbc" action="/SQlOptimize/utilManager" method="post">
	<span>ip:</span><input name="ip" type="text"/><br/>
	<span>username:</span><input name="username" type="text"/><br/>
	<span>password:</span><input name="password" type="password"/><br/>
	<span>port:</span><input name="port" type="text"/><br/>
	<span>SID:</span><input name="SID" type="text"/><br/>
	<span>table_prefix:</span><input name="table_prefix" type="text"/><br/>
	<input id="sub" type="submit" value="submit"/>
</form>
</body>
</html>