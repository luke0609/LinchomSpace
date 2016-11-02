package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;

public class HeadImage_activity extends AppCompatActivity {

    public static final String TAG =" Details_Activity" ;
    private ImageView iv_back1;
    private EditText tv_user_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private EditText tv_email;
    private EditText tv_office_phone;
    private EditText tv_home_office;
    private ImageView iv_photo;
    private EditText tv_mobile_phone;
    private TextView tv_ok;
    private RelativeLayout tv_add;
    UserInfoBean.DataBean dataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image_activity);

        iv_photo = ((ImageView) findViewById(R.id.iv_photo));
        tv_user_name = ((EditText)findViewById(R.id.tv_user_name));
        tv_sex = ((TextView) findViewById(R.id.tv_add));
        tv_birthday = ((TextView) findViewById(R.id.tv_birthday1));
        tv_email = ((EditText) findViewById(R.id.tv_email));
        tv_office_phone = ((EditText) findViewById(R.id.tv_office_phone));
        tv_home_office = ((EditText) findViewById(R.id.tv_home_phone));
        tv_mobile_phone = ((EditText) findViewById(R.id.tv_mobile_phone));

        tv_add = ((RelativeLayout) findViewById(R.id.rl_add));

//        System.out.println("onCreate");

        iv_back1 = ((ImageView) findViewById(R.id.iv_back1));
        iv_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                System.out.println("onCreatefinish");
            }
        });

        tv_ok = ((TextView) findViewById(R.id.tv_ok));
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Details_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",dataBean);
                intent.putExtras(bundle);
//                startActivityForResult(intent,66);     //标识
            }
        });

        initEvent();
        //upData();
        getData();
    }

    public void getData(){
        Intent intent=getIntent();
        dataBean= (UserInfoBean.DataBean) intent.getSerializableExtra("user");
        tv_user_name.setText(dataBean.getUser_name());
        tv_sex.setText(dataBean.getSex());
        tv_birthday.setText(dataBean.getBirthday());
        tv_email.setText(dataBean.getEmail());
        tv_home_office.setText(dataBean.getHome_phone());
        tv_office_phone.setText(dataBean.getOffice_phone());
        tv_mobile_phone.setText(dataBean.getMobile_phone());
    }

    private void initEvent() {

    }


    public void upData(){

        String username= tv_user_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday=tv_birthday.getText().toString();
        String[] birthdaies = birthday.split("-");
        String email=tv_email.getText().toString();
//         UserInfoBean.DataBean mUser=new UserInfoBean.DataBean(username,sex,birthday,email);
//        Gson gson=new Gson();
////      String userJson=gson.toJson(mUser);

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=edituserinfo&user_id=135");

        //requestParams.addBodyParameter("user_name","张晓文");
        //requestParams.addQueryStringParameter("mUser",userJson);

        requestParams.addQueryStringParameter("email",email);
        requestParams.addQueryStringParameter("user_name",username);
        requestParams.addQueryStringParameter("sex",sex);
        requestParams.addQueryStringParameter("birthdayYear",birthdaies[0]);
        requestParams.addQueryStringParameter("birthdayMonth",birthdaies[1]);
        requestParams.addQueryStringParameter("birthdayDay",birthdaies[2]);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                System.out.println("6666666666666666"+result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

//                Log.e("error",ex.getMessage().toString());
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    Log.e("网络错误",ex.getMessage().toString());
//                    // ...
//                } else { // 其他错误
//
//                    Log.e("其他错误",ex.getMessage().toString());
//                    // ...
//                }
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
