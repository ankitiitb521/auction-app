package com.ankit.bidding.controllers;

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
            return null;
        }
    }

    @GetMapping(value ="/{id}")
    public Response getUser(@PathVariable String id){
        try {
            return Response.status(201).entity(userService.getUserById(id)).build();
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping(value ="vendor/{id}")
    public Optional<Vendor> getVendor(@PathVariable String id){
        return userService.getVendorByUserId(id);
    }

    @GetMapping(value = "/vendors")
    public Response getAllVendors(){
        try {
            return Response.status(201).entity(userService.getAllVendors()).build();
        }
        catch (Exception e){
            return  null;
        }
    }

    @GetMapping(value = "/bidders")
    public Response getAllBidders(){
        try {
            return Response.status(201).entity(userService.getAllBidders()).build();
        }
        catch (Exception e){
            return  null;
        }
    }
}
