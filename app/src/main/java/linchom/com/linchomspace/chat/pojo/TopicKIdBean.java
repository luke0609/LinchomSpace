package linchom.com.linchomspace.chat.pojo;

import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/11/2.
 */

public class TopicKIdBean {
    /**
     * result : 0
     * data : {"total_pages":1,"total":"10","items":[{"id":"151","comments_id":"176","user_id":"135","content":"111111111111111","add_time":"1478061568","parent_comment_username":""},{"id":"146","comments_id":"176","user_id":"135","content":"你们好幸福","add_time":"1478009290","parent_comment_username":"张希"},{"id":"145","comments_id":"176","user_id":"135","content":"我们都会好想吃饭","add_time":"1478006393","parent_comment_username":"张希"},{"id":"144","comments_id":"176","user_id":"135","content":"我们都会好","add_time":"1478005714","parent_comment_username":"张希"},{"id":"143","comments_id":"176","user_id":"135","content":"大家好","add_time":"1478005395","parent_comment_username":"张希"},{"id":"142","comments_id":"176","user_id":"135","content":"你也好","add_time":"1478005192","parent_comment_username":"张希"},{"id":"139","comments_id":"176","user_id":"135","content":"你们好","add_time":"1477999092","parent_comment_username":"张希"},{"id":"138","comments_id":"176","user_id":"135","content":"今天晚上去happy","add_time":"1477998963","parent_comment_username":""},{"id":"137","comments_id":"176","user_id":"135","content":"我们都会有的","add_time":"1477998822","parent_comment_username":""},{"id":"136","comments_id":"176","user_id":"135","content":"回复@:好好好好好","add_time":"1477998433","parent_comment_username":""}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 1
     * total : 10
     * items : [{"id":"151","comments_id":"176","user_id":"135","content":"111111111111111","add_time":"1478061568","parent_comment_username":""},{"id":"146","comments_id":"176","user_id":"135","content":"你们好幸福","add_time":"1478009290","parent_comment_username":"张希"},{"id":"145","comments_id":"176","user_id":"135","content":"我们都会好想吃饭","add_time":"1478006393","parent_comment_username":"张希"},{"id":"144","comments_id":"176","user_id":"135","content":"我们都会好","add_time":"1478005714","parent_comment_username":"张希"},{"id":"143","comments_id":"176","user_id":"135","content":"大家好","add_time":"1478005395","parent_comment_username":"张希"},{"id":"142","comments_id":"176","user_id":"135","content":"你也好","add_time":"1478005192","parent_comment_username":"张希"},{"id":"139","comments_id":"176","user_id":"135","content":"你们好","add_time":"1477999092","parent_comment_username":"张希"},{"id":"138","comments_id":"176","user_id":"135","content":"今天晚上去happy","add_time":"1477998963","parent_comment_username":""},{"id":"137","comments_id":"176","user_id":"135","content":"我们都会有的","add_time":"1477998822","parent_comment_username":""},{"id":"136","comments_id":"176","user_id":"135","content":"回复@:好好好好好","add_time":"1477998433","parent_comment_username":""}]
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

    public static class DataBean {
        private int total_pages;
        private String total;
        private int pagesize;
        /**
         * id : 151
         * comments_id : 176
         * user_id : 135
         * content : 111111111111111
         * add_time : 1478061568
         * parent_comment_username :
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

        public static class ItemsBean {
            private String id;
            private String comments_id;
            private String user_id;
            private String content;
            private String add_time;
            private String parent_comment_username;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getComments_id() {
                return comments_id;
            }

            public void setComments_id(String comments_id) {
                this.comments_id = comments_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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

            public String getParent_comment_username() {
                return parent_comment_username;
            }

            public void setParent_comment_username(String parent_comment_username) {
                this.parent_comment_username = parent_comment_username;
            }
        }
    }
}
