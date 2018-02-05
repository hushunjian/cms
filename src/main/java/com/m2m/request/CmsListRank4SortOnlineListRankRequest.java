package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsListRank4SortOnlineListRankRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private ListRank[] targetData;

    @Data
    public static class ListRank {
        private Integer id;
        private Integer sort;
    }
}
