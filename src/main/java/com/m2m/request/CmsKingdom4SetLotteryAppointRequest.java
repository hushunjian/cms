package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4SetLotteryAppointRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long lotteryId;

    @NotNull
    private Long userId;

    @NotNull
    private Integer isAppoint;
}
