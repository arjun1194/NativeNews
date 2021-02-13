package com.arjun1194.nativenews.ui

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.arjun1194.nativenews.R

class ArticleDetail: AppCompatActivity() {

    private lateinit var webView: WebView
    private val args: ArticleDetailArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        Log.d(TAG, "onCreate: ${args.url}")
        initWebView()
    }


    private fun initWebView(){
        Log.d(TAG, "initWebView: Arguments -->> $args")
        webView = findViewById(R.id.webView)
        webView.apply{
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(args.url)
        }
    }

    companion object {
        private const val TAG = "ArticleDetail"
    }
}