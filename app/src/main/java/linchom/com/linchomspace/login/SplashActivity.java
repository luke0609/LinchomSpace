package linchom.com.linchomspace.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import linchom.com.linchomspace.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler x = new Handler();
        x.postDelayed(new splashhandler(), 10000);

    }

    class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), GuideActivity.class));
            SplashActivity.this.finish();
        }
    }
}
