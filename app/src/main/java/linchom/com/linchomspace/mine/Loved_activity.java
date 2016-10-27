package linchom.com.linchomspace.mine;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.fragment.FragAdapter;
import linchom.com.linchomspace.mine.fragment.goods_fragment;
import linchom.com.linchomspace.mine.fragment.news_fragment;

public class Loved_activity extends AppCompatActivity  implements View.OnClickListener{

    private ImageView iv_love_back;
    private ViewPager vp;
    private FragAdapter adapter;
    private Button rb_zixun;
    private Button rb_goods;


    List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved_activity);

        iv_love_back = ((ImageView) findViewById(R.id.iv_love_back));
        iv_love_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vp = ((ViewPager) findViewById(R.id.vp_viewPager));
        fragments.add(new news_fragment());
        fragments.add(new goods_fragment());
        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
//      vp.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        rb_zixun=(Button) findViewById(R.id.rb_zixun);
        rb_goods=(Button) findViewById(R.id.rb_goods);
        rb_zixun.setOnClickListener(this);
        rb_goods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_zixun:
                changeView(0);
//                System.out.println("zixun");
                rb_zixun.setBackgroundResource(R.drawable.buttonminus);
                rb_goods.setBackgroundResource(R.drawable.buttonminus1);
                break;

            case R.id.rb_goods:
                changeView(2);
//                System.out.println("rb_goods");
                rb_zixun.setBackgroundResource(R.drawable.buttonminus1);
                rb_goods.setBackgroundResource(R.drawable.buttonminus);
                break;


        }
    }
    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        vp.setCurrentItem(desTab, true);
    }


}


