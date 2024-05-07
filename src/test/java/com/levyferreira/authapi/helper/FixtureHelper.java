package com.levyferreira.authapi.helper;

import java.lang.reflect.Field;

public class FixtureHelper {
    public static void setField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set field value", e);
        }
    }
}
