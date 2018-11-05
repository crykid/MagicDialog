package com.blank.magicdialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 */
public class ScreenTool {

    private static Context context;

    private ScreenTool() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }



    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenWidth() {
        if (!checkInit()) {
            return 0;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static float getDensity() {
        if (!checkInit()) {
            return 0;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.density;
    }





    /**
     * 像素单位转换 dp到px
     */
    public static int dpTopx(float dp) {
        if (!checkInit()) {
            return 0;
        }
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);

    }




    private static boolean checkInit() {
        if (context == null) {
            return false;
        }
        return true;
    }
}
