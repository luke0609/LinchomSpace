package linchom.com.linchomspace.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import linchom.com.linchomspace.R;

public class ChatPublishActiviy extends AppCompatActivity implements View.OnClickListener{
    private Spinner sp_topic;
    private String cardNumber;
    private EditText et_title;
    private EditText add_content;
    Map<String,String> map = new HashMap<String,String>();

    private Button btn_publish;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_publish_activiy);
        initView();

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

        btn_publish = ((Button) findViewById(R.id.btn_publish));
        btn_publish.setOnClickListener(this);
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

    private void doPublish() {
        String title = et_title.getText().toString();
        String content = add_content.getText().toString();
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_topic");
        params.addQueryStringParameter("user_id", 129 + "");
        params.addQueryStringParameter("topic_category",cardNumber);
        params.addQueryStringParameter("topic_category_id",value);
        params.addQueryStringParameter("topic_name",content);
        params.addQueryStringParameter("communication_title",title);
        params.addQueryStringParameter("user_name","ljs123");
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                System.out.println(result);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_publish:
                doPublish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
