package linchom.com.linchomspace.service.pojo;

import java.util.List;

/**
 * Created by Jingsheng Liang on 2016/10/31.
 */

public class RregionBean {
    /**
     * result : 0
     * data : [{"region_id":"2","region_name":"北京"},{"region_id":"3","region_name":"安徽"},{"region_id":"4","region_name":"福建"},{"region_id":"5","region_name":"甘肃"},{"region_id":"6","region_name":"广东"},{"region_id":"7","region_name":"广西"},{"region_id":"8","region_name":"贵州"},{"region_id":"9","region_name":"海南"},{"region_id":"10","region_name":"河北"},{"region_id":"11","region_name":"河南"},{"region_id":"12","region_name":"黑龙江"},{"region_id":"13","region_name":"湖北"},{"region_id":"14","region_name":"湖南"},{"region_id":"15","region_name":"吉林"},{"region_id":"16","region_name":"江苏"},{"region_id":"17","region_name":"江西"},{"region_id":"18","region_name":"辽宁"},{"region_id":"19","region_name":"内蒙古"},{"region_id":"20","region_name":"宁夏"},{"region_id":"21","region_name":"青海"},{"region_id":"22","region_name":"山东"},{"region_id":"23","region_name":"山西"},{"region_id":"24","region_name":"陕西"},{"region_id":"25","region_name":"上海"},{"region_id":"26","region_name":"四川"},{"region_id":"27","region_name":"天津"},{"region_id":"28","region_name":"西藏"},{"region_id":"29","region_name":"新疆"},{"region_id":"30","region_name":"云南"},{"region_id":"31","region_name":"浙江"},{"region_id":"32","region_name":"重庆"},{"region_id":"33","region_name":"香港"},{"region_id":"34","region_name":"澳门"},{"region_id":"35","region_name":"台湾"}]
     * msg :
     * url :
     */

    private String result;
    private String msg;
    private String url;
    /**
     * region_id : 2
     * region_name : 北京
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
        private String region_id;
        private String region_name;

        public DataBean(String region_name, String region_id) {
            this.region_name = region_name;
            this.region_id = region_id;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
