package com.teboz.biz.web.utils;

import java.util.Random;

public class CheckCodeUtils {

	private static final Random RANDOM = new Random();
	
	public static String checkCode(){
		String checkCode = "";
		for(int i = 0 ; i < 6 ;i++ ){
			checkCode +=RANDOM.nextInt(10);
		}
		return checkCode;
	}
}
