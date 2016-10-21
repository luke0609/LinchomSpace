package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
        orderStatus="0";
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

    }
}
