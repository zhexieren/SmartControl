package com.smarpgrup.smartcontrol;

import com.covics.zxingscanner.OnDecodeCompletionListener;
import com.covics.zxingscanner.ScannerView;
import com.smarpgrup.source.Global;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

/**
 * Project Name:SmartControl
 * File Name:ScancodeActv.java
 * Package Name:com.smarpgrup.smartcontrol
 * Description：Scan Code To Add a Device
 * Date:2017年4月9日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class DevAddSacnCodeActv extends Activity implements OnDecodeCompletionListener{
	private ScannerView scannerView;
	private String resoultQR = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceadd);
		
		Log.v(Global.TAGSCANCODE, "Scan Code To Add Device ID");
		
		initUI();
	}

	private void initUI() {
		Log.v(Global.TAGSCANCODE, "initUI");
		scannerView = (ScannerView) findViewById(R.id.scanner_view);
		scannerView.setOnDecodeListener(this);
	}
	
	@Override
	public void onDecodeCompletion(String barcodeFormat, String barcode, Bitmap bitmap) {
		Log.v(Global.TAGSCANCODE, "onDecodeCompletion");
		
		if (barcode == null || "".equals(barcode)) {
			AlertDialog builder = new AlertDialog.Builder(DevAddSacnCodeActv.this).setTitle("Error")
					.setMessage("Not Found").show();
		} else {
			resoultQR = barcode.substring(barcode.indexOf("?") + 1, barcode.length());
			qrResoultShow(resoultQR);
		}
		//为什么不在这里弹出对话框，获取一个设备名，然后直接返回，就避免了新启动一个 activity 导致前面的 forresul 方法失效---后面的方法是在一旦 
		//activity finsh 后就生成？ 作为测试 V2 版本修改
	}

	private void qrResoultShow(final String stringQR) {
		Log.v(Global.TAGSCANCODE, "mShowDialog");
		
		AlertDialog alerDialog = new AlertDialog.Builder(DevAddSacnCodeActv.this).setTitle("The Result").setMessage(stringQR)
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.v(Global.TAGSCANCODE, "setNegativeButton");
						finish();
					}
				}).setPositiveButton("enter", new DialogInterface.OnClickListener() {
					@Override
					 public void onClick(DialogInterface dialog, int which) {
						Log.v(Global.TAGSCANCODE, "setPositiveButton");
						//二维码扫描成功，跳转至 DevSetNameActivity
						//传递扫描结果给 set name activity for new device
						Intent toActivity = new Intent(DevAddSacnCodeActv.this, DevSetNameActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString(Global.devName, stringQR);						
						toActivity.putExtras(bundle);
//						startActivity(toActivity);
						startActivityIfNeeded(toActivity, Global.scanCodeOK);
                    }
				}).show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		scannerView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		scannerView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
