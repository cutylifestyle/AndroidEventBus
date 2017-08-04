package com.sixin.androideventbus;

import com.sixin.annotation.EventType;
import com.sixin.annotation.ThreadMode;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * 订阅者对象，包含了订阅者和目标方法
 * @author 周文涛
 */

public class Subscription {

    /**
     * 订阅者对象
     * */
    private WeakReference<Object> mSubscriber;

    /**
     * 目标方法
     * */
    private Method mTargetMethod;

    /**
     * 执行事件的线程模型
     * */
    private ThreadMode mThreadMode;

    /**
     * 事件类型
     * */
    private EventType mEventType;

    public Subscription(Object subscriber,TargetMethod targetMethod){
        mSubscriber = new WeakReference<Object>(subscriber);
        mTargetMethod = targetMethod.getmMethod();
        mThreadMode = targetMethod.getmThreadMode();
        mEventType = targetMethod.getmEventType();
    }

}
