package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class GoodsAreaActivity extends AppCompatActivity {

    private String userId="12";

    private Button btn_goods_area_addaddress;
    private ListView lv_goods_area_list;

    private GoodsCommonAdapter<AreaListBean.Data> goodsCommonAdapter;

    private List<AreaListBean.Data> areaList =new ArrayList<AreaListBean.Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_area);

        initView();


        initData();

        initEvent();
    }

    private void initView() {
        btn_goods_area_addaddress = ((Button) findViewById(R.id.btn_goods_area_addaddress));
        lv_goods_area_list = ((ListView) findViewById(R.id.lv_goods_area_list));

    }

    private void initData() {


    }

    private void initEvent() {

        goodsCommonAdapter=new GoodsCommonAdapter<AreaListBean.Data>(getApplication(),areaList,R.layout.goods_area_item_layout) {
            @Override
            public void convert(GoodsViewHolder viewHolder, AreaListBean.Data data, int position) {

                TextView tv_goods_area_name=viewHolder.getViewById(R.id.tv_goods_area_name);

                TextView tv_goods_area_tel= viewHolder.getViewById(R.id.tv_goods_area_tel);

                TextView tv_goods_area_address=viewHolder.getViewById(R.id.tv_goods_area_address);

                tv_goods_area_name.setText(data.consignee);
                tv_goods_area_tel.setText(data.tel);
                tv_goods_area_address.setText(data.address);






            }
        };

        lv_goods_area_list.setAdapter(goodsCommonAdapter);

        getData();





        btn_goods_area_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoodsAreaActivity.this,AddaddressActivity.class);

                startActivity(intent);


            }
        });


    }

    private void getData() {
        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);
        //?act=consignee&user_id=12

        requestParams.addBodyParameter("act","consignee");

        requestParams.addBodyParameter("user_id",userId+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();

                AreaListBean areaListBean = gson.fromJson(result,AreaListBean.class);
                areaList.addAll(areaListBean.data);
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
