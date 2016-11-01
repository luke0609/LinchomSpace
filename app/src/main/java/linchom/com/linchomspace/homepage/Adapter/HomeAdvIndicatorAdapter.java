package linchom.com.linchomspace.homepage.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.image.ImageOptions;

import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Entity.HomePageAdvBean;

/**
 * Created by Administrator on 2016/10/26.
 */

public class HomeAdvIndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter{
    Context context ;
    List<HomePageAdvBean> advList;

    public HomeAdvIndicatorAdapter(Context context, List<HomePageAdvBean> advList) {
        this.context = context;
        this.advList = advList;
    }

    @Override
    public int getCount() {
        return  advList.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container)
    {
        if(convertView==null){

            convertView= View.inflate(context, R.layout.tab_guide,null);

        }

        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        if(convertView==null){
            convertView=new ImageView(context);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ImageView imageView =(ImageView)convertView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //写工具类????
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.adv_default)//加载中默认显示图片
                .setFailureDrawableId(R.drawable.goods_background)//加载失败后默认显示图片
                .build();

        //x.image().bind(imageView, advList.get(position%advList.size()).,imageOptions);
        Log.i("advlist",advList+"");
        return convertView;
    }
}
