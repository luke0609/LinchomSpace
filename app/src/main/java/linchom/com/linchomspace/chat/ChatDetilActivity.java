package linchom.com.linchomspace.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.ResultBean;
import linchom.com.linchomspace.chat.pojo.TopicDetialBean;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.DateUtils;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.photoutil.NineGridTestLayout;

import static linchom.com.linchomspace.R.id.tv_chat_time;
import static linchom.com.linchomspace.R.id.tv_name;
import static linchom.com.linchomspace.R.id.user_logo;

public class ChatDetilActivity extends AppCompatActivity {
    @InjectView(R.id.iv_back)
    ImageView ivBack;


    @InjectView(R.id.remark_num)
    TextView remarkNum;
    @InjectView(R.id.invis)
    RelativeLayout invis;
    @InjectView(R.id.et_remark)
    EditText etRemark;
    @InjectView(R.id.lv)
    ListView lv;
    NineGridTestLayout photo_show;
    String topicId;
    CommonAdapter<TopicDetialBean.DataBean.TopicCommentsBean> commentsAdapter;
    public final ArrayList<TopicDetialBean.DataBean.TopicCommentsBean> commentsList = new ArrayList<TopicDetialBean.DataBean.TopicCommentsBean>();
    String parent_comment_username;
    private TextView title;
     private  TextView tvChatUsername;
     private TextView tvChatTime;
     private TextView tvChatContent;

     private  ImageView userLogo;
    private TextView action_num;
    private ImageView user_logo;
    private String userId="";
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat_detil);
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userId = shared_prefs.getString("userId","");
        username = shared_prefs.getString("username","");
        ButterKnife.inject(this);
        StatusBarCompat.compat(this, Color.parseColor("#4EAFAB"));
        getView();
        getData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001){
            getData();
            System.out.println(" getData();");
        }
    }

    private void getView() {
        Intent intent = this.getIntent();
        topicId = intent.getStringExtra("topic_id");
        System.out.println(topicId + "");


        View header = View.inflate(this, R.layout.stick_header, null);//头部内容
        lv.addHeaderView(header);//添加头部
        View numberBar = View.inflate(this, R.layout.stick_action, null);
        lv.addHeaderView(numberBar);//ListView条目中的悬浮部分 添加到头部
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (firstVisibleItem >= 1) {
                    invis.setVisibility(View.VISIBLE);
                } else {

                    invis.setVisibility(View.GONE);
                }
            }
        });
        title = ((TextView) header.findViewById(R.id.title));
        userLogo= ((ImageView) header.findViewById(R.id.user_logo));
        tvChatUsername= ((TextView) header.findViewById(R.id.tv_chat_username));
        tvChatTime= ((TextView) header.findViewById(tv_chat_time));
        tvChatContent= ((TextView) header.findViewById(R.id.tv_chat_content));
        action_num = ((TextView) numberBar.findViewById(R.id.remark_num));
        photo_show=(NineGridTestLayout)header.findViewById(R.id.photo_show);
        user_logo = ((ImageView) header.findViewById(R.id.user_logo));
    }


    private void getData() {


        Log.i("xuanfu", "getData: ");
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "topicinfo");
        params.addQueryStringParameter("topic_id", topicId);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("xuanfu", "onSuccess: " + result);

                Gson gson = new Gson();
                TopicDetialBean bean = gson.fromJson(result, TopicDetialBean.class);

                commentsList.clear();
                commentsList.addAll(bean.getData().getTopic_comments());
                //清空原来的数据

                List<String> urllist=new ArrayList<>();
                if(bean.getData().getPhoto()!=null&&(!"".equals(bean.getData().getPhoto()))){

                    String[] urls=bean.getData().getPhoto().split(",");
                    for (int i = 0; i <urls.length ; i++) {
                        System.out.println("!!!"+urls[i]);
                        if ("0".equals(urls[i])){
                            continue;
                        }

                        urllist.add(urls[i]);
                    }
                    photo_show.setUrlList(urllist);
                }else {
                    urllist.clear();
                    photo_show.setUrlList(urllist);
                }

                title.setText(bean.getData().getTopic_name());
                tvChatUsername.setText(bean.getData().getUser_name());

                switch (bean.getData().getUser_id()){
                    case "129":
                        user_logo.setImageResource(R.drawable.kid);
                        break;
                    case "135":
                        user_logo.setImageResource(R.mipmap.avatar);
                        break;
                    case "140":
                        user_logo.setImageResource(R.mipmap.avatar);
                        break;
                    case "142":
                        user_logo.setImageResource(R.drawable.head1);
                        break;
                }
                int timeB = Integer.parseInt(bean.getData().getAdd_time());

                tvChatTime.setText(DateUtils.getGapTimeFromNow(new Date(timeB * 1000L)));
                tvChatContent.setText(bean.getData().getCommunication_title().trim());


                //设置listview的apter
                                       if (commentsAdapter == null) {
                            Log.i("MainpageFragment", "onSuccess: ");
                            commentsAdapter = new CommonAdapter<TopicDetialBean.DataBean.TopicCommentsBean>(getApplicationContext(), commentsList, R.layout.chat_remark_item) {

                        @Override
                        public void convert(ViewHolder viewHolder, final TopicDetialBean.DataBean.TopicCommentsBean comments, final int position) {
                            TextView tv_name = viewHolder.getViewById(R.id.tv_chat_username);
                            TextView tv_content = viewHolder.getViewById(R.id.tv_chat_content);
                            TextView tv_time = viewHolder.getViewById(tv_chat_time);
                            Button rmk_tip = viewHolder.getViewById(R.id.rmk_tip);
                            ImageView kid_remark = viewHolder.getViewById(R.id.kid_remark);
                            ImageView userlog=viewHolder.getViewById(R.id.user_logo);
                            RelativeLayout kid_content_item1=viewHolder.getViewById(R.id.kid_content_item1);
                            RelativeLayout kid_content_item2=viewHolder.getViewById(R.id.kid_content_item2);
                            TextView kid_username1=viewHolder.getViewById(R.id.kid_username1);
                            TextView kid_username2=viewHolder.getViewById(R.id.kid_username2);
                            TextView kid_content1=viewHolder.getViewById(R.id.kid_content1);
                            TextView kid_content2=viewHolder.getViewById(R.id.kid_content2);
                            TextView tv_more=viewHolder.getViewById(R.id.tv_more);
                            View struct_line2=viewHolder.getViewById(R.id.struct_line2);
                            kid_content_item1.setVisibility(View.GONE);
                            kid_content_item2.setVisibility(View.GONE);
                            struct_line2.setVisibility(View.GONE);
                            tv_more.setVisibility(View.GONE);
                            int kid_comment_listsize=comments.getCommentsss().size();
                            System.out.println(kid_comment_listsize);
                            if(kid_comment_listsize==1){
                                System.out.println("jinru1");
                                kid_content_item1.setVisibility(View.VISIBLE);
                                struct_line2.setVisibility(View.VISIBLE);
                                kid_username1.setText(comments.getCommentsss().get(0).getParent_comment_username()+":");
                                kid_content1.setText(comments.getCommentsss().get(0).getContent());
                            }else if(kid_comment_listsize==2){
                                System.out.println("jinru2");
                                kid_content_item1.setVisibility(View.VISIBLE);

                                struct_line2.setVisibility(View.VISIBLE);
                                kid_username1.setText(comments.getCommentsss().get(0).getParent_comment_username()+":");
                                kid_content1.setText(comments.getCommentsss().get(0).getContent());
                                kid_content_item2.setVisibility(View.VISIBLE);

                                kid_username2.setText(comments.getCommentsss().get(1).getParent_comment_username()+":");
                                kid_content2.setText(comments.getCommentsss().get(1).getContent());
                            }else if (kid_comment_listsize>2){
                                System.out.println("jinru3");
                                kid_content_item1.setVisibility(View.VISIBLE);
                                kid_content_item2.setVisibility(View.VISIBLE);
                                struct_line2.setVisibility(View.VISIBLE);
                                kid_username1.setText(comments.getCommentsss().get(0).getParent_comment_username()+":");
                                kid_content1.setText(comments.getCommentsss().get(0).getContent());

                                kid_username2.setText(comments.getCommentsss().get(1).getParent_comment_username()+":");
                                kid_content2.setText(comments.getCommentsss().get(1).getContent());
                                tv_more.setVisibility(View.VISIBLE);
                                tv_more.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ChatDetilActivity.this, KidChatDetilActivity.class);

                                        intent.putExtra("comments", comments);
                                        intent.putExtra("floor", (position + 1));
                                        System.out.println(position + 1);
                                        startActivityForResult(intent,1001);
                                    }
                                });
                            }
                            kid_remark.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ChatDetilActivity.this, KidChatDetilActivity.class);

                                    intent.putExtra("comments", comments);
                                    intent.putExtra("floor", (position + 1));
                                    System.out.println(position + 1);
                                    startActivityForResult(intent,1001);

                                }
                            });

                            if (comments.getAdd_time() != null) {
                                int second = Integer.parseInt(String.valueOf(comments.getAdd_time()));

                                tv_time.setText(DateUtils.getGapTimeFromNow(new Date(second * 1000L)));
                            }

                            rmk_tip.setText(position + 1 + "楼");
                            tv_name.setText(comments.getUser_name());

                            tv_content.setText(comments.getContent());
                            switch (comments.getUser_id()){
                                case "129":
                                    userlog.setImageResource(R.drawable.kid);
                                    break;
                                case "135":
                                    userlog.setImageResource(R.mipmap.avatar);
                                    break;
                                case "140":
                                    userlog.setImageResource(R.mipmap.avatar);
                                    break;
                                case "142":
                                    userlog.setImageResource(R.drawable.head1);

                                    break;
                            }


                            if (commentsList.size() != 0) {
                                remarkNum.setText(commentsList.size() + "");
                                action_num.setText(commentsList.size() + "");
                            } else {
                                remarkNum.setText("0");
                            }
                        }
                    };
                    lv.setAdapter(commentsAdapter);


                } else {
                    commentsAdapter.notifyDataSetChanged();
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

    private void doRemark() {
        String content = etRemark.getText().toString();
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_topic_comments");
        params.addQueryStringParameter("topic_id", topicId);
        params.addQueryStringParameter("user_id",userId);
        params.addQueryStringParameter("content", content);

        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultBean bean = gson.fromJson(result, ResultBean.class);
                if (bean.isData()) {
                    Toast.makeText(ChatDetilActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    getData();
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

    @OnClick({R.id.iv_back, R.id.bt_remark})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_remark:
                doRemark();
                etRemark.setText("");


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etRemark.getWindowToken(), 0);
                break;
        }
    }
}
