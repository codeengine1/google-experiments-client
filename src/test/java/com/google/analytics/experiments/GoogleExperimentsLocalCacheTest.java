package com.google.analytics.experiments;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
@Ignore
public class GoogleExperimentsLocalCacheTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsLocalCacheTest.class);

    @Test
    public void testLocalCacheRefresh() throws InterruptedException {
        Duration testDuration = Duration.of(3, ChronoUnit.SECONDS);
        GoogleExperimentsSettings settings = GoogleExperimentsClientTest.getTestSettings();
        GoogleExperimentsCache cache = new GoogleExperimentsLocalCache(settings, testDuration);

        for (int i = 0; i < 5; i++) {
            LOGGER.debug("Sleeping for 5 seconds ...");
            Thread.sleep(5000);
            LOGGER.debug(cache.getExperiments().toString());
        }
    }
}
