package com.shop.alipay.utils;

public class StringUtil {

	/**
	 * 判断字符串是否为null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str
	 *            要检查的字符串
	 *
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNotBlank(String str) {
		int length;
		if (str == null || (length = str.length()) == 0)
			return false;
		for (int i = 0; i < length; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return true;

		return false;
	}

	public static boolean contains(String str, char searchChar) {
		if (str == null || str.length() == 0)
			return false;
		return str.indexOf(searchChar) >= 0;
	}

	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;
		return str.indexOf(searchStr) >= 0;
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text, String repl, String with, int max) {
		if (text == null || repl == null || with == null || repl.length() == 0 || max == 0)
			return text;
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		for (int end = 0; (end = text.indexOf(repl, start)) != -1;) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();
			if (--max == 0)
				break;
		}

		buf.append(text.substring(start));
		return buf.toString();
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null)
			return str2 == null;
		else
			return str1.equals(str2);
	}

	public static int indexOf(String str, char searchChar) {
		if (str == null || str.length() == 0)
			return -1;
		else
			return str.indexOf(searchChar);
	}

	public static int indexOf(String str, char searchChar, int startPos) {
		if (str == null || str.length() == 0)
			return -1;
		else
			return str.indexOf(searchChar, startPos);
	}

	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null)
			return -1;
		else
			return str.indexOf(searchStr);
	}

	public static int indexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null)
			return -1;
		if (searchStr.length() == 0 && startPos >= str.length())
			return str.length();
		else
			return str.indexOf(searchStr, startPos);
	}

	public static String substring(String str, int start) {
		if (str == null)
			return null;
		if (start < 0)
			start = str.length() + start;
		if (start < 0)
			start = 0;
		if (start > str.length())
			return "";
		else
			return str.substring(start);
	}

	public static String substring(String str, int start, int end) {
		if (str == null)
			return null;
		if (end < 0)
			end = str.length() + end;
		if (start < 0)
			start = str.length() + start;
		if (end > str.length())
			end = str.length();
		if (start > end)
			return "";
		if (start < 0)
			start = 0;
		if (end < 0)
			end = 0;
		return str.substring(start, end);
	}

}
