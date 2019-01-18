package com.li.yu.mvc.base;

import android.app.Application;
import android.content.Context;

import com.li.yu.mvc.utils.HttpStatusInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2018/3/24.
 */

public class App extends Application{
    private static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initOkhttpClient();
        //抓错误日志//
        //initBugly();
        //CrashHandler.getInstance().init(getApplicationContext());
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .addInterceptor(new HttpStatusInterceptor())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static App getInstance(){
        return mApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
//    private void initBugly() {
//        // 获取当前包名
//        String packageName = getApplicationContext().getPackageName();
//        // 获取当前进程名
//        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        //buglly上报  调试阶段true 上线false
//        CrashReport.initCrashReport(getApplicationContext(),"8a137904d6", true, strategy);
//    }
}