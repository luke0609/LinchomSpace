package linchom.com.linchomspace.shopping.goodsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.GoodsActivity;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

/**
 * Created by Administrator on 2016/10/16.
 */

public class GoodsListFragment extends Fragment {


    private String introType;

    private int catId;

    private int pageCount;


    private String keyWord;



    View view;
    private PullToRefreshListView ptr_goodsList_ptr;

    private boolean refreshFlag =false;


    private ListView goodsListView;

    private GoodsCommonAdapter<GoodsListBean.Goods> goodsCommonAdapter;

    private List<GoodsListBean.Goods> goodsList =new ArrayList<GoodsListBean.Goods>();

    private int page =1;
    private RelativeLayout rl_goodsList_load_list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.goodslist_fragment, null);

        initView();
        initData();
        initEvent();


        return view;

    }

    private void initView() {

        Bundle bundle = getArguments();

        catId = bundle.getInt("catId");

        introType = bundle.getString("introType");

        ptr_goodsList_ptr = ((PullToRefreshListView) view.findViewById(R.id.ptr_goodsList_ptr));
        rl_goodsList_load_list = ((RelativeLayout) view.findViewById(R.id.rl_goodsList_load_list));


    }

    private void initData() {

    }

    private void initEvent() {

        eventPullToRefresh();

        goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), GoodsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {

        if(page==1&&refreshFlag==false){

            rl_goodsList_load_list.setVisibility(View.VISIBLE);


        }



        RequestParams requestParams = new RequestParams(GoodsHttpUtils.GOODSLISTURL);

        requestParams.addBodyParameter("key", GoodsHttpUtils.KEY);
        requestParams.addBodyParameter("verification", GoodsHttpUtils.VERIFICATION);



        requestParams.addBodyParameter("page", page + "");

        requestParams.addBodyParameter("cat_id", catId+ "");

        requestParams.addBodyParameter("intro_type", introType+ "");






        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                if (page == 1) {


                    goodsList.clear();

                }


                Gson gson = new Gson();

                GoodsListBean goodsListBean = gson.fromJson(result, GoodsListBean.class);

                GoodsListBean.Data goodsData = goodsListBean.data;


                pageCount = Integer.parseInt(goodsData.page_count);

                    if(page<=pageCount) {

                        goodsList.addAll(goodsData.goods);

                        goodsCommonAdapter.notifyDataSetChanged();

                    }else{

                        Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                        goodsCommonAdapter.notifyDataSetChanged();


                    }

                    ptr_goodsList_ptr.onRefreshComplete();






                rl_goodsList_load_list.setVisibility(View.GONE);




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

    }


    private void eventPullToRefresh() {

        ptr_goodsList_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_goodsList_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_goodsList_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_goodsList_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){
                    refreshFlag=true;

                    page =1;
                    getData();


                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){


                    page++;


                    getData();

                }



            }
        });

        ptr_goodsList_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        goodsListView=ptr_goodsList_ptr.getRefreshableView();

        //new adapter


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsListBean.Goods>(getActivity(),goodsList,R.layout.goodslist_item) {




            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsListBean.Goods goods, int position) {


                ImageView iv_goodsList_item_image = ((ImageView) viewHolder.getViewById(R.id.iv_goodsList_item_image));
                TextView tv_goodsList_item_title = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_title));

                TextView tv_goodsList_item_price = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_price));

                tv_goodsList_item_title.setText(goods.goods_name);
                tv_goodsList_item_price.setText(goods.shop_price);

                GoodsXUtilsImage.display(iv_goodsList_item_image,GoodsHttpUtils.IMGURL+goods.goods_thumb);


            }
        };


        //setadapter
        goodsListView.setAdapter(goodsCommonAdapter);
        //getdata()更新数据

        getData();




    }


}
