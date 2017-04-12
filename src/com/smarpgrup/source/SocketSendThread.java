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
 * Name:com.smarpgrup.source Description：Smart Control APP Connect With TCP/IP
 * Protocol Date:2017年4月10日 Copyright (c) 2017, zhexieren@outlook.com All Rights
 * Reserved. Version: 1.0
 */
public class SocketSendThread extends Thread {
	private int msgID; // 表示消息的类型，或者是 功能号---后边更新activity 时根据 msgid 控制
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
			 * 这里直接发送当前的 sendbuffer
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
				// 发送消息 handler
				// 这里是接收到数据，不用管是什么，只用传给主线程即可，由主线程根据 ID 判断是不是自己的消息
				Message msg = new Message();
				msg.obj = revMsg;
				msg.what = msgID;
				handler.sendMessage(msg);
			}
		}
	}
}