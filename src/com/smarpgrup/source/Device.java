package com.smarpgrup.source;

import android.util.Log;

/**
 * Project Name:SmartControl
 * File Name:device.java
 * Package Name:com.smarpgrup.source
 * Description：智能控制的设备类，用于记录设备的信息
 * Date:2017年4月10日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class Device {
	private int id;
	private String QR;
	private String name;
	private String currentState;
	//可以添加一个集合，管理所有的设备
	
	String TAG = "Device Class";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQR() {
		return QR;
	}
	public void setQR(String qR) {
		QR = qR;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	
	public Device(String name, String qR, int id) {
		Log.v(TAG, "Device");
		
		this.id = id;
		this.QR = qR;
		this.name = name;
	}
	
	public Device(String name, String qR, int id, String state) {
		Log.v(TAG, "Device");
		this.currentState = state;
		this.id = id;
		this.QR = qR;
		this.name = name;
	}
	
	@Override
	public String toString() {
		String ret = null;
		ret = this.id + ":" + this.name + ":" + this.QR + ":" + this.currentState;
		Log.v(TAG, "toString:" + ret.toString());
		return ret;
	}
}
