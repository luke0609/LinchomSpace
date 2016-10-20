package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import linchom.com.linchomspace.R;

public class JdlinkActivity extends AppCompatActivity {

    private  String jdLink;
    private WebView wv_goods_jdlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdlink);


        Intent intent =getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");


        jdLink = bundle.getString("jdlink");

        Toast.makeText(getApplicationContext(),jdLink,Toast.LENGTH_SHORT).show();

        initView();

        initData();

        initEvent();
    }

    private void initView() {

        wv_goods_jdlink = ((WebView) findViewById(R.id.wv_goods_jdlink));


    }

    private void initData() {



    }

    private void initEvent() {



        wv_goods_jdlink.getSettings().setJavaScriptEnabled(true);//设置设否支持JavaScript
        wv_goods_jdlink.loadUrl(jdLink);//加载地址
        wv_goods_jdlink.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        wv_goods_jdlink.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API功能
        wv_goods_jdlink.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setUseWideViewPort(true);//web1就是你自己定义的窗口对象。
//        webView.getSettings().setLoadWithOverviewMode(true);
        wv_goods_jdlink.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); //设置浏览



    }
}
