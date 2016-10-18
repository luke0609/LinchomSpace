package linchom.com.linchomspace.chat.pojo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import linchom.com.linchomspace.R;

/**
 * Created by Jingsheng Liang on 2016/9/30.
 */
public class ListViewAdapter extends BaseAdapter{
//    ArrayList<ListActivityBean.Dongtai> dongtaiList;
//    public void ListViewAdapter(ArrayList<ListActivityBean.Dongtai> dongtaiList){
//        this.dongtaiList=dongtaiList;
//
//    }
Context context;
    private ArrayList<ListActivityBean.Dongtai> dongtaiList;

    public ArrayList<ListActivityBean.Dongtai> getDongtaiList() {
        return dongtaiList;
    }

    public ListViewAdapter(Context context,ArrayList<ListActivityBean.Dongtai> dongtaiList) {
        this.context=context;
        this.dongtaiList=dongtaiList;
    }

    @Override
    public int getCount() {
        return dongtaiList.size();
    }

    @Override
    public Object getItem(int position) {
        return dongtaiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      //  Log.i(TAG, "加载listview item position:" + position);
        View view=View.inflate(context, R.layout.activity_list_lv_dongtai_item,null);
        TextView tv_title= ((TextView) view.findViewById(R.id.tv_title));
        TextView tv_name= ((TextView) view.findViewById(R.id.tv_name));
        TextView tv_zanshu=((TextView) view.findViewById(R.id.tv_zanshu));
        ListActivityBean.Dongtai dongtai=dongtaiList.get(position);

        try {
            tv_title.setText(URLDecoder.decode(dongtai.title,"utf-8"));
            tv_name.setText(URLDecoder.decode(dongtai.name,"utf-8"));
            tv_zanshu.setText(dongtai.dianzan+"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return view;
    }
}
