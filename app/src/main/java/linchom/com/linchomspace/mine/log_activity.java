package linchom.com.linchomspace.mine;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
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
    private GoodsCommonAdapter<LogInfoBean.DataBean> logCommonAdapter;
    private PullToRefreshListView lv_loglist;
    List<LogInfoBean.DataBean> beanlist=new ArrayList<LogInfoBean.DataBean>();
    private ImageView iv_logback;
    private int page =1;

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
        //  initEvent();
        eventPullToRefresh();
    }
    public void initData() {

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=message&user_id=12");

        requestParams.addBodyParameter("verification","e0d017ef76c8510244ebe0191f5dde15" );
        requestParams.addBodyParameter("page",page+"");

//        System.out.println("page==="+page);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
//                 初始化数据
//                List<LogInfoBean.DataBean> beanlist = new ArrayList<LogInfoBean.DataBean>();
//                System.out.println("onSuccess"+result);
//                LogInfoBean bean=new LogInfoBean();

                Gson gson = new Gson();
                LogInfoBean logInfoBean = gson.fromJson(result, LogInfoBean.class);
//                System.out.println("123"+logInfoBean);
//                System.out.println("sadsaa"+logInfoBean.data);
                beanlist.addAll(logInfoBean.data);
//                  System.out.println("if======"+beanlist);
                initEvent();
               /*     logCommonAdapter=new GoodsCommonAdapter<LogInfoBean.DataBean>(getApplicationContext(), beanlist, R.layout.log_item) {
                        @Override
                        public void convert(GoodsViewHolder viewHolder, LogInfoBean.DataBean dataBean, int position) {
                            TextView order_id = (viewHolder.getViewById(R.id.order_id));

                            System.out.println("initEvent=========="+dataBean.order_id);
                            order_id.setText(dataBean.order_id);
                            TextView shipping_status = (viewHolder.getViewById(R.id.shipping_status));
                            shipping_status.setText(dataBean.shipping_status);
//                    TextView action_user = ((TextView) viewHolder.getViewById(R.id.action_user));
//                    action_user.setText(dataBean.action_user);
                            TextView order_status = ( viewHolder.getViewById(R.id.order_status));
                            order_status.setText(dataBean.order_status);
//                    TextView pay_status = ((TextView) viewHolder.getViewById(R.id.pay_status));
//                    pay_status.setText(dataBean.pay_status);
                            TextView action_note = ( viewHolder.getViewById(R.id.action_note));
                            action_note.setText(dataBean.action_note);
                            TextView log_time =( viewHolder.getViewById(R.id.log_time));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                            Date date=new Date(Long.parseLong(dataBean.log_time));
                            log_time.setText(sdf.format(date));
                        }
                    };

                    lv_loglist.setAdapter(logCommonAdapter);*/
//                System.out.println("99999999999999");
//                rl_goodsList_load_list.setVisibility(View.GONE);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e(TAG,ex+"");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    public void initEvent() {
        //数据放进去
//        System.out.println("initEvent");
        logCommonAdapter = new GoodsCommonAdapter<LogInfoBean.DataBean>(getApplicationContext(), beanlist, R.layout.log_item) {

            @Override
            public void convert(GoodsViewHolder viewHolder, LogInfoBean.DataBean dataBean, int position) {
                TextView order_id = (viewHolder.getViewById(R.id.order_id));

//                System.out.println("initEvent=========="+dataBean.order_id);
                order_id.setText(dataBean.order_id);
                TextView shipping_status = (viewHolder.getViewById(R.id.shipping_status));
                shipping_status.setText(dataBean.shipping_status);
//                    TextView action_user = ((TextView) viewHolder.getViewById(R.id.action_user));
//                    action_user.setText(dataBean.action_user);
                TextView order_status = ( viewHolder.getViewById(R.id.order_status));
                order_status.setText(dataBean.order_status);
//                    TextView pay_status = ((TextView) viewHolder.getViewById(R.id.pay_status));
//                    pay_status.setText(dataBean.pay_status);
                TextView action_note = ( viewHolder.getViewById(R.id.action_note));
                action_note.setText(dataBean.action_note);
                TextView log_time =( viewHolder.getViewById(R.id.log_time));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                Date date=new Date(Long.parseLong(dataBean.log_time));
                log_time.setText(sdf.format(date));
            }

        };
//        initData();
        //放到适配器中在页面显示
        lv_loglist.setAdapter(logCommonAdapter);

    }

    private void eventPullToRefresh() {

        lv_loglist.setScrollingWhileRefreshingEnabled(true);
        lv_loglist.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv_loglist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据
                page++;
                initData();
                lv_loglist.onRefreshComplete();
            }
        });

    }
}

