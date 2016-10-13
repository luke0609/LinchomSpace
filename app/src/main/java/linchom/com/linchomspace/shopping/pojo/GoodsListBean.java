package linchom.com.linchomspace.shopping.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class GoodsListBean {




    public String result;
    public   Data data;//不能定义成静态
    public String msg;
    public String url;


    public   class  Data {


        public List<Goods> goods;

        public MyFilter filter;

        public String record_count;
        public String page_count;

        public  Data(List<Goods> goods, MyFilter filter, String record_count, String page_count) {
            this.goods = goods;
            this.filter = filter;
            this.record_count = record_count;
            this.page_count = page_count;
        }


        public List<Goods> getGoods() {
            return goods;
        }

        public void setGoods(List<Goods> goods) {
            this.goods = goods;
        }

        public MyFilter getFilter() {
            return filter;
        }

        public void setFilter(MyFilter filter) {
            this.filter = filter;
        }

        public String getRecord_count() {
            return record_count;
        }

        public void setRecord_count(String record_count) {
            this.record_count = record_count;
        }

        public String getPage_count() {
            return page_count;
        }

        public void setPage_count(String page_count) {
            this.page_count = page_count;
        }




    }

    public   class Goods {

        public String goods_id;
        public String goods_name;
        public String goods_type;
        public String goods_sn;
        public String shop_price;
        public String goods_thumb;
        public String tb_link;
        public String jd_link;
        public String is_on_sale;
        public String is_best;
        public String is_new;
        public String is_hot;
        public String sort_order;
        public String goods_number;
        public String integral;
        public String is_promote;

        public Goods(String goods_id, String goods_name, String goods_type, String goods_sn, String shop_price, String goods_thumb, String tb_link, String jd_link, String is_on_sale, String is_best, String is_new, String is_hot, String sort_order, String goods_number, String integral, String is_promote) {
            this.goods_id = goods_id;
            this.goods_name = goods_name;
            this.goods_type = goods_type;
            this.goods_sn = goods_sn;
            this.shop_price = shop_price;
            this.goods_thumb = goods_thumb;
            this.tb_link = tb_link;
            this.jd_link = jd_link;
            this.is_on_sale = is_on_sale;
            this.is_best = is_best;
            this.is_new = is_new;
            this.is_hot = is_hot;
            this.sort_order = sort_order;
            this.goods_number = goods_number;
            this.integral = integral;
            this.is_promote = is_promote;
        }




    }


    public class MyFilter {

        public String cat_id;
        public String intro_type;
        public String is_promote;
        public String stock_warning;
        public String brand_id;
        public String keyword;
        public String suppliers_id;
        public String is_on_sale;
        public String sort_by;
        public String sort_order;
        public String extension_code;
        public String is_delete;
        public String real_goods;
        public String record_count;
        public String page_size;
        public String page;
        public String page_count;
        public String start;


        public MyFilter(String cat_id, String intro_type, String is_promote, String stock_warning, String brand_id, String keyword, String suppliers_id, String is_on_sale, String sort_by, String sort_order, String extension_code, String is_delete, String real_goods, String record_count, String page_size, String page, String page_count, String start) {
            this.cat_id = cat_id;
            this.intro_type = intro_type;
            this.is_promote = is_promote;
            this.stock_warning = stock_warning;
            this.brand_id = brand_id;
            this.keyword = keyword;
            this.suppliers_id = suppliers_id;
            this.is_on_sale = is_on_sale;
            this.sort_by = sort_by;
            this.sort_order = sort_order;
            this.extension_code = extension_code;
            this.is_delete = is_delete;
            this.real_goods = real_goods;
            this.record_count = record_count;
            this.page_size = page_size;
            this.page = page;
            this.page_count = page_count;
            this.start = start;
        }




    }



}
