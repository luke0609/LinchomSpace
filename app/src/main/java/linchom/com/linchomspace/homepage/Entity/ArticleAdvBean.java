package linchom.com.linchomspace.homepage.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class ArticleAdvBean {


    /**
     * result : 0
     * data : [{"ad_link":"http://www.linchom.com/goods.php?id=255","position_name":"app资讯文章末尾广告","position_id":"4","ad_width":"630","ad_height":"270","ad_code":"1477660626477714658.jpg","position_desc":"app资讯文章末尾广告","img_url":"http://app.linchom.com/data/afficheimg/1477660626477714658.jpg","goods_id":"255"}]
     * msg :
     * url :
     */

    private String result;
    private String msg;
    private String url;
    /**
     * ad_link : http://www.linchom.com/goods.php?id=255
     * position_name : app资讯文章末尾广告
     * position_id : 4
     * ad_width : 630
     * ad_height : 270
     * ad_code : 1477660626477714658.jpg
     * position_desc : app资讯文章末尾广告
     * img_url : http://app.linchom.com/data/afficheimg/1477660626477714658.jpg
     * goods_id : 255
     */

    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String ad_link;
        private String position_name;
        private String position_id;
        private String ad_width;
        private String ad_height;
        private String ad_code;
        private String position_desc;
        private String img_url;
        private String goods_id;

        public String getAd_link() {
            return ad_link;
        }

        public void setAd_link(String ad_link) {
            this.ad_link = ad_link;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }

        public String getAd_width() {
            return ad_width;
        }

        public void setAd_width(String ad_width) {
            this.ad_width = ad_width;
        }

        public String getAd_height() {
            return ad_height;
        }

        public void setAd_height(String ad_height) {
            this.ad_height = ad_height;
        }

        public String getAd_code() {
            return ad_code;
        }

        public void setAd_code(String ad_code) {
            this.ad_code = ad_code;
        }

        public String getPosition_desc() {
            return position_desc;
        }

        public void setPosition_desc(String position_desc) {
            this.position_desc = position_desc;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }
    }
}
