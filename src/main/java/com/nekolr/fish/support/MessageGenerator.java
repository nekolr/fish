package com.nekolr.fish.support;

import com.nekolr.fish.util.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageGenerator {

    @Autowired
    private I18nUtils i18nUtils;

    /**
     * 生成实体已经存在的错误消息
     *
     * @param clazz          实体
     * @param saveBodyParams 对应生成 Map 的 key/value 数组
     * @return
     */
    public String generateEntityExistMessage(Class clazz, Object... saveBodyParams) {
        return i18nUtils.getMessage("exceptions.entity_exist", new Object[]{StringUtils.capitalize(clazz.getSimpleName()), MapUtils.toMap(String.class, String.class, saveBodyParams)});
    }
}
