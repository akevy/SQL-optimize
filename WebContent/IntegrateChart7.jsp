<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.3/highcharts.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.3/highcharts-3d.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highstock/2.0.3/highcharts-more.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"redowrite"
		   			
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var xline2 = new Array();
			     var xline3 = new Array();
			     
			     $.each(jsonData1, function(index, objVal) { 
			    	 var yline2 = new Array();
			    	 yline2[0] = objVal.snap_time;
			    	 yline2[1] = objVal.min_value;
			    	 yline2[2] = objVal.max_value;
		             xline.push(yline2);
		             var yline3 = new Array();
		             yline3[0] = objVal.snap_time;
		             yline3[1] = objVal.avg_value;
		             xline2.push(yline3);
		             xline3.push(objVal.snap_time);
		                   
		         });
			    
			     myFunction2(xline,xline2,xline3);
			   });
	
});
</script>


<script type="text/javascript">


function myFunction2(xline,xline2,xline3)
{
	$(function () {
		var ranges = xline,
		averages = xline2;

	    
		$('#container').highcharts({
		
			title: {
				text: 'Redo Write (MB/s)'
			},                                                                                   
	        subtitle: {                                                                          
	            text: 'From EnmoTech'                                                     
	        }, 
			
			xAxis: {
				/* type: 'datetime' */
				categories:xline3
			},
			
			yAxis: {
				title: {
					text: 'Redo Write MB/s'
				}
			},
			
			tooltip: {
				crosshairs: true,
				shared: true,
				valueSuffix: ' MB/s'
			},
			
			legend: {
			},
			
			series: [{
				name: 'average',
				data: averages,
				zIndex: 1,
				marker: {
					fillColor: 'white',
					lineWidth: 2,
					lineColor: Highcharts.getOptions().colors[0]
				}
			}, {
				name: 'Range',
				data: ranges,
				type: 'arearange',
				lineWidth: 0,
				linkedTo: ':previous',
				color: Highcharts.getOptions().colors[0],
				fillOpacity: 0.3,
				zIndex: 0
			}]
		
		});
	    
	});				                                                    				
}

		</script>
	</head>
	<body>
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>