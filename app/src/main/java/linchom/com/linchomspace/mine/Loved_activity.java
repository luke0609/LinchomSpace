package linchom.com.linchomspace.mine;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.fragment.goods_fragment;
import linchom.com.linchomspace.mine.fragment.news_fragment;
import linchom.com.linchomspace.mine.fragment.vidio_fragment;

public class Loved_activity extends AppCompatActivity {

    private RadioGroup rag;
    private ImageView iv_love_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved_activity);

        iv_love_back = ((ImageView) findViewById(R.id.iv_love_back));

        //默认选中的页面
        switchFragment(new news_fragment());

        initView();
        initdata();
    }
    private void initView() {
        rag = ((RadioGroup) findViewById(R.id.rag));
        //   lv_drawer = ((ShouCang) findViewById(R.id.lv_drawer));
    }

    private void initdata() {
        iv_love_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {

                    case R.id.rb_zixun:
                        // Log.i(TAG, "选中首页tab");
                        fragment = new news_fragment();
                        break;
                    case R.id.rb_vidio:
                        //  Log.i(TAG, "选中首页tab2");
                        fragment = new vidio_fragment();
                        break;

                    case  R.id.rv_goods:
                        fragment=new goods_fragment();
                        break;

                }
                switchFragment(fragment);
            }

        });

    }
    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.rb_rag, fragment).commit();

    }


}