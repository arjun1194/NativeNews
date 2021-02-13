package com.arjun1194.nativenews.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.arjun1194.nativenews.R

class ArticleDetail: AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var webChromeClient: WebChromeClient
    private val args: ArticleDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_article_detail)
        initWebView()
    }

    private fun initWebView(){
        Log.d(TAG, "initWebView: Arguments -->> $args")
        webView = findViewById(R.id.webView)
        webChromeClient = WebChromeClient()
        webView.webChromeClient = webChromeClient
        webView.loadUrl(args.url)
    }

    companion object {
        private const val TAG = "ArticleDetail"
    }
}