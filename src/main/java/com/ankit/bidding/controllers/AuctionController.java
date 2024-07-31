package com.ankit.bidding.controllers;

import com.ankit.bidding.dto.AuctionDto;
import com.ankit.bidding.models.Auction;
import com.ankit.bidding.repository.AuctionRepository;
import com.ankit.bidding.services.auctions.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping(value="/")
    public Auction addAuction(@RequestBody AuctionDto auctionDto) {
        try {
            return auctionService.createAuction(auctionDto);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/")
    public List<Auction> getAllAuction(){
        try {
            return auctionService.showAuctions();
        } catch (Exception e){
            return null;
        }
    }

}
