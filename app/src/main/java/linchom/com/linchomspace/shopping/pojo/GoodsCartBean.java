package linchom.com.linchomspace.shopping.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class GoodsCartBean {

    public String result;

    public List<Data> data;

    public String msg;

    public String url;

    public class Data{



                public String rec_id;
                public String user_id;
                public String goods_id;
                public String goods_name;
                public String goods_sn;
                public String goods_number;
                public String market_price;
                public String goods_price;
                public String goods_attr;
                public String is_real;
                public String extension_code;
                public String parent_id;
                public String is_gift;
                public String is_shipping;
                public String subtotal;
                public String formated_market_price;
                public String formated_goods_price;
                public String formated_subtotal;



    }
}
