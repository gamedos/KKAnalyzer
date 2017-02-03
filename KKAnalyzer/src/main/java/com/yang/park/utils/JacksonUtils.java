package com.yang.park.utils;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.type.TypeReference;

public class JacksonUtils {
	private static ObjectMapper objectMapper;

	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new IllegalArgumentException("wrong obejct: " + object, e);
		}
	}

	public static String toJsonString(Object object, Class<?> viewClass) {
		try {
			ObjectWriter writer = objectMapper.writerWithView(viewClass);

			return writer.writeValueAsString(object);
		} catch (Exception e) {
			throw new IllegalArgumentException("wrong obejct: " + object, e);
		}
	}

	public static String toJsonString(Object object, FilterProvider filters) {
		try {
			ObjectWriter writer = objectMapper.writer(filters);
			return writer.writeValueAsString(object);
		} catch (Exception e) {
			throw new IllegalArgumentException("wrong obejct: " + object, e);
		}
	}

	public static <T> T toObject(String jsonString, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			throw new IllegalArgumentException("wrong json string: " + jsonString, e);
		}
	}

	public static <T> T toObject(String jsonString, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(jsonString, typeReference);
		} catch (Exception e) {
			throw new IllegalArgumentException("wrong json string: " + jsonString, e);
		}
	}

	static {
		JsonFactory jsonFactory = new MappingJsonFactory();
		jsonFactory.configure(JsonParser.Feature.INTERN_FIELD_NAMES, false);
		jsonFactory.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, false);

		objectMapper = new ObjectMapper(jsonFactory);
//		objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, false);
	}
}