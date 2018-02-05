package com.m2m.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m2m.copier.CredentialCopier;
import com.m2m.entity.ExtCmsCredential;
import com.m2m.exception.ErrorMapping;
import com.m2m.exception.SystemException;
import com.m2m.mail.ExMailService;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.service.CmsCredentialService;

public class BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String STATUS = "status";
    private static String BODY = "body";
    private static String MESSAGE = "message";

    @Autowired
    private ExMailService exMailService;
    @Autowired
    protected CmsCredentialService cmsCredentialService;

    protected CmsCredentialResponse validate(String token) throws SystemException {
        ExtCmsCredential extCmsCredential = cmsCredentialService.getCredential(token);
        return CredentialCopier.INSTANCE.asImsCredentialResponse(extCmsCredential);
    }

    private void printStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logger.error(sw.toString());
    }

    @ResponseBody
    @ExceptionHandler({SystemException.class})
    public Object handleException(SystemException e) {
        printStackTrace(e);
        Map<String, Object> error = new HashMap<String, Object>();
        error.put(STATUS, ErrorMapping.getCode(e.getClass()));
        error.put(MESSAGE, ErrorMapping.getMessage(e.getClass()));
        return fail(error);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<String, Object>();
        if (e.getBindingResult().hasErrors()) {
            for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                String code = objectError.getCodes()[1];
                String field = code.replaceAll("^.*\\.", "");
                errors.put(STATUS, "4000");
                String message = String.format("%s %s", field, objectError.getDefaultMessage());
                Map<String, Object> body = new HashMap<String, Object>();
                body.put(MESSAGE, message);
                errors.put(BODY, body);
                break;
            }
        }
        return errors;
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e) {
        printStackTrace(e);
        Map<String, Object> error = new HashMap<String, Object>();
        error.put(STATUS, 9999);
        error.put(MESSAGE, e.getMessage());
        String subject = String.format("%s", e.getClass());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        exMailService.send(subject, sw.toString());
        return fail(error);
    }

    public Object success(Object body) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put(STATUS, 2000);
        response.put(BODY, body);
        return response;
    }

    public Object success() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put(STATUS, 2000);
        Map<String, Object> body = new HashMap<String, Object>();
        response.put(BODY, body);
        return response;
    }


    private Object fail(Map<String, Object> error) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put(STATUS, error.get(STATUS));
        Map<String, Object> body = new HashMap<String, Object>();
        body.put(MESSAGE, error.get(MESSAGE));
        response.put(BODY, body);
        return response;
    }
}