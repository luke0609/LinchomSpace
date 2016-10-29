package linchom.com.linchomspace.shopping.goodsadapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.pojo.GoodsAdvBean;

/**
 * Created by Administrator on 2016/10/12.
 */
public class MyGoodsIndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    Context context ;
    List<GoodsAdvBean> advList;

    public MyGoodsIndicatorAdapter(Context context, List<GoodsAdvBean> advList){
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

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //写工具类????

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.drawable.goods_background)//加载中默认显示图片
                .setFailureDrawableId(R.drawable.g1)//加载失败后默认显示图片
                .build();

        x.image().bind(imageView, advList.get(position%advList.size()).advImg,imageOptions);



        return convertView;


    }
}
