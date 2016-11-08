package linchom.com.linchomspace.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import linchom.com.linchomspace.R;

public class LogDetailActivity extends AppCompatActivity {

   private String title;

    private String content;

    private String addtime;

    private String photo;

    private TextView tv_service_log_detail_title;
    private TextView tv_service_log_detail_addtime;
    private TextView tv_service_log_detail_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_detail);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");

        title= bundle.getString("title");

        addtime=  bundle.getString("addtime");

        content =   bundle.getString("content");

        photo =  bundle.getString("photo");



        initView();

        initData();

        initEvent();


    }

    private void initView() {

        tv_service_log_detail_title = ((TextView) findViewById(R.id.tv_service_log_detail_title));

        tv_service_log_detail_addtime = ((TextView) findViewById(R.id.tv_service_log_detail_addtime));

        tv_service_log_detail_content = ((TextView) findViewById(R.id.tv_service_log_detail_content));


    }

    private void initData() {


    }

    private void initEvent() {

        tv_service_log_detail_title.setText(title);


        tv_service_log_detail_addtime.setText(addtime);

        tv_service_log_detail_content.setText(content);

    }
}
