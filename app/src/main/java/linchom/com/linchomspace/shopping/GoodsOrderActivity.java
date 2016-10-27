package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
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

    private String userId="12";


    private Double totalPrice=0.0;

    ArrayList<GoodsOrderBean> orderList;

    GoodsCommonAdapter<GoodsOrderBean> goodsCommonAdapter;
    private GoodsNoScrollListview lv_order_products;
    private TextView tv_goods_order_totalPrice;
    private ImageView titlebar_back;
    private Button btn_goods_order_submitorder;
    private ImageView iv_goods_order_area;


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

        btn_goods_order_submitorder = ((Button) findViewById(R.id.btn_goods_order_submitorder));

        iv_goods_order_area = ((ImageView) findViewById(R.id.iv_goods_order_area));


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


                String imgUrlChange = goodsOrderBean.goodsImg;
                Log.i(TAG,"imgUrlChange.substring(0,1)"+imgUrlChange.substring(0,1));

                if("h".equals(imgUrlChange.substring(0,1))){

                }else{
                    imgUrlChange= GoodsHttpUtils.IMGURL+imgUrlChange;
                }

                Log.i(TAG,"imgUrlChange"+imgUrlChange);

                GoodsXUtilsImage.display(iv_goods_order_img,imgUrlChange);



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

        btn_goods_order_submitorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // JsonObject
              /*  JSONObject jo = new JSONObject();
                try {
                    jo.put("result","0");
                    JSONArray ja = new JSONArray();

                    GoodsOrderBean goodsOrderBean=null;

                    JSONObject jo_sub = null;

                    for(int i= 0;i<orderList.size();i++){

                        jo_sub=new JSONObject();

                        goodsOrderBean = orderList.get(i);

                        //rec_id;
                        //user_id;
                        //goods_id;
                        //goods_name;
                        //goods_sn;
                        //goods_number;
                        //market_price;
                        //goods_price;
                        //goods_attr;
                        //is_real;
                        //extension_code;
                        //parent_id;
                        //is_gift;
                        //is_shipping;
                        //subtotal;
                        //formated_market_price;
                        //formated_goods_price;
                        //formated_subtotal;

                        jo_sub.put("rec_id","");
                        jo_sub.put("user_id","");
                        jo_sub.put("goods_id","");
                        jo_sub.put("goods_name","");
                        jo_sub.put("goods_sn","");
                        jo_sub.put("goods_number","");
                        jo_sub.put("market_price","");
                        jo_sub.put("goods_price","");
                        jo_sub.put("goods_attr","");
                        jo_sub.put("is_real","");
                        jo_sub.put("extension_code","");
                        jo_sub.put("parent_id","");
                        jo_sub.put("is_gift","");
                        jo_sub.put("is_shipping","");
                        jo_sub.put("subtotal","");
                        jo_sub.put("formated_market_price","");
                        jo_sub.put("formated_goods_price","");
                        jo_sub.put("formated_subtotal","");




                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }


                RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

                requestParams.addBodyParameter("act","done_order");


                requestParams.addBodyParameter("user_id",userId);


              //  requestParams.addBodyParameter("cart_goods",);*/

                Intent intent =new Intent(getApplicationContext(),GoodsAllOrderActivity.class);

                startActivity(intent);


            }
        });

        iv_goods_order_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoodsOrderActivity.this,GoodsAreaActivity.class);


                startActivity(intent);
            }
        });


    }
}
