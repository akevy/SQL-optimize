package com.dboracle.vo;

public class IntegrateBufferVo {
	private String snap_time;
	private int instance_number;
	private float value;
	private float bch;
	public float getBch() {
		return bch;
	}
	public void setBch(float bch) {
		this.bch = bch;
	}
	public String getSnap_time() {
		return snap_time;
	}
	public void setSnap_time(String snap_time) {
		this.snap_time = snap_time;
	}
	public int getInstance_number() {
		return instance_number;
	}
	public void setInstance_number(int instance_number) {
		this.instance_number = instance_number;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}
