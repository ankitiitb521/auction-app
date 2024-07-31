package com.ankit.bidding.repository;

import com.ankit.bidding.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    @Query("SELECT v FROM Vendor v WHERE v.userInfo.id = :userId")
    Vendor findByUserInfoId(@Param("userId") String userId);
}
