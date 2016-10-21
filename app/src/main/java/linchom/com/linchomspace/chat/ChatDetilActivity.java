package linchom.com.linchomspace.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.TopicList;

public class ChatDetilActivity extends AppCompatActivity {
    TopicList.DataBean.ItemsBean topicDetial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detil);
        Intent intent = this.getIntent();
        topicDetial = (TopicList.DataBean.ItemsBean) intent.getSerializableExtra("topicDetial");
        Toast.makeText(this, topicDetial.getTopic_name(), Toast.LENGTH_SHORT).show();
    }
}
