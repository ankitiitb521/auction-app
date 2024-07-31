package com.ankit.bidding.controllers;

import com.ankit.bidding.constants.MessageConstant;
import com.ankit.bidding.dto.SlotDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.services.slots.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping(value="/")
    public Response addSlot(@RequestBody SlotDto slotDto) {
        try {
            return Response.status(201).entity(slotService.addSlot(slotDto)).build();
        } catch (BusinessValidationException e){
            return Response.status(500).entity(e.getMessage()).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/")
    public Response getSlots(){
        try {
            return Response.status(201).entity(slotService.showSlots()).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }
}
