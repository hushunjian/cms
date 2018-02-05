package com.m2m.response;

import com.m2m.entity.ExtLotteryUser;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKingdomGetLotteryUsersResponse implements Serializable {
    private Long total;
    private List<ExtLotteryUser> data = new ArrayList<ExtLotteryUser>();
}
