package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsPagerAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsBean;
import linchom.com.linchomspace.shopping.widget.GoodsScrollView;

public class GoodsActivity extends AppCompatActivity implements View.OnClickListener{
//修改时间10/19
    private static final String TAG = "GoodsActivity";
    private GoodsScrollView sv_goods_scrollview;
    private RelativeLayout rl_goods_head;
    private TextView tv_goods_title_content;

    private int buyNum;

    GoodsPagerAdapter goodsPagerAdapter;

    private  String goodsId;//

    private List<String> goodsPicList =new ArrayList<String>();

    private List<String> goodsPicDetail =new ArrayList<String>();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        Intent intent =getIntent();
        Bundle bundle =  intent.getBundleExtra("bundle");
        goodsId = bundle.getString("goodsId");




        initView();
        initData();
        initEvent();


    }

    private void initView() {

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

        tv_goods_tbPrice = ((TextView) findViewById(R.id.tv_goods_tbPrice));

        tv_goods_jdPrice = ((TextView) findViewById(R.id.tv_goods_jdPrice));

        rl_goods_numsub = ((RelativeLayout) findViewById(R.id.rl_goods_numsub));
        rl_goods_numadd = ((RelativeLayout) findViewById(R.id.rl_goods_numadd));

        et_goods_buyNum = ((EditText) findViewById(R.id.et_goods_buyNum));

        btn_goods_tbBuy = ((Button) findViewById(R.id.btn_goods_tbBuy));

        btn_goods_jdBuy = ((Button) findViewById(R.id.btn_goods_jdBuy));




    }

    private void initData() {

        rl_goods_head.getBackground().setAlpha(0);
        int initColor = tv_goods_title_content.getTextColors().getDefaultColor();
        int initR = Color.red(initColor);
        int initG = Color.green(initColor);
        int initB = Color.blue(initColor);
        int newColor = Color.argb(0,initR, initG, initB);
        tv_goods_title_content.setTextColor(newColor);

        getData();






    }

    private void getData() {

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




                    tbLink= goodsBean.data.tb_link;

                    jdLink =goodsBean.data.jd_link;

                    Log.i(TAG,"tbLink"+tbLink);
                    Log.i(TAG,"jdLink"+jdLink);


                    if(tbLink==null||("0.00").equals(goodsBean.data.tb_price)){
                        btn_goods_tbBuy.setVisibility(View.INVISIBLE);

                    }

                    if(jdLink==null||("0.00").equals(goodsBean.data.jd_price)){

                        btn_goods_jdBuy.setVisibility(View.INVISIBLE);

                    }



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


        }

    }

    private void tbBuy() {



    }

    private void jdBuy() {

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




                goodsPicList.add(GoodsHttpUtils.IMGURL+goodsBean.data.goods_thumb);
                goodsPicList.add(GoodsHttpUtils.IMGURL+goodsBean.data.goods_img);
                goodsPicList.add(GoodsHttpUtils.IMGURL+goodsBean.data.original_img);

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

}
