package linchom.com.linchomspace.shopping.goodsadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

/**
 * Created by Administrator on 2016/10/9.
 */
public  abstract class GoodsCommonAdapter<T> extends BaseAdapter{
    Context context;

    List<T> lists;
    int layoutId;
    public GoodsCommonAdapter(Context context, List<T> lists, int layoutId){


            this.context =context ;


            this.lists  =lists;
        this.layoutId=layoutId;
    }


    @Override
    public int getCount() {
        return (lists!=null)?lists.size():0;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);//获取每一个Item的数据源
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GoodsViewHolder viewHolder = GoodsViewHolder.get(context,layoutId,convertView,parent);


        convert(viewHolder,lists.get(position),position);

        return viewHolder.getConvertView();
    }

    //取出控价 然后赋值

    public abstract void convert(GoodsViewHolder viewHolder,T t,int position);
}
