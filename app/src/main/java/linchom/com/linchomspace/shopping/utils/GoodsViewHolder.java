package linchom.com.linchomspace.shopping.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GoodsViewHolder {


    View convertView;

    SparseArray<View>  sparseArray;//key默认int 类型 value View;

//返回viewHolder关联的conertView
    public View getConvertView(){

            return convertView;

    }

    public GoodsViewHolder(Context context, int layoutId, ViewGroup parent){



            this.convertView = LayoutInflater.from(context).inflate(layoutId,null);

            convertView.setTag(this);

            sparseArray = new SparseArray<View>();

    }

    //获取viewHolder对象

    public static  GoodsViewHolder  get(Context context, int layoutId, View convertView , ViewGroup parent){

        GoodsViewHolder goodsViewHolder;
        if(convertView==null) {

            goodsViewHolder=  new GoodsViewHolder(context,layoutId,parent);//创建对象

        }else{


            goodsViewHolder= (GoodsViewHolder) convertView.getTag();
        }

        return  goodsViewHolder;


    }

    //根据id查找View

    public <T extends View> T  getViewById(int resourseId){

        View view = sparseArray.get(resourseId);

        //没有找到view

        if(view  == null){


            view= convertView.findViewById(resourseId);

            sparseArray.put(resourseId,view);
        }

        return (T)view;



    }


}
