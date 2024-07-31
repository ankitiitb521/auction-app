package com.ankit.bidding.services.schedules;

import com.ankit.bidding.models.Auction;
import com.ankit.bidding.models.BidInfo;
import com.ankit.bidding.models.Bidder;
import com.ankit.bidding.models.Slot;
import com.ankit.bidding.repository.AuctionRepository;
import com.ankit.bidding.repository.BidInfoRepository;
import com.ankit.bidding.repository.SlotRepository;
import com.ankit.bidding.services.notifications.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidInfoRepository bidInfoRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 * * * *") // Every hour at the start of the hour
    public void notifyAuctionWinners() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Slot> slotsToBeNotified = slotRepository.findSlotsToBeNotified(currentTime);

        for (Slot slot : slotsToBeNotified) {
            List<Auction> auctions = auctionRepository.findBySlot(slot);
            for (Auction auction : auctions) {
                List<BidInfo> bids = bidInfoRepository.findByAuctionOrderByBidAmountDesc(auction);
                if (!bids.isEmpty()) {
                    BidInfo highestBid = bids.get(0);
                    Bidder winner = highestBid.getBidder();
                    notificationService.sendNotification(winner, auction);
                }
            }
            slot.setNotified(true);
            slotRepository.save(slot);
        }
    }
}

