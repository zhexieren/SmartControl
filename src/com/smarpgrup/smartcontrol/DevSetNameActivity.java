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
 * File Name:NameForDevActv.java
 * Package Name:com.smarpgrup.smartcontrol
 * Description：Register Device To Server
 * Date:2017年4月9日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class DevSetNameActivity extends Activity implements OnClickListener{
	Button enter;
	EditText devName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_deviceregister);
		
		Log.v(Global.TAGNAMEDEV, "onCreate");
		
		initUI();
	}
	private void initUI() {
		Log.v(Global.TAGNAMEDEV, "initUI");
		
		enter = (Button) findViewById(R.id.device_register_activity_btn_enter);
		enter.setOnClickListener(this);	
		
		devName = (EditText) findViewById(R.id.namefordeviceActivity_edittext_devicename);
	}
	@Override
	public void onClick(View v) {
		Log.v(Global.TAGNAMEDEV, "onClick");
		
		Intent toActivity = null;
		
		switch (v.getId()) {
		case R.id.device_register_activity_btn_enter :
			//带参数跳转至 select device activity
						
			Toast.makeText(DevSetNameActivity.this, "device_register_activity_btn_enter", 1000).show();
			Bundle bundle = new Bundle();
			String deviceQR = "跳过扫描二维码";
			String devicename = devName.getText().toString();
//			String deviceQR = bundle.getString(Global.devQR);
//			String devicename = devName.getText().toString();
			
//			newDeviceQR	 = data.getExtras().getString(Global.devQR);
//			newDeviceName = data.getExtras().getString(Global.devName);
			bundle.putString(Global.devName, devicename);
			bundle.putString(Global.devQR, deviceQR);//这一句可能不需要，之后测试一下
			
			Log.v(Global.TAGNAMEDEV, deviceQR);
			Log.v(Global.TAGNAMEDEV, devicename);
			
			toActivity = new Intent(DevSetNameActivity.this, DevSlcActv.class);
			toActivity.putExtras(bundle);
			
//			startActivity(toActivity);
			startActivityIfNeeded(toActivity, Global.scanCodeOK);
			break;

		default:
			break;
		}
	}

}
