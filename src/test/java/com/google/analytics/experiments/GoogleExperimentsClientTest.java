package com.google.analytics.experiments;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
@Ignore
public class GoogleExperimentsClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsClientTest.class);

    public static GoogleExperimentsSettings getTestSettings() {
        Config config = ConfigFactory.load("test.application.conf");
        Config gaConfig = config.getConfig("google.analytics");
        return new GoogleExperimentsSettings(
                gaConfig.getString("serviceAccount.email"),
                gaConfig.getString("serviceAccount.key"),
                gaConfig.getString("accountId"),
                gaConfig.getString("trackingId"),
                gaConfig.getString("viewId")
        );
    }

    @Test
    public void testFetchExperiments() {
        GoogleExperimentsClient service = new GoogleExperimentsClient(getTestSettings());
        final GoogleExperimentsResult result = service.fetchExperiments();
        LOGGER.debug(result.toString());
    }
}
