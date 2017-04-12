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
 * Name:com.smarpgrup.smartcontrol Description��User Login Date:2017��4��9��
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
				// ���ص����û���½��Ϣ ��ʽ��
				// success/error + why + �û��� + ���� + �˺� + �û���½
				//����������msg �� Json �ַ���
				String string = (String) msg.obj;
				Msg loginMsg = null; 
				try {
					loginMsg = Msg.getMsg(string);
				} catch (JSONException e) {
					;
					e.printStackTrace();
				}
				/*
				 * ������
				 */
				if (msg.what == Global.loginMsgID) {
					String ret = loginMsg.getReason();
					//���ԣ�������ָ� FIXME
					ret = "success";
					if (ret.equals("success")) {
						Log.v(Global.TAGLOGIN, "Login sucess");

						// ���õ�ǰ�û���Ϣ--����Ż����õ�ǰ�ĵ�¼��Ϣ
						Account currentUser = Account.getUserAccount();
						currentUser.setPasswd(accountPasswd);
						currentUser.setNumber(accountNumber);
						currentUser.setOnLine(true);
						
						Toast.makeText(lgnActv.this, "login" + ret, 1000).show();
						// ��ת�� selectActivity
						startActivity(new Intent(lgnActv.this, DevSlcActv.class));
						
					} else {
						Toast.makeText(lgnActv.this, ret, 1000).show();
						// ��½ʧ�ܣ�����ת�����ڱ�ҳ�棬ret �Ƿ�������������ʧ����Ϣ
						Log.v(Global.TAGLOGIN, "Login error");
					}
				}else if(msg.what == Global.otherMsgID){
					Log.v(Global.TAGLOGIN, "From Server:" + loginMsg.getReason());
					Toast.makeText(lgnActv.this, "Connect Success", 1000).show();
				}
			}
		};
		
		/**
		 * �����������Ϳͻ��˵�����
		 */
		String conect = "connect";
		Toast.makeText(lgnActv.this, "Connect To Server", 1000).show();
	//	new SocketSendThread(Global.otherMsgID, this.handler, conect).start();
		//ȫ־pad����������
	}

	@Override
	public void onClick(View v) {
		Log.v(Global.TAGLOGIN, "onClick");

		switch (v.getId()) {
		case R.id.loginActivity_btn_login:
			// ��֤ �û���������
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
			// ��ת�� RegisterActivity
			Log.v(Global.TAGLOGIN, "register account");
			
			startActivity(new Intent(lgnActv.this, RgstAcntActv.class));
			break;
		}
	}

	private void isAccount(String accountName, String accountPasswd) throws JSONException {

		Log.v(Global.TAGLOGIN, "isAccount");
		Log.v(Global.TAGLOGIN, "Connet to Server");

		/**
		 * ����1 �� ���� login Activity �� �û��� + ���� + �˺� + �û���½ ,
		 * �������Է������ĵ�¼״̬��success/error + why + �û��� + ���� + �˺� + �û���½
		 */
		Msg msg = new Msg();
		msg.setType(Global.jsTypeAccountLogin);
		msg.setAccountName(accountName);
		msg.setAccountPasswd(accountPasswd);
		String sendMsg = msg.toString();
		Log.v(Global.TAGLOGIN, "Send Massage to Server ��" + sendMsg);
		
		//�����ǽ���Ҫ���͵��ַ������ݸ� socket �̣߳����͸�������
		new SocketSendThread(Global.loginMsgID, this.handler, sendMsg);
	}

	private void haveAccountFromeUI() {
		this.accountNumber = editTextAccountNumber.getText().toString();
		this.accountPasswd = editTextAccountPasswd.getText().toString();
		Log.v(Global.TAGLOGIN, "haveAccountFromeUI ��" + this.accountNumber + ":" + this.accountPasswd);

	}
}
