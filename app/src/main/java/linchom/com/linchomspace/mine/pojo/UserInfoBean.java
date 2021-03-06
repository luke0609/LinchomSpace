package linchom.com.linchomspace.mine.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2016/10/17.
 */
public class UserInfoBean implements Serializable{

    public String result;
    public DataBean data;
    public String msg;
    public String url;

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

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static class DataBean implements Serializable {
        private String rank_name;
        private String discount;
        private String email;
        private String user_photo;
        private String user_name;
        private String rank_points;
        private String pay_points;
        private String user_money;
        private String sex;
        private String birthday;
        private String question;
        private String qq;
        private String name;
        private String photo;
        private String msn;
        private String office_phone;
        private String home_phone;
        private String mobile_phone;
        private Object passwd_question;
        private Object passwd_answer;
        private List<?> bonus;

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getRank_points() {
            return rank_points;
        }

        public void setRank_points(String rank_points) {
            this.rank_points = rank_points;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public String getHome_phone() {
            return home_phone;
        }

        public void setHome_phone(String home_phone) {
            this.home_phone = home_phone;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public Object getPasswd_question() {
            return passwd_question;
        }

        public void setPasswd_question(Object passwd_question) {
            this.passwd_question = passwd_question;
        }

        public Object getPasswd_answer() {
            return passwd_answer;
        }

        public void setPasswd_answer(Object passwd_answer) {
            this.passwd_answer = passwd_answer;
        }

        public List<?> getBonus() {
            return bonus;
        }

        public void setBonus(List<?> bonus) {
            this.bonus = bonus;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "rank_name='" + rank_name + '\'' +
                    ", discount='" + discount + '\'' +
                    ", email='" + email + '\'' +
                    ", user_photo='" + user_photo + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", rank_points='" + rank_points + '\'' +
                    ", pay_points='" + pay_points + '\'' +
                    ", user_money='" + user_money + '\'' +
                    ", sex='" + sex + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", question='" + question + '\'' +
                    ", qq='" + qq + '\'' +
                    ", name='" + name + '\'' +
                    ", photo='" + photo + '\'' +
                    ", msn='" + msn + '\'' +
                    ", office_phone='" + office_phone + '\'' +
                    ", home_phone='" + home_phone + '\'' +
                    ", mobile_phone='" + mobile_phone + '\'' +
                    ", passwd_question=" + passwd_question +
                    ", passwd_answer=" + passwd_answer +
                    ", bonus=" + bonus +
                    '}';
        }

        public DataBean(){}
        public DataBean(String user_name, String email, String sex, String birthday, String office_phone, String home_phone, String mobile_phone) {
            this.user_name = user_name;
            this.email = email;
            this.sex = sex;
            this.birthday = birthday;
            this.office_phone = office_phone;
            this.home_phone = home_phone;
            this.mobile_phone = mobile_phone;
        }

        public DataBean(String user_name, String email, String sex, String birthday, String office_phone, String home_phone) {
            this.user_name = user_name;
            this.email = email;
            this.sex = sex;
            this.birthday = birthday;
            this.office_phone = office_phone;
            this.home_phone = home_phone;
        }

        public DataBean(String photo, String name, String birthday, String qq, String sex, String user_name, String user_photo, String mobile_phone, String home_phone, String office_phone) {
            this.photo = photo;
            this.name = name;
            this.birthday = birthday;
            this.qq = qq;
            this.sex = sex;
            this.user_name = user_name;
            this.user_photo = user_photo;
            this.mobile_phone = mobile_phone;
            this.home_phone = home_phone;
            this.office_phone = office_phone;
        }
    }


}
