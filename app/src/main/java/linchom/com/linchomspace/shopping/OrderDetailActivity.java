package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderDetailBean;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderFormBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

public class OrderDetailActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetailActivity";
    //orderState
    //orderFormList
    GoodsOrderFormBean.OrderForm  orderInfo;

   private String orderState;

    private  String userId;
    private TextView iv_goods_orderInfo_state;
    private TextView tv_orderInfo_name;
    private TextView tv_orderInfo_phone;
    private TextView tv_orderInfo_address;

    private String name;

    private String tel;

    private String address;
    private ListView lv_orderInfo_orderList;

    private GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo> goodsCommonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent =getIntent();


         Bundle bundle =  intent.getBundleExtra("bundle");

         Serializable serializable = bundle.getSerializable("orderFormList");

        orderState= bundle.getString("orderState");

        userId =bundle.getString("userId");


        orderInfo= (GoodsOrderFormBean.OrderForm)serializable;


        getData();

        initView();

        initData();

        initEvent();


    }



    private void initView() {

        iv_goods_orderInfo_state = ((TextView) findViewById(R.id.iv_goods_orderInfo_state));

        tv_orderInfo_name = ((TextView) findViewById(R.id.tv_orderInfo_name));
        tv_orderInfo_phone = ((TextView) findViewById(R.id.tv_orderInfo_phone));

        tv_orderInfo_address = ((TextView) findViewById(R.id.tv_orderInfo_address));

        lv_orderInfo_orderList = ((ListView) findViewById(R.id.lv_orderInfo_orderList));

    }

    private void initData() {


        iv_goods_orderInfo_state.setText(orderState);

        goodsCommonAdapter =new GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo>(getApplicationContext(),orderInfo.order_goods,R.layout.goods_order_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsOrderFormBean.OrderInfo orderInfo, int position) {


                ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);

               TextView tv_goods_order_goodsName=  viewHolder.getViewById(R.id.tv_goods_order_goodsName);

               TextView tv_goods_order_price =  viewHolder.getViewById(R.id.tv_goods_order_price);

               TextView tv_goods_order_goodsNum = viewHolder.getViewById(R.id.tv_goods_order_goodsNum);

                tv_goods_order_goodsName.setText(orderInfo.goods_name);
                tv_goods_order_price.setText(orderInfo.goods_price);
                tv_goods_order_goodsNum.setText(orderInfo.goods_number);

                String imgUrl =orderInfo.goods_img;

                if("h".equals(imgUrl.substring(0,1))){



                }else{

                    imgUrl=GoodsHttpUtils.IMGURL+imgUrl;

                }

                GoodsXUtilsImage.display(iv_goods_order_img,imgUrl);





            }
        };

        lv_orderInfo_orderList.setAdapter(goodsCommonAdapter);





    }

    private void initEvent() {



    }


    private void getData() {

        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","orderdetail");

        requestParams.addBodyParameter("user_id",userId);

        requestParams.addBodyParameter("order_id",orderInfo.order_id);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);


                Gson gson =new Gson();

                GoodsOrderDetailBean goodsOrderDetailBean =  gson.fromJson(result, GoodsOrderDetailBean.class);

                name = goodsOrderDetailBean.data.consignee;
                Log.i(TAG,"name"+name);

                tel = goodsOrderDetailBean.data.tel;
                Log.i(TAG,"tel"+tel);

                address =goodsOrderDetailBean.data.address;
                Log.i(TAG,"address"+address);



                tv_orderInfo_name.setText(name);
                tv_orderInfo_phone.setText(tel);
                tv_orderInfo_address.setText(address);



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
