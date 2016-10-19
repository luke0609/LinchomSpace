package linchom.com.linchomspace.homepage.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */

public class HomeAdvBean {

    /**
     * result : 0
     * data : [{"figure1":"http://app.linchom.com/images/h1.jpg","url":"http://app.linchom.com"},{"figure2":"http://app.linchom.com/images/h2.jpg","url":"http://app.linchom.com"},{"figure3":"http://app.linchom.com/images/h3.jpg","url":"http://app.linchom.com"}]
     * msg :
     * url :
     */

    private String result;
    private String msg;
    private String url;
    /**
     * figure1 : http://app.linchom.com/images/h1.jpg
     * url: http://app.linchom.com
     */

    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String figure1;
        private String url;

        public String getFigure1() {
            return figure1;
        }

        public void setFigure1(String figure1) {
            this.figure1 = figure1;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
