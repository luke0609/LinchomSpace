package linchom.com.linchomspace.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ramotion.foldingcell.FoldingCell;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.service.pojo.ServiceBean;
import linchom.com.linchomspace.service.utils.IpUtil;
import linchom.com.linchomspace.service.utils.ResUtil;
import linchom.com.linchomspace.service.utils.WheelDialogFragment;

import static linchom.com.linchomspace.R.id.content_category;
import static linchom.com.linchomspace.R.id.title_category;


public class ServiceFragment extends Fragment implements View.OnClickListener {
    Map<String,String> region = new HashMap<String,String>();
    Map<String,String> map_category = new HashMap<String,String>();
    private boolean flag = false;

    View view_main;
    private ViewPager vp_service;
    private List<View> viewList = new ArrayList<View>();
    private LayoutInflater inflater;
    private RadioGroup rg_choice;
    private RadioButton request;
    private RadioButton service;
    private PullToRefreshListView plv_1;
    private PullToRefreshListView plv_2;
    private List<ServiceBean.DataBean.ItemsBean> requireList = new ArrayList<>();
    private List<ServiceBean.DataBean.ItemsBean> serviceList = new ArrayList<>();
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private TextView service_category;
    private ImageView address_select;
    private TextView tv_address;
    private String category_id="";
    private String region_id="";
    private boolean clicked = false;// 记录加号按钮的点击状态，默认为没有点击

    private RelativeLayout plus_rl;
    private ImageView plus_im;
    private TextView dishui_tv, guoshui_tv,publish_tv;

    private Animation rotate_anticlockwise, rotate_clockwise, scale_max,
            scale_min, alpha_button;
    private RelativeLayout rl_plus;

    private ListView requireListView;
    private ListView serviceListView;
    private int page =1;
    private int pageCount=1;
    private boolean refreshFlag =false;
    private boolean pageSelecter=true;
    private CommonAdapter<ServiceBean.DataBean.ItemsBean> serviceCommonAdapter;

    String ipAddress=null;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_main = inflater.inflate(R.layout.fragment_service, container, false);
        initData();
        initMap();
        initView();
        return view_main;
    }
    private  void initMap(){
        region.put("智能家居","1");
        region.put("电脑网络","2");
        region.put("电工照明","3");
        region.put("厨卫设施","4");
        region.put("空调家电","5");
        region.put("家具维修","6");
        region.put("水暖","7");
        region.put("门窗","8");
        region.put("地板","9");
        region.put("墙壁","10");
        region.put("吊顶","11");
        region.put("开孔","12");
        region.put("保洁","13");
        region.put("搬运","14");
        region.put("其他","15");


        map_category.put("北京","52");
        map_category.put("上海","321");
        map_category.put("广州","76");
        map_category.put("深圳","77");
        map_category.put("成都","322");
        map_category.put("重庆","394");
        map_category.put("杭州","383");
        map_category.put("天津","343");

    }
    private void initData() {
        // TODO Auto-generated method stub
        rotate_anticlockwise = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_anticlockwise);
        rotate_clockwise = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_clockwise);
        scale_max = AnimationUtils.loadAnimation(getContext(), R.anim.scale_max);
        scale_min = AnimationUtils.loadAnimation(getContext(), R.anim.scale_min);
        alpha_button = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_button);

        plus_rl = (RelativeLayout) view_main.findViewById(R.id.plus_rl);
        rl_plus = ((RelativeLayout) view_main.findViewById(R.id.rl_plus));
        plus_im = (ImageView) view_main.findViewById(R.id.plus_im);
        dishui_tv = (TextView) view_main.findViewById(R.id.dishui_tv);
        guoshui_tv = (TextView) view_main.findViewById(R.id.guoshui_tv);
        publish_tv = (TextView) view_main.findViewById(R.id.publish_tv);
        service_category = ((TextView) view_main.findViewById(R.id.service_category));
        request = ((RadioButton) view_main.findViewById(R.id.request));
        service = ((RadioButton) view_main.findViewById(R.id.serviec));
        rg_choice = ((RadioGroup) view_main.findViewById(R.id.rg_choice));
        vp_service = ((ViewPager) view_main.findViewById(R.id.vp_service));
        address_select = ((ImageView) view_main.findViewById(R.id.address_select));
        tv_address = ((TextView) view_main.findViewById(R.id.tv_address));

        address_select.setOnClickListener(this);
        service_category.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        rl_plus.setOnClickListener(this);
        dishui_tv.setOnClickListener(this);
        guoshui_tv.setOnClickListener(this);
        publish_tv.setOnClickListener(this);
    }

    private void initView() {



        inflater = LayoutInflater.from(getContext());

        View view1 = inflater.inflate(R.layout.service_require_layout, null);
        View view2 = inflater.inflate(R.layout.service_service_layout, null);
        viewList.add(view1);
        viewList.add(view2);
        plv_1 = ((PullToRefreshListView) view1.findViewById(R.id.lv_require));
        plv_2 = ((PullToRefreshListView) view2.findViewById(R.id.lv_service));

        vp_service.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));

            }
        });
        vp_service.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                unfoldedIndexes.clear();
                page =1;
                if (position == 0)
                { request.setChecked(true);
                    pageSelecter=true;
                    requirePullToRefresh();

                }
                else {
                    service.setChecked(true);
                    pageSelecter=false;
                    servicePullToRefresh();
                   }




            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.request) {
                    vp_service.setCurrentItem(0);
                } else {
                    vp_service.setCurrentItem(1);
                }
            }
        });

        requirePullToRefresh();

    }
    private void getRequireList(int type) {
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php");
        final int service_type=type;

        requestParams.addBodyParameter("act","demand_services");
        requestParams.addBodyParameter("service_type",service_type+"");
        requestParams.addBodyParameter("category_id",category_id);
        requestParams.addBodyParameter("region_id",region_id);

        requestParams.addBodyParameter("page",page+"");
        System.out.println(requestParams);
        x.http().post(requestParams, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                System.out.println(result);
                ServiceBean bean = gson.fromJson(result, ServiceBean.class);
                pageCount=   bean.getData().getTotal_pages();

                if (service_type==2){
                    if (page==1){
                        requireList.clear();
                    }

                    if(page<=pageCount){
                        requireList.addAll(bean.getData().getItems());
                    }else{

                        Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                    }

                    plv_1.onRefreshComplete();
                    System.out.println(requireList);
                    serviceCommonAdapter.notifyDataSetChanged();
                }else {
                    if (page==1){
                        serviceList.clear();
                    }

                    if(page<=pageCount){
                        serviceList.addAll(bean.getData().getItems());
                    }else{

                        Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                    }
                    plv_2.onRefreshComplete();

                    System.out.println(serviceList);
                    serviceCommonAdapter.notifyDataSetChanged();

                }



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



    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    private void requirePullToRefresh() {

        plv_1.setScrollingWhileRefreshingEnabled(true);
        plv_1.setMode(PullToRefreshBase.Mode.BOTH);
        plv_1.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                PullToRefreshBase.Mode mode = plv_1.getCurrentMode();
                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){
                    refreshFlag=true;
                    page =1;
                    getRequireList(2);

                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){
                    page++;
                    getRequireList(2);

                }



            }
        });

        plv_1.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        requireListView=plv_1.getRefreshableView();


        requireListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return flag;
            }
        });
        //new adapter


        serviceCommonAdapter =new CommonAdapter<ServiceBean.DataBean.ItemsBean>(getContext(),requireList,R.layout.cell){


            @Override
            public void convert(ViewHolder viewHolder, ServiceBean.DataBean.ItemsBean itemsBean, final int position) {

                final FoldingCell fc = viewHolder.getViewById(R.id.folding_cell);

                // attach click listener to folding cell
                fc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fc.toggle(false);


                        flag=true;

                        Handler x = new Handler();
                        x.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag=false;
                            }
                        }, 1000);

                        if (unfoldedIndexes.contains(position))
                            registerFold(position);
                        else
                            registerUnfold(position);
                    }
                });

                if (unfoldedIndexes.contains(position)) {
                    fc.unfold(true);
                } else {
                    fc.fold(true);
                }


                TextView service_city= viewHolder.getViewById(R.id.service_city);
                TextView title_title=  viewHolder.getViewById(R.id.title_title);
                TextView title_status=  viewHolder.getViewById(R.id.title_status);
                TextView  title_release=viewHolder.getViewById(R.id.title_release);
                TextView  title_date=viewHolder.getViewById(R.id.title_date);

                TextView content_content=viewHolder.getViewById(R.id.content_content);
                TextView content_mobile=viewHolder.getViewById(R.id.content_mobile);
                TextView content_name= viewHolder.getViewById(R.id.content_name);
                TextView content_add_time= viewHolder.getViewById(R.id.content_add_time);
                TextView content_address= viewHolder.getViewById(R.id.content_address);

                TextView content_service_type=viewHolder.getViewById(R.id.content_service_type);

                TextView title_category=viewHolder.getViewById(R.id.title_category);
                TextView content_category=viewHolder.getViewById(R.id.content_category);
                content_category.setText(itemsBean.getTopic_category_name());
                title_category.setText("【"+itemsBean.getTopic_category_name()+"】");
                content_name.setText(itemsBean.getName());

                service_city.setText(itemsBean.getCity_name());
                title_title.setText(itemsBean.getTitle());
                title_status.setText(("0".equals(itemsBean.getStatus()))? "进行中" :"已完成");
                title_release.setText(("0".equals(itemsBean.getRelease()))? "公司" :"个人");
                title_date.setText(itemsBean.getAdd_time());

                content_content.setText(itemsBean.getContent());
                content_mobile.setText(itemsBean.getMobile());
                content_add_time.setText(itemsBean.getAdd_time());
                content_address.setText(itemsBean.getAddress());
                content_service_type.setText(("0".equals(itemsBean.getService_type())?"需求":"服务"));

            }

        };

        //setadapter
        requireListView.setAdapter(serviceCommonAdapter);
        //getdata()更新数据
        getRequireList(2);

    }
    private void servicePullToRefresh() {

        plv_2.setScrollingWhileRefreshingEnabled(true);
        plv_2.setMode(PullToRefreshBase.Mode.BOTH);
        plv_2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                PullToRefreshBase.Mode mode = plv_2.getCurrentMode();
                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){
                    refreshFlag=true;
                    page =1;
                    getRequireList(1);

                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){
                    page++;
                    getRequireList(1);

                }



            }
        });

        plv_2.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        serviceListView=plv_2.getRefreshableView();
        serviceListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return flag;
            }
        });
        //new adapter


        serviceCommonAdapter =new CommonAdapter<ServiceBean.DataBean.ItemsBean>(getContext(),serviceList,R.layout.cell){


            @Override
            public void convert(ViewHolder viewHolder, ServiceBean.DataBean.ItemsBean itemsBean, final int position) {

                final FoldingCell fc = viewHolder.getViewById(R.id.folding_cell);

                // attach click listener to folding cell
                fc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fc.toggle(false);

                        flag=true;

                        Handler x = new Handler();
                        x.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag=false;
                            }
                        }, 1000);
                        if (unfoldedIndexes.contains(position))
                            registerFold(position);
                        else
                            registerUnfold(position);
                    }
                });

                if (unfoldedIndexes.contains(position)) {
                    fc.unfold(true);
                } else {
                    fc.fold(true);
                }


                TextView service_city= viewHolder.getViewById(R.id.service_city);
                TextView title_title=  viewHolder.getViewById(R.id.title_title);
                TextView title_status=  viewHolder.getViewById(R.id.title_status);
                TextView  title_release=viewHolder.getViewById(R.id.title_release);
                TextView  title_date=viewHolder.getViewById(R.id.title_date);

                TextView content_content=viewHolder.getViewById(R.id.content_content);
                TextView content_mobile=viewHolder.getViewById(R.id.content_mobile);
                TextView content_name= viewHolder.getViewById(R.id.content_name);
                TextView content_add_time= viewHolder.getViewById(R.id.content_add_time);
                TextView content_address= viewHolder.getViewById(R.id.content_address);

                TextView content_service_type=viewHolder.getViewById(R.id.content_service_type);

                TextView title_category=viewHolder.getViewById(R.id.title_category);
                TextView content_category=viewHolder.getViewById(R.id.content_category);
                content_category.setText(itemsBean.getTopic_category_name());
                title_category.setText("【"+itemsBean.getTopic_category_name()+"】");
                content_name.setText(itemsBean.getName());

                service_city.setText(itemsBean.getCity_name());
                title_title.setText(itemsBean.getTitle());
                title_status.setText(("0".equals(itemsBean.getStatus()))? "进行中" :"已完成");
                title_release.setText(("0".equals(itemsBean.getRelease()))? "公司" :"个人");
                title_date.setText(itemsBean.getAdd_time());


                content_content.setText(itemsBean.getContent());
                content_mobile.setText(itemsBean.getMobile());
                content_add_time.setText(itemsBean.getAdd_time());
                content_address.setText(itemsBean.getAddress());
                content_service_type.setText(("0".equals(itemsBean.getService_type())?"需求":"服务"));

            }

        };

        //setadapter
        serviceListView.setAdapter(serviceCommonAdapter);
        //getdata()更新数据
        getRequireList(1);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service_category:
                up_choice_service();
                break;
            case R.id.address_select:

                address_select.setVisibility(View.GONE);
                tv_address.setVisibility(View.VISIBLE);
                IpUtil getip=new IpUtil();

                break;
            case R.id.tv_address:
                up_choice_address();
                break;
            case R.id.rl_plus:
                show_round();
                break;
            case R.id.dishui_tv:
                v.startAnimation(alpha_button);
                rl_plus.performClick();
                Intent intent1 =new Intent(getActivity(),ServiceContentActivity.class);

                startActivity(intent1);


                break;
            case R.id.guoshui_tv:
                v.startAnimation(alpha_button);
                rl_plus.performClick();
                Intent intent2 =new Intent(getActivity(),LogActivity.class);
                startActivity(intent2);
                break;
            case R.id.publish_tv:
                v.startAnimation(alpha_button);
                rl_plus.performClick();
                Intent intent=new Intent(getActivity(),ServicePublishActivity.class);
                startActivity(intent);
                break;
            }


    }

    public void up_choice_service() {
        final WheelDialogFragment wheelViewDialogFragment = WheelDialogFragment
                .newInstance(ResUtil.getStringArray(R.array.main_service_menu),
                        ResUtil.getString(R.string.app_cancel),
                        ResUtil.getString(R.string.app_sure), true, false, false);
        wheelViewDialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
            @Override
            public void onClickLeft(String value) {
                wheelViewDialogFragment.dismiss();
            }

            @Override
            public void onClickRight(String value) {
                wheelViewDialogFragment.dismiss();
                page=1;
                service_category.setText(value);
                category_id=region.get(value);
                unfoldedIndexes.clear();
                System.out.println("category_id="+category_id);
                Log.i("flag",(pageSelecter?"需求":"服务"));
                if(pageSelecter)
                {
                    requirePullToRefresh();
                }else {
                    servicePullToRefresh();
                }
            }

            @Override
            public void onValueChanged(String value) {

            }
        });
        wheelViewDialogFragment.show(getFragmentManager(), "");
    }
    public void up_choice_address() {
        final WheelDialogFragment wheelViewDialogFragment = WheelDialogFragment
                .newInstance(ResUtil.getStringArray(R.array.main_address_menu),
                        ResUtil.getString(R.string.app_cancel),
                        ResUtil.getString(R.string.app_sure), true, false, false);
        wheelViewDialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
            @Override
            public void onClickLeft(String value) {
                wheelViewDialogFragment.dismiss();
            }

            @Override
            public void onClickRight(String value) {
                wheelViewDialogFragment.dismiss();
                page=1;
                unfoldedIndexes.clear();
                tv_address.setText(value);
                region_id=map_category.get(value);
                System.out.println(region_id);
                if(pageSelecter)
                {
                    requirePullToRefresh();
                }else {
                    servicePullToRefresh();
                }
            }

            @Override
            public void onValueChanged(String value) {

            }
        });
        wheelViewDialogFragment.show(getFragmentManager(), "");
    }
    public void show_round(){
        // TODO Auto-generated method stub
        clicked = !clicked;
        // 两个按钮的显示隐藏
        dishui_tv.setVisibility(clicked ? View.VISIBLE : View.GONE);
        guoshui_tv.setVisibility(clicked ? View.VISIBLE : View.GONE);
        publish_tv.setVisibility(clicked ? View.VISIBLE : View.GONE);
        // 加号旋转
        plus_im.startAnimation(clicked ? rotate_anticlockwise
                : rotate_clockwise);
        // 按钮显示隐藏效果
        dishui_tv.startAnimation(clicked ? scale_max : scale_min);
        guoshui_tv.startAnimation(clicked ? scale_max : scale_min);
        publish_tv.startAnimation(clicked ? scale_max : scale_min);
        // 背景色的改变
        plus_rl.setBackgroundColor(clicked ? Color
                .parseColor("#99ffffff") : Color.TRANSPARENT);
        // 背景是否可点击，用于控制Framelayout层下面的视图是否可点击
        plus_rl.setClickable(clicked);
        dishui_tv.setClickable(clicked ?true :false);
        guoshui_tv.setClickable(clicked ?true :false);
        publish_tv.setClickable(clicked ?true :false);
    }






}