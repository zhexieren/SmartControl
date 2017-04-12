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
 * Name:com.smarpgrup Description��Device Select Date:2017��4��9�� Copyright (c)
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

		// ���°汾��Ҫ��� Item list ����ʼ����ÿһ���ɵ���İ�ť���������ת�����õ�ҳ��
		// ��ô������ʱ�����ɿ��Ʋ˵���FIXME
		// ��ʱ�û�һ����ťȷ��

		slctActvBtnItem = (Button) findViewById(R.id.selectActivity_btn_device);
		slctActvBtnAdd = (Button) findViewById(R.id.selectActivity_btn_registerdevice);

		slctActvBtnAdd.setOnClickListener(this);
		slctActvBtnItem.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Log.v(Global.TAGSELEDEVCT, "onClick");

		// ÿһ����ӹ�������Ӳ����һ��Item����� Item list ��������ĳһ�� Item ����ת�����Ƹ��豸�Ľ���
		// ����ֻ��һ����
		Intent toActivity = null;

		switch (v.getId()) {
		case R.id.selectActivity_btn_device:
			Log.v(Global.TAGSELEDEVCT, "select device success");
			// ������Ӧ���豸
			// ��ת�� ControlActivity
			toActivity = new Intent(DevSlcActv.this, DevCntrlActv.class);
			startActivity(toActivity);
			break;

		case R.id.selectActivity_btn_registerdevice:
			Log.v(Global.TAGSELEDEVCT, "select device add ");

			// ע�����豸����ת�� DevAddActv
			// �������豸�����ֺͶ�ά��
			toActivity = new Intent(DevSlcActv.this, DevSetNameActivity.class);
			/**
			 * ���� 1����� Activity ����ת����������ʱ�����ݱ�ʶ�� requestcode �Ƕ�ȫ����Ч����
			 * ���ڿ������ǵģ����ǵ�����һ��һ����ת ����Ը� Activity �ģ�
			 * ����취��ֱ����ת�����ر������ص����ݣ��ڳ�ʼ��ʱ������������뵱ǰ�û��İ��豸 ����ȡ���ݾ��ǣ�ֱ�ӻ�ȡ����������
			 * activity���ݹ��������ݣ� ��Ϊֻ�� ����Ҫ�� activity �Ż���
			 * startActivityIfNeeded(intent, requestCode) �������൱�ڰ� �������� ��������ȡ ��Ϊ
			 * �������� �����ԣ����������ʽ�����У���Ϊ
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
			// ����ɨ���ά������豸��ҵ����
			newDeviceQR = data.getExtras().getString(Global.devQR);
			newDeviceName = data.getExtras().getString(Global.devName);
			Log.v(Global.TAGSELEDEVCT, "scanCodeOK");
			break;
		case Global.setNameOk:
			// ���������豸���ֵ�ҵ����
			break;
		default:
			break;
		}

		int newDeviceID = registerDevice(newDeviceName, newDeviceQR);
		if (newDeviceID < 0) {
			// �������ע�����豸ʧ��
			Toast.makeText(DevSlcActv.this, "New Device Added", 1000).show();
			Log.v(Global.TAGSELEDEVCT, "request device id from server error");
		}

		Device newDevice = new Device(newDeviceName, newDeviceQR, newDeviceID);
		// ����������豸����Ϣ����һ����Ӧ�� Item ����ӵ� Item List �� V2 �汾
		Toast.makeText(DevSlcActv.this, "New Device Added", 1000).show();
		Log.v(Global.TAGSELEDEVCT, "New Device Added" + ":" + newDevice.toString());
	}

	private int registerDevice(String newDeviceNmae, String newDeviceQR) {
		int id = -1;
		id = 1234567;
		return id;
	}
}
