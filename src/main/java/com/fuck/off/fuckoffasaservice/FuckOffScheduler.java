package com.fuck.off.fuckoffasaservice;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configuration
@EnableScheduling
@SuppressWarnings({"WeakerAccess", "unused"})
public class FuckOffScheduler {

    private static final Logger LOGGER = Logger.getLogger(FuckOffScheduler.class);

    private static final String URL = "http://localhost:8080/rest/fuck-off/michael-couck";

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(200);

    private static final Random RANDOM = new Random();
    private static final List<Future<?>> FUTURES = new LinkedList<>();

    private static final long START = System.currentTimeMillis();

    @Scheduled(fixedRate = 5000)
    public void callService() {
        /*Future<?> future = EXECUTOR_SERVICE.submit(() -> {
            // Call the faas service
            //noinspection InfiniteLoopStatement
            long end = 0;
            do {
                end = System.currentTimeMillis();
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
                HttpStatus httpStatus = responseEntity.getStatusCode();
                Assert.isTrue(httpStatus.value() == 200);
                if (RANDOM.nextInt(10000000) % 1000 == 0) {
                    printLoad();
                    LOGGER.info("Status : " + httpStatus.value() + ", " + Thread.currentThread().getId() + ", " + FUTURES.size());
                }
                try {
                    Thread.sleep(10);
                } catch (final InterruptedException e) {
                    LOGGER.error(null, e);
                }
            } while(end - START < 600000);
        });
        FUTURES.add(future);*/
    }

    private void printLoad() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        double systemLoad = operatingSystemMXBean.getSystemLoadAverage();
        double availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        final double systemLoadPerCore = systemLoad / availableProcessors;
        LOGGER.info("System load per core : " + systemLoadPerCore + ", " + systemLoad + ", " + availableProcessors);
    }

}