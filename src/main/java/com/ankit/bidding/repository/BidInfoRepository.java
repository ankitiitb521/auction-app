package com.ankit.bidding.repository;

import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.BidInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidInfoRepository extends JpaRepository<BidInfo,Integer> {
    List<BidInfo> findByAuctionOrderByBidAmountDesc(Auction auction);
}
