package com.google.analytics.experiments;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleExperimentsVariation {
    public static final String ACTIVE_STATUS = "ACTIVE";
    private final String _name;
    private final String _url;
    private final String _status;
    private final BigDecimal _weight;

    public static GoogleExperimentsVariation selectWeightedRandom(List<GoogleExperimentsVariation> variations) {
        if (variations == null || variations.size() == 0) {
            return null;
        }

        RandomVariationSelector selector = new RandomVariationSelector();

        for (GoogleExperimentsVariation variation : variations) {
            if (variation.getWeight() == null
                    || variation.getWeight().equals(BigDecimal.ZERO)
                    || variation.getStatus() == null
                    || !variation.getStatus().equals(ACTIVE_STATUS)) {
                continue;
            }

            selector.add(variation);
        }

        if (selector.map.size() == 0) {
            return null;
        }

        return selector.next();
    }

    public GoogleExperimentsVariation(@JsonProperty("name") final String name,
                                      @JsonProperty("url") final String url,
                                      @JsonProperty("status") final String status,
                                      @JsonProperty("weight") final BigDecimal weight) {
        _name = name;
        _url = url;
        _status = status;
        _weight = weight;
    }

    public String getName() {
        return _name;
    }

    public String getUrl() {
        return _url;
    }

    public String getStatus() {
        return _status;
    }

    public BigDecimal getWeight() {
        return _weight;
    }

    @Override
    public String toString() {
        return "GoogleExperimentsVariation{" +
                "_name='" + _name + '\'' +
                ", _url='" + _url + '\'' +
                ", _status='" + _status + '\'' +
                ", _weight=" + _weight +
                '}';
    }

    private static class RandomVariationSelector {
        private final NavigableMap<BigDecimal, GoogleExperimentsVariation> map =
                new TreeMap<BigDecimal, GoogleExperimentsVariation>();
        private final SecureRandom random;
        private BigDecimal total = BigDecimal.ZERO;

        public RandomVariationSelector() {
            this(new SecureRandom());
        }

        public RandomVariationSelector(SecureRandom random) {
            this.random = random;
        }

        public void add(GoogleExperimentsVariation variation) {

            if (variation == null
                    || variation.getWeight() == null
                    || variation.getWeight().compareTo(BigDecimal.ZERO) < 0) {
                return;
            }

            total = total.add(variation.getWeight());
            map.put(total, variation);
        }

        public GoogleExperimentsVariation next() {
            double value = random.nextDouble() * total.doubleValue();
            return map.ceilingEntry(BigDecimal.valueOf(value)).getValue();
        }
    }
}
