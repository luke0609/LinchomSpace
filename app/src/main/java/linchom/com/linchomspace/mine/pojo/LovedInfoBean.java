package linchom.com.linchomspace.mine.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/10/19.
 */
public class LovedInfoBean {

    public String result;
    public  String msg;
    public Data data;
    public String url;

 public class Data{

     public Items items;
     public String total_pages;
     public String total;
     public String  pagesize;

 }

    public class Items{

        public Map<String,GoodsMap> map =new HashMap<String,GoodsMap>();

    }
    public class GoodsMap{

        public String rec_id;
        public String is_attention;
        public String goods_id;
        public String goods_name;
        public String market_price;
        public  String shop_price;
        public String promote_price;
        public String url;

        @Override
        public String toString() {
            return "GoodsMap{" +
                    "rec_id='" + rec_id + '\'' +
                    ", is_attention='" + is_attention + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", market_price='" + market_price + '\'' +
                    ", shop_price='" + shop_price + '\'' +
                    ", promote_price='" + promote_price + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }


        //        "rec_id": "259609",
//                "is_attention": "0",
//                "goods_id": null,
//                "goods_name": null,
//                "market_price": "￥0元",
//                "shop_price": "￥0元",
//                "promote_price": "",
//                "url": false

    }
}