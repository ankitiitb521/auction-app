package com.ankit.bidding.repository;

import com.ankit.bidding.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Long> {

    @Query("SELECT s FROM Slot s WHERE s.startTime BETWEEN :start AND :end")
    List<Slot> findSlotsByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM Slot s WHERE s.endTime < :currentTime AND s.isNotified = false")
    List<Slot> findSlotsToBeNotified(@Param("currentTime") LocalDateTime currentTime);

}
