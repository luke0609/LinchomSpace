package linchom.com.linchomspace.shopping.goodsfragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.OrderDetailActivity;
import linchom.com.linchomspace.shopping.alipay.OrderInfoUtil2_0;
import linchom.com.linchomspace.shopping.alipay.PayResult;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderFormBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AllOrderFragment extends Fragment {



    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016011801101432";

    /** 支付宝账户登录授权业务：入参pid值 */
    //public static final String PID = "2088911117593113";
    /** 支付宝账户登录授权业务：入参target_id值 */
    //public static final String TARGET_ID = "xiaozh2012@foxmail.com";

    /** 商户私钥，pkcs8格式 */
    public static final String RSA_PRIVATE =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMjJMt7bID3QHnP7\n" +
                    "224YQZBLZwYp3IPuHpmV3dm6KuOWmRtTCieM69ZfIU01WTH20mFzeXG1jLh2pCLD\n" +
                    "gMFcqk7JpgcRRxB7rlb/Cjbfkvf7etAEo9CkXJkNUIH1wWp479L7kW86sEArTB5c\n" +
                    "+qVZty4LG+znPL/aEvGCe6Hp9jMNAgMBAAECgYBZ0yDt8Cv5W0z0aF0fYLycGA+M\n" +
                    "A1hCsVmBLjmkuROM44e1YK3vSa0MJ1zXlKFFp/0wWZ+gDi9ZKsJ9RucoGhOaBmWW\n" +
                    "b1b9fBJ6QgCZK5AJwtvJFdHVxAuJgwPqajzGT5ooA9dsnEfoFK+Yv3NuHkkXLfBF\n" +
                    "Xnp6Tz6dbOelY8W9pQJBAPMONmmgTQLpMgmS8fM/SDY1L2pLUcGHYVBe99vm8tk+\n" +
                    "ibIv0Fj26b4Jsb1Rmwv//rOxLjejVSDQ26JXk3nZpGcCQQDTerAoH6LDS0r4xsJ4\n" +
                    "+QP2A/DDC0lIwOUCuOTqmL19g2ESRdL+f6UpovKb1ZeifzqqgJsf42I0QFMWfD4M\n" +
                    "ICRrAkByGPDoIs0kGa5YmjekVcejUtJAVr05WUEQhpRaEY9c9iOTlyh6KubNRCXA\n" +
                    "1scvGexKFFm62pzCu+juy6e6YrXvAkB5pX8i9V+ouuy7QYmoEIVoxEd/ykQzZ1HU\n" +
                    "SQrUr1uAkUwLOMLvxfj5hFPNtAVvYYQbg7K3mxJoQAALVRhT3UFVAkBCMyCh8B7S\n" +
                    "NaIYHWHaMwQqDhpWKHE6yvNR/VlWSPFpVZRQhRiGpxBAdJtPtranfd/GQfZUEwL7\n" +
                    "Vt3VA7SadSHD";

    private static final int SDK_PAY_FLAG = 1;
    //private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();


                        page=1;
                        pullFlag=false;
                        getData();




                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                default:
                    break;
            }
        }
    };








    private static final String TAG = "AllOrderFragment";
    View view;
    private PullToRefreshListView ptr_goods_orderform;

    private  ListView orderList;

    private ArrayList<GoodsOrderFormBean.OrderForm>  orderFormList =new ArrayList<GoodsOrderFormBean.OrderForm>();

    private GoodsCommonAdapter<GoodsOrderFormBean.OrderForm> orderFormAdapter;


    private int totalNum = 0;

    private Double totalPrice= 0.0;

    private int page = 1;

    private int totalPage=1;

    private String userId;

    TextView tv_orderform_orderstatus;
    private RelativeLayout rl_goods_orderform_load_pro;

    private boolean pullFlag=false;
    private List<Double> totalPriceList = new ArrayList<Double>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.goods_orderform_layout,null);

        Bundle bundle =getArguments();
        userId=bundle.getString("userId");


        initView();

        initData();


        initEvent();


        return view;

    }


    private void initView() {
        page = 1;

        ptr_goods_orderform = ((PullToRefreshListView) view.findViewById(R.id.ptr_goods_orderform));

        rl_goods_orderform_load_pro = ((RelativeLayout) view.findViewById(R.id.rl_goods_orderform_load_pro));


    }

    private void initData() {


    }

    private void initEvent() {

       eventPullToRefresh();




    }

    private void eventPullToRefresh() {


        ptr_goods_orderform.setScrollingWhileRefreshingEnabled(true);
        ptr_goods_orderform.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_goods_orderform.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);


                PullToRefreshBase.Mode mode = ptr_goods_orderform.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){

                    pullFlag=true;

                    page=1;

                    getData();



                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){
                    page++;
                    getData();



                }



            }
        });

        ptr_goods_orderform.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        orderList=ptr_goods_orderform.getRefreshableView();

        orderFormAdapter =new GoodsCommonAdapter<GoodsOrderFormBean.OrderForm>(getActivity(),orderFormList,R.layout.orderform_list_item_layout) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsOrderFormBean.OrderForm orderForm, final int position) {

                tv_orderform_orderstatus = viewHolder.getViewById(R.id.tv_orderform_orderstatus);

                GoodsNoScrollListview lv_orderform_list =viewHolder.getViewById(R.id.lv_orderform_list);

                TextView tv_orderform_totalNum = viewHolder.getViewById(R.id.tv_orderform_totalNum);


                TextView tv_orderform_totalPrice= viewHolder.getViewById(R.id.tv_orderform_totalPrice);

                final  Button btn_orderform_right = viewHolder.getViewById(R.id.btn_orderform_right);

               final Button btn_orderform_left = viewHolder.getViewById(R.id.btn_orderform_left);
                Button btn_orderform_detail = viewHolder.getViewById(R.id.btn_orderform_detail);

                btn_orderform_right.setTag(position);

                btn_orderform_left.setTag(position);

                totalNum=0;
                totalPrice=0.0;


                for(int i=0;i<(orderForm.order_goods).size();i++){

                    totalNum +=Integer.parseInt(orderForm.order_goods.get(i).goods_number) ;

                    totalPrice+=(Double.parseDouble(orderForm.order_goods.get(i).total));

                }

                totalPriceList.add(totalPrice);




                tv_orderform_totalNum.setText("共"+totalNum+"件");
                tv_orderform_totalPrice.setText("合计:"+totalPrice+"元");





                String orderStatus =orderForm.order_status;
                String shippingStatus =orderForm.shipping_status;
                String payStatus =orderForm.pay_status;
                if("0".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //未确认  待付款  等待买家付款

                    tv_orderform_orderstatus.setText("等待买家付款");

                    //取消订单    付款
                    btn_orderform_left.setVisibility(View.VISIBLE);

                    btn_orderform_right.setVisibility(View.INVISIBLE);

                    btn_orderform_left.setText("取消订单");

                    btn_orderform_right.setText("付款");

                    btn_orderform_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            toModifyOrder(orderFormList.get((int)btn_orderform_left.getTag()).order_id,"1");

                            page=1;
                            pullFlag=false;
                            getData();

                        }
                    });


                    btn_orderform_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                          //  Toast.makeText(getActivity(),"调用支付宝"+"订单号"+orderFormList.get((int)btn_orderform_right.getTag()).order_id,Toast.LENGTH_SHORT).show();



                            if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                                new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                //
                                                //finish();
                                            }
                                        }).show();
                                return;
                            }


                            Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,totalPriceList.get((int)btn_orderform_right.getTag())+"",orderFormList.get((int)btn_orderform_right.getTag()).order_id);
                            String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                            String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
                            final String orderInfo = orderParam + "&" + sign;

                            Runnable payRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(getActivity());
                                    Map<String, String> result = alipay.payV2(orderInfo, true);
                                    Log.i("msp", result.toString());

                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };

                            Thread payThread = new Thread(payRunnable);
                            payThread.start();






                        }
                    });




                }else if("2".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //取消    取消订单     买家取消订单

                    tv_orderform_orderstatus.setText("买家取消订单");

                    // 无   无
                    btn_orderform_left.setVisibility(View.INVISIBLE);
                    btn_orderform_right.setVisibility(View.INVISIBLE);

                }else if("1".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //确认    确认订单     买家已经确认订单
                    tv_orderform_orderstatus.setText("买家已经确认订单");

                    //无 无

                    btn_orderform_left.setVisibility(View.INVISIBLE);
                    btn_orderform_right.setVisibility(View.INVISIBLE);

                }else if("1".equals(orderStatus)&&"0".equals(shippingStatus)&&"2".equals(payStatus)){
                    //已付款             买家已经付款  无退款
                    tv_orderform_orderstatus.setText("买家已经付款");

                    //无  无
                    btn_orderform_left.setVisibility(View.INVISIBLE);

                    btn_orderform_right.setVisibility(View.INVISIBLE);





                }else if("1".equals(orderStatus)&&"3".equals(shippingStatus)&&"2".equals(payStatus)){
                    //配货中    待发货          等待卖家发货  无退款
                    tv_orderform_orderstatus.setText("等待卖家发货");

                    //无  无

                    btn_orderform_left.setVisibility(View.INVISIBLE);

                    btn_orderform_right.setVisibility(View.INVISIBLE);


                }else if("5".equals(orderStatus)&&"1".equals(shippingStatus)&&"2".equals(payStatus)){

                    //已发货               等待买家收货
                    tv_orderform_orderstatus.setText("等待买家收货");

                    //无    确认收货

                    btn_orderform_left.setVisibility(View.INVISIBLE);

                    btn_orderform_right.setText("确认收货");

                    btn_orderform_right.setVisibility(View.VISIBLE);

                    btn_orderform_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            toModifyOrder(orderFormList.get((int)btn_orderform_left.getTag()).order_id,"2");
                            page=1;
                            pullFlag=false;
                            getData();



                        }
                    });

                }else if("5".equals(orderStatus)&&"2".equals(shippingStatus)&&"2".equals(payStatus)){
                    //已收货              等待买家评价
                    tv_orderform_orderstatus.setText("等待买家评价");

                    //退货    评价

                    btn_orderform_left.setText("退货");
                    btn_orderform_left.setVisibility(View.VISIBLE);

                    btn_orderform_right.setText("评价");
                    btn_orderform_right.setVisibility(View.VISIBLE);


                    btn_orderform_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            toModifyOrder(orderFormList.get((int)btn_orderform_left.getTag()).order_id,"3");
                            page=1;
                            pullFlag=false;
                            getData();


                        }
                    });

                    btn_orderform_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(getActivity(),"评价",Toast.LENGTH_SHORT).show();


                        }
                    });




                }else if("4".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //退货                退货处理
                    tv_orderform_orderstatus.setText("退货处理");

                    //  无   无
                    btn_orderform_left.setVisibility(View.INVISIBLE);
                    btn_orderform_right.setVisibility(View.INVISIBLE);


                }else{
                    //订单信息
                    tv_orderform_orderstatus.setText("订单信息");

                    //  无    无

                    btn_orderform_left.setVisibility(View.INVISIBLE);
                    btn_orderform_right.setVisibility(View.INVISIBLE);
                }

                GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo> orderInfoGoodsCommonAdapter =new GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo>(getActivity(),orderForm.order_goods,R.layout.goods_order_list_item) {
                    @Override
                    public void convert(GoodsViewHolder viewHolder, GoodsOrderFormBean.OrderInfo orderInfo, int position) {

                   ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);


                        String imgUrlChange = orderInfo.goods_img;

                        if("h".equals(imgUrlChange.substring(0,1))){

                        }else{
                            imgUrlChange= GoodsHttpUtils.IMGURL+imgUrlChange;
                        }


                        GoodsXUtilsImage.display(iv_goods_order_img,imgUrlChange);

                       TextView tv_goods_order_goodsName=   viewHolder.getViewById(R.id.tv_goods_order_goodsName);

                       TextView tv_goods_order_price = viewHolder.getViewById(R.id.tv_goods_order_price);

                       TextView tv_goods_order_goodsNum= viewHolder.getViewById(R.id.tv_goods_order_goodsNum);


                        tv_goods_order_goodsName.setText(orderInfo.goods_name);

                        tv_goods_order_price.setText(orderInfo.goods_price);

                        tv_goods_order_goodsNum.setText(orderInfo.goods_number);



                    }
                };

                lv_orderform_list.setAdapter(orderInfoGoodsCommonAdapter);

                btn_orderform_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent =new Intent(getActivity(), OrderDetailActivity.class);


                        Bundle bundle =new Bundle();

                        String orderState = tv_orderform_orderstatus.getText().toString();

                        bundle.putString("orderState",orderState);

                        bundle.putSerializable("orderFormList",orderFormList.get(position));

                        bundle.putString("userId",userId);

                        intent.putExtra("bundle",bundle);



                        startActivity(intent);


                    }
                });





            }
        };


        orderList.setAdapter(orderFormAdapter);

        getData();


    }


    private void toModifyOrder(String orderId,String type){

       // app.linchom.com/appapi.php?act=editorderinfo&type=1&order_id=52&user_id=12

        //type 1 为取消 2为确认收货 3 退货
        //确认收货的前提 订单必须 是发货状态
        //退货  订单必须是已经支付

        //取消 重新拿数据 page=1；


        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","editorderinfo");

        requestParams.addBodyParameter("order_id",orderId+"");

        requestParams.addBodyParameter("user_id",userId+"");

        requestParams.addBodyParameter("type",type+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);


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


    private void getData() {

        if(page==1&&pullFlag==false){


            rl_goods_orderform_load_pro.setVisibility(View.VISIBLE);


        }


        //orderStatusInfo ;
        //shippingStatusInfo;
       // payStatusInfo ;



        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        //?act=ordersinfo&user_id=12
        requestParams.addBodyParameter("act","ordersinfo");
        requestParams.addBodyParameter("user_id",userId);

        //page

        requestParams.addBodyParameter("page",page+"");

/*

        requestParams.addQueryStringParameter("order_status","");

        requestParams.addQueryStringParameter("shipping_status","");

        requestParams.addQueryStringParameter("pay_status","");

*/

        Log.i(TAG,"requestParams"+requestParams);


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);

                if(page<=totalPage) {

                    Gson gson = new Gson();

                    GoodsOrderFormBean goodsOrderFormBean = gson.fromJson(result, GoodsOrderFormBean.class);


                    totalPage = Integer.parseInt(goodsOrderFormBean.data.total_pages);

                    if (page == 1) {

                        orderFormList.clear();

                        totalPriceList.clear();
                    }

                    orderFormList.addAll(goodsOrderFormBean.data.items);


                    orderFormAdapter.notifyDataSetChanged();

                    ptr_goods_orderform.onRefreshComplete();

                }else{

                    Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();
                    orderFormAdapter.notifyDataSetChanged();

                    ptr_goods_orderform.onRefreshComplete();

                }
                rl_goods_orderform_load_pro.setVisibility(View.GONE);




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
