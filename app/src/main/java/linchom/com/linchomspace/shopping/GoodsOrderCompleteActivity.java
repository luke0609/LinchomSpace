package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;

public class GoodsOrderCompleteActivity extends AppCompatActivity {

    private ImageView titlebar_back;
    private TextView tv_order_complete_orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_complete);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");

        String orderId = bundle.getString("orderId");


        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


        tv_order_complete_orderId = ((TextView) findViewById(R.id.tv_order_complete_orderId));

        tv_order_complete_orderId.setText("智巢科技订单号:"+orderId);


        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
