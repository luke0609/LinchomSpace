package linchom.com.linchomspace.homepage;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Constant.Constant;
import linchom.com.linchomspace.homepage.Entity.ArticleListBean;
import linchom.com.linchomspace.homepage.Utils.DateUtils;
import linchom.com.linchomspace.homepage.Utils.xUtilsImageUtils;
import linchom.com.linchomspace.homepage.progressbar.CircularProgress;

import static android.view.View.inflate;
import static linchom.com.linchomspace.R.id.iv_photo;
import static linchom.com.linchomspace.R.id.iv_zan;
import static linchom.com.linchomspace.R.id.tv_name;
import static linchom.com.linchomspace.R.id.tv_title;


public class HomeFragment extends Fragment {
    private static final String TAG1 = "getData1";
    private BannerComponent bannerComponent;
    private CircularProgress pb_progressBar;
    private RelativeLayout rl_hide_tuijian;
    private RelativeLayout rl_hide_hangyezixun;
    private RelativeLayout rl_hide_canpinzixun;
    private RelativeLayout rl_hide_video;
    private RelativeLayout rl_hide_pinpaizixun;
    private RelativeLayout rl_hide_hangyebiaozhun;
    private RelativeLayout rl_hide_hangyejishu;
    private RelativeLayout rl_hide_wulianwang;
    private RelativeLayout rl_hide_zhihuichengshi;
    private RelativeLayout rl_hide_zhihuishequ;
    private RelativeLayout rl_hide_wurenji;
    private RelativeLayout rl_hide_zhinengyanglao;
    private RelativeLayout rl_hide_zhinengjiankang;
    private RelativeLayout rl_hide_zhinengyiliao;
    private RelativeLayout rl_hide_jiqiren;
    private RelativeLayout rl_hide_vrar;
    private RelativeLayout rl_hide_zhanhuixinxi;
    private RelativeLayout rl_hide_peixunxinxi;
    private RelativeLayout rl_hide_zhichaofangan;
    private RelativeLayout rl_hide_zhichaoanli;





    final List<ArticleListBean.Article_list> arrList = new ArrayList<ArticleListBean.Article_list>();
    private View view;
    private View view1;
    private View view2;
    private View view3;
    private View bannnerAdv;

    private ViewPager vp_news;
    private RadioGroup rg_message;
    private HorizontalScrollView hs_navi;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private PullToRefreshListView ptr_arrlist_tuijian;
    private PullToRefreshListView ptr_arrlist_hangyezixun;
    private PullToRefreshListView ptr_arrlist_canpinzixun;
    private PullToRefreshListView ptr_arrlist_video;
    private PullToRefreshListView ptr_arrlist_pinpaizixun;
    private PullToRefreshListView ptr_arrlist_hangyebiaozhun;
    private PullToRefreshListView ptr_arrlist_hangyejishu;
    private PullToRefreshListView ptr_arrlist_wulianwang;
    private PullToRefreshListView ptr_arrlist_zhihuichengshi;
    private PullToRefreshListView ptr_arrlist_zhihuishequ;
    private PullToRefreshListView ptr_arrlist_wurenji;
    private PullToRefreshListView ptr_arrlist_zhinengyanglao;
    private PullToRefreshListView ptr_arrlist_zhinengjiankang;
    private PullToRefreshListView ptr_arrlist_zhinengyiliao;
    private PullToRefreshListView ptr_arrlist_jiqiren;
    private PullToRefreshListView ptr_arrlist_vrar;
    private PullToRefreshListView ptr_arrlist_zhanhuixinxi;
    private PullToRefreshListView ptr_arrlist_peixunxinxi;
    private PullToRefreshListView ptr_arrlist_zhichaofangan;
    private PullToRefreshListView ptr_arrlist_zhichaoanli;





    private boolean pullFlag = false;
    private int page = 1;//加载页数
    private int pageCount;
    //多个listview
    private ListView lv_tuijian;
    private ListView lv_hangyezixun;
    private ListView lv_canpinzixun;
    private List<View> listView = new ArrayList<View>();
    private MyAdapter adapter1;
    private MyAdapter adapter2;
    private MyAdapter adapter3;
    private RadioGroup myRadioGroup;
    int _id = 1000;
    private LinearLayout layout, titleLayout;
    private ImageView mImageView;
    FragmentTransaction transaction;
    private List<Map<String, String>> titleList = new ArrayList<Map<String, String>>();
    private ImageButton ib_xiala;
    private TextView tv_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);


        initView();
        initlistviews();
        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        vp_news.setCurrentItem(0);
        lv_tuijian = ptr_arrlist_tuijian.getRefreshableView();
        lv_tuijian.setAdapter(adapter1);
        gettuijian();
    }
    private int[] images = {R.drawable.city, R.drawable.qiiqu, R.drawable.caoyuan, R.drawable.meijing};


    private void initView() {
        tv_search = ((TextView) view.findViewById(R.id.tv_search));
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
            }
        });
        ib_xiala = ((ImageButton) view.findViewById(R.id.ib_xiala));
        ib_xiala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
            }
        });
        titleLayout = (LinearLayout) view.findViewById(R.id.title_lay);
        layout = (LinearLayout) view.findViewById(R.id.lay);
        mImageView = (ImageView) view.findViewById(R.id.img1);
        vp_news = ((ViewPager) view.findViewById(R.id.vp_news));

        hs_navi = (HorizontalScrollView) view.findViewById(R.id.hs_navi);
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "推荐");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "行业资讯");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "产品资讯");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "产品评测");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "视频");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "品牌资讯");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "行业标准");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "行业技术");
        titleList.add(map);

        map = new HashMap<String, String>();
        map.put("title", "物联网");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智慧城市");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智慧社区");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "无人机");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智能养老");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智能健康");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智能医疗");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "机器人");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "VR/AR");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "展会信息");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "培训信息");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智巢方案");
        titleList.add(map);
        map = new HashMap<String, String>();
        map.put("title", "智巢案例");
        titleList.add(map);
    }
    private void initlistviews(){
        view1 = view.inflate(getActivity(), R.layout.fragment_home_tuijian, null);
        pb_progressBar= (CircularProgress) view1.findViewById(R.id.pb_progressBar);
        rl_hide_tuijian = (RelativeLayout) view1.findViewById(R.id.rl_hide_tuijian);
        ptr_arrlist_tuijian = ((PullToRefreshListView) view1.findViewById(R.id.ptr_arrlist_tuijian));
        lv_tuijian = ptr_arrlist_tuijian.getRefreshableView();
        adapter1 = new MyAdapter();
        bannnerAdv = view1.inflate(getActivity(), R.layout.fragment_homepage_adv, null);
        //viewpage与indicator
        ViewPager viewPager = (ViewPager) bannnerAdv.findViewById(R.id.banner_viewPager);
        Indicator indicator = (Indicator) bannnerAdv.findViewById(R.id.guide_indicator);
        //indicator.setScrollBar(new ColorBar(getActivity(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        viewPager.setOffscreenPageLimit(2);
        bannerComponent = new BannerComponent(indicator, viewPager, true);
        bannerComponent.setAdapter(adapterbanner);
        bannerComponent.setAutoPlayTime(2500);
        lv_tuijian.addHeaderView(bannnerAdv);
        lv_tuijian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewHolder viewHolder = null;
                viewHolder = new ViewHolder();
                viewHolder.tv_title = ((TextView) view.findViewById(R.id.tv_title));
                viewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ArticleActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        listView.add(view1);

        View view2 = inflate(getActivity(), R.layout.fragment_home_hangyezixun, null);
        pb_progressBar= (CircularProgress) view2.findViewById(R.id.pb_progressBar);
        rl_hide_hangyezixun = (RelativeLayout) view2.findViewById(R.id.rl_hide_hangyezixun);
        ptr_arrlist_hangyezixun = ((PullToRefreshListView) view2.findViewById(R.id.ptr_arrlist_hangyezixun));
        lv_hangyezixun = ptr_arrlist_hangyezixun.getRefreshableView();
        adapter2 = new MyAdapter();
        lv_hangyezixun.setAdapter(adapter2);
        listView.add(view2);
        View view3 = inflate(getActivity(), R.layout.fragment_home_canpinzixun, null);
        pb_progressBar= (CircularProgress) view3.findViewById(R.id.pb_progressBar);
        rl_hide_canpinzixun = (RelativeLayout) view3.findViewById(R.id.rl_hide_canpinzixun);
        ptr_arrlist_canpinzixun = ((PullToRefreshListView) view3.findViewById(R.id.ptr_arrlist_canpinzixun));
        lv_canpinzixun = ptr_arrlist_canpinzixun.getRefreshableView();
        adapter3 = new MyAdapter();
        lv_canpinzixun.setAdapter(adapter3);
        listView.add(view3);
//        View view4 = inflate(getActivity(), R.layout.fragment_homepage_canpinpince, null);
//
//        lv_canpinpince = ((ListView) view4.findViewById(R.id.lv_canpinpince));
//        adapter4 = new MyAdapter();
//        listView.add(view4);
//        View view5 = inflate(getActivity(), R.layout.fragment_homepage_video, null);
//
//        lv_video = ((ListView) view5.findViewById(R.id.lv_video));
//        adapter5 = new MyAdapter();
//        listView.add(view5);
//        View view6 = inflate(getActivity(), R.layout.fragment_homepage_pinpaizixun, null);
//        lv_pinpaizixun = ((ListView) view6.findViewById(R.id.lv_pinpaizixun));
//        adapter6 = new MyAdapter();
//        listView.add(view6);
//        View view7 = inflate(getActivity(), R.layout.fragment_homepage_hangyebiaozhun, null);
//        lv_hangyebiaozhun = ((ListView) view7.findViewById(R.id.lv_hangyebiaozhun));
//        adapter7 = new MyAdapter();
//        listView.add(view7);
//        View view8 = inflate(getActivity(), R.layout.fragment_homepage_hangyejishu, null);
//        lv_hangyejishu = ((ListView) view8.findViewById(R.id.lv_hangyejishu));
//        adapter8 = new MyAdapter();
//        listView.add(view8);
//        View view9 = inflate(getActivity(), R.layout.fragment_homepage_wulianwang, null);
//        lv_wulianwang = ((ListView) view9.findViewById(R.id.lv_wulianwang));
//        adapter9 = new MyAdapter();
//        listView.add(view9);


    }
    private void initData() {
        ptr_arrlist_tuijian.setScrollingWhileRefreshingEnabled(true);
        ptr_arrlist_tuijian.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_arrlist_tuijian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_arrlist_tuijian.getCurrentMode();

                // View viewRefresh = null;

                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                    page++;
                    gettuijian();
                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    gettuijian();

                }
            }
        });
        ptr_arrlist_tuijian.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        myRadioGroup = new RadioGroup(getActivity());
        //myRadioGroup.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        myRadioGroup.setBackgroundColor(Color.WHITE);
        layout.addView(myRadioGroup);
        for (int i = 0; i < titleList.size(); i++) {
            Map<String, String> map = titleList.get(i);
            RadioButton radio = new RadioButton(getActivity());
            // radio.setBackgroundResource(R.drawable.radiobutton_homepage_navigation);

            //radio.setBackgroundResource(R.drawable.radiobtn_selector);
            radio.setButtonDrawable(android.R.color.transparent);
            LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            radio.setLayoutParams(l);
            radio.setBackgroundColor(Color.WHITE);
            radio.setGravity(Gravity.CENTER);
            radio.setPadding(16, 16, 30, 16);
            //radio.setPadding(left, top, right, bottom)
            radio.setId(_id + i);
            radio.setText(map.get("title"));
            ColorStateList color = getResources().getColorStateList(R.color.text_white2blue);
            radio.setTextColor(color);
            radio.setTextSize(16);
            radio.setTag(map);
            if (i == 0) {
                radio.setChecked(true);
                int itemWidth = (int) radio.getPaint().measureText(map.get("title"));
                mImageView.setLayoutParams(new LinearLayout.LayoutParams(itemWidth + radio.getPaddingLeft() + radio.getPaddingRight(), 4));
            }
            myRadioGroup.addView(radio);
        }


        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Map<String, Object> map = (Map<String, Object>) group.getChildAt(checkedId).getTag();
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) view.findViewById(radioButtonId);
                Map<String, Object> selectMap = (Map<String, Object>) rb.getTag();

                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation;
                translateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, rb.getLeft(), 0f, 0f);
                animationSet.addAnimation(translateAnimation);
                animationSet.setFillBefore(true);
                animationSet.setFillAfter(true);
                animationSet.setDuration(300);

                mImageView.startAnimation(animationSet);//开始上面白色横条图片的动画切换
                vp_news.setCurrentItem(radioButtonId - _id, false);//让下方ViewPager跟随上面的HorizontalScrollView切换
                mCurrentCheckedRadioLeft = rb.getLeft();//更新当前白色横条距离左边的距离


                //System.out.println("dis1:"+mCurrentCheckedRadioLeft);
                //System.out.println("dis2:"+( (int)mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.rdo2)));
                hs_navi.smoothScrollTo((int) mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);
                mImageView.setLayoutParams(new LinearLayout.LayoutParams(rb.getRight() - rb.getLeft(), 4));
                switch (checkedId) {
                    case 1000:
                        ptr_arrlist_tuijian.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_tuijian.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_tuijian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                //异步任务拿数据

                                PullToRefreshBase.Mode mode = ptr_arrlist_tuijian.getCurrentMode();

                                // View viewRefresh = null;

                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                    page++;
                                    gettuijian();
                                }
                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                    pullFlag = true;
                                    page = 1;
                                    gettuijian();

                                }
                            }
                        });
                        ptr_arrlist_tuijian.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                            @Override
                            public void onLastItemVisible() {
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });


                        lv_tuijian.setAdapter(adapter1);
                        gettuijian();
                        break;
                    case 1001:
                        ptr_arrlist_hangyezixun.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_hangyezixun.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_hangyezixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                //异步任务拿数据

                                PullToRefreshBase.Mode mode = ptr_arrlist_hangyezixun.getCurrentMode();

                                // View viewRefresh = null;

                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                    page++;
                                    gethangyezixun();
                                }
                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                    pullFlag = true;
                                    page = 1;
                                    gethangyezixun();

                                }
                            }
                        });
                        ptr_arrlist_hangyezixun.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                            @Override
                            public void onLastItemVisible() {
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });

                        lv_hangyezixun.setAdapter(adapter2);
                        gethangyezixun();
                        break;
                    case 1002:
                        ptr_arrlist_canpinzixun.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_canpinzixun.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_canpinzixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                //异步任务拿数据

                                PullToRefreshBase.Mode mode = ptr_arrlist_canpinzixun.getCurrentMode();

                                // View viewRefresh = null;

                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                    page++;
                                    getcanpinzixun();
                                }
                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                    pullFlag = true;
                                    page = 1;
                                    getcanpinzixun();

                                }
                            }
                        });
                        ptr_arrlist_canpinzixun.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                            @Override
                            public void onLastItemVisible() {
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });

                        lv_canpinzixun.setAdapter(adapter3);
                        getcanpinzixun();
                        break;
                    case 1003:
//                        lv_canpinpince.setAdapter(adapter4);
//                        getData4();
                        break;
                    case 1004:
//                        lv_video.setAdapter(adapter5);
//                        getData5();
                        break;
                    case 1005:
//                        lv_pinpaizixun.setAdapter(adapter6);
//                        getData6();
                        break;
                    case 1006:
//                        lv_hangyebiaozhun.setAdapter(adapter7);
//                        getData7();
                        break;
                    case 1007:
//                        lv_hangyejishu.setAdapter(adapter8);
//                        getData8();
                        break;
                    case 1008:
//                        lv_wulianwang.setAdapter(adapter9);
//                        getData9();
                        break;
                    default:

                        break;


                }
            }
        });

        vp_news.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                setCurrentTab(position);
                RadioButton radioButton = (RadioButton) view.findViewById(_id + position);
                radioButton.performClick();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        MypAdapter adapter = new MypAdapter(listView);
        vp_news.setOffscreenPageLimit(2);
        vp_news.setAdapter(adapter);


    }
    private void gettuijian() {
        if (page == 1 && pullFlag == false) {
            rl_hide_tuijian.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "12");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_tuijian.setVisibility(View.GONE);
                if (page == 1) {
                    arrList.clear();

                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");


                System.out.println(arrList);

//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面


                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter1.notifyDataSetChanged();
                    ptr_arrlist_tuijian.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_tuijian.onRefreshComplete();


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG1, "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void gethangyezixun() {
        if (page == 1 && pullFlag == false) {
            rl_hide_hangyezixun.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "12");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_hangyezixun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList.clear();

                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");


                System.out.println(arrList);

//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面


                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter1.notifyDataSetChanged();
                    ptr_arrlist_hangyezixun.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_hangyezixun.onRefreshComplete();


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG1, "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void getcanpinzixun() {
        if (page == 1 && pullFlag == false) {
            rl_hide_canpinzixun.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "14");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_canpinzixun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList.clear();

                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");


                System.out.println(arrList);

//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面


                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter1.notifyDataSetChanged();
                    ptr_arrlist_canpinzixun.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_canpinzixun.onRefreshComplete();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG1, "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private class MypAdapter extends PagerAdapter {
        private List<View> listView;

        public MypAdapter(List<View> listView) {
            this.listView = listView;//构造方法，参数是我们的页卡，这样比较方便。
        }

        @Override
        public int getCount() {
            return listView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            //container.removeView((View) object);
            System.out.println(position);
            container.removeView(listView.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            container.addView(listView.get(position));
            //System.out.println(position);
            return listView.get(position);
        }
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.i(TAG1, "加载listview item position" + position);
            //打气筒

            Log.i("convertView", "getView at position:" + position + " convertView:" + (convertView == null ? "null" : convertView.hashCode()));

            ViewHolder viewHolder = null;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getActivity(), R.layout.tuijian_item, null);
                viewHolder.tv_title = ((TextView) convertView.findViewById(tv_title));
                viewHolder.tv_name = ((TextView) convertView.findViewById(tv_name));
                viewHolder.iv_photo = ((ImageView) convertView.findViewById(iv_photo));
                viewHolder.iv_zan = ((ImageView) convertView.findViewById(iv_zan));

//                    viewHolder.iv_zan.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showPopupWindow(v);
//                        }
//                    });
                convertView.setTag(viewHolder);//缓存对象的
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //ListActivityBean.Dongtai dongtai = dongtaiList.get(position);
            ArticleListBean.Article_list arr = arrList.get(position);

            viewHolder.tv_title.setText(arr.title);

            viewHolder.tv_name.setText(linchom.com.linchomspace.homepage.Utils.DateUtils.getGapTimeFromNow(DateUtils.stringToDate(arr.date)));
            if (arr.article_pic == "") {
                viewHolder.iv_photo.setImageResource(R.drawable.article_item_tuijian);
            } else {
                viewHolder.iv_photo.setVisibility(View.VISIBLE);
                xUtilsImageUtils.display(viewHolder.iv_photo, "http://linchom.com//" + arr.article_pic);
            }


            return convertView;
        }
    }
    private static class ViewHolder {
        private TextView tv_title;
        private TextView tv_name;
        private ImageView iv_zan;
        private ImageView iv_photo;
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

    //广告轮播的适配器设置
    private IndicatorViewPager.IndicatorViewPagerAdapter adapterbanner = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = new View(container.getContext());
//            }
            if (convertView == null) {
                convertView = inflate(getActivity(), R.layout.tab_guide, null);
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
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri=Uri.parse("http://app.linchom.com");
                    Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                }
            });
            return convertView;
        }

//        @Override
//        public int getItemPosition(Object object) {
//            return RecyclingPagerAdapter.POSITION_NONE;
//        }

        @Override
        public int getCount() {
            return images.length;
        }
    };
}
