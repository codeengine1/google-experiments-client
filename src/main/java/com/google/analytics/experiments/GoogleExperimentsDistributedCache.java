package com.google.analytics.experiments;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Initial implementation uses Memcached which is a widely available distributed cache.
 *
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsDistributedCache implements GoogleExperimentsCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsDistributedCache.class);
    private volatile GoogleExperimentsResult _googleExperimentsResult;
    private final GoogleExperimentsClient _experimentsClient;
    private final MemcachedClient _memcachedClient;
    private final Duration _refreshInterval;
    private final String _memcachedKey;

    /**
     * @param settings
     * @param memcachedNodes example: [host1.tld:11211, host2.tld:11211]
     * @throws IOException
     */
    public GoogleExperimentsDistributedCache(GoogleExperimentsSettings settings,
                                             List<String> memcachedNodes,
                                             Duration refreshInterval) throws IOException {
        List<InetSocketAddress> sockets = new ArrayList<InetSocketAddress>();

        for (String node : memcachedNodes) {
            String[] segments = node.split("\\:");
            sockets.add(new InetSocketAddress(segments[0], new Integer(segments[1])));
        }

        _memcachedClient = new MemcachedClient(sockets);
        _experimentsClient = new GoogleExperimentsClient(settings);
        _refreshInterval = refreshInterval;

        _memcachedKey = String.format(
                "google-experiments:%s:%s:%s",
                settings.getGoogleAnalyticsAccountId(),
                settings.getGoogleAnalyticsTrackingId(),
                settings.getGoogleAnalyticsViewId()
        );

        Timer timer = new Timer();
        timer.schedule(new UpdateCacheTimer(), refreshInterval.toMillis(), refreshInterval.toMillis());

        fetchExperiments();
    }

    public GoogleExperimentsResult getExperiments() {
        Object memcachedResult = _memcachedClient.get(_memcachedKey);

        if (memcachedResult == null) {
            return null;
        }

        GoogleExperimentsResult result = ((GoogleExperimentsCacheResult) memcachedResult).getGoogleExperimentsResult();
        return result;
    }

    private void fetchExperiments() {
        _googleExperimentsResult = _experimentsClient.fetchExperiments();
        _memcachedClient.add(
                _memcachedKey,
                (60 * 60 * 24),
                new GoogleExperimentsCacheResult(_googleExperimentsResult)
        );
    }

    private class UpdateCacheTimer extends TimerTask {
        public void run() {
            Object memcachedResult = _memcachedClient.get(_memcachedKey);

            if (memcachedResult == null) {
                fetchExperiments();
                return;
            }

            GoogleExperimentsCacheResult cachedResult = ((GoogleExperimentsCacheResult) memcachedResult);

            if (cachedResult.getDateLastUpdated() == null) {
                fetchExperiments();
                return;
            }

            if (cachedResult.getDateLastUpdated().plus(_refreshInterval).isBefore(LocalDateTime.now())) {
                return;
            }

            fetchExperiments();
        }
    }
}
