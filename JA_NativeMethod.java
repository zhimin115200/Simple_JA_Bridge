package com.example.simple_jscallandroid;

import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebView;
import android.widget.Toast;

public class JA_NativeMethod {

	/**同步方法
	 * js中调用的方法名必须与该方法名一致
	 * */
	public static void showToast(WebView webView, JSONObject param, JA_Callback callback){

		Toast.makeText(webView.getContext(), param.toString(), Toast.LENGTH_SHORT).show();
		try {
			callback.apply(new JSONObject("{text:'这是同步返回'}"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**异步方法*/
	public static void testAsyCall(WebView webView, JSONObject param,final JA_Callback callback){

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					callback.apply(new JSONObject("{text:这是异步返回}"));
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
