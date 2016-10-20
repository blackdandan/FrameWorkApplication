package com.yzh.utils;

import android.util.Log;

/**
 * Log统一管理类
 * Created by Administrator on 2016/10/20 0020.
 */

public class L {
    private L(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static boolean isDebug = true;//是否需要打印Log,可以再application中的oncreate里面初始化
    private static final String TAG = "way";

    //下面四个是默认的tag函数
    public static void i(String msg){
        if (isDebug){
            Log.i(TAG,msg);
        }
    }
    public static void d(String msg){
        if (isDebug){
            Log.d(TAG,msg);
        }
    }
    public static void e(String msg){
        if (isDebug){
            Log.e(TAG,msg);
        }
    }
    public static void v(String msg){
        if (isDebug){
            Log.v(TAG,msg);
        }
    }
}
