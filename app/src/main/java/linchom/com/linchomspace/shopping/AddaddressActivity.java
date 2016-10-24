package linchom.com.linchomspace.shopping;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsCityBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class AddaddressActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "AddaddressActivity";

    private List<GoodsCityBean.Data> provinceList =new ArrayList<GoodsCityBean.Data>();
    private List<GoodsCityBean.Data> cityList =new ArrayList<GoodsCityBean.Data>();
    private List<GoodsCityBean.Data> areaList =new ArrayList<GoodsCityBean.Data>();
    GoodsCommonAdapter<GoodsCityBean.Data> goodsCommonAdapter;

    GoodsCommonAdapter<GoodsCityBean.Data> cityCommomAdapter;

    GoodsCommonAdapter<GoodsCityBean.Data> areaCommomAdapter;

    private TextView tv_goods_province;

    private TextView tv_goods_city;
    private TextView tv_goods_area;


    private String provinceId =null;

    private String cityId =null;

    private String areaId=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        tv_goods_province = ((TextView) findViewById(R.id.tv_goods_province));

        tv_goods_city = ((TextView) findViewById(R.id.tv_goods_city));
        tv_goods_area = ((TextView) findViewById(R.id.tv_goods_area));

    }

    private void initData() {





    }

    private void initEvent() {

        tv_goods_province.setOnClickListener(this);
        tv_goods_city.setOnClickListener(this);
        tv_goods_area.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_goods_province:
                provinceId =null;
                cityId =null;
                areaId=null;

                showProvincePop(v);

                tv_goods_city.setText("请选择市");
                tv_goods_area.setText("请选择区");


                break;

            case R.id.tv_goods_city:

                if(provinceId==null||provinceId==""){

                    Toast.makeText(getApplicationContext(),"请先选择省份",Toast.LENGTH_SHORT).show();
                }else{

                    cityId =null;
                    areaId=null;

                    tv_goods_area.setText("请选择区");

                    showCityPop(v);



                }


                break;
            case R.id.tv_goods_area:

                if(cityId==null||cityId==""){

                    Toast.makeText(getApplicationContext(),"请先选择市",Toast.LENGTH_SHORT).show();
                }else{
                    areaId=null;

                    showAreaPop(v);



                }


                break;


        }

    }

    private void showProvincePop(View view) {


        View contentView = LayoutInflater.from(this).inflate(
                R.layout.goods_ctiy_layout, null);

        ListView  goods_city_list = ((ListView) contentView.findViewById(R.id.goods_city_list));

        goodsCommonAdapter =new GoodsCommonAdapter<GoodsCityBean.Data>(getApplicationContext(),provinceList,R.layout.city_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCityBean.Data data, int position) {

                TextView tv_city_item=     ((TextView) viewHolder.getViewById(R.id.tv_city_item));

                tv_city_item.setText(data.region_name);


            }
        };

        goods_city_list.setAdapter(goodsCommonAdapter);

        getProvinceData();

        goods_city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //postion 开始
                Toast.makeText(getApplicationContext(),provinceList.get(position).region_name,Toast.LENGTH_SHORT).show();

                provinceId = provinceList.get(position).region_id;

                tv_goods_province.setText(provinceList.get(position).region_name);



            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setWidth(200);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.ic_launcher));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);


    }


    public void showCityPop(View view){
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.goods_ctiy_layout, null);

        ListView  goods_city_list = ((ListView) contentView.findViewById(R.id.goods_city_list));

        cityCommomAdapter =new GoodsCommonAdapter<GoodsCityBean.Data>(getApplicationContext(),cityList,R.layout.city_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCityBean.Data data, int position) {

                TextView tv_city_item= ((TextView) viewHolder.getViewById(R.id.tv_city_item));

                tv_city_item.setText(data.region_name);

            }
        };

        goods_city_list.setAdapter(cityCommomAdapter);

        getCityData();

        goods_city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //postion 开始
                Toast.makeText(getApplicationContext(),cityList.get(position).region_name,Toast.LENGTH_SHORT).show();

                cityId = cityList.get(position).region_id;

                tv_goods_city.setText(cityList.get(position).region_name);



            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setWidth(200);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.ic_launcher));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);



    }


    public void showAreaPop(View view){



        View contentView = LayoutInflater.from(this).inflate(
                R.layout.goods_ctiy_layout, null);

        ListView  goods_city_list = ((ListView) contentView.findViewById(R.id.goods_city_list));

        areaCommomAdapter =new GoodsCommonAdapter<GoodsCityBean.Data>(getApplicationContext(),areaList,R.layout.city_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsCityBean.Data data, int position) {

                TextView tv_city_item= ((TextView) viewHolder.getViewById(R.id.tv_city_item));

                tv_city_item.setText(data.region_name);

            }
        };

        goods_city_list.setAdapter(areaCommomAdapter);

        getAreaData();

        goods_city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //postion 开始
                Toast.makeText(getApplicationContext(),areaList.get(position).region_name,Toast.LENGTH_SHORT).show();

                areaId = areaList.get(position).region_id;

                tv_goods_area.setText(areaList.get(position).region_name);



            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setWidth(200);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.ic_launcher));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);




    }

    private void getProvinceData() {

        provinceList.clear();
        cityList.clear();
        areaList.clear();

        RequestParams requestParams  =new RequestParams(GoodsHttpUtils.SHOPURL);
        requestParams.addBodyParameter("act","get_regions");
        requestParams.addBodyParameter("region_type","1");
        requestParams.addBodyParameter("parent_id","1");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson =new Gson();

                GoodsCityBean goodsCityBean =   gson.fromJson(result,GoodsCityBean.class);
                provinceList.addAll( goodsCityBean.data);
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


    private void getCityData() {

        provinceList.clear();
        cityList.clear();
        areaList.clear();

        RequestParams requestParams  =new RequestParams(GoodsHttpUtils.SHOPURL);
        requestParams.addBodyParameter("act","get_regions");
        requestParams.addBodyParameter("region_type","2");
        requestParams.addBodyParameter("parent_id",provinceId);


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson =new Gson();

                GoodsCityBean goodsCityBean =   gson.fromJson(result,GoodsCityBean.class);
                cityList.addAll( goodsCityBean.data);
                cityCommomAdapter.notifyDataSetChanged();



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



    private void getAreaData() {

        provinceList.clear();
        cityList.clear();
        areaList.clear();

        RequestParams requestParams  =new RequestParams(GoodsHttpUtils.SHOPURL);
        requestParams.addBodyParameter("act","get_regions");
        requestParams.addBodyParameter("region_type","3");
        requestParams.addBodyParameter("parent_id",cityId);


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson =new Gson();

                GoodsCityBean goodsCityBean =   gson.fromJson(result,GoodsCityBean.class);
                areaList.addAll( goodsCityBean.data);




                areaCommomAdapter.notifyDataSetChanged();



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
