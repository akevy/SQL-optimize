<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>
<script src="http://s1.bdstatic.com/r/www/cache/ecom/esl/1-6-10/esl.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.post("/SQlOptimize/ChartServlet",
			   {
		 			action:"init"
			   },
			   function(data,status){
			     
			     var jsonData = eval(data);
			     
			     $("#select2").val("");
			     $("#select2").children().remove();
			     
			     $.each(jsonData, function(index, objVal) { 
			   	  var ap = "<option value="+objVal.dbid+">"+objVal.db_name+"</option>";
			    	 $("#select2").append(ap);	
	                     
	                });
			   
			  
			    
			   });
	
	 $("#select2").click(function(){
		 $("#select3").children().remove();
		 $.post("/SQlOptimize/ChartServlet",
				   {
			   		 action:"instance_num",
				     dbid:$("#select2").val(),
				   },
				   function(data,status){
				    /*  alert("Data: " + data + "\nStatus: " + status); */
				    /* eval把json字符串转换为json对象 */
				     var jsonData1 = eval(data);
				     var num = 0;
				     $.each(jsonData1, function(index, objVal) { 						
			                     num = objVal.instance_num;
			         });
				     for(var i=1;i<=num;i++){
				    	 var ap = "<option value="+i+">"+i+"</option>";
				    	 $("#select3").append(ap);	
				     }
				   });
	 });
	 
	 $("button").click(function(){
		  $.post("/SQlOptimize/ChartServlet",
		  {
		    action:"overviewperformance",
		    action2:"dbtime",
		    dbid:$("#select2").val(),
		    instance_number:$("#select3").val(),
		    statname:"DB time"
		  },
		  function(data,status){
			  
			  myCharts();
		  });
		});
	 
	 
	 
	 
	 
	
});




function myCharts(){
	 // 路径配置
	 /* var hash = location.hash.replace('#','') || 'blue'; */
	 
   require.config({
       paths:{ 
           'echarts' : 'http://echarts.baidu.com/build/echarts',
           'echarts/chart/bar' : 'http://echarts.baidu.com/build/echarts'
       }
   });
   
           require(
       [
		   
           'echarts',
           'JS/theme/blue',
           'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
         
       ],
       function (ec,theme) {
           // 基于准备好的dom，初始化echarts图表
           var myChart = ec.init(document.getElementById('main'),theme); 
           
          
           option = {
        		    tooltip : {
        		        trigger: 'axis'
        		    },
        		    toolbox: {
        		        show : true,
        		        feature : {
        		            mark : {show: true},
        		            dataView : {show: true, readOnly: false},
        		            magicType: {show: true, type: ['line', 'bar']},
        		            restore : {show: true},
        		            saveAsImage : {show: true}
        		        }
        		    },
        		    calculable : true,
        		    legend: {
        		        data:['DBTIME','CPUTIME','LOGICREAD','PHYSICALREAD']
        		    },
        		    xAxis : [
        		        {
        		            type : 'category',
        		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        		        }
        		    ],
        		    yAxis : [
        		        {
        		            type : 'value',
        		            name : '时间',
        		            axisLabel : {
        		                formatter: '{value} ms'
        		            }
        		        },
        		        {
        		            type : 'value',
        		            name : '块',
        		            axisLabel : {
        		                formatter: '{value} block'
        		            }
        		        }
        		    ],
        		    series : [

        		        {
        		            name:'DBTIME',
        		            type:'bar',
        		            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
        		           markPoint : {
        		            	                data : [
        		            	                    {type : 'max', name: '最大值'},
        		            	                    {type : 'min', name: '最小值'}
        		            	                ]
        		            	            },
        		            	            markLine : {
        		            	                data : [
        		            	                    {type : 'average', name: '平均值'}
        		            	                ]
        		            	            },
        		        },
        		        
        		        {
        		            name:'CPUTIME',
        		            type:'bar',
        		            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
        		           markPoint : {
        		            	                data : [
        		            	                    {type : 'max', name: '最大值'},
        		            	                    {type : 'min', name: '最小值'}
        		            	                ]
        		            	            },
        		            	            markLine : {
        		            	                data : [
        		            	                    {type : 'average', name: '平均值'}
        		            	                ]
        		            	            }
        		            	           
        		        },
        		        {
        		            name:'LOGICREAD',
        		            type:'line',
        		            yAxisIndex: 1,
        		            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2],
        		           markPoint : {
        		            	                data : [
        		            	                    {type : 'max', name: '最大值'},
        		            	                    {type : 'min', name: '最小值'}
        		            	                ]
        		            	            },
        		            	            markLine : {
        		            	                data : [
        		            	                    {type : 'average', name: '平均值'}
        		            	                ]
        		            	            }
        		        },
        		     	 {
        		            name:'PHYSICALREAD',
        		            type:'line',
        		            yAxisIndex: 1,
        		            data:[1.0, 1.2, 2.3, 3.5, 5.3, 10.2, 10.3, 13.4, 13.0, 6.5, 9.0, 6.2],
        		            markPoint : {
        		            	                data : [
        		            	                    {type : 'max', name: '最大值'},
        		            	                    {type : 'min', name: '最小值'}
        		            	                ]
        		            	            },
        		            	            markLine : {
        		            	                data : [
        		            	                    {type : 'average', name: '平均值'}
        		            	                ]
        		            	            }
        		        }
        		    ]
        		};
        		                    
           	                    
    
           // 为echarts对象加载数据 
           myChart.setOption(option);
     
       }
   );
}



</script>
</head>
<body>
<span>db_name:</span>
<select name="select2" id="select2">
</select>
<span>instance_num:</span>
<select name="select3" id="select3">
</select>
<div id = "submit"><button id="button">提交</button></div>
<div id="main" style="height: 500px"></div>

</body>
</html>