package linchom.com.linchomspace.service.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.service.LogDetailActivity;
import linchom.com.linchomspace.service.pojo.LogBean;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;
import linchom.com.linchomspace.shopping.widget.PullToRefreshStaggeredGridView;

/**
 * Created by Administrator on 2016/11/1.
 */

public class LogFragment extends Fragment {

    View view ;

    private PullToRefreshStaggeredGridView pull_grid_view_log;


    private StaggeredGridView staggeredGridView;


    private GoodsCommonAdapter<LogBean.Items> goodsCommonAdapter;


    private List<LogBean.Items> logList = new ArrayList<LogBean.Items>();


    private String userId ="135";

    private int page =1;

    private int pageCount=1;
    private RelativeLayout rl_service_pro_log;


    private boolean pullFlag = false;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        view= inflater.inflate(R.layout.service_log_fragment_layout,null);


        initView();

        initData();

        initEvent();

        return view;


    }



    private void initView() {

        page = 1;

        pull_grid_view_log = ((PullToRefreshStaggeredGridView) view.findViewById(R.id.pull_grid_view_log));

        rl_service_pro_log = ((RelativeLayout) view.findViewById(R.id.rl_service_pro_log));


    }

    private void initData() {


    }

    private void initEvent() {

        eventRefreshPullWater();




    }

    private void eventRefreshPullWater() {

        pull_grid_view_log.setMode(PullToRefreshBase.Mode.BOTH);

        pull_grid_view_log.setScrollingWhileRefreshingEnabled(true);

        pull_grid_view_log.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<StaggeredGridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {
                PullToRefreshBase.Mode mode = pull_grid_view_log.getCurrentMode();



                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){

                    pullFlag =true;

                    page = 1;

                    getData();




                }else if(mode == PullToRefreshBase.Mode.PULL_FROM_END){

                    page++;

                    getData();




                }







            }
        });

        staggeredGridView = pull_grid_view_log.getRefreshableView();

        goodsCommonAdapter=new GoodsCommonAdapter<LogBean.Items>(getActivity(),logList,R.layout.service_log_list_item_layout) {
            @Override
            public void convert(GoodsViewHolder viewHolder, LogBean.Items items, int position) {

                TextView tv_service_log=viewHolder.getViewById(R.id.tv_service_log);

                TextView tv_service_content= viewHolder.getViewById(R.id.tv_service_content);

                TextView tv_service_addtime = viewHolder.getViewById(R.id.tv_service_addtime);


                DynamicHeightImageView iv_service_log_img = viewHolder.getViewById(R.id.iv_service_log_img);

                tv_service_log.setText("【"+items.title+"】");
                tv_service_content.setText(items.content);


                Long time = Long.parseLong(items.add_time)*1000;

                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");


                String str = sdf.format(time);


                tv_service_addtime.setText(str);



                if(items.photo.length()==0){

                    iv_service_log_img.setVisibility(View.INVISIBLE);


                }

                String[] photos = (items.photo).split(",");
                if(photos.length!=0){

                    GoodsXUtilsImage.display(iv_service_log_img,photos[0]);

                }





            }
        };



        staggeredGridView.setAdapter(goodsCommonAdapter);

        getData();


        staggeredGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                //0开始

                Intent intent = new Intent(getActivity(), LogDetailActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("title",logList.get(position).title);

                bundle.putString("addtime",logList.get(position).add_time);

                bundle.putString("content",logList.get(position).content);


                bundle.putString("photo",logList.get(position).photo);



                intent.putExtra("bundle",bundle);



                startActivity(intent);


            }
        });




    }

    private void getData() {
        if(page==1&&pullFlag==false){


            rl_service_pro_log.setVisibility(View.VISIBLE);

        }





        final RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);


        //app.linchom.com/appapi.php?act=demand_services_log&user_id=135


        requestParams.addBodyParameter("act","demand_services_log");

        requestParams.addBodyParameter("user_id",userId);

        requestParams.addBodyParameter("page",page+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if(page==1){
                    logList.clear();


                }

                Gson gson = new Gson();


                LogBean logBean =gson.fromJson(result,LogBean.class);

                pageCount = Integer.parseInt(logBean.data.total_pages);

                if(page<=pageCount){

                    logList.addAll(logBean.data.items);

                }else{

                    Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

                }

                pull_grid_view_log.onRefreshComplete();



                goodsCommonAdapter.notifyDataSetChanged();

                rl_service_pro_log.setVisibility(View.GONE);



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
