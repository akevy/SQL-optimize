package com.dboracle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.dboracle.conndb.ConnOracle;
import com.dboracle.dao.DHBI;
import com.dboracle.dao.DHSS;
import com.dboracle.dao.Ddb_hist_sysstat;
import com.dboracle.dao.IntegrateBufferCache;
import com.dboracle.dao.IntegrateCputime;
import com.dboracle.dao.IntegrateIOPS;
import com.dboracle.dao.IntegratePga;
import com.dboracle.dao.IntegratePhyread_write;
import com.dboracle.dao.IntegrateSession;
import com.dboracle.util.Transform;
import com.dboracle.vo.DbinstanVo;
import com.dboracle.vo.IntegratePhyReadVo;
import com.dboracle.vo.IntegrateVo;
import com.dboracle.vo.SqlStatVo;
import com.dboracle.vo.VsqlVo;
@WebServlet(name="ChartServlet",urlPatterns={"/ChartServlet"})
public class ChartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		String action = request.getParameter("action");
		System.out.println(action);
		if(action.equals("sqlsummery")){
			request.getRequestDispatcher("/Chart2.jsp").forward(request, response);
		}
		
		/**
		 * 
		 * 处理Chart2.jsp的表格和图表的展示
		 */
		if(action.equals("chart2view")){
			String type = request.getParameter("type");
			String dbid = request.getParameter("dbid");
			String instance_num = request.getParameter("instance_num");
			String begin = request.getParameter("begin_time");
			String end = request.getParameter("end_time");
			Timestamp begin_time = null;
			Timestamp end_time = null;
			/**
			 * 如果前台没有写时间，则查全部数据
			 * 
			 */
			if(begin ==null&& end==null){
			Date begin_d = Transform.StringToDate(begin);
			Date end_d = Transform.StringToDate(end);
			 begin_time =new Timestamp(begin_d.getTime());
			 end_time = new Timestamp(end_d.getTime());
			}
			//System.out.println(type+" @ "+dbid+" @ "+instance_num+" @ "+begin_time+" @ "+end_time);
			DHSS dhs = new DHSS();
			
			List list = dhs.fillResult(type, begin_time, end_time, dbid, instance_num);			
			Iterator it = list.iterator();
			while(it.hasNext()){
			SqlStatVo bs = (SqlStatVo)it.next();
			}
			JSONArray jsa = JSONArray.fromObject(list);
			out.println(jsa);
			

		}
		
		if(action.equals("init")){
			DHBI dhbi = new DHBI();
			List list = dhbi.fillResult();
			JSONArray jsa = JSONArray.fromObject(list);
			out.println(jsa);
		}
		
		if(action.equals("instance_num")){
			String dbid = request.getParameter("dbid");
			DHBI dhbi = new DHBI();
			List list = dhbi.fillResult(dbid);
			JSONArray jsa = JSONArray.fromObject(list);
			out.println(jsa);
		}
		
		if(action.equals("performance")){
			
		}
//		整合评估适合使用到的数据库选择
		if(action.equals("selectdb")){
			request.getRequestDispatcher("/IntegrateSelectdb.jsp").forward(request, response);
		}
		
		if(action.equals("db_time")){
			request.getRequestDispatcher("/dbtime.jsp").forward(request, response);
		}
		
		if(action.equals("session")){
			request.getRequestDispatcher("/IntegrateChart.jsp").forward(request, response);
		}
		
		if(action.equals("cputime")){
			request.getRequestDispatcher("/IntegrateChart2.jsp").forward(request, response);
		}
		if(action.equals("buffercache")){
			request.getRequestDispatcher("/IntegrateChart3.jsp").forward(request, response);
		}
		if(action.equals("pga")){
			request.getRequestDispatcher("/IntegrateChart4.jsp").forward(request, response);
		}
		if(action.equals("PhysicalRead")){
			request.getRequestDispatcher("/IntegrateChart5.jsp").forward(request, response);
		}
		if(action.equals("PhysicalWrite")){
			request.getRequestDispatcher("/IntegrateChart6.jsp").forward(request, response);
		}
		if(action.equals("RedoWrite")){
			request.getRequestDispatcher("/IntegrateChart7.jsp").forward(request, response);
		}
		if(action.equals("IOPS")){
			request.getRequestDispatcher("/IntegrateChart8.jsp").forward(request, response);
		}
		
		
		/**
		 * 页面中的AJAX请求使用的action
		 * 
		 */
		if(action.equals("Intergate")){
			String action2 = request.getParameter("action2");
			if(action2.equals("session")){
				IntegrateSession ivo = new IntegrateSession();
				List list = ivo.fillResult();
				JSONArray jsa = JSONArray.fromObject(list);
				out.println(jsa);
			}
			
			if(action2.equals("cputime")){
				String action3 = request.getParameter("action3");
				
				if(action3.equals("chart1")){
					IntegrateCputime ivo = new IntegrateCputime();
					List list = ivo.fillResult(ivo.sql);
					JSONArray jsa = JSONArray.fromObject(list);
					System.out.println(jsa);
					System.out.println(action3);
					out.println(jsa);
					}
				if(action3.equals("chart2")){
					IntegrateCputime ivo = new IntegrateCputime();
					List list = ivo.fillResult(ivo.sql2);
					JSONArray jsa = JSONArray.fromObject(list);
					System.out.println(jsa);
					out.println(jsa);	
				}
			
			}
			if(action2.equals("buffercache")){
				String action3 = request.getParameter("action3");
				
				if(action3.equals("chart1")){
					IntegrateBufferCache ivo = new IntegrateBufferCache();
					List list = ivo.fillResult(ivo.sql);
					JSONArray jsa = JSONArray.fromObject(list);
					System.out.println(jsa);					
					out.println(jsa);
					}
				if(action3.equals("chart2")){
					IntegrateBufferCache ivo = new IntegrateBufferCache();
					List list = ivo.fillResult2(ivo.sql2);
					JSONArray jsa = JSONArray.fromObject(list);
										
					out.println(jsa);
					}
			
			
			}
			
			if(action2.equals("pga")){
				IntegratePga ivo = new IntegratePga();
				List list = ivo.fillResult();
				JSONArray jsa = JSONArray.fromObject(list);
				out.println(jsa);
			}
			
			if(action2.equals("phyread")){				
				IntegratePhyread_write ivo = new IntegratePhyread_write();
				List list = ivo.fillResult(ivo.sql);
				JSONArray jsa = JSONArray.fromObject(list);
				System.out.println(jsa);
				out.println(jsa);
			}
			if(action2.equals("phywrite")){				
				IntegratePhyread_write ivo = new IntegratePhyread_write();
				List list = ivo.fillResult(ivo.sql2);
				JSONArray jsa = JSONArray.fromObject(list);
				System.out.println(jsa);
				out.println(jsa);
			}
			if(action2.equals("redowrite")){				
				IntegratePhyread_write ivo = new IntegratePhyread_write();
				List list = ivo.fillResult(ivo.sql3);
				JSONArray jsa = JSONArray.fromObject(list);
				System.out.println(jsa);
				out.println(jsa);
			}
			
			if(action2.equals("IOPS")){
				String action3 = request.getParameter("action3");
				if(action3.equals("chart1")){					
					IntegrateIOPS ivo = new IntegrateIOPS();
					List list = ivo.fillResult(ivo.sql);
					JSONArray jsa = JSONArray.fromObject(list);
					System.out.println(jsa);					
					out.println(jsa);
					}
				if(action3.equals("chart2")){					
					IntegrateIOPS ivo = new IntegrateIOPS();
					List list = ivo.fillResult2(ivo.sql2);
					JSONArray jsa = JSONArray.fromObject(list);
					System.out.println(jsa);					
					out.println(jsa);
					}
			}
			
			if(action2.equals("selectdb")){
				
			}
			
			
		}
		
		/**
		 * 性能概览部分使用的action
		 */
		if(action.equals("overviewperformance")){
			String action2 = request.getParameter("action2");
			if(action2.equals("dbtime")){
				String dbid = request.getParameter("dbid");
				String instance_number = request.getParameter("instance_number");
				String statname = request.getParameter("statname");
				@SuppressWarnings("unused")
				Ddb_hist_sysstat ds = new Ddb_hist_sysstat();
				List list = ds.fillResult("100", dbid, instance_number, statname);
				JSONArray jsa = JSONArray.fromObject(list);
				System.out.print(jsa);
			}
		}
		
		
		
	}


}
