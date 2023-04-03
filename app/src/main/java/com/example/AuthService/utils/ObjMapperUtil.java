package com.example.AuthService.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjMapperUtil<T> {
    
    // Any object to map converter
    public static <T> Map<String, Object> convertToMap(T object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }
}
