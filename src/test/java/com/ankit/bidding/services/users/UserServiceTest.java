package com.ankit.bidding.services.users;

import com.ankit.bidding.dto.RegistrationDto;
import com.ankit.bidding.enums.Roles;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.factory.AppUser;
import com.ankit.bidding.models.Bidder;
import com.ankit.bidding.models.UserInfo;
import com.ankit.bidding.models.Vendor;
import com.ankit.bidding.repository.BidderRepository;
import com.ankit.bidding.repository.UserRepository;
import com.ankit.bidding.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private BidderRepository bidderRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidVendor_ShouldReturnVendor() throws BusinessValidationException, SystemFailureExeption {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId("123");
        registrationDto.setEmail("test@example.com");
        registrationDto.setName("Test Vendor");
        registrationDto.setPassword("password");
        registrationDto.setRoles(Roles.VENDOR.name());
        registrationDto.setBusinessName("Test Business");

        UserInfo userInfo = new UserInfo();
        userInfo.setId(registrationDto.getId());
        userInfo.setEmail(registrationDto.getEmail());
        userInfo.setName(registrationDto.getName());
        userInfo.setPassword(registrationDto.getPassword());
        userInfo.setRoles(Roles.VENDOR);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(vendorRepository.save(any(Vendor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser result = userService.registerUser(registrationDto);

        assertNotNull(result);
        assertTrue(result instanceof Vendor);
        Vendor vendor = (Vendor) result;
        assertEquals("Test Business", vendor.getBusinessName());
        assertEquals("123", vendor.getUserInfo().getId());
        assertEquals("encodedPassword", vendor.getUserInfo().getPassword());

        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    void registerUser_ValidBidder_ShouldReturnBidder() throws BusinessValidationException, SystemFailureExeption {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId("124");
        registrationDto.setEmail("bidder@example.com");
        registrationDto.setName("Test Bidder");
        registrationDto.setPassword("password");
        registrationDto.setRoles(Roles.BIDDER.name());
        registrationDto.setNotificationType("EMAIL");

        UserInfo userInfo = new UserInfo();
        userInfo.setId(registrationDto.getId());
        userInfo.setEmail(registrationDto.getEmail());
        userInfo.setName(registrationDto.getName());
        userInfo.setPassword(registrationDto.getPassword());
        userInfo.setRoles(Roles.BIDDER);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(bidderRepository.save(any(Bidder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser result = userService.registerUser(registrationDto);

        assertNotNull(result);
        assertInstanceOf(Bidder.class, result);
        Bidder bidder = (Bidder) result;
        assertEquals("EMAIL", bidder.getNotificationType());
        assertEquals("124", bidder.getUserInfo().getId());
        assertEquals("encodedPassword", bidder.getUserInfo().getPassword());

        verify(bidderRepository, times(1)).save(any(Bidder.class));
    }


    @Test
    void getUserById_ExistingUser_ShouldReturnUserInfo() throws SystemFailureExeption {
        String userId = "123";
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userInfo));

        Optional<UserInfo> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_NonExistingUser_ShouldReturnEmpty() throws SystemFailureExeption {
        String userId = "123";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserInfo> result = userService.getUserById(userId);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_RepositoryException_ShouldThrowSystemFailureExeption() {
        String userId = "123";

        when(userRepository.findById(userId)).thenThrow(new RuntimeException("Database error"));

        SystemFailureExeption thrown = assertThrows(SystemFailureExeption.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("Database error", thrown.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    }
