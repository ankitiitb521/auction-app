package com.ankit.bidding.repository;

import com.ankit.bidding.models.Bidder;
import com.ankit.bidding.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder,Long> {
    @Query("SELECT b FROM Vendor b WHERE b.userInfo.id = :userId")
    Bidder findByUserInfoId(@Param("userId") String userId);
}
