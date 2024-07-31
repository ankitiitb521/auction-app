package com.ankit.bidding.controllers;

import com.ankit.bidding.constants.MessageConstant;
import com.ankit.bidding.dto.BidRequest;
import com.ankit.bidding.models.BidInfo;
import com.ankit.bidding.services.bidinfos.BidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidInfoService bidInfoService;

    @PostMapping(value="/")
    public BidInfo createBid(@RequestBody BidRequest bidRequest) {
        return bidInfoService.addBid(bidRequest);
    }

    @GetMapping(value = "/")
    public List<BidInfo> getAllBids(){
        return bidInfoService.showAll();
    }

    @GetMapping(value = "/bidders/{bidderId}")
    public Response getBidHistoryByBidderId(@PathVariable Long bidderId){
        try {
            return Response.status(200).entity(bidInfoService.bidHistoryByBidderId(bidderId)).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

//    @GetMapping(value = "/{id}")
//    public Optional<BidInfo> getBid(@PathVariable Integer id){
//        return bidInfoRepository.findById(id);
//    }
}
