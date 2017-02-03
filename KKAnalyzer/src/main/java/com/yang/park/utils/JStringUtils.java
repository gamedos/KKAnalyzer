/**
 * @author Yang.Park
 * @Time    2017.12.10
 * 一些字符串操作
 */
package com.yang.park.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JStringUtils {

	
	/**
	 * 判断一个字符串是不是一个数字字符串
	 * @param string
	 * @return
	 */
    public static boolean isNumeric(String string) {
        if (string == null || string.length() == 0)
            return false;

        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(string.codePointAt(i)))
                return false;
        }
        return true;
    }

    public static String join(Iterator<String> strings, String sep) {
        if (!strings.hasNext())
            return "";

        String start = strings.next().toString();
        if (!strings.hasNext()) // only one, avoid builder
            return start;

        StringBuilder sb = new StringBuilder(64).append(start);
        while (strings.hasNext()) {
            sb.append(sep);
            sb.append(strings.next());
        }
        return sb.toString();
    }
	/**
	 * 字符串合并
	 * @param ig
	 * @return
	 */
	public static String join(String[] ig, String sep) {  
	    return JStringUtils.join(Arrays.asList(ig).iterator() , sep);  
	} 
	public static String join(List<String> list, String sep) {  
	    return JStringUtils.join(list.iterator() , sep);  
	}
	
	public static String key2key(String content , String key1, String key2){
		String retStr = "";
		
		int pos1 = content.indexOf(key1);
		if(pos1<0) return retStr;
		
		int pos2 = content.indexOf(key2,pos1);
		if(pos2<0) return retStr;
		pos1 = pos1 + key1.length();
		String subStr = content.substring(pos1, pos2);
		retStr = subStr.trim();//.replaceAll(".", "").replaceAll(",", "");
		return retStr;
	}
	
}
