package com.ankit.bidding.repository;

import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

    @Query("SELECT a FROM Auction a WHERE a.slot.startTime <= :currentTime AND a.slot.endTime >= :currentTime")
    List<Auction> findAuctionsByCurrentTime(@Param("currentTime") LocalDateTime currentTime);

    List<Auction> findBySlot(Slot slot);
}
