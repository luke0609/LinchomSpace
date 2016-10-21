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

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
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
        System.out.println(floor);
        tvChatUsername.setText(comments.getUser_name());
        if (comments.getAdd_time() != null) {
            int second = Integer.parseInt(String.valueOf(comments.getAdd_time()));
            String date = sdf.format(new Date(second * 1000L));
            tvChatTime.setText(date);
        }
        tvChatContent.setText(comments.getContent());
        rmkTip.setText(floor+"楼");

        etRemark.setHint("回复"+comments.getUser_name());
    }

    @OnClick({R.id.iv_back, R.id.bt_remark})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.bt_remark:
                break;
        }
    }
}
