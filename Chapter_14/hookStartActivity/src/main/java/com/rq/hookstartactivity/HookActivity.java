package com.rq.hookstartactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class HookActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        // hook activity 中的 startActivity 方法
//        replaceActivityInstrumentation(this);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://www.baidu.com"));
//        startActivity(intent);

        // hook context 中的 startActivity 方法
        replaceContextInstrumentation();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);

    }

    // Hook Activity 中的 startActivity 方法
    public void replaceActivityInstrumentation(Activity activity) {
        try {
            // 获取 Activity的 mInstrumentation 字段
            Field field = Activity.class.getDeclaredField("mInstrumentation");
            // 取消Java 的权限控制检查
            field.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) field.get(activity);

            Instrumentation instrumentationProxy = new InstrumentationProxy(instrumentation);
            // 注入对象，进行对象替换
            field.set(activity, instrumentationProxy);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Hook Context 中的 startActivity 方法
    public void replaceContextInstrumentation() {

        try {
            // 获取 ActivityThread 类
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");

            // sCurrentActivityThread 用于表示当前的 ActivityThread 对象
            Field activityThreadField = activityThreadClazz.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            Object currentActivityThread = activityThreadField.get(null);

            // 获取 ActivityThread 中的 mInstrumentation 字段
            Field mInstrumentationField = activityThreadClazz.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

            // 创建 InstrumentationProxy 对象，并进行替换
            Instrumentation mInstrumentationProxy = new InstrumentationProxy(mInstrumentation);
            mInstrumentationField.set(currentActivityThread, mInstrumentationProxy);

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
