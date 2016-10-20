package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private String goodsNum;
    private String goodsImg;
    private String goodsName;
    private String goodsPrice;

    ArrayList<GoodsOrderBean> orderList;

    GoodsCommonAdapter<GoodsOrderBean> goodsCommonAdapter;
    private GoodsNoScrollListview lv_order_products;


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

    }

    private void initData() {
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




    }

    private void initEvent() {


    }
}
