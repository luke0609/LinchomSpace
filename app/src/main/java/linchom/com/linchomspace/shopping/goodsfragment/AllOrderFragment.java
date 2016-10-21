package linchom.com.linchomspace.shopping.goodsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderFormBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AllOrderFragment extends Fragment {


    private static final String TAG = "AllOrderFragment";
    View view;
    private PullToRefreshListView ptr_goods_orderform;

    private  ListView orderList;

    private List<GoodsOrderFormBean.OrderForm>  orderFormList =new ArrayList<GoodsOrderFormBean.OrderForm>();

    private GoodsCommonAdapter<GoodsOrderFormBean.OrderForm> orderFormAdapter;


    private int totalNum = 0;

    private Double totalPrice= 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.goods_orderform_layout,null);


        initView();

        initData();


        initEvent();



        return view;

    }


    private void initView() {

        ptr_goods_orderform = ((PullToRefreshListView) view.findViewById(R.id.ptr_goods_orderform));


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
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_goods_orderform.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){



                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){



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

                TextView tv_orderform_orderstatus = viewHolder.getViewById(R.id.tv_orderform_orderstatus);

                GoodsNoScrollListview lv_orderform_list =viewHolder.getViewById(R.id.lv_orderform_list);

                TextView tv_orderform_totalNum = viewHolder.getViewById(R.id.tv_orderform_totalNum);


                TextView tv_orderform_totalPrice= viewHolder.getViewById(R.id.tv_orderform_totalPrice);

                Button btn_orderform_right = viewHolder.getViewById(R.id.btn_orderform_right);

                totalNum=0;
                totalPrice=0.0;


                for(int i=0;i<(orderForm.order_goods).size();i++){

                    totalNum +=Integer.parseInt(orderForm.order_goods.get(i).goods_number) ;

                    totalPrice+=(Double.parseDouble(orderForm.order_goods.get(i).total));

                }




                tv_orderform_totalNum.setText("共"+totalNum+"件");
                tv_orderform_totalPrice.setText("合计:"+totalPrice+"元");





                String orderStatus =orderForm.order_status;
                String shippingStatus =orderForm.shipping_status;
                String payStatus =orderForm.pay_status;
                if("0".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //未确认  待付款  等待买家付款

                    tv_orderform_orderstatus.setText("等待买家付款");


                }else if("2".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //取消    取消订单     买家取消订单
                    tv_orderform_orderstatus.setText("买家取消订单");

                }else if("1".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //确认    确认订单     买家已经确认订单
                    tv_orderform_orderstatus.setText("买家已经确认订单");

                }else if("1".equals(orderStatus)&&"0".equals(shippingStatus)&&"2".equals(payStatus)){
                    //已付款             买家已经付款
                    tv_orderform_orderstatus.setText("买家已经付款");


                }else if("1".equals(orderStatus)&&"3".equals(shippingStatus)&&"2".equals(payStatus)){
                    //配货中    待发货          等待卖家发货
                    tv_orderform_orderstatus.setText("等待卖家发货");


                }else if("5".equals(orderStatus)&&"1".equals(shippingStatus)&&"2".equals(payStatus)){

                    //已发货               等待买家收货
                    tv_orderform_orderstatus.setText("等待买家收货");

                }else if("5".equals(orderStatus)&&"2".equals(shippingStatus)&&"2".equals(payStatus)){
                    //已收货              等待买家评价
                    tv_orderform_orderstatus.setText("等待买家评价");

                }else if("4".equals(orderStatus)&&"0".equals(shippingStatus)&&"0".equals(payStatus)){
                    //退货                退货处理
                    tv_orderform_orderstatus.setText("退货处理");

                }else{
                    //订单信息
                    tv_orderform_orderstatus.setText("订单信息");
                }

                GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo> orderInfoGoodsCommonAdapter =new GoodsCommonAdapter<GoodsOrderFormBean.OrderInfo>(getActivity(),orderForm.order_goods,R.layout.goods_order_list_item) {
                    @Override
                    public void convert(GoodsViewHolder viewHolder, GoodsOrderFormBean.OrderInfo orderInfo, int position) {

                   ImageView iv_goods_order_img = viewHolder.getViewById(R.id.iv_goods_order_img);

                        GoodsXUtilsImage.display(iv_goods_order_img,orderInfo.goods_img);




                    }
                };

                lv_orderform_list.setAdapter(orderInfoGoodsCommonAdapter);



                btn_orderform_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        };


        orderList.setAdapter(orderFormAdapter);

        getData();


    }


    private void getData() {

        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php");

        //?act=ordersinfo&user_id=12
        requestParams.addQueryStringParameter("act","ordersinfo");
        requestParams.addQueryStringParameter("user_id","12");



        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);

                Gson gson =new Gson();

                GoodsOrderFormBean goodsOrderFormBean = gson.fromJson(result, GoodsOrderFormBean.class);

                String str = goodsOrderFormBean.data.items.get(0).order_goods.get(0).goods_name;
                Log.i(TAG,"str"+str);

                orderFormList.addAll(goodsOrderFormBean.data.items);

                orderFormAdapter.notifyDataSetChanged();


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
