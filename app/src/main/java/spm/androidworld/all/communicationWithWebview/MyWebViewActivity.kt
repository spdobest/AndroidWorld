package spm.androidworld.all.communicationWithWebview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_web_view.*

/**
 * https://www.checkmarx.com/2017/11/16/android-webview-secure-coding-practices/
 */

class MyWebViewActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(spm.androidworld.all.R.layout.activity_my_web_view)

        webView.loadUrl("file:///android_asset/index.html")
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()
        webView.webChromeClient = MyWebChromeClient()

    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            //Calling a javascript function in html page
            view.loadUrl("javascript:alert(showVersion('called by Android'))")
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

            if (request?.url?.toString()?.startsWith("S")!!) {

            }

            return super.shouldOverrideUrlLoading(view, request)
        }

    }

    private inner class MyWebChromeClient : WebChromeClient() {
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            Log.d("LogTag", message)
            result.confirm()
            return true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}
