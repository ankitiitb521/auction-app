package com.ankit.bidding.services.auctions.validator;

import com.ankit.bidding.dto.AuctionDto;

import java.math.BigDecimal;

public class AuctionValidator {
    public static void validate(AuctionDto auctionDto) {
        if (auctionDto.getProductName() == null || auctionDto.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        if (auctionDto.getBasePrice() == null || auctionDto.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base price must be greater than 0");
        }
    }
}

