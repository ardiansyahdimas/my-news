package com.idstar.test.mynews.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.idstar.test.core.domain.model.NewsArticleModel
import com.idstar.test.mynews.databinding.ActivityDetailNewsArticleBinding

class DetailNewsArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsArticleBinding
    private lateinit var webView: WebView

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(EXTRA_DATA, NewsArticleModel::class.java)
        } else {
            intent?.getParcelableExtra(EXTRA_DATA)
        }

        title = data?.title

        webView = binding.webView
        webView.webViewClient = MyWebViewClient()

        webView.loadUrl(data?.url.toString())
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (webView.canGoBack())
            webView.goBack()
        else
            onBackPressedDispatcher.onBackPressed()
        return true
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }
}