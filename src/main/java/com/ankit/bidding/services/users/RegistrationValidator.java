package com.ankit.bidding.services.users;

import com.ankit.bidding.dto.RegistrationDto;
import com.ankit.bidding.models.UserInfo;
import com.ankit.bidding.services.enums.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class RegistrationValidator {

    static Logger logger = LoggerFactory.getLogger(RegistrationValidator.class);

    public static UserInfo validate(RegistrationDto registrationDto){

        if (registrationDto.getId() == null || registrationDto.getId().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

        if (registrationDto.getEmail() == null || registrationDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (registrationDto.getName() == null || registrationDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (registrationDto.getPassword() == null || registrationDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (registrationDto.getRoles() == null || registrationDto.getRoles().isEmpty()) {
            throw new IllegalArgumentException("Roles cannot be null or empty");
        }

        try {
            Roles.valueOf(registrationDto.getRoles());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role. Role must be either BIDDER or VENDOR");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(registrationDto.getId());
        userInfo.setEmail(registrationDto.getEmail());
        userInfo.setName(registrationDto.getName());
        userInfo.setPassword(registrationDto.getPassword());
        if(registrationDto.getRoles().equals(Roles.BIDDER.name())){
            userInfo.setRoles(Roles.BIDDER);
        } else {
            userInfo.setRoles(Roles.VENDOR);
        }
        userInfo.setJoiningDate(LocalDateTime.now());
        return userInfo;
    }
}
