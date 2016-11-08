package linchom.com.linchomspace.chat;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.TopicList;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.DateUtils;
import linchom.com.linchomspace.chat.util.DisplayUtil;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.homepage.progressbar.CircularProgress;
import linchom.com.linchomspace.login.widget.DepthPageTransformer;
import linchom.com.linchomspace.photoutil.NineGridTestLayout;
import linchom.com.linchomspace.search.SearchActivity;


public class ChatFragment extends Fragment {
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    private int[] images;
    View view1;
    private BannerComponent bannerComponent;
    private IndicatorViewPager indicatorViewPager;
    private String chatId;
    private ImageView to_publish;
    private int pageCount = 1;
    private int startPage = 1;
    private CircularProgress CircularProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_chat, container, false);
//        initData();
        initView();
        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onStart() {
        super.onStart();
        //  bannerComponent.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        // bannerComponent.stopAutoPlay();
    }

    private void initView() {
        to_publish = ((ImageView) view1.findViewById(R.id.chat_search));
        to_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatPublishActiviy.class);
                startActivity(intent);
            }
        });

        CircularProgress = ((CircularProgress) view1.findViewById(R.id.progressBar_chat));
        CircularProgress.setVisibility(View.VISIBLE);
//        ViewPager viewPager = (ViewPager) view1.findViewById(R.id.banner_viewPager);
//        Indicator indicator = (Indicator) view1.findViewById(R.id.banner_indicator);
//        // indicator.setScrollBar(new ColorBar(getActivity(), Color.GRAY, 30, ScrollBar.Gravity.CENTENT_BACKGROUND));
//        viewPager.setOffscreenPageLimit(2);
//        bannerComponent = new BannerComponent(indicator, viewPager, false);
//        bannerComponent.setAdapter(adapter);
//        bannerComponent.setAutoPlayTime(2500);

        //viewpager
        ViewPager viewPager2 = (ViewPager) view1.findViewById(R.id.moretab_viewPager);
        ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) view1.findViewById(R.id.moretab_indicator);
        viewPager2.setPageTransformer(true, new DepthPageTransformer());
        float unSelectSize = 14;
        float selectSize = unSelectSize * 1.1f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.WHITE, Color.WHITE).setSize(selectSize, unSelectSize));

        scrollIndicatorView.setScrollBar(new ColorBar(getActivity(), Color.parseColor("#EEC900"), 4));


        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager2);

        indicatorViewPager.setAdapter(new MyAdapter());
        indicatorViewPager.setPageOffscreenLimit(0);

        //  indicatorViewPager.setCurrentItem(5,true);


        Bundle bundle = getArguments();
        if (bundle != null) {
            chatId = bundle.getString("chatId");
            switch (chatId) {
                case "0":
                    indicatorViewPager.setCurrentItem(0, true);
                    break;
                case "1":
                    indicatorViewPager.setCurrentItem(1, true);
                    break;
                case "2":
                    indicatorViewPager.setCurrentItem(2, true);
                    break;
                case "3":
                    indicatorViewPager.setCurrentItem(3, true);
                    break;
                case "4":
                    indicatorViewPager.setCurrentItem(4, true);
                    break;
                case "5":
                    indicatorViewPager.setCurrentItem(5, true);
                    break;
                case "6":
                    indicatorViewPager.setCurrentItem(6, true);
                    break;
                case "7":
                    indicatorViewPager.setCurrentItem(7, true);
                    break;
                case "8":
                    indicatorViewPager.setCurrentItem(8, true);
                    break;
                case "9":
                    indicatorViewPager.setCurrentItem(9, true);
                    break;
                case "10":
                    indicatorViewPager.setCurrentItem(10, true);
                    break;
                case "11":
                    indicatorViewPager.setCurrentItem(11, true);
                    break;
                case "12":
                    indicatorViewPager.setCurrentItem(12, true);
                    break;
                case "13":
                    indicatorViewPager.setCurrentItem(13, true);
                    break;
                case "14":
                    indicatorViewPager.setCurrentItem(14, true);
                    break;

            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("search_type", "chat");
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
//
//    private void initData() {
//        images = new int[]{R.drawable.gp1, R.drawable.gp2, R.drawable.gp3};
//
//    }


//    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
//
//
//        @Override
//        public View getViewForTab(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                // convertView = new View(container.getContext());
//                convertView = View.inflate(getActivity(), R.layout.tab_guide, null);
//            }
//            return convertView;
//        }
//
//        @Override
//        public View getViewForPage(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = new ImageView(getActivity());
//                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            }
//            ImageView imageView = (ImageView) convertView;
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setImageResource(images[position]);
//            return convertView;
//        }
//
//        @Override
//        public int getCount() {
//            return images.length;
//        }
//
//    };

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"智能家居", "智能健康", "智能管照", "产品推荐", "智能社区", "工程技术", "硬件开发", "机器人", "VR/AR", "协议标准", "需求对接", "品牌交流", "提问回答", "其他"};


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
        public View getViewForPage(final int position, View convertView, ViewGroup container) {
            Log.i("convertView", "getView at position:" + position + " convertView:" + (convertView == null ? "null" : convertView.hashCode()));
            convertView = View.inflate(getActivity(), R.layout.viewpage_layout, null);
            startPage = 1;
            pageCount = 1;
            final PullToRefreshListView listView = ((PullToRefreshListView) convertView.findViewById(R.id.lv));
            final List<TopicList.DataBean.ItemsBean> topicList = new ArrayList<>();
            final CommonAdapter<TopicList.DataBean.ItemsBean> adapter = new CommonAdapter<TopicList.DataBean.ItemsBean>(getActivity().getApplicationContext(), topicList, R.layout.topiclist_layout) {


                @Override
                public void convert(ViewHolder viewHolder, TopicList.DataBean.ItemsBean itemsBean, int position) {
                    TextView tv_name = viewHolder.getViewById(R.id.tv_chat_username);
                    TextView tv_chat_name = viewHolder.getViewById(R.id.tv_chat_name);
                    TextView tv_chat_time = viewHolder.getViewById(R.id.tv_chat_time);
                    TextView tv_chat_title = viewHolder.getViewById(R.id.tv_title);
                    TextView remark_num = viewHolder.getViewById(R.id.remark_num);
                    NineGridTestLayout photo_show = viewHolder.getViewById(R.id.photo_show);


                    tv_name.setText(itemsBean.getUser_name());
                    tv_chat_name.setText(itemsBean.getTopic_name().trim());
                    tv_chat_title.setText(itemsBean.getCommunication_title());
                    int timeB = Integer.parseInt(itemsBean.getAdd_time());
                    tv_chat_time.setText(DateUtils.getGapTimeFromNow(new Date(timeB * 1000L)));
                    remark_num.setText(itemsBean.getRepliesnumber());
                    List<String> urllist = new ArrayList<>();
                    if (itemsBean.getPhoto() != null && (!"".equals(itemsBean.getPhoto()))) {

                        String[] urls = itemsBean.getPhoto().split(",");
                        for (int i = 0; i < urls.length; i++) {
                            System.out.println("!!!" + urls[i]);
                            if ("0".equals(urls[i])) {
                                continue;
                            }

                            urllist.add(urls[i]);
                        }
                        photo_show.setUrlList(urllist);
                    } else {
                        urllist.clear();
                        photo_show.setUrlList(urllist);
                    }


                }
            };

            listView.setScrollingWhileRefreshingEnabled(true);
            listView.setMode(PullToRefreshBase.Mode.BOTH);

            listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                    PullToRefreshBase.Mode mode = listView.getCurrentMode();

                    if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {

                        getTopicList(position, topicList, adapter, listView, 1);


                    } else if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                        getTopicList(position, topicList, adapter, listView, ++startPage);

                    }


                }
            });

            ListView newsList = listView.getRefreshableView();
            newsList.setAdapter(adapter);

            newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    // Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
                    TopicList.DataBean.ItemsBean topicDetial = topicList.get(position - 1);

                    Intent intent = new Intent(getActivity(), ChatDetilActivity.class);
                    intent.putExtra("topic_id", topicDetial.getTopic_id());
                    startActivity(intent);
                }
            });
            getTopicList(position, topicList, adapter, listView, startPage);
            Log.i(position + "", "getViewForPage: ");

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

    private void getTopicList(int position, final List<TopicList.DataBean.ItemsBean> topicList, final CommonAdapter<TopicList.DataBean.ItemsBean> adapter, final PullToRefreshListView listView, final int page) {
        int type = (position + 1);
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "topic");
        params.addQueryStringParameter("topic_category_id", type + "");
        params.addQueryStringParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addQueryStringParameter("page", page + "");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CircularProgress.setVisibility(View.GONE);
                Gson gson = new Gson();
                System.out.println(result);
                TopicList bean = gson.fromJson(result, TopicList.class);
                pageCount = bean.getData().getTotal_pages();
                if (page == 1) {
                    topicList.clear();
                }
                if (page <= pageCount) {
                    topicList.addAll(bean.getData().getItems());
                }

                listView.onRefreshComplete();
                System.out.println(topicList);
                adapter.notifyDataSetChanged();


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