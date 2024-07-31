package com.ankit.bidding.services.utils;

import com.ankit.bidding.dto.SlotDto;
import com.ankit.bidding.models.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SlotValidator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    static Logger logger = LoggerFactory.getLogger(SlotValidator.class);

    public static Slot validate(SlotDto slotDTO) throws IllegalArgumentException{
        LocalDateTime startTime = parseDate(slotDTO.getStartTime());
        LocalDateTime endTime = parseDate(slotDTO.getEndTime());

        LocalDateTime now = LocalDateTime.now();
//        if (startTime.isBefore(now)) {
//            logger.error("Start time must be greater than the current time");
//            throw new IllegalArgumentException("Start time must be greater than the current time");
//        }
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be greater than the start time");
        }
        Slot slot = new Slot();
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setNotified(false);
        return slot;
    }

    private static LocalDateTime parseDate(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'T'HH:mm:ss'");
        }
    }
}

