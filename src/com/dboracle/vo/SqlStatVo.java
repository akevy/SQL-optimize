package com.dboracle.vo;

public class SqlStatVo {
	private String snap;
	private String sql_id;
	private String elapsed_time;
	private String snap_time;
	private String px_server_avg;
	private String parse_call_avg;
	private String buffer_get_avg;
	private String disk_read_avg;
	private String cpu_time_avg;
	private String sharable_mem_avg;
	
	public String getPx_server_avg() {
		return px_server_avg;
	}
	public void setPx_server_avg(String px_server_avg) {
		this.px_server_avg = px_server_avg;
	}
	public String getParse_call_avg() {
		return parse_call_avg;
	}
	public void setParse_call_avg(String parse_call_avg) {
		this.parse_call_avg = parse_call_avg;
	}
	public String getBuffer_get_avg() {
		return buffer_get_avg;
	}
	public void setBuffer_get_avg(String buffer_get_avg) {
		this.buffer_get_avg = buffer_get_avg;
	}
	public String getDisk_read_avg() {
		return disk_read_avg;
	}
	public void setDisk_read_avg(String disk_read_avg) {
		this.disk_read_avg = disk_read_avg;
	}
	public String getCpu_time_avg() {
		return cpu_time_avg;
	}
	public void setCpu_time_avg(String cpu_time_avg) {
		this.cpu_time_avg = cpu_time_avg;
	}
	public String getSharable_mem_avg() {
		return sharable_mem_avg;
	}
	public void setSharable_mem_avg(String sharable_mem_avg) {
		this.sharable_mem_avg = sharable_mem_avg;
	}
	public String getSnap_time() {
		return snap_time;
	}
	public void setSnap_time(String snap_time) {
		this.snap_time = snap_time;
	}
	public String getSql_id() {
		return sql_id;
	}
	public void setSql_id(String sql_id) {
		this.sql_id = sql_id;
	}
	
	public String getSnap() {
		return snap;
	}
	public void setSnap(String snap) {
		this.snap = snap;
	}
	public String getElapsed_time() {
		return elapsed_time;
	}
	public void setElapsed_time(String elapsed_time) {
		this.elapsed_time = elapsed_time;
	}
	
}
