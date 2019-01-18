package com.li.yu.mvc.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    /**
     * 显示toast
     */
    public static void showToast(Context context, int stringId) {
        Toast.makeText(context,stringId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String string) {
        Toast.makeText(context,string, Toast.LENGTH_SHORT).show();
    }
}
