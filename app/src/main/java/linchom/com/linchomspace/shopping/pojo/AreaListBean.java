package linchom.com.linchomspace.shopping.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class AreaListBean implements Serializable{



    public String result;

    public List<Data> data;

    public String msg;

    public String url;

    public class Data implements Serializable{


           public     String address_id;
           public     String address_name;
           public     String user_id;
           public     String consignee;
           public     String email;
           public     String country;
           public     String province;
           public     String city;
           public     String district;
           public     String address;
           public     String zipcode;
           public     String tel;
           public     String mobile;
           public     String sign_building;
           public     String best_time;


        public Data(String address_id, String address_name, String user_id, String consignee, String email, String country, String province, String city, String district, String address, String zipcode, String tel, String mobile, String sign_building, String best_time) {
            this.address_id = address_id;
            this.address_name = address_name;
            this.user_id = user_id;
            this.consignee = consignee;
            this.email = email;
            this.country = country;
            this.province = province;
            this.city = city;
            this.district = district;
            this.address = address;
            this.zipcode = zipcode;
            this.tel = tel;
            this.mobile = mobile;
            this.sign_building = sign_building;
            this.best_time = best_time;
        }
    }


}
