package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.goodsfragment.AllOrderFragment;

public class GoodsAllOrderActivity extends AppCompatActivity {

    List<Fragment> fragmentList =new ArrayList<Fragment>();

    private ViewPager vp_goods_orderfrom_pager;

    private String orderStatus;

    private String shippingStatus;

    private String payStatus;
    private RadioGroup rg_goods_orderfrom;
    private RadioButton rb_goods_orderform_one;
    private RadioButton rb_goods_orderform_two;
    private RadioButton rb_goods_orderform_three;
    private RadioButton rb_goods_orderform_four;
    private RadioButton rb_goods_orderform_five;
    private RadioButton rb_goods_orderform_six;
    private RadioButton rb_goods_orderform_seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_all_order);


        initView();

        initData();

        initEvent();

    }

    private void initView() {

        vp_goods_orderfrom_pager = ((ViewPager) findViewById(R.id.vp_goods_orderfrom_pager));
        rg_goods_orderfrom = ((RadioGroup) findViewById(R.id.rg_goods_orderfrom));
        rb_goods_orderform_one = ((RadioButton) findViewById(R.id.rb_goods_orderform_one));
        rb_goods_orderform_one.setChecked(true);

        rb_goods_orderform_two = ((RadioButton) findViewById(R.id.rb_goods_orderform_two));
        rb_goods_orderform_three = ((RadioButton) findViewById(R.id.rb_goods_orderform_three));
        rb_goods_orderform_four = ((RadioButton) findViewById(R.id.rb_goods_orderform_four));
        rb_goods_orderform_five = ((RadioButton) findViewById(R.id.rb_goods_orderform_five));
        rb_goods_orderform_six = ((RadioButton) findViewById(R.id.rb_goods_orderform_six));
        rb_goods_orderform_seven = ((RadioButton) findViewById(R.id.rb_goods_orderform_seven));


    }

    private void initData() {
        //new AllOrderFragment()
        //new UnPayFagment()
        //new UnReceivedFragment()
        //new UnEvaluateFragment()
        //new ReturnGoodsFragment()
        //
        //

        //orderStatusInfo ;
        //shippingStatusInfo;
        //payStatusInfo ;

        AllOrderFragment allOrderFragment =new AllOrderFragment();
        Bundle bundle =new Bundle();
        orderStatus="2";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        allOrderFragment.setArguments(bundle);


        AllOrderFragment unPayFragment =new AllOrderFragment();
        Bundle bundle1 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        unPayFragment.setArguments(bundle1);


        AllOrderFragment alreadyPayFragment =new AllOrderFragment();
        Bundle bundle2 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        alreadyPayFragment.setArguments(bundle2);

        AllOrderFragment unSendFragment =new AllOrderFragment();
        Bundle bundle3 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        unSendFragment.setArguments(bundle3);

        AllOrderFragment unReceivedFragment =new AllOrderFragment();
        Bundle bundle4 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        unReceivedFragment.setArguments(bundle4);

        AllOrderFragment unEvaluateFragment =new AllOrderFragment();
        Bundle bundle5 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        unEvaluateFragment.setArguments(bundle5);

        AllOrderFragment returnGoodsFragment =new AllOrderFragment();
        Bundle bundle6 =new Bundle();
        orderStatus="0";
        shippingStatus="0";
        payStatus="0";
        bundle.putString("orderStatus",orderStatus);
        bundle.putString("shippingStatus",shippingStatus);
        bundle.putString("payStatusInfo",payStatus);
        returnGoodsFragment.setArguments(bundle6);

        fragmentList.add(allOrderFragment);
        fragmentList.add(unPayFragment);
        fragmentList.add(alreadyPayFragment);
        fragmentList.add(unSendFragment);
        fragmentList.add(unReceivedFragment);
        fragmentList.add(unEvaluateFragment);
        fragmentList.add(returnGoodsFragment);




    }

    private void initEvent() {

        vp_goods_orderfrom_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vp_goods_orderfrom_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



            }

            @Override
            public void onPageSelected(int position) {



                switch (position){
                    case 0:
                        rb_goods_orderform_one.setChecked(true);

                        break;
                    case 1:
                        rb_goods_orderform_two.setChecked(true);


                        break;
                    case 2:
                        rb_goods_orderform_three.setChecked(true);


                        break;
                    case 3:
                        rb_goods_orderform_four.setChecked(true);

                        break;
                    case 4:
                        rb_goods_orderform_five.setChecked(true);

                        break;
                    case 5:
                        rb_goods_orderform_six.setChecked(true);

                        break;
                    case 6:
                        rb_goods_orderform_seven.setChecked(true);

                        break;


                }







            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        rg_goods_orderfrom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_goods_orderform_one:
                        vp_goods_orderfrom_pager.setCurrentItem(0);
                        break;
                    case R.id.rb_goods_orderform_two:
                        vp_goods_orderfrom_pager.setCurrentItem(1);

                        break;
                    case R.id.rb_goods_orderform_three:
                       vp_goods_orderfrom_pager.setCurrentItem(2);

                        break;
                    case R.id.rb_goods_orderform_four:
                        vp_goods_orderfrom_pager.setCurrentItem(3);

                        break;
                    case R.id.rb_goods_orderform_five:
                        vp_goods_orderfrom_pager.setCurrentItem(4);

                        break;
                    case R.id.rb_goods_orderform_six:
                        vp_goods_orderfrom_pager.setCurrentItem(5);

                        break;
                    case R.id.rb_goods_orderform_seven:
                        vp_goods_orderfrom_pager.setCurrentItem(6);

                        break;



                }

            }
        });

    }
}
