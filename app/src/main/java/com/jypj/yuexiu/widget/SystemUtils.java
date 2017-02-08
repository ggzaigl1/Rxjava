package com.jypj.yuexiu.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.widget.Toast;

public final class SystemUtils {

    private SystemUtils() {
    }

    /**
     * 土司的工具类
     *
     * @param context
     * @param text
     * @param isLongTimeShow
     */
    public static void showToast(Context context, String text, boolean isLongTimeShow) {
        int duration = isLongTimeShow ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    /**
     * 状态栏高度算法
     *
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

}
