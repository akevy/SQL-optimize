<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts-3d.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"buffercache",
		   			action3:"chart1"
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var yline = new Array();
			     var yline2 = new Array();
			     $.each(jsonData1, function(index, objVal) { 
			    	 if(objVal.instance_number==1){
			    		 xline.push(objVal.snap_time);
			    		 yline.push(objVal.value);
			    	 }else{
			    		 yline2.push(objVal.value);
			    	 }
              
		         });
			     myFunction(xline,yline,yline2);	
			   });
	 
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"buffercache",
		   			action3:"chart2"
			   },
			   function(data,status){
			    
			    var jsonData1 = eval(data);
			     var xline = new Array();
			     var xline2 = new Array();			    			    
			     $.each(jsonData1, function(index, objVal) { 			    	 
			    	 if(objVal.instance_number==1){
			    		 var yline = new Array();
			    		 yline[0]=objVal.value;
			    		 yline[1]=objVal.bch;
			    		 xline.push(yline);
			    		 /* yline.push(objVal.bch); */
			    	 }else{
			    		 var yline2 = new Array();
			    		 yline2[0]=objVal.value;
			    		 yline2[1]=objVal.bch;
			    		 xline2.push(yline2);
			    	 }
            
		         });
			     
			     myFunction2(xline,xline2);	
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
	            text: 'Buffer Cache(GB)'
	        },subtitle: {
	            text: 'From EnmoTech'
	        },
	        xAxis: {
	            categories: xline
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'Buffer Cache size GB'
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
	            name: 'Node1',
	            data: yline
	        }, {
	            name: 'Node2',
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
	            text: 'BufferCache and BuffeCache Hit'                        
	        },                                                                                   
	        subtitle: {                                                                          
	            text: 'From EnmoTech'                                                      
	        },                                                                                   
	        xAxis: {                                                                             
	            title: {                                                                         
	                enabled: true,                                                               
	                text: 'BufferCache Size (GB)'                                                          
	            },                                                                               
	            startOnTick: true,                                                               
	            endOnTick: true,                                                                 
	            showLastLabel: true                                                              
	        },                                                                                   
	        yAxis: {                                                                             
	            title: {                                                                         
	                text: 'BufferCache Hit '                                                          
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
	                    pointFormat: '{point.x} GB <br> {point.y} '                                
	                }                                                                            
	            }                                                                                
	        },                                                                                   
	        series: [{                                                                           
	            name: 'node1',                                                                  
	            color: 'rgba(223, 83, 83, .5)',                                                  
	            data: xline
	                                                                                             
	        }, {                                                                                 
	            name: 'node2',                                                                    
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