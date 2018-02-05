package com.m2m.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
public class ApplicationEventBus {

    private final EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));

    public void register(Object object){
        eventBus.register(object);
    }

    public void unRegister(Object object){
        eventBus.unregister(object);
    }

    public void post(Object event){
        eventBus.post(event);
    }

}
