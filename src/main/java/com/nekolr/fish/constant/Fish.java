package com.nekolr.fish.constant;

/**
 * 全局常量
 */
public class Fish {

    /**
     * 令牌过期时间，单位秒
     */
    public static final long JWT_PERIOD = 3600L;

    /**
     * JWT 签发者
     */
    public static final String JWT_ISSUER = "fish-server";

    /**
     * Token 在请求头中的前缀
     */
    public static final String TOKEN_HEADER_VALUE_PREFIX = "Bearer ";

    /**
     * Token 在请求头中的 key
     */
    public static final String TOKEN_HEADER_KEY = "Authorization";

    /**
     * 错误返回消息：没有凭证
     */
    public static final String ERROR_MSG_UNAUTHORIZED = "Unauthorized";

    /**
     * 加密私钥（当判断私钥有泄漏的风险时请修改它）
     */
    public static final String JWT_SECRET = ":xgj%eMd#gk+wh.`t2;XW!.dIuC&$#Lua+;%~!F=" +
            "G&16Eo7b3o|GudHr%:?ijHQ3=G&:hVFcnQV?57f*)p!wNMG*Sfz%:pSU~5n,J|G%ro1blr'*'yD&z@Y&1Aa|=Bu:k";

}
