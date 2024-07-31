package com.ankit.bidding.services.users;

import com.ankit.bidding.dto.RegistrationDto;
import com.ankit.bidding.enums.Roles;
import com.ankit.bidding.models.UserInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class RegistrationValidatorTest {

    @Test
    void validate_ValidRegistrationDto_ShouldReturnUserInfo() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId("123");
        registrationDto.setEmail("test@example.com");
        registrationDto.setName("Test User");
        registrationDto.setPassword("password");
        registrationDto.setRoles(Roles.BIDDER.name());

        UserInfo userInfo = RegistrationValidator.validate(registrationDto);

        assertNotNull(userInfo);
        assertEquals("123", userInfo.getId());
        assertEquals("test@example.com", userInfo.getEmail());
        assertEquals("Test User", userInfo.getName());
        assertEquals("password", userInfo.getPassword());
        assertEquals(Roles.BIDDER, userInfo.getRoles());
        assertNotNull(userInfo.getJoiningDate());
    }

    @Test
    void validate_NullId_ShouldThrowIllegalArgumentException() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@example.com");
        registrationDto.setName("Test User");
        registrationDto.setPassword("password");
        registrationDto.setRoles(Roles.BIDDER.name());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RegistrationValidator.validate(registrationDto);
        });

        assertEquals("ID cannot be null or empty", exception.getMessage());
    }

    @Test
    void validate_EmptyEmail_ShouldThrowIllegalArgumentException() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId("123");
        registrationDto.setName("Test User");
        registrationDto.setPassword("password");
        registrationDto.setRoles(Roles.BIDDER.name());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RegistrationValidator.validate(registrationDto);
        });

        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    void validate_InvalidRole_ShouldThrowIllegalArgumentException() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId("123");
        registrationDto.setEmail("test@example.com");
        registrationDto.setName("Test User");
        registrationDto.setPassword("password");
        registrationDto.setRoles("INVALID_ROLE");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RegistrationValidator.validate(registrationDto);
        });

        assertEquals("Invalid role. Role must be either BIDDER or VENDOR", exception.getMessage());
    }

    // Add more test cases for other invalid scenarios if needed
}
