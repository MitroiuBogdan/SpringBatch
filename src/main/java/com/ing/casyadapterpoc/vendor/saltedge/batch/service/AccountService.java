package com.ing.casyadapterpoc.vendor.saltedge.batch.service;

import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccountService {

    public static int index = 0;

    public List<Account> getAccounts() {
//        if (index == 0) {
            throw new RuntimeException("HELLLO");
//        } else {
//            System.out.println("getAccounts - " + index);
//            index++;
//            return IntStream.range(0, 100)
//                    .mapToObj(operand -> new Account(String.valueOf(Math.random())))
//                    .collect(Collectors.toList());
        }
    }

