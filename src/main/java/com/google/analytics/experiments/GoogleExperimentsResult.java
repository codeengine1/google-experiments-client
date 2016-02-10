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
