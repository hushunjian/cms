package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsEmotion4UpdateEmotionPackRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String cover;
    @NotNull
    private Integer type;
    @NotNull
    private Integer isValid;
    @NotNull
    private Integer packVersion;
    @NotNull
    private Integer analyVersion;
    @NotNull
    private Integer order;
    @NotNull
    private String extra;

}
