package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/27.
 */
public class MyDiscloseInfoBean {

    public  String result;
    public  Databean data;
    public  String msg;
    public  String url;

    @Override
    public String toString() {
        return "MyDiscloseInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public class Databean{

        public String total_pages;
        public String total;
        public List<DItems> items;
        public String pagesize;

        @Override
        public String toString() {
            return "Databean{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", items=" + items +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public class DItems{

        public String title;
        public String content;
        public String add_time;
        public String cat_id;
        public String author;

            @Override
            public String toString() {
                return "DItems{" +
                        "title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", author='" + author + '\'' +
                        '}';
            }
        }

    }

}
