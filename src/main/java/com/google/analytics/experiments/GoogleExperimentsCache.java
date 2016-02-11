package com.google.analytics.experiments;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public interface GoogleExperimentsCache {
    /**
     * @return googleExperimentsResult
     */
    public GoogleExperimentsResult getExperiments();

    /**
     * @param experimentId
     * @return isActive
     */
    public boolean isExperimentActive(String experimentId);

    /**
     * @param experimentId
     * @param variationName
     * @return isActive
     */
    public boolean isVariationActive(String experimentId, String variationName);
}
