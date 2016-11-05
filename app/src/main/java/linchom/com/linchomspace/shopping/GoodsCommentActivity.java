package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsCommonBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class GoodsCommentActivity extends AppCompatActivity {

    private PullToRefreshListView ptr_goods_common_ptr;


    private ListView commonListView;


    public List<GoodsCommonBean.Items>  commonList = new ArrayList<GoodsCommonBean.Items>();


    private GoodsCommonAdapter<GoodsCommonBean.Items> goodsCommonAdapter;

    private String goodsId;

    private int page = 1;

    private int totalPage = 1;
    private ImageView titlebar_back;
    private RelativeLayout rl_goods_comm_load_progress;


    private boolean pullFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_comment);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));


        Intent intent =getIntent();

        Bundle bundle =intent.getBundleExtra("bundle");
        goodsId =bundle.getString("goodsId");


        initView();

        initData();

        initEvent();



    }

    private void initView() {
        ptr_goods_common_ptr = ((PullToRefreshListView) findViewById(R.id.ptr_goods_common_ptr));


        titlebar_back = ((ImageView) findViewById(R.id.titlebar_back));

        rl_goods_comm_load_progress = ((RelativeLayout) findViewById(R.id.rl_goods_comm_load_progress));


        eventPullToRefresh();



    }




    private void initData() {


    }

    private void initEvent() {
        titlebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });



    }


    private void eventPullToRefresh() {

        ptr_goods_common_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_goods_common_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_goods_common_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_goods_common_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){
                    pullFlag =true;


                    page =1;

                    getData();





                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){

                    page++;

                    getData();



                }



            }
        });

        ptr_goods_common_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        commonListView=ptr_goods_common_ptr.getRefreshableView();

        goodsCommonAdapter = new GoodsCommonAdapter<GoodsCommonBean.Items>(getApplicationContext(),commonList,R.layout.article_comment_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCommonBean.Items items, int position) {
                //TextView tv_goods_common_name = viewHolder.getViewById(R.id.tv_goods_common_name);
                //TextView tv_goods_common_time= viewHolder.getViewById(R.id.tv_goods_common_time);

                // TextView tv_goods_common_content = viewHolder.getViewById(R.id.tv_goods_common_content);



                TextView tv_article_comment_username=viewHolder.getViewById(R.id.tv_article_comment_username);

                TextView tv_article_comment_time= viewHolder.getViewById(R.id.tv_article_comment_time);

                TextView tv_position = viewHolder.getViewById(R.id.tv_position);

                TextView tv_article_content= viewHolder.getViewById(R.id.tv_article_content);

                tv_position.setVisibility(View.INVISIBLE);



                tv_article_comment_username.setText(items.user_name);

                Long time = Long.parseLong(items.add_time)*1000;

                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


                String str = sdf.format(time);


                tv_article_comment_time.setText(str);

                tv_article_content.setText(items.content);



            }
        };


        commonListView.setAdapter(goodsCommonAdapter);

        getData();

    }

    private void getData() {


       // http://app.linchom.com/appapi.php?act=goods_comment&goods_id=120

        if(page==1&&pullFlag==false){

            rl_goods_comm_load_progress.setVisibility(View.VISIBLE);



        }



        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","goods_comment");

        requestParams.addBodyParameter("goods_id",goodsId+"");

        requestParams.addBodyParameter("page",page+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                rl_goods_comm_load_progress.setVisibility(View.GONE);

                Gson gson = new Gson();
                GoodsCommonBean goodsCommonBean = gson.fromJson(result,GoodsCommonBean.class);

                if(page==1){


                    commonList.clear();



                }

                totalPage = Integer.parseInt(goodsCommonBean.data.total_pages);

                if(page<=totalPage){

                    commonList.addAll(goodsCommonBean.data.items);


                }else{

                    Toast.makeText(getApplicationContext(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                }




                goodsCommonAdapter.notifyDataSetChanged();

                ptr_goods_common_ptr.onRefreshComplete();



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
