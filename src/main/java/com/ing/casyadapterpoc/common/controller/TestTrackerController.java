package com.ing.casyadapterpoc.common.controller;

import com.ing.casyadapterpoc.common.tracking.TestTracker;
import com.ing.casyadapterpoc.common.service.TestTrackerService;
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
