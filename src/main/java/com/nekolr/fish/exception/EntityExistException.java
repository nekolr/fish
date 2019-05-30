package com.nekolr.fish.exception;

import com.nekolr.fish.util.MapUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;

/**
 * 实体已经存在异常
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, Object... saveBodyParams) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), MapUtils.toMap(String.class, String.class, saveBodyParams)));
    }

    private static String generateMessage(String entity, Map<String, String> saveBodyParams) {
        return StringUtils.capitalize(entity) + saveBodyParams + " existed";
    }

}
