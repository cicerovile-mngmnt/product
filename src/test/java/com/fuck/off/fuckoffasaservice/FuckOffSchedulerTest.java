package com.fuck.off.fuckoffasaservice;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class FuckOffSchedulerTest {

    @Spy
    private FuckOffScheduler fuckOffScheduler;

    @Test
    @Ignore
    public void callService() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            fuckOffScheduler.callService();
            Thread.sleep(1000);
        }
    }

}
