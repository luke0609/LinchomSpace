package linchom.com.linchomspace.shopping.goodsfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.PuBuAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListBean;
import linchom.com.linchomspace.shopping.widget.PullToRefreshStaggeredGridView;


@SuppressLint("NewApi")
public class GoodsListWaterfallFragment extends Fragment implements PullToRefreshBase.OnRefreshListener<StaggeredGridView>{
    private StaggeredGridView staggeredGridView;

    private PullToRefreshStaggeredGridView mPullToRefreshStaggerdGridView;

    private boolean refreshFlag =false;

    private   int  page = 1;

    private String introType;

    private int catId;


    private String keyWord;

    private int pageCount;

    List<GoodsListBean.Goods> goodsList = new ArrayList<GoodsListBean.Goods>();

    PuBuAdapter puBuAdapter;


    View view;
    private RelativeLayout rl_goodsList_load_listpubu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.goodslistwaterfall_fragment, null);



        initView();
        initData();
        initEvent();



        return view;


    }

    @Override
    public void onRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {


        PullToRefreshBase.Mode mode = mPullToRefreshStaggerdGridView.getCurrentMode();

        // View viewRefresh = null;

        if(mode == PullToRefreshBase.Mode.PULL_FROM_START){

            refreshFlag=true;

            page =1;
            getData();


        }else if(mode == PullToRefreshBase.Mode.PULL_FROM_END){

            page++;
            getData();


        }

    }

    private void initView() {

        Bundle bundle = getArguments();

        catId = bundle.getInt("catId");

        introType = bundle.getString("introType");

        mPullToRefreshStaggerdGridView = (PullToRefreshStaggeredGridView) view.findViewById(R.id.pull_grid_view);
        rl_goodsList_load_listpubu = ((RelativeLayout) view.findViewById(R.id.rl_goodsList_load_listpubu));

    }

    private void initData() {

    }

    private void initEvent() {

        staggeredGridViewEvent();


    }

    private void staggeredGridViewEvent() {

        mPullToRefreshStaggerdGridView.setMode(PullToRefreshBase.Mode.BOTH);

        mPullToRefreshStaggerdGridView.setScrollingWhileRefreshingEnabled(true);


        mPullToRefreshStaggerdGridView.setOnRefreshListener(this);

        staggeredGridView = mPullToRefreshStaggerdGridView.getRefreshableView();

        puBuAdapter = new PuBuAdapter(getActivity(),goodsList);

        staggeredGridView.setAdapter(puBuAdapter);

        getData();

        staggeredGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void getData() {

        if(page==1&&refreshFlag==false){

            rl_goodsList_load_listpubu.setVisibility(View.VISIBLE);


        }




        RequestParams requestParams = new RequestParams(GoodsHttpUtils.GOODSLISTURL);

        requestParams.addBodyParameter("key", GoodsHttpUtils.KEY);
        requestParams.addBodyParameter("verification", GoodsHttpUtils.VERIFICATION);



        requestParams.addBodyParameter("page", page + "");

        requestParams.addBodyParameter("cat_id", catId + "");

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

                if(page<=pageCount){

                    goodsList.addAll(goodsData.goods);

                    puBuAdapter.notifyDataSetChanged();



                }else{

                    Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                    puBuAdapter.notifyDataSetChanged();


                }

                mPullToRefreshStaggerdGridView.onRefreshComplete();




                rl_goodsList_load_listpubu.setVisibility(View.GONE);




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




}
