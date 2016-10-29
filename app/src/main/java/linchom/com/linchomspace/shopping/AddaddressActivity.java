package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

    private String userId="12";


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


    private String provinceId ="";

    private String cityId ="";

    private String areaId="";
    private EditText et_goods_addaddress_name;
    private EditText et_goods_addaddress_tel;
    private EditText et_goods_cart_detailaddress;
    private EditText et_goods_cart_youbian;
    private Button btn_goods_area_save;


    private String name;
    private String tel;

    private String detailAddress;

    private String youbian;

     private String intentName ;
     private String intentAddress;
     private String intentTel ;
     private String intentYouBian;
     private String type;
     private String intentaddressId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);

        Intent intent =getIntent();

        Bundle bundle =intent.getBundleExtra("bundle");

        //"name"
        //"address"
        //"tel"
        //"youbian"

        //bundle.putString("type","add");


        type=bundle.getString("type");


        intentName = bundle.getString("name");

         intentAddress = bundle.getString("address");

         intentTel =  bundle.getString("tel");

         intentYouBian =  bundle.getString("youbian");

        //addressId=

        intentaddressId = bundle.getString("addressId");

        Log.i(TAG,"result"+intentName+intentAddress+intentTel+intentYouBian);


        initView();
        initData();
        initEvent();

    }

    private void initView() {
        tv_goods_province = ((TextView) findViewById(R.id.tv_goods_province));

        tv_goods_city = ((TextView) findViewById(R.id.tv_goods_city));
        tv_goods_area = ((TextView) findViewById(R.id.tv_goods_area));

        et_goods_addaddress_name = ((EditText) findViewById(R.id.et_goods_addaddress_name));

        et_goods_addaddress_tel = ((EditText) findViewById(R.id.et_goods_addaddress_tel));

        et_goods_cart_detailaddress = ((EditText) findViewById(R.id.et_goods_cart_detailaddress));

        et_goods_cart_youbian = ((EditText) findViewById(R.id.et_goods_cart_youbian));

        btn_goods_area_save = ((Button) findViewById(R.id.btn_goods_area_save));

    }

    private void initData() {
        //et_goods_addaddress_name
        //et_goods_addaddress_tel
        //et_goods_cart_detailaddress
        //et_goods_cart_youbian
        if("add".equals(type)){

        }else{
            et_goods_addaddress_name.setText(intentName);

        }

        if("add".equals(type)){

        }else{
            et_goods_cart_detailaddress.setText(intentAddress);

        }

        if("add".equals(type)){

        }else{

            et_goods_addaddress_tel.setText(intentTel);
        }

        if("add".equals(type)){



        }else{

            et_goods_cart_youbian.setText(intentYouBian);


        }





    }

    private void initEvent() {

        tv_goods_province.setOnClickListener(this);
        tv_goods_city.setOnClickListener(this);
        tv_goods_area.setOnClickListener(this);

        btn_goods_area_save.setOnClickListener(this);



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

            case R.id.btn_goods_area_save:
                if("add".equals(type)){

                    saveAddress();


                }else{

                    modifyAddress();

                }



                break;


        }

    }

    private void modifyAddress() {

       // http://app.linchom.com/appapi.php?
        // act=update_consignee
        // &consignee=%E5%BC%A0%E6%99%93%E6%96%871
        // &province=2
        // &city=52
        // &district=503
        // &email=2070118814@qq.com
        // &address=%E5%8C%97%E4%BA%AC
        // &zipcode=10000
        // &mobile=18500551431
        // &address_id=27


        name=et_goods_addaddress_name.getText().toString();
        tel =et_goods_addaddress_tel.getText().toString();
        detailAddress =et_goods_cart_detailaddress.getText().toString();
        youbian=et_goods_cart_youbian.getText().toString();

        if(name==null||name==""||tel==null||tel==""||detailAddress==null||detailAddress==""||
                youbian==null||youbian==""||provinceId==null||provinceId==""||cityId==null||cityId==""){


            Toast.makeText(getApplicationContext(),"信息不能为空",Toast.LENGTH_SHORT).show();

        }else{

            //http://app.linchom.com/appapi.php?act=add_consignee&consignee=%E5%BC%A0%E6%99%93%E6%96%87&province=2&city=52&district=503&email=2070118814@qq.com&address=%E5%8C%97%E4%BA%AC&zipcode=10000&mobile=18500551431


            RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);


            requestParams.addBodyParameter("act","update_consignee");

            requestParams.addBodyParameter("user_id",userId);

            requestParams.addBodyParameter("consignee",name);

            requestParams.addBodyParameter("province",provinceId);


            requestParams.addBodyParameter("city",cityId);


            requestParams.addBodyParameter("district",areaId);

            requestParams.addBodyParameter("email","");


            requestParams.addBodyParameter("address",detailAddress);


            requestParams.addBodyParameter("zipcode",youbian);

            requestParams.addBodyParameter("mobile",tel);

            requestParams.addBodyParameter("address_id",intentaddressId);

            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Log.i(TAG,"result"+result);

                    Intent intent =new Intent();

                    setResult(2);

                    finish();

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

    private void saveAddress() {

        name=et_goods_addaddress_name.getText().toString();
        tel =et_goods_addaddress_tel.getText().toString();
        detailAddress =et_goods_cart_detailaddress.getText().toString();
        youbian=et_goods_cart_youbian.getText().toString();

        if(name==null||name==""||tel==null||tel==""||detailAddress==null||detailAddress==""||
                youbian==null||youbian==""||provinceId==null||provinceId==""||cityId==null||cityId==""){


            Toast.makeText(getApplicationContext(),"信息不能为空",Toast.LENGTH_SHORT).show();

        }else{

            //http://app.linchom.com/appapi.php?act=add_consignee&consignee=%E5%BC%A0%E6%99%93%E6%96%87&province=2&city=52&district=503&email=2070118814@qq.com&address=%E5%8C%97%E4%BA%AC&zipcode=10000&mobile=18500551431


            RequestParams requestParams = new RequestParams(GoodsHttpUtils.SHOPURL);


            requestParams.addBodyParameter("act","add_consignee");

            requestParams.addBodyParameter("user_id",userId);

            requestParams.addBodyParameter("consignee",name);

            requestParams.addBodyParameter("province",provinceId);


            requestParams.addBodyParameter("city",cityId);


            requestParams.addBodyParameter("district",areaId);

            requestParams.addBodyParameter("email","");


            requestParams.addBodyParameter("address",detailAddress);


            requestParams.addBodyParameter("zipcode",youbian);

            requestParams.addBodyParameter("mobile",tel);

            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Log.i(TAG,"result"+result);

                    Intent intent =new Intent();

                    setResult(1);

                    finish();

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
