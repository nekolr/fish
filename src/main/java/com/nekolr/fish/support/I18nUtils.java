package com.nekolr.fish.support;

import cn.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化消息工具
 *
 * @author nekolr
 */
@Component
public class I18nUtils {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取消息
     *
     * @param code 消息编码
     * @return
     */
    public String getMessage(String code) {
        return this.getMessage(code, null);
    }

    /**
     * 获取消息
     *
     * @param code 消息编码
     * @param args 参数值
     * @return
     */
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }

    /**
     * 获取消息
     *
     * @param code           消息编码
     * @param args           参数值
     * @param defaultMessage 默认消息值
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        Assert.notNull(messageSource);
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }
}
