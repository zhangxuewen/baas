package com.baasmng.util.httpService;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static String getCompanyCookie(HttpServletRequest request, String cookieName) throws Exception {
		Cookie cook[] = request.getCookies();
		if (cook != null && cook.length > 0) {
			for (int i = 0; i < cook.length; ++i) {
				if (cook[i].getName().equals(cookieName)) {
					return URLDecoder.decode(cook[i].getValue(), "UTF-8");
				}
			}
		}
		return "";
	}

	public static void setCompanyCookie(String str, String cookieName, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie(cookieName, URLEncoder.encode(str, "UTF-8"));
		cookie.setMaxAge(60 * 60 * 24 * 365);
		cookie.setPath("/");
		response.addCookie(cookie);

	}
}
