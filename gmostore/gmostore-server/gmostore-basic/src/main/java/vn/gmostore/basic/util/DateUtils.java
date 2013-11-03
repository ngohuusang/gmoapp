package vn.gmostore.basic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String DD_MM_YYYY= "dd.MM.yyyy";

	public static Date format(String date, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date rt = null;
		try {
			rt = dateFormat.parse(date);
		}catch (Exception e) {
			rt = null;
		}
		return rt;
	}

	public static String format(Date date, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String rt = null;
		try {
			rt = dateFormat.format(date);
		}catch (Exception e) {
			rt = null;
		}
		return rt;
	}
}