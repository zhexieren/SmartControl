package com.smarpgrup.source;
/**
 * Project Name:SmartControl
 * File Name:Account.java
 * Package Name:com.smarpgrup.source
 * Description：User Account Information 使用单例模式
 * Date:2017年4月9日
 * Copyright (c) 2017, zhexieren@outlook.com All Rights Reserved.
 * Version: 1.0
 */
public class Account {
	private String passwd 	= null;
	private String name 	= null;
	private String number 	= null;
	private boolean onLine 	= false;
	
	
	public boolean isOnLine() {
		return onLine;
	}
	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	private static Account userAccount = null;
	static {
		userAccount = new Account();
	}
	
	private Account(){}
	
	public static Account getUserAccount(){
		return userAccount;		
	}
	public static Account getCurrentUserAccount(){
		return userAccount;
	}
}
