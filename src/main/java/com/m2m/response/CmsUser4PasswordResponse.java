package com.m2m.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CmsUser4PasswordResponse implements Serializable {
    private String password;
    private String newPassword;
}