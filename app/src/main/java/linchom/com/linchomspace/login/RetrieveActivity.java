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
import linchom.com.linchomspace.login.contantData.ErrBean;
import linchom.com.linchomspace.login.contantData.RegisterResultBean;
import linchom.com.linchomspace.login.widget.ClearWriteEditText;

import static linchom.com.linchomspace.R.drawable.back;

public class RetrieveActivity extends AppCompatActivity implements View.OnClickListener{
    private ClearWriteEditText app_phoneNum;
    private ClearWriteEditText app_password1,app_password2,app_code;
    private Button btn_post,app_regist_bt;
    String phoneNum="";
    String psd1="";
    String psd2="";
    String code="";
    private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        initView();

    }
    private void initView() {
        app_phoneNum = ((ClearWriteEditText) findViewById(R.id.app_phoneNum));
        app_password1 = ((ClearWriteEditText) findViewById(R.id.app_password1));
        app_password2 = ((ClearWriteEditText) findViewById(R.id.app_password2));
        app_code=((ClearWriteEditText) findViewById(R.id.app_code));
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
                doRegisterActivity();

                break;
        }
    }

    private void doRegisterActivity() {

        phoneNum = app_phoneNum.getText().toString().trim();
        code=app_code.getText().toString().trim();
        psd1=app_password1.getText().toString().trim();
        psd2=app_password2.getText().toString().trim();
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
        if(isEmpty(code)){
            showToast("验证码不能为空");
            app_code.setShakeAnimation();
            return;
        }
        if(isEmpty(psd1)){
            showToast("密码不能为空");
            app_password1.setShakeAnimation();
            return;
        }
        if(psd1.length()<6) {
            showToast("密码不能小于6位");
            app_password1.setShakeAnimation();
            return;
        }
        if(isEmpty(psd2)){
            showToast("请确认密码");
            app_password2.setShakeAnimation();
            return;
        }
        if(!psd1.equals(psd2)){
            showToast("请确认两次输入的密码相同");
            app_password2.setShakeAnimation();
            return;
        }
        app_regist_bt.setClickable(false);
        // user_name=18500551431&password=123456&code=801312
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php");
        requestParams.addBodyParameter("act","get_passwd");
        requestParams.addBodyParameter("user_name",phoneNum);
        requestParams.addBodyParameter("password",psd1);
        requestParams.addBodyParameter("beginpassword",psd2);
        requestParams.addBodyParameter("code",code);
        System.out.println(requestParams);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                RegisterResultBean bean = gson.fromJson(result, RegisterResultBean.class);

                if("0".equals(bean.getResult())){
                    showToast("密码修改成功");
                    finish();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showToast("请检查网络");
                btn_post.setClickable(true);
                app_regist_bt.setClickable(true);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
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
        btn_post.setClickable(false);
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php");
        requestParams.addBodyParameter("act","sendcode");
        requestParams.addBodyParameter("mobile",phoneNum);
        requestParams.addBodyParameter("type","1");
        System.out.println(requestParams);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                CodeBean bean = gson.fromJson(result, CodeBean.class);
                flag=bean.isData();
                if(flag){
                    showToast("发送成功");
                    btn_post.setText("已发送");

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showToast("短信发送失败");
                btn_post.setClickable(true);

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
