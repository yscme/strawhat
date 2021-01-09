package cn.yscme.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getTime(int i) {
		switch (i) {
		case 1:return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		case 2:return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
		case 3:return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		case 4:return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		default:
			return new Date().toString();
		}
	}
}
