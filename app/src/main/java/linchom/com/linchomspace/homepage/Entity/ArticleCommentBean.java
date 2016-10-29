package linchom.com.linchomspace.homepage.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class ArticleCommentBean {


    /**
     * result : 0
     * data : {"total_pages":2,"total":"12","items":[{"comment_id":"12","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820544","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"13","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820733","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"14","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820762","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"15","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821004","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"16","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821038","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"17","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821047","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"18","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821056","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"19","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821068","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"20","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821069","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"21","comment_type":"1","id_value":"120","email":"张晓文","user_name":"","content":"评论","comment_rank":"5","add_time":"1476875349","ip_address":"","status":"1","parent_id":"0","user_id":"135"}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 2
     * total : 12
     * items : [{"comment_id":"12","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820544","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"13","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820733","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"14","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476820762","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"15","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821004","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"16","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821038","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"17","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821047","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"18","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821056","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"19","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821068","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"20","comment_type":"1","id_value":"120","email":"张晓文","user_name":"2070118814@qq.com","content":"评论","comment_rank":"5","add_time":"1476821069","ip_address":"","status":"1","parent_id":"0","user_id":"135"},{"comment_id":"21","comment_type":"1","id_value":"120","email":"张晓文","user_name":"","content":"评论","comment_rank":"5","add_time":"1476875349","ip_address":"","status":"1","parent_id":"0","user_id":"135"}]
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
         * comment_id : 12
         * comment_type : 1
         * id_value : 120
         * email : 张晓文
         * user_name : 2070118814@qq.com
         * content : 评论
         * comment_rank : 5
         * add_time : 1476820544
         * ip_address :
         * status : 1
         * parent_id : 0
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
            private String comment_id;
            private String comment_type;
            private String id_value;
            private String email;
            private String user_name;
            private String content;
            private String comment_rank;
            private String add_time;
            private String ip_address;
            private String status;
            private String parent_id;
            private String user_id;

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getComment_type() {
                return comment_type;
            }

            public void setComment_type(String comment_type) {
                this.comment_type = comment_type;
            }

            public String getId_value() {
                return id_value;
            }

            public void setId_value(String id_value) {
                this.id_value = id_value;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getComment_rank() {
                return comment_rank;
            }

            public void setComment_rank(String comment_rank) {
                this.comment_rank = comment_rank;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getIp_address() {
                return ip_address;
            }

            public void setIp_address(String ip_address) {
                this.ip_address = ip_address;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
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
