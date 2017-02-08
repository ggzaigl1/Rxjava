package com.jypj.yuexiu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpBase;
import com.jypj.yuexiu.widget.AppLoading;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class WebActivity extends BaseActivity {
    WebView webview;
    TextView error;
    private static final String APP_CACAHE_DIRNAME = "/Android/data/com.jypj.yxjy/webcache/";

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        AppLoading.show(this);
        webview = (WebView) findViewById(R.id.activitywebview);
        error = (TextView) findViewById(R.id.showError);
        ((TextView) findViewById(R.id.web_title)).setText(getIntent().getStringExtra("title"));
        WebSettings settings = webview.getSettings();
        //支持JS
        settings.setJavaScriptEnabled(true);
        initWebView();
        //适配屏幕大小
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                webview.loadUrl(getIntent().getStringExtra("url"));
            }
        });
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                AppLoading.close();
                super.onPageFinished(view, url);
            }

            @SuppressWarnings("deprecation")
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e("Tag","errorCode===="+errorCode);
                webview.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        if (getIntent().getStringExtra("title").equals("详细内容")) {
            webview.loadUrl(getIntent().getStringExtra("url"));
        } else if (getIntent().getStringExtra("title").equals("统计视图")) {
            Map<String, String> extraHeader = new HashMap<>();
            extraHeader.put("token", HttpBase.token);
            String countUrl =HttpBase.HOST_STRING+"query/count?roleType=" + getIntent().getStringExtra("roleType");
            webview.loadUrl(countUrl, extraHeader);
        } else {
            setDataUrl();
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    private void initWebView() {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        webview.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        webview.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        //String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        //设置数据库缓存路径
        webview.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        webview.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        webview.getSettings().setAppCacheEnabled(true);
    }

    private void setDataUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Document doc = Jsoup.connect(getIntent().getStringExtra("url")).get();
                    doc.select("body>iframe").remove();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webview.loadDataWithBaseURL(null, doc.toString(), "text/html", "utf-8", null);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
