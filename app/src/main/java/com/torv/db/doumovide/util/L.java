package com.torv.db.doumovide.util;

import android.util.Log;

import com.torv.db.doumovide.BuildConfig;

/**
 * Created by admin on 16/6/25.
 */
public class L {

    private static final boolean isDebug = BuildConfig.DEBUG;
    private static final String AUTHOR_TAG = "torv_dou";

    public static void e(String msg){
        if(isDebug){
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.e(tag, msg);
        }
    }

    public static void d(String msg){
        if(isDebug){
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.d(tag, msg);
        }
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(),
                caller.getLineNumber());
        return AUTHOR_TAG +":" +tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
