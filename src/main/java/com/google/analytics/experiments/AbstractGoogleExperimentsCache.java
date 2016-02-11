package com.google.analytics.experiments;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
abstract class AbstractGoogleExperimentsCache implements GoogleExperimentsCache {

    public boolean isExperimentActive(String experimentId) {
       GoogleExperimentsResult experiments = getExperiments();

        if (experiments == null) {
            return false;
        }

        return experiments.isExperimentActive(experimentId);
    }

    public boolean isVariationActive(String experimentId, String variationName) {
        GoogleExperimentsResult experiments = getExperiments();

        if (experiments == null) {
            return false;
        }

        return experiments.isVariationActive(experimentId, variationName);
    }

    public boolean hasActiveVariations(String experimentId) {
        GoogleExperimentsResult experiments = getExperiments();

        if (experiments == null) {
            return false;
        }

        return experiments.hasActiveVariations(experimentId);
    }

    public GoogleExperimentsVariation selectVariation(String experimentId) {
        GoogleExperimentsResult experiments = getExperiments();

        if (experiments == null) {
            return null;
        }

        return experiments.selectVariation(experimentId);
    }

    public Integer getVariationIndex(String experimentId, String variationName) {
        GoogleExperimentsResult experiments = getExperiments();

        if (experiments == null) {
            return null;
        }

        return experiments.getVariationIndex(experimentId, variationName);
    }
}
