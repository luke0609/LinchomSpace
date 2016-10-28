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
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.pojo.GoodsCartBean;
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

    ArrayList<GoodsCartBean.Data> orderList;

    GoodsCommonAdapter<GoodsCartBean.Data> goodsCommonAdapter;
    private GoodsNoScrollListview lv_order_products;
    private TextView tv_goods_order_totalPrice;
    private ImageView titlebar_back;
    private Button btn_goods_order_submitorder;
    private ImageView iv_goods_order_area;
    private TextView tv_order_name;
    private TextView tv_order_phone;
    private TextView tv_order_detailAddress;

    private AreaListBean.Data areaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);


        Intent intent =getIntent();

        Bundle bundle =intent.getBundleExtra("bundle");

        orderList= (ArrayList<GoodsCartBean.Data>) bundle.getSerializable("orderList");

        areaList=  (AreaListBean.Data)bundle.getSerializable("areaList");

        if(areaList==null){



        }

        //goodsNum
       // goodsImg
        //goodsName
       // goodsPrice



        initView();

        initData();

        initEvent();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==2){

           Bundle bundle =data.getBundleExtra("bundle");

            String name = bundle.getString("name");
           String tel =  bundle.getString("tel");

            String address =  bundle.getString("address");

            Log.i(TAG,"name111"+name);

          // TextView aa=  ((TextView) findViewById(R.id.tv_goods_area_name));

           // aa.setText(name+"111");

            tv_order_name.setText(name+"");

            tv_order_phone.setText(tel+"");

            tv_order_detailAddress.setText(address+"");

        }


    }

    private void initView() {


        //tv_goods_area_name
        //tv_goods_area_tel
        //tv_goods_area_address

        tv_order_name = ((TextView) findViewById(R.id.tv_order_name));

        tv_order_phone = ((TextView) findViewById(R.id.tv_order_phone));

        tv_order_detailAddress = ((TextView) findViewById(R.id.tv_order_detailAddress));


        lv_order_products = ((GoodsNoScrollListview) findViewById(R.id.lv_order_products));

        tv_goods_order_totalPrice = ((TextView) findViewById(R.id.tv_goods_order_totalPrice));

        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));

        btn_goods_order_submitorder = ((Button) findViewById(R.id.btn_goods_order_submitorder));

        iv_goods_order_area = ((ImageView) findViewById(R.id.iv_goods_order_area));


    }

    private void initData() {

        tv_order_name.setText(areaList.consignee);
        tv_order_phone.setText(areaList.tel);
        tv_order_detailAddress.setText(areaList.address);



        countAllPrice();


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsCartBean.Data>(getApplicationContext(),orderList,R.layout.goods_order_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCartBean.Data data, int position) {

                ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);
                TextView tv_goods_order_goodsName=viewHolder.getViewById(R.id.tv_goods_order_goodsName);
                TextView tv_goods_order_price =viewHolder.getViewById(R.id.tv_goods_order_price);
                TextView tv_goods_order_goodsNum =viewHolder.getViewById(R.id.tv_goods_order_goodsNum);

                tv_goods_order_goodsName.setText(data.goods_name);

                tv_goods_order_price.setText(data.goods_price);
                tv_goods_order_goodsNum.setText(data.goods_number);


                String imgUrlChange = data.goods_img;

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

            int goodsNumber = Integer.parseInt(orderList.get(i).goods_number);

            Log.i(TAG,"goodsNumber"+goodsNumber);

            Double goodsMoney=Double.parseDouble(orderList.get(i).goods_price);
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


                startActivityForResult(intent,2);
            }
        });


    }


    public void dd(){



       /* JSONObject jo = new JSONObject();
        try {
            //jo.put("result","0");
            JSONArray ja = new JSONArray();


            OrderSubmitBean orderSubmitBean=null;

            JSONObject jo_sub = null;

            for(int i= 0;i<orderList.size();i++){

                jo_sub=new JSONObject();

                orderSubmitBean = orderList.get(i);



                jo_sub.put("rec_id",orderList.get(i).rec_id);
                jo_sub.put("user_id",orderList.get(i).user_id);
                jo_sub.put("goods_id",orderList.get(i).goods_id);
                jo_sub.put("goods_name",orderList.get(i).goods_name);
                jo_sub.put("goods_sn",orderList.get(i).goods_sn);
                jo_sub.put("goods_number",orderList.get(i).goods_number);
                jo_sub.put("market_price",orderList.get(i).market_price);
                jo_sub.put("goods_price",orderList.get(i).goods_price);
                jo_sub.put("goods_attr",orderList.get(i).goods_attr);
                jo_sub.put("is_real",orderList.get(i).is_real);
                jo_sub.put("extension_code",orderList.get(i).extension_code);
                jo_sub.put("parent_id",orderList.get(i).parent_id);
                jo_sub.put("is_gift",orderList.get(i).is_gift);
                jo_sub.put("is_shipping",orderList.get(i).is_shipping);
                jo_sub.put("subtotal",orderList.get(i).subtotal);
                jo_sub.put("formated_market_price",orderList.get(i).formated_market_price);
                jo_sub.put("formated_goods_price",orderList.get(i).formated_goods_price);
                jo_sub.put("formated_subtotal",orderList.get(i).formated_subtotal);

                ja.put(jo_sub);



            }

            jo.put("data",ja);



        } catch (JSONException e) {
            e.printStackTrace();
        }





        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","done_order");


        requestParams.addBodyParameter("user_id",userId);

        String dataJson = jo.toString();

        Log.i(TAG,"dataJson"+dataJson);

        requestParams.addBodyParameter("cart_goods",dataJson);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);


                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG,"ex"+ex);


                Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

*/






    }
}
