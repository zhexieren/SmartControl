package com.smarpgrup.source;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Project Name:SmartControl File Name:Socket.java Package
 * Name:com.smarpgrup.source Description��Smart Control APP Connect With TCP/IP
 * Protocol Date:2017��4��10�� Copyright (c) 2017, zhexieren@outlook.com All Rights
 * Reserved. Version: 1.0
 */
public class SocketSendThread extends Thread {
	private int msgID; // ��ʾ��Ϣ�����ͣ������� ���ܺ�---��߸���activity ʱ���� msgid ����
	private String sendBuffer;
	private Handler handler;

	public SocketSendThread(int msgID, Handler h, String buffer) {
		Log.v(Global.TAGSKTSNDTHD, "SocketSendThread");
		this.msgID = msgID;
		this.handler = h;
		this.sendBuffer = buffer;
	}

	@Override
	public void run() {
		super.run();
		Log.v(Global.TAGSKTSNDTHD, "SocketSendThread");
		try {
			if (Global.socket == null) {
				Global.socket = new Socket(Global.socketIP, Global.socketPort);
				Log.v(Global.TAGSKTSNDTHD, "First connect");
			}

			OutputStream os = Global.socket.getOutputStream();
			new ReceiverThread().start();
			
			/*
			 * ����ֱ�ӷ��͵�ǰ�� sendbuffer
			 */
			os.write(sendBuffer.getBytes());
			Log.v(Global.TAGSKTSNDTHD, sendBuffer);
		} catch (UnknownHostException e) {
			Log.e(Global.TAGSKTSNDTHD, "UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ReceiverThread extends Thread {
		@Override
		public void run() {
			Log.e(Global.TAGSKTSNDTHD, "ReceiverThread");
			super.run();
			String revMsg = null;
			try {
				InputStream is = Global.socket.getInputStream();
				byte[] data = new byte[Global.bufferSize];
				is.read(data);
				revMsg = new String(data).trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!revMsg.equals("")){
				Log.v(Global.TAGSKTSNDTHD, revMsg);
				// ������Ϣ handler
				// �����ǽ��յ����ݣ����ù���ʲô��ֻ�ô������̼߳��ɣ������̸߳��� ID �ж��ǲ����Լ�����Ϣ
				Message msg = new Message();
				msg.obj = revMsg;
				msg.what = msgID;
				handler.sendMessage(msg);
			}
		}
	}
}