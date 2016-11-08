package linchom.com.linchomspace.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.photoutil.NineGridTestLayout;

public class LogDetailActivity extends AppCompatActivity {

    private static final String TAG = "LogDetailActivity";
    private String title;

    private String content;

    private String addtime;

    private String photo;

    private String[] photos;

    private TextView tv_service_log_detail_title;
    private TextView tv_service_log_detail_addtime;
    private TextView tv_service_log_detail_content;
    private NineGridTestLayout ngtl_service_log_photo;
    private ImageView titlebar_back;


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

        Log.i(TAG,"photo"+photo.length());

        photos = photo.split(",");



        initView();

        initData();

        initEvent();


    }

    private void initView() {

        tv_service_log_detail_title = ((TextView) findViewById(R.id.tv_service_log_detail_title));

        tv_service_log_detail_addtime = ((TextView) findViewById(R.id.tv_service_log_detail_addtime));

        tv_service_log_detail_content = ((TextView) findViewById(R.id.tv_service_log_detail_content));

        ngtl_service_log_photo = ((NineGridTestLayout) findViewById(R.id.ngtl_service_log_photo));


        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


    }

    private void initData() {


    }

    private void initEvent() {

        tv_service_log_detail_title.setText(title);

        Long time = Long.parseLong(addtime)*1000;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String str = sdf.format(time);



        tv_service_log_detail_addtime.setText(str);

        tv_service_log_detail_content.setText(content);

        List<String> urlList =new ArrayList<String>();



        if(photo.length()!=0){

            for(int i =0;i<photos.length;i++){

                Log.i(TAG,"photos.length"+photos.length);

                urlList.add(photos[i]);
            }




        }




        if(photo.length()!=0){



            ngtl_service_log_photo.setUrlList(urlList);


        }

        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });



    }
}
