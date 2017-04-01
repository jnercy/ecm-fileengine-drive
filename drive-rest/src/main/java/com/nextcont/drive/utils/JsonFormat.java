package com.nextcont.drive.utils;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by wangxudong on 2015/12/23 0023.
 */
public class JsonFormat {

    public static ObjectMapper objectMapper = new ObjectMapper();


    static{
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        objectMapper.setSerializationInclusion(Include.NON_EMPTY).configure(JsonParser.Feature.ALLOW_COMMENTS, true)
		.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
		.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
		.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
		.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }


    public static <V> Optional<String> convertJson(List<V> list){
        try {
            return Optional.ofNullable(JsonFormat.objectMapper.writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <V,R> Optional<String> convertJson(Map<V,R> map){
        try {
            return Optional.ofNullable(JsonFormat.objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <V> Optional<String> convertJson(V v){
        try {
            return Optional.ofNullable(JsonFormat.objectMapper.writeValueAsString(v));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
	public static <V> Optional<V> convert2Object(String json, V v){
        try {
            return Optional.ofNullable((V) objectMapper.readValue(json,v.getClass()));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <V> Optional<V> convert2Object(String json, Class<V> jsonClass){
        try {
                return Optional.ofNullable(objectMapper.readValue(json,jsonClass));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static String toJson(Object object) {
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return returnStr;
    }

}
