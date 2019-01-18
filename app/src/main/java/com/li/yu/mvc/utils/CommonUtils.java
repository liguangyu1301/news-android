package com.li.yu.mvc.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.li.yu.mvc.base.App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CommonUtils {
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    public static int getRealScreenWidth() {
        int width = 0;
        WindowManager windowManager = (WindowManager) App.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            width = outMetrics.widthPixels;
        }
        return width;
    }
}
