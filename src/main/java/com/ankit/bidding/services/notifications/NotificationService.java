package com.ankit.bidding.services.notifications;

import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.Bidder;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(Bidder winner, Auction auction) {
        // Implement email notification logic here
        System.out.println("Sending email to " + winner.getUserInfo().getEmail() + " for winning the auction: " + auction.getProductName());
    }
}

