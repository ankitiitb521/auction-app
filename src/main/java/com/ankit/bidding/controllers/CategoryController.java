package com.ankit.bidding.controllers;

import com.ankit.bidding.constants.MessageConstant;
import com.ankit.bidding.dto.CategoryDto;
import com.ankit.bidding.dto.SlotDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.services.categories.CategoryService;
import com.ankit.bidding.services.slots.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value="/")
    public Response addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryService.addCategory(categoryDto);
            return Response.status(201).entity("successfully created").build();
        } catch (BusinessValidationException e){
            return Response.status(500).entity(e.getMessage()).build();
        } catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/")
    public Response showCategories(){
        try {
            return Response.status(200).entity(categoryService.showCategories()).build();
        }
        catch (Exception e){
            return Response.status(500).entity(MessageConstant.SERVER_ERROR).build();
        }
    }
}
