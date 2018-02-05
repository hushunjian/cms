package com.m2m.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsIndustry4SortEntryRequest extends CmsCredentialRequest implements Serializable {
	@NotNull
    private SortEntry[] targetData;

    @Data
    public static class SortEntry {
        private Long id;
        private Integer sort;
    }
}
