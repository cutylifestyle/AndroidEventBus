package com.sixin.androideventbus;

import com.sixin.annotation.EventType;
import com.sixin.annotation.ThreadMode;

import java.lang.reflect.Method;

/**
 * 订阅某个事件的函数类，包含了函数信息、参数名、执行的线程模式
 * @author 周文涛
 */

public class TargetMethod {

    /**
     * 订阅者的目标函数
     * */
    private Method mMethod;

    /**
     * 事件类型
     * */
    private EventType mEventType;

    /**
     * 处理事件的线程模式
     * */
    private ThreadMode mThreadMode;

    public TargetMethod(Method md , EventType type , ThreadMode mode){
        mMethod = md;
        mMethod.setAccessible(true);
        mEventType = type;
        mThreadMode = mode;
    }

    public Method getmMethod() {
        return mMethod;
    }

    public EventType getmEventType() {
        return mEventType;
    }

    public ThreadMode getmThreadMode() {
        return mThreadMode;
    }


}
