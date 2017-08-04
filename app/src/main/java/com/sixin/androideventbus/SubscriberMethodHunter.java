package com.sixin.androideventbus;

import android.support.annotation.NonNull;

import com.sixin.annotation.EventType;
import com.sixin.annotation.Subscriber;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 周文涛
 */

public class SubscriberMethodHunter {

    private Map<EventType,CopyOnWriteArrayList<Subscription>> mSubscriberMap;

    public SubscriberMethodHunter(@NonNull Map<EventType,CopyOnWriteArrayList<Subscription>> subscriberMap){
        mSubscriberMap = subscriberMap;
    }

    /**
     * 查找订阅对象中的所有订阅函数，订阅函数的参数只能有一个，找到订阅函数之后
     * ，将subscription存储到map中
     * */
    public void findSubscribeMethods(Object subscriber){
        Class<?> clazz =  subscriber.getClass();
        if(clazz != null){
            Method[] methods = clazz.getDeclaredMethods();
            for(int i = 0 ; i < methods.length ; i++){
                Method method = methods[i];
                Subscriber annotation = method.getAnnotation(Subscriber.class);
                if(annotation != null){
                    Class<?>[] paramsTypeClass = method.getParameterTypes();
                    if(paramsTypeClass != null && paramsTypeClass.length == 1){
                        Class<?> paramType = paramsTypeClass[0];
                        EventType eventType = new EventType(paramType,annotation.tag());
                        TargetMethod subscribeMethod = new TargetMethod(method, eventType, annotation.mode());
                        subscribe(eventType, subscribeMethod, subscriber);
                    }
                }
            }
        }
    }

    /**
     *
     * */
    private void subscribe(EventType eventType,TargetMethod method,Object subscriber){
        CopyOnWriteArrayList<Subscription> subscriptionfLists = mSubscriberMap.get(eventType);
        if(subscriptionfLists == null){
            subscriptionfLists = new CopyOnWriteArrayList<>();
        }

        Subscription newSubscription = new Subscription(subscriber, method);
        if(subscriptionfLists.contains(newSubscription)){
            return;
        }

        subscriptionfLists.add(newSubscription);
        mSubscriberMap.put(eventType, subscriptionfLists);
    }
}
