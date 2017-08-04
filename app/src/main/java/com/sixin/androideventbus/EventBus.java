package com.sixin.androideventbus;

import com.sixin.annotation.EventType;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 周文涛
 */

public final class EventBus {

    private static EventBus sDefaultBus;

    private final Map<EventType, CopyOnWriteArrayList<Subscription>> mSubcriberMap = new ConcurrentHashMap<>();
    private SubscriberMethodHunter mMethodHunter = new SubscriberMethodHunter(mSubcriberMap);

    private ThreadLocal<Queue<EventType>> mLocalEvents = new ThreadLocal<Queue<EventType>>(){
        @Override
        protected Queue<EventType> initialValue() {
            return new ConcurrentLinkedDeque<>();
        }
    };

    private EventDispatcher eventDispatcher = new EventDispatcher();

    private EventBus(){}

    public static EventBus getDefault(){
        if(sDefaultBus == null){
            synchronized (EventBus.class){
                if(sDefaultBus == null){
                    sDefaultBus = new EventBus();
                }
            }
        }
        return sDefaultBus;
    }

    public void register(Object subscriber){
        if(subscriber == null){
            return;
        }
        synchronized (this) {
            mMethodHunter.findSubscribeMethods(subscriber);
        }
    }

    public void post(Object event){
        if(event == null){
            throw new NullPointerException("event must not be null");
        }
        mLocalEvents.get().offer(new EventType(event.getClass(), EventType.DEFAULT_TAG));
    }

    /**
     * 事件分发器
     * */
    private class EventDispatcher{

        public void dispatchEvents(Object aEvent){
            Queue<EventType> eventQueue = mLocalEvents.get();
            while(eventQueue.size() > 0){
                deliveryEvent(eventQueue.poll(),aEvent);
            }
        }

        private void deliveryEvent(EventType type,Object aEvent){

        }

        private List<EventType> getMatchedEventTypes(EventType type,Object aEvent){
            List<EventType> eventTypes = null;
            return null;
        }

    }

}
