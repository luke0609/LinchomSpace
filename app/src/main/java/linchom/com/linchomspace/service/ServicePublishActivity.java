package linchom.com.linchomspace.service;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.service.adapter.RegionAdapter;
import linchom.com.linchomspace.service.pojo.RregionBean;

import static linchom.com.linchomspace.R.id.sp_city;

public class ServicePublishActivity extends AppCompatActivity {
    public List<RregionBean.DataBean> provincelist;
    public List<RregionBean.DataBean> citylist;
    public List<RregionBean.DataBean> districtlist;
    private Spinner sp_region;
    private Spinner sp_city;

    private Spinner sp_district;

    private String province_id=null;
    private String city_id=null;
    private String  district_id=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_publish);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));
        initView();
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

}
