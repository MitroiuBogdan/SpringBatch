package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Vendor;
import com.ing.casyadapterpoc.tracking.TestTracker;
import com.ing.casyadapterpoc.tracking.TestTrackerRepository;
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
