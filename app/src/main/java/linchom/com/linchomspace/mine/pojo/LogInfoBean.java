package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/22.
 */
public class LogInfoBean {

    public String result;
    public String msg;
    public String url;

    @Override
    public String toString() {
        return "LogInfoBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }

    public DataBean data;

    public class DataBean{

        public  String total_pages;
        public  String total;
        public  List<Litems> items;
        public  String pagesize;

        @Override
        public String toString() {
            return "DataBean{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", items=" + items +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public class Litems{
            public String id;
            public String category_id;
            public String content;
            public String add_time;
            public String is_show;
            public String user_id;
            public String title;
            public String photo;

            @Override
            public String toString() {
                return "Litems{" +
                        "id='" + id + '\'' +
                        ", category_id='" + category_id + '\'' +
                        ", content='" + content + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", is_show='" + is_show + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", title='" + title + '\'' +
                        ", photo='" + photo + '\'' +
                        '}';
            }
        }
    }

}

