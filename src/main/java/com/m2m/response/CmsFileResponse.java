package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsFileResponse implements Serializable {
    private String fileName;
}