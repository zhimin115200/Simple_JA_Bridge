package com.example.simple_jscallandroid;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/***
 * 
 * @author zhimin115200
 *
 */
public class JA_Callback {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    
    private static final String CALLBACK_JS_FORMAT = "javascript:js.call('%s', %s);";
    private int callbackId;
    private WeakReference<WebView> mWebViewRef;//防止内存泄露

    public JA_Callback(WebView view, int callbackId2) {
        mWebViewRef = new WeakReference<WebView>(view);
        this.callbackId = callbackId2;
    }

    public void apply(JSONObject jsonObject) {
        final String execJs = String.format(CALLBACK_JS_FORMAT, callbackId, String.valueOf(jsonObject));
        if (mWebViewRef != null && mWebViewRef.get() != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebViewRef.get().loadUrl(execJs);
                }
            });

        }

    }
}
