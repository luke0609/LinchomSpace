package linchom.com.linchomspace.shopping;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;

public class GoodsVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_video);
        StatusBarCompat.compat(this, Color.parseColor("#4EAFAB"));

    }
}
