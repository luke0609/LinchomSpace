package linchom.com.linchomspace.chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.ResultBean;
import linchom.com.linchomspace.chat.pojo.TopicDetialBean;
import linchom.com.linchomspace.chat.util.StatusBarCompat;

public class KidChatDetilActivity extends AppCompatActivity {
    TopicDetialBean.DataBean.TopicCommentsBean comments;
    int floor;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.user_logo)
    ImageView userLogo;
    @InjectView(R.id.tv_chat_username)
    TextView tvChatUsername;
    @InjectView(R.id.tv_chat_time)
    TextView tvChatTime;
    @InjectView(R.id.tv_chat_content)
    TextView tvChatContent;
    @InjectView(R.id.et_remark)
    EditText etRemark;
    @InjectView(R.id.bt_remark)
    Button btRemark;
    @InjectView(R.id.bar_title)
    TextView barTitle;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @InjectView(R.id.rmk_tip)
    Button rmkTip;
    String topic_id;
    String parent_comment_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_chat_detil);
        ButterKnife.inject(this);
        StatusBarCompat.compat(this, Color.parseColor("#262B4D"));
        initView();
    }

    private void initView() {
        Intent intent = this.getIntent();
        comments = (TopicDetialBean.DataBean.TopicCommentsBean) intent.getSerializableExtra("comments");
        floor =  intent.getIntExtra("floor", 1);

        tvChatUsername.setText(comments.getUser_name());
        parent_comment_username=comments.getUser_name();
        System.out.println(parent_comment_username);
        if (comments.getAdd_time() != null) {
            int second = Integer.parseInt(String.valueOf(comments.getAdd_time()));
            String date = sdf.format(new Date(second * 1000L));
            tvChatTime.setText(date);
        }
        tvChatContent.setText(comments.getContent());
        rmkTip.setText(floor+"楼");
        topic_id= comments.getTopic_id();
        etRemark.setHint("回复"+comments.getUser_name());
    }
    private void doRemark(){
        String content= etRemark.getText().toString();
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_topic_comments");
        params.addQueryStringParameter("topic_id", topic_id);
        params.addQueryStringParameter("user_id", 129+"");
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("parent_comment_username",parent_comment_username);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultBean bean = gson.fromJson(result, ResultBean.class);
                if(bean.isData()){
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
