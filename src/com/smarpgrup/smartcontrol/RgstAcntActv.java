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
 * Description：User Account Register
 * Date:2017年4月9日
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
		//测试 输入是否正确
		if(ret.equals("error")){
			Log.v(Global.TAGREGIS, "input invalue");
			Toast.makeText(RgstAcntActv.this, "Register error", 1000).show();
		}else {
			//向服务器注册用户名和密码
			Log.v(Global.TAGREGIS, "register to service");
			
			ret = registerAccountFromeServer(this.accountName, this.accountPasswd);
			
//			Account currentUser = Account.getUserAccount();
//			currentUser.setName(this.accountName);
//			currentUser.setPasswd(this.accountPasswd);
//			//设置当前用户的账号，是服务器传送回来的账号  功能1 的客户端
//			//currentUser.setNumber();
			
			if(ret.equals("success")){
				Toast.makeText(RgstAcntActv.this, "Register success", 1000).show();
				//这里注册成功，用户名就是 account
			}else{
				Toast.makeText(RgstAcntActv.this, ret.toString(), 1000).show();
				//提示 用户名或者密码有错误
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
		
		//验证密码是否相同，更新版的要验证符号的正确性
		String ret = null;
		this.accountPasswd = editText2ndPasswd.getText().toString();
		if(this.accountPasswd.equals(editText1stPasswd.getText().toString())){
			//成功
			this.accountName = editTextAccountName.getText().toString();
			Log.v(Global.TAGREGIS, this.accountName.toString() + "," + this.accountPasswd.toString());
			//设置当前用户
			ret = "success";
		}else{
			Toast.makeText(RgstAcntActv.this, "Password Error， Input Again", 1000).show();
			ret = "error";
		}
		
		return ret;
	}
}
