package com.m2m.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsKingdom4SetKingdomRestTimeRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Date startAt;
    @NotNull
    private Date endAt;
}
