package com.google.analytics.experiments;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleExperimentsItem {

	private final String _id;
	private final String _kind;
	private final String _selfLink;
	private final String _accountId;
	private final String _webPropertyId;
	private final String _internalWebPropertyId;
	private final String _profileId;
	private final String _name;
	private final String _description;
	private final ZonedDateTime _created;
	private final ZonedDateTime _updated;
	private final String _objectiveMetric;
	private final String _optimizationType;
	private final String _status;
	private final Boolean _winnerFound;
	private final ZonedDateTime _startTime;
	private final Boolean _rewriteVariationUrlsAsOriginal;
	private final BigDecimal _winnerConfidenceLevel;
	private final Integer _minimumExperimentLengthInDays;
	private final BigDecimal _trafficCoverage;
	private final Boolean _equalWeighting;
	private final String _snippet;
	private final List<GoogleExperimentsVariation> _variations;
	private final String _servingFramework;
	private final Boolean editableInGaUi;

	public GoogleExperimentsItem(@JsonProperty("id") String id,
								 @JsonProperty("kind") String kind,
								 @JsonProperty("selfLink") String selfLink,
								 @JsonProperty("accountId") String accountId,
								 @JsonProperty("webPropertyId") String webPropertyId,
								 @JsonProperty("internalWebPropertyId") String internalWebPropertyId,
								 @JsonProperty("profileId") String profileId,
								 @JsonProperty("name") String name,
								 @JsonProperty("description") String description,
								 @JsonProperty("created") ZonedDateTime created,
								 @JsonProperty("updated") ZonedDateTime updated,
								 @JsonProperty("objectiveMetric") String objectiveMetric,
								 @JsonProperty("optimizationType") String optimizationType,
								 @JsonProperty("status") String status,
								 @JsonProperty("winnerFound") Boolean winnerFound,
								 @JsonProperty("startTime") ZonedDateTime startTime,
								 @JsonProperty("rewriteVariationUrlsAsOriginal") Boolean rewriteVariationUrlsAsOriginal,
								 @JsonProperty("winnerConfidenceLevel") BigDecimal winnerConfidenceLevel,
								 @JsonProperty("minimumExperimentLengthInDays") Integer minimumExperimentLengthInDays,
								 @JsonProperty("trafficCoverage") BigDecimal trafficCoverage,
								 @JsonProperty("equalWeighting") Boolean equalWeighting,
								 @JsonProperty("snippet") String snippet,
								 @JsonProperty("variations") List<GoogleExperimentsVariation> variations,
								 @JsonProperty("servingFramework") String servingFramework,
								 @JsonProperty("editableInGaUi") Boolean editableInGaUi) {
		_id = id;
		_kind = kind;
		_selfLink = selfLink;
		_accountId = accountId;
		_webPropertyId = webPropertyId;
		_internalWebPropertyId = internalWebPropertyId;
		_profileId = profileId;
		_name = name;
		_description = description;
		_created = created;
		_updated = updated;
		_objectiveMetric = objectiveMetric;
		_optimizationType = optimizationType;
		_status = status;
		_winnerFound = winnerFound;
		_startTime = startTime;
		_rewriteVariationUrlsAsOriginal = rewriteVariationUrlsAsOriginal;
		_winnerConfidenceLevel = winnerConfidenceLevel;
		_minimumExperimentLengthInDays = minimumExperimentLengthInDays;
		_trafficCoverage = trafficCoverage;
		_equalWeighting = equalWeighting;
		_snippet = snippet;
		_variations = variations;
		_servingFramework = servingFramework;
		this.editableInGaUi = editableInGaUi;
	}

	public String getId() {
		return _id;
	}

	public String getKind() {
		return _kind;
	}

	public String getSelfLink() {
		return _selfLink;
	}

	public String getAccountId() {
		return _accountId;
	}

	public String getWebPropertyId() {
		return _webPropertyId;
	}

	public String getInternalWebPropertyId() {
		return _internalWebPropertyId;
	}

	public String getProfileId() {
		return _profileId;
	}

	public String getName() {
		return _name;
	}

	public String getDescription() {
		return _description;
	}

	public ZonedDateTime getCreated() {
		return _created;
	}

	public ZonedDateTime getUpdated() {
		return _updated;
	}

	public String getObjectiveMetric() {
		return _objectiveMetric;
	}

	public String getOptimizationType() {
		return _optimizationType;
	}

	public String getStatus() {
		return _status;
	}

	public Boolean getWinnerFound() {
		return _winnerFound;
	}

	public ZonedDateTime getStartTime() {
		return _startTime;
	}

	public Boolean getRewriteVariationUrlsAsOriginal() {
		return _rewriteVariationUrlsAsOriginal;
	}

	public BigDecimal getWinnerConfidenceLevel() {
		return _winnerConfidenceLevel;
	}

	public Integer getMinimumExperimentLengthInDays() {
		return _minimumExperimentLengthInDays;
	}

	public BigDecimal getTrafficCoverage() {
		return _trafficCoverage;
	}

	public Boolean getEqualWeighting() {
		return _equalWeighting;
	}

	public String getSnippet() {
		return _snippet;
	}

	public List<GoogleExperimentsVariation> getVariations() {
		return _variations;
	}

	public String getServingFramework() {
		return _servingFramework;
	}

	public Boolean getEditableInGaUi() {
		return editableInGaUi;
	}

	public GoogleExperimentsVariation selectRandomWeightedVariation() {
		return GoogleExperimentsVariation.selectWeightedRandom(_variations);
	}

	@Override
	public String toString() {
		return "GoogleExperimentsItem{" +
				"_id='" + _id + '\'' +
				", _kind='" + _kind + '\'' +
				", _selfLink='" + _selfLink + '\'' +
				", _accountId='" + _accountId + '\'' +
				", _webPropertyId='" + _webPropertyId + '\'' +
				", _internalWebPropertyId='" + _internalWebPropertyId + '\'' +
				", _profileId='" + _profileId + '\'' +
				", _name='" + _name + '\'' +
				", _description='" + _description + '\'' +
				", _created=" + _created +
				", _updated=" + _updated +
				", _objectiveMetric='" + _objectiveMetric + '\'' +
				", _optimizationType='" + _optimizationType + '\'' +
				", _status='" + _status + '\'' +
				", _winnerFound=" + _winnerFound +
				", _startTime=" + _startTime +
				", _rewriteVariationUrlsAsOriginal=" + _rewriteVariationUrlsAsOriginal +
				", _winnerConfidenceLevel=" + _winnerConfidenceLevel +
				", _minimumExperimentLengthInDays=" + _minimumExperimentLengthInDays +
				", _trafficCoverage=" + _trafficCoverage +
				", _equalWeighting=" + _equalWeighting +
				", _snippet='" + _snippet + '\'' +
				", _variations=" + _variations +
				", _servingFramework='" + _servingFramework + '\'' +
				", editableInGaUi=" + editableInGaUi +
				'}';
	}
}
