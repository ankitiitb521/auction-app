package com.ankit.bidding.services.slots.strategy.impl;

import com.ankit.bidding.models.Slot;
import com.ankit.bidding.repository.SlotRepository;
import com.ankit.bidding.services.slots.strategy.ShowSlotStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpcomingSlotStrategy implements ShowSlotStrategy {

    private final Logger logger = LoggerFactory.getLogger(ShowSlotStrategy.class);

    @Autowired
    private SlotRepository slotRepository;


    @Override
    public List<Slot> showSlots() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoDaysLater = now.plusDays(2);
        return slotRepository.findSlotsByStartTimeBetween(now, twoDaysLater);
    }
}
