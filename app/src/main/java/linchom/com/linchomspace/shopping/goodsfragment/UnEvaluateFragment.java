package linchom.com.linchomspace.shopping.goodsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.OrderDetailActivity;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderFormBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;
import linchom.com.linchomspace.shopping.widget.NoTouchLinearLayout;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Administrator on 2016/10/20.
 */

public class UnEvaluateFragment extends Fragment {








    private static final String TAG = "UnEvaluateFragment";
    View view;
    private PullToRefreshListView ptr_goods_orderform;

    private ListView orderList;

    private List<GoodsOrderFormBean.OrderForm> orderFormList =new ArrayList<GoodsOrderFormBean.OrderForm>();


    private List<GoodsOrderFormBean.OrderInfo> orderFormDetailList =new ArrayList<GoodsOrderFormBean.OrderInfo>();


    private GoodsCommonAdapter<GoodsOrderFormBean.OrderForm> orderFormAdapter;


    private int totalNum = 0;

    private Double totalPrice= 0.0;

    private int page = 1;

    private int totalPage=1;


    TextView tv_orderform_orderstatus;
    private RelativeLayout rl_goods_orderform_load_pro;

    private boolean pullFlag =false;


    private int chooseOrder =0;

    private String userId;

    private String userName="徐小龙";
    private NoTouchLinearLayout edit_vg_lyt;
    private EditText mCommentEdittext;
    private Button but_comment_send;


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

        ptr_goods_orderform = ((PullToRefreshListView) view.findViewById(R.id.ptr_goods_orderform));

        rl_goods_orderform_load_pro = ((RelativeLayout) view.findViewById(R.id.rl_goods_orderform_load_pro));

        edit_vg_lyt = ((NoTouchLinearLayout) view.findViewById(R.id.edit_vg_lyt));

        mCommentEdittext = ((EditText) view.findViewById(R.id.edit_comment));


        but_comment_send = ((Button) view.findViewById(R.id.but_comment_send));


    }

    private void initData() {


    }

    private void initEvent() {

        eventPullToRefresh();


        //处理返回键
        edit_vg_lyt.setOnResizeListener(new NoTouchLinearLayout.OnResizeListener() {
            @Override
            public void OnResize() {
                edit_vg_lyt.setVisibility(View.GONE);//输入框消息
                onFocusChange(false);//软键盘消息
            }
        });


        but_comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editContent =mCommentEdittext.getText().toString();
                Toast.makeText(getActivity(),"editContent"+editContent.length(),Toast.LENGTH_SHORT).show();

                if(editContent.length()!=0){

                    for(int i= 0; i<orderFormList.get(chooseOrder).order_goods.size();i++){

                        Toast.makeText(getActivity(),"发表成功",Toast.LENGTH_SHORT).show();


                        toSend(orderFormList.get(chooseOrder).order_goods.get(i).goods_id,editContent);

                        edit_vg_lyt.setVisibility(View.GONE);

                        onFocusChange(false);


                    }



                }else{

                    Toast.makeText(getActivity(),"请输入内容",Toast.LENGTH_SHORT).show();
                }





            }
        });

    }



    /**
     * 显示或隐藏输入法
     */
    private void onFocusChange(boolean hasFocus) {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        mCommentEdittext.getContext().getSystemService(INPUT_METHOD_SERVICE);
                if (isFocus) {
                    //显示输入法
                    mCommentEdittext.requestFocus();//获取焦点
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    //隐藏输入法
                    imm.hideSoftInputFromWindow(mCommentEdittext.getWindowToken(), 0);
                    edit_vg_lyt.setVisibility(View.GONE);
                }
            }
        }, 100);
    }

    /**
     * 点击屏幕其他地方收起输入法
     */



   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                onFocusChange(false);

            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }*/

    /**
     * 隐藏或者显示输入框
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            /**
             *这堆数值是算我的下边输入区域的布局的，
             * 规避点击输入区域也会隐藏输入区域
             */

            v.getLocationInWindow(leftTop);
            int left = leftTop[0] - 50;
            int top = leftTop[1] - 50;
            int bottom = top + v.getHeight() + 300;
            int right = left + v.getWidth() + 120;
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }





    private void toSend(String goodsId,String content){


        //  act=add_goods_comment
        //  user_name goods_id user_id content ip_address

        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","add_goods_comment");

        requestParams.addBodyParameter("user_name",userName);
        requestParams.addBodyParameter("goods_id",goodsId);

        requestParams.addBodyParameter("user_id",userId);

        requestParams.addBodyParameter("content",content);

        requestParams.addBodyParameter("ip_address","112.2.23.146");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

             final   Button btn_orderform_right = viewHolder.getViewById(R.id.btn_orderform_right);

                final  Button btn_orderform_left = viewHolder.getViewById(R.id.btn_orderform_left);


                Button btn_orderform_detail = viewHolder.getViewById(R.id.btn_orderform_detail);

                btn_orderform_right.setTag(position);

                btn_orderform_left.setTag(position);
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

                            //Toast.makeText(getActivity(),"评价",Toast.LENGTH_SHORT).show();

                            edit_vg_lyt.setVisibility(View.VISIBLE);

                            //先选择订单

                            chooseOrder =  (int)btn_orderform_right.getTag();

                            onFocusChange(true);




                            //找到订单中所有goodsId


                        }
                    });






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


    private void getData() {

        //orderStatusInfo ;
        //shippingStatusInfo;
        // payStatusInfo ;
        if(page==1&&pullFlag==false){

            rl_goods_orderform_load_pro.setVisibility(View.VISIBLE);

        }


        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php");

        //?act=ordersinfo&user_id=12
        requestParams.addQueryStringParameter("act","ordersinfo");
        requestParams.addQueryStringParameter("user_id",userId);

        //page

        requestParams.addQueryStringParameter("page",page+"");


        requestParams.addQueryStringParameter("order_status","5");

        requestParams.addQueryStringParameter("shipping_status","2");

        requestParams.addQueryStringParameter("pay_status","2");



        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);

                if(page<=totalPage) {

                    Gson gson = new Gson();

                    GoodsOrderFormBean goodsOrderFormBean = gson.fromJson(result, GoodsOrderFormBean.class);


                    totalPage = Integer.parseInt(goodsOrderFormBean.data.total_pages);

                    if (page == 1) {

                        orderFormList.clear();
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

}
