package linchom.com.linchomspace.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.AlertsInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class Information_activity extends AppCompatActivity {

    private ListView lv_info;
    List<AlertsInfoBean.DataBe.AItems> InfoList = new ArrayList<AlertsInfoBean.DataBe.AItems>();
    private GoodsCommonAdapter<AlertsInfoBean.DataBe.AItems> InfoCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_activity);

        lv_info = ((ListView) findViewById(R.id.lv_info));
//        initEvent();
        initData();

    }

    private void initEvent() {
        System.out.println("5555555555555");



    }

    private void initData() {

        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=push");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//              System.out.println("99999999999999999999999999"+result);
                Gson gson = new Gson();
                AlertsInfoBean alertsInfoBean = gson.fromJson(result, AlertsInfoBean.class);
                final AlertsInfoBean.DataBe bean = alertsInfoBean.data;
                InfoList.addAll(bean.items);
//                System.out.println("000000000000000000"+InfoList);
                InfoCommonAdapter = new GoodsCommonAdapter<AlertsInfoBean.DataBe.AItems>(getApplicationContext(), InfoList, R.layout.information_item) {


                    @Override
                    public void convert(GoodsViewHolder viewHolder, AlertsInfoBean.DataBe.AItems aItems, int position) {

                        TextView tv_content = viewHolder.getViewById(R.id.intv_content);
//                        System.out.println("666666666666666666666"+aItems.content);
                        tv_content.setText(aItems.content);
                    }
                };

                lv_info.setAdapter(InfoCommonAdapter);


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