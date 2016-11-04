package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

public class GoodsDetailActivity extends AppCompatActivity {

    private  GoodsCommonAdapter<String> goodsCommonAdapter;


    private ArrayList<String> picDetailList;
    private ListView lv_goods_detail;
    private ImageView titlebar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");

        picDetailList=(ArrayList<String>)bundle.getSerializable("goodsPicDetail");



        initView();
        initData();
        initEvent();
    }

    private void initView() {
        lv_goods_detail = ((ListView) findViewById(R.id.lv_goods_detail));

        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));


    }

    private void initData() {

    }

    private void initEvent() {

        goodsCommonAdapter =new GoodsCommonAdapter<String>(getApplicationContext(),picDetailList,R.layout.goods_detail_layout) {
            @Override
            public void convert(GoodsViewHolder viewHolder, String s, int position) {

                ImageView imageView = viewHolder.getViewById(R.id.iv_goods_detail);

                if((s.substring(0,1)).equals("h")){
                    //不做任何改变

                    //Log.i("aaaaa","不做任何改变的"+str2);


                }else {

                    //Log.i("aaaaa","改变的"+str2);

                    s= GoodsHttpUtils.IMGURL+s;

                }




                GoodsXUtilsImage.displayone(imageView,s);

            }
        };

        lv_goods_detail.setAdapter(goodsCommonAdapter);

        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });


    }
}
