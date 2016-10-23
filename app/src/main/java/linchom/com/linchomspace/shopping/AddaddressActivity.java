package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import linchom.com.linchomspace.R;

public class AddaddressActivity extends AppCompatActivity {

    private TextView tv_goods_addaddress_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        tv_goods_addaddress_city = ((TextView) findViewById(R.id.tv_goods_addaddress_city));

    }

    private void initData() {


    }

    private void initEvent() {
        tv_goods_addaddress_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

    }
}
