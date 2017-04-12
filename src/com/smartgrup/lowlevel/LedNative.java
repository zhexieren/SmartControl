package com.smartgrup.lowlevel;

import android.util.Log;

/**
 * Project Name:SmartControl
 * File Name:LedNative.java
 * Package Name:com.smartgrup.lowlevel
 * Description��Android JNI Control LED 
 * Date:2017��4��9��
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class LedNative {
	static{
		Log.i ( "LedNative" , "****************before load led so" );
		System.loadLibrary("led_jni"); //system/lib/libled_jni.so
		Log.i ( "LedNative" , "****************after load led so" );
	}
	
	public native int openDev();
	public native int closeDev();
	public native int devOn();
	public native int devOff();
}