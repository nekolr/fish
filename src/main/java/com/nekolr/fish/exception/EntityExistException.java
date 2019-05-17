package com.nekolr.fish.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 实体已经存在异常
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, Object... saveBodyParams) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, saveBodyParams)));
    }

    private static String generateMessage(String entity, Map<String, String> saveBodyParams) {
        return StringUtils.capitalize(entity) + saveBodyParams + " existed";
    }

    /**
     * @param keyType        生成 Map 的 key 的类型
     * @param valueType      生成 Map 的 value 的类型
     * @param saveBodyParams 将要填充到 Map 中的 key/value 数组
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object[] saveBodyParams) {
        if (saveBodyParams.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid saveBodyParams");
        }
        return IntStream.range(0, saveBodyParams.length / 2).map(i -> i * 2)
                .collect(HashMap::new, (m, i) -> m.put(keyType.cast(saveBodyParams[i]), valueType.cast(saveBodyParams[i + 1])), Map::putAll);
    }
}
