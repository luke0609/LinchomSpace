package linchom.com.linchomspace.shopping.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */

public class GoodsCityBean {

    public String result;

    public List<Data> data;

    public String msg;

    public String url;

    public class Data{

        public String region_id;


        public String region_name;
        public Data(){


        }

        public Data(String region_id, String region_name) {
            this.region_id = region_id;
            this.region_name = region_name;
        }
    }




}
