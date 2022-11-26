package com.softengine.metrics.springmetrics;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class MetricsService {
    private AtomicInteger meter;

    @Autowired
    public MetricsService(MeterRegistry meterRegistry){
        this.meter = meterRegistry.gauge("random.number.metrics", new AtomicInteger(0));
    }

    @Scheduled(fixedRate = 2000)
    public void postMetric(){
        meter.set(ThreadLocalRandom.current().nextInt(100));
    }

}
