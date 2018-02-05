package com.m2m.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2m.exception.SystemException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JSON {
    private static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static <T> T readAsList(File file, Class valueType) throws SystemException {
        T rtn = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(TIME_FORMAT));
        try {
            JavaType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
            rtn = mapper.readValue(file, collectionType);
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        }
        return rtn;
    }

    public static <T> T read(File file, Class<T> valueType) throws SystemException {
        T rtn = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(TIME_FORMAT));
        try {
            rtn = mapper.readValue(file, valueType);
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        }
        return rtn;
    }
}
