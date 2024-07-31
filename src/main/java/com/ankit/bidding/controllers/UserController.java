package com.ankit.bidding.controllers;

import com.ankit.bidding.constants.MessageConstant;
import com.ankit.bidding.dto.RegistrationDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.models.UserInfo;
import com.ankit.bidding.models.Vendor;
import com.ankit.bidding.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    public Response addNewUser(@RequestBody RegistrationDto regDto) {
        try {
            userService.registerUser(regDto);
            return Response.status(201).entity("successfully registered").build();
        } catch (BusinessValidationException e){
            return Response.status(500).entity(e.getMessage()).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value ="/{id}")
    public Response getUser(@PathVariable String id){
        try {
            return Response.status(201).entity(userService.getUserById(id)).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value ="vendor/{id}")
    public Response getVendor(@PathVariable String id){
        try {
            return Response.status(200).entity(userService.getVendorByUserId(id)).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value ="bidder/{id}")
    public Response getBidder(@PathVariable String id){
        try {
            return Response.status(200).entity(userService.getBidderByUserId(id)).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/vendors")
    public Response getAllVendors(){
        try {
            return Response.status(201).entity(userService.getAllVendors()).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/bidders")
    public Response getAllBidders(){
        try {
            return Response.status(201).entity(userService.getAllBidders()).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }
}
