package linchom.com.linchomspace.homepage.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Activity.ArticleActivity;
import linchom.com.linchomspace.homepage.Activity.NavigationActivity;
import linchom.com.linchomspace.homepage.Adapter.HomeAdvIndicatorAdapter;
import linchom.com.linchomspace.homepage.Constant.Constant;
import linchom.com.linchomspace.homepage.Entity.ArticleListBean;
import linchom.com.linchomspace.homepage.Entity.HomePageAdvBigBean;
import linchom.com.linchomspace.homepage.Entity.HomePageAdvDataBean;
import linchom.com.linchomspace.homepage.Utils.DateUtils;
import linchom.com.linchomspace.homepage.Utils.xUtilsImageUtils;
import linchom.com.linchomspace.homepage.progressbar.CircularProgress;
import linchom.com.linchomspace.search.SearchActivity;

import static android.view.View.inflate;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static linchom.com.linchomspace.R.id.iv_photo;
import static linchom.com.linchomspace.R.id.tv_name;
import static linchom.com.linchomspace.R.id.tv_source;
import static linchom.com.linchomspace.R.id.tv_title;


public class HomeFragment extends Fragment {
    private static final String TAG1 = "getData1";
    private static final String tag2 = "第一页数据";
    private BannerComponent bannerComponent;
    private CircularProgress pb_progressBar;
    private RelativeLayout rl_hide_tuijian;
    private RelativeLayout rl_hide_hangyezixun;
    private RelativeLayout rl_hide_canpinzixun;
    private RelativeLayout rl_hide_canpinpince;
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
    final List<ArticleListBean.Article_list> arrList1 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList2 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList3 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList4 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList5 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList6 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList7 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList8 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList9 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList10 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList11 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList12 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList13 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList14 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList15 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList16 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList17 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList18 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList19 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList20 = new ArrayList<ArticleListBean.Article_list>();
    final List<ArticleListBean.Article_list> arrList21 = new ArrayList<ArticleListBean.Article_list>();
    private View view;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private View view7;
    private View view8;
    private View view9;
    private View view10;
    private View view11;
    private View view12;
    private View view13;
    private View view14;
    private View view15;
    private View view16;
    private View view17;
    private View view18;
    private View view19;
    private View view20;
    private View bannnerAdv;
    private ViewPager vp_news;
    private RadioGroup rg_message;
    private HorizontalScrollView hs_navi;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private PullToRefreshListView ptr_arrlist_tuijian;
    private PullToRefreshListView ptr_arrlist_hangyezixun;
    private PullToRefreshListView ptr_arrlist_canpinzixun;
    private PullToRefreshListView ptr_arrlist_canpinpince;
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
    private ListView lv_canpinpince;
    private ListView lv_video;
    private ListView lv_pinpaizixun;
    private ListView lv_hangyebiaozhun;
    private ListView lv_hangyejishu;
    private ListView lv_wulianwang;
    private ListView lv_zhihuichengshi;
    private ListView lv_zhihuishequ;
    private ListView lv_wurenji;
    private ListView lv_zhinengyanglao;
    private ListView lv_zhinengjiankang;
    private ListView lv_zhinengyiliao;
    private ListView lv_jiqiren;
    private ListView lv_vrar;
    private ListView lv_zhanhuixinxi;
    private ListView lv_peixunxinxi;
    private ListView lv_zhichaofangan;
    private ListView lv_zhichaoanli;


    private List<View> listView = new ArrayList<View>();
    private MyAdapter adapter1;
    private MyAdapter adapter2;
    private MyAdapter adapter3;
    private MyAdapter adapter4;
    private MyAdapter adapter5;
    private MyAdapter adapter6;
    private MyAdapter adapter7;
    private MyAdapter adapter8;
    private MyAdapter adapter9;
    private MyAdapter adapter10;
    private MyAdapter adapter11;
    private MyAdapter adapter12;
    private MyAdapter adapter13;
    private MyAdapter adapter14;
    private MyAdapter adapter15;
    private MyAdapter adapter16;
    private MyAdapter adapter17;
    private MyAdapter adapter18;
    private MyAdapter adapter19;
    private MyAdapter adapter20;
    private MyAdapter adapter21;


    private RadioGroup myRadioGroup;
    int _id = 1000;
    private LinearLayout layout, titleLayout;
    private ImageView mImageView;
    FragmentTransaction transaction;
    private List<Map<String, String>> titleList = new ArrayList<Map<String, String>>();
    private ImageButton ib_xiala;
    private TextView tv_search;
    private RelativeLayout rl_empty;
    private RelativeLayout error_caterogy;
    private RelativeLayout rl_empty1;
    private RelativeLayout error_caterogy1;
    private RelativeLayout rl_empty2;
    private RelativeLayout error_caterogy2;
    private RelativeLayout rl_empty3;
    private RelativeLayout error_caterogy3;
    private RelativeLayout rl_empty4;
    private RelativeLayout error_caterogy4;
    private RelativeLayout rl_empty5;
    private RelativeLayout error_caterogy5;
    private RelativeLayout rl_empty6;
    private RelativeLayout error_caterogy6;
    private RelativeLayout rl_empty7;
    private RelativeLayout error_caterogy7;
    private RelativeLayout rl_empty8;
    private RelativeLayout error_caterogy8;
    private RelativeLayout rl_empty9;
    private RelativeLayout error_caterogy9;
    private RelativeLayout rl_empty10;
    private RelativeLayout error_caterogy10;
    private RelativeLayout rl_empty11;
    private RelativeLayout error_caterogy11;
    private RelativeLayout rl_empty12;
    private RelativeLayout error_caterogy12;
    private RelativeLayout rl_empty13;
    private RelativeLayout error_caterogy13;
    private RelativeLayout rl_empty14;
    private RelativeLayout error_caterogy14;
    private RelativeLayout rl_empty15;
    private RelativeLayout error_caterogy15;
    private RelativeLayout rl_empty16;
    private RelativeLayout error_caterogy16;
    private RelativeLayout rl_empty17;
    private RelativeLayout error_caterogy17;
    private RelativeLayout rl_empty18;
    private RelativeLayout error_caterogy18;
    private RelativeLayout rl_empty19;
    private RelativeLayout error_caterogy19;
    private RelativeLayout rl_empty20;
    private RelativeLayout error_caterogy20;
    private RelativeLayout rl_empty21;
    private RelativeLayout error_caterogy21;

    private Button reload1;
    private Button reload2;
    private Button reload3;
    private Button reload4;
    private Button reload5;
    private Button reload6;
    private Button reload7;
    private Button reload8;
    private Button reload9;
    private Button reload10;
    private Button reload11;
    private Button reload12;
    private Button reload13;
    private Button reload14;
    private Button reload15;
    private Button reload16;
    private Button reload17;
    private Button reload18;
    private Button reload19;
    private Button reload20;
    private Button reload21;

    //private Map<String ,String> advMap =new HashMap<String ,String>();

    private List<HomePageAdvDataBean> getAdvList = new ArrayList<HomePageAdvDataBean>();

    //private List<String> tempList =new ArrayList<String>();
    private HomeAdvIndicatorAdapter homeadvindicatorAdapter;
    private String CurrentItem;
    private String PdId;
    private RadioButton rb;
    Shimmer shimmer;
    ShimmerTextView tv1;
    String source1;
    ShimmerTextView tv2;
    String source2;
    ShimmerTextView tv3;
    String source3;
    ShimmerTextView tv4;
    String source4;
    ShimmerTextView tv5;
    String source5;
    ShimmerTextView tv6;
    String source6;
    ShimmerTextView tv7;
    String source7;
    ShimmerTextView tv8;
    String source8;
    ShimmerTextView tv9;
    String source9;
    ShimmerTextView tv10;
    String source10;
    ShimmerTextView tv11;
    String source11;
    ShimmerTextView tv12;
    String source12;
    ShimmerTextView tv13;
    String source13;
    ShimmerTextView tv14;
    String source14;
    ShimmerTextView tv15;
    String source15;
    ShimmerTextView tv16;
    String source16;
    ShimmerTextView tv17;
    String source17;
    ShimmerTextView tv18;
    String source18;
    ShimmerTextView tv19;
    String source19;
    ShimmerTextView tv20;
    String source20;
    ShimmerTextView tv21;
    String source21;


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
        Bundle bundle = getArguments();
        if(bundle!=null){
        PdId = bundle.getString("PdId");
            switch (PdId){
                case "0":
                    vp_news.setCurrentItem(0);
                    break;
                case "1":
                    vp_news.setCurrentItem(1);
                    break;
                case "2":
                    vp_news.setCurrentItem(2);
                    break;
                case "3":
                    vp_news.setCurrentItem(3);
                    break;
                case "4":
                    vp_news.setCurrentItem(4);
                    break;
                case "5":
                    vp_news.setCurrentItem(5);
                    break;
                case "6":
                    vp_news.setCurrentItem(6);
                    break;
                case "7":
                    vp_news.setCurrentItem(7);
                    break;
                case "8":
                    vp_news.setCurrentItem(8);
                    break;
                case "9":
                    vp_news.setCurrentItem(9);
                    break;
                case "10":
                    vp_news.setCurrentItem(10);
                    break;
                case "11":
                    vp_news.setCurrentItem(11);
                    break;
                case "12":
                    vp_news.setCurrentItem(12);
                    break;
                case "13":
                    vp_news.setCurrentItem(13);
                    break;
                case "14":
                    vp_news.setCurrentItem(14);
                    break;
                case "15":
                    vp_news.setCurrentItem(15);
                    break;
                case "16":
                    vp_news.setCurrentItem(16);
                    break;
                case "17":
                    vp_news.setCurrentItem(17);
                    break;
                case "18":
                    vp_news.setCurrentItem(18);
                    break;
                case "19":
                    vp_news.setCurrentItem(19);
                    break;
                case "20":
                   vp_news.setCurrentItem(20);
                break;
            }
        }
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

    private void initAdvList() {
        getAdvList.add(new HomePageAdvDataBean("1", "1", "1", "1", "1", "1", "1", "1", "1"));
        getAdvList.add(new HomePageAdvDataBean("1", "1", "1", "1", "1", "1", "1", "1", "1"));

        RequestParams requestParams = new RequestParams(Constant.HomeAdv);
        requestParams.addBodyParameter("position_id", "3");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "result" + result);
                RequestParams requestParams = new RequestParams(Constant.HomeAdv);

                requestParams.addBodyParameter("act", "ads");

                requestParams.addBodyParameter("position_id", "3");


                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Log.i(TAG, "result" + result);

                        getAdvList.clear();

                        Gson gson = new Gson();

                        HomePageAdvBigBean bean = gson.fromJson(result, HomePageAdvBigBean.class);

                        getAdvList.addAll(bean.data);


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


//                JsonParser parser = new JsonParser();
//
//                JsonElement element = parser.parse(result);
//
//                JsonObject root = element.getAsJsonObject();
//
//                JsonPrimitive resultjson = root.getAsJsonPrimitive("result");
//
//                JsonObject dataJson =  root.getAsJsonObject("data");
//
//                Map<String,String> a=   gson.fromJson(dataJson, new TypeToken<Map<String,String>>() {}.getType());
//
//                tempList.clear();
//                for (Map.Entry<String,String> m : a.entrySet()) {
//
//                    tempList.add(m.getValue());
//
//                    Log.i(TAG, "a" +m.getValue());
//
//
//                }
//                getAdvList.clear();

//                for(int i = 0;i<tempList.size();i++){
//                    getAdvList.add(new HomeAdvBean(tempList.get(i),tempList.get(i++)));
//
//                }


                Log.i(TAG, "advList" + getAdvList + "");

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


    private void initView() {

        tv_search = ((TextView) view.findViewById(R.id.tv_search));
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("search_type", "article");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        ib_xiala = ((ImageButton) view.findViewById(R.id.ib_xiala));
        ib_xiala.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();

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

    private void initlistviews() {
        view1 = view.inflate(getActivity(), R.layout.fragment_home_tuijian, null);
        pb_progressBar = (CircularProgress) view1.findViewById(R.id.pb_progressBar);
        rl_hide_tuijian = (RelativeLayout) view1.findViewById(R.id.rl_hide_tuijian);
        rl_empty1 = ((RelativeLayout)view1.findViewById(R.id.rl_empty1));
        tv1 = ((ShimmerTextView)view1.findViewById(R.id.shimmer_tv));
        source1 = "<i>联巢空间 </i>";
        tv1.setText(Html.fromHtml(source1));
        reload1 = ((Button) view1.findViewById(R.id.reload1));
        error_caterogy1 = ((RelativeLayout) view1.findViewById(R.id.error_caterogy1));
        ptr_arrlist_tuijian = ((PullToRefreshListView) view1.findViewById(R.id.ptr_arrlist_tuijian));
        lv_tuijian = ptr_arrlist_tuijian.getRefreshableView();
        adapter1 = new MyAdapter(arrList1);
        bannnerAdv = view1.inflate(getActivity(), R.layout.fragment_homepage_adv, null);
        //viewpage与indicator
        ViewPager viewPager = (ViewPager) bannnerAdv.findViewById(R.id.banner_viewPager);
        Indicator indicator = (Indicator) bannnerAdv.findViewById(R.id.guide_indicator);
        //indicator.setScrollBar(new ColorBar(getActivity(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        viewPager.setOffscreenPageLimit(2);
        initAdvList();
        bannerComponent = new BannerComponent(indicator, viewPager, true);
        homeadvindicatorAdapter = new HomeAdvIndicatorAdapter(getActivity(), getAdvList);
        bannerComponent.setAdapter(homeadvindicatorAdapter);
        bannerComponent.setAutoPlayTime(2500);
        lv_tuijian.addHeaderView(bannnerAdv);
        lv_tuijian.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList1.get(position - 2).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);

            }
        });
        listView.add(view1);
        View view2 = inflate(getActivity(), R.layout.fragment_home_hangyezixun, null);
        pb_progressBar = (CircularProgress) view2.findViewById(R.id.pb_progressBar);
        rl_hide_hangyezixun = (RelativeLayout) view2.findViewById(R.id.rl_hide_hangyezixun);
        rl_empty2 = ((RelativeLayout) view2.findViewById(R.id.rl_empty2));
        tv2 = ((ShimmerTextView)view2.findViewById(R.id.shimmer_tv));
        source2 = "<i>联巢空间 </i>";
        tv2.setText(Html.fromHtml(source2));
        reload2 = ((Button) view2.findViewById(R.id.reload2));
        error_caterogy2 = ((RelativeLayout) view2.findViewById(R.id.error_caterogy2));
        ptr_arrlist_hangyezixun = ((PullToRefreshListView) view2.findViewById(R.id.ptr_arrlist_hangyezixun));
        lv_hangyezixun = ptr_arrlist_hangyezixun.getRefreshableView();
        adapter2 = new MyAdapter(arrList2);
        lv_hangyezixun.setAdapter(adapter2);
        lv_hangyezixun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList2.get(position - 1).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        listView.add(view2);
        View view3 = inflate(getActivity(), R.layout.fragment_home_canpinzixun, null);
        pb_progressBar = (CircularProgress) view3.findViewById(R.id.pb_progressBar);
        rl_hide_canpinzixun = (RelativeLayout) view3.findViewById(R.id.rl_hide_canpinzixun);
        rl_empty3 = ((RelativeLayout) view3.findViewById(R.id.rl_empty3));
        tv3 = ((ShimmerTextView)view3.findViewById(R.id.shimmer_tv));
        source3 = "<i>联巢空间 </i>";
        tv3.setText(Html.fromHtml(source3));
        reload3 = ((Button) view3.findViewById(R.id.reload3));
        error_caterogy3 = ((RelativeLayout) view3.findViewById(R.id.error_caterogy3));
        ptr_arrlist_canpinzixun = ((PullToRefreshListView) view3.findViewById(R.id.ptr_arrlist_canpinzixun));

        lv_canpinzixun = ptr_arrlist_canpinzixun.getRefreshableView();
        adapter3 = new MyAdapter(arrList3);
        lv_canpinzixun.setAdapter(adapter3);
        lv_canpinzixun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList3.get(position - 2).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        listView.add(view3);
        View view4 = inflate(getActivity(), R.layout.fragment_home_canpinpince, null);
        pb_progressBar = (CircularProgress) view4.findViewById(R.id.pb_progressBar);
        rl_hide_canpinpince = (RelativeLayout) view4.findViewById(R.id.rl_hide_canpinpince);
        rl_empty4 = ((RelativeLayout) view4.findViewById(R.id.rl_empty4));
        tv4 = ((ShimmerTextView)view4.findViewById(R.id.shimmer_tv));
        source4 = "<i>联巢空间 </i>";
        tv4.setText(Html.fromHtml(source4));
        reload4 = ((Button) view4.findViewById(R.id.reload4));
        error_caterogy4 = ((RelativeLayout) view4.findViewById(R.id.error_caterogy4));
        ptr_arrlist_canpinpince = ((PullToRefreshListView) view4.findViewById(R.id.ptr_arrlist_canpinpince));
        lv_canpinpince = ptr_arrlist_canpinpince.getRefreshableView();
        adapter4 = new MyAdapter(arrList4);
        lv_canpinpince.setAdapter(adapter4);
        lv_canpinpince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList4.get(position - 1).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        listView.add(view4);
        View view5 = inflate(getActivity(), R.layout.fragment_home_video, null);
        pb_progressBar = (CircularProgress) view5.findViewById(R.id.pb_progressBar);
        rl_hide_video = (RelativeLayout) view5.findViewById(R.id.rl_hide_video);
        rl_empty5 = ((RelativeLayout) view5.findViewById(R.id.rl_empty5));
        tv5 = ((ShimmerTextView)view5.findViewById(R.id.shimmer_tv));
        source5 = "<i>联巢空间 </i>";
        tv5.setText(Html.fromHtml(source5));
        reload5 = ((Button) view5.findViewById(R.id.reload5));
        error_caterogy5 = ((RelativeLayout) view5.findViewById(R.id.error_caterogy5));
        ptr_arrlist_video = ((PullToRefreshListView) view5.findViewById(R.id.ptr_arrlist_video));

        lv_video = ptr_arrlist_video.getRefreshableView();
        adapter5 = new MyAdapter(arrList5);
        lv_video.setAdapter(adapter5);
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList5.get(position - 1).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        listView.add(view5);
        View view6 = inflate(getActivity(), R.layout.fragment_home_pinpaizixun, null);
        pb_progressBar = (CircularProgress) view6.findViewById(R.id.pb_progressBar);
        rl_hide_pinpaizixun = (RelativeLayout) view6.findViewById(R.id.rl_hide_pinpaizixun);
        rl_empty6 = ((RelativeLayout) view6.findViewById(R.id.rl_empty6));
        tv6 = ((ShimmerTextView)view6.findViewById(R.id.shimmer_tv));
        source6 = "<i>联巢空间 </i>";
        tv6.setText(Html.fromHtml(source6));
        reload6 = ((Button) view6.findViewById(R.id.reload6));
        error_caterogy6 = ((RelativeLayout) view6.findViewById(R.id.error_caterogy6));
        ptr_arrlist_pinpaizixun = ((PullToRefreshListView) view6.findViewById(R.id.ptr_arrlist_pinpaizixun));
        lv_pinpaizixun = ptr_arrlist_pinpaizixun.getRefreshableView();
        adapter6 = new MyAdapter(arrList6);
        lv_pinpaizixun.setAdapter(adapter6);
        listView.add(view6);
        View view7 = inflate(getActivity(), R.layout.fragment_home_hangyebiaozhun, null);
        pb_progressBar = (CircularProgress) view7.findViewById(R.id.pb_progressBar);
        rl_hide_hangyebiaozhun = (RelativeLayout) view7.findViewById(R.id.rl_hide_hangyebiaozhun);
        rl_empty7 = ((RelativeLayout) view7.findViewById(R.id.rl_empty7));
        tv7 = ((ShimmerTextView)view7.findViewById(R.id.shimmer_tv));
        source7 = "<i>联巢空间 </i>";
        tv7.setText(Html.fromHtml(source7));
        reload7 = ((Button) view7.findViewById(R.id.reload7));
        error_caterogy7 = ((RelativeLayout) view7.findViewById(R.id.error_caterogy7));
        ptr_arrlist_hangyebiaozhun = ((PullToRefreshListView) view7.findViewById(R.id.ptr_arrlist_hangyebiaozhun));
        lv_hangyebiaozhun = ptr_arrlist_hangyebiaozhun.getRefreshableView();
        adapter7 = new MyAdapter(arrList7);
        lv_hangyebiaozhun.setAdapter(adapter7);
        listView.add(view7);
        View view8 = inflate(getActivity(), R.layout.fragment_home_hangyejishu, null);
        pb_progressBar = (CircularProgress) view8.findViewById(R.id.pb_progressBar);
        rl_hide_hangyejishu = (RelativeLayout) view8.findViewById(R.id.rl_hide_hangyejishu);
        rl_empty8 = ((RelativeLayout) view8.findViewById(R.id.rl_empty8));
        tv8 = ((ShimmerTextView)view8.findViewById(R.id.shimmer_tv));
        source8 = "<i>联巢空间 </i>";
        tv8.setText(Html.fromHtml(source8));
        reload8 = ((Button) view8.findViewById(R.id.reload8));
        error_caterogy8 = ((RelativeLayout) view8.findViewById(R.id.error_caterogy8));
        ptr_arrlist_hangyejishu = ((PullToRefreshListView) view8.findViewById(R.id.ptr_arrlist_hangyejishu));
        lv_hangyejishu = ptr_arrlist_hangyejishu.getRefreshableView();
        adapter8 = new MyAdapter(arrList8);
        lv_hangyejishu.setAdapter(adapter8);
        listView.add(view8);
        View view9 = inflate(getActivity(), R.layout.fragment_home_wulianwang, null);
        pb_progressBar = (CircularProgress) view9.findViewById(R.id.pb_progressBar);
        rl_hide_wulianwang = (RelativeLayout) view9.findViewById(R.id.rl_hide_wulianwang);
        rl_empty9 = ((RelativeLayout) view9.findViewById(R.id.rl_empty9));
        tv9 = ((ShimmerTextView)view9.findViewById(R.id.shimmer_tv));
        source9 = "<i>联巢空间 </i>";
        tv9.setText(Html.fromHtml(source9));
        reload9 = ((Button) view9.findViewById(R.id.reload9));
        error_caterogy9 = ((RelativeLayout) view9.findViewById(R.id.error_caterogy9));
        ptr_arrlist_wulianwang = ((PullToRefreshListView) view9.findViewById(R.id.ptr_arrlist_wulianwang));
        lv_wulianwang = ptr_arrlist_wulianwang.getRefreshableView();
        adapter9 = new MyAdapter(arrList9);
        lv_wulianwang.setAdapter(adapter9);
        listView.add(view9);
        View view10 = inflate(getActivity(), R.layout.fragment_home_zhihuichengshi, null);
        pb_progressBar = (CircularProgress) view10.findViewById(R.id.pb_progressBar);
        rl_hide_zhihuichengshi = (RelativeLayout) view10.findViewById(R.id.rl_hide_zhihuichengshi);
        rl_empty10 = ((RelativeLayout) view10.findViewById(R.id.rl_empty10));
        tv10 = ((ShimmerTextView)view10.findViewById(R.id.shimmer_tv));
        source10 = "<i>联巢空间 </i>";
        tv10.setText(Html.fromHtml(source10));
        reload10 = ((Button) view10.findViewById(R.id.reload10));
        error_caterogy10 = ((RelativeLayout) view10.findViewById(R.id.error_caterogy10));
        ptr_arrlist_zhihuichengshi = ((PullToRefreshListView) view10.findViewById(R.id.ptr_arrlist_zhihuichengshi));
        lv_zhihuichengshi = ptr_arrlist_zhihuichengshi.getRefreshableView();
        adapter10 = new MyAdapter(arrList10);
        lv_zhihuichengshi.setAdapter(adapter10);
        listView.add(view10);
        View view11 = inflate(getActivity(), R.layout.fragment_home_zhihuishequ, null);
        pb_progressBar = (CircularProgress) view11.findViewById(R.id.pb_progressBar);
        rl_hide_zhihuishequ = (RelativeLayout) view11.findViewById(R.id.rl_hide_zhihuishequ);
        rl_empty11 = ((RelativeLayout) view11.findViewById(R.id.rl_empty11));
        tv11 = ((ShimmerTextView)view11.findViewById(R.id.shimmer_tv));
        source11 = "<i>联巢空间 </i>";
        tv11.setText(Html.fromHtml(source11));
        reload11 = ((Button) view11.findViewById(R.id.reload11));
        error_caterogy11 = ((RelativeLayout) view11.findViewById(R.id.error_caterogy11));
        ptr_arrlist_zhihuishequ = ((PullToRefreshListView) view11.findViewById(R.id.ptr_arrlist_zhihuishequ));
        lv_zhihuishequ = ptr_arrlist_zhihuishequ.getRefreshableView();
        adapter11 = new MyAdapter(arrList11);
        lv_zhihuishequ.setAdapter(adapter11);
        listView.add(view11);
        View view12 = inflate(getActivity(), R.layout.fragment_home_wurenji, null);
        pb_progressBar = (CircularProgress) view12.findViewById(R.id.pb_progressBar);
        rl_hide_wurenji = (RelativeLayout) view12.findViewById(R.id.rl_hide_wurenji);
        rl_empty12 = ((RelativeLayout) view12.findViewById(R.id.rl_empty12));
        tv12 = ((ShimmerTextView)view12.findViewById(R.id.shimmer_tv));
        source12 = "<i>联巢空间 </i>";
        tv12.setText(Html.fromHtml(source12));
        reload12 = ((Button) view12.findViewById(R.id.reload12));
        error_caterogy12 = ((RelativeLayout) view12.findViewById(R.id.error_caterogy12));
        ptr_arrlist_wurenji = ((PullToRefreshListView) view12.findViewById(R.id.ptr_arrlist_wurenji));
        lv_wurenji = ptr_arrlist_wurenji.getRefreshableView();
        adapter12 = new MyAdapter(arrList12);
        lv_wurenji.setAdapter(adapter12);
        listView.add(view12);
        View view13 = inflate(getActivity(), R.layout.fragment_home_zhinengyanglao, null);
        pb_progressBar = (CircularProgress) view13.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengyanglao = (RelativeLayout) view13.findViewById(R.id.rl_hide_zhinengyanglao);
        rl_empty13 = ((RelativeLayout) view13.findViewById(R.id.rl_empty13));
        tv13 = ((ShimmerTextView)view13.findViewById(R.id.shimmer_tv));
        source13 = "<i>联巢空间 </i>";
        tv13.setText(Html.fromHtml(source13));
        reload13 = ((Button) view13.findViewById(R.id.reload13));
        error_caterogy13 = ((RelativeLayout) view13.findViewById(R.id.error_caterogy13));
        ptr_arrlist_zhinengyanglao = ((PullToRefreshListView) view13.findViewById(R.id.ptr_arrlist_zhinengyanglao));
        lv_zhinengyanglao = ptr_arrlist_zhinengyanglao.getRefreshableView();
        adapter13 = new MyAdapter(arrList13);
        lv_zhinengyanglao.setAdapter(adapter13);
        listView.add(view13);
        View view14 = inflate(getActivity(), R.layout.fragment_home_zhinengjiankang, null);
        pb_progressBar = (CircularProgress) view14.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengjiankang = (RelativeLayout) view14.findViewById(R.id.rl_hide_zhinengjiankang);
        rl_empty14 = ((RelativeLayout) view14.findViewById(R.id.rl_empty14));
        tv14 = ((ShimmerTextView)view14.findViewById(R.id.shimmer_tv));
        source14 = "<i>联巢空间 </i>";
        tv14.setText(Html.fromHtml(source14));
        reload14 = ((Button) view14.findViewById(R.id.reload14));
        error_caterogy14 = ((RelativeLayout) view14.findViewById(R.id.error_caterogy14));
        ptr_arrlist_zhinengjiankang = ((PullToRefreshListView) view14.findViewById(R.id.ptr_arrlist_zhinengjiankang));
        lv_zhinengjiankang = ptr_arrlist_zhinengjiankang.getRefreshableView();
        adapter14 = new MyAdapter(arrList14);
        lv_zhinengjiankang.setAdapter(adapter14);
        listView.add(view14);
        View view15 = inflate(getActivity(), R.layout.fragment_home_zhinengyiliao, null);
        pb_progressBar = (CircularProgress) view15.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengyiliao = (RelativeLayout) view15.findViewById(R.id.rl_hide_zhinengyiliao);
        rl_empty15 = ((RelativeLayout) view15.findViewById(R.id.rl_empty15));
        tv15 = ((ShimmerTextView)view15.findViewById(R.id.shimmer_tv));
        source15 = "<i>联巢空间 </i>";
        tv15.setText(Html.fromHtml(source15));
        reload15 = ((Button) view15.findViewById(R.id.reload15));
        error_caterogy15 = ((RelativeLayout) view15.findViewById(R.id.error_caterogy15));
        ptr_arrlist_zhinengyiliao = ((PullToRefreshListView) view15.findViewById(R.id.ptr_arrlist_zhinengyiliao));
        lv_zhinengyiliao = ptr_arrlist_zhinengyiliao.getRefreshableView();
        adapter15 = new MyAdapter(arrList15);
        lv_zhinengyiliao.setAdapter(adapter15);
        listView.add(view15);
        View view16 = inflate(getActivity(), R.layout.fragment_home_jiqiren, null);
        pb_progressBar = (CircularProgress) view16.findViewById(R.id.pb_progressBar);
        rl_hide_jiqiren = (RelativeLayout) view16.findViewById(R.id.rl_hide_jiqiren);
        rl_empty16 = ((RelativeLayout) view16.findViewById(R.id.rl_empty16));
        tv16 = ((ShimmerTextView)view16.findViewById(R.id.shimmer_tv));
        source16 = "<i>联巢空间 </i>";
        tv16.setText(Html.fromHtml(source16));
        reload16 = ((Button) view16.findViewById(R.id.reload16));
        error_caterogy16 = ((RelativeLayout) view16.findViewById(R.id.error_caterogy16));
        ptr_arrlist_jiqiren = ((PullToRefreshListView) view16.findViewById(R.id.ptr_arrlist_jiqiren));
        lv_jiqiren = ptr_arrlist_jiqiren.getRefreshableView();
        adapter16 = new MyAdapter(arrList16);
        lv_jiqiren.setAdapter(adapter16);
        listView.add(view16);
        View view17 = inflate(getActivity(), R.layout.fragment_home_vrar, null);
        pb_progressBar = (CircularProgress) view17.findViewById(R.id.pb_progressBar);
        rl_hide_vrar = (RelativeLayout) view17.findViewById(R.id.rl_hide_vrar);
        rl_empty17 = ((RelativeLayout) view17.findViewById(R.id.rl_empty17));
        tv17 = ((ShimmerTextView)view17.findViewById(R.id.shimmer_tv));
        source17 = "<i>联巢空间 </i>";
        tv17.setText(Html.fromHtml(source17));
        reload17 = ((Button) view17.findViewById(R.id.reload17));
        error_caterogy17 = ((RelativeLayout) view17.findViewById(R.id.error_caterogy17));
        ptr_arrlist_vrar = ((PullToRefreshListView) view17.findViewById(R.id.ptr_arrlist_vrar));
        lv_vrar = ptr_arrlist_vrar.getRefreshableView();
        adapter17 = new MyAdapter(arrList17);
        lv_vrar.setAdapter(adapter17);
        listView.add(view17);
        View view18 = inflate(getActivity(), R.layout.fragment_home_zhanhuixinxi, null);
        pb_progressBar = (CircularProgress) view18.findViewById(R.id.pb_progressBar);
        rl_hide_zhanhuixinxi = (RelativeLayout) view18.findViewById(R.id.rl_hide_zhanhuixinxi);
        rl_empty18 = ((RelativeLayout) view18.findViewById(R.id.rl_empty18));
        tv18 = ((ShimmerTextView)view18.findViewById(R.id.shimmer_tv));
        source18 = "<i>联巢空间 </i>";
        tv18.setText(Html.fromHtml(source18));
        reload18 = ((Button) view18.findViewById(R.id.reload18));
        error_caterogy18 = ((RelativeLayout) view18.findViewById(R.id.error_caterogy18));
        ptr_arrlist_zhanhuixinxi = ((PullToRefreshListView) view18.findViewById(R.id.ptr_arrlist_zhanhuixinxi));
        lv_zhanhuixinxi = ptr_arrlist_wulianwang.getRefreshableView();
        adapter18 = new MyAdapter(arrList18);
        lv_zhanhuixinxi.setAdapter(adapter18);
        listView.add(view18);
        View view19 = inflate(getActivity(), R.layout.fragment_home_peixunxinxi, null);
        pb_progressBar = (CircularProgress) view19.findViewById(R.id.pb_progressBar);
        rl_hide_peixunxinxi = (RelativeLayout) view19.findViewById(R.id.rl_hide_peixunxinxi);
        rl_empty19 = ((RelativeLayout) view19.findViewById(R.id.rl_empty19));
        tv19 = ((ShimmerTextView)view19.findViewById(R.id.shimmer_tv));
        source19 = "<i>联巢空间 </i>";
        tv19.setText(Html.fromHtml(source19));
        reload19 = ((Button) view19.findViewById(R.id.reload19));
        error_caterogy19 = ((RelativeLayout) view19.findViewById(R.id.error_caterogy19));
        ptr_arrlist_peixunxinxi = ((PullToRefreshListView) view19.findViewById(R.id.ptr_arrlist_peixunxinxi));
        lv_peixunxinxi = ptr_arrlist_peixunxinxi.getRefreshableView();
        adapter19 = new MyAdapter(arrList19);
        lv_peixunxinxi.setAdapter(adapter19);
        listView.add(view19);
        View view20 = inflate(getActivity(), R.layout.fragment_home_zhichaofangan, null);
        pb_progressBar = (CircularProgress) view20.findViewById(R.id.pb_progressBar);
        rl_hide_zhichaofangan = (RelativeLayout) view20.findViewById(R.id.rl_hide_zhichaofangan);
        rl_empty20 = ((RelativeLayout) view20.findViewById(R.id.rl_empty20));
        tv20 = ((ShimmerTextView)view20.findViewById(R.id.shimmer_tv));
        source20 = "<i>联巢空间 </i>";
        tv20.setText(Html.fromHtml(source20));
        reload20 = ((Button) view20.findViewById(R.id.reload20));
        error_caterogy20 = ((RelativeLayout) view20.findViewById(R.id.error_caterogy20));
        ptr_arrlist_zhichaofangan = ((PullToRefreshListView) view20.findViewById(R.id.ptr_arrlist_zhichaofangan));
        lv_zhichaofangan = ptr_arrlist_zhichaofangan.getRefreshableView();

        adapter20 = new MyAdapter(arrList20);
        lv_zhichaofangan.setAdapter(adapter20);
        lv_zhichaofangan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private TextView tv_title;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList20.get(position - 1).article_id);
                Log.i("wenzhang", "article_id");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        listView.add(view20);
        View view21 = inflate(getActivity(), R.layout.fragment_home_zhichaoanli, null);
        pb_progressBar = (CircularProgress) view21.findViewById(R.id.pb_progressBar);
        rl_hide_zhichaoanli = (RelativeLayout) view21.findViewById(R.id.rl_hide_zhichaoanli);
        rl_empty21 = ((RelativeLayout) view21.findViewById(R.id.rl_empty21));
        tv21 = ((ShimmerTextView)view21.findViewById(R.id.shimmer_tv));
        source21 = "<i>联巢空间 </i>";
        tv21.setText(Html.fromHtml(source21));
        reload21 = ((Button) view21.findViewById(R.id.reload21));
        error_caterogy21 = ((RelativeLayout) view21.findViewById(R.id.error_caterogy21));
        ptr_arrlist_zhichaoanli = ((PullToRefreshListView) view21.findViewById(R.id.ptr_arrlist_zhichaoanli));
        lv_zhichaoanli = ptr_arrlist_zhichaoanli.getRefreshableView();
        adapter21 = new MyAdapter(arrList21);
        lv_zhichaoanli.setAdapter(adapter21);
        listView.add(view21);


    }

    private void initData() {
        ptr_arrlist_tuijian.setScrollingWhileRefreshingEnabled(true);
        ptr_arrlist_tuijian.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_arrlist_tuijian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                // Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        myRadioGroup = new RadioGroup(getActivity());
        //myRadioGroup.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        myRadioGroup.setGravity(Gravity.CENTER);
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
            radio.setBackgroundColor(0xFFf5f4f3);
            radio.setGravity(Gravity.CENTER);
            radio.setPadding(16, 7, 30, 7);
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

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(final RadioGroup group,final int checkedId) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Map<String, Object> map = (Map<String, Object>) group.getChildAt(checkedId).getTag();
                                int radioButtonId = group.getCheckedRadioButtonId();
                                //根据ID获取RadioButton的实例
                                rb = (RadioButton) view.findViewById(radioButtonId);
                                Map<String, Object> selectMap = (Map<String, Object>) rb.getTag();
                                rb.measure(0,0);
                                mCurrentCheckedRadioLeft = rb.getLeft();
                                //Toast.makeText(getActivity(), checkedId+"=="+group.getCheckedRadioButtonId()+"==="+((int) mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.activity_horizontal_margin)), Toast.LENGTH_SHORT).show();

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
                                //hs_navi.smoothScrollTo((int) mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                hs_navi.smoothScrollTo((int) mCurrentCheckedRadioLeft - (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);

                                            }
                                        });
                                    }
                                },10);

                                mImageView.setLayoutParams(new LinearLayout.LayoutParams(rb.getRight() - rb.getLeft(), 4));
                                switch (checkedId){
                                    case 1000:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_tuijian.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_tuijian.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_tuijian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                               // String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                                    //    android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                               // refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                                // Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                        lv_tuijian.setAdapter(adapter1);

                                        Log.i("aaaaa", "数据拿到");
                                        gettuijian();
                                        break;
                                    case 1001:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_hangyezixun.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_hangyezixun.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_hangyezixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                                // Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        lv_hangyezixun.setAdapter(adapter2);

                                        Log.i("aaaaa", "数据拿到");

                                        gethangyezixun();
                                        break;
                                    case 1002:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_canpinzixun.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_canpinzixun.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_canpinzixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                                //   Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        lv_canpinzixun.setAdapter(adapter3);

                                        Log.i("aaaaa", "数据拿到");

                                        getcanpinzixun();
                                        break;
                                    case 1003:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_canpinpince.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_canpinpince.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_canpinpince.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_canpinpince.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getcanpinpince();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getcanpinpince();

                                                }
                                            }
                                        });
                                        ptr_arrlist_canpinpince.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                // Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                        lv_canpinpince.setAdapter(adapter4);
                                        getcanpinpince();
                                        break;
                                    case 1004:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_video.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_video.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_video.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_video.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getvideo();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getvideo();

                                                }
                                            }
                                        });
                                        ptr_arrlist_video.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //  Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        lv_video.setAdapter(adapter5);
                                        getvideo();
                                        break;
                                    case 1005:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_pinpaizixun.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_pinpaizixun.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_pinpaizixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_pinpaizixun.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getpinpaizixun();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getpinpaizixun();

                                                }
                                            }
                                        });
                                        ptr_arrlist_pinpaizixun.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //    Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        lv_pinpaizixun.setAdapter(adapter6);
                                        getpinpaizixun();
                                        break;
                                    case 1006:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_hangyebiaozhun.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_hangyebiaozhun.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_hangyebiaozhun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_hangyebiaozhun.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    gethangyebiaozhun();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    gethangyebiaozhun();

                                                }
                                            }
                                        });
                                        ptr_arrlist_hangyebiaozhun.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //   Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_hangyebiaozhun.setAdapter(adapter7);
                                        gethangyebiaozhun();
                                        break;
                                    case 1007:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_hangyejishu.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_hangyejishu.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_hangyejishu.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_hangyejishu.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    gethangyejishu();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    gethangyejishu();

                                                }
                                            }
                                        });
                                        ptr_arrlist_hangyejishu.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //  Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_hangyejishu.setAdapter(adapter8);
                                        gethangyejishu();
                                        break;
                                    case 1008:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_wulianwang.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_wulianwang.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_wulianwang.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_wulianwang.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getwulianwang();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getwulianwang();

                                                }
                                            }
                                        });
                                        ptr_arrlist_wulianwang.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_wulianwang.setAdapter(adapter9);
                                        getwulianwang();
                                        break;
                                    case 1009:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhihuichengshi.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhihuichengshi.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhihuichengshi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhihuichengshi.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhihuichengshi();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhihuichengshi();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhihuichengshi.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhihuichengshi.setAdapter(adapter10);
                                        getzhihuichengshi();
                                        break;
                                    case 1010:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhihuishequ.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhihuishequ.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhihuishequ.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhihuishequ.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhihuishequ();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhihuishequ();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhihuishequ.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhihuishequ.setAdapter(adapter11);
                                        getzhihuishequ();
                                        break;
                                    case 1011:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_wurenji.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_wurenji.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_wurenji.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_wurenji.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getwurenji();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getwurenji();

                                                }
                                            }
                                        });
                                        ptr_arrlist_wurenji.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_wurenji.setAdapter(adapter12);
                                        getwurenji();
                                        break;
                                    case 1012:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhinengyanglao.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhinengyanglao.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhinengyanglao.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhinengyanglao.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhinengyanglao();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhinengyanglao();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhinengyanglao.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhinengyanglao.setAdapter(adapter13);
                                        getzhinengyanglao();
                                        break;
                                    case 1013:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhinengjiankang.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhinengjiankang.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhinengjiankang.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhinengjiankang.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhinengjiankang();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhinengjiankang();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhinengjiankang.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhinengjiankang.setAdapter(adapter14);
                                        getzhinengjiankang();
                                        break;
                                    case 1014:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhinengyiliao.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhinengyiliao.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhinengyiliao.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhinengyiliao.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhinengyiliao();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhinengyiliao();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhinengyiliao.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhinengyiliao.setAdapter(adapter15);
                                        getzhinengyiliao();
                                        break;
                                    case 1015:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_jiqiren.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_jiqiren.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_jiqiren.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_jiqiren.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getjiqiren();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getjiqiren();

                                                }
                                            }
                                        });
                                        ptr_arrlist_jiqiren.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_jiqiren.setAdapter(adapter16);
                                        getjiqiren();
                                        break;
                                    case 1016:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_vrar.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_vrar.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_vrar.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_vrar.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getvrar();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getvrar();

                                                }
                                            }
                                        });
                                        ptr_arrlist_vrar.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_vrar.setAdapter(adapter17);
                                        getvrar();
                                        break;
                                    case 1017:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhanhuixinxi.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhanhuixinxi.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhanhuixinxi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhanhuixinxi.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhanhuixinxi();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhanhuixinxi();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhanhuixinxi.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhanhuixinxi.setAdapter(adapter18);
                                        getzhanhuixinxi();
                                        break;
                                    case 1018:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_peixunxinxi.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_peixunxinxi.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_peixunxinxi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_peixunxinxi.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getpeixunxinxi();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getpeixunxinxi();

                                                }
                                            }
                                        });
                                        ptr_arrlist_peixunxinxi.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_peixunxinxi.setAdapter(adapter19);
                                        getpeixunxinxi();
                                        break;
                                    case 1019:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhichaofangan.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhichaofangan.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhichaofangan.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhichaofangan.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhichaofangan();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhichaofangan();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhichaofangan.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //    Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhichaofangan.setAdapter(adapter20);
                                        getzhichaofangan();
                                        break;
                                    case 1020:
                                        page=1;
                                        pullFlag = false;
                                        ptr_arrlist_zhichaoanli.setScrollingWhileRefreshingEnabled(true);
                                        ptr_arrlist_zhichaoanli.setMode(PullToRefreshBase.Mode.BOTH);
                                        ptr_arrlist_zhichaoanli.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                                            @Override
                                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
//                                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
//
//                                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                                                //异步任务拿数据

                                                PullToRefreshBase.Mode mode = ptr_arrlist_zhichaoanli.getCurrentMode();

                                                // View viewRefresh = null;

                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                                                    page++;
                                                    getzhichaoanli();
                                                }
                                                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                                                    pullFlag = true;
                                                    page = 1;
                                                    getzhichaoanli();

                                                }
                                            }
                                        });
                                        ptr_arrlist_zhichaoanli.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                                            @Override
                                            public void onLastItemVisible() {
                                                //  Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        lv_zhichaoanli.setAdapter(adapter21);
                                        getzhichaoanli();
                                        break;
                                    default:

                                        break;


                                }
                            }
                        });
                    }
                },40);
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
              //  radioButton.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        MypAdapter adapter = new MypAdapter(listView);
        vp_news.setOffscreenPageLimit(2);
        vp_news.setAdapter(adapter);


    }

    private void getjiqiren() {

        rl_empty16.setVisibility(View.GONE);
        error_caterogy16.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_jiqiren.setVisibility(View.GONE);
            rl_hide_jiqiren.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv16);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "45");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_jiqiren.setVisibility(View.GONE);
                rl_hide_jiqiren.setVisibility(View.GONE);
                if (page == 1) {
                    arrList16.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList16);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_jiqiren.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList16.addAll(articleData.getArticle_list());
                        adapter16.notifyDataSetChanged();
                        ptr_arrlist_jiqiren.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_jiqiren.onRefreshComplete();
                    }
                } else {
                    rl_hide_jiqiren.setVisibility(View.GONE);
                    rl_empty16.setVisibility(View.VISIBLE);
                    ptr_arrlist_jiqiren.setVisibility(View.GONE);
                    error_caterogy16.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_jiqiren.setVisibility(View.GONE);
                rl_empty16.setVisibility(View.GONE);
                ptr_arrlist_jiqiren.setVisibility(View.GONE);
                error_caterogy16.setVisibility(View.VISIBLE);
                reload16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getjiqiren();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhinengyiliao() {
        rl_empty15.setVisibility(View.GONE);
        error_caterogy15.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhinengyiliao.setVisibility(View.GONE);
            rl_hide_zhinengyiliao.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv15);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "44");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhinengyiliao.setVisibility(View.GONE);
                rl_hide_zhinengyiliao.setVisibility(View.GONE);
                if (page == 1) {
                    arrList15.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList15);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhinengyiliao.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList15.addAll(articleData.getArticle_list());
                        adapter15.notifyDataSetChanged();
                        ptr_arrlist_zhinengyiliao.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhinengyiliao.onRefreshComplete();
                    }
                } else {
                    rl_hide_zhinengyiliao.setVisibility(View.GONE);
                    rl_empty15.setVisibility(View.VISIBLE);
                    ptr_arrlist_zhinengyiliao.setVisibility(View.GONE);
                    error_caterogy15.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhinengyiliao.setVisibility(View.GONE);
                rl_empty15.setVisibility(View.GONE);
                ptr_arrlist_zhinengyiliao.setVisibility(View.GONE);
                error_caterogy15.setVisibility(View.VISIBLE);
                reload15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhinengyiliao();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getvrar() {
        rl_empty17.setVisibility(View.GONE);
        error_caterogy17.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_vrar.setVisibility(View.GONE);
            rl_hide_vrar.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv17);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "48");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_vrar.setVisibility(View.GONE);
                rl_hide_vrar.setVisibility(View.GONE);
                if (page == 1) {
                    arrList17.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList17);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_vrar.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList17.addAll(articleData.getArticle_list());
                        adapter17.notifyDataSetChanged();
                        ptr_arrlist_vrar.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_vrar.onRefreshComplete();
                    }
                } else {
                    rl_hide_vrar.setVisibility(View.GONE);
                    rl_empty17.setVisibility(View.VISIBLE);
                    ptr_arrlist_vrar.setVisibility(View.GONE);
                    error_caterogy17.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_vrar.setVisibility(View.GONE);
                rl_empty17.setVisibility(View.GONE);
                ptr_arrlist_vrar.setVisibility(View.GONE);
                error_caterogy17.setVisibility(View.VISIBLE);
                reload17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getvrar();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhanhuixinxi() {
        rl_empty18.setVisibility(View.GONE);
        error_caterogy18.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhanhuixinxi.setVisibility(View.GONE);
            rl_hide_zhanhuixinxi.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv18);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "38");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhanhuixinxi.setVisibility(View.GONE);
                rl_hide_zhanhuixinxi.setVisibility(View.GONE);
                if (page == 1) {
                    arrList18.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList18);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhanhuixinxi.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList18.addAll(articleData.getArticle_list());
                        adapter18.notifyDataSetChanged();
                        ptr_arrlist_zhanhuixinxi.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhanhuixinxi.onRefreshComplete();
                    }
                } else {
                    rl_hide_zhanhuixinxi.setVisibility(View.GONE);
                    rl_empty18.setVisibility(View.VISIBLE);
                    ptr_arrlist_zhanhuixinxi.setVisibility(View.GONE);
                    error_caterogy18.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhanhuixinxi.setVisibility(View.GONE);
                rl_empty18.setVisibility(View.GONE);
                ptr_arrlist_zhanhuixinxi.setVisibility(View.GONE);
                error_caterogy18.setVisibility(View.VISIBLE);
                reload18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhanhuixinxi();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getpeixunxinxi() {
        rl_empty19.setVisibility(View.GONE);
        error_caterogy19.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_peixunxinxi.setVisibility(View.GONE);
            rl_hide_peixunxinxi.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv19);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "39");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_peixunxinxi.setVisibility(View.GONE);
                rl_hide_peixunxinxi.setVisibility(View.GONE);
                if (page == 1) {
                    arrList19.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList19);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_peixunxinxi.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList19.addAll(articleData.getArticle_list());
                        adapter19.notifyDataSetChanged();
                        ptr_arrlist_peixunxinxi.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_peixunxinxi.onRefreshComplete();
                    }
                } else {
                    rl_hide_peixunxinxi.setVisibility(View.GONE);
                    rl_empty19.setVisibility(View.VISIBLE);
                    ptr_arrlist_peixunxinxi.setVisibility(View.GONE);
                    error_caterogy19.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_peixunxinxi.setVisibility(View.GONE);
                rl_empty19.setVisibility(View.GONE);
                ptr_arrlist_peixunxinxi.setVisibility(View.GONE);
                error_caterogy19.setVisibility(View.VISIBLE);
                reload19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getpeixunxinxi();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhichaofangan() {
        rl_empty20.setVisibility(View.GONE);
        error_caterogy20.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhichaofangan.setVisibility(View.GONE);
            rl_hide_zhichaofangan.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv20);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "16");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhichaofangan.setVisibility(View.GONE);
                rl_hide_zhichaofangan.setVisibility(View.GONE);
                if (page == 1) {
                    arrList20.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList20);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhichaofangan.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList20.addAll(articleData.getArticle_list());
                        adapter20.notifyDataSetChanged();
                        ptr_arrlist_zhichaofangan.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhichaofangan.onRefreshComplete();
                    }
                } else {
                    rl_hide_zhichaofangan.setVisibility(View.GONE);
                    rl_empty20.setVisibility(View.VISIBLE);
                    ptr_arrlist_zhichaofangan.setVisibility(View.GONE);
                    error_caterogy20.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhichaofangan.setVisibility(View.GONE);
                rl_empty20.setVisibility(View.GONE);
                ptr_arrlist_zhichaofangan.setVisibility(View.GONE);
                error_caterogy20.setVisibility(View.VISIBLE);
                reload20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhichaofangan();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhichaoanli() {
        rl_empty21.setVisibility(View.GONE);
        error_caterogy21.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {

            ptr_arrlist_zhichaoanli.setVisibility(View.GONE);
            rl_hide_zhichaoanli.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv21);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "45");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhichaoanli.setVisibility(View.GONE);
                rl_hide_zhichaoanli.setVisibility(View.GONE);
                if (page == 1) {
                    arrList21.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList21);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhichaoanli.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList21.addAll(articleData.getArticle_list());
                        adapter21.notifyDataSetChanged();
                        ptr_arrlist_zhichaoanli.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhichaoanli.onRefreshComplete();
                    }
                } else {

                    rl_empty21.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhichaoanli.setVisibility(View.GONE);
                ptr_arrlist_zhichaoanli.setVisibility(View.GONE);
                rl_empty21.setVisibility(View.GONE);
                error_caterogy21.setVisibility(View.VISIBLE);
                reload21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhichaoanli();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhinengjiankang() {
        rl_empty14.setVisibility(View.GONE);
        error_caterogy14.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhinengjiankang.setVisibility(View.GONE);
            rl_hide_zhinengjiankang.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv14);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "43");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhinengjiankang.setVisibility(View.GONE);
                rl_hide_zhinengjiankang.setVisibility(View.GONE);
                if (page == 1) {
                    arrList14.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList14);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhinengjiankang.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList14.addAll(articleData.getArticle_list());
                        adapter14.notifyDataSetChanged();
                        ptr_arrlist_zhinengjiankang.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhinengjiankang.onRefreshComplete();
                    }
                } else {

                    rl_empty14.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhinengjiankang.setVisibility(View.GONE);
                ptr_arrlist_zhinengjiankang.setVisibility(View.GONE);
                rl_empty14.setVisibility(View.GONE);
                error_caterogy14.setVisibility(View.VISIBLE);
                reload14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhinengjiankang();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getwurenji() {
        rl_empty12.setVisibility(View.GONE);
        error_caterogy12.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_wurenji.setVisibility(View.GONE);
            rl_hide_wurenji.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv12);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "46");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_wurenji.setVisibility(View.GONE);
                rl_hide_wurenji.setVisibility(View.GONE);
                if (page == 1) {
                    arrList12.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList12);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_wurenji.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList12.addAll(articleData.getArticle_list());
                        adapter12.notifyDataSetChanged();
                        ptr_arrlist_wurenji.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_wurenji.onRefreshComplete();
                    }
                } else {

                    rl_empty12.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_wurenji.setVisibility(View.GONE);
                ptr_arrlist_wurenji.setVisibility(View.GONE);
                rl_empty12.setVisibility(View.GONE);
                error_caterogy12.setVisibility(View.VISIBLE);
                reload12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getwurenji();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhinengyanglao() {

        rl_empty13.setVisibility(View.GONE);
        error_caterogy13.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhinengyanglao.setVisibility(View.GONE);
            rl_hide_zhinengyanglao.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv13);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "42");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhinengyanglao.setVisibility(View.GONE);
                rl_hide_zhinengyanglao.setVisibility(View.GONE);
                if (page == 1) {
                    arrList13.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList13);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhinengyanglao.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList13.addAll(articleData.getArticle_list());
                        adapter13.notifyDataSetChanged();
                        ptr_arrlist_zhinengyanglao.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhinengyanglao.onRefreshComplete();
                    }
                } else {

                    rl_empty13.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhinengyanglao.setVisibility(View.GONE);
                ptr_arrlist_zhinengyanglao.setVisibility(View.GONE);
                rl_empty13.setVisibility(View.GONE);
                error_caterogy13.setVisibility(View.VISIBLE);
                reload13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhinengyanglao();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhihuishequ() {
        rl_empty11.setVisibility(View.GONE);
        error_caterogy11.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            rl_hide_zhihuishequ.setVisibility(View.VISIBLE);
            ptr_arrlist_zhihuishequ.setVisibility(View.GONE);
            shimmer = new Shimmer();
            shimmer.start(tv11);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "41");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhihuishequ.setVisibility(View.GONE);
                rl_hide_zhihuishequ.setVisibility(View.GONE);
                if (page == 1) {
                    arrList11.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList11);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhihuishequ.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList11.addAll(articleData.getArticle_list());
                        adapter11.notifyDataSetChanged();
                        ptr_arrlist_zhihuishequ.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhihuishequ.onRefreshComplete();
                    }
                } else {

                    rl_empty11.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhihuishequ.setVisibility(View.GONE);
                ptr_arrlist_zhihuishequ.setVisibility(View.GONE);
                rl_empty11.setVisibility(View.GONE);
                error_caterogy11.setVisibility(View.VISIBLE);
                reload11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhihuishequ();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getzhihuichengshi() {

        rl_empty10.setVisibility(View.GONE);
        error_caterogy10.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_zhihuichengshi.setVisibility(View.GONE);
            rl_hide_zhihuichengshi.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv10);

        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "40");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_zhihuichengshi.setVisibility(View.GONE);
                rl_hide_zhihuichengshi.setVisibility(View.GONE);
                if (page == 1) {
                    arrList10.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList10);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_zhihuichengshi.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList10.addAll(articleData.getArticle_list());
                        adapter10.notifyDataSetChanged();
                        ptr_arrlist_zhihuichengshi.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_zhihuichengshi.onRefreshComplete();
                    }
                } else {

                    rl_empty10.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_zhihuichengshi.setVisibility(View.GONE);
                ptr_arrlist_zhihuichengshi.setVisibility(View.GONE);
                rl_empty10.setVisibility(View.GONE);
                error_caterogy10.setVisibility(View.VISIBLE);
                reload10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getzhihuichengshi();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getwulianwang() {
        rl_empty9.setVisibility(View.GONE);
        error_caterogy9.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_wulianwang.setVisibility(View.GONE);
            rl_hide_wulianwang.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv9);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "47");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_wulianwang.setVisibility(View.GONE);
                rl_hide_wulianwang.setVisibility(View.GONE);
                if (page == 1) {
                    arrList9.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList9);

                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_wulianwang.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList9.addAll(articleData.getArticle_list());
                        adapter9.notifyDataSetChanged();
                        ptr_arrlist_wulianwang.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_wulianwang.onRefreshComplete();
                    }
                } else {

                    rl_empty9.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_wulianwang.setVisibility(View.GONE);
                ptr_arrlist_wulianwang.setVisibility(View.GONE);
                rl_empty9.setVisibility(View.GONE);
                error_caterogy9.setVisibility(View.VISIBLE);
                reload9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getwulianwang();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void gethangyejishu() {

        rl_empty8.setVisibility(View.GONE);
        error_caterogy8.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_hangyejishu.setVisibility(View.GONE);
            rl_hide_hangyejishu.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv8);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "36");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_hangyejishu.setVisibility(View.GONE);
                rl_hide_hangyejishu.setVisibility(View.GONE);
                if (page == 1) {
                    arrList8.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList8);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_hangyejishu.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList8.addAll(articleData.getArticle_list());
                        adapter8.notifyDataSetChanged();
                        ptr_arrlist_hangyejishu.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_hangyejishu.onRefreshComplete();
                    }
                } else {

                    rl_empty8.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_hangyejishu.setVisibility(View.GONE);
                ptr_arrlist_hangyejishu.setVisibility(View.GONE);
                rl_empty8.setVisibility(View.GONE);
                error_caterogy8.setVisibility(View.VISIBLE);
                reload8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gethangyejishu();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void gethangyebiaozhun() {
        rl_empty7.setVisibility(View.GONE);
        error_caterogy7.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_hangyebiaozhun.setVisibility(View.GONE);
            rl_hide_hangyebiaozhun.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv7);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "32");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_hangyebiaozhun.setVisibility(View.GONE);
                rl_hide_hangyebiaozhun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList7.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList7);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_hangyebiaozhun.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList7.addAll(articleData.getArticle_list());
                        adapter7.notifyDataSetChanged();
                        ptr_arrlist_hangyebiaozhun.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_hangyebiaozhun.onRefreshComplete();
                    }
                } else {

                    rl_empty7.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_hangyebiaozhun.setVisibility(View.GONE);
                ptr_arrlist_hangyebiaozhun.setVisibility(View.GONE);
                rl_empty7.setVisibility(View.GONE);
                error_caterogy7.setVisibility(View.VISIBLE);
                reload7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gethangyebiaozhun();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getpinpaizixun() {
        rl_empty6.setVisibility(View.GONE);
        error_caterogy6.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_pinpaizixun.setVisibility(View.GONE);
            rl_hide_pinpaizixun.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv6);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "19");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_pinpaizixun.setVisibility(View.GONE);
                rl_hide_pinpaizixun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList6.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList6);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_pinpaizixun.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList6.addAll(articleData.getArticle_list());
                        adapter6.notifyDataSetChanged();
                        ptr_arrlist_pinpaizixun.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_pinpaizixun.onRefreshComplete();
                    }
                } else {

                    rl_empty6.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_pinpaizixun.setVisibility(View.GONE);
                ptr_arrlist_pinpaizixun.setVisibility(View.GONE);
                rl_empty6.setVisibility(View.GONE);
                error_caterogy6.setVisibility(View.VISIBLE);
                reload6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getpinpaizixun();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getvideo() {
        rl_empty5.setVisibility(View.GONE);
        error_caterogy5.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_video.setVisibility(View.GONE);
            rl_hide_video.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv5);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "15");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                ptr_arrlist_video.setVisibility(View.GONE);
                rl_hide_video.setVisibility(View.GONE);

                if (page == 1) {
                    arrList5.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                Log.i("数组长度", articleData.getArticle_list().size() + "");
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_video.setVisibility(View.VISIBLE);
                    System.out.println(arrList5);
                    if (page <= pageCount) {
                        arrList5.addAll(articleData.getArticle_list());
                        adapter5.notifyDataSetChanged();
                        ptr_arrlist_video.onRefreshComplete();

                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        adapter5.notifyDataSetChanged();
                        ptr_arrlist_video.onRefreshComplete();

                    }

                } else {
                    rl_hide_video.setVisibility(View.GONE);
                    rl_empty5.setVisibility(View.VISIBLE);
                    ptr_arrlist_video.setVisibility(View.GONE);
                    error_caterogy5.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG1, "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                rl_hide_video.setVisibility(View.GONE);
                ptr_arrlist_video.setVisibility(View.GONE);
                rl_empty5.setVisibility(View.GONE);
                error_caterogy5.setVisibility(View.VISIBLE);
                reload5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getvideo();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getcanpinpince() {
        rl_empty4.setVisibility(View.GONE);
        error_caterogy4.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            ptr_arrlist_canpinpince.setVisibility(View.GONE);
            rl_hide_canpinpince.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv4);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "14");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_canpinpince.setVisibility(View.GONE);
                rl_hide_canpinpince.setVisibility(View.GONE);
                if (page == 1) {
                    arrList4.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList4);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_canpinpince.setVisibility(View.VISIBLE);

                    if (page <= pageCount) {
                        arrList4.addAll(articleData.getArticle_list());
                        adapter4.notifyDataSetChanged();
                        ptr_arrlist_canpinpince.onRefreshComplete();

                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_canpinpince.onRefreshComplete();
                    }
                } else {
                    rl_hide_canpinpince.setVisibility(View.GONE);
                    rl_empty4.setVisibility(View.VISIBLE);
                    ptr_arrlist_canpinpince.setVisibility(View.GONE);
                    error_caterogy4.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_canpinpince.setVisibility(View.GONE);
                rl_empty4.setVisibility(View.GONE);
                ptr_arrlist_canpinpince.setVisibility(View.GONE);
                error_caterogy4.setVisibility(View.VISIBLE);
                reload4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getcanpinpince();
                    }
                });
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void gettuijian() {
        //initAdvList();
        Log.i("aaaaa", "数据拿到");
        rl_empty1.setVisibility(View.GONE);
        error_caterogy1.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            rl_hide_tuijian.setVisibility(View.VISIBLE);
            ptr_arrlist_tuijian.setVisibility(View.GONE);
            shimmer = new Shimmer();
            shimmer.start(tv1);
        }

        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "12");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                ptr_arrlist_tuijian.setVisibility(View.GONE);
                rl_hide_tuijian.setVisibility(View.GONE);
                if (page == 1) {
                    arrList1.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList1);
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_tuijian.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList1.addAll(articleData.getArticle_list());
                        adapter1.notifyDataSetChanged();
                        ptr_arrlist_tuijian.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_tuijian.onRefreshComplete();
                        adapter1.notifyDataSetChanged();
                    }
                } else {
                    rl_hide_tuijian.setVisibility(View.GONE);
                    rl_empty1.setVisibility(View.VISIBLE);
                    ptr_arrlist_tuijian.setVisibility(View.GONE);
                    error_caterogy1.setVisibility(View.GONE);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG1, "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                rl_hide_tuijian.setVisibility(View.GONE);
                //ptr_arrlist_tuijian.setVisibility(View.GONE);
                rl_empty1.setVisibility(View.GONE);
                error_caterogy1.setVisibility(View.VISIBLE);
                reload1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gettuijian();
                    }
                });
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
        Log.i("aaaaa", "数据拿到");
        rl_empty2.setVisibility(View.GONE);
        error_caterogy2.setVisibility(View.GONE);
        if (page == 1 && pullFlag == false) {
            ptr_arrlist_hangyezixun.setVisibility(View.GONE);
            rl_hide_hangyezixun.setVisibility(View.VISIBLE);
            shimmer = new Shimmer();
            shimmer.start(tv2);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "12");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_hangyezixun.setVisibility(View.GONE);
                rl_hide_hangyezixun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList2.clear();

                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i(TAG1, articleData.getArticle_list() + "");
                System.out.println(arrList2);

                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_hangyezixun.setVisibility(View.VISIBLE);
                    if (page <= pageCount) {
                        arrList2.addAll(articleData.getArticle_list());
                        adapter2.notifyDataSetChanged();
                        ptr_arrlist_hangyezixun.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_hangyezixun.onRefreshComplete();


                    }
                } else {
                    rl_hide_hangyezixun.setVisibility(View.GONE);
                    rl_empty2.setVisibility(View.VISIBLE);
                    ptr_arrlist_hangyezixun.setVisibility(View.GONE);
                    error_caterogy2.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_hangyezixun.setVisibility(View.GONE);
                rl_empty2.setVisibility(View.GONE);
                ptr_arrlist_hangyezixun.setVisibility(View.GONE);
                error_caterogy2.setVisibility(View.VISIBLE);
                reload2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gethangyezixun();
                    }
                });
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
        Log.i("aaaaa", "数据拿到");
        rl_empty3.setVisibility(View.GONE);
        error_caterogy3.setVisibility(View.GONE);

        if (page == 1 && pullFlag == false) {
            rl_hide_canpinzixun.setVisibility(View.VISIBLE);
            ptr_arrlist_canpinzixun.setVisibility(View.GONE);
            shimmer = new Shimmer();
            shimmer.start(tv3);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "18");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_canpinzixun.setVisibility(View.GONE);
                rl_hide_canpinzixun.setVisibility(View.GONE);
                if (page == 1) {
                    arrList3.clear();
                }
                Log.i(TAG1, result + "");
                Gson gson = new Gson();
                ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                ArticleListBean.Data articleData = bean.data;
                pageCount = Integer.parseInt(articleData.page_count);
                Log.i("数组长度", articleData.getArticle_list().size() + "");

                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_canpinzixun.setVisibility(View.VISIBLE);
                    System.out.println(arrList3);

//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面


                    if (page <= pageCount) {
                        arrList3.addAll(articleData.getArticle_list());
                        adapter3.notifyDataSetChanged();
                        ptr_arrlist_canpinzixun.onRefreshComplete();
                    } else {
                        Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_canpinzixun.onRefreshComplete();
                    }
                } else {

                    rl_empty3.setVisibility(View.VISIBLE);


                }
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_hide_canpinzixun.setVisibility(View.GONE);
                ptr_arrlist_canpinzixun.setVisibility(View.GONE);
                rl_empty3.setVisibility(View.GONE);
                error_caterogy3.setVisibility(View.VISIBLE);
                Log.i(TAG1, "exexex" + ex + "");
                reload3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getcanpinzixun();
                    }
                });

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
        private List<ArticleListBean.Article_list> list;

        public MyAdapter(List<ArticleListBean.Article_list> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
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
                viewHolder.tv_source = ((TextView) convertView.findViewById(tv_source));


                convertView.setTag(viewHolder);//缓存对象的
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //ListActivityBean.Dongtai dongtai = dongtaiList.get(position);
            ArticleListBean.Article_list arr = list.get(position);

            viewHolder.tv_title.setText(arr.title.replace(" ", ""));
            viewHolder.tv_source.setText(arr.source);

            viewHolder.tv_name.setText(linchom.com.linchomspace.homepage.Utils.DateUtils.getGapTimeFromNow(DateUtils.stringToDate(arr.date)));
            if (arr.photo == ""){
                viewHolder.iv_photo.setImageResource(R.drawable.aiqinhai);
            } else {
                viewHolder.iv_photo.setVisibility(View.VISIBLE);
              //  xUtilsImageUtils.display(viewHolder.iv_photo, "http://linchom.com//" + arr.article_pic);
                xUtilsImageUtils.display(viewHolder.iv_photo, arr.photo);
            }
            return convertView;
        }
    }

    private static class ViewHolder {
        private TextView tv_title;
        private TextView tv_name;
        private TextView tv_source;
        private ImageView iv_photo;
    }


}
