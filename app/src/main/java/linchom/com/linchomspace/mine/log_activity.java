package linchom.com.linchomspace.mine;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.LogInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;


public class log_activity extends AppCompatActivity {

    private static final String TAG = "log_activity";
    private GoodsCommonAdapter<LogInfoBean.DataBean.Litems> logCommonAdapter;
    private PullToRefreshListView lv_loglist;
    List<LogInfoBean.DataBean.Litems> logList = new ArrayList<LogInfoBean.DataBean.Litems>();
    private ImageView iv_logback;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_activity);

        lv_loglist = ((PullToRefreshListView) findViewById(R.id.lv_loglist));

        iv_logback = ((ImageView) findViewById(R.id.iv_logback));
        iv_logback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
        //initEvent();
//        eventPullToRefresh();
    }

    private void initEvent() {
        logCommonAdapter = new GoodsCommonAdapter<LogInfoBean.DataBean.Litems>(getApplicationContext(), logList, R.layout.log_item) {


            @Override
            public void convert(GoodsViewHolder viewHolder, LogInfoBean.DataBean.Litems litems, int position) {

                TextView tv_tb = viewHolder.getViewById(R.id.tv_bt);
                tv_tb.setText(litems.title);
                TextView tv_cont = viewHolder.getViewById(R.id.tv_cont);
                tv_cont.setText(litems.content);
                TextView add_time = viewHolder.getViewById(R.id.add_time);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                Date date = new Date(Long.parseLong((litems.add_time)));
                add_time.setText(simpleDateFormat.format(date));

            }
        };
        lv_loglist.setAdapter(logCommonAdapter);
    }

    private void initData() {
        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=demand_services_log&user_id=135");
        requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        requestParams.addBodyParameter("page", page + "");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                System.out.println("6666666"+result);
                Gson gson = new Gson();
                LogInfoBean logInfoBean = gson.fromJson(result, LogInfoBean.class);
                LogInfoBean.DataBean mbean = logInfoBean.data;
                logList.addAll(mbean.items);
                initEvent();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                System.out.println("99999"+ex+"");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
};

//    private void eventPullToRefresh() {
//
//        lv_loglist.setScrollingWhileRefreshingEnabled(true);
//        lv_loglist.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
//        lv_loglist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
////                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
////                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
////
////                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                //异步任务拿数据
//                page++;
//                initData();
//                lv_loglist.onRefreshComplete();
//            }
//        });
//
//    }
//}
//
