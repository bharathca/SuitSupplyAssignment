package com.java.base;

import java.util.Random;


public class JavaBase {
	public static int randomNumberGenerator(int range) {
		Random random = new Random();
		int randomN = random.nextInt(range - 1) + 1;
		return randomN;
	}
	
	public static String dynamicLocatorGenerator(String locatorValue, String replaceText) {
		return locatorValue.replace("replaceText", replaceText);
	}
	
	public static void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
