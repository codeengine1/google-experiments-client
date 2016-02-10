package com.google.analytics.experiments;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public interface GoogleExperimentsCache {
    /**
     * @return googleExperimentsResult
     */
    public GoogleExperimentsResult getExperiments();
}
