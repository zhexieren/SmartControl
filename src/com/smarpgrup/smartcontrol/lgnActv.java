package com.smarpgrup.smartcontrol;

import org.json.JSONException;
import com.smarpgrup.source.Account;
import com.smarpgrup.source.Global;
import com.smarpgrup.source.Msg;
import com.smarpgrup.source.SocketSendThread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Project Name:SmartControl File Name:LoginActivity.java Package
 * Name:com.smarpgrup.smartcontrol Description：User Login Date:2017年4月9日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved. Version: 1.0
 */
public class lgnActv extends Activity implements OnClickListener {
	private Handler handler;

	private String accountNumber;
	private String accountPasswd;

	// about UI
	Button loginActivity_btn_login;
	Button loginActivity_btn_register;
	EditText editTextAccountNumber;
	EditText editTextAccountPasswd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initUI();
	}

	private void initUI() {
		Log.v(Global.TAGLOGIN, "initUI");

		loginActivity_btn_login = (Button) findViewById(R.id.loginActivity_btn_login);
		loginActivity_btn_register = (Button) findViewById(R.id.loginActivity_btn_register);
		loginActivity_btn_login.setOnClickListener(this);
		loginActivity_btn_register.setOnClickListener(this);

		editTextAccountNumber = (EditText) findViewById(R.id.loginactivity_editText_account);
		editTextAccountPasswd = (EditText) findViewById(R.id.loginactivity_editText_password);
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 返回的是用户登陆信息 格式：
				// success/error + why + 用户名 + 密码 + 账号 + 用户登陆
				//做包解析，msg 是 Json 字符串
				String string = (String) msg.obj;
				Msg loginMsg = null; 
				try {
					loginMsg = Msg.getMsg(string);
				} catch (JSONException e) {
					;
					e.printStackTrace();
				}
				/*
				 * 包解析
				 */
				if (msg.what == Global.loginMsgID) {
					String ret = loginMsg.getReason();
					//调试，结束后恢复 FIXME
					ret = "success";
					if (ret.equals("success")) {
						Log.v(Global.TAGLOGIN, "Login sucess");

						// 设置当前用户信息--这里才会设置当前的登录信息
						Account currentUser = Account.getUserAccount();
						currentUser.setPasswd(accountPasswd);
						currentUser.setNumber(accountNumber);
						currentUser.setOnLine(true);
						
						Toast.makeText(lgnActv.this, "login" + ret, 1000).show();
						// 跳转至 selectActivity
						startActivity(new Intent(lgnActv.this, DevSlcActv.class));
						
					} else {
						Toast.makeText(lgnActv.this, ret, 1000).show();
						// 登陆失败，不跳转，留在本页面，ret 是服务器传过来的失败信息
						Log.v(Global.TAGLOGIN, "Login error");
					}
				}else if(msg.what == Global.otherMsgID){
					Log.v(Global.TAGLOGIN, "From Server:" + loginMsg.getReason());
					Toast.makeText(lgnActv.this, "Connect Success", 1000).show();
				}
			}
		};
		
		/**
		 * 建立服务器和客户端的链接
		 */
		String conect = "connect";
		Toast.makeText(lgnActv.this, "Connect To Server", 1000).show();
	//	new SocketSendThread(Global.otherMsgID, this.handler, conect).start();
		//全志pad无线有问题
	}

	@Override
	public void onClick(View v) {
		Log.v(Global.TAGLOGIN, "onClick");

		switch (v.getId()) {
		case R.id.loginActivity_btn_login:
			// 验证 用户名和密码
			Log.v(Global.TAGLOGIN, "login");
			haveAccountFromeUI();
			try {
				isAccount(this.accountNumber, this.accountPasswd);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case R.id.loginActivity_btn_register:
			Toast.makeText(lgnActv.this, "register", 1000).show();
			// 跳转至 RegisterActivity
			Log.v(Global.TAGLOGIN, "register account");
			
			startActivity(new Intent(lgnActv.this, RgstAcntActv.class));
			break;
		}
	}

	private void isAccount(String accountName, String accountPasswd) throws JSONException {

		Log.v(Global.TAGLOGIN, "isAccount");
		Log.v(Global.TAGLOGIN, "Connet to Server");

		/**
		 * 功能1 ： 发送 login Activity 中 用户名 + 密码 + 账号 + 用户登陆 ,
		 * 接收来自服务器的登录状态，success/error + why + 用户名 + 密码 + 账号 + 用户登陆
		 */
		Msg msg = new Msg();
		msg.setType(Global.jsTypeAccountLogin);
		msg.setAccountName(accountName);
		msg.setAccountPasswd(accountPasswd);
		String sendMsg = msg.toString();
		Log.v(Global.TAGLOGIN, "Send Massage to Server ：" + sendMsg);
		
		//这里是将需要发送的字符串传递给 socket 线程，发送给服务器
		new SocketSendThread(Global.loginMsgID, this.handler, sendMsg);
	}

	private void haveAccountFromeUI() {
		this.accountNumber = editTextAccountNumber.getText().toString();
		this.accountPasswd = editTextAccountPasswd.getText().toString();
		Log.v(Global.TAGLOGIN, "haveAccountFromeUI ：" + this.accountNumber + ":" + this.accountPasswd);

	}
}
