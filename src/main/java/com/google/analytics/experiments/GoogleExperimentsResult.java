package com.google.analytics.experiments;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleExperimentsResult {
    private final String _kind;
    private final String _username;
    private final Integer _totalResults;
    private final Integer _startIndex;
    private final Integer _itemsPerPage;
    private final List<GoogleExperimentsItem> _items;
    private String json;

    public GoogleExperimentsResult(@JsonProperty("kind") String kind,
                                   @JsonProperty("username") String username,
                                   @JsonProperty("totalResults") Integer totalResults,
                                   @JsonProperty("startIndex") Integer startIndex,
                                   @JsonProperty("itemsPerPage") Integer itemsPerPage,
                                   @JsonProperty("items") List<GoogleExperimentsItem> items) {
        _kind = kind;
        _username = username;
        _totalResults = totalResults;
        _startIndex = startIndex;
        _itemsPerPage = itemsPerPage;
        _items = items;
    }

    public String getKind() {
        return _kind;
    }

    public String getUsername() {
        return _username;
    }

    public Integer getTotalResults() {
        return _totalResults;
    }

    public Integer getStartIndex() {
        return _startIndex;
    }

    public Integer getItemsPerPage() {
        return _itemsPerPage;
    }

    public List<GoogleExperimentsItem> getItems() {
        return _items;
    }

    public String getJson() {
        return json;
    }

    public GoogleExperimentsResult setJson(String json) {
        this.json = json;
        return this;
    }

    public GoogleExperimentsItem getExperiment(String experimentId) {
        if (_items == null || _items.size() == 0) {
            return null;
        }

        for (GoogleExperimentsItem item : _items) {
            if (item.getId() == null
                    || item.getKind() == null
                    || !item.getKind().equals(GoogleExperimentsItem.EXPERIMENT_KIND)) {
                continue;
            }

            if (item.getId().equals(experimentId)) {
                return item;
            }
        }

        return null;
    }

    /**
     * @param experimentId
     * @return isActive
     */
    public boolean isExperimentActive(String experimentId) {
        GoogleExperimentsItem experiment = getExperiment(experimentId);

        if (experiment == null) {
            return false;
        }

        if (experiment.getStatus().equals(GoogleExperimentsItem.RUNNING_STATUS)) {
            return true;
        }

        return false;
    }

    /**
     * @param experimentId
     * @param variationName
     * @return isActive
     */
    public boolean isVariationActive(String experimentId, String variationName) {
        if (!isExperimentActive(experimentId)) {
            return false;
        }

        GoogleExperimentsItem experiment = getExperiment(experimentId);

        if (experiment.getVariations() == null || experiment.getVariations().size() == 0) {
            return false;
        }

        for (GoogleExperimentsVariation variation : experiment.getVariations()) {
            if (variation.getName() == null || variation.getStatus() == null) {
                continue;
            }

            if (variation.getName().equals(variationName)
                    && variation.getStatus().equals(GoogleExperimentsVariation.ACTIVE_STATUS)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param experimentId
     * @return isActive
     */
    public boolean hasActiveVariations(String experimentId) {
        if (!isExperimentActive(experimentId)) {
            return false;
        }

        GoogleExperimentsItem experiment = getExperiment(experimentId);

        if (experiment.getVariations() == null || experiment.getVariations().size() == 0) {
            return false;
        }

        for (GoogleExperimentsVariation variation : experiment.getVariations()) {
            if (variation.getName() == null || variation.getStatus() == null) {
                continue;
            }

            if (variation.getStatus().equals(GoogleExperimentsVariation.ACTIVE_STATUS)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param experimentId
     * @return variation
     */
    public GoogleExperimentsVariation selectVariation(String experimentId) {
        if (!isExperimentActive(experimentId)) {
            return null;
        }

        if (!hasActiveVariations(experimentId)) {
            return null;
        }

        GoogleExperimentsItem experiment = getExperiment(experimentId);
        return GoogleExperimentsVariation.selectWeightedRandom(experiment.getVariations());
    }

    /**
     * @param experimentId
     * @param variationName
     * @return variationIndex
     */
    public Integer getVariationIndex(String experimentId, String variationName) {
        if (!isExperimentActive(experimentId)) {
            return null;
        }

        if (!hasActiveVariations(experimentId)) {
            return null;
        }

        GoogleExperimentsItem experiment = getExperiment(experimentId);
        int index = 0;

        for (GoogleExperimentsVariation variation : experiment.getVariations()) {
            if (variation.getName().equals(variationName)) {
                return index;
            }

            index++;
        }

        return null;
    }

    @Override
    public String toString() {
        return "GoogleExperimentsResult{" +
                "_kind='" + _kind + '\'' +
                ", _username='" + _username + '\'' +
                ", _totalResults=" + _totalResults +
                ", _startIndex=" + _startIndex +
                ", _itemsPerPage=" + _itemsPerPage +
                ", _items=" + _items +
                '}';
    }
}
