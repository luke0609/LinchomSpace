package linchom.com.linchomspace.mine.fragment;


import android.content.DialogInterface;
import android.content.Intent;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Activity.ArticleActivity;
import linchom.com.linchomspace.homepage.Utils.xUtilsImageUtils;
import linchom.com.linchomspace.mine.pojo.AlertsInfoBean;
import linchom.com.linchomspace.mine.pojo.ArticleInFoBean;
import linchom.com.linchomspace.shopping.GoodsActivity;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

import static linchom.com.linchomspace.R.id.iv_photo;

/**
 * Created by lenovo on 2016/10/20.
 */
public class news_fragment extends Fragment {

    List<ArticleInFoBean.DataBean.AItems> aItemsList=new ArrayList<ArticleInFoBean.DataBean.AItems>();
    private GoodsCommonAdapter<ArticleInFoBean.DataBean.AItems> aItemsGoodsCommonAdapter;
    private PullToRefreshListView lv_news;
    private int page=1;
    private int pageCount=1;
    private ProgressBar firstBar;
    private static int mDelId = 0;
    private ImageView iv_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_newfragment,container,false);

        lv_news = ((PullToRefreshListView) view.findViewById(R.id.lv_news));
        firstBar = ((ProgressBar) view.findViewById(R.id.firstBar));
        initData();
        eventPullToRefresh();
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id",aItemsList.get(position-1).article_id);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        lv_news.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mDelId=position-1;
                showDialog();
                return true;
            }
        });
        return view;
    }

    private void eventPullToRefresh() {
        lv_news.setScrollingWhileRefreshingEnabled(true);
        lv_news.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv_news.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                initData();
                //
                lv_news.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv_news.onRefreshComplete();
                    }
                }, 1000);

            }
        });
        initEvent();

    }

    private void initData() {
        if (page==1) {
            firstBar.setVisibility(View.VISIBLE);
        }
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=collect_article&user_id=135");
        requestParams.addBodyParameter("verification","e0d017ef76c8510244ebe0191f5dde15" );
        requestParams.addBodyParameter("page",page+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                System.out.println("000000000"+result);
                if (page <= pageCount) {
                    Gson gson = new Gson();
                    ArticleInFoBean articleInFoBean = gson.fromJson(result, ArticleInFoBean.class);
                    ArticleInFoBean.DataBean bean = articleInFoBean.data;
                    pageCount = Integer.parseInt(bean.total_pages.toString());
                    aItemsList.addAll(bean.items);

                    aItemsGoodsCommonAdapter.notifyDataSetChanged();
                    //initEvent();
                } else {
                    Toast.makeText(getActivity(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    aItemsGoodsCommonAdapter.notifyDataSetChanged();
                }
                firstBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                System.out.println("66666666666666666"+ex+"");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    private void initEvent() {
//        System.out.println("9999999999999999999");
//        System.out.println("8888" + aItemsList);
        aItemsGoodsCommonAdapter = new GoodsCommonAdapter<ArticleInFoBean.DataBean.AItems>(getContext(), aItemsList, R.layout.article_activity) {
            @Override
            public void convert(GoodsViewHolder viewHolder, ArticleInFoBean.DataBean.AItems aItems, int position) {
                //ImageView iv_image= viewHolder.getViewById(R.id.iv_image);
                TextView title = viewHolder.getViewById(R.id.tv_title1);
                ImageView iv_photo=viewHolder.getViewById(R.id.iv_photo);
              xUtilsImageUtils.display(iv_photo, "http://linchom.com//" + aItems.article_pic);
//               x.image().bind(iv_photo,"http://linchom.com//" +aItems.article_pic);
//                    System.out.println("77777777" + aItems.title);
                title.setText(aItems.title);
//                TextView tv_come = viewHolder.getViewById(R.id.tv_come);
//                tv_come.setText(aItems.article_source);
                TextView tv_title = viewHolder.getViewById(R.id.tv_time);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date=new Date(Long.parseLong((aItems.add_time))*1000);
                tv_title.setText(simpleDateFormat.format(date));

            }
        };
        lv_news.setAdapter(aItemsGoodsCommonAdapter);

    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定取消收藏吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=drop_collect_article");
                requestParams.addBodyParameter("user_id","135");
                requestParams.addBodyParameter("type","1");
                requestParams.addBodyParameter("id",aItemsList.get(mDelId).article_id);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        System.out.println("666666666666"+result);
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
        int size = aItemsList.size();
        if (size > 0) {
            aItemsList.remove(mDelId);
            aItemsGoodsCommonAdapter.notifyDataSetChanged();
        }
    }

}

