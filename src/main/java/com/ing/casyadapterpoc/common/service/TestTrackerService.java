package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.tracking.TestTracker;
import com.ing.casyadapterpoc.common.tracking.TestTrackerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestTrackerService {

    private TestTrackerRepository testTrackerRepository;

    public String initiateTestTracker(Vendor vendor){
        new TestTracker(vendor);
        return testTrackerRepository.save(new TestTracker(vendor)).getId();
    }
}
