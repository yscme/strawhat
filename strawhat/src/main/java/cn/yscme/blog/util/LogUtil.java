package cn.yscme.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

/**
 * @author ysc
 * 日志
 */
public class LogUtil {
	public static void info(Class<?> c,String msg) {
		LoggerFactory.getLogger(c).info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" : "+msg);
	}
	public static void debug(Class<?> c,String msg) {
		LoggerFactory.getLogger(c).debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" : "+msg);;
	}
	public static void error(Class<?> c,String msg) {
		LoggerFactory.getLogger(c).error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" : "+msg);;
	}
	public static void warn(Class<?> c,String msg) {
		LoggerFactory.getLogger(c).warn(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" : "+msg);
	}
	public static void trace(Class<?> c,String msg) {
		LoggerFactory.getLogger(c).trace(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" : "+msg);
	}
}
