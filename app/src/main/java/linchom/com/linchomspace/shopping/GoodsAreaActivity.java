package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import linchom.com.linchomspace.R;

public class GoodsAreaActivity extends AppCompatActivity {

    private Button btn_goods_area_addaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_area);

        initView();


        initData();

        initEvent();
    }

    private void initView() {
        btn_goods_area_addaddress = ((Button) findViewById(R.id.btn_goods_area_addaddress));


    }

    private void initData() {


    }

    private void initEvent() {
        btn_goods_area_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoodsAreaActivity.this,AddaddressActivity.class);

                startActivity(intent);



            }
        });


    }
}
