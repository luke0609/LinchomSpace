package linchom.com.linchomspace.login;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.MainActivity;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.login.widget.DepthPageTransformer;

public class GuideActivity extends AppCompatActivity {
    ViewPager vp;
    int proviousPosition_vp=0;
    private Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide);


        final int[] points={R.id.iv_iv1,R.id.iv_iv2,R.id.iv_iv3,R.id.iv_iv4};
        btn_next= ((Button) findViewById(R.id.btn_next));

        List<Integer> imgs=new ArrayList<Integer>();
        imgs.add(0,R.drawable.start_i1);
        imgs.add(1,R.drawable.start_i2);
        imgs.add(2,R.drawable.start_i3);
        imgs.add(3,R.drawable.start_i4);
        vp = ((ViewPager) findViewById(R.id.vp_guide));
        vp.setPageTransformer(true, new DepthPageTransformer());

        MyPageAdapter pageAdapter=new MyPageAdapter(imgs);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((ImageView) findViewById(points[position])).setImageResource(R.drawable.point_black);
                ((ImageView) findViewById(points[proviousPosition_vp])).setImageResource(R.drawable.point_gray);
                proviousPosition_vp=position;
                if (position==points.length-1){
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    btn_next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setAdapter(pageAdapter);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private class MyPageAdapter extends PagerAdapter {
        List<Integer> imgs;
        private ImageView iv_vp_item;

        public MyPageAdapter(List<Integer> imgs){
            this.imgs=imgs;
        }
        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(getApplicationContext(),R.layout.vp_item,null);
            iv_vp_item = ((ImageView) view.findViewById(R.id.iv_vp_item));
            iv_vp_item.setImageResource(imgs.get(position));
            container.addView(view);
            return view;
        }
    }
    }

