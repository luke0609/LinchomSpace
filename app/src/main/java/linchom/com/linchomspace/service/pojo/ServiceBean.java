package linchom.com.linchomspace.service.pojo;

import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/28.
 */

public class ServiceBean {

    /**
     * result : 0
     * data : {"total_pages":1,"total":"2","items":[{"id":"21","category_id":"2","service_type":"0","content":"","release":"1","mobile":"12574683256","country":"1","province":"2","city":"52","district":"503","add_time":"2016-11-03 13:37:31","address":"北京北京朝阳区","user_id":"1","ip":"100.000.0","photo":"","status":"0","is_show":"1","title":"你好，我有一个需求","topic_category_name":"智能健康","province_name":"北京","city_name":"北京","district_name":"朝阳区","addressinfo":"1","name":""},{"id":"11","category_id":"2","service_type":"0","content":"11111111","release":"1","mobile":"13554683790","country":"1","province":"2","city":"52","district":"503","add_time":"2016-10-31 12:49:20","address":"北京北京朝阳区","user_id":"1","ip":"100.000.0","photo":"","status":"0","is_show":"1","title":"I love you ","topic_category_name":"智能健康","province_name":"北京","city_name":"北京","district_name":"朝阳区","addressinfo":"1","name":""}],"pagesize":10}
     * msg :
     * url :
     */

    private String result;
    /**
     * total_pages : 1
     * total : 2
     * items : [{"id":"21","category_id":"2","service_type":"0","content":"","release":"1","mobile":"12574683256","country":"1","province":"2","city":"52","district":"503","add_time":"2016-11-03 13:37:31","address":"北京北京朝阳区","user_id":"1","ip":"100.000.0","photo":"","status":"0","is_show":"1","title":"你好，我有一个需求","topic_category_name":"智能健康","province_name":"北京","city_name":"北京","district_name":"朝阳区","addressinfo":"1","name":""},{"id":"11","category_id":"2","service_type":"0","content":"11111111","release":"1","mobile":"13554683790","country":"1","province":"2","city":"52","district":"503","add_time":"2016-10-31 12:49:20","address":"北京北京朝阳区","user_id":"1","ip":"100.000.0","photo":"","status":"0","is_show":"1","title":"I love you ","topic_category_name":"智能健康","province_name":"北京","city_name":"北京","district_name":"朝阳区","addressinfo":"1","name":""}]
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
         * id : 21
         * category_id : 2
         * service_type : 0
         * content :
         * release : 1
         * mobile : 12574683256
         * country : 1
         * province : 2
         * city : 52
         * district : 503
         * add_time : 2016-11-03 13:37:31
         * address : 北京北京朝阳区
         * user_id : 1
         * ip : 100.000.0
         * photo :
         * status : 0
         * is_show : 1
         * title : 你好，我有一个需求
         * topic_category_name : 智能健康
         * province_name : 北京
         * city_name : 北京
         * district_name : 朝阳区
         * addressinfo : 1
         * name :
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
            private String category_id;
            private String service_type;
            private String content;
            private String release;
            private String mobile;
            private String country;
            private String province;
            private String city;
            private String district;
            private String add_time;
            private String address;
            private String user_id;
            private String ip;
            private String photo;
            private String status;
            private String is_show;
            private String title;
            private String topic_category_name;
            private String province_name;
            private String city_name;
            private String district_name;
            private String addressinfo;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getRelease() {
                return release;
            }

            public void setRelease(String release) {
                this.release = release;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTopic_category_name() {
                return topic_category_name;
            }

            public void setTopic_category_name(String topic_category_name) {
                this.topic_category_name = topic_category_name;
            }

            public String getProvince_name() {
                return province_name;
            }

            public void setProvince_name(String province_name) {
                this.province_name = province_name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getDistrict_name() {
                return district_name;
            }

            public void setDistrict_name(String district_name) {
                this.district_name = district_name;
            }

            public String getAddressinfo() {
                return addressinfo;
            }

            public void setAddressinfo(String addressinfo) {
                this.addressinfo = addressinfo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
