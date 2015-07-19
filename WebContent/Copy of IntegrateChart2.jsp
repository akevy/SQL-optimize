<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/SQlOptimize/JS/highcharts.js"></script>
<script type="text/javascript" src="/SQlOptimize/JS/highcharts-3d.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"cputime",
		   			action3:"chart1"
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
	
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"cputime",
		   			action3:"chart2"
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
			     myFunction2(xline,yline,yline2);
			   });
	
});
</script>


<script type="text/javascript">



function myFunction(xline,yline,yline2)
{
	$(function () {                                                                                  
	    $('#container').highcharts({                                                             
	        chart: {                                                                             
	            type: 'scatter',                                                                 
	            zoomType: 'xy'                                                                   
	        },                                                                                   
	        title: {                                                                             
	            text: 'CPUTIME'                        
	        },                                                                                   
	        subtitle: {                                                                          
	            text: 'From EnmoTech'                                                      
	        },                                                                                   
	        xAxis: {                                                                             
	            title: {                                                                         
	                enabled: true,                                                               
	                text: '散点数'                                                          
	            },                                                                               
	            startOnTick: true,                                                               
	            endOnTick: true,                                                                 
	            showLastLabel: true
	            
	            
	        },                                                                                   
	        yAxis: {                                                                             
	            title: {                                                                         
	                text: 'CPUTIME s/s'                                                          
	            }                                                                                
	        },                                                                                   
	        legend: {                                                                            
	            layout: 'vertical',                                                              
	            align: 'left',                                                                   
	            verticalAlign: 'top',                                                            
	            x: 100,                                                                          
	            y: 70,                                                                           
	            floating: true,                                                                  
	            backgroundColor: '#FFFFFF',                                                      
	            borderWidth: 1                                                                   
	        },                                                                                   
	        plotOptions: {                                                                       
	            scatter: {                                                                       
	                marker: {                                                                    
	                    radius: 5,                                                               
	                    states: {                                                                
	                        hover: {                                                             
	                            enabled: true,                                                   
	                            lineColor: 'rgb(100,100,100)'                                    
	                        }                                                                    
	                    }                                                                        
	                },                                                                           
	                states: {                                                                    
	                    hover: {                                                                 
	                        marker: {                                                            
	                            enabled: false                                                   
	                        }                                                                    
	                    }                                                                        
	                },                                                                           
	                tooltip: {                                                                   
	                    headerFormat: '<b>{series.name}</b><br>',                                
	                    pointFormat: '{point.y} s/s'                                
	                }                                                                            
	            }                                                                                
	        },                                                                                   
	        series: [{                                                                           
	            name: '当前负载',                                                                  
	            color: 'rgba(223, 83, 83, .5)',                                                  
	            data: yline  
	                                                                                             
	        }/* ,{                                                                           
	            name: '两倍负载',                                                                  
	            color: 'rgba(119, 152, 191, .5)',                                                  
	            data: yline2  
	                                                                                             
	        } */]                                                                                   
	    });                                                                                      
	});                                                                                          				
}

function myFunction2(xline,yline,yline2)
{
	$(function () {
	    // Set up the chart
	    var chart = new Highcharts.Chart({
	        chart: {
	            renderTo: 'container2',
	            type: 'column',
	            margin: 75,
	            options3d: {
	                enabled: true,
	                alpha: 15,
	                beta: 15,
	                depth: 50,
	                viewDistance: 25
	            }
	        },
	        title: {
	            text: 'CPUTIME 每日峰值(s/s)'
	        },
	        subtitle: {
	            text: 'From EnmoTech'
	        },
           
	        plotOptions: {
	            column: {
	                depth: 25,
	                 dataLabels: {
	                    enabled: true
	                }
	            },
	             line: {
	                dataLabels: {
	                    enabled: true
	                }
	            }
	        },xAxis: {
	        	categories:xline
	        },
	        series: [{
	        	name:"CPUTIME ",
	            data: yline
	        }]
	    });
	    

	    // Activate the sliders
	    $('#R0').on('change', function(){
	        chart.options.chart.options3d.alpha = this.value;
	        showValues();
	        chart.redraw(false);
	    });
	    $('#R1').on('change', function(){
	        chart.options.chart.options3d.beta = this.value;
	        showValues();
	        chart.redraw(false);
	    });

	    function showValues() {
	        $('#R0-value').html(chart.options.chart.options3d.alpha);
	        $('#R1-value').html(chart.options.chart.options3d.beta);
	    }
	    showValues();
	});				
}

		</script>
	</head>
	<body>



<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<div id="container2" style="min-width:700px;height:400px"></div>
﻿	<div id="sliders" style="min-width:310px;max-width: 800px;margin: 0 auto;">
		<table>
			<tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span></td></tr>
			<tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span></td></tr>
		</table>
	</div>

</body>
</html>