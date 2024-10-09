package com.backend.tms.comm.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapValueExtractorUtil {


    /*
     * Not creating this file common because it requires DateTimeUtil which is placed in commonservices
     * We have dependency of common in commonservice not the other way around
     * We can't move DateTimeUtil in commons because it uses property value and is marked with @Service annotation and is not completely a Util class
     * Moving DateTimeUtil in common creates other dependencies for crm and to security to inject irrelevant beans.
     * */

    private MapValueExtractorUtil() {
    }

    public static String getValueAsString(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return (value != null) ? value.toString() : null;
    }

    public static String getValueAsStringWithBackup(Map<String, Object> data, String primaryKey, String backupKey) {
        String value = getValueAsString(data, primaryKey);
        return (value != null) ? value : getValueAsString(data, backupKey);
    }

    public static boolean getValueAsBoolean(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        return false;
    }

    public static BigDecimal getValueAsBigDecimal(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Number) {
            return new BigDecimal(value.toString());
        }
        return BigDecimal.ZERO;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getValueAsMap(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        }
        return Collections.emptyMap();
    }

    public static LocalDate noSQLToLocalDate(Map<String, Object> data, String dateKey) {
        Map<String, Object> dateMap = getValueAsMap(data, dateKey);
        if (dateMap.isEmpty()) {
            return null;
        }
        return DateTimeUtil.noSQLToLocalDate(dateMap);
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getValueAsListOfMap(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            if (!list.isEmpty() && list.get(0) instanceof Map) {
                return (List<Map<String, Object>>) list;
            }
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getValueAsListOfObject(Map<String, Object> data, String key, Class<T> clazz) {
        Object value = data.get(key);
        if (value instanceof List<?>) {
            List<?> list = (List<?>) value;
            if (clazz.isInstance(list.get(0))) {
                return (List<T>) list;
            }
        }
        return Collections.emptyList();
    }

    public static Integer getValueAsInteger(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value != null) {
            try {
                return Integer.valueOf(value.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
