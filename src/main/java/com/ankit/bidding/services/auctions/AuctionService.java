package com.ankit.bidding.services.auctions;

import com.ankit.bidding.dto.AuctionDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.Category;
import com.ankit.bidding.models.Slot;
import com.ankit.bidding.models.Vendor;
import com.ankit.bidding.repository.AuctionRepository;
import com.ankit.bidding.repository.CategoryRepository;
import com.ankit.bidding.repository.SlotRepository;
import com.ankit.bidding.repository.VendorRepository;
import com.ankit.bidding.services.auctions.validator.AuctionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    private final Logger logger = LoggerFactory.getLogger(AuctionService.class);

    public Auction createAuction(AuctionDto auctionDto) throws BusinessValidationException, SystemFailureExeption {

        try {
            // Validate the request DTO
            AuctionValidator.validate(auctionDto);

            // Fetch the related entities
            Slot slot = slotRepository.findById(auctionDto.getSlotId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid slot ID"));

            Category category = categoryRepository.findById(auctionDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

            Vendor vendor = vendorRepository.findById(auctionDto.getVendorId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid vendor ID"));

            // Create and populate the Auction entity
            Auction auction = new Auction();
            auction.setProductName(auctionDto.getProductName());
            auction.setBasePrice(auctionDto.getBasePrice());
            auction.setSlot(slot);
            auction.setCategory(category);
            auction.setVendor(vendor);

            // Save and return the Auction entity
            return auctionRepository.save(auction);
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            throw  new BusinessValidationException(e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public List<Auction> showAuctions() throws SystemFailureExeption {
        try {
            return auctionRepository.findAuctionsByCurrentTime(LocalDateTime.now());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }
}

