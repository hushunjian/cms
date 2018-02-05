package com.m2m.util;

import com.m2m.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;


public class Home {
    private static Logger logger = LoggerFactory.getLogger(Home.class);
    private static String REGEX_FILE_SEPARATOR = "/";

    public static File findInHome(String relativePath) throws SystemException {
        File directory = home();
        File rtn = findInDirectory(directory, relativePath);
        return rtn;
    }

    public static String source(File file) throws SystemException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new com.m2m.exception.FileNotFoundException();
        } catch (IOException e) {
            throw new com.m2m.exception.IOException();
        }
        return sb.toString();
    }

    private static File findInDirectory(File directory, String relativePath) {
        File rtn = directory;
        String[] names = relativePath.split(REGEX_FILE_SEPARATOR);
        for (String name : names) {
            rtn = exist(rtn, name);
            if (null == rtn) {
                return null;
            }
        }
        return rtn;
    }

    private static File exist(File directory, String name) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    private static File home() throws SystemException {
        File pwd = pwd();
        logger.debug(String.format("Home:%s.", pwd.getAbsolutePath()));
        return pwd;
    }

    private static URL getURL(String fileName) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL rtn = cl.getResource(fileName);
        return rtn;
    }

    public static File pwd() throws SystemException {
        URL url = getURL("");
        File rtn = null;
        try {
            rtn = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new com.m2m.exception.URISyntaxException();
        }
        return rtn;
    }
}
