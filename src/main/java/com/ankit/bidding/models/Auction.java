package com.ankit.bidding.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Auction implements Serializable {

    @Serial
    private static final long serialVersionUID = -7701472919551240348L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // check in service layer
    private String productName;

    // check in serice layer
    private BigDecimal basePrice;

    @ManyToOne()
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

}
