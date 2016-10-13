package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
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
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class GoodsListActivity extends AppCompatActivity {

    private static final String TAG = "GoodsListActivity";

    private PullToRefreshListView ptr_goodsList_ptr;

    private ListView goodsListView;

    private String introType;

    private int catId;


    private String keyWord;

    private  int page;

    private GoodsCommonAdapter<GoodsListBean.Goods> goodsCommonAdapter;

    private List<GoodsListBean.Goods>  goodsList =new ArrayList<GoodsListBean.Goods>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        initData();
        initEvent();


    }

    private void initView() {

        ptr_goodsList_ptr = ((PullToRefreshListView) findViewById(R.id.ptr_goodsList_ptr));

    }

    private void initData() {
        //cateId
        //bundle

        Intent intent  =getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");


        catId=bundle.getInt("cateId");



    }



    private void initEvent() {
        eventPullToRefresh();


    }



    private void eventPullToRefresh() {

        ptr_goodsList_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_goodsList_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_goodsList_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_goodsList_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){


                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){
                    page++;
                    getData();


                }



            }
        });

        ptr_goodsList_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        goodsListView=ptr_goodsList_ptr.getRefreshableView();

        //new adapter


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsListBean.Goods>(getApplicationContext(),goodsList,R.layout.goodslist_item) {




            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsListBean.Goods goods, int position) {


                ImageView iv_goodsList_item_image = ((ImageView) viewHolder.getViewById(R.id.iv_goodsList_item_image));
                TextView tv_goodsList_item_title = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_title));

                TextView tv_goodsList_item_price = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_price));

                tv_goodsList_item_title.setText(goods.goods_name);
                tv_goodsList_item_price.setText(goods.shop_price);


            }
        };


        //setadapter
        goodsListView.setAdapter(goodsCommonAdapter);
        //getdata()更新数据

        getData();


    }


    private void getData() {

        RequestParams requestParams =new RequestParams(GoodsHttpUtils.GOODSLISTURL);

        requestParams.addBodyParameter("key",GoodsHttpUtils.KEY);
        requestParams.addBodyParameter("verification",GoodsHttpUtils.VERIFICATION);

        requestParams.addBodyParameter("cat_id",catId+"");

        requestParams.addBodyParameter("page",page+"");


        requestParams.addBodyParameter("intro_type","");

        requestParams.addBodyParameter("keyword","");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"result"+result);

               Gson gson =new Gson();

                GoodsListBean goodsListBean = gson.fromJson(result,GoodsListBean.class);

                GoodsListBean.Data goodsData = goodsListBean.data;
               // goodsList.clear();
                 goodsList.addAll(goodsData.goods);//!!!!!!

                goodsCommonAdapter.notifyDataSetChanged();
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
