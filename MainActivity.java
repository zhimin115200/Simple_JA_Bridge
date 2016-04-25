package com.example.simple_jscallandroid;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 
 * @author zhimin115200
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initWebView();
		initNativeMethod();
	}

	private void initNativeMethod() {
		// TODO Auto-generated method stub

		Method[] methods = JA_NativeMethod.class.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC) || (method.getName()) == null) {
				continue;
			}
			Class[] parameters = method.getParameterTypes();
			if (null != parameters && parameters.length == 3) {
				if (parameters[0] == WebView.class && parameters[1] == JSONObject.class && parameters[2] == JA_Callback.class) {
					JA_Bridge.registMethod(method);
				}
			}
		}
	}

	private void initWebView() {
		// TODO Auto-generated method stub
		WebView mWebView = (WebView) findViewById(R.id.webview);

		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);//打开js接口
		mWebView.setWebChromeClient(new JA_Bridge_WebChromeClient());
		mWebView.loadUrl("file:///android_asset/my.html");
	}

}
