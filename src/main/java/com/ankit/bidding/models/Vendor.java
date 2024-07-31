package com.ankit.bidding.models;

import com.ankit.bidding.factory.AppUser;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
public class Vendor implements AppUser,Serializable {

    @Serial
    private static final long serialVersionUID = -7701472919551240346L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserInfo userInfo;


    public String getBusinessName() {
        return businessName;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
