<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/SQlOptimize/JS/esl/esl.js"></script>
<script type="text/javascript" src="/SQlOptimize/JS/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	 $.post("/SQlOptimize/ChartServlet",
			   {
		   			action:"Intergate",
		   			action2:"session"
			   },
			   function(data,status){			     
			     var jsonData1 = eval(data);
			     var xline = new Array();
			     var yline = new Array();
			     var yline2 = new Array();
			     $.each(jsonData1, function(index, objVal) { 						
		                    xline.push(objVal.snap_time);
		                    yline.push(objVal.value);
		                    yline2.push(objVal.value * 2);
		         });
			     myFunction(xline,yline,yline2);
			   });
	
	
});
</script>

<script type="text/javascript">
function myFunction(xline,yline,yline2)
{
	 // 路径配置
    require.config({
        paths:{ 
            'echarts' : '/SQlOptimize/JS/echart/echarts',
            'echarts/chart/bar' : '/SQlOptimize/JS/echart/echarts/echarts'
            
        }
    });
    
            require(
        [
            'echarts',
            'JS/theme/blue',
            'echarts/chart/bar' 
            
        ],
        function (ec,theme) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'),theme); 
            
            option = {
            	    title : {
            	        text: '整合后session数量',
            	        subtext: 'Enmotech'
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['当前业务量','两倍业务量']
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            magicType : {show: true, type: ['line', 'bar']},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'category',
            	            data : xline
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'当前业务量',
            	            type:'bar',
            	            data:yline,
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
            	        } ,
            	        {
            	            name:'两倍业务量',
            	            type:'bar',
            	            data:yline2,
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            },
            	             
            	            markLine : {
            	                data : [
            	                    {type : 'average', name : '平均值'}
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
</head>
<body>
<div id="main" style="height: 300px"></div>
</body>
</html>