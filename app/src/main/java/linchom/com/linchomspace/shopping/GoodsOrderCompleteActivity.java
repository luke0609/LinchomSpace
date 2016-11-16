package linchom.com.linchomspace.shopping;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;

public class GoodsOrderCompleteActivity extends AppCompatActivity {

    private ImageView titlebar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_complete);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));


        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
