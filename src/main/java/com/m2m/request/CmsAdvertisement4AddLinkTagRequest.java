package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAdvertisement4AddLinkTagRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer position;
    @NotNull
    private Long positionId;
}