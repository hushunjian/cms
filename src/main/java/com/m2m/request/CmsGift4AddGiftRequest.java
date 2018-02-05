package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsGift4AddGiftRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String image;
    @NotNull
    private Integer imageWidth;
    @NotNull
    private Integer imageHeight;
    @NotNull
    private Integer price;
    @NotNull
    private Integer addPrice;
    private String gifImage;
    @NotNull
    private Integer duration;
    @NotNull
    private Integer sortNumber;
}
