package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.AreaListBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class GoodsAreaActivity extends AppCompatActivity {

    private static final String TAG = "GoodsAreaActivity";
    private String userId="12";

    private Button btn_goods_area_addaddress;
    private ListView lv_goods_area_list;

    private GoodsCommonAdapter<AreaListBean.Data> goodsCommonAdapter;

    private List<AreaListBean.Data> areaList =new ArrayList<AreaListBean.Data>();

    private Map<Integer,Boolean>  checkStatusMap = new HashMap<Integer,Boolean>();

    private Integer checkFlag;

    private CheckBox ckFlag;//当前选中的ck


   // private List<CheckBox> ckList = new ArrayList<CheckBox>();

    private Set<CheckBox> ckSet = new HashSet<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_area);

        initView();


        initData();

        initEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==1){

            getData();

        }
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
            public void convert(GoodsViewHolder viewHolder, AreaListBean.Data data, final int position) {

                TextView tv_goods_area_name=viewHolder.getViewById(R.id.tv_goods_area_name);

                TextView tv_goods_area_tel= viewHolder.getViewById(R.id.tv_goods_area_tel);

                TextView tv_goods_area_address=viewHolder.getViewById(R.id.tv_goods_area_address);

              final  TextView tv_goods_area_delete = viewHolder.getViewById(R.id.tv_goods_area_delete);

                tv_goods_area_delete.setTag(position);

               TextView tv_goods_area_modify =  viewHolder.getViewById(R.id.tv_goods_area_modify);

                tv_goods_area_modify.setTag(position);

               final  CheckBox ck_goods_area_check =  viewHolder.getViewById(R.id.ck_goods_area_check);

                ck_goods_area_check.setTag(position);

                ckSet.add(ck_goods_area_check);

                //ckList.add(ck_goods_area_check);

                //Log.i(TAG,"ckList"+ckList);

                ck_goods_area_check.setChecked(checkStatusMap.get((int)ck_goods_area_check.getTag()));

                tv_goods_area_name.setText(data.consignee);
                tv_goods_area_tel.setText(data.tel);
                tv_goods_area_address.setText(data.address);


                ck_goods_area_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        if(isChecked&&position==(int)ck_goods_area_check.getTag()){

                            //相当于修改成默认地址

                            //把这个页面关掉

                            //点击其他checkBox弹起

                            checkFlag=(int)ck_goods_area_check.getTag();

                            checkStatusMap.put((int)ck_goods_area_check.getTag(),true);

                            ckFlag=ck_goods_area_check;

                            for(Map.Entry<Integer,Boolean> m :checkStatusMap.entrySet()){

                                    if(m.getKey()!=checkFlag){


                                        checkStatusMap.put(m.getKey(),false);
                                    }


                            }

                            for(CheckBox checkBox:ckSet){

                              if(checkBox!=ckFlag){

                                  checkBox.setChecked(false);

                              }

                            }

                            Log.i(TAG,"checkStatusMap"+checkStatusMap);


                            Intent intent =new Intent();

                            Bundle bundle =new Bundle();

                            bundle.putString("name",areaList.get((int)ck_goods_area_check.getTag()).consignee);

                            bundle.putString("tel",areaList.get((int)ck_goods_area_check.getTag()).mobile);

                            bundle.putString("address",areaList.get((int)ck_goods_area_check.getTag()).address);
                            intent.putExtra("bundle",bundle);

                            Log.i(TAG,areaList.get((int)ck_goods_area_check.getTag()).consignee);

                            setResult(2,intent);





                        }else if(!isChecked&&position==(int)ck_goods_area_check.getTag()){

                            //不做任何操作


                            checkStatusMap.put((int)ck_goods_area_check.getTag(),false);

                            Log.i(TAG,"checkStatusMap"+checkStatusMap);


                        }




                    }
                });




                tv_goods_area_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //http://app.linchom.com/appapi.php?act=drop_consignee&address_id=28&user_id=12


                        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

                        requestParams.addBodyParameter("act","drop_consignee");

                        requestParams.addBodyParameter("user_id",userId+"");

                        String addressId = areaList.get((int)tv_goods_area_delete.getTag()).address_id;

                        requestParams.addBodyParameter("address_id",addressId+"");

                        x.http().post(requestParams, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {


                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();

                                getData();


                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                                Toast.makeText(getApplicationContext(),"删除失败",Toast.LENGTH_SHORT).show();

                                getData();


                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });







                    }
                });


            }
        };

        lv_goods_area_list.setAdapter(goodsCommonAdapter);

        getData();





        btn_goods_area_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoodsAreaActivity.this,AddaddressActivity.class);




                startActivityForResult(intent,1);

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

                areaList.clear();

                areaList.addAll(areaListBean.data);

                for(int i= 0;i<areaList.size();i++){

                    checkStatusMap.put(i,false);

                }

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
