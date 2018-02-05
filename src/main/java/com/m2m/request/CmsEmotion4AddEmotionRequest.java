package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsEmotion4AddEmotionRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long packId;
    @NotNull
    private String name;
    @NotNull
    private String cover;
    @NotNull
    private Integer order;
    @NotNull
    private String extra;
    @NotNull
    private Integer width;
    @NotNull
    private Integer height;
}
