package linchom.com.linchomspace.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.photoutil.UploadBean;
import me.iwf.photopicker.widget.MultiPickResultView;

public class FeedBack_activity extends AppCompatActivity{
    private ImageView iv_fkback;
    private TextView b_ok;
    private EditText et_dis;
//    private ImageView iv_post;
    ArrayList<String> list_photo;
    private MultiPickResultView post_view;
    private String photoAddress="";
    private int count=0;
    String photo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity);

        et_dis = ((EditText) findViewById(R.id.et_dis));

//        iv_post = ((ImageView) findViewById(R.id.iv_post));
        b_ok = ((TextView) findViewById(R.id.b_ok));

        post_view = ((MultiPickResultView) findViewById(R.id.post_view));
        post_view.init(this,MultiPickResultView.ACTION_SELECT,null);

//        iv_post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialog();
//            }
//        });
//    }
//    public void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setMessage("");
//        builder.setTitle("提示");
//        builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
//
////            }
//        });
//
//        builder.setNegativeButton("从相册选择照片", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        builder.show();


        b_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_photo.size()==0){
                        post();
                    }else {
                        for (int i = 0; i < list_photo.size(); i++) {
                            photoUpload(i);


                        }
                }
            }
        });
        iv_fkback = ((ImageView) findViewById(R.id.iv_fkback));
        iv_fkback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    private void photoUpload(int i){
//        System.out.println(list_photo.get(i));

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
                    post();
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
    public void post(){
        final String msg=et_dis.getText().toString();

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=user_add_customer&user_id=135&content=321321");
        requestParams.addQueryStringParameter("msg",msg);
        requestParams.addQueryStringParameter("photo",photoAddress);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

//                        System.out.println("onsucess"+result);
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

        Toast.makeText(getApplicationContext(),"反馈提交成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        post_view.onActivityResult(requestCode, resultCode, data);
        list_photo = post_view.getPhotos();
        //  recycler_view.showPics(list_photo);

        for (int i = 0; i < list_photo.size(); i++) {
            System.out.println(list_photo.get(i));
        }
    }

    }
