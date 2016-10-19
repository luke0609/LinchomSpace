package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

    GoodsPagerAdapter goodsPagerAdapter;

    private  String goodsId;//

    private List<String> goodsPicList =new ArrayList<String>();

    private List<String> goodsPicDetail =new ArrayList<String>();
    private ViewPager rl_goods_goodsShow;
    private ImageView iv_goods_turnleft;
    private ImageView iv_goods_turnright;

    private Integer imgPage=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        Intent intent =getIntent();
        Bundle bundle =  intent.getBundleExtra("bundle");
        goodsId = bundle.getString("goodsId");

        Toast.makeText(getApplicationContext(),goodsId,Toast.LENGTH_SHORT).show();

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
