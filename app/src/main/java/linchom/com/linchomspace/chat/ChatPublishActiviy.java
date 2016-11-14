package linchom.com.linchomspace.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.homepage.Activity.NavigationActivity;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.photoutil.UploadBean;
import me.iwf.photopicker.widget.MultiPickResultView;

import static android.R.attr.y;

public class ChatPublishActiviy extends AppCompatActivity implements View.OnClickListener{
    private Spinner sp_topic;
    private String cardNumber;
    private EditText et_title;
    private EditText add_content;
    Map<String,String> map = new HashMap<String,String>();
    ArrayList<String> list_photo;
    private TextView btn_publish;
    String value;
    String photo="";
    private ImageView iv_back;
    private MultiPickResultView recycler_view;
    private String photoAddress="";
    private int count=0;
    private String userId="";
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_publish_activiy);
        initView();
        StatusBarCompat.compat(this, Color.parseColor("#4EAFAB"));
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userId = shared_prefs.getString("userId","");
        username = shared_prefs.getString("username","");
    }
    private void initView() {

        map.put("智能家居","1");
        map.put("智能健康","2");
        map.put("智能管照","3");
        map.put("产品推荐","4");
        map.put("智能社区","5");
        map.put("工程技术","6");
        map.put("硬件开发","7");
        map.put("机器人","8");
        map.put("VR/AR","9");
        map.put("协议标准","10");
        map.put("需求对接","11");
        map.put("品牌交流","12");
        map.put("提问回答","13");
        map.put("其他","14");
        list_photo=new ArrayList<String>();
        recycler_view = ((MultiPickResultView) findViewById(R.id.recycler_view));
        recycler_view.init(this,MultiPickResultView.ACTION_SELECT,null);

        btn_publish = ((TextView) findViewById(R.id.btn_publish));
        btn_publish.setOnClickListener(this);
        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_back.setOnClickListener(this);
        sp_topic = ((Spinner) findViewById(R.id.sp_topic));
        et_title = ((EditText) findViewById(R.id.et_title));
        add_content = ((EditText) findViewById(R.id.add_content));
        final ArrayAdapter<CharSequence> adapterspinner1 = ArrayAdapter
                .createFromResource(this, R.array.Topic,
                        android.R.layout.simple_list_item_1);
        sp_topic.setAdapter(adapterspinner1);
        sp_topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardNumber = sp_topic.getSelectedItem().toString();
                System.out.println(cardNumber+"2");
                value = (String)map.get(cardNumber);
                System.out.println(cardNumber+"===="+value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cardNumber="智能家居";
                System.out.println(cardNumber+"3");
            }
        });




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
        String title = et_title.getText().toString().trim();

        String content = add_content.getText().toString().trim();

        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_topic");
        params.addQueryStringParameter("user_id",userId);
        params.addQueryStringParameter("topic_category",cardNumber);
        params.addQueryStringParameter("topic_category_id",value);
        params.addQueryStringParameter("topic_name",title);
        params.addQueryStringParameter("communication_title",content);
        params.addQueryStringParameter("user_name",username);
        params.addQueryStringParameter("photo",photoAddress);

        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Toast.makeText(ChatPublishActiviy.this, "发布成功", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),cardNumber,Toast.LENGTH_SHORT).show();
                if(cardNumber.equals("智能家居")){
                   topubchat("0");
                }
                if(cardNumber.equals("智能健康")){
                    topubchat("1");
                }
                if(cardNumber.equals("智能管照")){
                    topubchat("2");
                }
                if(cardNumber.equals("产品推荐")){
                    topubchat("3");
                }
                if(cardNumber.equals("智能社区")){
                    topubchat("4");
                }
                if(cardNumber.equals("工程技术")){
                    topubchat("5");
                }
                if(cardNumber.equals("硬件开发")){
                    topubchat("6");
                }
                if(cardNumber.equals("机器人")){
                    topubchat("7");
                }
                if(cardNumber.equals("VR/AR")){
                    topubchat("8");
                    Toast.makeText(ChatPublishActiviy.this, "=="+cardNumber, Toast.LENGTH_SHORT).show();
                }
                if(cardNumber.equals("协议标准")){
                    topubchat("9");
                }
                if(cardNumber.equals("需求对接")){
                    topubchat("10");
                }
                if(cardNumber.equals("品牌交流")){
                    topubchat("11");
                }
                if(cardNumber.equals("提问回答")){
                    topubchat("12");
                }
                if(cardNumber.equals("其他")){
                    topubchat("13");
                }
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

    private void topubchat(String pchatId) {
        Intent intent = new Intent(ChatPublishActiviy.this, MainActivity.class);
        intent.putExtra("idpchat", 1);
        intent.putExtra("pchatId",pchatId);
        //Toast.makeText(ChatPublishActiviy.this, pchatId, Toast.LENGTH_SHORT).show();
        startActivity(intent);
//        intent.putExtra("idchat", 1);
//        intent.putExtra("chatId",pchatId);
//        startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_publish:

                if(et_title.getText().toString().length()==0||add_content.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"请完善信息",Toast.LENGTH_SHORT).show();

                }else{


                    if(list_photo.isEmpty()){
                        doPublish();
                    }else {
                        for(int i=0; i<list_photo.size();i++){
                            photoUpload(i);


                        }

                    }




                }


                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recycler_view.onActivityResult(requestCode, resultCode, data);
        list_photo=recycler_view.getPhotos();
        //  recycler_view.showPics(list_photo);

        for (int i=0;i<list_photo.size();i++){
            System.out.println(list_photo.get(i));
        }

    }
}
