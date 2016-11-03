package linchom.com.linchomspace.service.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ramotion.foldingcell.FoldingCell;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.service.pojo.ServiceListBean;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

/**
 * Created by Administrator on 2016/11/1.
 */

public class FuwuFragment extends Fragment {

    View view ;
    private PullToRefreshListView ptr_service_content_ptr;

    private ListView listView;

    private List<ServiceListBean.Items> serviceLsit = new ArrayList<ServiceListBean.Items>();

    private GoodsCommonAdapter<ServiceListBean.Items> goodsCommonAdapter;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    private int  page = 1;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.service_fragment_layout,null);


        initView();

        initData();

        initEvent();

        return view;


    }

    private void initView() {


        ptr_service_content_ptr = ((PullToRefreshListView) view.findViewById(R.id.ptr_service_content_ptr));

    }

    private void initData() {


    }

    private void initEvent() {

        eventPtr();


    }

    private void eventPtr() {

        ptr_service_content_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_service_content_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_service_content_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_service_content_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){

                    page = 1;

                    getData();


                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){

                    page++;

                    getData();


                }



            }
        });

        ptr_service_content_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        listView=ptr_service_content_ptr.getRefreshableView();

        goodsCommonAdapter = new GoodsCommonAdapter<ServiceListBean.Items>(getActivity(),serviceLsit,R.layout.cell) {
            @Override
            public void convert(GoodsViewHolder viewHolder, ServiceListBean.Items items, final int position) {

                final FoldingCell fc = viewHolder.getViewById(R.id.folding_cell);

                // attach click listener to folding cell
                fc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fc.toggle(false);
                        if (unfoldedIndexes.contains(position))
                            registerFold(position);
                        else
                            registerUnfold(position);
                    }
                });

                if (unfoldedIndexes.contains(position)) {
                    fc.unfold(true);
                } else {
                    fc.fold(true);
                }


                TextView service_city= viewHolder.getViewById(R.id.service_city);
                TextView title_title=  viewHolder.getViewById(R.id.title_title);
                TextView title_status=  viewHolder.getViewById(R.id.title_status);
                TextView  title_release=viewHolder.getViewById(R.id.title_release);
                TextView  title_date=viewHolder.getViewById(R.id.title_date);

                TextView content_content=viewHolder.getViewById(R.id.content_content);
                TextView content_mobile=viewHolder.getViewById(R.id.content_mobile);
                TextView content_name= viewHolder.getViewById(R.id.content_name);
                TextView content_add_time= viewHolder.getViewById(R.id.content_add_time);
                TextView content_address= viewHolder.getViewById(R.id.content_address);

                TextView content_service_type=viewHolder.getViewById(R.id.content_service_type);
                service_city.setText(items.city_name);
                title_title.setText(items.title);
                title_status.setText(("0".equals(items.status))? "进行中" :"已完成");
                title_release.setText(("0".equals(items.release))? "公司" :"个人");
                title_date.setText(items.add_time);

                content_name.setText(items.user_id);
                content_content.setText(items.content);
                content_mobile.setText(items.mobile);
                content_add_time.setText(items.add_time);
                content_address.setText(items.address);
                content_service_type.setText(("0".equals(items.service_type)?"需求":"服务"));



            }
        };

        listView.setAdapter(goodsCommonAdapter);

        getData();

    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    private void getData(){

        //http://app.linchom.com/appapi.php?act=demand_services&service_type=2&usr_id=12

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","demand_services");


        requestParams.addBodyParameter("service_type","1");

        requestParams.addBodyParameter("user_id","12");

        requestParams.addBodyParameter("page",page+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if(page ==1){
                    serviceLsit.clear();


                }


                Gson gson = new Gson();

                ServiceListBean serviceListBean =   gson.fromJson(result, ServiceListBean.class);

                serviceLsit.addAll(serviceListBean.data.items);

                goodsCommonAdapter.notifyDataSetChanged();

                ptr_service_content_ptr.onRefreshComplete();






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
