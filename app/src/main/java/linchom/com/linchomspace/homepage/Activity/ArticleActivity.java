package linchom.com.linchomspace.homepage.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Constant.Constant;
import linchom.com.linchomspace.homepage.Entity.ArticleInfoBean;
import linchom.com.linchomspace.homepage.View.SlideSelectView;

public class ArticleActivity extends AppCompatActivity {

    @InjectView(R.id.ib_commemt)
    ImageButton ibCommemt;
    @InjectView(R.id.ib_collect)
    ImageButton ibCollect;
    @InjectView(R.id.ib_share)
    ImageButton ibShare;

    @InjectView(R.id.tv_comment)
    TextView tvComment;

    @InjectView(R.id.article_title)
    TextView articleTitle;
    @InjectView(R.id.webview)
    WebView webview;

    @InjectView(R.id.rl_background_gray)
    RelativeLayout rlBackgroundGray;

    @InjectView(R.id.article_back)
    ImageButton articleBack;
    @InjectView(R.id.article_buy)
    ImageButton articleBuy;
    private SlideSelectView slideSelectView;
    private String[] textStrings;
    @InjectView(R.id.more)
    ImageButton more;
    private WebView webView;
    private TextView article_title;
    private String article_id;
    private ImageView iv_night;
    private Button btn_article_popup_cancel;
    private ImageView iv_font;
    private LinearLayout ll_more;
    private LinearLayout ll_fontselector;
    private WebSettings settings;
    boolean day = false;
    private EditText et_write_comment;
    private  RelativeLayout rl_article_titlebar;
    private  RelativeLayout rl_article_bottombar;
    private ScrollView sv_article;
    private boolean isRunning = false;
    private boolean isLoading=false;
    private ObjectAnimator mHeaderAnimator;
    private ObjectAnimator mBottomAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        article_id = bundle.getString("article_id");
        Log.i("aaa", article_id);
        initView();
        initData();
        bindEvents();


    }



    private void initView() {
        sv_article = ((ScrollView) findViewById(R.id.sv_article));
        rl_article_bottombar = ((RelativeLayout) findViewById(R.id.rl_article_bottombar));
        rl_article_titlebar = ((RelativeLayout) findViewById(R.id.rl_article_titlebar));
        article_title = ((TextView) findViewById(R.id.article_title));
        ll_more = ((LinearLayout) findViewById(R.id.ll_more));
        ll_fontselector = ((LinearLayout) findViewById(R.id.ll_fontselector));
        webView = (WebView) findViewById(R.id.webview);//加载WebView 
        settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        //settings.setTextSize(WebSettings.TextSize.SMALLEST);
        webView.getSettings().setBuiltInZoomControls(true);//设置可缩放
        // webView.getSettings().setUseWideViewPort(true);//可以任意比例缩放
        webView.getSettings().setJavaScriptEnabled(true);//设置设否支持JavaScript
        webView.loadUrl(Constant.ArticleWebView + article_id);//加载地址
//        settings.setLoadWithOverviewMode(true);
//        settings.setUseWideViewPort(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient());
        // 开启 DOM storage API功能
        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setUseWideViewPort(true);//web1就是你自己定义的窗口对象。
//        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); //设置浏览
    }

    private void initData() {

        getArticle();
    }

    private void getArticle() {
        RequestParams params = new RequestParams(Constant.Articleinfo);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("id", article_id);//由前一个界面带过来的
        // params.addBodyParameter("page","5");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                System.out.println(result);
                Gson gson = new Gson();
                ArticleInfoBean bean = gson.fromJson(result, ArticleInfoBean.class);
                // ArticleListBean.Article_list article=bean.data.getArticle_list().get(0).title;
                // String info=bean.data.getArticle_list().get(0).title;
                if(bean.getData().getGoods_id()=="0"){
                    articleBuy.setVisibility(View.GONE);
                }
                Log.i("aaaaaaa",bean.getData().getGoods_id());

                String info = bean.getData().getTitle();
                System.out.println(info);
                article_title.setText(info);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webView.canGoBack())
                webView.goBack();
                else
                finish();
            return true;
        }
        return false;
    }


    private void showMorePopupWindow(View view) {
        rlBackgroundGray.setVisibility(View.VISIBLE);
        final View contentView = LayoutInflater.from(ArticleActivity.this).inflate(
                R.layout.article_more_popupwindow, null);
        ll_more = ((LinearLayout) contentView.findViewById(R.id.ll_more));
        ll_fontselector = ((LinearLayout) contentView.findViewById(R.id.ll_fontselector));
        slideSelectView = (SlideSelectView) contentView.findViewById(R.id.slideSelectView);
        btn_article_popup_cancel = (Button) contentView.findViewById(R.id.btn_article_popup_cancel);
        textStrings = new String[]{"小", "正常",
                "大"};
        slideSelectView.setString(textStrings);

        slideSelectView.setOnSelectListener(new SlideSelectView.onSelectListener() {
            @Override
            public void onSelect(int index) {

                switch (index) {
                    case 0:
                        settings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;
                    case 1:
                        settings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 2:
                        settings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;

                }
            }


        });
        iv_night = ((ImageView) contentView.findViewById(R.id.iv_night));
        iv_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switchNightModel();
                if (day) {
                    iv_night.setBackgroundResource(R.drawable.night_circle);
                    iv_night.setImageResource(R.drawable.article_day);
                    day = true;


                } else {
                    iv_night.setBackgroundResource(R.drawable.article_more_circle);
                    iv_night.setImageResource(R.drawable.article_night);
                    day = false;

                }


            }
        });
        iv_font = ((ImageView) contentView.findViewById(R.id.iv_font));
        iv_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // settings.setTextSize(WebSettings.TextSize.LARGER);
                ll_more.setVisibility(View.GONE);
                ll_fontselector.setVisibility(View.VISIBLE);


            }
        });
        btn_article_popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlBackgroundGray.setVisibility(View.GONE);
                contentView.setVisibility(View.GONE);

            }
        });
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rlBackgroundGray.setVisibility(View.GONE);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(
        //  R.drawable.meijing));

        // 设置好参数之后再show
        // popupWindow.showAsDropDown(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }

    private void switchNightModel() {


    }

    @OnClick({R.id.more, R.id.ib_commemt, R.id.ib_collect, R.id.ib_share, R.id.tv_comment, R.id.article_buy,R.id.article_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more:
                showMorePopupWindow(view);
                break;
            case R.id.ib_commemt:
                break;
            case R.id.ib_collect:
                break;
            case R.id.ib_share:
                break;
            case R.id.tv_comment:
                showCommentPopupWindow(view);
                break;
            case R.id.article_buy:
                break;
            case R.id.article_back:
                this.finish();
                break;
        }
    }

    private void showCommentPopupWindow(final View view) {
        rlBackgroundGray.setVisibility(View.VISIBLE);
        final View contentView = LayoutInflater.from(ArticleActivity.this).inflate(
                R.layout.article_comment_popupwindow, null);
        et_write_comment = ((EditText) contentView.findViewById(R.id.et_write_comment));
        et_write_comment.requestFocus();

        InputMethodManager imm = (InputMethodManager) et_write_comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
 //       ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(et_write_comment,0);
      //  InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //这里给它设置了弹出的时间，
        imm.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);
        //隐藏软键盘
        //imm.hideSoftInputFromWindow(et_write_comment.getWindowToken(), 0);
//       final NoTouchLinearLayout ll_comment_popup = (NoTouchLinearLayout) contentView.findViewById(R.id.ll_comment_popup);
//        ll_comment_popup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    ll_comment_popup.setVisibility(View.GONE);
//                }
//            }
//        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, 420, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                rlBackgroundGray.setVisibility(View.GONE);
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(
        //  R.drawable.meijing));

        // 设置好参数之后再show
        // popupWindow.showAsDropDown(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
    private void bindEvents() {
        sv_article.setOnTouchListener(new View.OnTouchListener() {
            private float mEndY;
            private float mStartY;
            private int direction;//0表示向上，1表示向下
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;
                        if (v1 < -3 && !isRunning&& direction == 1) {
                            direction = 0;
                            showBar();
                            mStartY = mEndY;
                            return false;
                        } else if (v1 >3 && !isRunning && direction == 0) {
                            direction = 1;
                            hideBar();
                            mStartY = mEndY;
                            return false;
                        }
                        mStartY = mEndY;

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });


    }

    private void showBar() {
       mHeaderAnimator = ObjectAnimator.ofFloat(rl_article_titlebar, "translationY", -rl_article_titlebar.getHeight());
       mBottomAnimator = ObjectAnimator.ofFloat(rl_article_bottombar, "translationY", rl_article_bottombar.getHeight());
       mHeaderAnimator.setDuration(300).start();
        mBottomAnimator.setDuration(300).start();
        mHeaderAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isRunning = true;
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isRunning = false;
            }
        });

    }
    private void hideBar() {
        mHeaderAnimator = ObjectAnimator.ofFloat(rl_article_titlebar, "translationY", 0);
        mBottomAnimator = ObjectAnimator.ofFloat(rl_article_bottombar,"translationY", 0);

        mHeaderAnimator.setDuration(300).start();
       mBottomAnimator.setDuration(300).start();

    }
}

