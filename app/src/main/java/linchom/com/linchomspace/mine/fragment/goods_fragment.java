package linchom.com.linchomspace.mine.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import linchom.com.linchomspace.mine.pojo.LovedInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.R;

/**
 * Created by lenovo on 2016/10/20.
 */
public class goods_fragment extends Fragment {

    private PullToRefreshListView lv_goods;
    private int page=1;
    private int pageCount=1;
    private ProgressBar firstBar;
    private static int mDelId = 0;
    List<LovedInfoBean.DataBean.Items> loveList=new ArrayList<LovedInfoBean.DataBean.Items>();
    private GoodsCommonAdapter<LovedInfoBean.DataBean.Items> loveCommonAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_goodsfragment, null);
        //拿到控件
        lv_goods = ((PullToRefreshListView) view.findViewById(R.id.lv_goods));
        firstBar = ((ProgressBar) view.findViewById(R.id.firstBar));
        eventPullToRefresh();
        lv_goods.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mDelId=position-1;
                showDialog();
                return true;
            }
        });
        //initData();
        return  view;


    }

    private void eventPullToRefresh() {
        lv_goods.setScrollingWhileRefreshingEnabled(true);
        lv_goods.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv_goods.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                initData();
                //
                lv_goods.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv_goods.onRefreshComplete();
                    }
                }, 1000);

            }
        });
        initEvent();
        //initData();
    }

    public void initEvent() {
//        System.out.println("999999999999999999");
        loveCommonAdapter=new GoodsCommonAdapter<LovedInfoBean.DataBean.Items>(getActivity(),loveList,R.layout.my_loved_activity) {
            @Override
            public void convert(GoodsViewHolder viewHolder, LovedInfoBean.DataBean.Items items, int position) {
                ImageView goods_img=viewHolder.getViewById(R.id.goods_img);
                x.image().bind(goods_img,items.goods_img);
                TextView  goods_name=viewHolder.getViewById(R.id.goods_name);
                goods_name.setText(items.goods_name);
                TextView shop_price=viewHolder.getViewById(R.id.shop_price);
                shop_price.setText(items.shop_price);
                TextView add_time=viewHolder.getViewById(R.id.add_time);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                Date date=new Date((Long.parseLong(items.add_time)));
                add_time.setText(simpleDateFormat.format(date));
            }
        };

        lv_goods.setAdapter(loveCommonAdapter);
        initData();
    }

    private void initData() {
        if (page==1) {
            firstBar.setVisibility(View.VISIBLE);
        }
        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php?act=collect_goods&user_id=12");
        requestParams.addBodyParameter("verification","e0d017ef76c8510244ebe0191f5dde15" );
        requestParams.addBodyParameter("page",page+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (page <= pageCount) {
                    Gson gson=new Gson();
                    LovedInfoBean bean=gson.fromJson(result,LovedInfoBean.class);
                    LovedInfoBean.DataBean mbean=bean.data;
                    pageCount = Integer.parseInt(mbean.total_pages.toString());
//                System.out.println("00000000000000000"+mbean);
                    loveList.addAll(mbean.items);
                    loveCommonAdapter.notifyDataSetChanged();
                    //initEvent();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    loveCommonAdapter.notifyDataSetChanged();
                }
                firstBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println(ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=drop_collect_article");
                requestParams.addBodyParameter("user_id","12");
                requestParams.addBodyParameter("type","2");
                requestParams.addBodyParameter("id",loveList.get(mDelId).goods_id);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                deleteItem();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
    private void deleteItem() {
        int size = loveList.size();
        if (size > 0) {
            loveList.remove(mDelId);
            loveCommonAdapter.notifyDataSetChanged();
        }
    }
}
