package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.callback.NotifyCallbackData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SaltEdgeCallbackService {

    private final SaltEdgeRefreshService saltedgeRefreshService;
    private final static String FINISH = "finish";
    private final static String INTERACTIVE = "interactive";

    public void processNotifyCallback(NotifyCallbackData notifyData) {
        String connectionId = notifyData.getConnectionId();
        String stage = notifyData.getStage();
        switch (stage) {
            case FINISH:
                log.info("processNotifyCallback - Success callback is received, starting to fetch data for: {}", connectionId);
                saltedgeRefreshService.startDataFetching(connectionId);
                break;
            case INTERACTIVE:
                log.info("processNotifyCallback - Interactive callback is received");
                break;
            default:
                log.warn("processNotifyCallback - No logic defined");
        }
    }
}
