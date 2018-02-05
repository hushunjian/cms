package com.m2m.eventbus.event;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonalMessageEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long uid;
	private String message;
}
