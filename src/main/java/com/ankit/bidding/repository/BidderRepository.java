package com.ankit.bidding.repository;

import com.ankit.bidding.models.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder,Long> {
}
