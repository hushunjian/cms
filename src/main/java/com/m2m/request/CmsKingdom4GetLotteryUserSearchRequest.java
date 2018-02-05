package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4GetLotteryUserSearchRequest extends CmsCredentialRequest implements Serializable {

    @NotNull
    private Long pageIndex;
    @NotNull
    private Long pageSize;
    @NotNull
    private Integer lotteryId;

    private String userNick;

    private Integer isAppoint;
}
