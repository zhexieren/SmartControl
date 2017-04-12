package com.smarpgrup.source;

import java.net.Socket;
/**
 * Project Name:SmartControl
 * File Name:Global.java
 * Package Name:Source
 * Description��Global define for this APP
 * Date:2017��4��9��
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
    
    //��Ҫ�Ķ���������Ϊͨ�ŵĹؼ���  Activity ͨ�ŵĹؼ���
	public static String devName = "devName";
	public static String devQR   = "devQR";
	
	/**
	 * �����ķ���Э�� �� string + msgID
	 * 		����Э�� �� string + msgID
	 * ����1 �� ���� login Activity �� �û���  + ����  +  �˺� + �û���½ , 				
	 * 		�������Է������ĵ�¼״̬��success/error + why + �û���  + ����  +  �˺� + �û���½
	 * 
	 * ����2 �� ���� register activity �е� �û��� +  ���� + �û�ע��  					
	 * 		������ע���� success/error + why + �û���  + ����  +  �˺� + �û�ע��  �����ݣ��ж��Ƿ�ɹ��������µ�ǰ���û�
	 * 
	 * ����3 �� ���� select device ��  �û���  + ���� + �˺� + ��ȡ�豸�б�
	 * 		������ success/error + why + device list + �û���  + ���� + �˺� + ��ȡ�豸�б�    ע�� list ���� ��Ӧ�豸�ĵ�ǰ״̬
	 * 
	 * ����4 �� ���� control device ��  �û���  + ���� + �˺� + �豸Ψһ��ʾ�� + ����״̬ + �豸����
	 * 		������  success/error + why + �û���  + ���� + �˺� + �豸Ψһ��ʾ�� + ����״̬ + �豸����
	 * 
	 * ����5���ͻ����ͷ�����ͨ�� String + �û��� + ���� + ������Ϣ
	 * 		������ success/error + why + �û���  + �˺� + ���� + ������Ϣ
	 * 
	 * ����7 : ���� device add �� �û� + ����  + ע���豸�� + ע���豸��ά�� + �豸ע��
	 * 			�������豸ע����  success/error + why +  �û� + ����  + ע���豸�� + ע���豸��ά�� + �豸Ψһ��ʾ�� + �豸ע��
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
