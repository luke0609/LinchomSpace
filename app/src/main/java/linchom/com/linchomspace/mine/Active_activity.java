package linchom.com.linchomspace.mine;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

import linchom.com.linchomspace.R;

public class Active_activity extends AppCompatActivity {

    private LayoutInflater inflate;

    private BannerComponent bannerComponent;

    private ViewPager banner_viewPager;
    private Indicator banner_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_activity);
        banner_viewPager = ((ViewPager) findViewById(R.id.banner_viewPager));
        banner_indicator = ((Indicator) findViewById(R.id.banner_indicator));

        bannerComponent=new BannerComponent(banner_indicator,banner_viewPager,false);
        inflate = LayoutInflater.from(getApplicationContext());
        bannerComponent.setAdapter(adapter);
        bannerComponent.setAutoPlayTime(2500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bannerComponent.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }

    private int[] images = {R.drawable.start_i1, R.drawable.start_i2, R.drawable.start_i3, R.drawable.start_i4};

    private IndicatorViewPager.IndicatorPagerAdapter adapter=new IndicatorViewPager.IndicatorViewPagerAdapter() {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_guide, container, false);
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            convertView.setBackgroundResource(images[position]);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    };
}
