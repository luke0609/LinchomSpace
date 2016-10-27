package linchom.com.linchomspace.shopping.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/27.
 */

public class OrderSubmitBean implements Serializable{

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

    public OrderSubmitBean(String rec_id, String user_id, String goods_id, String goods_name, String goods_sn, String goods_number, String market_price, String goods_price, String goods_attr, String is_real, String extension_code, String parent_id, String is_gift, String is_shipping, String subtotal, String formated_market_price, String formated_goods_price, String formated_subtotal) {
        this.rec_id = rec_id;
        this.user_id = user_id;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_sn = goods_sn;
        this.goods_number = goods_number;
        this.market_price = market_price;
        this.goods_price = goods_price;
        this.goods_attr = goods_attr;
        this.is_real = is_real;
        this.extension_code = extension_code;
        this.parent_id = parent_id;
        this.is_gift = is_gift;
        this.is_shipping = is_shipping;
        this.subtotal = subtotal;
        this.formated_market_price = formated_market_price;
        this.formated_goods_price = formated_goods_price;
        this.formated_subtotal = formated_subtotal;
    }
}
