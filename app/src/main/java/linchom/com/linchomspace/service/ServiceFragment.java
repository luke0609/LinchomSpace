package linchom.com.linchomspace.service;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ramotion.foldingcell.FoldingCell;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.service.utils.ResUtil;
import linchom.com.linchomspace.service.utils.WheelDialogFragment;



public class ServiceFragment extends Fragment implements View.OnClickListener {

    View view_main;
    private ViewPager vp_service;
    private List<View> viewList = new ArrayList<View>();
    private LayoutInflater inflater;
    private RadioGroup rg_choice;
    private RadioButton request;
    private RadioButton service;
    private PullToRefreshListView plv_1;
    private List<String> list = new ArrayList<>();
    CommonAdapter<String> adapter;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private TextView service_category;
    private ImageView address_select;
    private TextView tv_address;

    private boolean clicked = false;// 记录加号按钮的点击状态，默认为没有点击

    private RelativeLayout plus_rl;
    private ImageView plus_im;
    private TextView dishui_tv, guoshui_tv,rizhi_tv;

    private Animation rotate_anticlockwise, rotate_clockwise, scale_max,
            scale_min, alpha_button;
    private RelativeLayout rl_plus;
    private PullToRefreshListView plv_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_main = inflater.inflate(R.layout.fragment_service, container, false);
        initData();
        initView();
        return view_main;
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
    }

    private void initView() {
        plus_rl = (RelativeLayout) view_main.findViewById(R.id.plus_rl);
        rl_plus = ((RelativeLayout) view_main.findViewById(R.id.rl_plus));
        plus_im = (ImageView) view_main.findViewById(R.id.plus_im);
        dishui_tv = (TextView) view_main.findViewById(R.id.dishui_tv);
        guoshui_tv = (TextView)view_main.findViewById(R.id.guoshui_tv);
        rizhi_tv=(TextView)view_main.findViewById(R.id.rizhi_tv);

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
        rizhi_tv.setOnClickListener(this);
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
                if (position == 0) request.setChecked(true);
                else service.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.request){
                    vp_service.setCurrentItem(0);
                }else {
                    vp_service.setCurrentItem(1);
                }
            }
        });

        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        if (adapter == null) {
            adapter = new CommonAdapter<String>(getContext(), list, R.layout.cell) {
                @Override
                public void convert(ViewHolder viewHolder, String s, final int position) {


                    final FoldingCell fc = viewHolder.getViewById(R.id.folding_cell);

                    // attach click listener to folding cell
                    fc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fc.toggle(false);
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

                }
            };
            plv_1.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
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
                break;
            case R.id.guoshui_tv:
                v.startAnimation(alpha_button);
                rl_plus.performClick();
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
                service_category.setText(value);

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
                tv_address.setText(value);

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
        rizhi_tv.setVisibility(clicked ? View.VISIBLE : View.GONE);
        // 加号旋转
        plus_im.startAnimation(clicked ? rotate_anticlockwise
                : rotate_clockwise);
        // 按钮显示隐藏效果
        dishui_tv.startAnimation(clicked ? scale_max : scale_min);
        guoshui_tv.startAnimation(clicked ? scale_max : scale_min);
        rizhi_tv.startAnimation(clicked ? scale_max : scale_min);
        // 背景色的改变
        plus_rl.setBackgroundColor(clicked ? Color
                .parseColor("#99ffffff") : Color.TRANSPARENT);
        // 背景是否可点击，用于控制Framelayout层下面的视图是否可点击
        plus_rl.setClickable(clicked);
        dishui_tv.setClickable(clicked ?true :false);
        guoshui_tv.setClickable(clicked ?true :false);
        rizhi_tv.setClickable(clicked ?true :false);
    }



    public void publish_service(){


    }
    public void mine_service(){}
}