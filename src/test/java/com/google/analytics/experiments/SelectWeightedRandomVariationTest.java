package com.google.analytics.experiments;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class SelectWeightedRandomVariationTest {
	private static Logger LOGGER = LoggerFactory.getLogger(SelectWeightedRandomVariationTest.class);

	@Test
	public void selectWeightedRandomVariation() {
		GoogleExperimentsVariation variation1 = new GoogleExperimentsVariation(
				"variation1",
				"http://domain.com/variation1",
				GoogleExperimentsVariation.ACTIVE_STATUS,
				BigDecimal.valueOf(0.1)
		);

		GoogleExperimentsVariation variation2 = new GoogleExperimentsVariation(
				"variation2",
				"http://domain.com/variation2",
				GoogleExperimentsVariation.ACTIVE_STATUS,
				BigDecimal.valueOf(0.9)
		);

		GoogleExperimentsVariation variationDisabled = new GoogleExperimentsVariation(
				"disabled",
				"http://domain.com/disabled",
				"INACTIVE",
				BigDecimal.valueOf(1)
		);

		List<GoogleExperimentsVariation> variations = Arrays.asList(variation1, variation2, variationDisabled);
		int totalSelections = 99999;
		int timesVariation1Selected = 0;
		int timesVariation2Selected = 0;
		int timesVariationDisabledSelected = 0;

		for (int i = 0; i < totalSelections; i++) {
			GoogleExperimentsVariation randomSelectedVariation =
					GoogleExperimentsVariation.selectWeightedRandom(variations);

			if (randomSelectedVariation.getName().equals("variation1")) {
				timesVariation1Selected++;
			}

			if (randomSelectedVariation.getName().equals("variation2")) {
				timesVariation2Selected++;
			}

			if (randomSelectedVariation.getName().equals("disabled")) {
				timesVariationDisabledSelected++;
			}
		}

		LOGGER.debug("Variation1 selected {} of {} total", timesVariation1Selected, totalSelections);
		float variation1Percent = (timesVariation1Selected * 100.0f) / totalSelections;
		LOGGER.debug("Variation1 selected {}% of the time", variation1Percent);
		Assert.assertTrue(variation1Percent > 9f);
		Assert.assertTrue(variation1Percent < 11f);

		LOGGER.debug("Variation2 selected {} of {} total", timesVariation2Selected, totalSelections);
		float variation2Percent = (timesVariation2Selected * 100.0f) / totalSelections;
		LOGGER.debug("Variation1 selected {}% of the time", variation2Percent);
		Assert.assertTrue(variation2Percent > 89f);
		Assert.assertTrue(variation2Percent < 91f);

		Assert.assertEquals(0, timesVariationDisabledSelected);
	}
}
