package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/29.
 */
public class ArticleInFoBean {

    public String result;
    public String msg;
    public String url;
    public DataBean data;

    @Override
    public String toString() {
        return "ArticleInFoBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }

    public class DataBean{
    public String total_pages;
    public String total;
    public List<AItems> items;
    public String pagesize;

        @Override
        public String toString() {
            return "DataBean{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", items=" + items +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public class AItems{
            public String rec_id;
            public String user_id;
            public String article_id;
            public String cat_id;
            public String add_time;
            public String title;

            @Override
            public String toString() {
                return "AItems{" +
                        "rec_id='" + rec_id + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", article_id='" + article_id + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", author='" + author + '\'' +
                        ", author_email='" + author_email + '\'' +
                        ", keywords='" + keywords + '\'' +
                        ", article_type='" + article_type + '\'' +
                        ", is_open='" + is_open + '\'' +
                        ", file_url='" + file_url + '\'' +
                        ", link='" + link + '\'' +
                        ", description='" + description + '\'' +
                        ", mobile_article_id='" + mobile_article_id + '\'' +
                        ", article_pic='" + article_pic + '\'' +
                        ", cat_checkbox='" + cat_checkbox + '\'' +
                        ", article_url='" + article_url + '\'' +
                        ", article_source='" + article_source + '\'' +
                        ", status='" + status + '\'' +
                        ", photo='" + photo + '\'' +
                        ", content_url='" + content_url + '\'' +
                        '}';
            }

            public String content;
            public String author;
            public String author_email;
            public String keywords;
            public String article_type;
            public String is_open;
            public String file_url;
            public String link;
            public String description;
            public String mobile_article_id;
            public String article_pic;
            public String cat_checkbox;
            public String article_url;
            public String article_source;
            public String status;
            public String photo;
            public String content_url;

        }
}

}
