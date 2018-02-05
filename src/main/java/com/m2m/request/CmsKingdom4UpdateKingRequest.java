package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4UpdateKingRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long topicId;
    @NotNull
    private Long sourceUid;
    @NotNull
    private Long targetUid;
    @NotNull
    private Long optUid;
}
