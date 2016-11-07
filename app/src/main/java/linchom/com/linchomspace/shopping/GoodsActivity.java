package linchom.com.linchomspace.shopping;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.homepage.Utils.ToastUtil;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.shopping.contant.GoodsContant;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsPagerAdapter;
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.pojo.GoodsBean;
import linchom.com.linchomspace.shopping.pojo.GoodsCartBean;
import linchom.com.linchomspace.shopping.pojo.GoodsCommonBean;
import linchom.com.linchomspace.shopping.pojo.JoinCartBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.PictureHandle;
import linchom.com.linchomspace.shopping.widget.GoodsNoScrollListview;
import linchom.com.linchomspace.shopping.widget.GoodsScrollView;

public class GoodsActivity extends AppCompatActivity implements View.OnClickListener{
//修改时间11/4




    private static final String TAG = "GoodsActivity";
    private GoodsScrollView sv_goods_scrollview;
    private RelativeLayout rl_goods_head;
    private TextView tv_goods_title_content;

    private int buyNum;

    GoodsPagerAdapter goodsPagerAdapter;

    private  String goodsId;//

    private List<String> goodsPicList =new ArrayList<String>();

    private ArrayList<String> goodsPicDetail =new ArrayList<String>();
    private ViewPager rl_goods_goodsShow;
    private ImageView iv_goods_turnleft;
    private ImageView iv_goods_turnright;

    private Integer imgPage=0;
    private String tbLink;
    private String jdLink;


    private TextView tv_goods_proName;
    private TextView tv_goods_shopPrice;
    private TextView tv_goods_markerPrice;
    private TextView tv_goods_goodsStock;
    private TextView tv_goods_tbPrice;
    private TextView tv_goods_jdPrice;
    private RelativeLayout rl_goods_numsub;
    private RelativeLayout rl_goods_numadd;
    private EditText et_goods_buyNum;
    private Button btn_goods_tbBuy;
    private Button btn_goods_jdBuy;
    private RelativeLayout rl_goods_proDetail;
    private ImageView iv_gooods_back;
    private Button btn_goods_buyNow;
    private ImageView iv_gooods_cart;


    private  String  goodsNum;

    private  String  goodsImg;

    private  String  goodsName;

    private  String  goodsPrice;
    private Button btn_goods_joinCart;

    private String userId; //要从sharepreferece拿 ？？？？？？？？？？？？？？？？


    private boolean flagAdd=false;


    private ArrayList<GoodsCartBean.Data> orderList = new ArrayList<GoodsCartBean.Data>();



    private List<AreaListBean.Data> areaList = new ArrayList<AreaListBean.Data>();
    private RelativeLayout rl_goods_load_progress;

    private PathMeasure mPathMeasure;

    private float[] mCurrentPosition = new float[2];
    private ImageView iv_goods_cart_rmb;
    private RelativeLayout rl_activity_goods;
    private ImageView iv_goods_Collection;


    private String[] goodsIds;

    private String goodsIdString;


    private boolean collectFlag=false;

    private String newStringGoodsId="";
    private RelativeLayout rl_goods_proComment;

    private int stockNum=0;
    private ImageView iv_goods_tblogo;
    private ImageView iv_goods_jdlogo;
    private TextView tv_goods_tb;
    private TextView tv_goods_jd;
    private TextView tv_goods_tbfunhao;
    private TextView tv_goods_jdfunhao;
    private GoodsNoScrollListview lv_goods_common_list;

    private GoodsCommonAdapter<GoodsCommonBean.Items> goodsCommonAdapter;

    private List<GoodsCommonBean.Items> commonList= new ArrayList<GoodsCommonBean.Items>();

    private boolean commFlag = false;
    private ImageView iv_goods_common;

    LayoutInflater inflater;

    LayoutInflater inflaterOne;

    View viewFoot;

    View viewFootOne;



    private int commPage =1;

    private int totalNum = 0;
    private Button btn_goods_comm_more;
    private RelativeLayout rl_goods_comm_foot;
    private RelativeLayout rl_goods_comm_footone;
    private View view_goods_detai;
    private RelativeLayout rl_goods_link;

    private String userName;
    private RelativeLayout rl_goods_link_tb;
    private RelativeLayout rl_goods_link_jd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        StatusBarCompat.compat(this, Color.parseColor("#212121"));


        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
         userName = shared_prefs.getString("username","");
         userId = shared_prefs.getString("userId","");



        Intent intent =getIntent();
        Bundle bundle =  intent.getBundleExtra("bundle");
        goodsId = bundle.getString("goodsId");

        Log.i(TAG,"goodsId"+goodsId);


        initView();
        initData();
        initEvent();


    }

    private void initView() {

        view_goods_detai = ((View) findViewById(R.id.view_goods_detai));


        sv_goods_scrollview = ((GoodsScrollView) findViewById(R.id.sv_goods_scrollview));
        rl_goods_head = ((RelativeLayout) findViewById(R.id.rl_goods_head));
        tv_goods_title_content = ((TextView) findViewById(R.id.tv_goods_title_content));

        rl_goods_goodsShow = ((ViewPager) findViewById(R.id.rl_goods_goodsShow));

        iv_goods_turnleft = ((ImageView) findViewById(R.id.iv_goods_turnleft));
        iv_goods_turnright = ((ImageView) findViewById(R.id.iv_goods_turnright));
        tv_goods_proName = ((TextView) findViewById(R.id.tv_goods_proName));
        tv_goods_shopPrice = ((TextView) findViewById(R.id.tv_goods_shopPrice));
        tv_goods_markerPrice = ((TextView) findViewById(R.id.tv_goods_markerPrice));
        tv_goods_goodsStock = ((TextView) findViewById(R.id.tv_goods_goodsStock));



        rl_goods_numsub = ((RelativeLayout) findViewById(R.id.rl_goods_numsub));
        rl_goods_numadd = ((RelativeLayout) findViewById(R.id.rl_goods_numadd));

        et_goods_buyNum = ((EditText) findViewById(R.id.et_goods_buyNum));




        rl_goods_proDetail = ((RelativeLayout) findViewById(R.id.rl_goods_proDetail));


        iv_gooods_back = ((ImageView) findViewById(R.id.iv_gooods_back));

        btn_goods_buyNow = ((Button) findViewById(R.id.btn_goods_buyNow));

        iv_gooods_cart = ((ImageView) findViewById(R.id.iv_gooods_cart));

        btn_goods_joinCart = ((Button) findViewById(R.id.btn_goods_joinCart));

        rl_goods_load_progress = ((RelativeLayout) findViewById(R.id.rl_goods_load_progress));

        iv_goods_cart_rmb = ((ImageView) findViewById(R.id.iv_goods_cart_rmb));

        rl_activity_goods = ((RelativeLayout) findViewById(R.id.rl_activity_goods));

        iv_goods_Collection = ((ImageView) findViewById(R.id.iv_goods_Collection));

        rl_goods_proComment = ((RelativeLayout) findViewById(R.id.rl_goods_proComment));

        iv_goods_tblogo = ((ImageView) findViewById(R.id.iv_goods_tblogo));

        iv_goods_jdlogo = ((ImageView) findViewById(R.id.iv_goods_jdlogo));

        tv_goods_tb = ((TextView) findViewById(R.id.tv_goods_tb));

        tv_goods_jd = ((TextView) findViewById(R.id.tv_goods_jd));

        tv_goods_tbfunhao = ((TextView) findViewById(R.id.tv_goods_tbfunhao));

        tv_goods_jdfunhao = ((TextView) findViewById(R.id.tv_goods_jdfunhao));

        tv_goods_tbPrice = ((TextView) findViewById(R.id.tv_goods_tbPrice));

        tv_goods_jdPrice = ((TextView) findViewById(R.id.tv_goods_jdPrice));


        btn_goods_tbBuy = ((Button) findViewById(R.id.btn_goods_tbBuy));

        btn_goods_jdBuy = ((Button) findViewById(R.id.btn_goods_jdBuy));

        lv_goods_common_list = ((GoodsNoScrollListview) findViewById(R.id.lv_goods_common_list));

        iv_goods_common = ((ImageView) findViewById(R.id.iv_goods_common));

         inflater =LayoutInflater.from(getApplicationContext());

         viewFoot =inflater.inflate(R.layout.goods_comm_list_foot,null);


        rl_goods_comm_foot = ((RelativeLayout) viewFoot.findViewById(R.id.rl_goods_comm_foot));

        inflaterOne = LayoutInflater.from(getApplicationContext());


        viewFootOne =inflaterOne.inflate(R.layout.goods_comm_list_foot_one,null);

        rl_goods_comm_footone = ((RelativeLayout) viewFootOne.findViewById(R.id.rl_goods_comm_footone));

        btn_goods_comm_more = ((Button) viewFoot.findViewById(R.id.btn_goods_comm_more));


        rl_goods_link = ((RelativeLayout) findViewById(R.id.rl_goods_link));

        rl_goods_link_tb = ((RelativeLayout) findViewById(R.id.rl_goods_link_tb));

        rl_goods_link_jd = ((RelativeLayout) findViewById(R.id.rl_goods_link_jd));


    }

    private void initData() {

        //初始化 收藏键

        SharedPreferences sharedPreferences= getSharedPreferences(GoodsContant.GOODSCOLLECTIONPREFS,this.MODE_PRIVATE);
        goodsIdString =  sharedPreferences.getString("goodsId","");


       if(goodsIdString!=null&goodsIdString!="") {

            goodsIds = goodsIdString.split(",");


           for (int i = 0; i < goodsIds.length; i++) {

               if (goodsIds[i].equals(goodsId)){

                   collectFlag=true;


                    iv_goods_Collection.setImageResource(R.drawable.collect_check5);


               }


           }

       }


        rl_goods_head.getBackground().setAlpha(0);
        int initColor = tv_goods_title_content.getTextColors().getDefaultColor();
        int initR = Color.red(initColor);
        int initG = Color.green(initColor);
        int initB = Color.blue(initColor);
        int newColor = Color.argb(0,initR, initG, initB);
        tv_goods_title_content.setTextColor(newColor);

        getData();



        goodsCommonAdapter = new GoodsCommonAdapter<GoodsCommonBean.Items>(getApplicationContext(),commonList,R.layout.article_comment_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCommonBean.Items items, int position) {
                //TextView tv_goods_common_name = viewHolder.getViewById(R.id.tv_goods_common_name);
                //TextView tv_goods_common_time= viewHolder.getViewById(R.id.tv_goods_common_time);

               // TextView tv_goods_common_content = viewHolder.getViewById(R.id.tv_goods_common_content);



                TextView tv_article_comment_username=viewHolder.getViewById(R.id.tv_article_comment_username);

                TextView tv_article_comment_time= viewHolder.getViewById(R.id.tv_article_comment_time);

                TextView tv_position = viewHolder.getViewById(R.id.tv_position);

                TextView tv_article_content= viewHolder.getViewById(R.id.tv_article_content);

                tv_position.setVisibility(View.INVISIBLE);



                tv_article_comment_username.setText(items.user_name);

                Long time = Long.parseLong(items.add_time)*1000;

                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


                String str = sdf.format(time);


                tv_article_comment_time.setText(str);

                tv_article_content.setText(items.content);



            }
        };




        lv_goods_common_list.addFooterView(viewFoot);
        lv_goods_common_list.addFooterView(viewFootOne);


        rl_goods_comm_foot.setVisibility(View.GONE);
        rl_goods_comm_footone.setVisibility(View.GONE);


        lv_goods_common_list.setAdapter(goodsCommonAdapter);



        getCommonData();




    }


    private void getCommonData(){


        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","goods_comment");

        requestParams.addBodyParameter("goods_id",goodsId+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GoodsCommonBean goodsCommonBean = gson.fromJson(result,GoodsCommonBean.class);

                commonList.clear();

                commonList.addAll(goodsCommonBean.data.items);

                commPage=Integer.parseInt(goodsCommonBean.data.total_pages);

                totalNum = Integer.parseInt(goodsCommonBean.data.total);

                   if(totalNum>0){

                       if(commPage>1){


                           rl_goods_comm_foot.setVisibility(View.VISIBLE);
                                 //lv_goods_common_list.addFooterView(viewFoot);

                             }

                    }else{

                       rl_goods_comm_footone.setVisibility(View.VISIBLE);


                       // lv_goods_common_list.addFooterView(viewFootOne);

                  }




                goodsCommonAdapter.notifyDataSetChanged();


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

        rl_goods_load_progress.setVisibility(View.VISIBLE);

            RequestParams requestParams =new RequestParams(GoodsHttpUtils.GOODSDETAILURL+goodsId);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Gson gson =new Gson();

                    GoodsBean goodsBean = gson.fromJson(result, GoodsBean.class);

                    tv_goods_proName.setText(goodsBean.data.goods_name);
                    tv_goods_shopPrice.setText( goodsBean.data.shop_price);
                    tv_goods_markerPrice.setText(goodsBean.data.market_price);
                    tv_goods_goodsStock.setText(goodsBean.data.goods_number);
                    tv_goods_tbPrice.setText(goodsBean.data.tb_price);
                    tv_goods_jdPrice.setText(goodsBean.data.jd_price);


                    goodsImg=goodsBean.data.goods_thumb;
                    goodsName=goodsBean.data.goods_name;
                    goodsPrice=goodsBean.data.shop_price;

                    view_goods_detai.setVisibility(View.VISIBLE);


                    stockNum = Integer.parseInt(goodsBean.data.goods_number);


                    if(stockNum<1){
                        btn_goods_buyNow.setEnabled(false);

                        btn_goods_buyNow.setBackgroundColor(Color.LTGRAY);


                        btn_goods_joinCart.setEnabled(false);

                        btn_goods_joinCart.setBackgroundColor(Color.LTGRAY);


                    }else{


                        btn_goods_buyNow.setEnabled(true);


                        btn_goods_joinCart.setEnabled(true);

                    }


                    goodsPicDetail.clear();

                    goodsPicDetail.addAll(PictureHandle.getImageSrc(goodsBean.data.goods_desc));

                    Log.i(TAG,""+PictureHandle.getImageSrc(goodsBean.data.goods_desc)+"");




                    tbLink= goodsBean.data.tb_link;

                    jdLink =goodsBean.data.jd_link;

                    Log.i(TAG,"tbLink"+tbLink);
                    Log.i(TAG,"jdLink"+jdLink);


                    //iv_goods_tblogo
                    //iv_goods_jdlogo
                    //tv_goods_tb
                    //tv_goods_jd
                    //tv_goods_tbfunhao
                    //tv_goods_jdfunhao
                    //tv_goods_tbPrice
                    //tv_goods_jdPrice
                    //btn_goods_tbBuy
                    //btn_goods_jdBuy


                    if(tbLink==null||("0.00").equals(goodsBean.data.tb_price)){

                        rl_goods_link_tb.setVisibility(View.GONE);



                    }

                    if(jdLink==null||("0.00").equals(goodsBean.data.jd_price)){

                       rl_goods_link_jd.setVisibility(View.GONE);

                    }

                    if(("0.00").equals(goodsBean.data.jd_price)&&("0.00").equals(goodsBean.data.tb_price)){

                        rl_goods_link.setVisibility(View.GONE);


                    }








                    rl_goods_load_progress.setVisibility(View.GONE);




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


    private void initEvent() {
        sv_goods_scrollview.setScrollViewListener(new GoodsScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {

                titleAnima(y);

            }
        });

        goodsPagerAdapter =new GoodsPagerAdapter(getApplicationContext(),goodsPicList);

        rl_goods_goodsShow.setAdapter(goodsPagerAdapter);

        getImgData();

        iv_goods_turnleft.setOnClickListener(this);
        iv_goods_turnright.setOnClickListener(this);
        rl_goods_numsub.setOnClickListener(this);
        rl_goods_numadd.setOnClickListener(this);
        btn_goods_tbBuy.setOnClickListener(this);
        btn_goods_jdBuy.setOnClickListener(this);
        rl_goods_proDetail.setOnClickListener(this);
        iv_gooods_back.setOnClickListener(this);
        btn_goods_buyNow.setOnClickListener(this);


        iv_gooods_cart.setOnClickListener(this);
        btn_goods_joinCart.setOnClickListener(this);

        iv_goods_Collection.setOnClickListener(this);

        rl_goods_proComment.setOnClickListener(this);

        btn_goods_comm_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =new Intent(GoodsActivity.this,GoodsCommentActivity.class);
                Bundle bundle= new Bundle();

                bundle.putString("goodsId",goodsId);

                intent.putExtra("bundle",bundle);

                startActivity(intent);


            }
        });




    }



    public void titleAnima(int y) {
        int scrollHeight = sv_goods_scrollview.getChildAt(0).getHeight()
                - sv_goods_scrollview.getHeight();
        float scrollPercent = (float) y / scrollHeight;
        rl_goods_head.getBackground().setAlpha((int) (255 * scrollPercent));

         int color = tv_goods_title_content.getTextColors().getDefaultColor();
         int r = Color.red(color);
         int g = Color.green(color);
         int b = Color.blue(color);
        int changeToColor = Color.argb((int) (255 * scrollPercent), r, g, b);

        tv_goods_title_content.setTextColor(changeToColor);




    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_goods_turnleft:
                goodsTurnLeft();
                break;
            case R.id.iv_goods_turnright:
                goodsTurnRight();
                break;
            case R.id.rl_goods_numsub:
                subNum();

                break;
            case R.id.rl_goods_numadd:
                addNum();
                break;
            case R.id.btn_goods_tbBuy:
                tbBuy();
                break;
            case R.id.btn_goods_jdBuy:
                jdBuy();
                break;

            case R.id.rl_goods_proDetail:
                toGoodsDetail();
                break;

            case R.id.iv_gooods_back:
                finish();

                break;

            case R.id.btn_goods_buyNow:

                flagAdd=true;

                if(userId!=""){

                    toBuyNow();


                }else{

                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.iv_gooods_cart:

                if(userId!=""){

                    toCart();

                }else{

                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btn_goods_joinCart:

                btn_goods_joinCart.setText("加入中");



                if(userId!=""){

                    toJoinCart();


                }else{

                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.iv_goods_Collection:

                if(userId!=""){

                    toCollection();


                }else{

                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.rl_goods_proComment:


                if(commFlag==false){


                    lv_goods_common_list.setVisibility(View.VISIBLE);

                    iv_goods_common.setImageResource(R.drawable.goods_comm_zhankai);

                    commFlag=true;

                }else{

                    lv_goods_common_list.setVisibility(View.GONE);

                    iv_goods_common.setImageResource(R.drawable.goods_comm_shouqi);


                    commFlag=false;

                }





                break;




        }

    }

    private void toCollection() {

        SharedPreferences sharedPreferences =getSharedPreferences(GoodsContant.GOODSCOLLECTIONPREFS,this.MODE_PRIVATE);

        if(collectFlag==true){

            //取消收藏干掉  collectFlag变为false

            //存在收藏Id 什么也不做

            if(goodsIdString!=null&&goodsIdString!=""){

                newStringGoodsId="";



                for(int i = 0;i<goodsIds.length;i++){

                    if(goodsIds[i].equals(goodsId)){
                        //什么也不做

                    }else{

                        //重新拼字符串
                        newStringGoodsId=newStringGoodsId+goodsIds[i]+",";


                        Log.i(TAG,"newStringGoodsId"+newStringGoodsId);

                    }


                }


            }


            SharedPreferences.Editor editor=  sharedPreferences.edit();



            editor.putString("goodsId",newStringGoodsId);

            editor.commit();








             toCancelCollection();



        }else{

            //不存在 存入

            SharedPreferences.Editor editor=  sharedPreferences.edit();



            editor.putString("goodsId",goodsIdString+","+goodsId);

            editor.commit();


            toJoinCollection();




        }



    }

    private void toCancelCollection() {





        //app.linchom.com/appapi.php?act=drop_collect_article&type=2&user_id=12&id=415

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","drop_collect_article");


        requestParams.addBodyParameter("user_id",userId);

        requestParams.addBodyParameter("type","2");

        requestParams.addBodyParameter("id",goodsId);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_style,
                        (ViewGroup) findViewById(R.id.ll_toast));
                ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_collect);
                image.setImageResource(R.drawable.collect_success1);
                TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
                text.setText("取消收藏");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                ToastUtil.showMyToast(toast, 1000);

               // Toast.makeText(getApplicationContext(),"取消收藏",Toast.LENGTH_SHORT).show();
                iv_goods_Collection.setImageResource(R.drawable.article_collect3);
                collectFlag=false;


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_style,
                        (ViewGroup) findViewById(R.id.ll_toast));
                ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_collect);
                image.setImageResource(R.drawable.collect_success1);
                TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
                text.setText("取消失败");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                ToastUtil.showMyToast(toast, 1000);


               // Toast.makeText(getApplicationContext(),"取消失败",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });








    }

    private void toJoinCollection() {

        //app.linchom.com/appapi.php?act=add_collect_goods&user_id=12&goods_id=20

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","add_collect_goods");

        requestParams.addBodyParameter("user_id",userId);

        requestParams.addBodyParameter("goods_id",goodsId);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                iv_goods_Collection.setImageResource(R.drawable.collect_check5);

                collectFlag=true;


                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_style,
                        (ViewGroup) findViewById(R.id.ll_toast));
                ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_collect);
                image.setImageResource(R.drawable.collect_success1);
                TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
                text.setText("收藏成功");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                ToastUtil.showMyToast(toast, 1000);



               // Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {


                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_style,
                        (ViewGroup) findViewById(R.id.ll_toast));
                ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_collect);
                image.setImageResource(R.drawable.collect_success1);
                TextView text = (TextView) layout.findViewById(R.id.tv_toast_collect);
                text.setText("收藏失败");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                ToastUtil.showMyToast(toast, 1000);



                //Toast.makeText(getApplicationContext(),"收藏失败",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }


    private void toJoinCart() {


        if(stockNum<1){

            Toast.makeText(getApplicationContext(),"库存不足,不能加入",Toast.LENGTH_SHORT).show();
        }else {

            getCartData();

        }





    }

    private void getCartData() {



        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","addcart");

        requestParams.addBodyParameter("goods_id",goodsId+"");

        requestParams.addBodyParameter("goods_number",et_goods_buyNum.getText()+"");

        requestParams.addBodyParameter("user_id",userId+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {



                Gson gson =new Gson();

                JoinCartBean joinCartBean =  gson.fromJson(result, JoinCartBean.class);


                String str = joinCartBean.data.session_id;


                Log.i(TAG,"result"+joinCartBean.result);

                Log.i(TAG,"str"+str);


                //Toast.makeText(getApplicationContext(),"加入成功",Toast.LENGTH_SHORT).show();

                //做判断是立即购买 还是加入购物车
                if(flagAdd==true){
                    //访问购物车列表


                    ergodicCart();


                }else{

                    btn_goods_joinCart.setText("已添加购物车");
                    btn_goods_joinCart.setEnabled(false);


                    Toast.makeText(getApplicationContext(),"加入成功",Toast.LENGTH_SHORT).show();

                    addCart(iv_goods_cart_rmb);


                }



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Toast.makeText(getApplicationContext(),"加入失败",Toast.LENGTH_SHORT).show();

                btn_goods_joinCart.setText("加入购物车");




            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }


    private void ergodicCart(){

        //Toast.makeText(getApplicationContext(),"遍历购物车",Toast.LENGTH_SHORT).show();

        //http://app.linchom.com/appapi.php?act=cartgoods
        // user_id=12


        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","cartgoods");

        requestParams.addBodyParameter("user_id",userId+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

               // Toast.makeText(getApplicationContext(),"result"+result,Toast.LENGTH_SHORT).show();


                Gson gson = new Gson();

                GoodsCartBean goodsCartBean =  gson.fromJson(result, GoodsCartBean.class);

                orderList.clear();

                orderList.addAll(goodsCartBean.data);

                //把这个list传过去;

                //初始化默认地址

                initDefaultAddress();


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


    private void initDefaultAddress(){

        //地址也要传过去

        //Toast.makeText(getApplicationContext(),"初始化默认地址",Toast.LENGTH_SHORT).show();


        //遍历遍历地址

        //http://app.linchom.com/appapi.php?act=consignee&user_id=12

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","consignee");

        requestParams.addBodyParameter("user_id",userId+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                Log.i(TAG,"收货地址列表result"+result);

                Log.i(TAG,"收货地址列表areaList"+areaList);


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

                Log.i(TAG,"收货地址列表ex"+ex);
                Log.i(TAG,"收货地址列表exareaList"+areaList);

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

    private void toCart() {

        Intent intent =new Intent(GoodsActivity.this,GoodsCartActivity.class);


        startActivity(intent);



    }

    private void defaultArea(){
        //http://app.linchom.com/appapi.php?act=default_consignee&user_id=135&address_id=36

        //Toast.makeText(getApplicationContext(),areaList.get(0).address_id,Toast.LENGTH_SHORT).show();

        if(areaList.size()!=0) {


            RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);


            requestParams.addBodyParameter("act", "default_consignee");
            requestParams.addBodyParameter("user_id", userId + "");
            requestParams.addBodyParameter("address_id", areaList.get(0).address_id + "");


            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                   // Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();

                    //orderList
                   // areaList.get(0)        传过去

                    rl_goods_load_progress.setVisibility(View.GONE);


                    btn_goods_buyNow.setEnabled(true);

                    btn_goods_buyNow.setText("立即购买");





                    Intent intent = new Intent(GoodsActivity.this,GoodsOrderActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("orderList",orderList);

                    bundle.putSerializable("orderCartList",null);

                    bundle.putSerializable("areaList",areaList.get(0));

                    Log.i(TAG,"有地址传的地址"+areaList.get(0));

                    intent.putExtra("bundle",bundle);

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

        }else{

            //无地址不用修改 直接传null

            Log.i(TAG,"没地址传的地址"+null);

            rl_goods_load_progress.setVisibility(View.GONE);



            btn_goods_buyNow.setEnabled(true);

            btn_goods_buyNow.setText("立即购买");


            Intent intent = new Intent(GoodsActivity.this,GoodsOrderActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("orderList",orderList);

                    /// 没收货地址   再说

                    bundle.putSerializable("areaList",null);

                    bundle.putSerializable("orderCartList",null);

                    intent.putExtra("bundle",bundle);

                    startActivity(intent);


        }





    }

    private void toBuyNow() {

        btn_goods_buyNow.setEnabled(false);

        btn_goods_buyNow.setText("提交中");

        if(stockNum<1){

            Toast.makeText(getApplicationContext(),"库存不足",Toast.LENGTH_SHORT).show();
        }else{



            rl_goods_load_progress.setVisibility(View.VISIBLE);



            //先清空购物车 然后去拿购物车的第一件商品 然后再跳到订单详情页面；

            //去取地址 把第一个改成默认地址  (没默认地址会提交失败)

            //


            RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

            requestParams.addBodyParameter("act","clear_cart");
            requestParams.addBodyParameter("user_id",userId+"");

            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    // Toast.makeText(GoodsActivity.this,"清空购物车",Toast.LENGTH_SHORT).show();

                    //加入购物车

                    getCartData();

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

    private void toGoodsDetail() {

        Intent intent =new Intent(GoodsActivity.this,GoodsDetailActivity.class);
        Bundle bundle =new Bundle();


        bundle.putSerializable("goodsPicDetail",goodsPicDetail);
//        bundle.putSerializable();

        intent.putExtra("bundle",bundle);

        startActivity(intent);



    }

    private void tbBuy() {

        Intent intent =new Intent(GoodsActivity.this,TblinkActivity.class);
        Bundle bundle =new Bundle();

        bundle.putString("tblink",tbLink);

        intent.putExtra("bundle",bundle);

        startActivity(intent);



    }

    private void jdBuy() {

        Intent intent =new Intent(GoodsActivity.this,JdlinkActivity.class);
        Bundle bundle =new Bundle();

        bundle.putString("jdlink",jdLink);

        intent.putExtra("bundle",bundle);

        startActivity(intent);

    }

    private void subNum() {


        buyNum =Integer.parseInt(et_goods_buyNum.getText().toString()) ;

       if(buyNum>1) {

           buyNum--;

           et_goods_buyNum.setText(buyNum + "");

       }


    }

    private void addNum() {

        buyNum =Integer.parseInt(et_goods_buyNum.getText().toString()) ;

        if(buyNum<(Integer.parseInt(tv_goods_goodsStock.getText().toString()))){

            buyNum++;

            et_goods_buyNum.setText(buyNum+"");


        }else{

            Toast.makeText(getApplicationContext(),"库存不足",Toast.LENGTH_SHORT).show();
        }




    }



    private void goodsTurnLeft() {
        if(imgPage>0){


            imgPage--;
            rl_goods_goodsShow.setCurrentItem(imgPage);

        }

    }

    private void goodsTurnRight() {

        if(imgPage<2){
            imgPage++;
            rl_goods_goodsShow.setCurrentItem(imgPage);

        }

    }


    private void getImgData() {

        RequestParams requestParams =new RequestParams(GoodsHttpUtils.GOODSDETAILURL+goodsId);


        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson =new Gson();

                GoodsBean goodsBean = gson.fromJson(result, GoodsBean.class);


                String imgUrlChange = goodsBean.data.goods_thumb;

                if("h".equals(imgUrlChange.substring(0,1))){

                }else{
                    imgUrlChange=GoodsHttpUtils.IMGURL+imgUrlChange;
                }




                String imgUrlChange1 = goodsBean.data.goods_img;
                if("h".equals(imgUrlChange1.substring(0,1))){

                }else{
                    imgUrlChange1=GoodsHttpUtils.IMGURL+imgUrlChange1;
                }


                String imgUrlChange2 = goodsBean.data.original_img;
                if("h".equals(imgUrlChange2.substring(0,1))){

                }else{
                    imgUrlChange2=GoodsHttpUtils.IMGURL+imgUrlChange2;
                }





                goodsPicList.add(imgUrlChange);
                goodsPicList.add(imgUrlChange1);
                goodsPicList.add(imgUrlChange2);

                Log.i(TAG,"picture"+GoodsHttpUtils.IMGURL+goodsBean.data.goods_thumb);

                goodsPagerAdapter.notifyDataSetChanged();


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


    private void addCart( ImageView iv) {
        final ImageView goods = new ImageView(GoodsActivity.this);
        goods.setImageDrawable(iv.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        rl_activity_goods.addView(goods, params);

        int[] parentLocation = new int[2];
        rl_activity_goods.getLocationInWindow(parentLocation);

        int startLoc[] = new int[2];
        btn_goods_joinCart.getLocationInWindow(startLoc);///////////////

        int endLoc[] = new int[2];
        iv_gooods_cart.getLocationInWindow(endLoc);


        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        float toX = endLoc[0] - parentLocation[0] + iv_gooods_cart.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

        Path path = new Path();
        path.moveTo(startX, startY);

        path.quadTo(startX, (startY+toY)/2, toX, toY);

        mPathMeasure = new PathMeasure(path, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (Float) animation.getAnimatedValue();

                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {

                rl_activity_goods.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }



}
