package linchom.com.linchomspace.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobeta.android.dslv.DragSortListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.LogInfoBean;
import linchom.com.linchomspace.mine.pojo.LovedInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class log_activity extends AppCompatActivity {

    private static final String TAG = "log_activity";
    private GoodsCommonAdapter<LogInfoBean.DataBean> logCommonAdapter;
    private DragSortListView lv_loglist;

    List<LogInfoBean.DataBean> beanlist=new ArrayList<LogInfoBean.DataBean>();

    private ImageView iv_logback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_activity);

        lv_loglist = ((DragSortListView) findViewById(R.id.lv_loglist));

        iv_logback = ((ImageView) findViewById(R.id.iv_logback));
        iv_logback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //initData();
        initEvent();

        lv_loglist.setRemoveListener(new DragSortListView.RemoveListener() {
            @Override
            public void remove(int i) {
             //   logCommonAdapter.remove(i);
            }
        });
    }
    public void initData() {
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=message&user_id=12");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            private TextView order_id;
            private TextView action_user;
            private TextView order_status;
            private TextView shipping_status;
            private TextView pay_status;
            private TextView action_note;
            private TextView log_time;
            @Override
            public void onSuccess(String result) {

//                System.out.println("onSuccess"+result);
//                LogInfoBean bean=new LogInfoBean();
                Gson gson=new Gson();
                LogInfoBean logInfoBean=gson.fromJson(result,LogInfoBean.class);
//                System.out.println("123"+logInfoBean);
//                System.out.println("sadsaa"+logInfoBean.data);
                beanlist.addAll(logInfoBean.data);
//                System.out.println("etsadas");
                initEvent();
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

    public void initEvent(){
        //数据放进去
        logCommonAdapter = new GoodsCommonAdapter<LogInfoBean.DataBean>(getApplicationContext(), beanlist, R.layout.log_item) {

            @Override
            public void convert(GoodsViewHolder viewHolder, LogInfoBean.DataBean dataBean, int position) {
                TextView order_id = ((TextView) viewHolder.getViewById(R.id.order_id));
                order_id.setText(dataBean.order_id);
                TextView shipping_status = ((TextView) viewHolder.getViewById(R.id.shipping_status));
                shipping_status.setText(dataBean.shipping_status);
                TextView action_user = ((TextView) viewHolder.getViewById(R.id.action_user));
                action_user.setText(dataBean.action_user);
                TextView order_status = ((TextView) viewHolder.getViewById(R.id.order_status));
                order_status.setText(dataBean.order_status);
                TextView pay_status = ((TextView) viewHolder.getViewById(R.id.pay_status));
                pay_status.setText(dataBean.pay_status);
                TextView action_note = ((TextView) viewHolder.getViewById(R.id.action_note));
                action_note.setText(dataBean.action_note);
                TextView log_time =((TextView) viewHolder.getViewById(R.id.log_time));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                Date date=new Date(Long.parseLong(dataBean.log_time));
                log_time.setText(sdf.format(date));
            }

        };
        initData();
        //放到适配器中在页面显示
        lv_loglist.setAdapter(logCommonAdapter);

    }
}
