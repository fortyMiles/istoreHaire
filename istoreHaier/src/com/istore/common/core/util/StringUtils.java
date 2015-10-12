package com.istore.common.core.util;

public class StringUtils {
	
	/***
	 * is null
	 * @return
	 */
	public static String isNull(String str){
		if("".equals(str)){
			return null;
		}else{
			return str;
		}
	}

}
