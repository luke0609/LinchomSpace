package linchom.com.linchomspace.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Utils.ToastUtil;
import linchom.com.linchomspace.login.contantData.CodeBean;
import linchom.com.linchomspace.login.contantData.LoginResultBean;
import linchom.com.linchomspace.login.widget.ClearWriteEditText;

import static linchom.com.linchomspace.R.id.app_password1;
import static linchom.com.linchomspace.R.id.app_password2;
import static linchom.com.linchomspace.R.id.app_regist_bt;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ClearWriteEditText app_phoneNum;
    private ClearWriteEditText app_password1,app_password2;
    private Button btn_post,app_regist_bt;
    String phoneNum="";
    private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        app_phoneNum = ((ClearWriteEditText) findViewById(R.id.app_phoneNum));
        app_password1 = ((ClearWriteEditText) findViewById(R.id.app_password1));
        app_password2 = ((ClearWriteEditText) findViewById(R.id.app_password2));
        btn_post = ((Button) findViewById(R.id.btn_post));
        app_regist_bt=((Button) findViewById(R.id.app_regist_bt));
        btn_post.setOnClickListener(this);
        app_regist_bt.setOnClickListener(this);
    }



    private void showToast(String msg){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_style,
                (ViewGroup) findViewById(R.id.ll_toast));
        ImageView image = (ImageView) layout
                .findViewById(R.id.iv_toast_collect);
        image.setImageResource(R.drawable.collect_success1);
        TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
        text.setText(msg);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        ToastUtil.showMyToast(toast, 1000);
    }

    @Override

        public void onClick(View v){
            switch (v.getId()) {
                case R.id.btn_post:
                    getkey();
                    break;
                case R.id.app_regist_bt:


                    break;
            }
        }

    private void getkey() {
      phoneNum = app_phoneNum.getText().toString().trim();


        if (isEmpty(phoneNum)) {
            showToast("手机号不能为空");
            app_phoneNum.setShakeAnimation();
            return;
        }


        if (!isPhoneNumberValid(phoneNum)){
            showToast("请输入正确的手机号");
            app_phoneNum.setShakeAnimation();
            return;
        }
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php");
        requestParams.addBodyParameter("act","sendcode");
        requestParams.addBodyParameter("mobile",phoneNum);
        System.out.println(requestParams);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                CodeBean bean = gson.fromJson(result, CodeBean.class);
                flag=bean.isData();
                btn_post.setText("发送成功");
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




    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 手机号
     *
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        CharSequence inputStr = phoneNumber;
        //正则表达式

        String phone="^1[34578]\\d{9}$" ;


        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(inputStr);


        if(matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}

