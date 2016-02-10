package com.google.analytics.experiments;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsDistributedCacheTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsDistributedCacheTest.class);

    @Test
    public void testDistributedCacheRefresh() throws InterruptedException, IOException {
        Duration testDuration = Duration.of(3, ChronoUnit.SECONDS);
        GoogleExperimentsSettings settings = GoogleExperimentsClientTest.getTestSettings();
        GoogleExperimentsCache cache = new GoogleExperimentsDistributedCache(
                settings,
                Arrays.asList("localhost:11211"),
                testDuration
        );

        for (int i = 0; i < 5; i++) {
            LOGGER.debug("Sleeping for 1 seconds ...");
            Thread.sleep(1000);
            LOGGER.debug(cache.getExperiments().toString());
        }
    }
}
