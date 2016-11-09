package linchom.com.linchomspace.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import linchom.com.linchomspace.chat.pojo.ResultBean;
import linchom.com.linchomspace.chat.pojo.TopicDetialBean;
import linchom.com.linchomspace.chat.pojo.TopicKIdBean;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.login.contantData.Contant;

import static linchom.com.linchomspace.R.id.lv;
import static linchom.com.linchomspace.R.id.tv_chat_time;

public class KidChatDetilActivity extends AppCompatActivity {
    TopicDetialBean.DataBean.TopicCommentsBean comments;
    int floor;
    private  TextView tvChatUsername;
    private TextView tvChatTime;
    private TextView tvChatContent;
    private String userId="";
    private String username="";
    private  ImageView userLogo;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    CommonAdapter<TopicKIdBean.DataBean.ItemsBean> commentsAdapter;
    public final ArrayList<TopicKIdBean.DataBean.ItemsBean> commentsList = new ArrayList<TopicKIdBean.DataBean.ItemsBean>();

    String parent_comment_username;
    String id;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.et_remark)
    EditText etRemark;
    @InjectView(R.id.bt_remark)
    Button btRemark;
    @InjectView(R.id.lv_kid_list)
    ListView lvKidList;
    private Button rmkTip;
    private ImageView user_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_chat_detil);
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userId = shared_prefs.getString("userId","");
        username = shared_prefs.getString("username","");
        ButterKnife.inject(this);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));
        initView();
        getData();
    }

    private void initView() {
        Intent intent = this.getIntent();
        comments = (TopicDetialBean.DataBean.TopicCommentsBean) intent.getSerializableExtra("comments");
        floor = intent.getIntExtra("floor", 1);
        id = comments.getId();

        View header = View.inflate(this, R.layout.kid_comments_head, null);//头部内容
        lvKidList.addHeaderView(header);//添加头部

        userLogo= ((ImageView) header.findViewById(R.id.user_logo));
        tvChatUsername= ((TextView) header.findViewById(R.id.tv_chat_username));
        tvChatTime= ((TextView) header.findViewById(tv_chat_time));
        tvChatContent= ((TextView) header.findViewById(R.id.tv_chat_content));
        rmkTip = ((Button) header.findViewById(R.id.rmk_tip));
        user_logo = ((ImageView) header.findViewById(R.id.user_logo));
        switch (comments.getUser_id()){
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

        tvChatUsername.setText(comments.getUser_name());
        parent_comment_username = comments.getUser_name();
        System.out.println(parent_comment_username);
        if (comments.getAdd_time() != null) {
            int second = Integer.parseInt(String.valueOf(comments.getAdd_time()));
            String date = sdf.format(new Date(second * 1000L));
            tvChatTime.setText(date);
        }
        tvChatContent.setText(comments.getContent());
        rmkTip.setText(floor + "楼");

        etRemark.setHint("回复" + comments.getUser_name());
    }

    private void getData() {
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "list_topic_comments_username");
        params.addQueryStringParameter("id", id);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TopicKIdBean bean = gson.fromJson(result, TopicKIdBean.class);
                commentsList.clear();
                commentsList.addAll(bean.getData().getItems());

                if (commentsAdapter == null) {
                    Log.i("MainpageFragment", "onSuccess: ");
                    commentsAdapter = new CommonAdapter<TopicKIdBean.DataBean.ItemsBean>(getApplicationContext(), commentsList, R.layout.kid_comments_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, TopicKIdBean.DataBean.ItemsBean itemsBean, int position) {
                            TextView kid_username1 = viewHolder.getViewById(R.id.kid_username1);
                            TextView kid_content1 = viewHolder.getViewById(R.id.kid_content1);
                            TextView kid_time = viewHolder.getViewById(R.id.kid_time);
                            kid_username1.setText(itemsBean.getParent_comment_username()+":");
                            kid_content1.setText(itemsBean.getContent());
                            if (itemsBean.getAdd_time() != null) {
                                int second = Integer.parseInt(String.valueOf(itemsBean.getAdd_time()));
                                String date = sdf.format(new Date(second * 1000L));
                                kid_time.setText(date);
                            }

                        }
                    };
                    lvKidList.setAdapter(commentsAdapter);


                } else {
                    commentsAdapter.notifyDataSetChanged();
                }

        }
        @Override
        public void onError (Throwable ex,boolean isOnCallback){

        }

        @Override
        public void onCancelled (CancelledException cex){

        }

        @Override
        public void onFinished () {

        }
    }

    );

}

    private void doRemark() {
        String content = etRemark.getText().toString();
        if("".equals(content)){
            Toast.makeText(this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_topic_comments_username");
        params.addQueryStringParameter("user_id", userId);
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("parent_comment_username", parent_comment_username);
        params.addQueryStringParameter("id", id);
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultBean bean = gson.fromJson(result, ResultBean.class);
                Intent intent = new Intent();
                KidChatDetilActivity.this.setResult(RESULT_OK, intent);
                if (bean.isData()) {
                    Toast.makeText(KidChatDetilActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    finish();
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
                break;
        }
    }
}
