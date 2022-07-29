package com.ing.casyadapterpoc.tracking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionTracker {

    private Action action;
    private Integer attempts;
    private Integer apiCalls;

    public ActionTracker(Action action) {
        this.action = action;
        this.attempts = 0;
        this.apiCalls = 0;
    }

    public void incrementAttempts(int increment){
        this.attempts += increment;
    }

    public void incrementApiCalls(int increment){
        this.apiCalls += increment;
    }
}
