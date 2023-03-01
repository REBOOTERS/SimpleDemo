package com.example.tencenter.androiddemo

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webview = findViewById<WebView>(R.id.webview)

        val settings = webview.settings
        settings.javaScriptEnabled = true

        val URL_USER_PROTOCOL = "https://www.baidu.com"

        webview.loadUrl(URL_USER_PROTOCOL)
    }
}