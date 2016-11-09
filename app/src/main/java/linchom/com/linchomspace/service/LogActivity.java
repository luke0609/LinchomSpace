package linchom.com.linchomspace.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.ChatPublishActiviy;
import linchom.com.linchomspace.photoutil.UploadBean;
import me.iwf.photopicker.widget.MultiPickResultView;

import static linchom.com.linchomspace.R.id.add_content;
import static linchom.com.linchomspace.R.id.et_address_ex;
import static linchom.com.linchomspace.R.id.et_phonenum;
import static linchom.com.linchomspace.R.id.et_title;
import static linchom.com.linchomspace.R.id.recycler_view;

public class LogActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(et_title)
    EditText etTitle;
    @InjectView(add_content)
    EditText addContent;
    @InjectView(recycler_view)
    MultiPickResultView recyclerView;
    ArrayList<String> list_photo;
    private String photoAddress="";
    private int count=0;
    String photo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.inject(this);
        recyclerView.init(this,MultiPickResultView.ACTION_SELECT,null);
    }

    @OnClick({R.id.iv_back, R.id.btn_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_publish:
                if (etTitle.getText().toString().length() == 0
                        || addContent.getText().toString().length() == 0)
                         {
                            Toast.makeText(getApplicationContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                             break;
                             }
                else {
                    if(list_photo.isEmpty()){
                        doPublish();
                    }else {
                        for(int i=0; i<list_photo.size();i++){
                            photoUpload(i);
                        }
                    }
                }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recyclerView.onActivityResult(requestCode, resultCode, data);
        list_photo=recyclerView.getPhotos();
        //  recycler_view.showPics(list_photo);

        for (int i=0;i<list_photo.size();i++){
            System.out.println(list_photo.get(i));
        }

    }
    private void photoUpload(int i){
        System.out.println(list_photo.get(i));

        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");

        params.addBodyParameter("act", "uploadimage");

        params.addBodyParameter("goods_img",new File(list_photo.get(i)));
        System.out.println(params);
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                UploadBean bean = gson.fromJson(result, UploadBean.class);
                photo=bean.getData();

                photoAddress=photoAddress+photo+",";
                count++;
                System.out.println(photoAddress);
                System.out.println(count);
                if (count==list_photo.size()){
                    System.out.println("xxxxxxx");
                    doPublish();
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
    private void doPublish() {
        String title = etTitle.getText().toString().trim();

        String content = addContent.getText().toString().trim();
        if("".equals(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if("".equals(content)){
            Toast.makeText(this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_demand_services_log");
        params.addQueryStringParameter("user_id", 135+ "");
        params.addQueryStringParameter("title",title);
        params.addQueryStringParameter("content",content);
        params.addQueryStringParameter("photo",photoAddress);

        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Toast.makeText(LogActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
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
}
