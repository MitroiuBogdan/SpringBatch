package com.ing.casyadapterpoc.controller;

import com.ing.casyadapterpoc.service.TestTrackerService;
import com.ing.casyadapterpoc.tracking.TestTracker;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("tests/{testTrackerId}")
@Log
public class TestTrackerController {

    private TestTrackerService testTrackerService;


    @GetMapping
    public TestTracker getTestTracker(@PathVariable String testTrackerId){
        return null;
    }

}
