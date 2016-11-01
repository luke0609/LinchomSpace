package linchom.com.linchomspace.service.utils;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import linchom.com.linchomspace.service.pojo.IpBean;

/**
 * Created by Jingsheng Liang on 2016/11/1.
 */

public class IpUtil {

    public void getIp() {
        // http://ip.taobao.com/service/getIpInfo2.php?ip=myip

        RequestParams params = new RequestParams("http://ip.taobao.com/service/getIpInfo2.php");
        params.addQueryStringParameter("ip", "myip");
        System.out.println(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IpBean bean = gson.fromJson(result, IpBean.class);
                String  ipAddress = bean.getData().getIp();

                System.out.println(ipAddress);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
