package linchom.com.linchomspace.shopping.goodsadapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.x;

import java.util.List;

import linchom.com.linchomspace.R;

/**
 * Created by Administrator on 2016/10/12.
 */
public class MyGoodsIndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    Context context ;
    List<String> advList;

    public MyGoodsIndicatorAdapter(Context context, List<String> advList){
        this.context =context;
        this.advList =advList;

    }

    @Override
    public int getCount() {

        return advList.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {

            if(convertView==null){

                convertView= View.inflate(context, R.layout.goods_tab_guide,null);

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



        x.image().bind(imageView, advList.get(position%advList.size()));




        return convertView;


    }
}
