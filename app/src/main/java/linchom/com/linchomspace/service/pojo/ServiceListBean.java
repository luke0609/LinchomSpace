package linchom.com.linchomspace.service.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ServiceListBean {

    public String result;


    public Data data;


    public String msg;

    public String url;


    public class Data{

            public String total_pages;

            public String total;

            public List<Items> items;


            public String pagesize;





    }


    public class Items{

            public    String id;
            public    String category_id;
            public    String service_type;
            public    String content;
            public    String release;
            public    String mobile;
            public    String country;
            public    String province;
            public    String city;
            public    String district;
            public    String add_time;
            public    String address;
            public    String user_id;
            public    String ip;
            public    String photo;
            public    String status;
            public    String is_show;
            public    String title;
            public    String topic_category_name;
            public    String province_name;
            public    String city_name;
            public    String district_name;
            public    String addressinfo;



    }



}
