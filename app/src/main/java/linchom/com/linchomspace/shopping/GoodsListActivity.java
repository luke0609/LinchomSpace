package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

public class GoodsListActivity extends AppCompatActivity implements View.OnClickListener{
//修改时间17:02

    private static final String TAG = "GoodsListActivity";

    private PullToRefreshListView ptr_goodsList_ptr;

    private ListView goodsListView;

    private String introType;

    private int catId;


    private String keyWord;

    private  int page=1;

    private int pageCount;

    private GoodsCommonAdapter<GoodsListBean.Goods> goodsCommonAdapter;

    private List<GoodsListBean.Goods>  goodsList =new ArrayList<GoodsListBean.Goods>();
    private RelativeLayout rl_goodsList_load;

    private boolean pullFlag = false;
    private Button btn_goodsList_default;
    private Button btn_goodsList_hot;
    private Button btn_goodsList_new;
    private Button btn_goodsList_price;


    private View v_goodLists_default_line;
    private View v_goodLists_hot_line;
    private View v_goodLists_new_line;
    private View v_goodLists_price_line;


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

        rl_goodsList_load = ((RelativeLayout) findViewById(R.id.rl_goodsList_load));

        btn_goodsList_default = ((Button) findViewById(R.id.btn_goodsList_default));
        btn_goodsList_hot = ((Button) findViewById(R.id.btn_goodsList_hot));
        btn_goodsList_new = ((Button) findViewById(R.id.btn_goodsList_new));
        btn_goodsList_price = ((Button) findViewById(R.id.btn_goodsList_price));

        v_goodLists_default_line = ((View) findViewById(R.id.v_goodLists_default_line));
        v_goodLists_hot_line = ((View) findViewById(R.id.v_goodLists_hot_line));
        v_goodLists_new_line = ((View) findViewById(R.id.v_goodLists_new_line));
        v_goodLists_price_line = ((View) findViewById(R.id.v_goodLists_price_line));


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
        btn_goodsList_default.setOnClickListener(this);
        btn_goodsList_hot.setOnClickListener(this);
        btn_goodsList_new.setOnClickListener(this);
        btn_goodsList_price.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_goodsList_default:
                pressDefault();

                break;
            case R.id.btn_goodsList_hot:
                pressHot();
                break;
            case R.id.btn_goodsList_new:
                pressNew();
                break;
            case R.id.btn_goodsList_price:
                pressPrice();
                break;

        }



    }

    private void pressDefault() {

        btn_goodsList_default.setEnabled(false);
        //btn_goodsList_default.setClickable(false);
        //默认
        introType=null;
        page =1;
        btn_goodsList_default.setTextColor(Color.rgb(255,64,00));
        v_goodLists_default_line.setVisibility(View.VISIBLE);

        getData();

    }

    private void pressHot() {
        //is_hot
        introType="is_hot";
        page=1;
        getData();

    }

    private void pressNew() {
        //is_new  is_best
        introType="is_best";
        page=1;
        getData();

    }

    private void pressPrice() {

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

                    pullFlag=true;
                    page =1;
                    getData();


                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){


                    page++;
                    Log.i(TAG,page+"");

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


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsListBean.Goods>(getApplicationContext(),goodsList,R.layout.goodslist_item) {




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


    private void getData() {
        if(page==1&&pullFlag==false){

            rl_goodsList_load.setVisibility(View.VISIBLE);


        }


        RequestParams requestParams =new RequestParams(GoodsHttpUtils.GOODSLISTURL);

        requestParams.addBodyParameter("key",GoodsHttpUtils.KEY);
        requestParams.addBodyParameter("verification",GoodsHttpUtils.VERIFICATION);

        requestParams.addBodyParameter("cat_id",catId+"");

        requestParams.addBodyParameter("page",page+"");


        requestParams.addBodyParameter("intro_type",introType+"");

        requestParams.addBodyParameter("keyword","");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                rl_goodsList_load.setVisibility(View.GONE);

                if(page==1){


                    goodsList.clear();

                }

               Gson gson =new Gson();

                GoodsListBean goodsListBean = gson.fromJson(result,GoodsListBean.class);

                GoodsListBean.Data goodsData = goodsListBean.data;

                pageCount = Integer.parseInt(goodsData.page_count);

                Log.i(TAG,"总页数"+pageCount+"");

                if(page<=pageCount) {
                    goodsList.addAll(goodsData.goods);

                    goodsCommonAdapter.notifyDataSetChanged();

                    ptr_goodsList_ptr.onRefreshComplete();

                }else{
                    Toast.makeText(getApplicationContext(),"已经是最后一页了",Toast.LENGTH_SHORT).show();


                    ptr_goodsList_ptr.onRefreshComplete();


                }

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
