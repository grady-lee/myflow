package com.lee.utils;

import java.lang.reflect.Field;

public class EnumUtils {

    public static void handleEnum(Object bean) {
        if (bean == null) return;

        for (Field field : bean.getClass().getDeclaredFields()) {
            if (!field.getType().isEnum()) continue;

            field.setAccessible(true);
            try {
                Object raw = field.get(bean);
                if (raw == null) continue;

                // raw 可能是 Integer / Boolean / String
                String code;
                if (raw instanceof Boolean) {
                    code = ((Boolean) raw) ? "1" : "0";
                } else {
                    code = raw.toString();
                }

                Object enumValue = field.getType()
                        .getMethod("fromCode", Integer.class)
                        .invoke(null, code);

                field.set(bean, enumValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

