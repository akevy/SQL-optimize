<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.3/highcharts.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.3/highcharts-3d.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"IOPS",
		   			action3:"chart1"
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var yline = new Array();
			     var yline2 = new Array();
			     $.each(jsonData1, function(index, objVal) { 			    	
			    		 xline.push(objVal.snap_time);
			    		 yline.push(objVal.read_value);			    	
			    		 yline2.push(objVal.write_value);			    	               
		         });
			     myFunction(xline,yline,yline2);	
			   });
	 
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"IOPS",
		   			action3:"chart2"
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var yline = new Array();
			     var num = 0;
			     $.each(jsonData1, function(index, objVal) {
			    	var yline2 = new Array();
			    	num = num + 150;
			    	yline2[0] = parseFloat(num);
			    	yline2[1] = parseFloat(objVal.value);
			    	yline.push(yline2);
            
		         });			
			     alert(yline);
			     myFunction2(xline,yline);	
			   });
	
	 
	
});
</script>


<script type="text/javascript">
function myFunction(xline,yline,yline2)
{
	$(function () {
	    $('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'IOPS Read and Write'
	        },subtitle: {
	            text: 'From EnmoTech'
	        },
	        xAxis: {
	            categories: xline
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'IO Request '
	            },
	            stackLabels: {
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        legend: {
	            align: 'right',
	            x: -70,
	            verticalAlign: 'top',
	            y: 20,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
	            borderColor: '#CCC',
	            borderWidth: 1,
	            shadow: false
	        },
	        tooltip: {
	            formatter: function() {
	                return '<b>'+ this.x +'</b><br/>'+
	                    this.series.name +': '+ this.y +'<br/>'+
	                    'Total: '+ this.point.stackTotal;
	            }
	        },
	        plotOptions: {
	            column: {
	                stacking: 'normal',
	                dataLabels: {
	                    enabled: true,
	                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
	                }
	            }
	        },
	        series: [{
	            name: 'singlerw',
	            data: yline
	        }, {
	            name: 'muiltrw',
	            data: yline2
	        }]
	    });
	});		
}


function myFunction2(xline,yline)
{
	$(function () {                                                                                  
	    $('#container2').highcharts({                                                             
	        chart: {                                                                             
	            type: 'scatter',                                                                 
	            zoomType: 'xy'                                                                   
	        },                                                                                   
	        title: {                                                                             
	            text: 'LGWR Write Wait(s)'                        
	        },                                                                                   
	        subtitle: {                                                                          
	            text: 'From EnmoTech'                                                      
	        },                                                                                   
	        xAxis: {                                                                             
	            title: {                                                                         
	                enabled: true,                                                               
	                text: 'Wait (s)'                                                          
	            },                                                                               
	            startOnTick: true,                                                               
	            endOnTick: true,                                                                 
	            showLastLabel: true                                                              
	        },                                                                                   
	        yAxis: {                                                                             
	            title: {                                                                         
	                text: 'LGWR Wait (s) '                                                          
	            }                                                                                
	        },                                                                                   
	        legend: {                                                                            
	            layout: 'vertical',                                                              
	            align: 'left',                                                                   
	            verticalAlign: 'top',                                                            
	            x: 100,                                                                          
	            y: 10,                                                                           
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
	                    pointFormat: '{point.y} '                                
	                }                                                                            
	            }                                                                                
	        },                                                                                   
	        series: [ {                                                                                 
	            name: 'LGWR Wait',                                                                    
	            color: 'rgba(119, 152, 191, .5)',                                                
	            data: yline                                            
	        }]                                                                                   
	    });                                                                                      
	});                                                                                          				
}


		</script>
	</head>
	<body>
 <div id="container" style="min-width:700px;height:400px"></div>
 
 <div id="container2" style="min-width:700px;height:400px"></div>

</body>
</html>