package linchom.com.linchomspace.chat.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/18.
 */

public class TopicList implements Serializable{


    /**
     * result : 0
     * data : {"total_pages":4,"total":"37","items":[{"topic_id":"176","topic_category":"智能家居","topic_category_id":"1","topic_name":"123567","communication_title":"1234","add_time":"1478518203","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/8339141.jpg,http://app.linchom.com/images/201611/3984710.jpg,","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":1},{"topic_id":"175","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片","communication_title":"图片批量上传","add_time":"1478517103","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/4093750.jpg,http://app.linchom.com/images/201611/4924865.jpg,http://app.linchom.com/images/201611/3274444.jpg,","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":2},{"topic_id":"174","topic_category":"智能家居","topic_category_id":"1","topic_name":"呵呵呵","communication_title":"呵呵呵","add_time":"1478509910","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/6030914.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":3},{"topic_id":"173","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片3","communication_title":"图片","add_time":"1478506888","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/4105560.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":4},{"topic_id":"172","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片2","communication_title":"test","add_time":"1478502561","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/2225524.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":5},{"topic_id":"171","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片测试","communication_title":"test","add_time":"1478502415","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":6},{"topic_id":"170","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片测试","communication_title":"test","add_time":"1478502412","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":7},{"topic_id":"169","topic_category":"智能家居","topic_category_id":"1","topic_name":"","communication_title":"","add_time":"1478500170","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":8},{"topic_id":"140","topic_category":"智能家居","topic_category_id":"1","topic_name":"test","communication_title":"tests","add_time":"1478333465","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"2","key":9},{"topic_id":"139","topic_category":"12312","topic_category_id":"1","topic_name":"你们好","communication_title":"你们好","add_time":"1478331598","user_name":"Jordan","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"1","key":10}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 4
     * total : 37
     * items : [{"topic_id":"176","topic_category":"智能家居","topic_category_id":"1","topic_name":"123567","communication_title":"1234","add_time":"1478518203","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/8339141.jpg,http://app.linchom.com/images/201611/3984710.jpg,","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":1},{"topic_id":"175","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片","communication_title":"图片批量上传","add_time":"1478517103","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/4093750.jpg,http://app.linchom.com/images/201611/4924865.jpg,http://app.linchom.com/images/201611/3274444.jpg,","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":2},{"topic_id":"174","topic_category":"智能家居","topic_category_id":"1","topic_name":"呵呵呵","communication_title":"呵呵呵","add_time":"1478509910","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/6030914.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":3},{"topic_id":"173","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片3","communication_title":"图片","add_time":"1478506888","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/4105560.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":4},{"topic_id":"172","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片2","communication_title":"test","add_time":"1478502561","user_name":"梁京生","user_id":"129","photo":"http://app.linchom.com/images/201611/2225524.jpg","mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":5},{"topic_id":"171","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片测试","communication_title":"test","add_time":"1478502415","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":6},{"topic_id":"170","topic_category":"智能家居","topic_category_id":"1","topic_name":"图片测试","communication_title":"test","add_time":"1478502412","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":7},{"topic_id":"169","topic_category":"智能家居","topic_category_id":"1","topic_name":"","communication_title":"","add_time":"1478500170","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":8},{"topic_id":"140","topic_category":"智能家居","topic_category_id":"1","topic_name":"test","communication_title":"tests","add_time":"1478333465","user_name":"梁京生","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"2","key":9},{"topic_id":"139","topic_category":"12312","topic_category_id":"1","topic_name":"你们好","communication_title":"你们好","add_time":"1478331598","user_name":"Jordan","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"1","key":10}]
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
         * topic_id : 176
         * topic_category : 智能家居
         * topic_category_id : 1
         * topic_name : 123567
         * communication_title : 1234
         * add_time : 1478518203
         * user_name : 梁京生
         * user_id : 129
         * photo : http://app.linchom.com/images/201611/8339141.jpg,http://app.linchom.com/images/201611/3984710.jpg,
         * mobile : null
         * email : null
         * company : null
         * family_address : null
         * repliesnumber : 0
         * key : 1
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

        public static class ItemsBean implements Serializable{
            private String topic_id;
            private String topic_category;
            private String topic_category_id;
            private String topic_name;
            private String communication_title;
            private String add_time;
            private String user_name;
            private String user_id;
            private String photo;
            private Object mobile;
            private Object email;
            private Object company;
            private Object family_address;
            private String repliesnumber;
            private int key;

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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public Object getFamily_address() {
                return family_address;
            }

            public void setFamily_address(Object family_address) {
                this.family_address = family_address;
            }

            public String getRepliesnumber() {
                return repliesnumber;
            }

            public void setRepliesnumber(String repliesnumber) {
                this.repliesnumber = repliesnumber;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }
        }
    }
}
