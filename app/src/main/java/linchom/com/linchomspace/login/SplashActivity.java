package linchom.com.linchomspace.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.login.contantData.Contant;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        boolean isLogin = shared_prefs.getBoolean("isLogin", false);
        if (isLogin) {
            Handler x = new Handler();
            x.postDelayed(new jumphandler(), 4000);
        }else {
            Handler x = new Handler();
            x.postDelayed(new splashhandler(), 4000);
        }
    }

    class jumphandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), GuideActivity.class));
            SplashActivity.this.finish();
        }
    }
}
