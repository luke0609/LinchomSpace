package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
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
import linchom.com.linchomspace.shopping.pojo.GoodsCartBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class GoodsCartActivity extends AppCompatActivity {


    private static final String TAG = "GoodsCartActivity";
    private String userId="12";

    private ListView cartListView;
    private PullToRefreshListView ptr_cartList_ptr;

    private GoodsCommonAdapter<GoodsCartBean.Data> goodsCommonAdapter;

    private List<GoodsCartBean.Data> cartList =new ArrayList<GoodsCartBean.Data>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_cart);

        getData();


        initView();

        initData();

        initEvent();
    }

    private void initView() {
        ptr_cartList_ptr = ((PullToRefreshListView) findViewById(R.id.ptr_cartList_ptr));


    }

    private void initData() {


    }

    private void initEvent() {

        eventPtr();

    }

    private void eventPtr() {

        ptr_cartList_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_cartList_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_cartList_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_cartList_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){





                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){



                }



            }
        });

        ptr_cartList_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        cartListView=ptr_cartList_ptr.getRefreshableView();



        goodsCommonAdapter =new GoodsCommonAdapter<GoodsCartBean.Data>(getApplicationContext(),cartList,R.layout.goods_cart_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCartBean.Data data, int position) {

            TextView iv_goods_cart_name =viewHolder.getViewById(R.id.iv_goods_cart_name);
            TextView tv_goods_cart_price=  viewHolder.getViewById(R.id.tv_goods_cart_price);

                iv_goods_cart_name.setText(data.goods_name);


            }
        };

        cartListView.setAdapter(goodsCommonAdapter);

        getData();


    }

    private void getData() {
        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","cartgoods");

        requestParams.addBodyParameter("user_id","12");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                

                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                Gson gson =new Gson();
                GoodsCartBean goodsCartBean =  gson.fromJson(result,GoodsCartBean.class);
                cartList.addAll(goodsCartBean.data);

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
