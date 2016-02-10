package com.google.analytics.experiments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsCacheResult implements Serializable{

    private String _json;
    private LocalDateTime _dateLastUpdated;

    public GoogleExperimentsCacheResult(GoogleExperimentsResult googleExperimentsResult) {
        this._json = googleExperimentsResult.getJson();
        this._dateLastUpdated = LocalDateTime.now();
    }

    public GoogleExperimentsResult getGoogleExperimentsResult() {
        if (_json == null) {
            return null;
        }

        GoogleExperimentsResult result = ObjectMapper.readValue(_json, GoogleExperimentsResult.class);
        result.setJson(_json);
        return result;
    }

    public LocalDateTime getDateLastUpdated() {
        return _dateLastUpdated;
    }

    public String get_json() {
        return _json;
    }

    public GoogleExperimentsCacheResult set_json(String _json) {
        this._json = _json;
        return this;
    }

    public LocalDateTime get_dateLastUpdated() {
        return _dateLastUpdated;
    }

    public GoogleExperimentsCacheResult set_dateLastUpdated(LocalDateTime _dateLastUpdated) {
        this._dateLastUpdated = _dateLastUpdated;
        return this;
    }
}
