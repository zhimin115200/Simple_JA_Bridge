package com.example.simple_jscallandroid;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 
 * @author zhimin115200
 *
 */
public class JA_Bridge_WebChromeClient extends WebChromeClient {

	@Override
	public boolean onJsPrompt(WebView view, String url, String message,
			String defaultValue, JsPromptResult result) {
		// TODO Auto-generated method stub
		result.confirm(JA_Bridge.callAndroid(view, message));
		return true;
	}

}
