package cn.yscme.blog.util;

public class StringUtil {
	//判断请求数据是否为空
	public static boolean isNull(String str) {
		if(str==null||str.equals("")) {
			return true;
		}
		return false;
	}
}
