package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;

public class Details_Activity extends AppCompatActivity {

    UserInfoBean.DataBean userInfoBean;
    public static final String TAG = " Details_Activity";


    private ImageView iv_back;
    private ImageView iv_user_photo;
    private TextView tv_ed;
    private TextView tv_user_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private TextView tv_email;
    private TextView tv_office_phone;
    private TextView tv_home_phone;
    private TextView tv_mobile_phone;
    private  TextView tv_name;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("userInfoBean111" + userInfoBean);
        if (requestCode == 2 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            userInfoBean = (UserInfoBean.DataBean) bundle.getSerializable("user");
            System.out.println("userInfoBean" + userInfoBean);
            tv_name.setText(userInfoBean.getName());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        System.out.println("onCreate");


        tv_user_name = ((TextView) findViewById(R.id.tv_user_name));
        tv_sex = ((TextView) findViewById(R.id.tv_add));
        tv_birthday = ((TextView) findViewById(R.id.tv_birthday));
        tv_email = ((TextView) findViewById(R.id.tv_email));
        tv_name = (TextView) findViewById(R.id.tv_name);
        iv_user_photo = ((ImageView) findViewById(R.id.iv_user_photo));
        tv_office_phone = ((TextView) findViewById(R.id.tv_office_phone));
        tv_mobile_phone = ((TextView) findViewById(R.id.tv_mobile_phone));
        tv_home_phone = ((TextView) findViewById(R.id.tv_home_phone));

        tv_ed = ((TextView) findViewById(R.id.tv_ed));

        tv_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HeadImage_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", userInfoBean);
                System.out.println(userInfoBean);
                intent.putExtras(bundle);
                startActivityForResult(intent,2);
            }
        });



        initData();

    }



    public void getData(){
        String name=tv_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday=tv_birthday.getText().toString();
        String email=tv_email.getText().toString();
        String office_phone=tv_office_phone.getText().toString();
        String home_office=tv_home_phone.getText().toString();
        String mobile_phone=tv_mobile_phone.getText().toString();
        userInfoBean=new UserInfoBean.DataBean(name,email,sex,birthday,office_phone,home_office,mobile_phone);
    }

    public void initData() {

        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=userinfo&user_id=135");
//        requestParams.addBodyParameter("key", "linchom");
//       requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
//        requestParams.addBodyParameter("user_id", "135");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
//                System.out.println("onsucess" + result);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
//                System.out.println("======"+bean);
//                System.out.println(bean.getResult());
                userInfoBean = bean.getData();
//                System.out.println("======" + userInfoBean);
                x.image().bind(iv_user_photo,userInfoBean.getUser_photo());
                tv_mobile_phone.setText(userInfoBean.getMobile_phone());
                tv_name.setText(userInfoBean.getName());
                tv_birthday.setText(userInfoBean.getBirthday());
                tv_email.setText(userInfoBean.getEmail());
                tv_office_phone.setText(userInfoBean.getOffice_phone());
                tv_home_phone.setText(userInfoBean.getHome_phone());
                tv_sex.setText((userInfoBean.getSex().equals("1")) ? "男" : "女");

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG, ex + "");

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