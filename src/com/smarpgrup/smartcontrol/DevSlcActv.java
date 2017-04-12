package com.smarpgrup.smartcontrol;

import com.smarpgrup.source.Device;
import com.smarpgrup.source.Global;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Project Name:SmartControl File Name:SlctDevActv.java Package
 * Name:com.smarpgrup Description：Device Select Date:2017年4月9日 Copyright (c)
 * 2017, zhexieren@outlook.com All Rights Reserved. Version: 1.0
 */
 public class DevSlcActv extends Activity implements OnClickListener {
//public class DevSlcActv extends Activity implements OnClickListener {
	Button slctActvBtnAdd;
	Button slctActvBtnItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceselect);

		initUI();
	}

	private void initUI() {
		Log.v(Global.TAGSELEDEVCT, "initUI");

		// 更新版本需要添加 Item list 并初始化成每一个可点击的按钮，点击后跳转到空置的页面
		// 怎么在运行时才生成控制菜单？FIXME
		// 暂时用户一个按钮确定

		slctActvBtnItem = (Button) findViewById(R.id.selectActivity_btn_device);
		slctActvBtnAdd = (Button) findViewById(R.id.selectActivity_btn_registerdevice);

		slctActvBtnAdd.setOnClickListener(this);
		slctActvBtnItem.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Log.v(Global.TAGSELEDEVCT, "onClick");

		// 每一个添加过的智能硬件是一个Item，组成 Item list 如果点击了某一个 Item 就跳转至控制该设备的界面
		// 这里只有一个灯
		Intent toActivity = null;

		switch (v.getId()) {
		case R.id.selectActivity_btn_device:
			Log.v(Global.TAGSELEDEVCT, "select device success");
			// 控制相应的设备
			// 跳转至 ControlActivity
			toActivity = new Intent(DevSlcActv.this, DevCntrlActv.class);
			startActivity(toActivity);
			break;

		case R.id.selectActivity_btn_registerdevice:
			Log.v(Global.TAGSELEDEVCT, "select device add ");

			// 注册新设备，跳转至 DevAddActv
			// 设置新设备的名字和二维码
			toActivity = new Intent(DevSlcActv.this, DevSetNameActivity.class);
			/**
			 * 问题 1、多个 Activity 间跳转，传递数据时，数据标识码 requestcode 是对全局有效的吗？
			 * 现在看来不是的，这是单独的一对一的跳转 是针对该 Activity 的？
			 * 解决办法：直接跳转，不必保留本地的数据，在初始化时，向服务器申请当前用户的绑定设备 而获取数据就是，直接获取，所有其他
			 * activity传递过来的数据， 因为只有 有需要的 activity 才会用
			 * startActivityIfNeeded(intent, requestCode) 这样，相当于把 传递数据 由主动获取 变为
			 * 被动接收 经测试，上述解决方式不可行，因为
			 */
//			 startActivityForResult(toActivity, Global.scanCodeOK);
			startActivity(toActivity);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.v(Global.TAGSELEDEVCT, "onActivityResult");
		String newDeviceName = null;
		String newDeviceQR = null;

		switch (requestCode) {
		case Global.scanCodeOK:
			// 来自扫描二维码添加设备的业务处理
			newDeviceQR = data.getExtras().getString(Global.devQR);
			newDeviceName = data.getExtras().getString(Global.devName);
			Log.v(Global.TAGSELEDEVCT, "scanCodeOK");
			break;
		case Global.setNameOk:
			// 来自设置设备名字的业务处理
			break;
		default:
			break;
		}

		int newDeviceID = registerDevice(newDeviceName, newDeviceQR);
		if (newDeviceID < 0) {
			// 向服务器注册新设备失败
			Toast.makeText(DevSlcActv.this, "New Device Added", 1000).show();
			Log.v(Global.TAGSELEDEVCT, "request device id from server error");
		}

		Device newDevice = new Device(newDeviceName, newDeviceQR, newDeviceID);
		// 这里根据新设备的信息建造一个相应的 Item 并添加到 Item List 中 V2 版本
		Toast.makeText(DevSlcActv.this, "New Device Added", 1000).show();
		Log.v(Global.TAGSELEDEVCT, "New Device Added" + ":" + newDevice.toString());
	}

	private int registerDevice(String newDeviceNmae, String newDeviceQR) {
		int id = -1;
		id = 1234567;
		return id;
	}
}
