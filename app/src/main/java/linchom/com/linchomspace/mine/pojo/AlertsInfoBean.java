package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/26.
 */
public class AlertsInfoBean {

    public String result;
    public String msg;

    public AlertsInfoBean(String result, String msg, String url, DataBe data) {
        this.result = result;
        this.msg = msg;
        this.url = url;
        this.data = data;
    }

    @Override
    public String toString() {
        return "AlertsInfoBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';

    }

    public String url;
    public DataBe data ;

    public class DataBe{
        public String total_pages ;
        public String total ;

        public DataBe(String total_pages, String total, String pagesize, List<AItems> items) {
            this.total_pages = total_pages;
            this.total = total;
            this.pagesize = pagesize;
            this.items = items;
        }

        @Override
        public String toString() {
            return "DataBe{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", pagesize='" + pagesize + '\'' +
                    ", items=" + items +
                    '}';
        }

        public String pagesize ;
        public List<AItems> items;

        public class AItems{

            public  String id;

            public AItems(String id, String content) {
                this.id = id;
                this.content = content;
            }

            @Override
            public String toString() {
                return "AItems{" +
                        "id='" + id + '\'' +
                        ", content='" + content + '\'' +
                        '}';

            }

            public String  content;

        }



                 }
}
