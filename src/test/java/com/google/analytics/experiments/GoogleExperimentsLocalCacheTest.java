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
        Duration testDuration = Duration.of(2, ChronoUnit.SECONDS);
        GoogleExperimentsSettings settings = GoogleExperimentsClientTest.getTestSettings();
        GoogleExperimentsCache cache = new GoogleExperimentsLocalCache(settings, testDuration);
        final String experimentId = "3PVvw9CBSi-dAbphi3waoQ";

        for (int i = 0; i < 10; i++) {
            LOGGER.debug("Sleeping for 2.5 seconds ...");
            Thread.sleep(2500);
            GoogleExperimentsVariation variation = cache.selectVariation(experimentId);
            int index = cache.getVariationIndex(experimentId, variation.getName());
            LOGGER.debug("Experiment {}; Variation {}:{}", experimentId, variation.getName(), index);
        }
    }
}
