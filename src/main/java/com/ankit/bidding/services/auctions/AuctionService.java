package com.ankit.bidding.services.auctions;

import com.ankit.bidding.dto.AuctionDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.EntityNotFoundException;
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
import java.util.Map;
import java.util.stream.Collectors;

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
            AuctionValidator.validate(auctionDto);

            Slot slot = slotRepository.findById(auctionDto.getSlotId())
                    .orElseThrow(() -> new EntityNotFoundException("Slot not found with ID: " + auctionDto.getSlotId()));

            Category category = categoryRepository.findById(auctionDto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + auctionDto.getCategoryId()));

            Vendor vendor = vendorRepository.findById(auctionDto.getVendorId())
                    .orElseThrow(() ->  new EntityNotFoundException("Slot not found with ID: " + auctionDto.getSlotId()));

            // Create and populate the Auction entity
            Auction auction = new Auction();
            auction.setProductName(auctionDto.getProductName());
            auction.setBasePrice(auctionDto.getBasePrice());
            auction.setSlot(slot);
            auction.setCategory(category);
            auction.setVendor(vendor);

            // Save and return the Auction entity
            return auctionRepository.save(auction);
        } catch (IllegalArgumentException  | EntityNotFoundException e){
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
//            Map<Long,List<Auction>> list = auctionRepository.findAuctionsByCurrentTime(LocalDateTime.now()).stream().collect(Collectors.groupingBy(auction -> auction.getCategory().getId()))
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public List<Auction> auctionHistory(Long vendorId) throws SystemFailureExeption {
        try {
            System.out.println(vendorId);
            return auctionRepository.findByVendorId(vendorId);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }
}

