package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/22.
 */
public class LogInfoBean {

    public String result;
    public String msg;
    public String url;
    public List<DataBean> data;


    public class DataBean{
        public String user_id;
        public String order_id;
        public String action_user;
        public String order_status;
        public String shipping_status;
        public String pay_status;
        public String action_note;
        public String log_time;

        @Override
        public String toString() {
            return "DataBean{" +
                    "user_id='" + user_id + '\'' +
                    ", order_id='" + order_id + '\'' +
                    ", action_user='" + action_user + '\'' +
                    ", order_status='" + order_status + '\'' +
                    ", shipping_status='" + shipping_status + '\'' +
                    ", pay_status='" + pay_status + '\'' +
                    ", action_note='" + action_note + '\'' +
                    ", log_time='" + log_time + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LogInfoBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }
}

