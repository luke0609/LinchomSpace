package linchom.com.linchomspace.homepage.Fragment;

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
import linchom.com.linchomspace.homepage.Activity.ArticleActivity;
import linchom.com.linchomspace.homepage.Activity.NavigationActivity;
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
    final List<ArticleListBean.Article_list> arrList = new ArrayList<ArticleListBean.Article_list>();
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
                Intent intent=new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("article_id",arrList.get(position-2).article_id);
                Log.i("wenzhang","article_id");
                intent.putExtra("bundle",bundle);
                startActivity(intent);
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
        View view4 = inflate(getActivity(), R.layout.fragment_home_canpinpince, null);
        pb_progressBar= (CircularProgress) view4.findViewById(R.id.pb_progressBar);
        rl_hide_canpinpince = (RelativeLayout) view4.findViewById(R.id.rl_hide_canpinpince);
        ptr_arrlist_canpinpince = ((PullToRefreshListView) view4.findViewById(R.id.ptr_arrlist_canpinpince));
        lv_canpinpince= ptr_arrlist_canpinpince.getRefreshableView();
        adapter4 = new MyAdapter();
        lv_canpinpince.setAdapter(adapter4);
        listView.add(view4);
        View view5 = inflate(getActivity(), R.layout.fragment_home_video, null);
        pb_progressBar= (CircularProgress) view5.findViewById(R.id.pb_progressBar);
        rl_hide_video = (RelativeLayout) view5.findViewById(R.id.rl_hide_video);
        ptr_arrlist_video = ((PullToRefreshListView) view5.findViewById(R.id.ptr_arrlist_video));
        lv_video= ptr_arrlist_video.getRefreshableView();
        adapter5 = new MyAdapter();
        lv_video.setAdapter(adapter5);
        listView.add(view5);
        View view6 = inflate(getActivity(), R.layout.fragment_home_pinpaizixun, null);
        pb_progressBar= (CircularProgress) view6.findViewById(R.id.pb_progressBar);
        rl_hide_pinpaizixun = (RelativeLayout) view6.findViewById(R.id.rl_hide_pinpaizixun);
        ptr_arrlist_pinpaizixun = ((PullToRefreshListView) view6.findViewById(R.id.ptr_arrlist_pinpaizixun));
        lv_pinpaizixun= ptr_arrlist_pinpaizixun.getRefreshableView();
        adapter6 = new MyAdapter();
        lv_pinpaizixun.setAdapter(adapter6);
        listView.add(view6);
        View view7 = inflate(getActivity(), R.layout.fragment_home_hangyebiaozhun, null);
        pb_progressBar= (CircularProgress) view7.findViewById(R.id.pb_progressBar);
        rl_hide_hangyebiaozhun = (RelativeLayout) view7.findViewById(R.id.rl_hide_hangyebiaozhun);
        ptr_arrlist_hangyebiaozhun= ((PullToRefreshListView) view7.findViewById(R.id.ptr_arrlist_hangyebiaozhun));
        lv_hangyebiaozhun= ptr_arrlist_hangyebiaozhun.getRefreshableView();
        adapter7 = new MyAdapter();
        lv_hangyebiaozhun.setAdapter(adapter7);
        listView.add(view7);
        View view8 = inflate(getActivity(), R.layout.fragment_home_hangyejishu, null);
        pb_progressBar= (CircularProgress) view8.findViewById(R.id.pb_progressBar);
        rl_hide_hangyejishu = (RelativeLayout) view8.findViewById(R.id.rl_hide_hangyejishu);
        ptr_arrlist_hangyejishu= ((PullToRefreshListView) view8.findViewById(R.id.ptr_arrlist_hangyejishu));
        lv_hangyejishu= ptr_arrlist_hangyejishu.getRefreshableView();
        adapter8 = new MyAdapter();
        lv_hangyejishu.setAdapter(adapter8);
        listView.add(view8);
        View view9 = inflate(getActivity(), R.layout.fragment_home_wulianwang, null);
        pb_progressBar= (CircularProgress) view9.findViewById(R.id.pb_progressBar);
        rl_hide_wulianwang = (RelativeLayout) view9.findViewById(R.id.rl_hide_wulianwang);
        ptr_arrlist_wulianwang= ((PullToRefreshListView) view9.findViewById(R.id.ptr_arrlist_wulianwang));
        lv_wulianwang= ptr_arrlist_wulianwang.getRefreshableView();
        adapter9 = new MyAdapter();
        lv_wulianwang.setAdapter(adapter9);
        listView.add(view9);
        View view10 = inflate(getActivity(), R.layout.fragment_home_zhihuichengshi, null);
        pb_progressBar= (CircularProgress) view10.findViewById(R.id.pb_progressBar);
        rl_hide_zhihuichengshi = (RelativeLayout) view10.findViewById(R.id.rl_hide_zhihuichengshi);
        ptr_arrlist_zhihuichengshi= ((PullToRefreshListView) view10.findViewById(R.id.ptr_arrlist_zhihuichengshi));
        lv_zhihuichengshi= ptr_arrlist_zhihuichengshi.getRefreshableView();
        adapter10 = new MyAdapter();
        lv_zhihuichengshi.setAdapter(adapter10);
        listView.add(view10);
        View view11 = inflate(getActivity(), R.layout.fragment_home_zhihuishequ, null);
        pb_progressBar= (CircularProgress) view11.findViewById(R.id.pb_progressBar);
        rl_hide_zhihuishequ = (RelativeLayout) view11.findViewById(R.id.rl_hide_zhihuishequ);
        ptr_arrlist_zhihuishequ= ((PullToRefreshListView) view11.findViewById(R.id.ptr_arrlist_zhihuishequ));
        lv_zhihuishequ= ptr_arrlist_zhihuishequ.getRefreshableView();
        adapter11 = new MyAdapter();
        lv_zhihuishequ.setAdapter(adapter11);
        listView.add(view11);
        View view12 = inflate(getActivity(), R.layout.fragment_home_wurenji, null);
        pb_progressBar= (CircularProgress) view12.findViewById(R.id.pb_progressBar);
        rl_hide_wurenji = (RelativeLayout) view12.findViewById(R.id.rl_hide_wurenji);
        ptr_arrlist_wurenji= ((PullToRefreshListView) view12.findViewById(R.id.ptr_arrlist_wurenji));
        lv_wurenji= ptr_arrlist_wurenji.getRefreshableView();
        adapter12 = new MyAdapter();
        lv_wurenji.setAdapter(adapter12);
        listView.add(view12);
        View view13 = inflate(getActivity(), R.layout.fragment_home_zhinengyanglao, null);
        pb_progressBar= (CircularProgress) view13.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengyanglao = (RelativeLayout) view13.findViewById(R.id.rl_hide_zhinengyanglao);
        ptr_arrlist_zhinengyanglao= ((PullToRefreshListView) view13.findViewById(R.id.ptr_arrlist_zhinengyanglao));
        lv_zhinengyanglao= ptr_arrlist_zhinengyanglao.getRefreshableView();
        adapter13 = new MyAdapter();
        lv_zhinengyanglao.setAdapter(adapter13);
        listView.add(view13);
        View view14 = inflate(getActivity(), R.layout.fragment_home_zhinengjiankang, null);
        pb_progressBar= (CircularProgress) view14.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengjiankang = (RelativeLayout) view14.findViewById(R.id.rl_hide_zhinengjiankang);
        ptr_arrlist_zhinengjiankang= ((PullToRefreshListView) view14.findViewById(R.id.ptr_arrlist_zhinengjiankang));
        lv_zhinengjiankang= ptr_arrlist_zhinengjiankang.getRefreshableView();
        adapter14 = new MyAdapter();
        lv_zhinengjiankang.setAdapter(adapter14);
        listView.add(view14);
        View view15 = inflate(getActivity(), R.layout.fragment_home_zhinengyiliao, null);
        pb_progressBar= (CircularProgress) view15.findViewById(R.id.pb_progressBar);
        rl_hide_zhinengyiliao = (RelativeLayout) view15.findViewById(R.id.rl_hide_zhinengyiliao);
        ptr_arrlist_zhinengyiliao= ((PullToRefreshListView) view15.findViewById(R.id.ptr_arrlist_zhinengyiliao));
        lv_zhinengyiliao= ptr_arrlist_zhinengyiliao.getRefreshableView();
        adapter15 = new MyAdapter();
        lv_zhinengyiliao.setAdapter(adapter15);
        listView.add(view15);
        View view16 = inflate(getActivity(), R.layout.fragment_home_jiqiren, null);
        pb_progressBar= (CircularProgress) view16.findViewById(R.id.pb_progressBar);
        rl_hide_jiqiren = (RelativeLayout) view16.findViewById(R.id.rl_hide_jiqiren);
        ptr_arrlist_jiqiren= ((PullToRefreshListView) view16.findViewById(R.id.ptr_arrlist_jiqiren));
        lv_jiqiren= ptr_arrlist_jiqiren.getRefreshableView();
        adapter16 = new MyAdapter();
        lv_jiqiren.setAdapter(adapter16);
        listView.add(view16);
        View view17 = inflate(getActivity(), R.layout.fragment_home_vrar, null);
        pb_progressBar= (CircularProgress) view17.findViewById(R.id.pb_progressBar);
        rl_hide_vrar = (RelativeLayout) view17.findViewById(R.id.rl_hide_vrar);
        ptr_arrlist_vrar= ((PullToRefreshListView) view17.findViewById(R.id.ptr_arrlist_vrar));
        lv_vrar= ptr_arrlist_vrar.getRefreshableView();
        adapter17 = new MyAdapter();
        lv_vrar.setAdapter(adapter17);
        listView.add(view17);
        View view18 = inflate(getActivity(), R.layout.fragment_home_zhanhuixinxi, null);
        pb_progressBar= (CircularProgress) view18.findViewById(R.id.pb_progressBar);
        rl_hide_zhanhuixinxi = (RelativeLayout) view18.findViewById(R.id.rl_hide_zhanhuixinxi);
        ptr_arrlist_zhanhuixinxi= ((PullToRefreshListView) view18.findViewById(R.id.ptr_arrlist_zhanhuixinxi));
        lv_zhanhuixinxi= ptr_arrlist_wulianwang.getRefreshableView();
        adapter18 = new MyAdapter();
        lv_zhanhuixinxi.setAdapter(adapter18);
        listView.add(view18);
        View view19 = inflate(getActivity(), R.layout.fragment_home_peixunxinxi, null);
        pb_progressBar= (CircularProgress) view19.findViewById(R.id.pb_progressBar);
        rl_hide_peixunxinxi = (RelativeLayout) view19.findViewById(R.id.rl_hide_peixunxinxi);
        ptr_arrlist_peixunxinxi= ((PullToRefreshListView) view19.findViewById(R.id.ptr_arrlist_peixunxinxi));
        lv_peixunxinxi= ptr_arrlist_peixunxinxi.getRefreshableView();
        adapter19 = new MyAdapter();
        lv_peixunxinxi.setAdapter(adapter19);
        listView.add(view19);
        View view20 = inflate(getActivity(), R.layout.fragment_home_zhichaofangan, null);
        pb_progressBar= (CircularProgress) view20.findViewById(R.id.pb_progressBar);
        rl_hide_zhichaofangan = (RelativeLayout) view20.findViewById(R.id.rl_hide_zhichaofangan);
        ptr_arrlist_zhichaofangan= ((PullToRefreshListView) view20.findViewById(R.id.ptr_arrlist_zhichaofangan));
        lv_zhichaofangan= ptr_arrlist_zhichaofangan.getRefreshableView();
        adapter20 = new MyAdapter();
        lv_zhichaofangan.setAdapter(adapter20);
        listView.add(view20);
        View view21 = inflate(getActivity(), R.layout.fragment_home_zhichaoanli, null);
        pb_progressBar= (CircularProgress) view21.findViewById(R.id.pb_progressBar);
        rl_hide_zhichaoanli = (RelativeLayout) view21.findViewById(R.id.rl_hide_zhichaoanli);
        ptr_arrlist_zhichaoanli= ((PullToRefreshListView) view21.findViewById(R.id.ptr_arrlist_zhichaoanli));
        lv_zhichaoanli= ptr_arrlist_zhichaoanli.getRefreshableView();
        adapter21 = new MyAdapter();
        lv_zhichaoanli.setAdapter(adapter21);
        listView.add(view21);



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

                        pullFlag=false;
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

                        Log.i("aaaaa","数据拿到");
                        gettuijian();
                        break;
                    case 1001:
                        pullFlag=false;
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

                        Log.i("aaaaa","数据拿到");

                        gethangyezixun();
                        break;
                    case 1002:
                        pullFlag=false;
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

                        Log.i("aaaaa","数据拿到");

                        getcanpinzixun();
                        break;
                    case 1003:
                        pullFlag=false;
                        ptr_arrlist_canpinpince.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_canpinpince.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_canpinpince.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });


                        lv_canpinpince.setAdapter(adapter4);
                        getcanpinpince();
                        break;
                    case 1004:
                        pullFlag=false;
                        ptr_arrlist_video.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_video.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_video.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });

                        lv_video.setAdapter(adapter5);
                        getvideo();
                        break;
                    case 1005:
                        pullFlag=false;
                        ptr_arrlist_pinpaizixun.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_pinpaizixun.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_pinpaizixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });

                        lv_pinpaizixun.setAdapter(adapter6);
                        getpinpaizixun();
                        break;
                    case 1006:
                        pullFlag=false;
                        ptr_arrlist_hangyebiaozhun.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_hangyebiaozhun.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_hangyebiaozhun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });
                        lv_hangyebiaozhun.setAdapter(adapter7);
                        gethangyebiaozhun();
                        break;
                    case 1007:
                        pullFlag=false;
                        ptr_arrlist_hangyejishu.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_hangyejishu.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_hangyejishu.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });
                        lv_hangyejishu.setAdapter(adapter8);
                        gethangyejishu();
                        break;
                    case 1008:
                        pullFlag=false;
                        ptr_arrlist_wulianwang.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_wulianwang.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_wulianwang.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhihuichengshi.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhihuichengshi.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhihuichengshi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhihuishequ.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhihuishequ.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhihuishequ.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_wurenji.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_wurenji.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_wurenji.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhinengyanglao.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhinengyanglao.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhinengyanglao.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhinengjiankang.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhinengjiankang.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhinengjiankang.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhinengyiliao.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhinengyiliao.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhinengyiliao.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_jiqiren.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_jiqiren.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_jiqiren.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_vrar.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_vrar.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_vrar.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhanhuixinxi.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhanhuixinxi.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhanhuixinxi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_peixunxinxi.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_peixunxinxi.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_peixunxinxi.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                        pullFlag=false;
                        ptr_arrlist_zhichaofangan.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhichaofangan.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhichaofangan.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
                            }
                        });
                        lv_zhichaofangan.setAdapter(adapter20);
                        getzhichaofangan();
                        break;
                    case 1020:
                        pullFlag=false;
                        ptr_arrlist_zhichaoanli.setScrollingWhileRefreshingEnabled(true);
                        ptr_arrlist_zhichaoanli.setMode(PullToRefreshBase.Mode.BOTH);
                        ptr_arrlist_zhichaoanli.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                            @Override
                            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                                String label = android.text.format.DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
                                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
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

    private void getjiqiren() {
        if (page == 1 && pullFlag == false) {
            rl_hide_jiqiren.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "45");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_jiqiren.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter16.notifyDataSetChanged();
                    ptr_arrlist_jiqiren.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_jiqiren.onRefreshComplete();
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

    private void getzhinengyiliao() {
        if (page == 1 && pullFlag == false) {
            rl_hide_zhinengyiliao.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "44");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhinengyiliao.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter15.notifyDataSetChanged();
                    ptr_arrlist_zhinengyiliao.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhinengyiliao.onRefreshComplete();
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

    private void getvrar() {
        if (page == 1 && pullFlag == false) {
        rl_hide_vrar.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "48");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_vrar.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter17.notifyDataSetChanged();
                    ptr_arrlist_vrar.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_vrar.onRefreshComplete();
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

    private void getzhanhuixinxi() {
        if (page == 1 && pullFlag == false) {
        rl_hide_zhanhuixinxi.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "38");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhanhuixinxi.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter18.notifyDataSetChanged();
                    ptr_arrlist_zhanhuixinxi.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhanhuixinxi.onRefreshComplete();
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

    private void getpeixunxinxi() {
        if (page == 1 && pullFlag == false) {
        rl_hide_peixunxinxi.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "39");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_peixunxinxi.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter19.notifyDataSetChanged();
                    ptr_arrlist_peixunxinxi.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_peixunxinxi.onRefreshComplete();
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

    private void getzhichaofangan() {
        if (page == 1 && pullFlag == false) {
        rl_hide_zhichaofangan.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "16");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhichaofangan.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter20.notifyDataSetChanged();
                    ptr_arrlist_zhichaofangan.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhichaofangan.onRefreshComplete();
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

    private void getzhichaoanli() {
        if (page == 1 && pullFlag == false) {
        rl_hide_zhichaoanli.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "45");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhichaoanli.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter17.notifyDataSetChanged();
                    ptr_arrlist_zhichaoanli.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhichaoanli.onRefreshComplete();
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

    private void getzhinengjiankang() {if (page == 1 && pullFlag == false) {
        rl_hide_zhinengjiankang.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "43");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhinengjiankang.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter14.notifyDataSetChanged();
                    ptr_arrlist_zhinengjiankang.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhinengjiankang.onRefreshComplete();
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

    private void getwurenji() {
        if (page == 1 && pullFlag == false) {
        rl_hide_wurenji.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "46");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_wurenji.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter12.notifyDataSetChanged();
                    ptr_arrlist_wurenji.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_wurenji.onRefreshComplete();
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

    private void getzhinengyanglao() {
        if (page == 1 && pullFlag == false) {
        rl_hide_zhinengyanglao.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "42");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhinengyanglao.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter13.notifyDataSetChanged();
                    ptr_arrlist_zhinengyanglao.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhinengyanglao.onRefreshComplete();
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

    private void getzhihuishequ() {if (page == 1 && pullFlag == false) {
        rl_hide_zhihuishequ.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "41");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhihuishequ.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter11.notifyDataSetChanged();
                    ptr_arrlist_zhihuishequ.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhihuishequ.onRefreshComplete();
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

    private void getzhihuichengshi() {if (page == 1 && pullFlag == false) {
        rl_hide_zhihuichengshi.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "40");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_zhihuichengshi.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter10.notifyDataSetChanged();
                    ptr_arrlist_zhihuichengshi.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_zhihuichengshi.onRefreshComplete();
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

    private void getwulianwang() {if (page == 1 && pullFlag == false) {
        rl_hide_wulianwang.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "47");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_wulianwang.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter9.notifyDataSetChanged();
                    ptr_arrlist_wulianwang.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_wulianwang.onRefreshComplete();
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

    private void gethangyejishu() {
        if (page == 1 && pullFlag == false) {
        rl_hide_hangyejishu.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "36");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_hangyejishu.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter8.notifyDataSetChanged();
                    ptr_arrlist_hangyejishu.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_hangyejishu.onRefreshComplete();
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

    private void gethangyebiaozhun() {
        if (page == 1 && pullFlag == false) {
        rl_hide_hangyebiaozhun.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "32");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_hangyebiaozhun.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter7.notifyDataSetChanged();
                    ptr_arrlist_hangyebiaozhun.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_hangyebiaozhun.onRefreshComplete();
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

    private void getpinpaizixun() {if (page == 1 && pullFlag == false) {
        rl_hide_pinpaizixun.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "19");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_pinpaizixun.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter6.notifyDataSetChanged();
                    ptr_arrlist_pinpaizixun.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_pinpaizixun.onRefreshComplete();
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

    private void getvideo() {
        if (page == 1 && pullFlag == false) {
        rl_hide_video.setVisibility(View.VISIBLE);
    }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "15");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_video.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter5.notifyDataSetChanged();
                    ptr_arrlist_video.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_video.onRefreshComplete();
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
    private void getcanpinpince() {
        if (page == 1 && pullFlag == false) {
            rl_hide_canpinpince.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "14");
        params.addBodyParameter("page", page + "");
        org.xutils.x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rl_hide_canpinpince.setVisibility(View.GONE);
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
                if (page <= pageCount) {
                    arrList.addAll(articleData.getArticle_list());
                    adapter4.notifyDataSetChanged();
                    ptr_arrlist_canpinpince.onRefreshComplete();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    ptr_arrlist_canpinpince.onRefreshComplete();
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
    private void gettuijian() {
        Log.i("aaaaa","数据拿到");

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
        Log.i("aaaaa","数据拿到");

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

        Log.i("aaaaa","数据拿到");

        if (page == 1 && pullFlag == false) {
            rl_hide_canpinzixun.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("cat_id", "18");
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
