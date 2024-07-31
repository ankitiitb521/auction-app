package com.ankit.bidding.dto;

import java.io.Serial;
import java.io.Serializable;

public class CategoryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7197065609469596776L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
