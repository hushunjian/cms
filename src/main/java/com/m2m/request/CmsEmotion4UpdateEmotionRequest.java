package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsEmotion4UpdateEmotionRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Integer packId;
    @NotNull
    private Integer id;
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
