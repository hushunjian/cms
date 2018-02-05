package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAppUser4AddRobotRequest extends CmsCredentialRequest implements Serializable {

    @NotNull
    private Long phoneBegin;
    @NotNull
    private Integer creatCount;
    @NotNull
    private String originPassword;
}
