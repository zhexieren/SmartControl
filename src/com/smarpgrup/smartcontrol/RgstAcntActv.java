package com.smarpgrup.smartcontrol;

import com.smarpgrup.source.Global;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Project Name:SmartControl
 * File Name:RgstAcntActv.java
 * Package Name:com.smarpgrup.smartcontrol
 * Description��User Account Register
 * Date:2017��4��9��
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class RgstAcntActv extends Activity implements OnClickListener {
	
	private String accountName = null;
	private String accountPasswd = null;
	
	// About UI
	Button registerActivityBtnRegister;
	EditText editTextAccountName;
	EditText editText1stPasswd;
	EditText editText2ndPasswd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeraccount);
		
		Log.v(Global.TAGREGIS, "onCreat");
		
		initUI();
	}
	
	private void initUI() {
		Log.v(Global.TAGREGIS, "initUI");
		
		registerActivityBtnRegister = (Button) findViewById(R.id.registerActivity_btn_register);
		registerActivityBtnRegister.setOnClickListener(this);
		
		editTextAccountName = (EditText) findViewById(R.id.registerActivity_editText_username);
		editText1stPasswd 	= (EditText) findViewById(R.id.registerActivity_editText_1stpasswd);
		editText2ndPasswd	= (EditText) findViewById(R.id.registerActivity_editText_2ndpasswd);
	}
	
	@Override
	public void onClick(View v) {
		Log.v(Global.TAGREGIS, "onClick");
		
		String ret = null;
		Intent toActivity = null;
		
		switch (v.getId()) {
		case R.id.registerActivity_btn_register:
			Log.v(Global.TAGREGIS, "Click register button ");
			ret = registerAccount();
			
			if(ret.equals("success")){
				Log.v(Global.TAGREGIS, "Register Account Success");
				toActivity = new Intent(RgstAcntActv.this, DevSlcActv.class);
			}else{
				Log.v(Global.TAGREGIS, "Register Account error");
			}
			break;
			
		default:
			break;
		}
		startActivity(toActivity);
	}

	private String registerAccount() {
		String ret = inputValid();
		//���� �����Ƿ���ȷ
		if(ret.equals("error")){
			Log.v(Global.TAGREGIS, "input invalue");
			Toast.makeText(RgstAcntActv.this, "Register error", 1000).show();
		}else {
			//�������ע���û���������
			Log.v(Global.TAGREGIS, "register to service");
			
			ret = registerAccountFromeServer(this.accountName, this.accountPasswd);
			
//			Account currentUser = Account.getUserAccount();
//			currentUser.setName(this.accountName);
//			currentUser.setPasswd(this.accountPasswd);
//			//���õ�ǰ�û����˺ţ��Ƿ��������ͻ������˺�  ����1 �Ŀͻ���
//			//currentUser.setNumber();
			
			if(ret.equals("success")){
				Toast.makeText(RgstAcntActv.this, "Register success", 1000).show();
				//����ע��ɹ����û������� account
			}else{
				Toast.makeText(RgstAcntActv.this, ret.toString(), 1000).show();
				//��ʾ �û������������д���
			}
		}
		return ret;
	}

	private String registerAccountFromeServer(String accountName, String accountPasswd) {
		Log.v(Global.TAGREGIS, "registerAccountFromeServer");
		String ret = null;
		ret = "success";
		return ret;
	}

	private String inputValid() {
		Log.v(Global.TAGREGIS, "inputValid");
		
		//��֤�����Ƿ���ͬ�����°��Ҫ��֤���ŵ���ȷ��
		String ret = null;
		this.accountPasswd = editText2ndPasswd.getText().toString();
		if(this.accountPasswd.equals(editText1stPasswd.getText().toString())){
			//�ɹ�
			this.accountName = editTextAccountName.getText().toString();
			Log.v(Global.TAGREGIS, this.accountName.toString() + "," + this.accountPasswd.toString());
			//���õ�ǰ�û�
			ret = "success";
		}else{
			Toast.makeText(RgstAcntActv.this, "Password Error�� Input Again", 1000).show();
			ret = "error";
		}
		
		return ret;
	}
}
