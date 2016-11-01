package linchom.com.linchomspace.service;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.DisplayUtil;
import linchom.com.linchomspace.service.fragment.DemandFragment;
import linchom.com.linchomspace.service.fragment.FuwuFragment;
import linchom.com.linchomspace.service.fragment.LogFragment;

public class ServiceContentActivity extends AppCompatActivity {


    private List<Fragment> fragmentList =new ArrayList<Fragment>();

    private List<String> tabList = new ArrayList<String>();

    private FixedIndicatorView indicator_service_head;
    private ViewPager vp_service_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_content);


        initView();

        initData();


        initEvent();

    }

    private void initView() {

        indicator_service_head = ((FixedIndicatorView) findViewById(R.id.indicator_service_head));


        vp_service_content = ((ViewPager) findViewById(R.id.vp_service_content));
    }

    private void initData() {

    }

    private void initEvent() {


        tabList.add("需求");
        tabList.add("服务");

        tabList.add("日志");

        fragmentList.add(new DemandFragment());
        fragmentList.add(new FuwuFragment());

        fragmentList.add(new LogFragment());



        set(indicator_service_head, tabList);

        vp_service_content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vp_service_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                indicator_service_head.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


    private void set(Indicator indicator, List<String> tabList) {
        indicator.setAdapter(new MyAdapter(tabList));

        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.LTGRAY, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.tab_top_text_2);
        int unSelectColor = getResources().getColor(R.color.tab_top_text_1);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

        indicator.setOnItemSelectListener(new Indicator.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View selectItemView, int select, int preSelect) {

                vp_service_content.setCurrentItem(select);


            }
        });


    }

    private class MyAdapter extends Indicator.IndicatorAdapter {

        private List<String> count;

        public MyAdapter(List<String> count) {
            super();
            this.count = count;
        }

        @Override
        public int getCount() {
            return count.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.service_tab_item,null);
            }

           TextView tv_service_tab = ((TextView) convertView.findViewById(R.id.tv_service_tab));


            tv_service_tab.setWidth(DisplayUtil.dipToPix(getApplicationContext(),50));


            tv_service_tab.setText(count.get(position));


            return convertView;
        }
    }



}
