package linchom.com.linchomspace.homepage.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.homepage.Constant.Constant;
import linchom.com.linchomspace.homepage.Entity.ArticleAddCommentBean;
import linchom.com.linchomspace.homepage.Entity.ArticleCommentBean;
import linchom.com.linchomspace.homepage.Entity.CommentPositionBean;
import linchom.com.linchomspace.homepage.Utils.ToastUtil;
import linchom.com.linchomspace.login.contantData.Contant;

import static linchom.com.linchomspace.R.id.tv_comment2;

public class CommentActivity extends AppCompatActivity implements View.OnLayoutChangeListener {

    @InjectView(R.id.article_comment_back)
    ImageButton articleCommentBack;
    @InjectView(R.id.rl_article_comment_titlebar)
    RelativeLayout rlArticleCommentTitlebar;
    @InjectView(tv_comment2)
    TextView tvComment2;
    @InjectView(R.id.rl_article_comment_bottombar)
    RelativeLayout rlArticleCommentBottombar;
    @InjectView(R.id.rl_comment_detail)
    RelativeLayout rlCommentDetail;
    @InjectView(R.id.rl_nocomment)
    RelativeLayout rlNocomment;
    @InjectView(R.id.iv_nonet)
    ImageView ivNonet;
    @InjectView(R.id.reload)
    Button reload;

    @InjectView(R.id.ptr_arrlist_comment)
    PullToRefreshListView ptrArrlistComment;
    CommonAdapter<ArticleCommentBean.DataBean.ItemsBean> commentAdapter;
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    ArticleCommentBean.DataBean.ItemsBean articleComment;
    @InjectView(R.id.rl_wait_comment)
    RelativeLayout rlWaitComment;
    @InjectView(R.id.error_net)
    RelativeLayout errorNet;
    @InjectView(R.id.rl_background_gray)
    RelativeLayout rlBackgroundGray;
    private String article_id;
    private int page = 1;
    private int total_pages;
    public final ArrayList<ArticleCommentBean.DataBean.ItemsBean> commentList = new ArrayList<ArticleCommentBean.DataBean.ItemsBean>();
    public ListView lv_comment;
    private boolean pullFlag = false;
    private  String comment;
    private  String comment1;
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    PopupWindow popupWindow;
    private String userName;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userName = shared_prefs.getString("username","");
        userId = shared_prefs.getString("userId","");
        receiveArticleId();
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        activityRootView = findViewById(R.id.fl_comment);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;

        ptrArrlistComment.setScrollingWhileRefreshingEnabled(true);
        ptrArrlistComment.setMode(PullToRefreshBase.Mode.BOTH);
        ptrArrlistComment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(CommentActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据
                PullToRefreshBase.Mode mode = ptrArrlistComment.getCurrentMode();
                // View viewRefresh = null;
                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {
                    page++;
                    getComment();

                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    getComment();

                }
            }
        });
        ptrArrlistComment.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                // Toast.makeText(SearchArticleActivity.this, "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        lv_comment = ptrArrlistComment.getRefreshableView();

        commentAdapter = new CommonAdapter<ArticleCommentBean.DataBean.ItemsBean>(getApplicationContext(), commentList, R.layout.article_comment_item) {

            @Override
            public void convert(ViewHolder viewHolder, ArticleCommentBean.DataBean.ItemsBean itemsBean, int position) {
                TextView tv_article_comment_username = viewHolder.getViewById(R.id.tv_article_comment_username);
                TextView tv_article_comment_time = viewHolder.getViewById(R.id.tv_article_comment_time);
                TextView tv_article_content = viewHolder.getViewById(R.id.tv_article_content);
               // TextView tv_position = viewHolder.getViewById(R.id.tv_position);
                 tv_article_comment_username.setText(itemsBean.getUser_name());
                //tv_article_comment_username.setText(userName);
                //tv_position.setText("江苏苏州");
                int commentTime = Integer.parseInt(itemsBean.getAdd_time());
                String date = sdf.format(new Date(commentTime * 1000L));
                tv_article_comment_time.setText(date);
                tv_article_content.setText(itemsBean.getContent());
            }
        };

        lv_comment.setAdapter(commentAdapter);
        getComment();


    }
    private void receiveArticleId(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle1");
        article_id = bundle.getString("article_id");
    }

    private void initData() {
        getComment();
    }

    @OnClick({R.id.article_comment_back, tv_comment2, R.id.reload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_comment_back:
                Intent intent = new Intent();
                CommentActivity.this.setResult(RESULT_OK,intent);
                finish();
                break;
            case tv_comment2:
                showCommentPopupwindow(view);
                break;
            case R.id.reload:
                getComment();
                break;

        }
    }

    private void showCommentPopupwindow(View view) {

        rlBackgroundGray.setVisibility(View.VISIBLE);
        final View contentView = LayoutInflater.from(CommentActivity.this).inflate(R.layout.article_comment_popupwindow, null);
        TextView tv_comment2 = ((TextView)findViewById(R.id.tv_comment2));
        final LinearLayout ll_comment_popup = ((LinearLayout)contentView.findViewById(R.id.ll_comment_popup));
        tv_comment2.requestFocus();
       final  InputMethodManager imm = (InputMethodManager) tv_comment2.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        //这里给它设置了弹出的时间，
       imm.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);
        //隐藏软键盘

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, 520, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rlBackgroundGray.setVisibility(View.GONE);
            }
        });


        final EditText et_write_comment = ((EditText) contentView.findViewById(R.id.et_write_comment));
        et_write_comment.requestFocus();
        Button publish_comment = ((Button) contentView.findViewById(R.id.publish_comment));
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        publish_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                comment=et_write_comment.getText().toString();
                // Toast.makeText(getApplicationContext(),comment,Toast.LENGTH_SHORT).show();
                publishComment(comment);
                popupWindow.dismiss();
                //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                imm.hideSoftInputFromWindow(et_write_comment.getWindowToken(), 0);
                rlBackgroundGray.setVisibility(View.GONE);
                getComment();
            }
        });

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);



    }

    private void publishComment(String comment) {

            // http://app.linchom.com/appapi.php?act=add_article_comment&user_name=%E5%BC%A0%E6%99%93%E6%96%87&user_id=135&article_id=120&email=2070118814@qq.com&content=%E8%AF%84%E8%AE%BA
            RequestParams params = new RequestParams(Constant.ArticleAddComment);
            params.addQueryStringParameter("user_name", userName);
            //Toast.makeText(getApplicationContext(), userName + "=====" + userId, Toast.LENGTH_SHORT).show();
            params.addQueryStringParameter("article_id", article_id);
            params.addQueryStringParameter("user_id", userId);
            // params.addBodyParameter("email","2070118814@qq.com");
            params.addBodyParameter("content", comment);
            params.addBodyParameter("type", "2");
            System.out.println(params);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println(result);
                    Gson gson = new Gson();
                    //Log.i("comment",comment);
                    ArticleAddCommentBean bean = gson.fromJson(result, ArticleAddCommentBean.class);
                    Log.i("评论结果", bean.getResult() + "");
                    if (bean.getResult().equals("0")) {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_style,
                                (ViewGroup) findViewById(R.id.ll_toast));
                        ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_collect);
                        image.setImageResource(R.drawable.publishsuccess);
                        TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
                        text.setText("评论成功");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        ToastUtil.showMyToast(toast, 1000);

                    }


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

    //强制隐藏软键盘
    public static void hideSystemKeyBoard(Context mcontext,View v) {
        InputMethodManager imm = (InputMethodManager) ((CommentActivity) mcontext)
        .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    private void getComment(){
        rlNocomment.setVisibility(View.GONE);
        errorNet.setVisibility(View.GONE);
        if (page == 1 && pullFlag == false) {
            rlWaitComment.setVisibility(View.VISIBLE);
        }

        //"http://app.linchom.com/appapi.php?act=article_comment&article_id=120"
        RequestParams params = new RequestParams(Constant.ArticleSeeComment);
        params.addBodyParameter("article_id", article_id);
        params.addBodyParameter("page", page + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                ptrArrlistComment.setVisibility(View.GONE);
                rlWaitComment.setVisibility(View.GONE);
                if (page == 1) {
                    commentList.clear();
                }
                Gson gson = new Gson();
                ArticleCommentBean bean = gson.fromJson(result, ArticleCommentBean.class);
                Log.i("bean", bean.getData().getTotal());
                ArticleCommentBean.DataBean commentData = bean.getData();
                total_pages = commentData.getTotal_pages();
                Log.i("total_pages", total_pages + "");
                Log.i("数组长度", commentData.getItems().size() + "");
                if (!commentData.getTotal().equals("0")) {
                    ptrArrlistComment.setVisibility(View.VISIBLE);
//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面
                    Log.i("刷新后的page", page + "");
                    if (page <= total_pages) {
                        commentList.addAll(commentData.getItems());
                        commentAdapter.notifyDataSetChanged();
                        ptrArrlistComment.onRefreshComplete();
                    } else {
                        Toast.makeText(CommentActivity.this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        commentAdapter.notifyDataSetChanged();
                        ptrArrlistComment.onRefreshComplete();


                    }
                } else {
                    rlNocomment.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rlWaitComment.setVisibility(View.GONE);
                ptrArrlistComment.setVisibility(View.GONE);
                rlNocomment.setVisibility(View.GONE);
                errorNet.setVisibility(View.VISIBLE);
                reload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getComment();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void getCommentPosition(){

        RequestParams params = new RequestParams(Constant.CommentPosition);


        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                CommentPositionBean bean = gson.fromJson(result, CommentPositionBean.class);

                CommentPositionBean.DataBean commentData = bean.getData();
                TextView tv_position = (TextView)findViewById(R.id.tv_position);
                String position=commentData.getCity();
                tv_position.setText(position);
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
    protected void onResume() {
        super.onResume();
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

            // Toast.makeText(ArticleActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            // Toast.makeText(ArticleActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();

            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }

        }

    }

}
