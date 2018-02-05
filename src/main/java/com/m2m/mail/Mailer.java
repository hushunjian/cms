package com.m2m.mail;


public class Mailer {
    public static void send(ExMailService exMailService, String receiver, String subject, String message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                exMailService.send(receiver, subject, message);
            }
        }).start();
    }
    public static void send(ExMailService exMailService, String subject, String message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                exMailService.send(subject, message);
            }
        }).start();
    }
}
