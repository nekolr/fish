package com.nekolr.fish.util;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MapUtils {

    /**
     * @param keyType        生成 Map 的 key 的类型
     * @param valueType      生成 Map 的 value 的类型
     * @param saveBodyParams 将要填充到 Map 中的 key/value 数组
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object[] saveBodyParams) {
        if (saveBodyParams.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid saveBodyParams");
        }
        return IntStream.range(0, saveBodyParams.length / 2).map(i -> i * 2)
                .collect(HashMap::new, (m, i) -> m.put(keyType.cast(saveBodyParams[i]), valueType.cast(saveBodyParams[i + 1])), Map::putAll);
    }
}
