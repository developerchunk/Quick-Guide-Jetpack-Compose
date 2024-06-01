package com.developerstring.quickguidejetpack.open_pdf_url

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun OpenPDFViaUrl() {

    // setting the link of the pdf into a variable
    val url = "[Link of the PDF]"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PDFWebView(url)
    }

}

// creating a composable function for opening the url into a WebView, so that the internal browser can load the pdf
@Composable
fun PDFWebView(pdfUrl: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(pdfUrl)
        }
    })
}