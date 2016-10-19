package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;

public class Details_Activity extends AppCompatActivity {

    public static final String TAG =" Details_Activity" ;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        System.out.println("onCreate");

        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php?act=userinfo&user_id=135");
//        requestParams.addBodyParameter("key", "linchom");
//       requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
//        requestParams.addBodyParameter("user_id", "135");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            private ImageView iv_photo;
            private TextView tv_sex;
            private View tv_email;
            private TextView tv_qq;
            private TextView tv_birthday;
            private TextView tv_user_name;
            private TextView tv_mobile_phone;

            @Override
            public void onSuccess(String result) {

                System.out.println("onsucess"+result);

                Gson gson = new Gson();
                UserInfoBean bean=gson.fromJson(result,UserInfoBean.class);

//                System.out.println("======"+bean);
//                System.out.println(bean.getResult());

                UserInfoBean.DataBean dataBean =bean.getData();

                TextView tv_mobile_phone = ((TextView) findViewById(R.id.tv_mobile_phone));
                tv_mobile_phone.setText(dataBean.getMobile_phone());

                TextView tv_user_name = ((TextView) findViewById(R.id.tv_user_name));
                tv_user_name.setText(dataBean.getUser_name());

                TextView tv_birthday = ((TextView) findViewById(R.id.tv_birthday));
                tv_birthday.setText(dataBean.getBirthday());

                TextView tv_qq = ((TextView) findViewById(R.id.tv_qq));
                tv_qq.setText(dataBean.getQq());

                TextView tv_email =(TextView) findViewById(R.id.tv_email);
                tv_email.setText(dataBean.getEmail());
               TextView tv_sex = ((TextView) findViewById(R.id.tv_add));
                tv_sex.setText(dataBean.getSex());
//                System.out.println("++++="+dataBean);



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG,ex+"");

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

