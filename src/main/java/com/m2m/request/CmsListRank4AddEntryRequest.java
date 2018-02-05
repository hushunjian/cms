package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsListRank4AddEntryRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long sourceId;
    @NotNull
    private Integer type;
    @NotNull
    private TargetInfo[] targetData;

    @Data
    public static class TargetInfo {
        private Long targetId;
        private Integer sort;
    }

}
