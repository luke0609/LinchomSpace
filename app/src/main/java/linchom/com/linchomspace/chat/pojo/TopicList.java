package linchom.com.linchomspace.chat.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/18.
 */

public class TopicList implements Serializable{

    /**
     * result : 0
     * data : {"total_pages":1,"total":"3","items":[{"topic_id":"9","topic_category":"12312","topic_category_id":"1","topic_name":"你好","communication_title":"你好","add_time":"1476762585","user_name":"劳保","user_id":"135"}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 1
     * total : 3
     * items : [{"topic_id":"9","topic_category":"12312","topic_category_id":"1","topic_name":"你好","communication_title":"你好","add_time":"1476762585","user_name":"劳保","user_id":"135"}]
     * pagesize : 10
     */

    private DataBean data;
    private String msg;
    private String url;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean implements Serializable{
        private int total_pages;
        private String total;
        private int pagesize;
        /**
         * topic_id : 9
         * topic_category : 12312
         * topic_category_id : 1
         * topic_name : 你好
         * communication_title : 你好
         * add_time : 1476762585
         * user_name : 劳保
         * user_id : 135
         */

        private List<ItemsBean> items;

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements Serializable {
            @Override
            public String toString() {
                return "ItemsBean{" +
                        "topic_id='" + topic_id + '\'' +
                        ", topic_category='" + topic_category + '\'' +
                        ", topic_category_id='" + topic_category_id + '\'' +
                        ", topic_name='" + topic_name + '\'' +
                        ", communication_title='" + communication_title + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", user_name='" + user_name + '\'' +
                        ", user_id='" + user_id + '\'' +
                        '}';
            }

            private String topic_id;
            private String topic_category;
            private String topic_category_id;
            private String topic_name;
            private String communication_title;
            private String add_time;
            private String user_name;
            private String user_id;

            public String getTopic_id() {
                return topic_id;
            }

            public void setTopic_id(String topic_id) {
                this.topic_id = topic_id;
            }

            public String getTopic_category() {
                return topic_category;
            }

            public void setTopic_category(String topic_category) {
                this.topic_category = topic_category;
            }

            public String getTopic_category_id() {
                return topic_category_id;
            }

            public void setTopic_category_id(String topic_category_id) {
                this.topic_category_id = topic_category_id;
            }

            public String getTopic_name() {
                return topic_name;
            }

            public void setTopic_name(String topic_name) {
                this.topic_name = topic_name;
            }

            public String getCommunication_title() {
                return communication_title;
            }

            public void setCommunication_title(String communication_title) {
                this.communication_title = communication_title;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
