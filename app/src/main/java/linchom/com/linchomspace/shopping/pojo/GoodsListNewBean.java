package linchom.com.linchomspace.shopping.pojo;

import java.util.HashMap;
import java.util.Map;



public class GoodsListNewBean {

    public String result;

    public Data data;

    public  String msg;


    public String url;



    public class  Data{

        public String total_pages;

        public String total;

        public Items items;

        public String  pagesize;


    }


    public class  Items{

        public Map<String,GoodsMap> map =new HashMap<String,GoodsMap>();

    }


    public class GoodsMap{


        public String  goods_id;
        public String  goods_name;
        public String  cat_id;
        public String  name;
        public String  goods_brief;
        public String  goods_style_name;
        public String  market_price;
        public String  shop_price;
        public String  type;
        public String  promote_price;
        public String  goods_thumb;
        public String  goods_img;
        public String  url;
        public String   xs;
        public String  pl;
        public String  sc;




    }




}
