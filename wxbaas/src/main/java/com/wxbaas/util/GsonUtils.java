package com.wxbaas.util;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * GsonUtil
 * 
 * @author xuewen.zhangxuewen
 */
public class GsonUtils {
	final private static Logger logger = LoggerFactory.getLogger(GsonUtils.class);
	private static final GsonBuilder gb = new GsonBuilder();
	private static final Gson json = new Gson();
	private static Gson json2 = gb.create();
	/**
	 * test
	 */
	static {
		// gb.serializeNulls();
		gb.registerTypeAdapter(new TypeToken<List<Map<String, Object>>>() {
		}.getType(), new JsonDeserializer<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> deserialize(JsonElement json, Type typeOfT,
					JsonDeserializationContext context) throws JsonParseException {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = null;
				JsonArray arr = json.getAsJsonArray();
				Iterator<JsonElement> jit = arr.iterator();
				while (jit.hasNext()) {
					map = new HashMap<String, Object>();
					JsonElement jel = jit.next();
					Iterator<Entry<String, JsonElement>> it = jel.getAsJsonObject().entrySet().iterator();
					Entry<String, JsonElement> item = null;
					String key = null;
					JsonElement je = null;
					while (it.hasNext()) {
						item = it.next();
						key = item.getKey();
						je = item.getValue();
						if (!je.isJsonNull() && je.isJsonPrimitive() && ((JsonPrimitive) je).isNumber()) {
							try {
								map.put(key, je.getAsInt());
							} catch (Exception ex) {
								map.put(key, context.deserialize(je, Object.class));
							}
						} else {
							map.put(key, context.deserialize(je, Object.class));
						}
					}
					list.add(map);
				}
				return list;
			}

		});
		gb.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
		}.getType(), new JsonDeserializer<Map<String, Object>>() {

			@Override
			public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<Entry<String, JsonElement>> it = json.getAsJsonObject().entrySet().iterator();
				Entry<String, JsonElement> item = null;
				String key = null;
				JsonElement je = null;
				while (it.hasNext()) {
					item = it.next();
					key = item.getKey();
					je = item.getValue();
					if (!je.isJsonNull() && je.isJsonPrimitive() && ((JsonPrimitive) je).isNumber()) {
						try {
							map.put(key, je.getAsInt());
						} catch (Exception ex) {
							map.put(key, context.deserialize(je, Object.class));
						}
					} else {
						map.put(key, context.deserialize(je, Object.class));
					}
				}

				return map;
			}
		});
	}

	/**
	 * 
	 * @param type
	 * @param typeAdapter
	 */
	public static void registerTypeAdapter(Type type, Object typeAdapter) {
		gb.registerTypeAdapter(type, typeAdapter);
		json2 = gb.create();
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonDefault(Object o) {
		if (o == null)
			return "{}";
		try {
			return json.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to jsonDefault got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJson(Object o) {
		if (o == null)
			return "{}";
		try {
			return json2.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to json got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonStructDefault(Object o) {
		if (o == null)
			return "{}";
		try {
			return json.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to json got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonStruct(Object o) {
		if (o == null)
			return "{}";
		return json2.toJson(o);
	}

	/**
	 * 
	 * @param jsonStr
	 * @param t
	 * @return
	 */
	public static <T> T fromJsonDefault(String jsonStr, Class<T> t) {
		if (jsonStr == null)
			return null;
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonStr));
			reader.setLenient(true);
			return json.fromJson(reader, t);
		} catch (Throwable ex) {
			logger.error("From json[" + jsonStr + "] to Class[" + t.getName() + "] got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param jsonStr
	 * @param t
	 * @return
	 */
	public static <T> T fromJson(String jsonStr, Class<T> t) {
		if (jsonStr == null)
			return null;
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonStr));
			reader.setLenient(true);
			return json2.fromJson(reader, t);
		} catch (Throwable ex) {
			logger.error("From json[" + jsonStr + "] to Class[" + t + "] got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static <T> T fromJsonDefault(String jsonStr, Type type) {
		if (jsonStr == null)
			return null;
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonStr));
			reader.setLenient(true);
			return json.fromJson(reader, type);
		} catch (Throwable ex) {
			logger.error("From jsonDefault[" + jsonStr + "] to Class[" + type + "] got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String jsonStr, Type type) {
		if (jsonStr == null)
			return null;
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonStr));
			reader.setLenient(true);
			return json2.fromJson(reader, type);
		} catch (Throwable ex) {
			logger.error("From json[" + jsonStr + "] to Class[" + type + "] got exception", ex);
		}
		return null;
	}
}
