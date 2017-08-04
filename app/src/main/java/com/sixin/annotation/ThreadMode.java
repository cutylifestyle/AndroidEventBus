package com.sixin.annotation;

/**
 *@author 周文涛
 */

public enum ThreadMode {

    /**
     * 事件执行在UI线程
     * */
    MAIN,

    /**
     * 事件执行在发布线程
     * */
    POST,

    /**
     * 事件执行在子线程
     * */
    ASYNC
}
