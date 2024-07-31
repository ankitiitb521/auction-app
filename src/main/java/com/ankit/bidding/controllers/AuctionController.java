package com.ankit.bidding.controllers;

import com.ankit.bidding.constants.MessageConstant;
import com.ankit.bidding.dto.AuctionDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.services.auctions.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/auctions/")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping(value="/")
    public Response createAuction(@RequestBody AuctionDto auctionDto) {
        try {
            return Response.status(201).entity(auctionService.createAuction(auctionDto)).build();
        } catch (BusinessValidationException e){
            return Response.status(400).entity(e.getMessage()).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/all")
    public Response showAuctions(){
        try {
            return Response.status(200).entity(auctionService.showAuctions()).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/vendors/{vendorId}")
    public Response getAuctionHistory(@PathVariable Long vendorId){
        try {
            System.out.println(vendorId);
            return Response.status(200).entity(auctionService.auctionHistory(vendorId)).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

}
