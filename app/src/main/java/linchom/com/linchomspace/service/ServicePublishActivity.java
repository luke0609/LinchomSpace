package linchom.com.linchomspace.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.service.adapter.RegionAdapter;
import linchom.com.linchomspace.service.pojo.IpBean;
import linchom.com.linchomspace.service.pojo.RregionBean;

public class ServicePublishActivity extends AppCompatActivity implements View.OnClickListener{
    Map<String,String> region = new HashMap<String,String>();
    public List<RregionBean.DataBean> provincelist;
    public List<RregionBean.DataBean> citylist;
    public List<RregionBean.DataBean> districtlist;
    private Spinner sp_region;
    private Spinner sp_city;
    private Spinner sp_district;

    private String ipaddress=null;

    private String province_id=null;
    private String city_id=null;
    private String  district_id=null;
    private String cardNumber;
    private  Spinner sp_topic;


    private RadioGroup rg_category;
    private RadioGroup rg_release;

    private String service_type="0";
    private String category_id;
    private String release="1";
    private EditText et_title;
    private EditText add_content;
    private EditText et_phonenum;
    private EditText et_address_ex;

    private TextView tv_post;
    private ImageView iv_back;
    private ImageView iv_goback;
    private TextView tv_next;
    private RelativeLayout rl_2_1;
    private RelativeLayout rl_2_2;
    private String userId="";
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_publish);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userId = shared_prefs.getString("userId","");
        username = shared_prefs.getString("username","");

        initMap();
        initView();
        getIp();
    }

    private  void initMap(){
        region.put("智能居家","1");
        region.put("门窗安装","2");
        region.put("水暖","3");
        region.put("电工照明","4");
        region.put("空调电器","5");
        region.put("开孔","6");
        region.put("地板","7");
        region.put("墙壁","8");
        region.put("吊顶","9");
        region.put("维修","10");
        region.put("保洁","11");
        region.put("搬运","12");
        region.put("其他","13");
        sp_topic = ((Spinner) findViewById(R.id.sp_topic));

        final ArrayAdapter<CharSequence> adapterspinner1 = ArrayAdapter
                .createFromResource(this, R.array.main_service_menu,
                      R.layout.spinner_item);
        sp_topic.setAdapter(adapterspinner1);
        sp_topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardNumber = sp_topic.getSelectedItem().toString();
                System.out.println(cardNumber+"2");
                category_id = (String)region.get(cardNumber);
                System.out.println(cardNumber+"===="+category_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        sp_region = ((Spinner) findViewById(R.id.sp_region));
        sp_city= ((Spinner) findViewById(R.id.sp_city));
        sp_district = ((Spinner) findViewById(R.id.sp_district));
        citylist=new ArrayList<RregionBean.DataBean>();
        districtlist=new ArrayList<RregionBean.DataBean>();
        provincelist=new ArrayList<RregionBean.DataBean>();

        clearProvince();
        clearCity();
        clearDistrict();
        getProvince();



        rg_category = ((RadioGroup) findViewById(R.id.rg_category));
        rg_release = ((RadioGroup) findViewById(R.id.rg_release));
        rg_category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbn_require) {
                    service_type="0";
                } else {
                    service_type="1";
                }
                System.out.println("service_type"+service_type);
            }
        });
        rg_release.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbn_person) {
                    release="1";
                } else {
                    release="0";
                }
                System.out.println("release"+release);
            }
        });

        et_title = ((EditText) findViewById(R.id.et_title));
        add_content = ((EditText) findViewById(R.id.add_content));
        et_phonenum = ((EditText) findViewById(R.id.et_phonenum));
        et_address_ex = ((EditText) findViewById(R.id.et_address_ex));

        tv_post = ((TextView) findViewById(R.id.tv_post));
        tv_post.setOnClickListener(this);
        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_back.setOnClickListener(this);
        iv_goback = ((ImageView) findViewById(R.id.iv_goback));
        iv_goback.setOnClickListener(this);
        tv_next = ((TextView) findViewById(R.id.tv_next));
        tv_next.setOnClickListener(this);
        rl_2_1 = ((RelativeLayout) findViewById(R.id.rl_2_1));
        rl_2_2 = ((RelativeLayout) findViewById(R.id.rl_2_2));

        sp_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RregionBean.DataBean selectItem =(RregionBean.DataBean)parent.getItemAtPosition(position);
                System.out.println(selectItem.getRegion_id());

                clearDistrict();

                clearCity();

                province_id=selectItem.getRegion_id();

                if ("1".equals(province_id))
                {
                    clearCity();
                    clearDistrict();
                }else{
                    System.out.println("获得市");
                    getCity();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RregionBean.DataBean selectItem =(RregionBean.DataBean)parent.getItemAtPosition(position);
                System.out.println(selectItem.getRegion_id());
                city_id=selectItem.getRegion_id();
                if(!city_id.equals("1"))
                {
                     getDistrict();}
                else {
                    clearDistrict();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RregionBean.DataBean selectItem =(RregionBean.DataBean)parent.getItemAtPosition(position);
                System.out.println(selectItem.getRegion_id());
                district_id=selectItem.getRegion_id();
                System.out.println(province_id+"_"+city_id+"_"+district_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getProvince() {
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "get_regions");
        params.addQueryStringParameter("region_type", 1 + "");
        params.addQueryStringParameter("parent_id", 1 + "");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                RregionBean bean = gson.fromJson(result, RregionBean.class);
                RregionBean.DataBean tip = new RregionBean.DataBean("省", "1");

                provincelist.clear();
                provincelist.add(tip);
                provincelist.addAll(bean.getData());

                RegionAdapter regionAdapter = new RegionAdapter(getApplicationContext(), provincelist);
                sp_region.setAdapter(regionAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("省err");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void getCity() {
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act","get_regions");
        params.addQueryStringParameter("region_type",2+"");
        params.addQueryStringParameter("parent_id",province_id+"");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                RregionBean bean=gson.fromJson(result,RregionBean.class);
                RregionBean.DataBean tip= new RregionBean.DataBean("市","1");

                citylist.clear();
                citylist.add(tip);
                citylist.addAll(bean.getData());
                RegionAdapter regionAdapter=new RegionAdapter(getApplicationContext(),citylist);
                sp_city.setAdapter(regionAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("市err");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void getDistrict() {
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act","get_regions");
        params.addQueryStringParameter("region_type",3+"");
        params.addQueryStringParameter("parent_id",city_id+"");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                RregionBean bean=gson.fromJson(result,RregionBean.class);
                RregionBean.DataBean tip= new RregionBean.DataBean("县/区","1");

                districtlist.clear();
                districtlist.add(tip);
                districtlist.addAll(bean.getData());
                RegionAdapter regionAdapter=new RegionAdapter(getApplicationContext(),districtlist);
                sp_district.setAdapter(regionAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("县err");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void clearProvince(){
        RregionBean.DataBean tip = new RregionBean.DataBean("省", "1");
        provincelist.clear();
        provincelist.add(tip);
        RegionAdapter regionAdapter = new RegionAdapter(getApplicationContext(), provincelist);
        sp_region.setAdapter(regionAdapter);
    }
    private void clearCity(){
       RregionBean.DataBean tip2= new RregionBean.DataBean("市","1");
       citylist.clear();
       citylist.add(tip2);
       RegionAdapter regionAdapter2=new RegionAdapter(getApplicationContext(),citylist);
       sp_city.setAdapter(regionAdapter2);
   }
    private void clearDistrict(){
        RregionBean.DataBean tip3= new RregionBean.DataBean("县/区","1");
        districtlist.clear();
        districtlist.add(tip3);
        RegionAdapter regionAdapter3=new RegionAdapter(getApplicationContext(),districtlist);
        sp_district.setAdapter(regionAdapter3);
    }

    private void getIp() {
        // http://ip.taobao.com/service/getIpInfo2.php?ip=myip

        RequestParams params = new RequestParams("http://ip.taobao.com/service/getIpInfo2.php");
        params.addQueryStringParameter("ip", "myip");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IpBean bean = gson.fromJson(result, IpBean.class);
                String  ipAddress = bean.getData().getIp();
                ipaddress=ipAddress;
                System.out.println(ipAddress);

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

    private void doPublish() {
        //et_title
//        add_content
//                et_phonenum
//                        et_address_ex

        String title= et_title.getText().toString();
        String content=add_content.getText().toString();
        String phonenum=et_phonenum.getText().toString();
        String address_ex=et_address_ex.getText().toString();

        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "add_demand_services");
        params.addQueryStringParameter("category_id",category_id);
        params.addQueryStringParameter("user_id", userId);
        params.addQueryStringParameter("service_type",service_type);
        params.addQueryStringParameter("content",content);
        params.addQueryStringParameter("release",release);
        params.addQueryStringParameter("title",title);
        params.addQueryStringParameter("mobile",phonenum);
        params.addQueryStringParameter("province",province_id);
        params.addQueryStringParameter("city",city_id);
        params.addQueryStringParameter("district",district_id);
        params.addQueryStringParameter("address",address_ex);
       // params.addQueryStringParameter("photo", 135 + "");
        params.addQueryStringParameter("ip",ipaddress);
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(ServicePublishActivity.this, "发布完成", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onClick(View v) {
        //点击按钮时，表示选中不同的项
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:

                if(et_phonenum.getText().toString().length()==0
                        ||et_address_ex.getText().toString().length()==0
                        ||province_id.equals("1")
                        ||city_id.equals("1")
                        ){

                    Toast.makeText(getApplicationContext(),"请完善信息",Toast.LENGTH_SHORT).show();


                }else{

//                    Toast.makeText(getApplicationContext(),category_id
//                            +service_type
//                            +add_content.getText().toString()
//                            +et_title.getText().toString()
//                            +et_phonenum.getText().toString()
//                    +et_address_ex.getText().toString()
//                    +release
//                    +province_id
//                    +city_id
//                    +"区"+district_id,Toast.LENGTH_SHORT).show();

                    if(district_id.equals("1")){

                        district_id="";

                    }

                    doPublish();



                }


                break;
            case R.id.iv_goback:
                rl_2_2.setVisibility(View.GONE);
                rl_2_1.setVisibility(View.VISIBLE);
                iv_goback.setVisibility(View.GONE);
                iv_back.setVisibility(View.VISIBLE);
                tv_next.setVisibility(View.VISIBLE);
                tv_post.setVisibility(View.GONE);
                break;
            case R.id.tv_next:


                if(category_id==""||
                        category_id==null||
                        et_title.getText().toString().length()==0||
                        add_content.getText().toString().length()==0){

                    Toast.makeText(getApplicationContext(),"请完善信息",Toast.LENGTH_SHORT).show();
                }else {

                    rl_2_1.setVisibility(View.GONE);
                    rl_2_2.setVisibility(View.VISIBLE);
                    iv_back.setVisibility(View.GONE);
                    iv_goback.setVisibility(View.VISIBLE);
                    tv_post.setVisibility(View.VISIBLE);
                    tv_next.setVisibility(View.GONE);


                }




                break;
        }
    }

}
