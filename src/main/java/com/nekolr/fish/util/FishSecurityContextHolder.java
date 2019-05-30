package com.nekolr.fish.util;

import com.nekolr.fish.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class FishSecurityContextHolder {

    @Autowired
    private I18nUtils i18nUtils;

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.unauthorized"));
        }

        return userDetails;
    }
}
