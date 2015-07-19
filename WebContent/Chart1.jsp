<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"
	import="com.dboracle.vo.SqlStatVo"
	import="java.util.List"
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<script src="http://s1.bdstatic.com/r/www/cache/ecom/esl/1-6-10/esl.js"></script>
</head>
<body>

    <%
    /* 
    	用于画单挑SQL的图
    */
    List list = (List)session.getAttribute("charvo"); 
    	Iterator  i = list.iterator();
    	StringBuffer xline = new StringBuffer();
    	StringBuffer yline = new StringBuffer();
    	SqlStatVo ssv =null;
    	while( i.hasNext()){
    		ssv =(SqlStatVo) i.next();
    	xline.append(ssv.getSnap());
    	xline.append(","); 
    	yline.append(ssv.getElapsed_time());
    	System.out.println();
    	yline.append(","); 
    	}
 	System.out.println(xline);
 	System.out.println(yline);
    %>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height: 400px"></div>
	<script type="text/javascript">
        // 路径配置
        require.config({
            paths:{ 
                'echarts' : 'http://echarts.baidu.com/build/echarts',
                'echarts/chart/bar' : 'http://echarts.baidu.com/build/echarts'
            }
        });
        
                require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                option = {
                	    title : {
                	    	/* 标题 */
                	        text: '单次执行时间',
                	        subtext: ''
                	    },
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['单位 秒']
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
                	            data : [<%=xline%>]
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    series : [
                	        {
                	            name:'ELAPSED_TIME',
                	            type:'bar',
                	            data:[<%=yline%>],
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
        
    </script>

</body>
</html>