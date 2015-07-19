<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择需要评估的数据库</title>
<script type="text/javascript"
	src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<!-- bootstap组件 -->
<link
	href="/SQlOptimize/JS/bootstrap/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/SQlOptimize/JS/bootstrap/starter-template.css"
	rel="stylesheet">
<script src="/SQlOptimize/JS/bootstrap/ie-emulation-modes-warning.js"></script>
<script
	src="/SQlOptimize/JS/bootstrap/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/SQlOptimize/JS/bootstrap/ie10-viewport-bug-workaround.js"></script>

<!-- bootstap组件 -->
<script type="text/javascript">
$(document).ready(function(){
	
	/*选择dbid*/
	var dbstr = '';
	 $(".gettr1").mouseup(function(){
		 
		 var str = $(".gettr1 td").get(0).innerHTML;
		 str = str.trim()+',';
		 dbstr += str;
			$(".form-control").get(0).value= dbstr; 
		  });
	 $(".gettr2").mouseup(function(){
		 var str = $(".gettr2 td").get(0).innerHTML;
		 str = str.trim()+',';
		 dbstr += str;
			$(".form-control").get(0).value= dbstr;
		  });	
	 $(".gettr3").mouseup(function(){
		 var str = $(".gettr3 td").get(0).innerHTML;
		 str = str.trim()+',';
		 dbstr += str;
			$(".form-control").get(0).value= dbstr;
		  });	
	 $(".gettr4").mouseup(function(){
		 var str = $(".gettr4 td").get(0).innerHTML;
		 str = str.trim()+',';
		 dbstr += str;
			$(".form-control").get(0).value= dbstr;
		  });
	 $(".gettr5").mouseup(function(){
		 var str = $(".gettr4 td").get(0).innerHTML;
		 str = str.trim()+',';
		 dbstr += str;
			$(".form-control").get(0).value= dbstr;
		  });
	 
	 /*table hide*/
	 $("#OK1").mouseup(function(){
		$("#table_hide").hide();
		  });
	 $("#NO1").mouseup(function(){
			$("#table_hide").show();
			$(".form-control").get(0).value= "";
			  });
	
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"selectdb"
		   			
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var yline = new Array();
			     var yline2 = new Array();
			     $.each(jsonData1, function(index, objVal) { 						
		                    xline.push(objVal.snap_time);
		                    yline.push(objVal.value * 1);
		                    yline2.push(objVal.value * 2);
		         });
			     myFunction(xline,yline,yline2);			     
			   });
	 
});

</script>

</head>
<body>

<h6>请选择需要评估的数据库,dbid以逗号分隔</h6>

<div class="container-fluid">
<div class="row-fluid">
<div>
	<input type="text" placeholder="eg:dbid1,dbid2[...]" class="form-control">
</div>
	<div class="span12">
		 <button class="btn btn-primary btn-small" id = "OK1" type="button">确定</button>
		  <button class="btn btn-small" id = "NO1" type="button">清空</button>
	</div>
</div>
</div>

<div class="container-fluid">
<div class="row-fluid">
	<div class="span12">
		<h6>
			请使用鼠标点击需要整合分析的行
		</h6>
		<table class="table table-bordered table-condensed table-hover" id = "table_hide">
			<thead>
				<tr>
					<th>
						dbid
					</th>
					<th>
						dbname
					</th>
					<th>
						start_snaptime
					</th>
					<th>
						end_snaptime
					</th>
				</tr>
			</thead>
			<tbody>
				<tr class = "gettr1">
					<td class = "getdbid">
						1
					</td>
					<td>
						TB - Monthly
					</td>
					<td>
						01/04/2012
					</td>
					<td>
						Default
					</td>
				</tr>
						<tr class = "gettr2">
					<td class = "getdbid">
						2
					</td>
					<td>
						TB - Monthly
					</td>
					<td>
						01/04/2012
					</td>
					<td>
						Default
					</td>
				</tr>
						<tr class = "gettr3">
					<td class = "getdbid">
						3
					</td>
					<td>
						TB - Monthly
					</td>
					<td>
						01/04/2012
					</td>
					<td>
						Default
					</td>
				</tr>
								<tr class = "gettr4">
					<td class = "getdbid">
						3
					</td>
					<td>
						TB - Monthly
					</td>
					<td>
						01/04/2012
					</td>
					<td>
						Default
					</td>
				</tr>
				
								<tr class = "gettr5">
					<td class = "getdbid">
						3
					</td>
					<td>
						TB - Monthly
					</td>
					<td>
						01/04/2012
					</td>
					<td>
						Default
					</td>
				</tr>
				
			</tbody>
		</table>
	</div>
</div>
</div>

</body>
</html>