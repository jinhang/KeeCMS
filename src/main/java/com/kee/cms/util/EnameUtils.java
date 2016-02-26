package com.kee.cms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnameUtils {

	public static boolean test(String str) {
		boolean result = false;
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		if (count == 0) {
			result = true;
			return result;
		} else {
			return result;
		}
	}
}
