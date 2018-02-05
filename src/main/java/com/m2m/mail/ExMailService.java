package com.m2m.mail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExMailService {
    private static Logger logger = LoggerFactory.getLogger(ExMailService.class);
    private static String server = "smtp.exmail.qq.com";

    @Value("${mail.account}")
    private String account;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.proxySet}")
    private String proxySet;
    @Value("${mail.socksProxyHost}")
    private String socksProxyHost;
    @Value("${mail.socksProxyPort}")
    private String socksProxyPort;
    @Value("${mail.receivers}")
    private String receivers;
    private static String port = "465";


    public void send(String subject, String message){
        try {
            send0(subject, message);
        }catch (EmailException e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.error(sw.toString());
        }
    }

    void send(String receiver, String subject, String message){
        try {
            send0(receiver, subject, message);
        }catch (EmailException e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.error(sw.toString());
        }
    }
    private void setupProxy() {
        Properties properties = System.getProperties();
        if("true".equalsIgnoreCase(proxySet)) {
            properties.setProperty("proxySet", proxySet);
            properties.setProperty("socksProxyHost", socksProxyHost);
            properties.setProperty("socksProxyPort", socksProxyPort);
        }
    }

    private void resetProxy() {
        Properties properties = System.getProperties();
        properties.setProperty("proxySet", "false");
        properties.setProperty("socksProxyHost", "");
        properties.setProperty("socksProxyPort", "");
    }

    private void send0(String receiver, String subject, String message) throws EmailException {
        setupProxy();
        SimpleEmail email = new SimpleEmail();
        DefaultAuthenticator authenticator = new DefaultAuthenticator(account, password);
        email.setHostName(server);
        email.setSslSmtpPort(port);
        email.setAuthenticator(authenticator);
        email.setSSLOnConnect(false);
        email.setFrom(account);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(receiver);
        email.send();
        resetProxy();
    }

    private void send0(String subject, String message) throws EmailException {
        setupProxy();
        SimpleEmail email = new SimpleEmail();
        DefaultAuthenticator authenticator = new DefaultAuthenticator(account, password);
        email.setHostName(server);
        email.setSslSmtpPort(port);
        email.setAuthenticator(authenticator);
        email.setSSLOnConnect(false);
        email.setFrom(account);
        email.setSubject(subject);
        email.setMsg(message);
        for (String receiver : Arrays.asList(receivers.split(";"))) {
            String trimmed = receiver.trim();
            if(!"".equals(trimmed) && trimmed.contains("@") ) {
                email.addTo(trimmed);
            }
        }
        email.send();
        resetProxy();
    }
}
