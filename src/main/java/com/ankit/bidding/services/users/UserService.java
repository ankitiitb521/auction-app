package com.ankit.bidding.services.users;

import com.ankit.bidding.dto.RegistrationDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.models.Bidder;
import com.ankit.bidding.models.Category;
import com.ankit.bidding.models.UserInfo;
import com.ankit.bidding.models.Vendor;
import com.ankit.bidding.repository.BidderRepository;
import com.ankit.bidding.repository.UserRepository;
import com.ankit.bidding.repository.VendorRepository;
import com.ankit.bidding.services.enums.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private BidderRepository bidderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public void registerUser(RegistrationDto registrationDto) throws BusinessValidationException, SystemFailureExeption {
        try {
            UserInfo userInfo = RegistrationValidator.validate(registrationDto);
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            if(userInfo.getRoles().equals(Roles.VENDOR)){
                Vendor vendor = new Vendor();
                vendor.setBusinessName(registrationDto.getBusinessName());
                vendor.setUserInfo(userInfo);
                vendorRepository.save(vendor);
            }
            else {
                Bidder bidder = new Bidder();
                bidder.setNotificationType(registrationDto.getNotificationType());
                bidder.setUserInfo(userInfo);
                bidderRepository.save(bidder);
            }
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            throw  new BusinessValidationException(e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public Optional<UserInfo> getUserById(String userId) throws SystemFailureExeption {
        try {
            return userRepository.findById(userId);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public Optional<Vendor> getVendorByUserId(String userId) throws SystemFailureExeption {
        try {
            return Optional.ofNullable(vendorRepository.findByUserInfoId(userId));
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public Optional<Bidder> getBidderByUserId(String userId) throws SystemFailureExeption {
        try {
            return Optional.ofNullable(bidderRepository.findByUserInfoId(userId));
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public List<Vendor> getAllVendors() throws SystemFailureExeption {
        try {
            return vendorRepository.findAll();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public List<Bidder> getAllBidders() throws SystemFailureExeption {
        try {
            return bidderRepository.findAll();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

}
