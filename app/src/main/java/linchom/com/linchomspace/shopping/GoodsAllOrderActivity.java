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
import linchom.com.linchomspace.shopping.goodsfragment.ReturnGoodsFragment;
import linchom.com.linchomspace.shopping.goodsfragment.UnEvaluateFragment;
import linchom.com.linchomspace.shopping.goodsfragment.UnPayFagment;
import linchom.com.linchomspace.shopping.goodsfragment.UnReceivedFragment;
import linchom.com.linchomspace.shopping.goodsfragment.UnSendFragment;

public class GoodsAllOrderActivity extends AppCompatActivity {

    List<Fragment> fragmentList =new ArrayList<Fragment>();

    private ViewPager vp_goods_orderfrom_pager;

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
        fragmentList.add(new AllOrderFragment());
        fragmentList.add(new UnPayFagment());
        fragmentList.add(new UnSendFragment());
        fragmentList.add(new UnReceivedFragment());
        fragmentList.add(new UnEvaluateFragment());
        fragmentList.add(new ReturnGoodsFragment());



    }

    private void initEvent() {

        vp_goods_orderfrom_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });

    }
}
