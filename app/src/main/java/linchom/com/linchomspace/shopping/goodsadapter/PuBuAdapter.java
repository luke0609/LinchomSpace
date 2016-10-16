package linchom.com.linchomspace.shopping.goodsadapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.List;
import java.util.Random;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.pojo.GoodsListBean;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;


public class PuBuAdapter extends BaseAdapter {


    private  LayoutInflater mLayoutInflater;


    private static final String TAG = "PuBuAdapter";
    Context context;

    List<GoodsListBean.Goods> lists;


    public PuBuAdapter(Context context, List<GoodsListBean.Goods> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {

            convertView= View.inflate(context, R.layout.goodslist_item_pubu,null);
            vh = new ViewHolder();
            vh.iv_goodslist_pubu_img = (DynamicHeightImageView) convertView
                    .findViewById(R.id.iv_goodslist_pubu_img);
            vh.tv_goodslist_pubu_name = (TextView) convertView.findViewById(R.id.tv_goodslist_pubu_name);

            vh.tv_goodslist_pubu_price = (TextView) convertView.findViewById(R.id.tv_goodslist_pubu_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);
        if (position % 2 == 0) {
            vh.iv_goodslist_pubu_img.setHeightRatio(1);
        } else {
            vh.iv_goodslist_pubu_img.setHeightRatio(1.5);
        }
        vh.tv_goodslist_pubu_name.setText(lists.get(position).goods_name);

        vh.tv_goodslist_pubu_price.setText(lists.get(position).shop_price);

        GoodsXUtilsImage.display(vh.iv_goodslist_pubu_img,"http://linchom.com//"+lists.get(position).goods_thumb);


        return convertView;


    }


    static class ViewHolder {
        DynamicHeightImageView iv_goodslist_pubu_img;
        TextView tv_goodslist_pubu_name;

        TextView tv_goodslist_pubu_price;
    }

//根据位置随机高度


    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private final Random mRandom = new Random();

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.i(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }



}
