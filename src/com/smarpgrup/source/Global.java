package com.smarpgrup.source;

import java.net.Socket;
/**
 * Project Name:SmartControl
 * File Name:Global.java
 * Package Name:Source
 * Description：Global define for this APP
 * Date:2017年4月9日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */

public class Global {
	
	public static Socket socket 	= null;
	public static String socketIP 	= "192.168.7.72";
	public static int socketPort 	= 10000;
	public static int bufferSize;
	
	public final static int idLoginActivity 		= 1;
	public final static int idControlActivity 		= 2;
	public final static int idRegisterActivity 		= 3;
	public final static int idScancodeActivity 		= 4;
	public final static int idSelectdeviceActivity 	= 5;
	public final static int idNamefordeviceActivity	= 6;
	public final static int idControlDeviceActivity	= 7;
	
	/**
	 * logcat TAG
	 */
	public final static String TAGLOGIN 	= "loginActivity";
	public final static String TAGCTR	 	= "controlActivity";
	public final static String TAGREGIS 	= "registerAccountActivity";
	public final static String TAGSCANCODE 	= "scancodeActivity";
	public final static String TAGSELEDEVCT	= "selectDevActivity";
	public final static String TAGNAMEDEV 	= "namefordevActivity";
	public final static String TAGCNTDEV	= "ControlDeviceActivity";
	public static final String TAGSKTSNDTHD = "TAGSocketSendThread";
	public static final String TAGMSG 		= "TAGMSG";
	
	
    public final static int devAddRequest 	= 1;
    public final static int scanCodeOK 		= 2;
    public final static int setNameOk		= 3;
    
    //不要改动，仅仅作为通信的关键字  Activity 通信的关键字
	public static String devName = "devName";
	public static String devQR   = "devQR";
	
	/**
	 * 基本的发送协议 ： string + msgID
	 * 		接收协议 ： string + msgID
	 * 功能1 ： 发送 login Activity 中 用户名  + 密码  +  账号 + 用户登陆 , 				
	 * 		接收来自服务器的登录状态，success/error + why + 用户名  + 密码  +  账号 + 用户登陆
	 * 
	 * 功能2 ： 发送 register activity 中的 用户名 +  密码 + 用户注册  					
	 * 		并接收注册结果 success/error + why + 用户名  + 密码  +  账号 + 用户注册  的数据，判断是否成功，并更新当前的用户
	 * 
	 * 功能3 ： 发送 select device 中  用户名  + 密码 + 账号 + 获取设备列表
	 * 		并接收 success/error + why + device list + 用户名  + 密码 + 账号 + 获取设备列表    注意 list 中有 相应设备的当前状态
	 * 
	 * 功能4 ： 发送 control device 中  用户名  + 密码 + 账号 + 设备唯一标示符 + 期望状态 + 设备操作
	 * 		并接收  success/error + why + 用户名  + 密码 + 账号 + 设备唯一标示符 + 期望状态 + 设备操作
	 * 
	 * 功能5：客户机和服务器通过 String + 用户名 + 密码 + 其他消息
	 * 		并接受 success/error + why + 用户名  + 账号 + 密码 + 其他消息
	 * 
	 * 功能7 : 发送 device add 中 用户 + 密码  + 注册设备名 + 注册设备二维码 + 设备注册
	 * 			并接收设备注册结果  success/error + why +  用户 + 密码  + 注册设备名 + 注册设备二维码 + 设备唯一标示符 + 设备注册
	 */
	/**
	 * msgID
	 */
	public final static int otherMsgID		= 100;
	public final static int loginMsgID 		= 1;
	public final static int regAcntMsgID 	= 2;
	public final static int devLstMsgID 	= 3;
	public final static int devOpMsgID 		= 4;
	public final static int regDevMsgID 	= 5;
	
	/**
	 * Json Protocol
	 */
	public final static String msgJSResult			= "msgJSResult";
	public final static String accountJSName		= "accountName";
	public final static String accountJSNumber		= "accountNumber";
	public final static String accountJSPasswd 		= "accountPasswd";
	public final static String accountJSState		= "accountJSState";
	public final static String deviceJSName			= "deviceName";
	public final static String deviceJSQR			= "deviceQR";
	public final static String deviceJSID			= "deviceID";
	public final static String deviceJSList			= "deviceList";
	public final static String deviceJSIntentState	= "deviceJSIntentState";
	public final static String deviceJSCurrentState	= "deviceJSCurrentState";
	public final static String otherJSMsg			= "otherJSMsg";
	
	public final static String msgJSType			= "msgJSType";
	public final static int jsTypeAccountLogin		= 1;
	public final static int jsTypeAccountRegister	= 2;
	public final static int jsDevicelistRequest		= 3;
	public final static int jsTypeeviceOperation	= 4;
	public final static int jsDeviceRegister		= 7;
	public final static int jsTypeOtherMsg			= 8;
}
