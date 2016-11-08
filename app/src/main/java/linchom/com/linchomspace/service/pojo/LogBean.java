package linchom.com.linchomspace.service.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public class LogBean {
    public String result;


    public  Data data;

    public String msg;

    public String url;


    public class Data{

        public String total_pages;

        public String total;


        public List<Items> items;

        public String pagesize;





    }


    public class Items{

              public String   id;
              public String   category_id;
              public String   content;
              public String   add_time;
              public String   user_id;
              public String   is_show;
              public String   title;

              public String     photo;
              public String   category_name;


    }




}
