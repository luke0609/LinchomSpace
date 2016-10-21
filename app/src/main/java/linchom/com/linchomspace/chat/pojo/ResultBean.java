package linchom.com.linchomspace.chat.pojo;

/**
 * Created by Jingsheng Liang on 2016/10/19.
 */

public class ResultBean {

    /**
     * result : 0
     * data : true
     * msg :
     * url :
     */

    private String result;
    private boolean data;
    private String msg;
    private String url;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
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
