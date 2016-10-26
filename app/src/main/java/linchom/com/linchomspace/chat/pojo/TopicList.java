package linchom.com.linchomspace.chat.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/18.
 */

public class TopicList implements Serializable{


    /**
     * result : 0
     * data : {"total_pages":3,"total":"26","items":[{"topic_id":"76","topic_category":"智能家居","topic_category_id":"1","topic_name":"was","communication_title":"WWF","add_time":"1477468662","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":1},{"topic_id":"75","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465524","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":2},{"topic_id":"74","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465469","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":3},{"topic_id":"73","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465240","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":4},{"topic_id":"71","topic_category":"智能家居","topic_category_id":"1","topic_name":"测试2","communication_title":"你好","add_time":"1477464123","user_name":"劳保","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"1","key":5},{"topic_id":"69","topic_category":"智能家居","topic_category_id":"1","topic_name":"测试","communication_title":"你好","add_time":"1477463644","user_name":"劳保","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":6},{"topic_id":"68","topic_category":"智能家居","topic_category_id":"1","topic_name":"off FCC","communication_title":"Sass's","add_time":"1477463327","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":7},{"topic_id":"67","topic_category":"智能家居","topic_category_id":"1","topic_name":"were we","communication_title":"CSX's FCC's\n","add_time":"1477452792","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":8},{"topic_id":"66","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"eeee","add_time":"1477446500","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":9},{"topic_id":"64","topic_category":"智能家居","topic_category_id":"1","topic_name":"智能家居相关产品有推荐的吗","communication_title":"我们家要装修","add_time":"1477446194","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":10}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 3
     * total : 26
     * items : [{"topic_id":"76","topic_category":"智能家居","topic_category_id":"1","topic_name":"was","communication_title":"WWF","add_time":"1477468662","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":1},{"topic_id":"75","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465524","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":2},{"topic_id":"74","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465469","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":3},{"topic_id":"73","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"123","add_time":"1477465240","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":4},{"topic_id":"71","topic_category":"智能家居","topic_category_id":"1","topic_name":"测试2","communication_title":"你好","add_time":"1477464123","user_name":"劳保","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"1","key":5},{"topic_id":"69","topic_category":"智能家居","topic_category_id":"1","topic_name":"测试","communication_title":"你好","add_time":"1477463644","user_name":"劳保","user_id":"135","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":6},{"topic_id":"68","topic_category":"智能家居","topic_category_id":"1","topic_name":"off FCC","communication_title":"Sass's","add_time":"1477463327","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":7},{"topic_id":"67","topic_category":"智能家居","topic_category_id":"1","topic_name":"were we","communication_title":"CSX's FCC's\n","add_time":"1477452792","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":8},{"topic_id":"66","topic_category":"智能家居","topic_category_id":"1","topic_name":"123","communication_title":"eeee","add_time":"1477446500","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":9},{"topic_id":"64","topic_category":"智能家居","topic_category_id":"1","topic_name":"智能家居相关产品有推荐的吗","communication_title":"我们家要装修","add_time":"1477446194","user_name":"ljs123","user_id":"129","photo":null,"mobile":null,"email":null,"company":null,"family_address":null,"repliesnumber":"0","key":10}]
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
         * topic_id : 76
         * topic_category : 智能家居
         * topic_category_id : 1
         * topic_name : was
         * communication_title : WWF
         * add_time : 1477468662
         * user_name : ljs123
         * user_id : 129
         * photo : null
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

        public static class ItemsBean {
            private String topic_id;
            private String topic_category;
            private String topic_category_id;
            private String topic_name;
            private String communication_title;
            private String add_time;
            private String user_name;
            private String user_id;
            private Object photo;
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

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
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
