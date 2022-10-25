package com.ing.casyadapterpoc.vendor.saltedge.batch.listeners;

import com.ing.casyadapterpoc.exceptions.InternalException;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.SkipProcessListener;
import javax.batch.api.chunk.listener.SkipReadListener;
import javax.batch.api.chunk.listener.SkipWriteListener;
import java.util.List;

public class AccountSkipListener implements SkipReadListener, SkipProcessListener, SkipWriteListener {


    @OnSkipInRead
    public void onSkipReadItem(Exception e) throws Exception {
        if (e instanceof RuntimeException) {
            System.out.println("onSkipReadItem");
        }
    }

    @Override
    public void onSkipProcessItem(Object o, Exception e) throws Exception {
//        System.out.println("HELLO");
        if (e instanceof RuntimeException) {
            System.out.println("HELLO");
        }
    }

    @OnSkipInProcess
    public void onSkipProcessItem2(Object o, Exception e) throws Exception {
        if (e instanceof RuntimeException) {
            System.out.println("onSkipProcessItem2" + e);
        }
    }

    @Override
    public void onSkipWriteItem(List<Object> list, Exception e) throws Exception {
        if (e instanceof InternalException) {
            System.out.println("HELLO");
        }
    }
}
