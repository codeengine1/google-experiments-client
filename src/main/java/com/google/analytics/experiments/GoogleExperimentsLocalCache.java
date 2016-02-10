package com.google.analytics.experiments;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsLocalCache {
    private final GoogleExperimentsClient _client;
    private volatile GoogleExperimentsResult _googleExperimentsResult;

    public GoogleExperimentsLocalCache(GoogleExperimentsSettings settings,
                                       Duration refreshInterval) {
        this(new GoogleExperimentsClient(settings), refreshInterval);
    }

    public GoogleExperimentsLocalCache(GoogleExperimentsClient client,
                                       Duration refreshInterval) {
        _client = client;
        fetchExperiments();

        Timer timer = new Timer();
        timer.schedule(new UpdateCacheTimer(), 0, refreshInterval.toMillis());
    }

    public GoogleExperimentsResult getExperiments() {
        return _googleExperimentsResult;
    }

    private class UpdateCacheTimer extends TimerTask {
        public void run() {
            fetchExperiments();
        }
    }

    private void fetchExperiments() {
        _googleExperimentsResult = _client.fetchExperiments();
    }
}
