package linchom.com.linchomspace.chat.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/20.
 */

public class TopicDetialBean {

    /**
     * result : 0
     * data : {"topic_id":"1","topic_category":"智能家居","topic_category_id":"1","topic_name":"我在北京有个别墅客户想安装智能家居，谁有好的品牌推荐？","communication_title":"我在北京有个别墅客户想安装智能家居，谁有好的品牌推荐？","add_time":"1476756498","user_name":"张晓文","user_id":"135","topic_comments":[{"id":"1","topic_id":"1","user_id":"135","content":"评论11111","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"2","topic_id":"1","user_id":"135","content":"评论222222","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"3","topic_id":"1","user_id":"135","content":"评论333333","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"4","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"5","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"6","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"47","topic_id":"1","user_id":"135","content":"12312","add_time":"1476945070","parent_comment_username":"","user_name":"张晓文111","user_photo":""},{"id":"48","topic_id":"1","user_id":"135","content":"12312","add_time":"1476945170","parent_comment_username":"张三","user_name":"张晓文111","user_photo":""}]}
     * msg :
     * url :
     */

    private String result;
    /**
     * topic_id : 1
     * topic_category : 智能家居
     * topic_category_id : 1
     * topic_name : 我在北京有个别墅客户想安装智能家居，谁有好的品牌推荐？
     * communication_title : 我在北京有个别墅客户想安装智能家居，谁有好的品牌推荐？
     * add_time : 1476756498
     * user_name : 张晓文
     * user_id : 135
     * topic_comments : [{"id":"1","topic_id":"1","user_id":"135","content":"评论11111","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"2","topic_id":"1","user_id":"135","content":"评论222222","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"3","topic_id":"1","user_id":"135","content":"评论333333","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"4","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"5","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"6","topic_id":"1","user_id":"135","content":"12312","add_time":null,"parent_comment_username":null,"user_name":"张晓文111","user_photo":""},{"id":"47","topic_id":"1","user_id":"135","content":"12312","add_time":"1476945070","parent_comment_username":"","user_name":"张晓文111","user_photo":""},{"id":"48","topic_id":"1","user_id":"135","content":"12312","add_time":"1476945170","parent_comment_username":"张三","user_name":"张晓文111","user_photo":""}]
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
        private String topic_id;
        private String topic_category;
        private String topic_category_id;
        private String topic_name;
        private String communication_title;
        private String add_time;
        private String user_name;
        private String user_id;
        /**
         * id : 1
         * topic_id : 1
         * user_id : 135
         * content : 评论11111
         * add_time : null
         * parent_comment_username : null
         * user_name : 张晓文111
         * user_photo :
         */

        private List<TopicCommentsBean> topic_comments;

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

        public List<TopicCommentsBean> getTopic_comments() {
            return topic_comments;
        }

        public void setTopic_comments(List<TopicCommentsBean> topic_comments) {
            this.topic_comments = topic_comments;
        }

        public static class TopicCommentsBean implements Serializable{
            private String id;
            private String topic_id;
            private String user_id;
            private String content;
            private Object add_time;
            private Object parent_comment_username;
            private String user_name;
            private String user_photo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTopic_id() {
                return topic_id;
            }

            public void setTopic_id(String topic_id) {
                this.topic_id = topic_id;
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

            public Object getAdd_time() {
                return add_time;
            }

            public void setAdd_time(Object add_time) {
                this.add_time = add_time;
            }

            public Object getParent_comment_username() {
                return parent_comment_username;
            }

            public void setParent_comment_username(Object parent_comment_username) {
                this.parent_comment_username = parent_comment_username;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_photo() {
                return user_photo;
            }

            public void setUser_photo(String user_photo) {
                this.user_photo = user_photo;
            }
        }
    }
}
