package com.m2m.response;

import com.m2m.entity.ExtLottery;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CmsKingdom4AllLotteryResponse implements Serializable {
    private Long total;
    private List<ExtLottery> data = new ArrayList<ExtLottery>();
}
