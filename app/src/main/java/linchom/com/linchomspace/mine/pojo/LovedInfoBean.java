package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/19.
 */
public class LovedInfoBean {
    public String result;
    public DataBean data;
    public String msg;
    public String url;

    @Override
    public String toString() {
        return "LovedInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public  class DataBean{

        public String total_pages;
        public List<Items> items;
        public String total;
        public String pagesize;

        @Override
        public String toString() {
            return "DataBean{" +
                    "total_pages='" + total_pages + '\'' +
                    ", items=" + items +
                    ", total='" + total + '\'' +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public  class Items{

            public String goods_id;
            public String add_time;
            public String shop_price;
            public String goods_img;
            public String goods_name;

            @Override
            public String toString() {
                return "Items{" +
                        "goods_id='" + goods_id + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", shop_price='" + shop_price + '\'' +
                        ", goods_img='" + goods_img + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        '}';
            }
        }

    }


}
