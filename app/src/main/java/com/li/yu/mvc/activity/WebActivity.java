package com.li.yu.mvc.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.yu.mvc.R;
import com.li.yu.mvc.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;

    public static void start(Context context,String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initEventAndData() {
        String url = getIntent().getStringExtra("url");
        WebSettings settings =  webview.getSettings();
        webview.setHorizontalScrollBarEnabled(false);
        webview.getSettings().setSupportZoom(false);//  设置可以支持缩放
        //设置WebView可触摸放大缩小
        webview.getSettings().setBuiltInZoomControls(false);
        settings.setSupportZoom(true);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);
//        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //自适应屏幕   居中显示
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(url);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
