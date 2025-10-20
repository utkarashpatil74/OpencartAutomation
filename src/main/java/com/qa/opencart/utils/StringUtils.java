package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getRandomMailId() {
		String mail="testautomation"+System.currentTimeMillis()+"@gmail.com";
		return mail;
	}

}
