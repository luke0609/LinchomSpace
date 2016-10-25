package linchom.com.linchomspace.mine.pojo;

import java.util.List;

/**
 * Created by lenovo on 2016/10/24.
 */
public class KindsInfoBean {

    public String result;
    public List<DaBean> data;
    public String msg;
    public String url;

    @Override
    public String toString() {
        return "KindsInfoBean{" +
                "result='" + result + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public class DaBean{

        public String category_id;
        public String topic_category_name;

        @Override
        public String toString() {
            return "DaBean{" +
                    "category_id='" + category_id + '\'' +
                    ", topic_category_name='" + topic_category_name + '\'' +
                    '}';
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getTopic_category_name() {
            return topic_category_name;
        }

        public void setTopic_category_name(String topic_category_name) {
            this.topic_category_name = topic_category_name;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DaBean> getData() {
        return data;
    }

    public void setData(List<DaBean> data) {
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
}
