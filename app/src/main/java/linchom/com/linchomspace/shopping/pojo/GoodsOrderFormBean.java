package linchom.com.linchomspace.shopping.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class GoodsOrderFormBean implements Serializable{


    public String result;

    public Data data;

    public String msg;

    public String url;


    public class  Data implements Serializable{

        public String total_pages;

        public  String total;


        public List<OrderForm> items;

        public String pagesize;


    }




    public class OrderForm implements Serializable{


            public String order_id;
            public String order_sn;
            public String user_id;

            public String order_status;
            public String shipping_status;
            public String pay_status;
            public String confirm_time;
        public List<OrderInfo> order_goods;


    }

    public class OrderInfo implements Serializable{
        public String    goods_name;
        public String    order_id;
        public String    goods_id;
        public String    goods_sn;
        public String    goods_number;
        public String    goods_price;
        public String    total;
        public String    goodsnum;
        public String    goods_img;


    }





}
