package linchom.com.linchomspace.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.login.contantData.LoginResultBean;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.et_userName)
    EditText etUserName;
    @InjectView(R.id.et_userPsd)
    EditText etUserPsd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.fastregister)
    TextView fastregister;
    @InjectView(R.id.findpsd)
    TextView findpsd;
    @InjectView(R.id.iv_qq)
    ImageView ivQq;
    @InjectView(R.id.iv_weibo)
    ImageView ivWeibo;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_login, R.id.fastregister, R.id.findpsd, R.id.iv_qq, R.id.iv_weibo})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                checkLogin();
                btnLogin.setClickable(false);
                break;
            case R.id.fastregister:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.findpsd:
                Intent intentRetrieve = new Intent(LoginActivity.this, RetrieveActivity.class);
                startActivity(intentRetrieve);
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
        }
    }

    private void checkLogin() {
        progressBar.setVisibility(View.VISIBLE);
        //获取用户输入的用户名密码
        final String username = etUserName.getText().toString().trim();
        final String password = etUserPsd.getText().toString().trim();

        //访问网络
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appuser.php?type=login");
        requestParams.addBodyParameter("key", "linchom");
        requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        requestParams.addBodyParameter("username", username);
        requestParams.addBodyParameter("password", password);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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
                    editor.putString("userId", userId);
                    editor.putBoolean("isLogin", isLogin);
                    editor.putString("username", username);
                    editor.commit();
                    //提示
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //跳转
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {                                             //登录失败
                    msg = bean.getMsg();
                    Toast.makeText(LoginActivity.this, "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
                    btnLogin.setClickable(true);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "登录失败，请检查网络", Toast.LENGTH_SHORT).show();
                btnLogin.setClickable(true);

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
