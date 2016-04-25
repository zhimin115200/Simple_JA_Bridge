package com.example.simple_jscallandroid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.webkit.WebView;

public class JA_Bridge {

	private static HashMap<String ,Method> androidMethods = new HashMap<String ,Method>();

	public static void registMethod(Method method){
		androidMethods.put(method.getName() , method);
	}
	public static void unregistMethod(Method method){
		androidMethods.remove(method.getName());
	}
	final static String PROTO_TYPE = "JA_Bridge";
	
	/**调用android的方法
	 * message中包含方法名，回调的id，js传递给android的数据
	 * */
	public static String callAndroid(WebView webView, String message){

		if (message.startsWith(PROTO_TYPE)) {
			Uri uri = Uri.parse(message);
			String methodName = uri.getHost();
			int callbackId = uri.getPort();
			String params = uri.getQuery();//json
			Method method = androidMethods.get(methodName);
			try {
				method.invoke(null, webView, new JSONObject(params) , new JA_Callback(webView ,callbackId ));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
