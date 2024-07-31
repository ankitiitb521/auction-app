package com.ankit.bidding.services.slots;

import com.ankit.bidding.dto.SlotDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.models.Slot;
import com.ankit.bidding.repository.SlotRepository;
import com.ankit.bidding.services.slots.strategy.ShowSlotStrategy;
import com.ankit.bidding.services.utils.SlotValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SlotService {

    private final Logger logger = LoggerFactory.getLogger(SlotService.class);

    @Autowired
    private ShowSlotStrategy showSlotStrategy;

    @Autowired
    SlotRepository slotRepository;

    public List<Slot> showSlots() throws SystemFailureExeption {
        try {
            return showSlotStrategy.showSlots();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public Slot addSlot(SlotDto slotDto) throws Exception {
        try {
            Slot slot = SlotValidator.validate(slotDto);
            return saveSlot(slot);
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            throw  new BusinessValidationException(e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    @Transactional
    private Slot saveSlot(Slot slot){
        return slotRepository.save(slot);
    }
}
