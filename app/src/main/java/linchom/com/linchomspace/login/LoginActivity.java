package linchom.com.linchomspace.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;

import linchom.com.linchomspace.homepage.Utils.ToastUtil;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.login.contantData.LoginResultBean;
import linchom.com.linchomspace.login.widget.ClearWriteEditText;




public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearWriteEditText mUserName, mPassWord;
    // 登录
    private Button mButtonLogin;
    // 忘记密码 注册 左侧title  又侧title
    private TextView mRegister;
    // 大 logo 图片  背景图片
    private ImageView mImgBackgroud;

    private String sUserName, sPassword;
    private TextView mForgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUserName = (ClearWriteEditText) findViewById(R.id.app_username_et);
        mPassWord = (ClearWriteEditText) findViewById(R.id.app_password_et);
        mButtonLogin = (Button) findViewById(R.id.app_sign_in_bt);
        mRegister = (TextView) findViewById(R.id.de_login_register);
        mImgBackgroud = (ImageView) findViewById(R.id.de_img_backgroud);
        mForgot = ((TextView) findViewById(R.id.de_login_forgot));

        mForgot.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mUserName.setOnClickListener(this);
        mPassWord.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                mImgBackgroud.startAnimation(animation);
            }
        }, 200);

    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_sign_in_bt:
                checkLogin();
                break;
            case R.id.de_login_forgot:
                Intent intent = new Intent(LoginActivity.this, RetrieveActivity.class);
                startActivity(intent);
                break;
            case R.id.de_login_register:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }




    private void checkLogin() {
        //获取用户输入的用户名密码
        sUserName = mUserName.getText().toString().trim();
        sPassword = mPassWord.getText().toString().trim();

        if (isEmpty(sUserName)) {
            showToast("请输入手机号");
            mUserName.setShakeAnimation();
            return;
        }
        System.out.println(isPhoneNumberValid(sUserName));

//        if (!isPhoneNumberValid(sUserName)){
//            showToast("请输入正确的手机号");
//            mUserName.setShakeAnimation();
//            return;
//        }

        if (isEmpty(sPassword)) {
            showToast("请输入密码");
            mPassWord.setShakeAnimation();
            return;
        }

//                if (!isPhoneNumberValid(sUserName)) {
//
//                    return;
//                }

        //正在登录
        //访问网络
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appuser.php?type=login");
        requestParams.addBodyParameter("key", "linchom");
        requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        requestParams.addBodyParameter("username",sUserName );
        requestParams.addBodyParameter("password", sPassword);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("22222"+result);
                Gson gson = new Gson();
                String userId = null;
                boolean isLogin = false;
                String msg = null;
                LoginResultBean bean = gson.fromJson(result, LoginResultBean.class);
                String loginResult = bean.getResult();
                if ("ok".equals(loginResult)) {                          //登录成功
                    isLogin = true;
                    userId = bean.getData();
                    //写入本地文件
                    SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared_prefs.edit();
                    System.out.println(userId+"   "+sUserName);
                    editor.putString("userId", userId);
                    editor.putBoolean("isLogin", isLogin);
                    editor.putString("username", sUserName);
                    editor.commit();
                    //提示
                   // Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    showToast("登录成功");
                    //跳转
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {                                             //登录失败
                  //  Toast.makeText(LoginActivity.this, "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
                        showToast("登录失败，账号或密码错误");
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//
//                Toast.makeText(LoginActivity.this, "登录失败，请检查网络", Toast.LENGTH_SHORT).show();
                showToast("登录失败，请检查网络");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 非空
     * @param str
     * @return
     */
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
    }
