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
 * Name:com.smarpgrup.smartcontrol Description：Device Control Date:2017年4月9日
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
				 * 包解析
				 */
				switch (msg.what) {
				case Global.devOpMsgID:
					/**
					 * 更新当前设备状态
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
		// 向服务器发送 功能3 的数据指令
		Msg msg = new Msg();
		msg.setType(Global.devOpMsgID);
		// 后期为了安全的考虑，建议对设备的有些操作应该加上权限
		msg.setDevIntentState(cmd);

		String deviceControlCMD = msg.toString();
		Log.v(Global.TAGCNTDEV, deviceControlCMD);
		/**
		 * 其实可以将 msg 对应处理的 handler 放入 msg 类中，作为属性来处理 在这里初始化 msg
		 * 对象时再初始化给具体的对象，这样就能使用全局的一个 msg对象，一个 protocol 这个作为 扩展 在 V2
		 */
		// TMD WIFI 坏了 先测试本地硬件
//		new SocketSendThread(msg.getType(), handler, deviceControlCMD);
	}

}
