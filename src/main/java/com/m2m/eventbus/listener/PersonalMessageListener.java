package com.m2m.eventbus.listener;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.m2m.eventbus.ApplicationEventBus;
import com.m2m.eventbus.event.PersonalMessageEvent;
import com.m2m.exception.SystemException;
import com.m2m.service.LocalConfigService;

@Component
public class PersonalMessageListener {
	private final ApplicationEventBus applicationEventBus;
	private final LocalConfigService localConfigService;
	
	@Autowired
	public PersonalMessageListener(ApplicationEventBus applicationEventBus,LocalConfigService localConfigService){
		this.applicationEventBus = applicationEventBus;
		this.localConfigService = localConfigService;
	}
	
	@PostConstruct
    public void init(){
        this.applicationEventBus.register(this);
    }
	
	@Subscribe
	public void sendMessage(PersonalMessageEvent personalMessageEvent) throws SystemException{
		localConfigService.sendSysMessage(String.valueOf(personalMessageEvent.getUid()), personalMessageEvent.getMessage());
	}
}
