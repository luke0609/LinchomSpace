package linchom.com.linchomspace.shopping.goodsadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

/**
 * Created by Administrator on 2016/10/19.
 */

public class GoodsPagerAdapter extends PagerAdapter{

    Context context;

    List<String> imgList =new ArrayList<String>();


    public GoodsPagerAdapter(Context context, List<String> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       // super.destroyItem(container, position, object);

        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        View view =View.inflate(context, R.layout.goods_img_layout,null);


        ImageView iv_goods_show = ((ImageView) view.findViewById(R.id.iv_goods_show));

        GoodsXUtilsImage.display(iv_goods_show,imgList.get(position));

        container.addView(view);


        return view;


    }


}
