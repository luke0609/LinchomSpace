package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/25.
 */
public class MychatInfoBean {

    public String result;
    public Mdata data;
    public String msg;
    public String url;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Mdata getData() {
        return data;
    }

    public void setData(Mdata data) {
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

    @Override
    public String toString() {
        return "MychatInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public class Mdata{
        public String total_pages;
        public String total;

        @Override
        public String toString() {
            return "Mdata{" +
                    "total_pages='" + total_pages + '\'' +
                    ", total='" + total + '\'' +
                    ", items=" + items +
                    ", pagesize='" + pagesize + '\'' +
                    '}';
        }

        public List<Imtems> items;
        public String pagesize;


       public class Imtems{

           public String topic_id;
           public String topic_category;
           public String topic_category_id;
           public String topic_name;
           public String communication_title;
           public String add_time;
           public String user_name;
           public String user_id;
           public String photo;
           public String mobile;
           public String email;
           public String company;
           public String family_address;
           public String repliesnumber;


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

           public String getMobile() {
               return mobile;
           }

           public void setMobile(String mobile) {
               this.mobile = mobile;
           }

           public String getEmail() {
               return email;
           }

           public void setEmail(String email) {
               this.email = email;
           }

           public String getCompany() {
               return company;
           }

           public void setCompany(String company) {
               this.company = company;
           }

           public String getFamily_address() {
               return family_address;
           }

           public void setFamily_address(String family_address) {
               this.family_address = family_address;
           }

           public String getRepliesnumber() {
               return repliesnumber;
           }

           public void setRepliesnumber(String repliesnumber) {
               this.repliesnumber = repliesnumber;
           }

           @Override
           public String toString() {
               return "Imtems{" +
                       "topic_id='" + topic_id + '\'' +
                       ", topic_category='" + topic_category + '\'' +
                       ", topic_category_id='" + topic_category_id + '\'' +
                       ", topic_name='" + topic_name + '\'' +
                       ", communication_title='" + communication_title + '\'' +
                       ", add_time='" + add_time + '\'' +
                       ", user_name='" + user_name + '\'' +
                       ", user_id='" + user_id + '\'' +
                       ", photo='" + photo + '\'' +
                       ", mobile='" + mobile + '\'' +
                       ", email='" + email + '\'' +
                       ", company='" + company + '\'' +
                       ", family_address='" + family_address + '\'' +
                       ", repliesnumber='" + repliesnumber + '\'' +
                       '}';
           }
       }
    }

}
