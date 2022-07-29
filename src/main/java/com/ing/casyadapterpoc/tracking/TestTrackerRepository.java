package com.ing.casyadapterpoc.tracking;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TestTrackerRepository {

    private final Map<String, TestTracker> testTrackers;

    public TestTrackerRepository() {
        this.testTrackers = new HashMap<>();
    }

    public TestTracker save(TestTracker testTracker){

        String id = testTracker.getId();
        if(!StringUtils.hasText(id)){
            id = UUID.randomUUID().toString();
            testTracker.setId(id);
        }
        testTrackers.put(id,testTracker);
        return testTracker;
    }

    public TestTracker findById(String id){
        return testTrackers.get(id);
    }

}
