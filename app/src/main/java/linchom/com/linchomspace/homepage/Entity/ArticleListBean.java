package linchom.com.linchomspace.homepage.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */

public class ArticleListBean {
    public String result;
    public Data   data;//不能定义成静态
    public String msg;
    public String url;


    public class Data{


        public List<Article_list> article_list;

        public MyFilter filter;

        public String record_count;
        public String page_count;

        public Data(List<Article_list> article_list, MyFilter filter, String record_count, String page_count) {
            this.article_list = article_list;
            this.filter = filter;
            this.record_count = record_count;
            this.page_count = page_count;
        }

        public List<Article_list> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<Article_list> article_list) {
            this.article_list = article_list;
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
    public class Article_list{
        public String article_id;
        public String cat_id;
        public String title;
        public  String article_pic;
        public String article_url;
        public String is_open;
        public String add_time;
        public String description;
        public String cat_name;
        public String date;
        public String photo;
        public String content_url;
        public String source;

        public Article_list(String article_id, String cat_id, String title, String article_pic, String article_url, String is_open, String add_time, String description, String cat_name, String date, String photo, String content_url) {
            this.article_id = article_id;
            this.cat_id = cat_id;
            this.title = title;
            this.article_pic = article_pic;
            this.article_url = article_url;
            this.is_open = is_open;
            this.add_time = add_time;
            this.description = description;
            this.cat_name = cat_name;
            this.date = date;
            this.photo = photo;
            this.content_url = content_url;
        }
    }

    public  class MyFilter{
       public String keyword;
        public String cat_id;
        public String sort_by ;
        public String sort_order ;
        public String record_count;
        public String page_size;
        public String page ;
        public String page_count;
        public String start;

        public MyFilter(String keyword, String start, String cat_id, String sort_by, String sort_order, String record_count, String page, String page_size, String page_count) {
            this.keyword = keyword;
            this.start = start;
            this.cat_id = cat_id;
            this.sort_by = sort_by;
            this.sort_order = sort_order;
            this.record_count = record_count;
            this.page = page;
            this.page_size = page_size;
            this.page_count = page_count;
        }
    }
}
