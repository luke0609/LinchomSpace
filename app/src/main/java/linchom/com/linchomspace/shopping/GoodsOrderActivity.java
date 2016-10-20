package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;

public class GoodsOrderActivity extends AppCompatActivity {

    private static final String TAG = "GoodsOrderActivity";
    private String goodsNum;
    private String goodsImg;
    private String goodsName;
    private String goodsPrice;


    private Double totalPrice=0.0;

    ArrayList<GoodsOrderBean> orderList;

    GoodsCommonAdapter<GoodsOrderBean> goodsCommonAdapter;
    private GoodsNoScrollListview lv_order_products;
    private TextView tv_goods_order_totalPrice;
    private ImageView titlebar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);

        Intent intent =getIntent();

        Bundle bundle =intent.getBundleExtra("bundle");

        orderList= (ArrayList<GoodsOrderBean>) bundle.getSerializable("orderList");

        //goodsNum
       // goodsImg
        //goodsName
       // goodsPrice










        initView();

        initData();

        initEvent();


    }

    private void initView() {

        lv_order_products = ((GoodsNoScrollListview) findViewById(R.id.lv_order_products));

        tv_goods_order_totalPrice = ((TextView) findViewById(R.id.tv_goods_order_totalPrice));

        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


    }

    private void initData() {

        countAllPrice();


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsOrderBean>(getApplicationContext(),orderList,R.layout.goods_order_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsOrderBean goodsOrderBean, int position) {

                ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);
                TextView tv_goods_order_goodsName=viewHolder.getViewById(R.id.tv_goods_order_goodsName);
                TextView tv_goods_order_price =viewHolder.getViewById(R.id.tv_goods_order_price);
                TextView tv_goods_order_goodsNum =viewHolder.getViewById(R.id.tv_goods_order_goodsNum);

                tv_goods_order_goodsName.setText(goodsOrderBean.goodsName);

                tv_goods_order_price.setText(goodsOrderBean.goodsPrice);
                tv_goods_order_goodsNum.setText(goodsOrderBean.goodsNum);

                GoodsXUtilsImage.display(iv_goods_order_img,goodsOrderBean.goodsImg);



            }
        };

        lv_order_products.setAdapter(goodsCommonAdapter);


        tv_goods_order_totalPrice.setText("合计:"+totalPrice+"元");





    }

    private void countAllPrice() {

        for(int i=0;i<orderList.size();i++){

            int goodsNumber = Integer.parseInt(orderList.get(i).goodsNum);

            Log.i(TAG,"goodsNumber"+goodsNumber);

            Double goodsMoney=Double.parseDouble(orderList.get(i).goodsPrice);
            Log.i(TAG,"goodsMoney"+goodsMoney);

            totalPrice +=(goodsNumber*goodsMoney);




        }



    }

    private void initEvent() {

        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
