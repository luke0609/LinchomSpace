package linchom.com.linchomspace.mine.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2016/10/27.
 */
public class MyDiscloseInfoBean implements Serializable{

    public  String result;
    public  Databean data;
    public  String msg;
    public  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyDiscloseInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public class Databean implements Serializable{

        public String total_pages;
        public String total;
        public List<DItems> items;
        public String pagesize;

        public String getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(String total_pages) {
            this.total_pages = total_pages;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<DItems> getItems() {
            return items;
        }

        public void setItems(List<DItems> items) {
            this.items = items;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        @Override
        public String toString() {
            return "Databean{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", items=" + items +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public class DItems implements Serializable{

        public String title;
        public String content;
        public String add_time;
        public String cat_id;
        public String photo;
        public String author;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            @Override
            public String toString() {
                return "DItems{" +
                        "title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", photo='" + photo + '\'' +
                        ", author='" + author + '\'' +
                        '}';
            }
        }

    }

}
