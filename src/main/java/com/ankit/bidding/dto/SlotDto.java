package com.ankit.bidding.dto;

import java.io.Serial;
import java.io.Serializable;

public class SlotDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7197065609469596776L;

    private String startTime;

    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
