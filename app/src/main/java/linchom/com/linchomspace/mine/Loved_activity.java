package linchom.com.linchomspace.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.fragment.goods_fragment;
import linchom.com.linchomspace.mine.fragment.news_fragment;

public class Loved_activity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<Fragment>();
    private ViewPager vp_viewPager;
    private RadioGroup rg_rag;
    private RadioButton rb_goods;
    private RadioButton rb_zixun;
    private String userId = "12";
    private ImageView iv_love_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved_activity);


        initView();
        initData();
        initEvent();
    }

    private void initView() {

        iv_love_back = ((ImageView) findViewById(R.id.iv_love_back));

        vp_viewPager = ((ViewPager) findViewById(R.id.vp_viewPager));
        rg_rag = ((RadioGroup) findViewById(R.id.rg_rag));
        rb_goods = ((RadioButton) findViewById(R.id.rb_goods));
        rb_zixun = ((RadioButton) findViewById(R.id.rb_zixun));
        rb_zixun.setChecked(true);
    }

    private void initData() {
        news_fragment newsfragment=new news_fragment();
        Bundle bundle =new Bundle();
        bundle.putString("userId",userId);
        newsfragment.setArguments(bundle);

        goods_fragment goodsfragment=new goods_fragment();
        Bundle bundle1=new Bundle();
        bundle.putString("userId",userId);
        goodsfragment.setArguments(bundle1);


        fragments.add(newsfragment);
        fragments.add(goodsfragment);

        iv_love_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEvent() {

        vp_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return  fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        vp_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rb_zixun.setChecked(true);

                        break;
                    case 1:
                        rb_goods.setChecked(true);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg_rag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_goods:
                        vp_viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_zixun:
                        vp_viewPager.setCurrentItem(0);
                        break;
                }
            }
        });

    }



}
//        iv_love_back = ((ImageView) findViewById(R.id.iv_love_back));
//        iv_love_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        vp = ((ViewPager) findViewById(R.id.vp_viewPager));
//        fragments.add(new news_fragment());
//        fragments.add(new goods_fragment());
//
//        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
//        vp.setAdapter(adapter);
//        vp.setCurrentItem(0);
//
//        rb_zixun=(Button) findViewById(R.id.rb_zixun);
//        rb_goods=(Button) findViewById(R.id.rb_goods);


