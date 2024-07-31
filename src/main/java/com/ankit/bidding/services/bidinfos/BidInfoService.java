package com.ankit.bidding.services.bidinfos;

import com.ankit.bidding.dto.BidRequest;
import com.ankit.bidding.execption.EntityNotFoundException;
import com.ankit.bidding.execption.InvalidBidException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.BidInfo;
import com.ankit.bidding.models.Bidder;
import com.ankit.bidding.repository.AuctionRepository;
import com.ankit.bidding.repository.BidInfoRepository;
import com.ankit.bidding.repository.BidderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidInfoService {

    @Autowired
    private BidInfoRepository bidInfoRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidderRepository bidderRepository;

    private final Logger logger = LoggerFactory.getLogger(BidInfoService.class);

    public BidInfo addBid(BidRequest bidRequest) {
        Optional<Auction> optionalAuction = auctionRepository.findById(bidRequest.getAuctionId());
        if (optionalAuction.isEmpty()) {
            throw new EntityNotFoundException("Auction not found with ID: " + bidRequest.getAuctionId());
        }
        Auction auction = optionalAuction.get();

        Optional<Bidder> optionalBidder = bidderRepository.findById(bidRequest.getBidderId());
        if (optionalBidder.isEmpty()) {
            throw new EntityNotFoundException("Bidder not found with ID: " + bidRequest.getBidderId());
        }
        Bidder bidder = optionalBidder.get();

        // Validate bid amount
        if (bidRequest.getBidAmount().compareTo(auction.getBasePrice()) <= 0) {
            throw new IllegalArgumentException("Bid amount must be greater than auction base price.");
        }

        // Validate timestamp
        if (bidRequest.getTimestamp().isAfter(auction.getSlot().getEndTime())) {
            throw new InvalidBidException("Bid timestamp must be before auction slot end time.");
        }

        // Create BidInfo entity
        BidInfo bidInfo = new BidInfo();
        bidInfo.setBidAmount(bidRequest.getBidAmount());
        bidInfo.setTimestamp(bidRequest.getTimestamp());
        bidInfo.setBidder(bidder);
        bidInfo.setAuction(auction);

        // Save BidInfo
        return bidInfoRepository.save(bidInfo);
    }

    public List<BidInfo> showAll(){
        return bidInfoRepository.findAll();
    }

    public List<BidInfo> bidHistoryByBidderId(Long bidderId) throws SystemFailureExeption {
        try {
            System.out.println(bidderId);
            return bidInfoRepository.findByBidderId(bidderId);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

}

