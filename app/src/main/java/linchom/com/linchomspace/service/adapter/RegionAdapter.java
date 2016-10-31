package linchom.com.linchomspace.service.adapter;

import android.content.Context;
import android.graphics.Region;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.service.pojo.RregionBean;

/**
 * Created by Jingsheng Liang on 2016/10/31.
 */

public class RegionAdapter extends BaseAdapter {
    private List<RregionBean.DataBean> mList;
    private Context mContext;

    public RegionAdapter(Context pContext, List<RregionBean.DataBean> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.spinner_item, null);
        if(convertView!=null)
        {
            TextView _TextView2=(TextView)convertView.findViewById(R.id.textView2);
            _TextView2.setText(mList.get(position).getRegion_name());
        }
        return convertView;
    }


}
