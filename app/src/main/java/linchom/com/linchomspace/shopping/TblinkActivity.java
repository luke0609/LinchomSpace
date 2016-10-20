package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import linchom.com.linchomspace.R;

public class TblinkActivity extends AppCompatActivity {

    private  String tbLink;
    private WebView wv_goods_tblink;
    private ImageView titlebar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tblink);

        Intent intent =getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");


        tbLink = bundle.getString("tblink");
        Toast.makeText(getApplicationContext(),tbLink,Toast.LENGTH_SHORT).show();

        initView();

        initData();

        initEvent();


    }

    private void initView() {

        wv_goods_tblink = ((WebView) findViewById(R.id.wv_goods_tblink));

        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


    }

    private void initData() {


    }

    private void initEvent() {




        wv_goods_tblink.getSettings().setJavaScriptEnabled(true);//设置设否支持JavaScript
        wv_goods_tblink.loadUrl(tbLink);//加载地址
        wv_goods_tblink.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        wv_goods_tblink.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API功能
        wv_goods_tblink.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setUseWideViewPort(true);//web1就是你自己定义的窗口对象。
//        webView.getSettings().setLoadWithOverviewMode(true);
        wv_goods_tblink.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); //设置浏览


        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
