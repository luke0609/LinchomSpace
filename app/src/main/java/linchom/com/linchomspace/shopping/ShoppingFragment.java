package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.goodsadapter.MyGoodsIndicatorAdapter;


public class ShoppingFragment extends Fragment {

    View view;


    private ViewPager vp_banner_goods;
    private Indicator indicator_banner_goods;

    private BannerComponent bannerComponent;

    private MyGoodsIndicatorAdapter myGoodsIndicatorAdapter;

    private List<String> advList =new ArrayList<String>();
    private Button btn_goods_one_one;
    private Button btn_goods_one_two;
    private Button btn_goods_one_three;
    private Button btn_goods_one_four;
    private Button btn_goods_one_five;
    private Button btn_goods_one_six;
    private Button btn_goods_one_seven;
    private Button btn_goods_one_eight;
    private Button btn_goods_one_nine;

    private Button btn_goods_two_one;
    private Button btn_goods_two_two;
    private Button btn_goods_two_three;
    private Button btn_goods_two_four;
    private Button btn_goods_two_five;
    private Button btn_goods_two_six;
    private Button btn_goods_two_seven;
    private Button btn_goods_two_eight;
    private Button btn_goods_two_nine;
    private Button btn_goods_two_ten;
    private Button btn_goods_two_eleven;
    private Button btn_goods_two_twelve;
    private Button btn_goods_two_thirteen;
    private Button btn_goods_two_fourteen;
    private Button btn_goods_two_fifteen;


    private Button btn_goods_three_one;
    private Button btn_goods_three_two;
    private Button btn_goods_three_three;
    private Button btn_goods_three_four;
    private Button btn_goods_three_five;
    private Button btn_goods_three_six;

    private Button btn_goods_four_one;
    private Button btn_goods_four_two;
    private Button btn_goods_four_three;
    private Button btn_goods_four_four;
    private Button btn_goods_four_five;
    private Button btn_goods_four_six;
    private Button btn_goods_four_seven;
    private Button btn_goods_four_eight;

    private Button btn_goods_five_one;
    private Button btn_goods_five_two;
    private Button btn_goods_five_three;
    private Button btn_goods_five_four;
    private Button btn_goods_five_five;
    private Button btn_goods_five_six;
    private Button btn_goods_five_seven;
    private Button btn_goods_five_eight;


    private Button btn_goods_six_one;
    private Button btn_goods_six_two;
    private Button btn_goods_six_three;
    private Button btn_goods_six_four;
    private Button btn_goods_six_five;
    private Button btn_goods_six_six;
    private Button btn_goods_six_seven;
    private Button btn_goods_six_eight;
    private Button btn_goods_six_nine;
    private Button btn_goods_six_ten;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_shopping, container, false);
        initView();
        initData();
        initEvent();

        return  view;
    }

    private void initView() {

    }

    private void initData() {

    }

    private void initEvent() {

    }

}
