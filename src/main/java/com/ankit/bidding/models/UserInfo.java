package com.ankit.bidding.models;
import com.ankit.bidding.enums.Roles;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -7701472919551240347L;
    @Id
    private String id;

    private String email;

    private String name;

    private LocalDateTime joiningDate;

    private String password;

    private Roles roles;

    public Roles getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", joiningDate=" + joiningDate +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }
}
