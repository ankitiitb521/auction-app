package com.ankit.bidding.controllers;

import com.ankit.bidding.dto.BidRequest;
import com.ankit.bidding.models.BidInfo;
import com.ankit.bidding.repository.BidInfoRepository;
import com.ankit.bidding.services.bidinfos.BidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidInfoService bidInfoService;

    @PostMapping(value="/")
    public BidInfo addBid(@RequestBody BidRequest bidRequest) {
        return bidInfoService.addBid(bidRequest);
    }

    @GetMapping(value = "/")
    public List<BidInfo> getAllBids(){
        return bidInfoService.showAll();
    }

//    @GetMapping(value = "/{id}")
//    public Optional<BidInfo> getBid(@PathVariable Integer id){
//        return bidInfoRepository.findById(id);
//    }
}
