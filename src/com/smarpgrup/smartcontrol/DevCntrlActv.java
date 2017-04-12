package com.smarpgrup.smartcontrol;

import org.json.JSONException;

import com.smarpgrup.source.Global;
import com.smarpgrup.source.Msg;
import com.smartgrup.lowlevel.LedNative;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Project Name:SmartControl File Name:CtrlDevActv.java Package
 * Name:com.smarpgrup.smartcontrol Description��Device Control Date:2017��4��9��
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved. Version: 1.0
 */
public class DevCntrlActv extends Activity implements OnClickListener {
	Button btn_on;
	Button btn_off;

	LedNative LED;
	private String devOn = "led_on";
	private String devOff = "led_off";
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devcntrl);

		initUI();
	}

	private void initUI() {
		btn_on = (Button) findViewById(R.id.controlActivity_btn_openled);
		btn_off = (Button) findViewById(R.id.controlActivity_btn_closeled);
		btn_on.setOnClickListener(this);
		btn_off.setOnClickListener(this);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				String string = (String) msg.obj;
				Msg devState = null;
				try {
					devState = Msg.getMsg(string);
				} catch (JSONException e) {

					e.printStackTrace();
				}
				/**
				 * ������
				 */
				switch (msg.what) {
				case Global.devOpMsgID:
					/**
					 * ���µ�ǰ�豸״̬
					 */
					if (devState.getDevCurState().equals(devOn)) {
						Toast.makeText(DevCntrlActv.this, devState.getDevCurState(), 1000).show();
					}else if (devState.getDevCurState().equals(devOn)) {
						Toast.makeText(DevCntrlActv.this, devState.getDevCurState(), 1000).show();
					}else{
						Toast.makeText(DevCntrlActv.this, devState.getReason(), 1000).show();
					}

					break;

				default:
					break;
				}
			}
		};
	}

	@Override
	public void onClick(View v) {
		Log.v(Global.TAGCNTDEV, "onClick");

		String cmd = null;

		switch (v.getId()) {
		case R.id.controlActivity_btn_openled:
			LED.devOn();
//			cmd = this.devOn;
			break;

		case R.id.controlActivity_btn_closeled:
			LED.devOff();
//			cmd = this.devOff;

			break;
		}

		Log.v(Global.TAGCNTDEV, cmd);
		Toast.makeText(DevCntrlActv.this, cmd, 1000).show();
		// ����������� ����3 ������ָ��
		Msg msg = new Msg();
		msg.setType(Global.devOpMsgID);
		// ����Ϊ�˰�ȫ�Ŀ��ǣ�������豸����Щ����Ӧ�ü���Ȩ��
		msg.setDevIntentState(cmd);

		String deviceControlCMD = msg.toString();
		Log.v(Global.TAGCNTDEV, deviceControlCMD);
		/**
		 * ��ʵ���Խ� msg ��Ӧ����� handler ���� msg ���У���Ϊ���������� �������ʼ�� msg
		 * ����ʱ�ٳ�ʼ��������Ķ�����������ʹ��ȫ�ֵ�һ�� msg����һ�� protocol �����Ϊ ��չ �� V2
		 */
		// TMD WIFI ���� �Ȳ��Ա���Ӳ��
//		new SocketSendThread(msg.getType(), handler, deviceControlCMD);
	}

}
