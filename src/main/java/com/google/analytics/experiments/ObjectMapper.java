package com.google.analytics.experiments;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
class ObjectMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapper.class);
	private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER =
			new com.fasterxml.jackson.databind.ObjectMapper();

	static {
		MAPPER.registerModule(new JavaTimeModule());
	}

	/**
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return hydrated
	 */
	public static <T> T readValue(String json, Class<T> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Throwable ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * Makes JSON pretty
	 *
	 * @param json
	 * @return prettyJson
	 */
	public static String prettify(String json) {
		if (json == null || json.trim().isEmpty()) {
			return null;
		}

		try {
			JsonNode jsonNode = MAPPER.readValue(json.getBytes(), JsonNode.class);
			return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
		} catch (Throwable ex) {
			LOGGER.error(ex.getMessage(), ex);
			return json;
		}
	}
}

