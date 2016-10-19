package linchom.com.linchomspace.chat.pojo;

import java.util.ArrayList;

/**
 * Created by Jingsheng Liang on 2016/9/14.
 */
public class ListActivityBean {
    public int status;
    public ArrayList<Dongtai> dongtailist;

    @Override
    public String toString() {
        return "ListActivityBean{" +
                "status=" + status +
                ", dongtailist=" + dongtailist +
                '}';
    }

    public static class Dongtai{
        public Integer dongtaiId;
        public String photoImg;
        public String name;
        public String title;
        public String content;
        public Integer dianzan;

        @Override
        public String toString() {
            return "Dongtai{" +
                    "dongtaiId=" + dongtaiId +
                    ", photoImg='" + photoImg + '\'' +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", dianzan=" + dianzan +
                    '}';
        }
    }
}
