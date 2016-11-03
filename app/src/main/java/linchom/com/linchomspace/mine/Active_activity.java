package linchom.com.linchomspace.mine;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.ActivityInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class Active_activity extends AppCompatActivity {

    private GoodsCommonAdapter<ActivityInfoBean.DataBean> activityCommonAdapter;
    private ListView lv_activity;
    List<ActivityInfoBean.DataBean> dataBeanList=new ArrayList<>();
    List<ActivityInfoBean.DataBean> imgList=new ArrayList<>();
    private ProgressBar firstBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_activity);

        firstBar = ((ProgressBar)findViewById(R.id.firstBar));
        lv_activity = ((ListView) findViewById(R.id.lv_activity));

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(imgList.get(position).ad_link));
                startActivity(intent);
            }
        });

        initData();
        initEvent();

    }

    private void initEvent() {
        activityCommonAdapter=new GoodsCommonAdapter<ActivityInfoBean.DataBean>(getApplicationContext(),imgList,R.layout.activity_items) {


            @Override
            public void convert(GoodsViewHolder viewHolder, ActivityInfoBean.DataBean dataBean, int position) {
                ImageView imageView=viewHolder.getViewById(R.id.iv_activity);
                x.image().bind(imageView,dataBean.img_url);
            }
        };
        lv_activity.setAdapter(activityCommonAdapter);

    }

    private void initData() {
        firstBar.setVisibility(View.VISIBLE);
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=ads");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                System.out.println("000000000000"+result);
                Gson gson=new Gson();
                ActivityInfoBean bean=gson.fromJson(result,ActivityInfoBean.class);
//                System.out.println("3333333333333333333"+bean);
                dataBeanList.addAll(bean.data);
//                System.out.println("8888888888888888888"+dataBeanList);
                for (int i=0;i<dataBeanList.size();i++){
                    if (dataBeanList.get(i).position_desc.equals("我的下的活动页面")){
                        imgList.add(dataBeanList.get(i));
                    }
                }
                firstBar.setVisibility(View.GONE);
                initEvent();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                System.out.println("76667676767676776677"+ex);
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

