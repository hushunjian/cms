package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsListRank4SortEntryRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private SortEntry[] targetData;

    @Data
    public static class SortEntry {

        private Long id;

        private Integer sort;
    }
}
