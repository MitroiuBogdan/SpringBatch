package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.callback.NotifyCallback;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.callback.NotifyCallbackData;
import com.ing.casyadapterpoc.vendor.saltedge.service.SaltEdgeCallbackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/saltedge/callback")
@AllArgsConstructor
public class CallbackController {

    private final SaltEdgeCallbackService callbackService;

    @PostMapping("/notify")
    public void notifyMessage(@RequestBody NotifyCallback message) {
        log.info("notifyMessage - Notify callback is received with payload: {}", safeLog(message.getData()));
        NotifyCallbackData notifyMessage = message.getData();
        if (isResponseValid(notifyMessage)) {
            callbackService.processNotifyCallback(notifyMessage);
        } else {
            log.error("notifyMessage - Cannot process callback {}", safeLog(notifyMessage));
        }

    }

    public boolean isResponseValid(NotifyCallbackData data) {
        return null != data && null != data.getStage() && null != data.getConnectionId() && null != data.getCustomerId();
    }

    public String safeLog(NotifyCallbackData data) {
        return null != data ? data.toString() : "Empty callback";
    }
}
