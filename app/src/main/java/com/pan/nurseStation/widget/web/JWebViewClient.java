package com.pan.nurseStation.widget.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JWebViewClient extends WebViewClient {
    static JWebViewClient client = new JWebViewClient();

    public static JWebViewClient getInstance() {
        return client;
    }

    /**
     * 打开网页时不调用系统浏览器,而是在本WebView中显示.
     * 在网页上的所有加载都经过这个方法
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    /**
     * 开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    /**
     * 在页面加载结束时调用。我们可以关闭loading 条，切换程序动作。
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    /**
     * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    /**
     * 加载页面的服务器出现错误时（如404）调用。
     */
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    /**
     * 处理https请求
     */
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        //handler.proceed();    //表示等待证书响应
        // handler.cancel();      //表示挂起连接，为默认方式
        // handler.handleMessage(null);    //可做其他处理
        super.onReceivedSslError(view, handler, error);
    }

}
