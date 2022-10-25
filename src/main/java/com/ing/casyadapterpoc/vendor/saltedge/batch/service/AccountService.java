package com.ing.casyadapterpoc.vendor.saltedge.batch.service;

import com.ing.casyadapterpoc.exceptions.InternalException;
import com.ing.casyadapterpoc.exceptions.InternalExceptionDictionary;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccountService {

    public static int index = 0;

    public LinkedList<Account> getAccounts() {
//        if (index == 0) {
//            throw new RuntimeException("HELLLO");
//        } else {
//        throw new RuntimeException(InternalExceptionDictionary.EXCEPTION.getMessage());
//            index++;
        return IntStream.range(0, 100)
                .mapToObj(operand -> new Account(String.valueOf(Math.random())))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}

