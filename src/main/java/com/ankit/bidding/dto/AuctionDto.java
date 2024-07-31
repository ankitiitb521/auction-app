package com.ankit.bidding.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class AuctionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7197065609469596775L;

    private String productName;

    private BigDecimal basePrice;

    private Long slotId;

    private Long categoryId;

    private Long vendorId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}

