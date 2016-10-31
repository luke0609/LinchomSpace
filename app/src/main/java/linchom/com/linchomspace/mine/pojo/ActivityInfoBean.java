package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/30.
 */
public class ActivityInfoBean {
    public String result;
    public String msg;
    public List<DataBean> data;
    public String url;



    public class DataBean{
        public String ad_code;
        public String position_name;
        public String ad_link;
        public String position_desc;
        public String img_url;
        public String goods_id;
        public String ad_width;
        public String ad_height;

        @Override
        public String toString() {
            return "DataBean{" +
                    "ad_code='" + ad_code + '\'' +
                    ", position_name='" + position_name + '\'' +
                    ", ad_link='" + ad_link + '\'' +
                    ", position_desc='" + position_desc + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", ad_width='" + ad_width + '\'' +
                    ", ad_height='" + ad_height + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ActivityInfoBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + data +
                ", url='" + url + '\'' +
                '}';
    }
}
