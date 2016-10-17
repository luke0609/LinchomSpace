package linchom.com.linchomspace.chat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.ListActivityBean;
import linchom.com.linchomspace.chat.pojo.ListViewAdapter;
import linchom.com.linchomspace.chat.util.DisplayUtil;


public class ChatFragment extends Fragment {
    private int[] images;
    View view1;
    private BannerComponent bannerComponent;
    private IndicatorViewPager indicatorViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_chat, container, false);
        initData();
        initView();
        return view1;
    }

    @Override
    public void onStart() {
        super.onStart();
        bannerComponent.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) view1.findViewById(R.id.banner_viewPager);
        Indicator indicator = (Indicator) view1.findViewById(R.id.banner_indicator);
        // indicator.setScrollBar(new ColorBar(getActivity(), Color.GRAY, 30, ScrollBar.Gravity.CENTENT_BACKGROUND));
        viewPager.setOffscreenPageLimit(2);
        bannerComponent = new BannerComponent(indicator, viewPager, false);
        bannerComponent.setAdapter(adapter);
        bannerComponent.setAutoPlayTime(2500);

        //viewpager
        ViewPager viewPager2 = (ViewPager) view1.findViewById(R.id.moretab_viewPager);
        ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) view1.findViewById(R.id.moretab_indicator);

        float unSelectSize = 13;
        float selectSize = unSelectSize * 1.2f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.GRAY, Color.argb(100, 87, 87, 87)).setSize(selectSize, unSelectSize));

        scrollIndicatorView.setScrollBar(new ColorBar(getActivity(), Color.GRAY, 4));


        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager2);

        indicatorViewPager.setAdapter(new MyAdapter());
        indicatorViewPager.setPageOffscreenLimit(0);
    }

    private void initData() {
        images = new int[]{R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4};

    }


    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {


        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                // convertView = new View(container.getContext());
                convertView = View.inflate(getActivity(), R.layout.tab_guide, null);
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new ImageView(getActivity());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[position]);
            return convertView;
        }

        @Override
        public int getCount() {
            return images.length;
        }

    };

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"智能家居", "智能健康", "智能照管", "产品推介", "智能社区", "工程技术", "硬件开发", "机器人", "VR/AR", "协议标准", "需求对接", "品牌交流", "提问回答", "其他"};
        private String[] urls = {"", "2", "3", "4", "", "2", "3", "4", "", "2", "3", "4", "", "2", "3"};


        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {

            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.tab_top, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);

            int witdh = getTextWidth(textView);
            int padding = DisplayUtil.dipToPix(getActivity(), 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.2f) + padding);

            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            Log.i("convertView", "getView at position:" + position + " convertView:" + (convertView == null ? "null" : convertView.hashCode()));
            convertView = View.inflate(getActivity(), R.layout.viewpage_layout, null);
            ListView listView = ((ListView) convertView.findViewById(R.id.lv));
            // ListView listView =new ListView(container.getContext());

            ArrayList<ListActivityBean.Dongtai> dongtaiList = new ArrayList<ListActivityBean.Dongtai>();
            ListViewAdapter lvadpt = new ListViewAdapter(getActivity(), dongtaiList);
            listView.setAdapter(lvadpt);
            Log.i("convertView", "getView at position:" + position + "==========" + urls[position]);
            getDongtaiList(urls[position], lvadpt);


            return convertView;

        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }

    }

    private void getDongtaiList(String url, final ListViewAdapter adapter2) {
        String url2;
        url2 = url;
        String web = "http://10.40.5.7:8080/web/getdongtaibypage";
        web += url2;
        final ArrayList<ListActivityBean.Dongtai> dongtaiList = adapter2.getDongtaiList();
        RequestParams params = new RequestParams(web);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                ListActivityBean bean = gson.fromJson(result, ListActivityBean.class);
                dongtaiList.clear();
                dongtaiList.addAll(bean.dongtailist);

                System.out.println(dongtaiList);

                adapter2.notifyDataSetChanged();

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