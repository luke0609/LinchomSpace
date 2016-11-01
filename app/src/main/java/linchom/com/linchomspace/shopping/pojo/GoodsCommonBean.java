package linchom.com.linchomspace.shopping.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class GoodsCommonBean {

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


                public String comment_id;
                public String comment_type;
                public String id_value;
                public String email;
                public String user_name;
                public String content;
                public String comment_rank;
                public String add_time;
                public String ip_address;
                public String status;
                public String parent_id;
                public String user_id;


    }

}
