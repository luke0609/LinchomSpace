package linchom.com.linchomspace.shopping.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/10.
 */
public class PictureHandle {


    public  static List<String> descHandle(String string) {

        List<String> picturesUrl = new ArrayList<String>();

        //记录第一个出现。.jpg的位置

        List<Integer> jpgList = new ArrayList<Integer>();

        int a = 0;

        //从 a位置 开始找下一个   .jpg

        while (true) {


            int jpgIndex = string.indexOf(".jpg", a);

            if (jpgIndex == -1) {
                break;
            }
            jpgList.add(jpgIndex);
            a = jpgIndex + 1;
        }



        int httpIndex = -1;


        int c=0;

        int endIndex= 0;


        while(true){
            //找end

            if(endIndex>jpgList.size()-1){
                break;

            }


            httpIndex = string.indexOf("src",c);

            String str =((string.substring(httpIndex, jpgList.get(endIndex)))+".jpg").trim();
            String str2 = str.substring(5,str.length());

            Log.i("aaaaa",str2.substring(0,1));

            Log.i("aaaaa",str2);

            if((str2.substring(0,1)).equals("h")){
                //不做任何改变

                Log.i("aaaaa","不做任何改变的"+str2);


            }else {

                Log.i("aaaaa","改变的"+str2);

                str2="http://linchom.com/"+str2;

            }

            picturesUrl.add(str2);

            endIndex++;

            c= httpIndex+1;


        }



        return picturesUrl;


    }


    public static List<String> getImageSrc(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);

            // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            imageSrcList.add(src);

        }
        return imageSrcList;
    }



}
