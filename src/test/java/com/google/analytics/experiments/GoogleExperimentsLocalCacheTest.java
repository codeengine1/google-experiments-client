package com.google.analytics.experiments;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsLocalCacheTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsLocalCacheTest.class);

    @Test
    public void testLocalCachRefresh() throws InterruptedException {
        Duration testDuration = Duration.of(3, ChronoUnit.SECONDS);
        GoogleExperimentsSettings settings = GoogleExperimentsClientTest.getTestSettings();
        GoogleExperimentsLocalCache cache = new GoogleExperimentsLocalCache(settings, testDuration);

        for (int i = 0; i < 5; i++) {
            LOGGER.debug("Sleeping for 5 seconds ...");
            Thread.sleep(5000);
        }
    }
}
