package com.sixin.annotation;

/**
 * @author 周文涛
 */

public class EventType {

    public static final String DEFAULT_TAG = "default_tag";

    /**
     * 参数类型
     * */
    private Class<?> mParamType;

    /**
     * 函数的tag
     * */
    private String mTag = DEFAULT_TAG;

    public EventType(Class<?> paramType , String tag){
        mParamType = paramType;
        mTag = tag;
    }
}
