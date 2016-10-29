package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.KindsInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;

public class Disclose_activity extends AppCompatActivity {

    private ImageView iv_disback;
    private TextView b_submit;
    private TextView tv_mine_baoliao;
    private TextView tv_biaoti;
    private GoodsCommonAdapter<KindsInfoBean.DaBean> kindsCommonAdapter;
    List<KindsInfoBean.DaBean> kindslist=new ArrayList<KindsInfoBean.DaBean>();
    List<String> content=new ArrayList<String>();
    private ListView lv_fenlei;

    PopupWindow popupWindow;
    private RelativeLayout rl_fj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclose_activity);

//        System.out.println("onCreate");

        lv_fenlei = ((ListView) findViewById(R.id.lv_fenlei));

        iv_disback = ((ImageView) findViewById(R.id.iv_disback));

        tv_mine_baoliao = ((TextView) findViewById(R.id.tv_mine_baoliao));

        tv_biaoti = ((TextView) findViewById(R.id.tv_biaoti));

        iv_disback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_fj = (RelativeLayout) findViewById(R.id.rl_fj);
        rl_fj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        b_submit = ((TextView) findViewById(R.id.b_submit));
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        getData();
        initData();
//      initEvent();

        tv_biaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.pop_item, null);
                popupWindow=new PopupWindow(popupView,200,ViewGroup.LayoutParams.MATCH_PARENT);
                ListView lv= (ListView) popupView.findViewById(R.id.lv_fenlei);
//                System.out.println("content========="+content);
                ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.pop_items,content);
                lv.setAdapter(arrayAdapter);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popupWindow.showAsDropDown(v);
            }
        });
    }

//    public void initEvent() {
//        kindsCommonAdapter = new GoodsCommonAdapter<KindsInfoBean.DaBean>(getApplicationContext(), kindslist, R.layout.pop_items) {
//
//
//            private TextView topic_category_id;
//            private TextView topic_category_name;
//
//            @Override
//            public void convert(GoodsViewHolder viewHolder, KindsInfoBean.DaBean daBean, int position) {
//
//              TextView  topic_category_id = ((TextView) viewHolder.getViewById(R.id.topic_category_id));
//                topic_category_id.setText(daBean.category_id);
//                TextView  topic_category_name = ((TextView) viewHolder.getViewById(R.id.topic_category_name));
//                topic_category_name.setText(daBean.topic_category_name);
//            }
//            };
//        initData();
//        //放到适配器中在页面显示
//        lv_fenlei.setAdapter(kindsCommonAdapter);
//        }

    public void initData() {

        tv_mine_baoliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mydisclose.class);
                startActivity(intent);
            }
        });


//        tv_biaoti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View popupView = getLayoutInflater().inflate(R.layout.pop_item, null);
//                popupWindow=new PopupWindow(popupView,ViewGroup.LayoutParams.MATCH_PARENT,200);
//                ListView lv= (ListView)findViewById(R.id.lv_fenlei);
//                ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.pop_items,content);
//                lv.setAdapter(arrayAdapter);
//                popupWindow.setTouchable(true);
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
//                popupWindow.showAsDropDown(v);
//
//
//                tv_biaoti = (TextView) findViewById(R.id.tv_biaoti);
//                tv_biaoti.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.showAsDropDown(v);
//                    }
//                });
//
//
//            }
//        });
    }
    public void getData(){
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=topic_category");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();
                KindsInfoBean kindsInfoBean=gson.fromJson(result,KindsInfoBean.class);
                kindslist.addAll(kindsInfoBean.data);
                System.out.println("kindslist"+kindslist);
                for (int i=0;i<kindslist.size();i++) {
                    String a = kindslist.get(i).topic_category_name;
                    content.add(a);

                }
                System.out.println("content"+content);

//                initEvent();
                //lv_fenlei.setAdapter();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println("errrr"+ex.getMessage().toString());
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

