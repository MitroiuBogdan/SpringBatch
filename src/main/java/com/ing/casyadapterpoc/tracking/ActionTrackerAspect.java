package com.ing.casyadapterpoc.tracking;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class ActionTrackerAspect {

    TestTrackerRepository testTrackerRepository;

    @Around(value = "@annotation(TrackedAction)")
    public Object trackAction(ProceedingJoinPoint joinPoint, TrackedAction trackedAction) throws Throwable {
        Object result = joinPoint.proceed();
        if(result instanceof Mono){
            ((Mono<?>) result).transformDeferredContextual((mono, contextView) -> {
                String testTrackerId = contextView.get("testTrackerId");

                TestTracker testTracker = testTrackerRepository.findById(testTrackerId);
                testTracker.incrementActionAttempt(trackedAction.action());
                testTrackerRepository.save(testTracker);

                return mono;
            });
        }
        return result;
    }
}
