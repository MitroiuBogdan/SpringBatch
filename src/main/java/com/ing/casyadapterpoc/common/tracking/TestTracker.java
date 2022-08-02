package com.ing.casyadapterpoc.common.tracking;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TestTracker {

    private String id;
    private Vendor vendor;
    private String userId;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private Map<Action, ActionTracker> actions;

    public TestTracker(Vendor vendor) {
        this.vendor = vendor;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.actions = new HashMap<>();
    }

    public void incrementActionAttempt(Action action){
        ActionTracker toSave = this.actions.getOrDefault(action, new ActionTracker(action));
        toSave.incrementAttempts(1);
        actions.put(toSave.getAction(),toSave);
    }

    public void incrementActionApiCalls(Action action){
        ActionTracker toSave = this.actions.getOrDefault(action, new ActionTracker(action));
        toSave.incrementApiCalls(1);
        actions.put(toSave.getAction(),toSave);
    }
}
