package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.pojo.GoodsCartBean;
import linchom.com.linchomspace.shopping.pojo.OrderSubmitBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

public class GoodsCartActivity extends AppCompatActivity {

    private List<AreaListBean.Data> areaList = new ArrayList<AreaListBean.Data>();



    private static final String TAG = "GoodsCartActivity";
    private String userId="12";

    private ListView cartListView;
    private PullToRefreshListView ptr_cartList_ptr;

    private GoodsCommonAdapter<GoodsCartBean.Data> goodsCommonAdapter;

    private List<GoodsCartBean.Data> cartList =new ArrayList<GoodsCartBean.Data>();


    private Map<Integer,Boolean> checkStatusMap = new HashMap<Integer,Boolean>();

    private Map<Integer,Boolean> checkModifyStatusMap = new HashMap<Integer,Boolean>();


    private Map<Integer,String> buyNumMap = new HashMap<Integer,String>();


    private Double totalPrice =0.0;
    private TextView tv_goods_cart_totalprice;





    private boolean flagDelete =false;
    private Button btn_goods_cart_total;


    private ArrayList<OrderSubmitBean> orderCartList =new ArrayList<OrderSubmitBean>();
    private RelativeLayout rl_goods_cart_load_pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_cart);




        initView();

        initData();

        initEvent();
    }

    private void initView() {
        ptr_cartList_ptr = ((PullToRefreshListView) findViewById(R.id.ptr_cartList_ptr));

        tv_goods_cart_totalprice = ((TextView) findViewById(R.id.tv_goods_cart_totalprice));


        btn_goods_cart_total = ((Button) findViewById(R.id.btn_goods_cart_total));

        rl_goods_cart_load_pro = ((RelativeLayout) findViewById(R.id.rl_goods_cart_load_pro));


    }

    private void initData() {


    }

    private void initEvent() {

        eventPtr();

        totalMoney();

    }

    private void totalMoney() {

        btn_goods_cart_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //orderList.add(new GoodsOrderBean(goodsNum, goodsImg,goodsName,goodsPrice));

                orderCartList.clear();


                for (Map.Entry<Integer,Boolean> m : checkStatusMap.entrySet()) {


                   if(m.getValue()){

                       Log.i(TAG,m.getKey()+"");

                       buyNumMap.get(m.getKey());

                       Log.i(TAG, buyNumMap.get(m.getKey())+"");



                       int threeNum = Integer.parseInt(buyNumMap.get(m.getKey()));

                       Double threePrice =Double.parseDouble(cartList.get(m.getKey()).goods_price);

                       Double threeTotal =threeNum*threePrice;




                       orderCartList.add(new OrderSubmitBean(cartList.get(m.getKey()).rec_id,
                               cartList.get(m.getKey()).user_id,
                               cartList.get(m.getKey()).goods_id,
                               cartList.get(m.getKey()).goods_name,
                               cartList.get(m.getKey()).goods_sn,
                               buyNumMap.get(m.getKey()),
                               cartList.get(m.getKey()).market_price,
                               cartList.get(m.getKey()).goods_price,
                               cartList.get(m.getKey()).goods_attr,
                               cartList.get(m.getKey()).is_real,
                               cartList.get(m.getKey()).extension_code,
                               cartList.get(m.getKey()).parent_id,
                               cartList.get(m.getKey()).is_gift,
                               cartList.get(m.getKey()).is_shipping,
                               threeTotal+"",
                               cartList.get(m.getKey()).formated_market_price,
                               cartList.get(m.getKey()).formated_goods_price,
                               threeTotal+"",
                               cartList.get(m.getKey()).goods_img
                               ));

                   }

                }

                //初始收货地址

                if(orderCartList.size()!=0){
                    initDefaultAddress();




                }else{

                    Toast.makeText(getApplicationContext(),"请先选择商品",Toast.LENGTH_SHORT).show();


                }






            }
        });



    }

    private void initDefaultAddress(){

        rl_goods_cart_load_pro.setVisibility(View.VISIBLE);

        //地址也要传过去

       // Toast.makeText(getApplicationContext(),"初始化默认地址",Toast.LENGTH_SHORT).show();


        //遍历遍历地址

        //http://app.linchom.com/appapi.php?act=consignee&user_id=12

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","consignee");

        requestParams.addBodyParameter("user_id",16+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //Toast.makeText(getApplicationContext(),"result"+result,Toast.LENGTH_SHORT).show();

                Gson gson = new Gson();
                //areaList
                areaList.clear();

                AreaListBean areaListBean =  gson.fromJson(result,AreaListBean.class);

                areaList.addAll(areaListBean.data);

                //找出第一个地址Id设为默认地址

                defaultArea();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                defaultArea();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }


    private void defaultArea() {
        //http://app.linchom.com/appapi.php?act=default_consignee&user_id=135&address_id=36

        //Toast.makeText(getApplicationContext(),areaList.get(0).address_id,Toast.LENGTH_SHORT).show();

        if (areaList.size() != 0) {


            RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);


            requestParams.addBodyParameter("act", "default_consignee");
            requestParams.addBodyParameter("user_id", userId + "");
            requestParams.addBodyParameter("address_id", areaList.get(0).address_id + "");


            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                   // Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();

                    //orderList
                    // areaList.get(0)        传过去

                    rl_goods_cart_load_pro.setVisibility(View.GONE);

                    Intent intent = new Intent(GoodsCartActivity.this, GoodsOrderActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("orderList", null);

                    bundle.putSerializable("orderCartList", orderCartList);

                    bundle.putSerializable("areaList", areaList.get(0));

                    intent.putExtra("bundle", bundle);

                    startActivity(intent);

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

        } else {



                    Intent intent = new Intent(GoodsCartActivity.this, GoodsOrderActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("orderList", null);

                    bundle.putSerializable("orderCartList", orderCartList);

                    /// 没收货地址   再说

                    bundle.putSerializable("areaList", null);

                    intent.putExtra("bundle", bundle);

                    startActivity(intent);





        }

    }




    private void eventPtr() {

        ptr_cartList_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_cartList_ptr.setMode(PullToRefreshBase.Mode.DISABLED);
        ptr_cartList_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                PullToRefreshBase.Mode mode = ptr_cartList_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){





                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){



                }



            }
        });

        ptr_cartList_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        cartListView=ptr_cartList_ptr.getRefreshableView();



        goodsCommonAdapter =new GoodsCommonAdapter<GoodsCartBean.Data>(getApplicationContext(),cartList,R.layout.goods_cart_list_item) {

            int buyNum;

            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCartBean.Data data, final int position) {

            final  CheckBox cb_goods_cart_choice=  viewHolder.getViewById(R.id.cb_goods_cart_choice);

            TextView iv_goods_cart_name =viewHolder.getViewById(R.id.iv_goods_cart_name);
            TextView tv_goods_cart_price=  viewHolder.getViewById(R.id.tv_goods_cart_price);

             final  ImageView iv_goods_cart_img= viewHolder.getViewById(R.id.iv_goods_cart_img);

                GoodsXUtilsImage.display(iv_goods_cart_img,data.goods_img);

           final  TextView tv_goods_cart_num=   viewHolder.getViewById(R.id.tv_goods_cart_num);

                final  RelativeLayout rl_goods_cart_sub= viewHolder.getViewById(R.id.rl_goods_cart_sub);

           final TextView et_goods_cart_buynum=  viewHolder.getViewById(R.id.et_goods_cart_buynum);

                final  RelativeLayout rl_goods_cart_add= viewHolder.getViewById(R.id.rl_goods_cart_add);


            final CheckBox cb_goods_cart_modify= viewHolder.getViewById(R.id.cb_goods_cart_modify);
           final  RelativeLayout rl_goods_cart_modify= viewHolder.getViewById(R.id.rl_goods_cart_modify);

           final Button btn_goods_cart_delete=   viewHolder.getViewById(R.id.btn_goods_cart_delete);


                iv_goods_cart_img.setTag(position);

                btn_goods_cart_delete.setTag(position);


                cb_goods_cart_choice.setTag(position);
                cb_goods_cart_choice.setChecked(checkStatusMap.get(position));

                cb_goods_cart_modify.setTag(position);
                cb_goods_cart_modify.setChecked(checkModifyStatusMap.get(position));



                rl_goods_cart_sub.setTag(position);
                rl_goods_cart_add.setTag(position);


                et_goods_cart_buynum.setTag(position);
                et_goods_cart_buynum.setText(buyNumMap.get(position));


                tv_goods_cart_num.setText(buyNumMap.get(position));

                if(checkModifyStatusMap.get(position)){

                    cb_goods_cart_modify.setText("完成");
                    rl_goods_cart_modify.setVisibility(View.VISIBLE);

                }else{

                    cb_goods_cart_modify.setText("编辑");
                    rl_goods_cart_modify.setVisibility(View.GONE);



                }




                iv_goods_cart_name.setText(data.goods_name);

                tv_goods_cart_price.setText(data.goods_price);



                //清  0？？？？？？？？？？

                cb_goods_cart_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if(isChecked&&position==(int)cb_goods_cart_choice.getTag()){

                            checkStatusMap.put((int)buttonView.getTag(),true);

                           double onePrice = Double.parseDouble(cartList.get((int)cb_goods_cart_choice.getTag()).goods_price);

                           int oneNum= Integer.parseInt(buyNumMap.get((int)cb_goods_cart_choice.getTag()));

                            totalPrice+= (onePrice*oneNum);

                            tv_goods_cart_totalprice.setText("合计:"+totalPrice+"元");

                            flagDelete=false;




                        }else if(!isChecked&&position==(int)cb_goods_cart_choice.getTag()){
                                //有勾的调用这个方法


                                checkStatusMap.put((int) buttonView.getTag(), false);


                                double onePrice = Double.parseDouble(cartList.get((int) cb_goods_cart_choice.getTag()).goods_price);

                                int oneNum = Integer.parseInt(buyNumMap.get((int) cb_goods_cart_choice.getTag()));


                            if(flagDelete==false) {

                                totalPrice -= (onePrice * oneNum);

                                tv_goods_cart_totalprice.setText("合计:" + totalPrice + "元");
                            }

                                if(flagDelete==true){

                                    totalPrice=0.0;
                                    tv_goods_cart_totalprice.setText("合计:" + totalPrice + "元");


                                }





                        }

                    }
                });



                cb_goods_cart_modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked&&position==(int)cb_goods_cart_modify.getTag()){
                            //flagDelete=false;

                            rl_goods_cart_modify.setVisibility(View.VISIBLE);

                            cb_goods_cart_modify.setText("完成");

                            checkModifyStatusMap.put((int)buttonView.getTag(),true);




                        }else if(!isChecked&&position==(int)cb_goods_cart_modify.getTag()){
                           // flagDelete=false;


                            rl_goods_cart_modify.setVisibility(View.GONE);
                            cb_goods_cart_modify.setText("编辑");
                            tv_goods_cart_num.setText(buyNumMap.get((int)cb_goods_cart_modify.getTag()));
                            checkModifyStatusMap.put((int)buttonView.getTag(),false);

                            Log.i(TAG,"checkModifyStatusMap"+checkModifyStatusMap);


                        }



                    }
                });

                rl_goods_cart_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        flagDelete=false;


                        if(position==(int)et_goods_cart_buynum.getTag()&&position==(int)rl_goods_cart_sub.getTag()) {

                            buyNum = Integer.parseInt(et_goods_cart_buynum.getText().toString());

                            if (buyNum > 1) {
                                buyNum--;

                                if (checkStatusMap.get((int) et_goods_cart_buynum.getTag())) {

                                    double onePrice = Double.parseDouble(cartList.get((int) cb_goods_cart_choice.getTag()).goods_price);

                                    totalPrice -= onePrice;

                                    tv_goods_cart_totalprice.setText("合计:" + totalPrice + "元");

                                }

                            }
                            buyNumMap.put((int)v.getTag(),buyNum+"");

                            et_goods_cart_buynum.setText(buyNum + "");


                        }


                    }
                });

                rl_goods_cart_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       flagDelete=false;


                        if(position==(int)et_goods_cart_buynum.getTag()&&position==(int)rl_goods_cart_add.getTag()) {


                            buyNum = Integer.parseInt(et_goods_cart_buynum.getText().toString());


                                buyNum++;



                            buyNumMap.put((int)v.getTag(),buyNum+"");

                            Log.i(TAG,"buyNumMap"+buyNumMap);



                            et_goods_cart_buynum.setText(buyNum + "");


                            if(checkStatusMap.get((int)et_goods_cart_buynum.getTag())){

                                double onePrice = Double.parseDouble(cartList.get((int)cb_goods_cart_choice.getTag()).goods_price);

                                totalPrice+=onePrice;

                                tv_goods_cart_totalprice.setText("合计:"+totalPrice+"元");


                            }

                        }

                    }
                });


                btn_goods_cart_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //总价先减去删除价格

                        ///double twoPrice = Double.parseDouble(cartList.get((int)btn_goods_cart_delete.getTag()).goods_price);
                       // int twoNum= Integer.parseInt(buyNumMap.get((int)btn_goods_cart_delete.getTag()));

                       // totalPrice-=(twoPrice*twoNum);



                        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

                        requestParams.addBodyParameter("act","flow_drop_cart_goods");

                        requestParams.addBodyParameter("rec_id",cartList.get((int)v.getTag()).rec_id+"");


                        requestParams.addBodyParameter("user_id",userId+"");

                        x.http().post(requestParams, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {


                              //  Log.i(TAG,"result"+result);
                               // Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();


                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();

                                flagDelete =true;




                                getData();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                Toast.makeText(getApplicationContext(),"删除失败",Toast.LENGTH_SHORT).show();



                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });





                    }
                });


                iv_goods_cart_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent =new Intent(GoodsCartActivity.this,GoodsActivity.class);

                        String goodsId = cartList.get((int)iv_goods_cart_img.getTag()).goods_id;

                        Bundle bundle =new Bundle();

                        bundle.putString("goodsId",goodsId);

                        intent.putExtra("bundle",bundle);

                        startActivity(intent);


                    }
                });


            }
        };

        cartListView.setAdapter(goodsCommonAdapter);

        getData();


    }

    private void getData() {

        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","cartgoods");

        requestParams.addBodyParameter("user_id",userId+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                


                Gson gson =new Gson();
                GoodsCartBean goodsCartBean =  gson.fromJson(result,GoodsCartBean.class);

                cartList.clear();
                cartList.addAll(goodsCartBean.data);

                Log.i(TAG,cartList.size()+"");

                checkStatusMap.clear();


                for(int i=0;i<cartList.size();i++){


                    checkStatusMap.put(i,false);


                }
                checkModifyStatusMap.clear();

                for(int i=0;i<cartList.size();i++){


                    checkModifyStatusMap.put(i,false);


                }
                buyNumMap.clear();


                for(int i=0;i<cartList.size();i++){



                    buyNumMap.put(i,cartList.get(i).goods_number);
                }

                totalPrice=0.0;

                tv_goods_cart_totalprice.setText("合计:0.0元");

                goodsCommonAdapter.notifyDataSetChanged();

                
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                totalPrice=0.0;

                tv_goods_cart_totalprice.setText("合计:0.0元");


                cartList.clear();
                goodsCommonAdapter.notifyDataSetChanged();



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
