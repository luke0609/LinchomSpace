package linchom.com.linchomspace.application;

import android.app.Application;
import android.content.Context;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/23.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        context = getApplicationContext(); // 是否输出debug日志, 开启debug会影响性能.
    }



    public static Context getContext() {
        return context;
    }


}
