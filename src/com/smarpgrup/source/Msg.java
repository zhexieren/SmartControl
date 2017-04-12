package com.smarpgrup.source;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Project Name:SmartControl File Name:MsgP.java Package
 * Name:com.smarpgrup.source Description：Json Message Protocol Date:2017年4月11日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved. Version: 1.0
 */
public class Msg {
	/**
	 * 功能1 ： 发送 login Activity 中 用户名 + 密码 + 账号 + 用户登陆 ,
	 * 接收来自服务器的登录状态，success/error + why + 用户名 + 密码 + 账号 + 用户登陆
	 * 
	 * 功能2 ： 发送 register activity 中的 用户名 + 密码 + 用户注册
	 * 并接收注册结果 success/error + why + 用户名 + 密码 + 账号 + 用户注册 的数据，判断是否成功，并更新当前的用户
	 * 
	 * 功能3 ： 发送 select device 中 用户名 + 密码 + 账号 + 获取设备列表 并接收 success/error + why +
	 * device list + 用户名 + 密码 + 账号 + 获取设备列表 注意 list 中有 相应设备的当前状态
	 * 
	 * 功能4 ： 发送 control device 中 用户名 + 密码 + 账号 + 设备唯一标示符 + 期望状态 + 设备操作 并接收
	 * success/error + why + 用户名 + 密码 + 账号 + 设备唯一标示符 + 期望状态 + 设备操作
	 * 
	 * 功能7 : 发送 device add 中 用户 + 密码 + 注册设备名 + 注册设备二维码 + 设备注册 并接收设备注册结果
	 * success/error + why + 用户 + 密码 + 注册设备名 + 注册设备二维码 + 设备唯一标示符 + 设备注册
	 */
	private int type; // 在 Global 中定义  -- msgID
	private String accountName;
	private String accountPasswd;
	private String accountNumber;
	private boolean accountState;
	private String reason;
	private String deviceName;
	private String deviceID;
	private String deviceQR;
	private String devIntentState;
	private String devCurState;
	private String otherMsg;
	// 这里涉及到一个 设备列表应该怎么设置，做一个list 用集合
	// List<Device> dev_list;

	public Msg() {
	}

	public Msg(int type, String accountName, String accountPasswd, String accountNumber, boolean accountState,
			String reason, String deviceName, String deviceID, String deviceQR, String devCutState, String otherMsg) {
		this.type = type;
		this.accountName = accountName;
		this.accountPasswd = accountPasswd;
		this.accountNumber = accountNumber;
		this.accountState = accountState;
		this.reason = reason;
		this.deviceName = deviceName;
		this.deviceID = deviceID;
		this.deviceQR = deviceQR;
		this.devCurState = devCutState;
		this.otherMsg = otherMsg;
	}


	public String getOtherMsg() {
		return otherMsg;
	}

	public void setOtherMsg(String otherMsg) {
		this.otherMsg = otherMsg;
	}

	public int getType() {
		//这里需要先获取当前消息的 ID  以减少直接拆包的复杂性，主要是为了优化  V2
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPasswd() {
		return accountPasswd;
	}

	public void setAccountPasswd(String accountPasswd) {
		this.accountPasswd = accountPasswd;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isAccountState() {
		return accountState;
	}

	public void setAccountState(boolean accountState) {
		this.accountState = accountState;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceQR() {
		return deviceQR;
	}

	public void setDeviceQR(String deviceQR) {
		this.deviceQR = deviceQR;
	}

	public String getDevCurState() {
		return devCurState;
	}

	public void setDevCurState(String devCurState) {
		this.devCurState = devCurState;
	}

	public void setDevIntentState(String devIntentState) {
		this.devIntentState = devIntentState;
	}

	/**
	* @Title: getMsg
	* @Description: 将一个 String 转化为 Msg 类型的对象
	* @param @param string
	* @param @return Msg
	* @param @throws JSONException
	* @return Msg
	* @throws 有可能因为不是本协议的字符串，就转换不了
	 */
	public static Msg getMsg(String string) throws JSONException {
		Log.v(Global.TAGMSG, "getMsg" + string);
		
		JSONObject obj = new JSONObject(string);
		int type = obj.getInt(Global.msgJSType);
		String accountName = obj.getString(Global.accountJSName);
		String accountPasswd = obj.getString(Global.accountJSPasswd);
		String accountNumber = obj.getString(Global.accountJSNumber);
		boolean accountState = obj.getBoolean(Global.accountJSState);
		String reason = obj.getString(Global.msgJSResult);
		String deviceName = obj.getString(Global.deviceJSName);
		String deviceID = obj.getString(Global.deviceJSID);
		String deviceQR = obj.getString(Global.deviceJSQR);
		String devCutState = obj.getString(Global.deviceJSCurrentState);
		String other = obj.getString(Global.otherJSMsg);

		Msg result = new Msg(type, accountName, accountPasswd, accountNumber, accountState, reason, deviceName,
				deviceID, deviceQR, devCutState, other);
		return result;
	}
	
	@Override
	public String toString() {
		String string = null;
		JSONObject obj = new JSONObject();
		try {
			obj.put(Global.msgJSType, this.type);
			obj.put(Global.accountJSNumber, this.accountNumber);
			obj.put(Global.accountJSName, this.accountName);
			obj.put(Global.accountJSPasswd, this.accountPasswd);
			obj.put(Global.accountJSState, this.accountState);
			obj.put(Global.msgJSResult, this.reason);
			obj.put(Global.deviceJSName, this.deviceName);
			obj.put(Global.deviceJSID, this.deviceID);
			obj.put(Global.deviceJSQR, this.deviceQR);
			obj.put(Global.deviceJSIntentState, this.devIntentState);
			obj.put(Global.otherJSMsg, this.otherMsg);
			string = obj.toString();
			Log.v(Global.TAGMSG, "toString" + string);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return string;
	}
}
