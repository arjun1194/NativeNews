package com.arjun1194.nativenews.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.arjun1194.nativenews.R

class ArticleDetail: AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var webviewClient: WebViewClient
    lateinit var webChromeClient: WebChromeClient

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_article_detail)
    }

    fun initWebview(){
        webChromeClient = WebChromeClient()

        webView.webChromeClient = webChromeClient
        webView.clearCache(true)
        webView.clearHistory()
        webView.loadUrl("")


    }
}