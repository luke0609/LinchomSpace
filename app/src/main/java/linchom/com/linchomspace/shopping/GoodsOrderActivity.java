package linchom.com.linchomspace.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.pojo.GoodsCartBean;
import linchom.com.linchomspace.shopping.pojo.OrderSubmitBean;
import linchom.com.linchomspace.shopping.pojo.OrderSuccessBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;

public class GoodsOrderActivity extends AppCompatActivity {

    private static final String TAG = "GoodsOrderActivity";
    private String goodsNum;
    private String goodsImg;
    private String goodsName;
    private String goodsPrice;

    private String userId;


    private Double totalPrice=0.0;

    ArrayList<GoodsCartBean.Data> orderList;

    GoodsCommonAdapter<GoodsCartBean.Data> goodsCommonAdapter;

    GoodsCommonAdapter<OrderSubmitBean> cartCommonAdapter;

    private GoodsNoScrollListview lv_order_products;
    private TextView tv_goods_order_totalPrice;
    private ImageView titlebar_back;
    private Button btn_goods_order_submitorder;
    private ImageView iv_goods_order_area;
    private TextView tv_order_name;
    private TextView tv_order_phone;
    private TextView tv_order_detailAddress;

    private AreaListBean.Data areaList;


    private ArrayList<OrderSubmitBean> orderCartList;

    private boolean areaFlag=false;
    private RelativeLayout rl_goods_order_pro;

    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);


        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userName = shared_prefs.getString("username","");

        userId = shared_prefs.getString("userId","");


        Intent intent =getIntent();

        Bundle bundle =intent.getBundleExtra("bundle");

        orderList= (ArrayList<GoodsCartBean.Data>) bundle.getSerializable("orderList");

        orderCartList = (ArrayList<OrderSubmitBean>)bundle.getSerializable("orderCartList");

        areaList=  (AreaListBean.Data)bundle.getSerializable("areaList");
        Log.i(TAG,"orderCartList"+orderCartList);

        Log.i(TAG,"areaLsit"+areaList);

        if(areaList==null){

            areaFlag=true;

        }

        //goodsNum
        //goodsImg
        //goodsName
        //goodsPrice



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

            areaFlag=false;


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

        rl_goods_order_pro = ((RelativeLayout) findViewById(R.id.rl_goods_order_pro));


    }

    private void initData() {

        if(areaFlag==false){

            tv_order_name.setText(areaList.consignee);
            tv_order_phone.setText(areaList.tel);
            tv_order_detailAddress.setText(areaList.address);


        }



        if(orderList!=null&&orderCartList==null) {


            countAllPrice();


            goodsCommonAdapter = new GoodsCommonAdapter<GoodsCartBean.Data>(getApplicationContext(), orderList, R.layout.goods_order_list_item) {
                @Override
                public void convert(GoodsViewHolder viewHolder, GoodsCartBean.Data data, int position) {

                    ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);
                    TextView tv_goods_order_goodsName = viewHolder.getViewById(R.id.tv_goods_order_goodsName);
                    TextView tv_goods_order_price = viewHolder.getViewById(R.id.tv_goods_order_price);
                    TextView tv_goods_order_goodsNum = viewHolder.getViewById(R.id.tv_goods_order_goodsNum);

                    tv_goods_order_goodsName.setText(data.goods_name);

                    tv_goods_order_price.setText(data.goods_price);
                    tv_goods_order_goodsNum.setText(data.goods_number);


                    String imgUrlChange = data.goods_img;

                    Log.i(TAG, "imgUrlChange.substring(0,1)" + imgUrlChange.substring(0, 1));

                    if ("h".equals(imgUrlChange.substring(0, 1))) {

                    } else {
                        imgUrlChange = GoodsHttpUtils.IMGURL + imgUrlChange;
                    }

                    Log.i(TAG, "imgUrlChange" + imgUrlChange);

                    GoodsXUtilsImage.display(iv_goods_order_img, imgUrlChange);


                }
            };

            lv_order_products.setAdapter(goodsCommonAdapter);


            tv_goods_order_totalPrice.setText("合计:" + totalPrice + "元");


        }else if(orderList==null&&orderCartList!=null){


            Toast.makeText(getApplicationContext(),"购物车中内容",Toast.LENGTH_SHORT).show();



            countCartAllPrice();


            cartCommonAdapter = new GoodsCommonAdapter<OrderSubmitBean>(getApplicationContext(), orderCartList, R.layout.goods_order_list_item) {
                @Override
                public void convert(GoodsViewHolder viewHolder, OrderSubmitBean data, int position) {

                    ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);
                    TextView tv_goods_order_goodsName = viewHolder.getViewById(R.id.tv_goods_order_goodsName);
                    TextView tv_goods_order_price = viewHolder.getViewById(R.id.tv_goods_order_price);
                    TextView tv_goods_order_goodsNum = viewHolder.getViewById(R.id.tv_goods_order_goodsNum);

                    tv_goods_order_goodsName.setText(data.goods_name);

                    tv_goods_order_price.setText(data.goods_price);
                    tv_goods_order_goodsNum.setText(data.goods_number);


                    String imgUrlChange = data.goods_img;

                    if ("h".equals(imgUrlChange.substring(0, 1))) {

                    } else {
                        imgUrlChange = GoodsHttpUtils.IMGURL + imgUrlChange;
                    }


                    GoodsXUtilsImage.display(iv_goods_order_img, imgUrlChange);


                }
            };

            lv_order_products.setAdapter(cartCommonAdapter);


            tv_goods_order_totalPrice.setText("合计:" + totalPrice + "元");





        }





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


    private void countCartAllPrice(){


        for(int i=0;i<orderCartList.size();i++){

            int goodsNumber = Integer.parseInt(orderCartList.get(i).goods_number);

            Log.i(TAG,"goodsNumber"+goodsNumber);

            Double goodsMoney=Double.parseDouble(orderCartList.get(i).goods_price);
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

                rl_goods_order_pro.setVisibility(View.VISIBLE);

                btn_goods_order_submitorder.setEnabled(false);

                btn_goods_order_submitorder.setText("提交中");


                if(orderList!=null&&orderCartList==null){




                    JSONObject jo = new JSONObject();
                    try {
                        //jo.put("result","0");
                        JSONArray ja = new JSONArray();


                        //OrderSubmitBean orderSubmitBean=null;

                        JSONObject jo_sub = null;

                        for(int i= 0;i<orderList.size();i++){

                            jo_sub=new JSONObject();

                         //   orderSubmitBean = orderCartList.get(i);







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


                    submitOrderForm(jo.toString());




                }else if(orderList==null&&orderCartList!=null){


                    JSONObject jo = new JSONObject();
                    try {
                        //jo.put("result","0");
                        JSONArray ja = new JSONArray();


                        OrderSubmitBean orderSubmitBean=null;

                        JSONObject jo_sub = null;

                        for(int i= 0;i<orderCartList.size();i++){

                            jo_sub=new JSONObject();

                            orderSubmitBean = orderCartList.get(i);





                            jo_sub.put("rec_id",orderSubmitBean.rec_id);
                            jo_sub.put("user_id",orderSubmitBean.user_id);
                            jo_sub.put("goods_id",orderSubmitBean.goods_id);
                            jo_sub.put("goods_name",orderSubmitBean.goods_name);
                            jo_sub.put("goods_sn",orderSubmitBean.goods_sn);
                            jo_sub.put("goods_number",orderSubmitBean.goods_number);
                            jo_sub.put("market_price",orderSubmitBean.market_price);
                            jo_sub.put("goods_price",orderSubmitBean.goods_price);
                            jo_sub.put("goods_attr",orderSubmitBean.goods_attr);
                            jo_sub.put("is_real",orderSubmitBean.is_real);
                            jo_sub.put("extension_code",orderSubmitBean.extension_code);
                            jo_sub.put("parent_id",orderSubmitBean.parent_id);
                            jo_sub.put("is_gift",orderSubmitBean.is_gift);
                            jo_sub.put("is_shipping",orderSubmitBean.is_shipping);
                            jo_sub.put("subtotal",orderSubmitBean.subtotal);
                            jo_sub.put("formated_market_price",orderSubmitBean.formated_market_price);
                            jo_sub.put("formated_goods_price",orderSubmitBean.formated_goods_price);
                            jo_sub.put("formated_subtotal",orderSubmitBean.formated_subtotal);

                            ja.put(jo_sub);



                        }

                        jo.put("data",ja);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    submitOrderForm(jo.toString());


                }




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


    public void submitOrderForm(String jsonData){







        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","done_order");


        requestParams.addBodyParameter("user_id",userId);



        requestParams.addBodyParameter("cart_goods",jsonData);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);


                JsonParser parser = new JsonParser();

                JsonElement element = parser.parse(result);

                JsonObject root = element.getAsJsonObject();


                JsonPrimitive resultjson = root.getAsJsonPrimitive("result");



                String resultFlag = resultjson.getAsString();


                Log.i(TAG,"resultFlag"+resultFlag);

                if("0".equals(resultFlag)&&areaFlag==false){

                    rl_goods_order_pro.setVisibility(View.GONE);

                    btn_goods_order_submitorder.setEnabled(true);

                    btn_goods_order_submitorder.setText("提交成功");



                    Gson gson = new Gson();

                    OrderSuccessBean orderSuccessBean =  gson.fromJson(result, OrderSuccessBean.class);

                    Toast.makeText(getApplicationContext(),"提交成功,订单号:"+orderSuccessBean.data.order_id,Toast.LENGTH_SHORT).show();

                    //调用支付宝？

                }else{

                    rl_goods_order_pro.setVisibility(View.GONE);

                    btn_goods_order_submitorder.setEnabled(true);

                    btn_goods_order_submitorder.setText("提交失败");


                    Toast.makeText(getApplicationContext(),"提交失败,地址信息不完整",Toast.LENGTH_SHORT).show();


                }





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







    }
}
