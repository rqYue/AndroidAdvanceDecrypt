package com.rq.hookstartactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {

    private static final String TAG = "InstrumentationProxy";
    Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation){
        mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options)  {

        Log.d(TAG, "Hook 成功" + "---who" + who);

        try {
            // 通过反射找到 Instrumentation 的 execStartActivity 方法
            Method execStartActivityMethod = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);

            return (ActivityResult) execStartActivityMethod.invoke(mInstrumentation, who, contextThread,
                    token, target, intent, requestCode, options);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
