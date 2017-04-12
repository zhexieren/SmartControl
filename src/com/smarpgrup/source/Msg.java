package com.smarpgrup.source;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Project Name:SmartControl File Name:MsgP.java Package
 * Name:com.smarpgrup.source Description��Json Message Protocol Date:2017��4��11��
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved. Version: 1.0
 */
public class Msg {
	/**
	 * ����1 �� ���� login Activity �� �û��� + ���� + �˺� + �û���½ ,
	 * �������Է������ĵ�¼״̬��success/error + why + �û��� + ���� + �˺� + �û���½
	 * 
	 * ����2 �� ���� register activity �е� �û��� + ���� + �û�ע��
	 * ������ע���� success/error + why + �û��� + ���� + �˺� + �û�ע�� �����ݣ��ж��Ƿ�ɹ��������µ�ǰ���û�
	 * 
	 * ����3 �� ���� select device �� �û��� + ���� + �˺� + ��ȡ�豸�б� ������ success/error + why +
	 * device list + �û��� + ���� + �˺� + ��ȡ�豸�б� ע�� list ���� ��Ӧ�豸�ĵ�ǰ״̬
	 * 
	 * ����4 �� ���� control device �� �û��� + ���� + �˺� + �豸Ψһ��ʾ�� + ����״̬ + �豸���� ������
	 * success/error + why + �û��� + ���� + �˺� + �豸Ψһ��ʾ�� + ����״̬ + �豸����
	 * 
	 * ����7 : ���� device add �� �û� + ���� + ע���豸�� + ע���豸��ά�� + �豸ע�� �������豸ע����
	 * success/error + why + �û� + ���� + ע���豸�� + ע���豸��ά�� + �豸Ψһ��ʾ�� + �豸ע��
	 */
	private int type; // �� Global �ж���  -- msgID
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
	// �����漰��һ�� �豸�б�Ӧ����ô���ã���һ��list �ü���
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
		//������Ҫ�Ȼ�ȡ��ǰ��Ϣ�� ID  �Լ���ֱ�Ӳ���ĸ����ԣ���Ҫ��Ϊ���Ż�  V2
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
	* @Description: ��һ�� String ת��Ϊ Msg ���͵Ķ���
	* @param @param string
	* @param @return Msg
	* @param @throws JSONException
	* @return Msg
	* @throws �п�����Ϊ���Ǳ�Э����ַ�������ת������
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
